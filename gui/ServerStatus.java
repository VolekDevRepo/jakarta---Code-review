package cz.mawalgar.services.dao.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.apache.commons.lang3.StringUtils;

import cz.mawalgar.services.dao.en.IpView;
import cz.mawalgar.services.dao.en.MawaCharacter;
import cz.mawalgar.services.dao.en.PageView;

/**
 * @author Martin Volek
 * Created: 13. 4. 2020
 */
@ApplicationScoped
public class ServerStatus implements Serializable {

  private static final long serialVersionUID = 9221553763926810804L;

  private boolean authServerStatus;

  private List<MawaCharacter> playersOnline;

  private List<MawaCharacter> allCharacters;

  private PageView pageView;

  public void incrementView() {
    if (this.pageView == null) {
      return;
    }
    this.pageView.setViewsCount(this.pageView.getViewsCount() + 1);
  }


  public boolean isAuthServerStatus() {
    return authServerStatus;
  }


  public void setAuthServerStatus(boolean authServerStatus) {
    this.authServerStatus = authServerStatus;
  }


  public List<MawaCharacter> getPlayersOnline() {
    return playersOnline;
  }


  public void setPlayersOnline(List<MawaCharacter> playersOnline) {
    this.playersOnline = playersOnline;
  }


  public List<MawaCharacter> getAllCharacters() {
    return allCharacters;
  }


  public void setAllCharacters(List<MawaCharacter> allCharacters) {
    this.allCharacters = allCharacters;
  }


  public PageView getPageView() {
    return pageView;
  }


  public void setPageView(PageView pageView) {
    this.pageView = pageView;
  }


}
