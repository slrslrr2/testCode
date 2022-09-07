package com.test.code.organize.inflearnthejavatest.study;

import com.test.code.organize.inflearnthejavatest.domain.Member;
import com.test.code.organize.inflearnthejavatest.domain.Study;
import com.test.code.organize.inflearnthejavatest.member.MemberService;

import java.util.Optional;

public class StudyService {

    private final MemberService memberService;

    private final StudyRepository repository;

    public StudyService(MemberService memberService, StudyRepository repository) {
        assert memberService != null;
        assert repository != null;
        this.memberService = memberService;
        this.repository = repository;
    }

    public Study createNewStudy(Long memberId, Study study) {
        Optional<Member> member = memberService.findById(memberId);
        study.setOwner(member.orElseThrow(() -> new IllegalArgumentException("Member doesn't exist for id: '" + memberId + "'")));
        return repository.save(study);
    }
//
//    public Study openStudy(Study study) {
//        study.open();
//        Study openedStudy = repository.save(study);
//        memberService.notify(openedStudy);
//        return openedStudy;
//    }
//
//    public void hi() {
//
//    }
}
