package com.ohgiraffers.requestmapping;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller     // Bean 등록
@RequestMapping("/order/*")
/* 필기.
*   클래스 레벨에 @RequestMapping 어노테이션 사용이 가능하다.
*   클래스 레벨에 공통 URL 을 설정하면 핸들러 메소드에 URL 에 중복되는
*   내용을 작성하지 않아도 된다.
*   이 때 오일드카드(*) 를 이용해서 포괄적인 URL 패턴을 이용할 수 있다.
* */
public class ClassMappingTestController {

    @GetMapping("/regist")
    public String regist(Model model) {

        model.addAttribute("message",
                "GET 방식의 주문 등록용 핸들로 메소드 호출함...");

        return "mappingResult";
    }


    // 다른 url 이여도 한군데다가 모아서 사용 가능 => value = {} 사용!!!
    @RequestMapping(value = {"/modify", "/delete"}, method = RequestMethod.POST)
    public String modifyDelete(Model model){

        model.addAttribute("message",
                "POST 방식의 주문 정보 수정과 주문 정보 삭제 공통 처리용" + "핸들러 메소드 호출됨...");


        return "mappingResult";
    }

    /* 필기.
    *   @PathVariable 어노테이션을 이용해 요청 path로 부터 변수를 받아올 수 있다.
    *   path variable 로 전달 되는 {변수명} 은 반드시 매개변수 명과 동일해야 한다.
    *   만약 동일하지 않으면 @PathVariable("이름") 을 설정해주어야 한다.
    * */

    @GetMapping("/detail/{orderNo}")        // index 파일에서 3를 넘겼는게 그것은 orderNo 에 저장
    public String selectOrderDetail(Model model, @PathVariable int orderNo) {

        model.addAttribute("message",
                orderNo + "번 주문 상세 내용 조회용 핸들러 메소드 호출됨...");

        return "mappingResult";
    }



    // 만약 변수명이 다르면 @PathVariable("orderNo") 보내주면 된다.
//    @GetMapping("/detail/{orderNo}")
//    public String selectOrderDetail(Model model, @PathVariable("orderNo") int orderNo2) {
//
//        model.addAttribute("message",
//                orderNo2 + "번 주문 상세 내용 조회용 핸들러 메소드 호출됨...");
//
//        return "mappingResult";
//    }





}
