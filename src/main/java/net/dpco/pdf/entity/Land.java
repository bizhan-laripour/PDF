package net.dpco.pdf.entity;

import net.dpco.pdf.enums.LandOwnershipState;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;


/**
 * JPA entity class for Land. It's annotated with JPA and lombok.
 *
 * @author souda
 * @see <a href="https://www.oracle.com/technetwork/middleware/ias/toplink-jpa-annotations-096251.html">JPA-annotaion</a>
 * @see <a href="https://projectlombok.org/">lombok-project</a>
 * @since 2019-07
 */

@Entity
@Table(name = "LANDS")
public class Land extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  private Integer area;

  private Integer unit;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "ADDRESS", referencedColumnName = "ID")
  private Address address;


  @Enumerated(EnumType.STRING)
  private LandOwnershipState landOwnershipState;

  @OneToMany(mappedBy = "land", cascade = CascadeType.ALL)
  @LazyCollection(LazyCollectionOption.FALSE)
  private Set<LandOwner> landOwners;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getArea() {
    return area;
  }

  public void setArea(Integer area) {
    this.area = area;
  }

  public Integer getUnit() {
    return unit;
  }

  public void setUnit(Integer unit) {
    this.unit = unit;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public LandOwnershipState getLandOwnershipState() {
    return landOwnershipState;
  }

  public void setLandOwnershipState(LandOwnershipState landOwnershipState) {
    this.landOwnershipState = landOwnershipState;
  }

  public Set<LandOwner> getLandOwners() {
    return landOwners;
  }

  public void setLandOwners(Set<LandOwner> landOwners) {
    this.landOwners = landOwners;
  }
}
