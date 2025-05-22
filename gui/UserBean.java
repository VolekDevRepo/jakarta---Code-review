package cz.mawalgar.jsf.login;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

import cz.mawalgar.services.dao.AuthUserDao;

/**
 * @author Martin Volek
 * Created: 24.07.2018
 */
@Named
@SessionScoped
public class UserBean implements Serializable {

  private static final long serialVersionUID = 2131601559000364648L;

  /**
   * Logged user ID.
   */
  private Integer userId;

  private String alias;

  private String image;

  @Inject
  private AuthUserDao authUserDao;

  @Inject
  private ExternalContext externalContext;


  @PostConstruct
  public void init() {
    final String username = this.externalContext.getUserPrincipal().getName();
    this.userId = this.authUserDao.getUserIdByUsername(username);
    this.alias = this.authUserDao.getAliasByUserId(this.userId);
    this.image = this.authUserDao.getImageByUserId(this.userId);
  }


  /**
   * @return logged user ID
   */
  public Integer getUserId() {
    return this.userId;
  }


  public String getAlias() {
    if (this.alias == null) {
      return "User";
    }
    return alias;
  }


  public String getImage() {
    return image;
  }


  public void setImage(String image) {
    this.image = image;
  }

}
