package cz.mawalgar.gui.jsf.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import cz.mawalgar.services.dao.IpViewsDao;
import cz.mawalgar.services.dao.MawaCharactersDao;
import cz.mawalgar.services.dao.PageViewsDao;
import cz.mawalgar.services.dao.dto.ServerStatus;
import cz.mawalgar.services.dao.en.IpView;
import cz.mawalgar.services.dao.en.MawaCharacter;
import cz.mawalgar.services.dao.en.PageView;
import cz.mawalgar.services.server.MawalgarPing;

/**
 * @author Martin Volek
 * 29. 4. 2020
 */
@Named
@ApplicationScoped
public class ServerStatusBean implements Serializable {

  private static final long serialVersionUID = 6046098905056312440L;

  @Inject
  private ServerStatus serverStatus;

  @Inject
  private MawaCharactersDao mawaCharactersDao;

  @Inject
  private PageViewsDao pageViewsDao;

  @Inject
  private IpViewsDao ipViewsDao;

  @Inject
  private MawalgarPing mawalgarPing;

  private boolean authServerStatus;

  private List<MawaCharacter> playersOnline = new ArrayList<>();

  private List<MawaCharacter> allPlayers = new ArrayList<>();

  private Integer onlinePlayersCount;

  private Integer allPlayersCount;

  private Integer alliancePercentage;

  private Integer hordePercentage;

  private PageView pageView;

  private List<IpView> ipViews = new ArrayList<>();

  private List<MawaCharacter> topPlayed = new ArrayList<>();

  private List<MawaCharacter> topKiller = new ArrayList<>();

  private List<MawaCharacter> todayTopKiller = new ArrayList<>();


  @PostConstruct
  public void init() {
    this.serverStatus.setAuthServerStatus(this.mawalgarPing.hostAvailabilityCheck("0.0.0.0", 8085));
    this.serverStatus.setAllCharacters(this.mawaCharactersDao.getAllCharacters());
    this.serverStatus.setAllCharacters(this.mawaCharactersDao.getAllCharacters());
    Optional<PageView> view  = this.pageViewsDao.getPageView();
    if (view.isPresent()) {
      this.serverStatus.setPageView(view.get());
    }

    this.onlinePlayersCount = (int) this.allPlayers.stream().filter(b -> b.isOnline()).count();
    this.loadServerStatus();
  }


  public void loadServerStatus() {
    this.authServerStatus = this.serverStatus.isAuthServerStatus();
    this.allPlayers = this.serverStatus.getAllCharacters();
    this.pageView = this.serverStatus.getPageView();

    this.playersOnline = this.allPlayers.stream().filter(b -> b.isOnline()).collect(Collectors.toList());
    this.onlinePlayersCount = this.playersOnline.size();
    this.allPlayersCount = this.allPlayers.size();
    this.topPlayed  = this.allPlayers.stream().sorted(Comparator.comparingInt(MawaCharacter::getTotaltime).reversed()).limit(5).collect(Collectors.toList());
    this.topKiller = this.allPlayers.stream().sorted(Comparator.comparingInt(MawaCharacter::getTotalKills).reversed()).limit(5).collect(Collectors.toList());
    this.todayTopKiller = this.allPlayers.stream().sorted(Comparator.comparingInt(MawaCharacter::getTodayKills).reversed()).limit(5).collect(Collectors.toList());
    this.calculatePercentage();
  }

  private void calculatePercentage() {
    if (this.onlinePlayersCount == 0) {
      this.alliancePercentage = 0;
      this.hordePercentage = 0;
      return;
    }
    final Long allianceCount = this.playersOnline.parallelStream().filter(b -> Objects.equals(b.getRace().getFaction(), "Alliance")).count();
    final float alliancePerc = (float) ((allianceCount*100)/this.onlinePlayersCount);
    this.alliancePercentage = new BigDecimal(alliancePerc).setScale(0, RoundingMode.HALF_UP).intValueExact();
    this.hordePercentage = 100 - this.alliancePercentage;
  }

  public void incrementIp(final String ip) {
    if(this.ipViews.isEmpty()) {
      this.ipViews = this.ipViewsDao.getIpViews();
    }
    if (this.alreadyExist(ip) || StringUtils.isBlank(ip)) {
      return;
    }
    final IpView newIpView = new IpView();
    newIpView.setIp(ip);
    final IpView createdIpView = this.ipViewsDao.createIpView(newIpView);
    this.ipViews.add(createdIpView);
  }

  private boolean alreadyExist(final String ip) {
   return this.ipViews.stream().anyMatch(b -> Objects.equals(b.getIp(), ip));
  }


  public boolean isAuthServerStatus() {
    return authServerStatus;
  }


  public List<MawaCharacter> getPlayersOnline() {
    return playersOnline;
  }


  public List<MawaCharacter> getAllPlayers() {
    return allPlayers;
  }


  public Integer getOnlinePlayersCount() {
    return onlinePlayersCount;
  }


  public Integer getAllPlayersCount() {
    return allPlayersCount;
  }


  public List<MawaCharacter> getTopPlayed() {
    return topPlayed;
  }


  public List<MawaCharacter> getTopKiller() {
    return topKiller;
  }


  public List<MawaCharacter> getTodayTopKiller() {
    return todayTopKiller;
  }


  public Integer getAlliancePercentage() {
    return alliancePercentage;
  }


  public Integer getHordePercentage() {
    return hordePercentage;
  }


  public PageView getPageView() {
    return pageView;
  }


  public Integer getIpViewsCount() {
    return ipViews.size();
  }


}
