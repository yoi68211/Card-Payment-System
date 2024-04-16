package com.os.controller;

import com.os.customer.dto.UpdateDTO;
import com.os.customer.service.UpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PayInfoRestController {
    private final UpdateService updateService;

    @PostMapping("/payDetailEdit")
    public void updateDetail(@RequestBody @Valid UpdateDTO updateDTO){
        if(updateDTO.isAutoOrBasic()){
            boolean check = updateService.updateBasic(updateDTO);
        } else{
            boolean check = updateService.updateAuto(updateDTO);
        }
    }

    @PostMapping("/payDetailDel")
    public void deleteDetail(@RequestBody @Valid UpdateDTO updateDTO){

        boolean check = updateService.delUpdate(updateDTO);
    }

    @PostMapping("/autoPayStop")
    public void autoPayStop(@RequestBody @Valid UpdateDTO updateDTO){

        updateService.autoPayStop(updateDTO);
    }
}
