package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    @GetMapping("/shousai/{id}")
    public String getShousai(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("employee", service.getEmployee(id));
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
    public String postShinki(@Validated Employee employee, BindingResult res, Model model) {
        if (res.hasErrors()) {
            // エラーあり
            return getShinki(employee);
        }
        // 社員番号、氏名、パスワードが空の場合登録画面に戻る
        if ("".equals(employee.getAuthentication().getCode())) {

            return "employee/shinki";
        }
        if ("".equals(employee.getName())) {

            return "employee/shinki";
        }
        if ("".equals(employee.getAuthentication().getPassword())) {

            return "employee/shinki";
        }
        // 登録
        employee.getAuthentication().setEmployee(employee);
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

    /** 更新（編集）画面を表示 */
    @GetMapping("/update/{id}")
    public String getUpdate(@PathVariable("id") Integer id, Model model) {
        // modelに登録
        model.addAttribute("employee", service.getEmployee(id));
        // 更新（編集）画面に遷移
        return "employee/update";

    }

    /** 更新（編集）処理 */
    @PostMapping("/update/{id}")
    public String postEmployee(Employee employee) {
        // 氏名が空の場合編集ページへ戻る
        if ("".equals(employee.getName())) {

            return "employee/update";
        }
        // 登録
        employee.getAuthentication().setEmployee(employee);
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

    /** 削除処理 */
    @GetMapping("/{id}/delete")
    public String deleteEmployee(Model model, Employee Employee) {
        // データベースのデータを削除する
        service.delete(Employee.getId());
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }
}
