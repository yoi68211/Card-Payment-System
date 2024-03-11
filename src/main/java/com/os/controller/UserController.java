package com.os.controller;

import com.os.dto.CustomUserDetails;
import com.os.dto.UserResponse;
import com.os.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/update")
    public String updateUser(Model model) {


        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = currentUser.getUserId();


        UserResponse userResponse = userService.getUserResponseById(userId);

        model.addAttribute("userResponse", userResponse);

        return "/user/update";
    }


}
