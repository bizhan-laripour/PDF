package net.dpco.pdf.entity.verification;

import net.dpco.pdf.entity.BaseEntity;

import javax.persistence.*;


@Entity
@Table(name = "ApplicantVerifications")
public class ApplicantVerification extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  private String nationalCode;


  private Long mobileNo;


  private Long verificationCode;


  private String ip;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNationalCode() {
    return nationalCode;
  }

  public void setNationalCode(String nationalCode) {
    this.nationalCode = nationalCode;
  }

  public Long getMobileNo() {
    return mobileNo;
  }

  public void setMobileNo(Long mobileNo) {
    this.mobileNo = mobileNo;
  }

  public Long getVerificationCode() {
    return verificationCode;
  }

  public void setVerificationCode(Long verificationCode) {
    this.verificationCode = verificationCode;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }
}
