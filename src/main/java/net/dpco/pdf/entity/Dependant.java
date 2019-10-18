package net.dpco.pdf.entity;

import net.dpco.pdf.enums.Gender;
import net.dpco.pdf.enums.MaritalStatus;
import net.dpco.pdf.enums.PhysicalStatus;
import net.dpco.pdf.enums.RelationshipWithApplicant;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * JPA entity class for Dependant. It's annotated with JPA and lombok.
 *
 * @author souda
 * @see <a href="https://www.oracle.com/technetwork/middleware/ias/toplink-jpa-annotations-096251.html">JPA-annotaion</a>
 * @see <a href="https://projectlombok.org/">lombok-project</a>
 * @since 2019-07
 */

@Entity
@Table(name = "DEPENDANTS")
public class Dependant extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  private String firstName;


  private String lastName;


  private String nationalCode;


  @Enumerated(EnumType.STRING)
  private Gender gender;


  private String birthYear;


  @Size(max = 30, message = "{dependant.job.title.long}")
  private String jobTitle;


  @Enumerated(EnumType.STRING)
  private PhysicalStatus physicalStatus;


  @Enumerated(EnumType.STRING)
  private MaritalStatus maritalStatus;


  @Enumerated(EnumType.STRING)
  private RelationshipWithApplicant relationshipWithApplicant;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "APPLICANT", referencedColumnName = "ID")
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

  public String getNationalCode() {
    return nationalCode;
  }

  public void setNationalCode(String nationalCode) {
    this.nationalCode = nationalCode;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public String getBirthYear() {
    return birthYear;
  }

  public void setBirthYear(String birthYear) {
    this.birthYear = birthYear;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public PhysicalStatus getPhysicalStatus() {
    return physicalStatus;
  }

  public void setPhysicalStatus(PhysicalStatus physicalStatus) {
    this.physicalStatus = physicalStatus;
  }

  public MaritalStatus getMaritalStatus() {
    return maritalStatus;
  }

  public void setMaritalStatus(MaritalStatus maritalStatus) {
    this.maritalStatus = maritalStatus;
  }

  public RelationshipWithApplicant getRelationshipWithApplicant() {
    return relationshipWithApplicant;
  }

  public void setRelationshipWithApplicant(RelationshipWithApplicant relationshipWithApplicant) {
    this.relationshipWithApplicant = relationshipWithApplicant;
  }

  public Applicant getApplicant() {
    return applicant;
  }

  public void setApplicant(Applicant applicant) {
    this.applicant = applicant;
  }
}
