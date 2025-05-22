package cz.mawalgar.services.dao.en;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import cz.mawalgar.services.dao.conv.BooleanToIntConverter;

/**
 * @author Martin Volek
 *         Created: 07.02.2020
 */
@Table(name = "Comments")
@Entity
public class Comment implements Serializable {

  private static final long serialVersionUID = 9221553763926810804L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "userId")
  private Integer userId;

  @Column(name = "newsId")
  private Integer newsId;

  @Column(name = "timeCreated")
  private LocalDateTime timeCreated;

  @Column(name = "timeUpdated")
  private LocalDateTime timeUpdated;

  @Column(name = "text")
  private String text;

  @Column(name = "edited")
  @Convert(converter = BooleanToIntConverter.class)
  private boolean edited;


  public Integer getId() {
    return id;
  }


  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getUserId() {
    return userId;
  }


  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public Integer getNewsId() {
    return newsId;
  }


  public void setNewsId(Integer newsId) {
    this.newsId = newsId;
  }


  public LocalDateTime getTimeCreated() {
    return timeCreated;
  }


  public void setTimeCreated(LocalDateTime timeCreated) {
    this.timeCreated = timeCreated;
  }


  public LocalDateTime getTimeUpdated() {
    return timeUpdated;
  }


  public void setTimeUpdated(LocalDateTime timeUpdated) {
    this.timeUpdated = timeUpdated;
  }


  public String getText() {
    return text;
  }


  public void setText(String text) {
    this.text = text;
  }


  public boolean isEdited() {
    return edited;
  }


  public void setEdited(boolean edited) {
    this.edited = edited;
  }


  /**
   * This object attributes as string.
   */
  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

}
