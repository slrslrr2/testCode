package com.test.code.organize.inflearnthejavatest.member;

import com.test.code.organize.inflearnthejavatest.domain.Member;
import com.test.code.organize.inflearnthejavatest.domain.Study;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);

    void validate(Long memberId);

    void notify(Study newstudy);

    void notify(Member member);
}
