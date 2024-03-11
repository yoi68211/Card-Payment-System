package com.os.controller;

import com.os.entity.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InsertController {
    @PostMapping("send")
    public String send(@RequestBody List<Product> product){

        return null;
    }

}
