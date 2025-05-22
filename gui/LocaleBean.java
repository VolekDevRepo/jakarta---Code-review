package cz.mawalgar.gui.jsf.bean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;

/**
 * @author Martin Volek
 */
@Named
@SessionScoped
public class LocaleBean implements Serializable {

  private static final long serialVersionUID = -8107069677035092309L;

  @Inject
  private FacesContext facesContext;

  @Inject
  private ExternalContext externalContext;

  private Locale locale;
  private String language;

  private boolean english;
  private boolean german;
  private boolean czech;

  /**
   * Initializes selected locale per session.
   */
  @PostConstruct
  public void init() {
    Cookie cookie = (Cookie) externalContext.getRequestCookieMap().get("localization");
    if (cookie == null) {
    final ResourceBundle resourceBundle = this.facesContext.getApplication().getResourceBundle(this.facesContext, "mb");
    this.locale = resourceBundle.getLocale();
    } else {
      this.locale = new Locale(cookie.getValue());
    }

    this.language = this.locale.getLanguage();
    switch (this.language) {
      case "de":
        this.german = true;
        return;
      case "cs":
        this.czech = true;
        return;
      default: this.english = true;
    }
  }


  /**
   * @return selected locale per session
   */
  public Locale getLocale() {
    return this.locale;
  }


  /**
   * @return Locale language
   */
  public String getLanguage() {
    return this.language;
  }


  public void setLanguage(final String language) {
    this.language = language;
    this.setLocaleBoolean();
    this.locale = new Locale(language);
    this.setCookie(language);
    this.facesContext.getViewRoot().setLocale(this.locale);
  }


  public void setLocaleBoolean() {
    if (this.language.equals("cs")) {
      if (this.czech == false) {
        this.english = false;
        this.czech = true;
      }
      return;
    }
    if (this.language.equals("en")) {
      if (this.english == false) {
        this.czech = false;
        this.english = true;
      }
    }
  }


  /**
   * Sets the cookie with specific language.
   */
  private void setCookie(final String language) {
    String name = "localization";
    String value = language;
    Map<String, Object> properties = new HashMap<>();
    properties.put("maxAge", 31536000);
    properties.put("path", this.externalContext.getRequestContextPath());
    try {
      externalContext.addResponseCookie(name, URLEncoder.encode(value, "UTF-8"), properties);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }


  /**
   * @return <code>true</code> if the language is english
   */
  public boolean isEnglish() {
    return this.english;
  }


  /**
   * @return <code>true</code> if the language is german
   */
  public boolean isGerman() {
    return this.german;
  }


  /**
   * @return <code>true</code> if the language is czech
   */
  public boolean isCzech() {
    return this.czech;
  }

}
