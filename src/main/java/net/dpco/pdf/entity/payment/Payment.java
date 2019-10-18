package net.dpco.pdf.entity.payment;

import java.util.Date;
import javax.persistence.*;

import net.dpco.pdf.entity.BaseEntity;

import net.dpco.pdf.enums.payment.PaymentType;


@Entity
@Table(name = "PAYMENTS")
public class Payment extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  private Long trackingCode;


  private Long amount;


  private Long orderId;


  @Temporal(TemporalType.TIMESTAMP)
  private Date paidTime;


  private String retrievalRefNo;


  private String systemTraceNo;


  @Enumerated(EnumType.STRING)
  private PaymentType type;

  private String token;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getTrackingCode() {
    return trackingCode;
  }

  public void setTrackingCode(Long trackingCode) {
    this.trackingCode = trackingCode;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Date getPaidTime() {
    return paidTime;
  }

  public void setPaidTime(Date paidTime) {
    this.paidTime = paidTime;
  }

  public String getRetrievalRefNo() {
    return retrievalRefNo;
  }

  public void setRetrievalRefNo(String retrievalRefNo) {
    this.retrievalRefNo = retrievalRefNo;
  }

  public String getSystemTraceNo() {
    return systemTraceNo;
  }

  public void setSystemTraceNo(String systemTraceNo) {
    this.systemTraceNo = systemTraceNo;
  }

  public PaymentType getType() {
    return type;
  }

  public void setType(PaymentType type) {
    this.type = type;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
