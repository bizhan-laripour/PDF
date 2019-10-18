package net.dpco.pdf.entity;

import net.dpco.pdf.enums.ResidenceCase;

import javax.persistence.*;
import java.util.Date;

/**
 * JPA entity class for Residence Record. It's annotated with JPA and lombok.
 *
 * @author souda
 * @see <a href="https://www.oracle.com/technetwork/middleware/ias/toplink-jpa-annotations-096251.html">JPA-annotaion</a>
 * @see <a href="https://projectlombok.org/">lombok-project</a>
 * @since 2019-07
 */

@Entity
@Table(name = "RESIDENCE_RECORDS")
public class ResidenceRecord extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
  private Address address;


  @Enumerated(EnumType.STRING)
  private ResidenceCase residenceCase;


  @Temporal(TemporalType.DATE)
  private Date fromDate;


  @Temporal(TemporalType.DATE)
  private Date toDate;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "APPLICANT", referencedColumnName = "ID")
  private Applicant applicant;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public ResidenceCase getResidenceCase() {
    return residenceCase;
  }

  public void setResidenceCase(ResidenceCase residenceCase) {
    this.residenceCase = residenceCase;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public Date getToDate() {
    return toDate;
  }

  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }

  public Applicant getApplicant() {
    return applicant;
  }

  public void setApplicant(Applicant applicant) {
    this.applicant = applicant;
  }
}
