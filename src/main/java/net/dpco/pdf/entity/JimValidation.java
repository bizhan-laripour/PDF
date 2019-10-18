package net.dpco.pdf.entity;

import javax.persistence.*;

@Entity
@Table(name = "JIM_VALIDATIONS")
// TODO must set unique constraint on nationalCode And put creation date and update date(extend
// baseEntity)
public class JimValidation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String nationalCode;

  private String isJimRed;

  private String jimDocNumber;

  public String getNationalCode() {
    return nationalCode;
  }

  public void setNationalCode(String nationalCode) {
    this.nationalCode = nationalCode;
  }

  public String getIsJimRed() {
    return isJimRed;
  }

  public void setIsJimRed(String isJimRed) {
    this.isJimRed = isJimRed;
  }

  public String getJimDocNumber() {
    return jimDocNumber;
  }

  public void setJimDocNumber(String jimDocNumber) {
    this.jimDocNumber = jimDocNumber;
  }
}
