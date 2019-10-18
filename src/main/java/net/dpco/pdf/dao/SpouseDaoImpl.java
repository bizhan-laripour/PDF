package net.dpco.pdf.dao;

import net.dpco.pdf.entity.Applicant;
import net.dpco.pdf.entity.Spouse;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class SpouseDaoImpl implements SpouseDao {

  private SessionFactory sessionFactory;

  @Autowired
  public SpouseDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public Spouse save(Spouse spouse) throws Exception {
    try {
      sessionFactory.getCurrentSession().save(spouse);
      return spouse;
    } catch (Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }

  @Override
  public List<Spouse> findByApplicant(Applicant applicant) throws Exception {
    try {
      CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
      CriteriaQuery<Spouse> criteriaQuery = criteriaBuilder.createQuery(Spouse.class);
      Root<Spouse> root = criteriaQuery.from(Spouse.class);
      criteriaQuery.select(root);
      Predicate applicantPredicate = criteriaBuilder.equal(root.get("applicant"), applicant);
      criteriaQuery.where(applicantPredicate);
      return sessionFactory.getCurrentSession().createQuery(criteriaQuery).list();
    } catch (Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }
}
