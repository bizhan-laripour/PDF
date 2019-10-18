package net.dpco.pdf.entity;

import net.dpco.pdf.enums.EducationLevel;
import net.dpco.pdf.enums.Gender;
import net.dpco.pdf.enums.PhysicalStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * JPA entity class for Spousess. It's annotated with JPA and lombok.
 *
 * @author souda
 * @see <a href="https://www.oracle.com/technetwork/middleware/ias/toplink-jpa-annotations-096251.html">JPA-annotaion</a>
 * @see <a href="https://projectlombok.org/">lombok-project</a>
 * @since 2019-07
 */

@Entity
@Table(name = "SPOUSES")
public class Spouse extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)


  private Long id;


  private String firstName;


  private String lastName;


  private String birthCertificateNo;


  private String issuedPlace;


  private String birthPlace;


  private String fatherName;


  @Temporal(TemporalType.DATE)
  private Date birthDate;


  private String nationalCode;


  private String mobileNo;


  @Enumerated(EnumType.STRING)
  private Gender gender;


  @Enumerated(EnumType.STRING)
  private PhysicalStatus physicalStatus;


  @Enumerated(EnumType.STRING)
  private EducationLevel educationLevel;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "JOB", referencedColumnName = "ID")
  private Job job;

  @ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinColumn(name = "APPLICANT", referencedColumnName = "ID", nullable = false, updatable = false)
  private Applicant applicant;

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

  public String getBirthCertificateNo() {
    return birthCertificateNo;
  }

  public void setBirthCertificateNo(String birthCertificateNo) {
    this.birthCertificateNo = birthCertificateNo;
  }

  public String getIssuedPlace() {
    return issuedPlace;
  }

  public void setIssuedPlace(String issuedPlace) {
    this.issuedPlace = issuedPlace;
  }

  public String getBirthPlace() {
    return birthPlace;
  }

  public void setBirthPlace(String birthPlace) {
    this.birthPlace = birthPlace;
  }

  public String getFatherName() {
    return fatherName;
  }

  public void setFatherName(String fatherName) {
    this.fatherName = fatherName;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public String getNationalCode() {
    return nationalCode;
  }

  public void setNationalCode(String nationalCode) {
    this.nationalCode = nationalCode;
  }

  public String getMobileNo() {
    return mobileNo;
  }

  public void setMobileNo(String mobileNo) {
    this.mobileNo = mobileNo;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public PhysicalStatus getPhysicalStatus() {
    return physicalStatus;
  }

  public void setPhysicalStatus(PhysicalStatus physicalStatus) {
    this.physicalStatus = physicalStatus;
  }

  public EducationLevel getEducationLevel() {
    return educationLevel;
  }

  public void setEducationLevel(EducationLevel educationLevel) {
    this.educationLevel = educationLevel;
  }

  public Job getJob() {
    return job;
  }

  public void setJob(Job job) {
    this.job = job;
  }

  public Applicant getApplicant() {
    return applicant;
  }

  public void setApplicant(Applicant applicant) {
    this.applicant = applicant;
  }
}

