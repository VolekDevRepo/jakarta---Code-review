package cz.mawalgar.gui.jsf.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import cz.mawalgar.gui.jsf.utils.DialogOptions;
import cz.mawalgar.services.dao.AuthUserDao;
import cz.mawalgar.services.dao.CommentDao;
import cz.mawalgar.services.dao.NewsDao;
import cz.mawalgar.services.dao.en.AuthRole;
import cz.mawalgar.services.dao.en.Comment;
import cz.mawalgar.services.dao.en.News;
import cz.mawalgar.services.dao.to.CommentTO;
import cz.mawalgar.services.dao.to.NewsTO;
import cz.mawalgar.services.util.log.LogLevel;
import cz.mawalgar.services.util.log.Logged;

/**
 * @author morty
 *
 */
@Named
@ApplicationScoped
public class HomeDashboardBean implements Serializable {

  private static final long serialVersionUID = 8262443591619901440L;

  @Inject
  private NewsDao newsDao;

  @Inject
  private AuthUserDao userDao;

  @Inject
  private CommentDao commentDao;

  private List<NewsTO> news = new ArrayList<>();


  /**
   * Initializing the form on viewAction.
   */
  @PostConstruct
  public void init() {
    final List<News> allNews = newsDao.getAllNews();

    allNews.forEach(b -> {
      final NewsTO newsTO = new NewsTO();
      newsTO.setNews(b);
      newsTO.setUser(this.userDao.getUserById(b.getUserId()));
      final List<CommentTO> commentTOs = new ArrayList<>();
      final List<Comment> comments = this.commentDao.getCommentsByNewsId(newsTO.getNews().getId());
      Collections.reverse(comments);
      comments.forEach(c -> {
        CommentTO commentTO = new CommentTO();
        commentTO.setComment(c);
        commentTO = this.recalculateTime(commentTO);
        commentTO.setUser(this.userDao.getUserById(c.getUserId()));
        commentTOs.add(commentTO);
      });
      newsTO.setComments(commentTOs);

      this.news.add(newsTO);
    }
   );
  }


  public void clearList() {
    final List<NewsTO> allNewsTO = new ArrayList<>();
    allNewsTO.addAll(this.news);
    this.news.clear();
    // this.init();
    allNewsTO.forEach(b -> {
     final List<CommentTO> comments = new ArrayList<>();
     b.getComments().forEach(c -> comments.add(this.recalculateTime(c)));
     b.setComments(comments);
     this.news.add(b);
    });
  }


  public void openCreateNewsDialog() {
    final Map<String, Object> options = new HashMap<>();
    options.put("modal", Boolean.TRUE);
    options.put("responsive", Boolean.TRUE);
    options.put("resizable", Boolean.TRUE);
    options.put("draggable", Boolean.TRUE);
    options.put("height", "43vw");
    options.put("width", "40vw");
    options.put("contentWidth", "40vw");
    options.put("contentHeight", "43vw");
    options.put("closeOnEscape", Boolean.TRUE);
    PrimeFaces.current().dialog().openDynamic("/home/adminDialog/createNewsDialog.xhtml", options, null);
  }

  public void openCreateCommentDialog(final Integer newsId) {
    final Map<String, List<String>> params = new HashMap<>();
    params.put("selectedNewsId", Collections.singletonList(String.valueOf(newsId)));
    final Map<String, Object> options = new DialogOptions(DialogOptions.WIDTH_750).getOptions();
    PrimeFaces.current().dialog().openDynamic("/home/dialog/createCommentDialog.xhtml", options, params);
  }

  public void openEditCommentDialog(final Integer commentId) {
    final Map<String, List<String>> params = new HashMap<>();
    params.put("selectedCommentId", Collections.singletonList(String.valueOf(commentId)));
    final Map<String, Object> options = new DialogOptions(DialogOptions.WIDTH_750).getOptions();
    PrimeFaces.current().dialog().openDynamic("/home/dialog/editCommentDialog.xhtml", options, params);
  }


  public void saveNews(final SelectEvent event) {
    final News createdNews = this.newsDao.createNews((News) event.getObject());
    final NewsTO createdNewsTO = new NewsTO();
    createdNewsTO.setNews(createdNews);
    createdNewsTO.setUser(this.userDao.getUserById(createdNews.getUserId()));
    this.news.add(0, createdNewsTO);
  }

  public void deleteComment(final NewsTO newsTO, final CommentTO commentToDelete) {
    this.commentDao.delete(commentToDelete.getComment());
    newsTO.setComments(newsTO.getComments().stream()
        .filter(c -> !c.getComment().getId().equals(commentToDelete.getComment().getId())).collect(Collectors.toList()));
    this.news = this.news.stream()
        .map(c -> Objects.equals(c.getNews().getId(), newsTO.getNews().getId()) ? newsTO : c)
        .collect(Collectors.toList());

//    final FacesMessage message = new FacesMessage(
//        this.mb.getString("role.deleted", toDelete.getCode(), toDelete.getName()), "");
//    this.facesContext.addMessage(null, message);
  }

  public void onCommentChange(final SelectEvent event) {
    final NewsTO newsTO = (NewsTO) event.getObject();
    this.news = this.news.stream()
        .map(c -> Objects.equals(c.getNews().getId(), newsTO.getNews().getId()) ? newsTO : c)
        .collect(Collectors.toList());
  }

  public CommentTO recalculateTime(final CommentTO commentTO) {
  final LocalDateTime localDateTime = commentTO.getComment().getTimeCreated();
  final Long createdSeconds = this.getSeconds(localDateTime);
  final Long nowSeconds = this.getSeconds(LocalDateTime.now());
  final Long seconds = nowSeconds - createdSeconds;
  if (seconds <= 119) {
    commentTO.setCreatedBeforeMinutes(null);
    commentTO.setCreatedBeforeHours(null);
    commentTO.setCreatedBeforeDays(null);
  }
  else if (seconds > 119 && seconds < 3599) {
    commentTO.setCreatedBeforeMinutes(this.roundDown(seconds, ChronoUnit.MINUTES));
    commentTO.setCreatedBeforeHours(null);
    commentTO.setCreatedBeforeDays(null);
  }

  else if (seconds >= 3599 && seconds < 86400) {
    commentTO.setCreatedBeforeMinutes(null);
    commentTO.setCreatedBeforeHours(this.roundDown(seconds, ChronoUnit.HOURS));
    commentTO.setCreatedBeforeDays(null);
  }
  else {
    commentTO.setCreatedBeforeMinutes(null);
    commentTO.setCreatedBeforeHours(null);
    commentTO.setCreatedBeforeDays(this.roundDown(seconds, ChronoUnit.DAYS));
  }
  return commentTO;
}

  private Long getSeconds(final LocalDateTime localDateTime) {
    Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    return instant.toEpochMilli() / 1000;
  }


  private Integer roundDown(final Long seconds, final ChronoUnit chronoUnit) {
    switch (chronoUnit) {
      case MINUTES:
        return new BigDecimal(seconds / 60).setScale(0, RoundingMode.HALF_DOWN).intValueExact();
      case HOURS:
        return new BigDecimal(seconds / 3600).setScale(0, RoundingMode.HALF_DOWN).intValueExact();
      case DAYS:
        return new BigDecimal(seconds / 86400).setScale(0, RoundingMode.HALF_DOWN).intValueExact();
      default:
        return null;
    }
  }


  public List<NewsTO> getNews() {
    return news;
  }


  public void setNews(List<NewsTO> news) {
    this.news = news;
  }


  public String convertDate(final LocalDateTime givenDateTime) {
    if (givenDateTime == null) {
      return "";
    }
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    return formatter.format((LocalDateTime) givenDateTime);
  }
}
