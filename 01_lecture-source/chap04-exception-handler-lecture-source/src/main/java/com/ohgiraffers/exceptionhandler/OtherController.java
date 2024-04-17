package com.ohgiraffers.exceptionhandler;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherController {

    /* 필기.
    *   다른 컨트롤러에서 Exception 이 발생 했을때
    *   우리가 ExceptionHandlerController 에 정의 해둔
    *   @ExceptionHandler 가 동작을 하지 않는다.
    * */

    // nullpointexception 발생(예외가 발생 할때 마다 exceotionhandler 을써야 하는데 그것들을 줄이기 위해 모아 놓은 aop 이용한다)
    @GetMapping("other-controller-null")
    public String otherNUllPointExceptionTest() {

        String str = null;;
        System.out.println(str.charAt(0));

        return "/";
    }

    @GetMapping("other-controller-user")
    public String otherUserExceptionTest() throws MemberRegistException {

        boolean check = true;
        if (check) {
            throw new MemberRegistException("너 또 왔니..?");
        }

        return "/";
    }


    @GetMapping("other-controller-array")
    public String otherArrayExceptionTest() {

        // 배열 길이 0 첫번쨰 인덱스 찾음 그러나 없음 그래서 에러가 남
        double[] array = new double[0];
        System.out.println(array[0]);

        return "/";

    }
}
