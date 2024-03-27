package com.os.controller;

import com.os.dto.*;
import com.os.entity.SaveProduct;
import com.os.entity.User;
import com.os.service.PaymentInsertService;
import com.os.service.SavePaymentService;
import com.os.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class InsertController {

    private final PaymentInsertService paymentService;
    private final SavePaymentService savePaymentService;
    private final UserService userService;

    @PostMapping("/insert")
    public String insertData(@RequestBody @Valid InsertDTO dto) {
        System.out.println(dto.toString());
        User user = userService.findId();
        if (user != null) {
            paymentService.insert_basic(dto, user);
            return "ok";
        } else {
            return "bad";
        }
    }



@PostMapping("/save")
public String save(@RequestBody SavePaymentDTO dto, RedirectAttributes redirectAttributes) {
    System.out.println(dto.toString());
    User user = userService.findId();

    if (user.getSavePayment() == null) {
        System.out.println("save");
        savePaymentService.save(dto, user);
    } else {
        System.out.println("saveupdate");
        savePaymentService.save_update(dto);
    }
    redirectAttributes.addFlashAttribute("message", "저장이 완료되었습니다.");
    return "redirect:/insert_form";
}

    @GetMapping("/load")
    public SavePaymentLoadDTO load(){
        User user = userService.findId();
        long userId = user.getId();

        SavePaymentLoadDTO savePaymentLoad = savePaymentService.load_info(userId);
        long s_payment_id = savePaymentLoad.getId();
        List<SaveProduct> saveProduct = savePaymentService.load_list(s_payment_id);

        List<SaveProductDTO> productDTOList = saveProduct.stream()
                .map(this::convertToDTO) // 변환 메서드 호출
                .collect(Collectors.toList());

        savePaymentLoad.setProductList(productDTOList);
        System.out.println("dto: " + savePaymentLoad.toString());
        return savePaymentLoad;
    }
    private SaveProductDTO convertToDTO(SaveProduct saveProduct) {
        SaveProductDTO saveProductDTO = new SaveProductDTO();
        saveProductDTO.setS_productName(saveProduct.getS_productName());
        saveProductDTO.setS_productPrice(saveProduct.getS_productPrice());
        saveProductDTO.setS_productTotalItems(saveProduct.getS_productTotalItem());

        return saveProductDTO;
    }
}
