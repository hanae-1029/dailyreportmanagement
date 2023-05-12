package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

@Controller
public class TopController {
    private final ReportService service;

    public TopController(ReportService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String getMyReport(@Validated Report report, BindingResult res,
            @AuthenticationPrincipal UserDetail userdetail, Model model) {

        model.addAttribute("reportlist", service.getMyReport(userdetail.getEmployee()));

        // top.htmlに画面遷移
        return "top";
    }

}
