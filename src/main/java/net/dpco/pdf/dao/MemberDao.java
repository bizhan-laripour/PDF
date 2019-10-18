package net.dpco.pdf.dao;

import net.dpco.pdf.entity.Member;

public interface MemberDao {

  Member save(Member member);

  Member findByNationalCodeAndTrackingCode(String nationalCode, String trackingCode);
}
