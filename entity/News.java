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
 * Created: 07.02.2020
 */
@Table(name = "News")
@Entity
public class News implements Serializable {

  private static final long serialVersionUID = 9221553763926810804L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "userId")
  private Integer userId;

  @Column(name = "nameCz")
  private String nameCz;

  @Column(name = "nameEn")
  private String nameEn;

  @Column(name = "textCz")
  private String textCz;

  @Column(name = "textEn")
  private String textEn;

  @Column(name = "timeCreated")
  private LocalDateTime timeCreated;

  @Column(name = "timeUpdated")
  private LocalDateTime timeUpdated;

  @Column(name = "commentsAllowed")
  @Convert(converter = BooleanToIntConverter.class)
  private boolean commentsAllowed;


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


  public String getNameCz() {
    return nameCz;
  }

  public void setNameCz(String nameCz) {
    this.nameCz = nameCz;
  }


  public String getNameEn() {
    return nameEn;
  }


  public void setNameEn(String nameEn) {
    this.nameEn = nameEn;
  }


  public String getTextCz() {
    return textCz;
  }


  public void setTextCz(String textCz) {
    this.textCz = textCz;
  }


  public String getTextEn() {
    return textEn;
  }


  public void setTextEn(String textEn) {
    this.textEn = textEn;
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


  public boolean isCommentsAllowed() {
    return commentsAllowed;
  }


  public void setCommentsAllowed(boolean commentsAllowed) {
    this.commentsAllowed = commentsAllowed;
  }


  /**
   * This object attributes as string.
   */
  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

}
