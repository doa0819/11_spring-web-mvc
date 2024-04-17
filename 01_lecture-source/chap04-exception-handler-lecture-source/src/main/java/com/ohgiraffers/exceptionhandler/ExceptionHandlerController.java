package com.ohgiraffers.exceptionhandler;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

/* 필기. 예외처리 handler 사용 방법!!! // @ExceptionHandler  사용 */

@Controller
public class ExceptionHandlerController {

    @GetMapping("controller-null")
    public String nullPointExceptionTest() {

        String str =null;
        System.out.println(str.charAt(0));  // 에러 예외 오류 발생

        return "/";
    }

    // 모든 예외들은 @ExceptionHandler 하위에 존재 한다.
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException exception) {
        // 문자열로 view 페이지 반환 가능
        System.out.println("controller 레벨의 exception 처리");

        return "error/nullPointer";     // view 페이지 반환
    }

    @GetMapping("controller-user")
    public String userException() throws MemberRegistException {


        boolean check = true;
        if (check) {
            throw new MemberRegistException("나는 당신 같은 회원 둔 적 없어.");     //예외처리 해야함
        }

        return "/";     // 활성화 되지 않음  -> MemberRegistException class 만들고 throws 던져주고 @ExceptionHandler 헤줌
    }

    @ExceptionHandler(MemberRegistException.class)
    public String userExceptionTest(MemberRegistException exception, Model model) {

        System.out.println("controller 레벨의 exception 처리");
        model.addAttribute("exception", exception);

        return "error/memberRegist";
    }


    // 다른 컨트롤러 에서 error 발생시 exception 에서 발생하는지 확인
    // advice 공통된 처리 해야 하는 것들의 집합
}
