package net.dpco.pdf.service;

import net.dpco.pdf.entity.Member;
import net.dpco.pdf.entity.Spouse;

import java.util.List;

public interface SpousesService {

  List<Spouse> findByMember(Member member);
}
