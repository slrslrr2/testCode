package com.test.code.organize;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class StudyTest2 {
    @Test
    void assumeTrue_test(){
        String property = System.getProperty("os.name");
        System.out.println(property);
        System.out.println("실행1");

        assumeTrue(property.startsWith("Windows"));
        System.out.println("실행2");
    }
}
