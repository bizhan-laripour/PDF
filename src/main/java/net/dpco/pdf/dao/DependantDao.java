package net.dpco.pdf.dao;

import net.dpco.pdf.entity.Applicant;
import net.dpco.pdf.entity.Dependant;

import java.util.List;

public interface DependantDao {

    Dependant save(Dependant dependant) throws Exception;

    List<Dependant> findByApplicant(Applicant applicant) throws Exception;
}
