package net.dpco.pdf.dao.impl;

import net.dpco.pdf.dao.MemberDao;
import net.dpco.pdf.entity.Member;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class MemberDaoImpl implements MemberDao {

  private SessionFactory sessionFactory;

  @Autowired
  public MemberDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public Member save(Member member) {
    sessionFactory.getCurrentSession().save(member);
    return member;
  }

  @Override
  public Member findByNationalCodeAndTrackingCode(String nationalCode, String trackingCode) {
    CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
    CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
    Root<Member> root = criteriaQuery.from(Member.class);
    criteriaQuery.select(root);
    Predicate predicate = criteriaBuilder.equal(root.get("nationalCode"), nationalCode);
    Predicate predicate1 = criteriaBuilder.equal(root.get("trackingCode"), trackingCode);
    criteriaQuery.where(criteriaBuilder.and(predicate, predicate1));
    List<Member> list = sessionFactory.getCurrentSession().createQuery(criteriaQuery).list();
    if (list.size() != 0) {
      return list.get(0);
    }
    return null;
  }
}
