package com.jtrio.zagzag.enums;

//import lombok.AllArgsConstructor;
//import lombok.Getter;

//@AllArgsConstructor
//@Getter
public enum ErrorMsg {
    EMAIL("이메일을 확인해주세요."),
    NOT_FOUND("해당 데이터를 찾을 수 없습니다.");

    public final String value;

    ErrorMsg(String errVal) {
        this.value = errVal;
    }

    public String getValue(){
        return value;
    }
}
