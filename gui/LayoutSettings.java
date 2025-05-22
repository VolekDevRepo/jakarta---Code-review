package cz.mawalgar.gui.jsf.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import cz.mawalgar.gui.jsf.i18n.cdi.MessageBundle;
import cz.mawalgar.gui.jsf.i18n.rb.ResourceBundleApi;
import cz.mawalgar.gui.jsf.view.GuestPreferences;
import cz.mawalgar.jsf.login.UserBean;
import cz.mawalgar.services.dao.AuthUserDao;
import cz.mawalgar.services.dao.en.AuthUser;


@Named
@SessionScoped
public class LayoutSettings implements Serializable {

  private static final long serialVersionUID = 8824759736770563743L;


  @Inject
  private GuestPreferences guestPreferences;

  @Inject
  private LocaleBean localeBean;

  @Inject
  private FacesContext facesContext;

  @Inject
  @MessageBundle
  private ResourceBundleApi mb;

  @Inject
  private AuthUserDao dao;

  @Inject
  private UserBean userBean;

  private AuthUser user;

  @PostConstruct
  public void init() {
    this.user = this.dao.getUserById(userBean.getUserId());
  }


  public void changeLanguage(final String language) {
    if (this.user == null) {
      this.user = this.dao.getUserById(userBean.getUserId());
    }

    if (this.user.getLayoutChangeAttemptsToday() == 20) {
      this.settingsError();
      return;
    }
    this.user.setLayoutChangeAttemptsToday(this.user.getLayoutChangeAttemptsToday() + 1);
    this.dao.updateUser(this.user);
    this.localeBean.setLanguage(language);
    this.settingsWarning();
    if (this.user.getLayoutChangeAttemptsToday() == 20) {
      this.settingsError();
    }
  }



  public void changeMenu(final boolean layoutMode) {
    if (this.user == null) {
      this.user = this.dao.getUserById(userBean.getUserId());
    }

    if (this.user.getLayoutChangeAttemptsToday() == 20) {
      settingsError();
      return;
    }
    this.user.setLayoutChangeAttemptsToday(this.user.getLayoutChangeAttemptsToday() + 1);
    this.dao.updateUser(this.user);
    this.guestPreferences.setLightMenu(layoutMode);
    this.settingsWarning();
    if (this.user.getLayoutChangeAttemptsToday() == 20) {
      this.settingsError();
    }
  }


  private void settingsError() {
    this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, this.mb.getString("layout.change.overflow"), ""));
  }


  public void changeLayout(final String theme) {
    if (this.user == null) {
      this.user = this.dao.getUserById(userBean.getUserId());
    }

    if (this.user.getLayoutChangeAttemptsToday() == 20) {
      this.settingsError();
      return;
    }
    this.user.setLayoutChangeAttemptsToday(this.user.getLayoutChangeAttemptsToday() + 1);
    this.dao.updateUser(this.user);
    this.guestPreferences.setTheme(theme);
    this.settingsWarning();
    if (this.user.getLayoutChangeAttemptsToday() == 20) {
      this.settingsError();
    }
  }


  private void settingsWarning() {
    this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, this.mb.getString("layout.attempts.available",
        20 - this.user.getLayoutChangeAttemptsToday()), ""));
  }


  public AuthUser getUser() {
    return user;
  }
}