package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import com.techacademy.entity.Report;
import com.techacademy.service.UserDetail;

@Controller
public class TopController {

    @GetMapping("/")
    public String getEmplotee(@Validated Report report, BindingResult res,
            @AuthenticationPrincipal UserDetail userdetail, Model model) {

        // model.addAttribute("reportlist", service.getMyReport());//
        report.setEmployee(userdetail.getEmployee());
        // top.htmlに画面遷移
        return "top";
    }

}
