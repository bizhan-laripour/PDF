package net.dpco.pdf.dao;

import net.dpco.pdf.entity.Applicant;
import net.dpco.pdf.entity.Spouse;

import java.util.List;

public interface SpouseDao {

    Spouse save(Spouse spouse) throws Exception;

    List<Spouse> findByApplicant(Applicant applicant) throws Exception;

}
