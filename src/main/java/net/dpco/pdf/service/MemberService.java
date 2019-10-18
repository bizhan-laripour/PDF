package net.dpco.pdf.service;

import net.dpco.pdf.entity.Member;

public interface MemberService {

  Member save(Member member);

  Member findByNationalCodeAndTrackingCode(String nationalCode, String trackingCode);
}
