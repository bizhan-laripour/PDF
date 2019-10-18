//package net.dpco.pdf.service.impl;
//
//import net.dpco.pdf.dao.MemberDao;
//import net.dpco.pdf.entity.entity.Member;
//import net.dpco.pdf.service.MemberService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class MemberServiceImpl implements MemberService {
//
//  private MemberDao memberDao;
//
//  @Autowired
//  public MemberServiceImpl(MemberDao memberDao) {
//    this.memberDao = memberDao;
//  }
//
//  @Override
//  public Member save(Member member) {
//    return memberDao.save(member);
//  }
//
//  @Override
//  @Transactional
//  public Member findByNationalCodeAndTrackingCode(String nationalCode, String trackingCode) {
//    return memberDao.findByNationalCodeAndTrackingCode(nationalCode, trackingCode);
//  }
//}
////
