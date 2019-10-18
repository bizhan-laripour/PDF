package net.dpco.pdf.entity;

import net.dpco.pdf.enums.JobType;

import javax.persistence.*;

/**
 * JPA entity class for Job. It's annotated with JPA and lombok.
 *
 * @author souda
 * @see <a href="https://www.oracle.com/technetwork/middleware/ias/toplink-jpa-annotations-096251.html">JPA-annotaion</a>
 * @see <a href="https://projectlombok.org/">lombok-project</a>
 * @since 2019-07
 */

@Entity
@Table(name = "JOBS")
public class Job extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  private String title;


  private JobType jobType;


  private String landLineNo;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "ADDRESS", referencedColumnName = "ID")
  private Address address;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public JobType getJobType() {
    return jobType;
  }

  public void setJobType(JobType jobType) {
    this.jobType = jobType;
  }

  public String getLandLineNo() {
    return landLineNo;
  }

  public void setLandLineNo(String landLineNo) {
    this.landLineNo = landLineNo;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}
