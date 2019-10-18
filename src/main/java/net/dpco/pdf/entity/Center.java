package net.dpco.pdf.entity;

import lombok.NonNull;
import net.dpco.pdf.enums.CenterStatus;

import javax.persistence.*;

/**
 * JPA entity class for Center. It's annotated with JPA and lombok.
 *
 * @author souda
 * @see <a href="https://www.oracle.com/technetwork/middleware/ias/toplink-jpa-annotations-096251.html">JPA-annotaion</a>
 * @see <a href="https://projectlombok.org/">lombok-project</a>
 * @since 2019-07
 */
@Entity
@Table(name = "CENTERS")
public class Center extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  private String province;


  private String city;


  private Integer capacity;


  @Enumerated(EnumType.STRING)
  private CenterStatus centerStatus;

  @Transient
  private Boolean hasCapacity;

  public Center(@NonNull Long id, String province, String city, Boolean hasCapacity) {
    this.id = id;
    this.province = province;
    this.city = city;
    this.hasCapacity = hasCapacity;
  }

  public Center() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }

  public CenterStatus getCenterStatus() {
    return centerStatus;
  }

  public void setCenterStatus(CenterStatus centerStatus) {
    this.centerStatus = centerStatus;
  }

  public Boolean getHasCapacity() {
    return hasCapacity;
  }

  public void setHasCapacity(Boolean hasCapacity) {
    this.hasCapacity = hasCapacity;
  }
}
