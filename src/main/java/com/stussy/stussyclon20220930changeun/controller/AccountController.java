package com.stussy.stussyclon20220930changeun.controller;

import com.stussy.stussyclon20220930changeun.exception.dto.RegisterReqDto;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    @GetMapping("/account/login")
    public String login(Model model,
                        @RequestParam @Nullable String email,
                        @RequestParam @Nullable String error) { //Nullable은 email을 받아도 되고 안받아도 되는것을 허용 해주는 어노테이션
        model.addAttribute("email", email == null ? "" : email);
        model.addAttribute("error", error == null ? "" : error);
        return "account/login";
    }

    @GetMapping("/account/register")
    public String register() {
        return "account/register";
    }
}
