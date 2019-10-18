package net.dpco.pdf.entity;

import javax.persistence.*;

/**
 * JPA entity class for Land Owner. It's annotated with JPA and lombok.
 *
 * @author souda
 * @see <a href="https://www.oracle.com/technetwork/middleware/ias/toplink-jpa-annotations-096251.html">JPA-annotaion</a>
 * @see <a href="https://projectlombok.org/">lombok-project</a>
 * @since 2019-07
 */

@Entity
@Table(name = "LAND_OWNERS")
public class LandOwner extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  private String firstName;


  private String lastName;


  private String nationalCode;


  private Double partnershipSharePercent;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "LAND", referencedColumnName = "ID")
  private Land land;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getNationalCode() {
    return nationalCode;
  }

  public void setNationalCode(String nationalCode) {
    this.nationalCode = nationalCode;
  }

  public Double getPartnershipSharePercent() {
    return partnershipSharePercent;
  }

  public void setPartnershipSharePercent(Double partnershipSharePercent) {
    this.partnershipSharePercent = partnershipSharePercent;
  }

  public Land getLand() {
    return land;
  }

  public void setLand(Land land) {
    this.land = land;
  }
}
