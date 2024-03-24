package com.os.controller;

import com.os.dto.InsertDTO;
import com.os.dto.UpdateDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayInfoRestController {
    
    @PostMapping("/payDetailEdit")
    public void updateDetail(@RequestBody @Valid UpdateDTO updateDTO){
        System.out.println("DTO =>" + updateDTO.toString());
        System.out.println("아작스로 rest 호출");

    }
}
