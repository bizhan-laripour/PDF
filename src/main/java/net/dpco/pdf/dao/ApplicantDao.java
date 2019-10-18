package net.dpco.pdf.dao;

import net.dpco.pdf.entity.Applicant;

public interface ApplicantDao {

    Applicant save(Applicant applicant) throws Exception;

    Applicant findByNationalCodeAndTrackingCode(String nationalCode , String trackingCode) throws Exception;
}
