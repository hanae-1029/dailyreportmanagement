package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Employee;
import com.techacademy.service.EmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    /** 一覧画面を表示 */
    @GetMapping("/list")
    public String getList(Model model) {
        // 全件結果をModelに登録
        model.addAttribute("employeelist", service.getEmployeeList());
        // employee/list.htmlに画面遷移
        return "employee/list";
    }

    /** 詳細画面を表示 */
    @GetMapping("/shousai/{id}/")
    public String getShousai(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("shousai", service.getShousai(id));
        // employee/shousai.htmlに画面遷移
        return "employee/shousai";
    }

    /** 新規登録画面を表示 */
    @GetMapping("/shinki")
    public String getShinki(@ModelAttribute Employee employee) {
        // 新規登録画面に遷移
        return "employee/shinki";
    }

    /** 新規登録処理 */
    @PostMapping("/shinki")
    public String postShinki(Employee employee) {
        // 登録
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

}
