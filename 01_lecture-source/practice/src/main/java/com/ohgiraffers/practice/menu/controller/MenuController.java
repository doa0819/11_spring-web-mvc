package com.ohgiraffers.practice.menu.controller;

import com.ohgiraffers.practice.menu.model.dto.MenuDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    // 전체 메뉴 조회
    @GetMapping("/list")
    public String findMenuList(Model model) {

        List<MenuDTO> menuList = menuService.findAllMenu();
        model.addAttribute("menuList", menuList);

        return "menu/list";



    }
}
