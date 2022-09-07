package com.test.code.organize;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudyTest2 {
    @Test
    @Order(1)
    void assumeTrue_test(){
        String property = System.getProperty("os.name");
        System.out.println(property);
        System.out.println("실행1");

        assumeTrue(property.startsWith("Windows"));
        System.out.println("실행2");
    }

    @Test
    @Order(2)
    @EnabledOnOs({OS.MAC, OS.LINUX})
    @DisplayName("MAC, LINUX와 관련된 운영체제만 실행")
    void enable_related(){
    }

    @Test
    @DisabledOnOs({OS.MAC, OS.LINUX})
    @DisplayName("MAC, LINUX와 관련된 운영체제를 제외하고 실행")
    void disabled_related(){
    }

    @Test
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_11})
    @DisplayName("JAVA8, 11버전만 실행")
    void enable_jre_related(){
    }

    @Test
    @DisabledOnJre({JRE.JAVA_8, JRE.JAVA_11})
    @DisplayName("JAVA8, 11버전만 실행안함")
    void disabled_jre_related(){
    }

    @DisplayName("10번 반복하는 테스트")
    @RepeatedTest(value = 10, name="{displayName}, {currentRepetition}/{totalRepetitions}")
    void repeated_test(RepetitionInfo repetitionInfo){
        System.out.println(repetitionInfo.getCurrentRepetition()); //몇번째 테스트인지
        System.out.println(repetitionInfo.getTotalRepetitions()); // 전체 테스트 갯수
    }

    @DisplayName("파라미터를 지정하여 반복하는 메서드")
    @ParameterizedTest(name="{displayName} {index} {arguments}")
    @ValueSource(strings = {"첫번째Param", "두번째Param", "세번째Param"})
    @NullAndEmptySource
    void iterator_with_parameter(String message){
        System.out.println("message = " + message);
    }

    @DisplayName("생성자 함수에 파라미터를 넘겨서 Study클래스 만들기")
    @ParameterizedTest(name="{displayName}")
    @ValueSource(ints = {10, 20, 40})
    void create_study_throught_converter(@ConvertWith(StudyConverter.class) Study study){
        System.out.println("study = " + study);
    }

    static class StudyConverter extends SimpleArgumentConverter{
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(targetType, Study.class, "Can only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }

    @DisplayName("파라미터를 지정하여 반복하는 메서드")
    @ParameterizedTest(name="{displayName} {index} {arguments}")
    @CsvSource
    @Disabled
    void iterator_with_parameters(String message){
        System.out.println("message = " + message);
    }
}
