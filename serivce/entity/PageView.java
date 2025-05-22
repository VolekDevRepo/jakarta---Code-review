package cz.mawalgar.services.dao.en;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PageViews")
public class PageView implements Serializable {

  private static final long serialVersionUID = -3320400805223606188L;

  @Id
  private Integer id;

  @Column
  private Integer viewsCount;

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getViewsCount() {
    return this.viewsCount;
  }

  public void setViewsCount(Integer viewsCount) {
    this.viewsCount = viewsCount;
  }

}
