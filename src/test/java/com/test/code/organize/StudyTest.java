package com.test.code.organize;

import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

class StudyTest {

    @Test
    @DisplayName("create 메서드를 실행하는 단위테스트 함수")
    void assert_testing_in_study() {
        Study study = new Study(-10);
        assertNotNull(study);

        // param1: 기대하는 값
        // param2: 실제 나오는 값
        // param3: 바로 String을 줄 수 있고, 람다방식으로 작성할 수 있다.
        //         - String은 해당 결과가 성공/실패 하던 무조건 message를 만든다.
        //         - 람다방식의 경우 실패할 경우에만 message를 만든다.
        // assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이 DRAFT여야 한다.");
        assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 DRAFT여야 한다.");
        // 만약 위 메서드가 실패라면 아래 메서드는 실행하지 않는다.

        assertTrue(study.getLimit() > 0, () -> "스터디 참석 인원은 0보다 커야한다.");
    }

    @Test
    @DisplayName("assertAll 중간에 실패 함수가 있다고 하더라도, 전부 실행하는지 테스트")
    void assert_all_testing_in_study() {
        Study study = new Study(-10);
        assertAll(
                //assertAll은 중간에 메서드가 실패한다해도 아래 메서드를 모두 실행해준다.
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 DRAFT여야 한다."),
                () -> assertTrue(study.getLimit() > 0, () -> "스터디 참석 인원은 0보다 커야한다.")

        );
    }

    @Test
    @DisplayName("assertThrows 테스트 및 exception 기대 메시지 받기")
    void assert_throws_testing_in_study(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-1));
        String message = exception.getMessage();
        assertEquals("limit는 0보다 커야한다.", message);
    }

    @Test
    @DisplayName("assertTimeout 으로 시간초과 테스트")
    void assert_timeout_testing_in_study(){
        //0.1초안에 끝내야한다.
        assertTimeout(Duration.ofMillis(100), () -> {
            Study study = new Study(5);
            Thread.sleep(300); //0.3초걸림
        });
    }

    @Test
    @DisplayName("assertTimeoutPreemptively 으로 시간초과 테스트 / 메소드 실행 시간 안기다리고 이는 0.1초만에 테스트 끝냄")
    void assert_timeout_preemptively_testing_in_study(){
        //0.1초안에 끝내야한다.
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            Study study = new Study(5);
            Thread.sleep(300); //0.3초걸림
        });
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("beforeAll");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("afterAll");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("beforeEach");
    }

    @AfterEach
    void afterEach() {
        System.out.println("afterEach");
    }

}