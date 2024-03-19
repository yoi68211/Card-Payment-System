package com.os.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {


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
    public String insert_form(){ return "insert_form"; }

}