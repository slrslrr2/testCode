package com.test.code.organize.inflearnthejavatest.study;

import com.test.code.organize.inflearnthejavatest.domain.Member;
import com.test.code.organize.inflearnthejavatest.domain.Study;
import com.test.code.organize.inflearnthejavatest.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {
    // 구현체가 정의되어있지 않는 memberService, repository는 interface는, 의존성주입을 Mock객체를 통해 한다.
    @Test
    void createNewStudy(@Mock MemberService memberService, @Mock StudyRepository repository){
        Member member = new Member();
        member.setId(1L);
        member.setEmail("gbitkim@test.com");

        /** memberService.findById을 Stubbing
            memberService.findById(1L)를 사용할 경우
            Optional.of(member)를 return 한다는 구현체가 선언된것

            - any() ==> 아무값
         **/
        when(memberService.findById(any())).thenReturn(Optional.of(member));

        Optional<Member> findById = memberService.findById(1L);
        assertEquals(Optional.of(member), findById);

        /**
         when(interfaceMethod).thenThrow을 통해 예외를 던질 수 있다.
         */
        when(memberService.findById(1L)).thenThrow(new RuntimeException());
        memberService.findById(1L);

        /**
         void로 선언된 아래 메서드를 실행하면,
            IllegalArgumentException을 발생시켜라
         */
        doThrow(new IllegalArgumentException()).when(memberService).validate(1L);
        assertThrows(IllegalArgumentException.class, () -> {
            memberService.validate(1L);
        });
//        StudyService studyService = new StudyService(memberService, repository);


        Study study = new Study(10, "테스트");
        when(memberService.findById(1L)).thenReturn(Optional.of(new Member(1L, "gbitkim@test.com")));
        when(repository.save(study)).thenReturn(study);
    }
}