package com.test.code.organize;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class TestRequestDTO {
    @NotNull
    private String name;

    @Size(min = 10, max = 20, message = "나이를 입력해주세요")
    private int age;
}
