package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    //!!!! 최초의 페이지 시작을 "/"(최초의 요청) return "main" 으로 많이 한다!!! 꼭 알기!!!
    // => 여태까지 우리는 index 로 진행 했는데 "/" 많이 한다!!!
    @RequestMapping(value = {"/", "/main"})
    public String main(){

        return "main";
    }
}
