package net.dpco.pdf.service;

import net.dpco.pdf.entity.Applicant;

public interface ApplicantService {

    Applicant save(Applicant applicant) throws Exception;

    Applicant findByNationalCodeAndTrackingCode(String nationalCode , String trackingCode) throws Exception;
}
