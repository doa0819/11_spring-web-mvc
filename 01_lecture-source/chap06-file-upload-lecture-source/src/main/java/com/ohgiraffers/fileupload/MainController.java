package com.ohgiraffers.fileupload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    //RequsetMapping 대신 GetMapping 사용
    @GetMapping("/")
    public String main() {

        return "main";
    }
}
