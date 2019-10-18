package net.dpco.pdf.dao.impl;

import net.dpco.pdf.dao.SpousesDao;
import net.dpco.pdf.entity.Member;
import net.dpco.pdf.entity.Spouse;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class SpouseDaoImpl implements SpousesDao {

  private SessionFactory sessionFactory;

  public SpouseDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public List<Spouse> findByMember(Member member) {
    CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
    CriteriaQuery<Spouse> criteriaQuery = criteriaBuilder.createQuery(Spouse.class);
    Root<Spouse> root = criteriaQuery.from(Spouse.class);
    criteriaQuery.select(root);
    criteriaQuery.where(criteriaBuilder.equal(root.get("member"), member));
    return sessionFactory.getCurrentSession().createQuery(criteriaQuery).list();
  }
}
