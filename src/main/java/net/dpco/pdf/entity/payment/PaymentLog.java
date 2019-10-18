package net.dpco.pdf.entity.payment;


import net.dpco.pdf.entity.BaseEntity;
import net.dpco.pdf.enums.payment.PaymentStatus;

import javax.persistence.*;


@Entity
@Table(name = "PAYMENTLOGS")
public class PaymentLog extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "PAYMENT", referencedColumnName = "ID")
  private Payment payment;


  private String token;


  private String description;


  private Long resultCode;


  private Long orderId;


  private String retrievalRefNo;


  private String systemTraceNo;


  @Enumerated(EnumType.STRING)
  private PaymentStatus status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getResultCode() {
    return resultCode;
  }

  public void setResultCode(Long resultCode) {
    this.resultCode = resultCode;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
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

  public PaymentStatus getStatus() {
    return status;
  }

  public void setStatus(PaymentStatus status) {
    this.status = status;
  }
}
