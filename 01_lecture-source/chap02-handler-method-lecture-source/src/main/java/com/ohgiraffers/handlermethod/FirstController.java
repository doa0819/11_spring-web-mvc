package com.ohgiraffers.handlermethod;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

@Controller
@RequestMapping("/first/*")
@SessionAttributes("id")    // key 값은 항상 동일 해야 한다.
//@SessionAttributes(names = {"id", "pwd"})       // 여러값 설정시 names={,} 설정(대부분 여러개 가지고 올때는 {}사용)
public class FirstController {

    /* 필기.
    *   컨트롤러의 핸들러 메소드의 반환 값을 void 형으로 설정하게 되면
    *   요청 주소가 view 의 이름이 된다.
    *   => /first/regist 요청이 들어오면 /first/regist 뷰를 응답한다.*/

    @GetMapping("/regist")
    public void regist() {}

    /* 필기.
    *   WebRequest 로 요청 파라미터 전달 받기
    *   WebRequest 라는 녀석은 HttpServletRequest 의 정보를 대부분 가지고 있는
    *   API 로 Servlet 에 종속(휘둘리지?)족이지 않다.
    *   Spring 일부이기 때문에 Servlet 을 사용하는 것처럼 동일하게
    *   사용할 수 있다.
    * */
    @PostMapping("regist")
    public String registMenu(Model model, WebRequest request) {

        String name = request.getParameter("name");   // name = key값
        int price = Integer.parseInt(request.getParameter("price"));
        int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));

        String message = name + "을(를) 신규 메뉴 목록의 " + categoryCode + "번 카테고리에" +
                price + "원으로 등록했습니다.!!!";

        model.addAttribute("message", message);

        return "first/messagePrinter";

    }


    @GetMapping("/modify")
    public void modify() {}


    /* 필기.
    *   아무 값도 넘기지 않았을 때
    *   required 속성 디폴드 값 true
    *   이 속성을 false 로 하게 되면 해당 name 값이 존재하지 않아도 null 로 처리하며
    *   에러가 발생하지 않는다.
    *   defaultValue 를 사용하게 되면 기본 값으로 사용할 수 있다.
    * */
//    @PostMapping("/modify")
//    public String modifyMenuPrice(Model model, @RequestParam String modifyName,
//                                                @RequestParam int modifyPrice) {
//
//        String message = modifyName + "메뉴 가격을 "+ modifyPrice + "원으로 변경하였습니다!!!";
//
//        model.addAttribute("message", message);
//
//        return "first/messagePrinter";
//    }

    // 매게변수명 바꿈(key 값 정해 놓음 => 매게변수명 맞춰줘야 한다.)
//    @PostMapping("/modify")
//    public String modifyMenuPrice(Model model, @RequestParam("modify") String modifyName2,
//                                  @RequestParam ("modify")int modifyPrice2) {
//
//        String message = modifyName2 + "메뉴 가격을 "+ modifyPrice2 + "원으로 변경하였습니다!!!";
//
//        model.addAttribute("message", message);
//
//        return "first/messagePrinter";
//    }

    // 값을 넣지 않아도 0 으로 설정해줘서 0이 나온다.
    @PostMapping("/modify")
    public String modifyMenuPrice(Model model, @RequestParam(required = false) String modifyName,
                                                @RequestParam(defaultValue = "0") int modifyPrice) {

        String message = modifyName + "메뉴 가격을 "+ modifyPrice + "원으로 변경하였습니다!!!";

        model.addAttribute("message", message);

        return "first/messagePrinter";
    }


    @PostMapping("modifyAll")
    public String modifyMenu(Model model, @RequestParam Map<String, String> parameters){

        String modifyMenu = parameters.get("modifyName2");
        int modifyPrice = Integer.parseInt(parameters.get("modifyPrice2"));

        String message = modifyMenu + "메뉴 가격을 "+ modifyPrice + "원으로 변경하였습니다!!!";
        model.addAttribute("message", message);

        return "first/messagePrinter";
    }


    @GetMapping("/search")
    public void search() {}

    /* 필기.
    *   3. @ModelAttribute 를 이용하는방법
    *   DTO 같은 모델을 커멘드 객체로 전달 받는다.
    *   @ModelAttribute(" 모델에 담을 key 값") 이름 정하지 않았다?
    *   -> 타입의 앞글자를 소문자로 한 네이밍 규칙을 따른다.
    *   (@ModelAttribute("menu") MenuDTO menu)
    * */
    @PostMapping("search")
    public String searchMenu(@ModelAttribute MenuDTO menu) {

        System.out.println("menu = " + menu);

        return "first/searchResult";
    }

    
    // @ModelAttribute 생략이 가능하다!!!! => MenuDTO 에 알아서 들어간다!!
    // 하지만 명시적으로 작성하는것이 좋다 => 남들이 알아보기 쉽게 하기 위해서
//    @PostMapping("search")
//    public String searchMenu( MenuDTO menu) {
//
//        System.out.println("menu = " + menu);
//
//        return "first/searchResult";
//    }



    @GetMapping("login")
    public void login(){}


    /* 4-1. session 이용하기
    *   HttpSession 을 매개변수로 선언하면 핸들러 메소드 호출 시 세션 객체를 넣어서 호출한다.
    * */
    @PostMapping("login1")
    public String sessionTest1(HttpSession session,@RequestParam String id){

        session.setAttribute("id",id);      // <input> 값을 가지고 와서 session에 넣어준것이다.

        return "first/loginResult";
    }


    @GetMapping("logout")
    public String logoutTest1(HttpSession session){

        session.invalidate();

        return "first/loginResult";
    }


    /* 필기.
    *   4-2. @SessionAttributes 를 이용하여 session 에 값 담기
    *   클래스 레벨에 @SessionAttributes 어노테이션을 이용하여
    *   세션에 값을 담을 key 값을 설정해두면
    *   Model 영역에 해당 key 로 값이 추가되는 경우 session 에 자동 등록을 해준다.
    * */
    @PostMapping("login2")
    public String sessionTest2(Model model, @RequestParam String id){

        model.addAttribute("id",id);


        return "first/loginResult";
    }

    /*  SessionAttributes 로 등록 된 값은 session 의 상태를 관리하는
    *   SessionStatus 의 setComplete() 메소드를 호출해야 사용이 만료된다.
    * */
    @GetMapping("logout2")
    public String logoutTest2(SessionStatus sessionStatus){

        /* 현재 컨트롤러 세션에 저장된 모든 정보를 제거한다.
        *   개별적인 제거는 불가능하다.
        * */
        sessionStatus.setComplete();

        return "first/loginResult";
    }


    @GetMapping("body")     //void 는 /first/body 라는 걸 페이지로 바로 볼수 있다.
    public void body(){}

    @PostMapping("body")
    /* @RequestBody
    *   http 본문 자체를 읽는 부분을 모델로 변환시켜 주는 어노테이션
    *   출력 시 쿼리스트링 형태의 문자열이 전송된다.
    *   JSON 으로 전달하는 경우 Jackson 의 컨버터로 자동 파싱하여 사용할 수 있다.
    *
    *    RestAPI 작성 시 자주 사용되며, 일반적인 form 전송을 할 때는 거의 사용하지 않는다.
    *   @RequestHeader 헤더에 대한 정보 가져오기
    * */
    public void bodyTest(@RequestBody String body, @RequestHeader("content-type") String contentType) throws UnsupportedEncodingException {
        System.out.println("body = " + body);       // 쿼리스트링 형식으로 값이 넘어옴(사이트에서 넣은 값이 자바로 나옴) => 한글데이터 깨져서 나옴
        System.out.println("contentType = " + contentType);
        System.out.println(URLDecoder.decode(body,"UTF-8"));        // 인코딩 방식 바꿔줘서 한글파일 안깨짐
    }
}
