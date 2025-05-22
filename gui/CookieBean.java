package cz.mawalgar.gui.jsf.bean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Cookie;

import org.primefaces.PrimeFaces;

/**
 * A cookie managing bean.
 *
 * @author Martin Volek
 * 06. 01. 2020
 * Mawalgar
 */

@Named
@ViewScoped
public class CookieBean implements Serializable {

  private static final long serialVersionUID = -6329415494491649969L;

  private PrimeFaces currentFaces = PrimeFaces.current();

  final CookieHelper cookieHelper = new CookieHelper();

  /**
   * Initialization
   */
  public void init() {
    final Cookie cookie = this.cookieHelper.getCookie("startDialogShowed");
    if (cookie == null) {
      currentFaces.executeScript("PF('startDialogWidget').show();");
      return;
    }
    try {
      String dialogBool = URLDecoder.decode(cookie.getValue(), "UTF-8");
      if (!dialogBool.equals("done")) {
        currentFaces.executeScript("PF('startDialogWidget').show();");
        return;
      }
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }


  /**
   * Sets the cookie with specific variables.
   */
  public void setCookie() {
    String name = "startDialogShowed";
    String value = "done";
    int expiry = 31536000;
    this.cookieHelper.setCookie(name, value, expiry);
    currentFaces.executeScript("PF('startDialogWidget').hide();");
  }

}
