//package net.dpco.pdf.entity;
//
//import net.dpco.pdf.entity.entity.Member;
//
//import javax.persistence.*;
//
//@Entity
//public class Spousess {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private Integer id;
//
//  private String name;
//
//  private String lastName;
//
//  private String fatherName;
//
//  private String nationalCode;
//
//  @ManyToOne(cascade = CascadeType.ALL)
//  @JoinColumn(name = "applicant_id", referencedColumnName = "id")
//  private Member member;
//
//  public String getName() {
//    return name;
//  }
//
//  public void setName(String name) {
//    this.name = name;
//  }
//
//  public String getLastName() {
//    return lastName;
//  }
//
//  public void setLastName(String lastName) {
//    this.lastName = lastName;
//  }
//
//  public String getFatherName() {
//    return fatherName;
//  }
//
//  public void setFatherName(String fatherName) {
//    this.fatherName = fatherName;
//  }
//
//  public String getNationalCode() {
//    return nationalCode;
//  }
//
//  public void setNationalCode(String nationalCode) {
//    this.nationalCode = nationalCode;
//  }
//
//  public Member getMember() {
//    return member;
//  }
//
//  public void setMember(Member member) {
//    this.member = member;
//  }
//
//  public Integer getId() {
//    return id;
//  }
//
//  public void setId(Integer id) {
//    this.id = id;
//  }
//}
