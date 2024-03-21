package com.os.controller;

import com.os.entity.User;
import com.os.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class MainController {

    public final UserService userService;

    @GetMapping
    public String index() {
        return "login";
    }
    
    @GetMapping("dashboard")
    public String login() {
        return "dashboard";
    }


    @GetMapping("/join")
    public String joinPage()   {

        return "join";

    }
    @GetMapping("/paymentDetails")
    public String paymentDetailsPage()   {

        return "paymentDetails";

    }

    @GetMapping("pageSample")
    public String pageSample() {
        return "/pageSample";
    }

    @GetMapping("insert_form")
    public String insert_form(){
        User user = userService.findId();
        long userId = user.getId();
        System.out.println(userId);
        return "insert_form";
    }

}
