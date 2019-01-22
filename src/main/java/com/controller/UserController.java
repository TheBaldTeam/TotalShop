package com.controller;

import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired(required = false)
    private UserService userService;

    //用户登录
    @RequestMapping(value = "/login")
    public Map login(User user) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> usermap = new HashMap<>();
        usermap.put("username", user.getUsername());
        usermap.put("password", user.getPassword());
        usermap.put("tel", user.getTel());
        User newUser = userService.login(usermap);
        if (newUser != null) {
            if (newUser.getIsAdmin() == 1) {
                map.put("status", "ok");
                map.put("info", 1);//1为管理员
                map.put("userId", newUser.getId());
            } else if (newUser.getIsSeller() == 1) {
                map.put("status", "ok");
                map.put("info", 2);//2为商家
                map.put("userId", newUser.getId());
            } else if (newUser.getIsVip() == 1) {
                map.put("status", "ok");
                map.put("info", 3);//3为vip
                map.put("userId", newUser.getId());
            } else {
                map.put("status", "yes");
                map.put("info", 0);//0为普通用户
                map.put("userId", newUser.getId());
            }
        } else {
            map.put("status", "no");//用户不存在
            map.put("info", -1);//-1为用户不存在
        }
        return map;
    }

    //用户注册
    @RequestMapping(value = "/registered")
    public Map registered(User user) {
        /*System.out.println(username+"      "+tel);
          return null*/
        int isOk = userService.insertSelective(user);
        Map<String, Object> map = new HashMap<>();
        if (isOk > 0) {
            map.put("status", "ok");
            map.put("info", 1);//1代表注册成功
        } else {
            map.put("status", "no");
            map.put("info", -1);//1代表注册失败
        }
        return map;
    }

    //关联地址表查询用户信息
    @RequestMapping(value = "/selectWithAddress")
    public User selectWithAddress(Integer userid) {
        User user = userService.selectAddressByUserId(userid);
        return user;
    }
}
