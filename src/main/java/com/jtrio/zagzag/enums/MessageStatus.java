package com.jtrio.zagzag.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageStatus {
    DELETE("사용자 요청에 의해 삭제된 글입니다."),
    BLOCK("관리자에 의해 차단된 글입니다."),
    NORMAL("");

    final private String name;
}
