package com.os.service;

import com.os.dto.SavePaymentDTO;
import com.os.dto.SaveProductDTO;
import com.os.entity.SavePayment;
import com.os.entity.SaveProduct;
import com.os.entity.User;
import com.os.repository.SavePaymentRepository;
import com.os.repository.SaveProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class SavePaymentService {
    private final SavePaymentRepository savePaymentRepository;
    private final SaveProductRepository saveproductRepository;
    private final UserService userService;


    public boolean save(SavePaymentDTO dto) {

        User user = userService.findId();

        if (user != null) {

            SavePayment savePayment= new SavePayment();
            savePayment.toSavePayment(dto);
            savePaymentRepository.save(savePayment);

            for (SaveProductDTO saveProductDTO : dto.getProductList()) {
                SaveProduct saveProduct = new SaveProduct();
                saveProduct.setS_productName(saveProductDTO.getS_productName());
                saveProduct.setS_productTotalItem(saveProductDTO.getS_productTotalItems());
                saveProduct.setS_productPrice(saveProductDTO.getS_productPrice());

                saveProduct.setSavePayment(savePayment);

                saveproductRepository.save(saveProduct);
            }

        return true;
        }else {
            return false;
        }
    }

    public boolean save_update(SavePaymentDTO dto){
        User user = userService.findId();

        if (user != null) {
            Optional<SavePayment> optionalSavePayment = savePaymentRepository.findByUserId(user.getId());

            if (optionalSavePayment.isPresent()) {
                SavePayment savePayment = optionalSavePayment.get();

                List<SaveProduct> savedProducts = savePayment.getSaveProducts();

                List<SaveProductDTO> newProducts = dto.getProductList();

                int savedCount = savedProducts.size();
                int newCount = newProducts.size();

                if (savedCount > newCount) {
                    // 보낸 정보 개수만큼만 업데이트
                    for (int i = 0; i < newCount; i++) {
                        SaveProduct savedProduct = savedProducts.get(i);
                        SaveProductDTO newProduct = newProducts.get(i);

                        // 업데이트
                        savedProduct.setS_productName(newProduct.getS_productName());
                        savedProduct.setS_productTotalItem(newProduct.getS_productTotalItems());
                        savedProduct.setS_productPrice(newProduct.getS_productPrice());

                        saveproductRepository.save(savedProduct);
                    }
                    for (int i = newCount; i < savedCount; i++) {
                        SaveProduct savedProduct = savedProducts.get(i);
                        saveproductRepository.delete(savedProduct);
                    }
                }
                else {
                    // 보낸 정보 업데이트
                    for (int i = 0; i < savedCount; i++) {
                        SaveProduct savedProduct = savedProducts.get(i);
                        SaveProductDTO newProduct = newProducts.get(i);

                        // 업데이트
                        savedProduct.setS_productName(newProduct.getS_productName());
                        savedProduct.setS_productTotalItem(newProduct.getS_productTotalItems());
                        savedProduct.setS_productPrice(newProduct.getS_productPrice());

                        saveproductRepository.save(savedProduct);
                    }
                    for (int i = savedCount; i < newCount; i++) {
                        SaveProductDTO newProduct = newProducts.get(i);
                        SaveProduct saveProduct = new SaveProduct();
                        saveProduct.setS_productName(newProduct.getS_productName());
                        saveProduct.setS_productTotalItem(newProduct.getS_productTotalItems());
                        saveProduct.setS_productPrice(newProduct.getS_productPrice());
                        saveProduct.setSavePayment(savePayment);

                        saveproductRepository.save(saveProduct);
                    }
                }
                // 새로운 정보로 업데이트합니다.
                savePayment.setS_paymentName(dto.getS_paymentName());
                savePayment.setS_paymentEmail(dto.getS_paymentEmail());
                savePayment.setS_paymentPhone(dto.getS_paymentPhone());
                savePayment.setS_paymentAddress(dto.getS_paymentAddress());
                savePayment.setS_paymentTitle(dto.getS_paymentTitle());
                savePayment.setS_paymentType(dto.getS_paymentType());
                savePayment.setS_paymentFirstpay(dto.getS_paymentFirstPay());
                savePayment.setS_paymentBizTo(dto.getS_paymentBizTo());
                savePayment.setS_paymentCycle(dto.getS_paymentCycle());
                savePayment.setS_paymentDate(dto.getS_paymentDate());
                savePayment.setS_paymentPay(dto.getS_paymentPay());

                // 엔티티를 저장합니다.
                savePaymentRepository.save(savePayment);
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }

    }

//    public SavePaymentLoadDTO load_info(long userId) {
//        return savePaymentRepository.findFirstByUserIdOrderByCreatedAtDesc(userId);
//    }
//
//    public List<SaveProductDTO> load_info(long s_paymentId) {
////        return saveproductRepository.findFirstByUserIdOrderByCreatedAtDesc(s_paymentId);
//        return null;
//    }
    public long countByUserId(long userId){
        return savePaymentRepository.countByUserId(userId);
    }
}