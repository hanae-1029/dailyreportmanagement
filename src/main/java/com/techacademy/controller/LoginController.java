package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    /** ログイン画面を表示 */
    @GetMapping("/login")
    public String getLogin() {
        // login.htmlに画面遷移
        return "login";
    }

}
