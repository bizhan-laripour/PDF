package net.dpco.pdf.entity;

import net.dpco.pdf.enums.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * JPA entity class for Applicant. It's annotated with JPA and lombok.
 *
 * @author souda
 * @see <a href="https://www.oracle.com/technetwork/middleware/ias/toplink-jpa-annotations-096251.html">JPA-annotaion</a>
 * @see <a href="https://projectlombok.org/">lombok-project</a>
 * @since 2019-07
 */

@Entity
@Table(name = "APPLICANTS")
public class Applicant extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;


  private String firstName;


  private String lastName;


  private String birthCertificateNo;


  private String birthCertificateSerialNo;


  private String birthCertificateSeries;


  private String issuedPlace;


  private String birthPlace;


  private String fatherName;


  @Temporal(TemporalType.DATE)
  private Date birthDate;


  private String nationalCode;


  @Enumerated(EnumType.STRING)
  private MaritalStatus maritalStatus;


  @Enumerated(EnumType.STRING)
  private IncomeLevel incomeLevel;


  @Enumerated(EnumType.STRING)
  private HeadHouseStatus headHouseStatus;


  @Enumerated(EnumType.STRING)
  private InvestmentAmountLevel investmentAmountLevel;


  @Enumerated(EnumType.STRING)
  private PaybackAmountLevel paybackAmountLevel;


  private Long mobileNo;


  private String email;


  private Long alternativeMobileNo;


  private String landLineNo;


  private String ip;


  @Enumerated(EnumType.STRING)
  private Gender gender;


  @Enumerated(EnumType.STRING)
  private PhysicalStatus physicalStatus;


  @Enumerated(EnumType.STRING)
  private EducationLevel educationLevel;


  private Boolean isElite;


  private Boolean isKhanevadeShohada;


  private Boolean isIsargar;

  private Boolean isJanbazan;


  private Boolean isKhanevahehTahtPosheshKhas;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "JOB", referencedColumnName = "ID")
  private Job job;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "LAND", referencedColumnName = "ID")
  private Land land;

  @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  private Set<Spouse> spouses;

  @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  private Set<ResidenceRecord> residenceRecords;

  @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  private Set<Dependant> dependants;


  private Long trackingCode;


  private Long verificationCode;


  @Temporal(TemporalType.TIMESTAMP)
  private Date archiveTime;


  @Enumerated(EnumType.STRING)
  private ArchiveReason archiveReason;

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

  public String getBirthCertificateSerialNo() {
    return birthCertificateSerialNo;
  }

  public void setBirthCertificateSerialNo(String birthCertificateSerialNo) {
    this.birthCertificateSerialNo = birthCertificateSerialNo;
  }

  public String getBirthCertificateSeries() {
    return birthCertificateSeries;
  }

  public void setBirthCertificateSeries(String birthCertificateSeries) {
    this.birthCertificateSeries = birthCertificateSeries;
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

  public MaritalStatus getMaritalStatus() {
    return maritalStatus;
  }

  public void setMaritalStatus(MaritalStatus maritalStatus) {
    this.maritalStatus = maritalStatus;
  }

  public IncomeLevel getIncomeLevel() {
    return incomeLevel;
  }

  public void setIncomeLevel(IncomeLevel incomeLevel) {
    this.incomeLevel = incomeLevel;
  }

  public HeadHouseStatus getHeadHouseStatus() {
    return headHouseStatus;
  }

  public void setHeadHouseStatus(HeadHouseStatus headHouseStatus) {
    this.headHouseStatus = headHouseStatus;
  }

  public InvestmentAmountLevel getInvestmentAmountLevel() {
    return investmentAmountLevel;
  }

  public void setInvestmentAmountLevel(InvestmentAmountLevel investmentAmountLevel) {
    this.investmentAmountLevel = investmentAmountLevel;
  }

  public PaybackAmountLevel getPaybackAmountLevel() {
    return paybackAmountLevel;
  }

  public void setPaybackAmountLevel(PaybackAmountLevel paybackAmountLevel) {
    this.paybackAmountLevel = paybackAmountLevel;
  }

  public Long getMobileNo() {
    return mobileNo;
  }

  public void setMobileNo(Long mobileNo) {
    this.mobileNo = mobileNo;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Long getAlternativeMobileNo() {
    return alternativeMobileNo;
  }

  public void setAlternativeMobileNo(Long alternativeMobileNo) {
    this.alternativeMobileNo = alternativeMobileNo;
  }

  public String getLandLineNo() {
    return landLineNo;
  }

  public void setLandLineNo(String landLineNo) {
    this.landLineNo = landLineNo;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
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

  public Boolean getElite() {
    return isElite;
  }

  public void setElite(Boolean elite) {
    isElite = elite;
  }

  public Boolean getKhanevadeShohada() {
    return isKhanevadeShohada;
  }

  public void setKhanevadeShohada(Boolean khanevadeShohada) {
    isKhanevadeShohada = khanevadeShohada;
  }

  public Boolean getIsargar() {
    return isIsargar;
  }

  public void setIsargar(Boolean isargar) {
    isIsargar = isargar;
  }

  public Boolean getJanbazan() {
    return isJanbazan;
  }

  public void setJanbazan(Boolean janbazan) {
    isJanbazan = janbazan;
  }

  public Boolean getKhanevahehTahtPosheshKhas() {
    return isKhanevahehTahtPosheshKhas;
  }

  public void setKhanevahehTahtPosheshKhas(Boolean khanevahehTahtPosheshKhas) {
    isKhanevahehTahtPosheshKhas = khanevahehTahtPosheshKhas;
  }

  public Job getJob() {
    return job;
  }

  public void setJob(Job job) {
    this.job = job;
  }

  public Land getLand() {
    return land;
  }

  public void setLand(Land land) {
    this.land = land;
  }

  public Set<Spouse> getSpouses() {
    return spouses;
  }

  public void setSpouses(Set<Spouse> spouses) {
    this.spouses = spouses;
  }

  public Set<ResidenceRecord> getResidenceRecords() {
    return residenceRecords;
  }

  public void setResidenceRecords(Set<ResidenceRecord> residenceRecords) {
    this.residenceRecords = residenceRecords;
  }

  public Set<Dependant> getDependants() {
    return dependants;
  }

  public void setDependants(Set<Dependant> dependants) {
    this.dependants = dependants;
  }

  public Long getTrackingCode() {
    return trackingCode;
  }

  public void setTrackingCode(Long trackingCode) {
    this.trackingCode = trackingCode;
  }

  public Long getVerificationCode() {
    return verificationCode;
  }

  public void setVerificationCode(Long verificationCode) {
    this.verificationCode = verificationCode;
  }

  public Date getArchiveTime() {
    return archiveTime;
  }

  public void setArchiveTime(Date archiveTime) {
    this.archiveTime = archiveTime;
  }

  public ArchiveReason getArchiveReason() {
    return archiveReason;
  }

  public void setArchiveReason(ArchiveReason archiveReason) {
    this.archiveReason = archiveReason;
  }
}
