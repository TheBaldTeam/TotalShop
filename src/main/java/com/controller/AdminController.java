package com.controller;

import com.entity.User;
import com.service.SellerBcImgService;
import com.service.SellerService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired(required = false)
    private SellerService sellerService;
    @Autowired(required = false)
    private UserService userService;
    @Autowired(required = false)
    private SellerBcImgService sellerBcImgService;

    @RequestMapping("/selectWtihoutConfirm")
    public List<User> selectWtihoutConfirm(){
        Map<String, Object> map = new HashMap<>();
        List<User> userList = userService.selectUnconfirmed();
        return userList;
    }

    @RequestMapping("/sellerRecipt")
    public Map sellerRecipt(User user){
        Map<String, Object> map = new HashMap<>();
        int isOk = userService.updateByPrimaryKeySelective(user);
        if(isOk > 0){
            map.put("status","ok");
            map.put("info", 1);
        }else{
            map.put("status","no");
            map.put("info", 0);
        }
        return map;
    }
}