package net.dpco.pdf.dao;

import net.dpco.pdf.entity.Member;
import net.dpco.pdf.entity.Spouse;

import java.util.List;

public interface SpousesDao {

  List<Spouse> findByMember(Member member);
}
