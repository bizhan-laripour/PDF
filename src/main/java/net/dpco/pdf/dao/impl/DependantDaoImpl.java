package net.dpco.pdf.dao.impl;


import net.dpco.pdf.dao.ApplicantDao;
import net.dpco.pdf.dao.DependantDao;
import net.dpco.pdf.entity.Applicant;
import net.dpco.pdf.entity.Dependant;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DependantDaoImpl implements DependantDao {

    private SessionFactory sessionFactory;

    @Autowired
    public DependantDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Dependant save(Dependant dependant) throws Exception {
        try{
            sessionFactory.getCurrentSession().save(dependant);
            return dependant;
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public List<Dependant> findByApplicant(Applicant applicant) throws Exception {
        try{
            CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<Dependant> criteriaQuery = criteriaBuilder.createQuery(Dependant.class);
            Root<Dependant> root = criteriaQuery.from(Dependant.class);
            criteriaQuery.select(root);
            Predicate applicantPredicate = criteriaBuilder.equal(root.get("applicant") , applicant);
            criteriaQuery.where(applicantPredicate);
            return  sessionFactory.getCurrentSession().createQuery(criteriaQuery).list();
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
