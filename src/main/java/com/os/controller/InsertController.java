package com.os.controller;

import com.os.dto.InsertDTO;
import com.os.dto.SavePaymentDTO;
import com.os.entity.User;
import com.os.service.PaymentInsertService;
import com.os.service.SavePaymentService;
import com.os.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequiredArgsConstructor
public class InsertController {

    private final PaymentInsertService paymentService;
    private final SavePaymentService savePaymentService;
    private final UserService userService;

    @PostMapping("/insert")
    public String insertData(@RequestBody @Valid InsertDTO dto) {
           boolean res = paymentService.insert_basic(dto);
           if (res){
               return "ok";
           }else {
               return "bad";
           }
    }

    @PostMapping("/save")
    public String save(@RequestBody SavePaymentDTO dto, RedirectAttributes redirectAttributes){
        System.out.println(dto.toString());
        User user = userService.findId();
        long userId = user.getId();
        long count = savePaymentService.countByUserId(userId);
        System.out.println("count : " + count);
        boolean res;
        if(count == 0){
            res = savePaymentService.save(dto);

        }else{
            res = savePaymentService.save_update(dto);
        }
        if(res) {
            redirectAttributes.addFlashAttribute("message", "저장이 완료되었습니다.");
            return "redirect:/insert_form";
        }else{
            return null;
        }
    }

//    @PostMapping("/load")
//    public String load(Model model){
//        User user = userService.findId();
//        long userId = user.getId();
//
//        SavePaymentLoadDTO savePaymentLoadDTO = savePaymentService.load_info(userId);
//        long s_paymentId = savePaymentLoadDTO.getId();
//        List<SaveProductDTO> saveProductDTO = savePaymentService.load_list(s_paymentId);
//
//        model.addAttribute("spy",savePaymentDTO);
//        model.addAttribute("spr",savePaymentDTO);
//        return "insert_form";
//    }

}
