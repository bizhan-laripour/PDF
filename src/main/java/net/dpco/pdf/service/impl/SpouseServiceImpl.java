package net.dpco.pdf.service.impl;

import net.dpco.pdf.dao.SpousesDao;
import net.dpco.pdf.entity.Member;
import net.dpco.pdf.entity.Spouse;
import net.dpco.pdf.service.SpousesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpouseServiceImpl implements SpousesService {

  private SpousesDao spousesDao;

  @Autowired
  public SpouseServiceImpl(SpousesDao spousesDao) {
    this.spousesDao = spousesDao;
  }

  @Override
  @Transactional
  public List<Spouse> findByMember(Member member) {
    return spousesDao.findByMember(member);
  }
}
