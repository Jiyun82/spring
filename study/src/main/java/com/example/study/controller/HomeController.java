package com.example.study.controller;

import com.example.study.SessionConstants;
import com.example.study.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Member member, Model model){
        model.addAttribute("member", member);

        return "home";
    }
}
