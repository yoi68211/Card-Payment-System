package com.os.controller;

import com.os.dto.InsertDTO;
import com.os.dto.ProductDTO;
import com.os.dto.UpdateDTO;
import com.os.service.UpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequiredArgsConstructor
@RestController
public class PayInfoRestController {
    private final UpdateService updateService;

    @PostMapping("/payDetailEdit")
    public void updateDetail(@RequestBody @Valid UpdateDTO updateDTO){
        boolean check = updateService.update(updateDTO);
    }

    @PostMapping("/payDetailDel")
    public void deleteDetail(@RequestBody @Valid UpdateDTO updateDTO){
        System.out.println("삭제 실행 => " + updateDTO.getPaymentId());

        boolean check = updateService.delUpdate(updateDTO);

    }
}
