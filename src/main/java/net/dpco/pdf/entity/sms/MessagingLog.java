package net.dpco.pdf.entity.sms;

import net.dpco.pdf.entity.BaseEntity;
import net.dpco.pdf.enums.sms.MessageLogType;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGING_LOGS")
public class MessagingLog extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  private Long mobileNo;


  @Enumerated(EnumType.STRING)
  private MessageLogType messageLogType;


  private Long statusCode;


  private Long messageId;


  private String text;


  private String description;


  private Long relatedId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getMobileNo() {
    return mobileNo;
  }

  public void setMobileNo(Long mobileNo) {
    this.mobileNo = mobileNo;
  }

  public MessageLogType getMessageLogType() {
    return messageLogType;
  }

  public void setMessageLogType(MessageLogType messageLogType) {
    this.messageLogType = messageLogType;
  }

  public Long getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(Long statusCode) {
    this.statusCode = statusCode;
  }

  public Long getMessageId() {
    return messageId;
  }

  public void setMessageId(Long messageId) {
    this.messageId = messageId;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getRelatedId() {
    return relatedId;
  }

  public void setRelatedId(Long relatedId) {
    this.relatedId = relatedId;
  }
}
