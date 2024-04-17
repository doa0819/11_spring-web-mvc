package com.ohgiraffers.exceptionhandler;

public class MemberRegistException extends Exception{

    public MemberRegistException(String msg) {
        super(msg); // 부모에게 메세지 전달(this : 자기자신 super : 부모)

    }

}
