package com.project.knit.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessageEnum {

    SUCCESS("Success"),
    FAIL("Fail");

    private final String message;

}
