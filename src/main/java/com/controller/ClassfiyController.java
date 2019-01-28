package com.controller;

import com.entity.Shop;
import com.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/classfiy")
public class ClassfiyController {

    @Autowired
    private ShopService shopService;

    @RequestMapping("/selectLevel")
    public List<Shop> getStaticImg(){
        return shopService.selectTwo();
    }

}
