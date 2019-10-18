package net.dpco.pdf.entity;

import net.dpco.pdf.enums.actionlog.ActionLogType;

import javax.persistence.*;


@Entity
@Table(name = "ACTION_LOGS")
public class ActionLog extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  private String nationalCode;


  private Long trackingCode;


  @Enumerated(EnumType.STRING)
  private ActionLogType actionLogType;

  private String description;

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

  public Long getTrackingCode() {
    return trackingCode;
  }

  public void setTrackingCode(Long trackingCode) {
    this.trackingCode = trackingCode;
  }

  public ActionLogType getActionLogType() {
    return actionLogType;
  }

  public void setActionLogType(ActionLogType actionLogType) {
    this.actionLogType = actionLogType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
