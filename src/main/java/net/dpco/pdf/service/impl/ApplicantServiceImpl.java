package net.dpco.pdf.service.impl;

import net.dpco.pdf.dao.ApplicantDao;
import net.dpco.pdf.entity.Applicant;
import net.dpco.pdf.service.ApplicantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ApplicantServiceImpl implements ApplicantService {

    private ApplicantDao applicantDao;

    public ApplicantServiceImpl(ApplicantDao applicantDao){
        this.applicantDao = applicantDao;
    }

    @Override
    @Transactional
    public Applicant save(Applicant applicant) throws Exception {
        try{
        applicantDao.save(applicant);
        return applicant;
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public Applicant findByNationalCodeAndTrackingCode(String nationalCode, String trackingCode) throws Exception {
        try{
            return applicantDao.findByNationalCodeAndTrackingCode(nationalCode , nationalCode);
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
