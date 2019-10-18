package net.dpco.pdf.service.impl;


import net.dpco.pdf.dao.DependantDao;
import net.dpco.pdf.entity.Applicant;
import net.dpco.pdf.entity.Dependant;
import net.dpco.pdf.service.DependantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DependantServiceImpl implements DependantService {

    private DependantDao dependantDao;

    @Autowired
    public DependantServiceImpl(DependantDao dependantDao){
        this.dependantDao = dependantDao;
    }


    @Override
    public Dependant save(Dependant dependant) throws Exception {
        try{
            return dependantDao.save(dependant);
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public List<Dependant> findByApplicant(Applicant applicant) throws Exception {
        try{
            return dependantDao.findByApplicant(applicant);
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
