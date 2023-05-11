package com.techacademy.controller;

import java.time.LocalDateTime;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("report")
public class ReportController {
    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    /** 一覧画面を表示 */
    @GetMapping("/list")
    public String getList(Model model) {
        // 全件検索結果をModelに登録
        model.addAttribute("reportlist", service.getReportList());
        // user/list.htmlに画面遷移
        return "report/list";
    }

    /** 詳細画面を表示 */
    @GetMapping("/shousai/{id}")
    public String getShousai(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("report", service.getReport(id));
        // employee/shousai.htmlに画面遷移
        return "report/shousai";
    }

    /** 新規登録画面を表示 */
    @GetMapping("/shinki")
    public String getShinki(@ModelAttribute Report report) {
        // 新規登録画面に遷移
        return "report/shinki";
    }

    /** 新規登録処理 */
    @PostMapping("/shinki")
    public String postShinki(@Validated Report report, BindingResult res,
            @AuthenticationPrincipal UserDetail userdetail, Model model) {
        if (res.hasErrors()) {
            // エラーあり
            return getShinki(report);
        }
        // 登録
        report.setEmployee(userdetail.getEmployee());
        report.setCreatedAt(LocalDateTime.now());
        report.setUpdatedAt(LocalDateTime.now());
        service.saveReport(report);
        // 一覧画面にリダイレクト
        return "redirect:/report/list";
    }

    /** 更新画面を表示 */
    @GetMapping("/update/{id}")
    public String getUpdate(@PathVariable("id") Integer id, Model model) {
        // Modelに登録
        model.addAttribute("report", service.getReport(id));
        // 更新画面に遷移
        return "report/update";
    }

    /** 更新処理 */
    @PostMapping("/update/{id}")
    public String postUpdate(@Validated Report report, BindingResult res,
            @AuthenticationPrincipal UserDetail userdetail, Model model) {
        if (res.hasErrors()) {
            // エラーあり
            return "report/update";
        }

        // 登録
        Report motoReport = service.getReport(report.getId());
        report.setCreatedAt(motoReport.getCreatedAt());
        report.setUpdatedAt(motoReport.getUpdatedAt());
        report.setEmployee(userdetail.getEmployee());
        service.saveReport(report);
        // 一覧画面にリダイレクト
        return "redirect:/report/list";
    }

}
