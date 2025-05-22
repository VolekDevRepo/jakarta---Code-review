package cz.mawalgar.services.dao.en;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the core database account.
 */
@Entity
@Table(name = "account")
public class MawaUser implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * The user ID.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  /**
   * The username/login name
   */
  @Column(nullable = false, length = 32)
  private String username;

  /**
   * Last name of the user
   */
  @Column(nullable = false, length = 40)
  private String sha_pass_hash;

  /**
   * session key
   */
  @Column
  private String sessionkey;

  /**
   * session column v
   */
  @Column
  private String v;

  /**
   * session column s
   */
  @Column
  private String s;

  /**
   * dont know what is it, 128 VARBINARY, DEFAULT NULL
   */
  @Column
  private String totp_secret;

  /**
   * Email of the user
   */
  @Column
  private String email;

  /**
   * Reg mail of the user
   */
  @Column
  private String reg_mail;

  /**
   * Account valid FROM date
   */
  @Column
  private LocalDateTime joindate;

  /**
   * Last ip
   */
  @Column
  private String last_ip;

  /**
   * Last attempt ip
   */
  @Column
  private String last_attempt_ip;

  /**
   * Failed logins
   */
  @Column
  private Integer failed_logins;


  /**
   * Locked
   */
  @Column
  private Integer locked;

  /**
   * Lock country
   */
  @Column
  private String lock_country;


  /**
   * Last login
   */
  @Column
  private LocalDateTime last_login;

  /**
   * Online status, 0 = false
   */
  @Column
  private Integer online;

  /**
   * 0 = classic, 1 = tbc, 2 = wotlk... Should be only 2
   */
  @Column
  private Integer expansion;

  /**
   * Time muted
   */
  @Column
  private BigInteger mutetime;

  /**
   * Mute reason
   */
  @Column
  private String mutereason;

  /**
   * Mute by
   */
  @Column
  private String muteby;

  /**
   * Need study!!!
   */
  @Column
  private Integer locale;

  /**
   * Operation system
   */
  @Column
  private String os;

  /**
   * Recruiter
   */
  @Column
  private Integer recruiter;


  public int getId() {
    return id;
  }


  public void setId(int id) {
    this.id = id;
  }


  public String getUsername() {
    return username;
  }


  public void setUsername(String username) {
    this.username = username;
  }


  public String getSha_pass_hash() {
    return sha_pass_hash;
  }


  public void setSha_pass_hash(String sha_pass_hash) {
    this.sha_pass_hash = sha_pass_hash;
  }


  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public LocalDateTime getJoindate() {
    return joindate;
  }


  public void setJoindate(LocalDateTime joindate) {
    this.joindate = joindate;
  }


  public Integer getFailed_logins() {
    return failed_logins;
  }


  public void setFailed_logins(Integer failed_logins) {
    this.failed_logins = failed_logins;
  }


  public Integer getLocked() {
    return locked;
  }


  public void setLocked(Integer locked) {
    this.locked = locked;
  }


  public LocalDateTime getLast_login() {
    return last_login;
  }


  public void setLast_login(LocalDateTime last_login) {
    this.last_login = last_login;
  }


  public BigInteger getMutetime() {
    return mutetime;
  }


  public void setMutetime(BigInteger mutetime) {
    this.mutetime = mutetime;
  }


  public Integer getLocale() {
    return locale;
  }


  public void setLocale(Integer locale) {
    this.locale = locale;
  }


  public String getSessionkey() {
    return sessionkey;
  }


  public void setSessionkey(String sessionkey) {
    this.sessionkey = sessionkey;
  }


  public String getV() {
    return v;
  }


  public void setV(String v) {
    this.v = v;
  }


  public String getS() {
    return s;
  }


  public void setS(String s) {
    this.s = s;
  }


  public String getTotp_secret() {
    return totp_secret;
  }


  public void setTotp_secret(String totp_secret) {
    this.totp_secret = totp_secret;
  }


  public String getReg_mail() {
    return reg_mail;
  }


  public void setReg_mail(String reg_mail) {
    this.reg_mail = reg_mail;
  }


  public String getLast_ip() {
    return last_ip;
  }


  public void setLast_ip(String last_ip) {
    this.last_ip = last_ip;
  }


  public String getLast_attempt_ip() {
    return last_attempt_ip;
  }


  public void setLast_attempt_ip(String last_attempt_ip) {
    this.last_attempt_ip = last_attempt_ip;
  }


  public String getLock_country() {
    return lock_country;
  }


  public void setLock_country(String lock_country) {
    this.lock_country = lock_country;
  }


  public Integer getOnline() {
    return online;
  }


  public void setOnline(Integer online) {
    this.online = online;
  }


  public Integer getExpansion() {
    return expansion;
  }


  public void setExpansion(Integer expansions) {
    this.expansion = expansions;
  }


  public String getMutereason() {
    return mutereason;
  }


  public void setMutereason(String mutereason) {
    this.mutereason = mutereason;
  }


  public String getMuteby() {
    return muteby;
  }


  public void setMuteby(String muteby) {
    this.muteby = muteby;
  }


  public String getOs() {
    return os;
  }


  public void setOs(String os) {
    this.os = os;
  }


  public Integer getRecruiter() {
    return recruiter;
  }


  public void setRecruiter(Integer recruiter) {
    this.recruiter = recruiter;
  }

}
