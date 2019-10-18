package net.dpco.pdf.dao.impl;

import net.dpco.pdf.dao.ApplicantDao;
import net.dpco.pdf.entity.Applicant;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ApplicantDaoImpl implements ApplicantDao {

  private SessionFactory sessionFactory;

  @Autowired
  public ApplicantDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public Applicant save(Applicant applicant) {
    sessionFactory.getCurrentSession().save(applicant);
    return applicant;
  }

  @Override
  public Applicant findByNationalCodeAndTrackingCode(String nationalCode, String trackingCode)
      throws Exception {

    try {
      CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
      CriteriaQuery<Applicant> criteriaQuery = criteriaBuilder.createQuery(Applicant.class);
      Root<Applicant> root = criteriaQuery.from(Applicant.class);
      criteriaQuery.select(root);
      Predicate nationalCodePredicate =
          criteriaBuilder.equal(root.get("nationalCode"), nationalCode);
      Predicate trackingCodePredicate =
          criteriaBuilder.equal(root.get("trackingCode"), trackingCode);
      criteriaQuery.where(criteriaBuilder.and(nationalCodePredicate, trackingCodePredicate));
      List<Applicant> list = sessionFactory.getCurrentSession().createQuery(criteriaQuery).list();
      if (list.size() != 0) {
        return list.get(0);
      }
      return null;
    } catch (Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }
}
