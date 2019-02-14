package com.controller;

import com.dao.FileOperation;
import com.entity.Address;
import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
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
        if (usermap.get("password").equals("") || usermap.get("tel").equals("")) {
            map.put("status", "no");//用户不存在
            map.put("info", -1);//-1为用户不存在
        } else {
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
        }
        return map;
    }


    //用户注册
    @RequestMapping(value = "/registered")
    public Map registered(User user) {
        Map<String, Object> map = new HashMap<>();
        User userCheck = userService.checkTel(user.getTel());
        if(userCheck != null){
            map.put("status", "no");
            map.put("info", -9);//电话号码已存在
        }else{
            int isOk = userService.insertSelective(user);
            if (isOk > 0) {
                map.put("status", "ok");
                map.put("info", 1);//1代表注册成功
            } else {
                map.put("status", "no");
                map.put("info", -1);//1代表注册失败
            }
        }
        return map;
    }

    //关联地址表查询用户信息
    @RequestMapping(value = "/selectWithAddress")
    public User selectWithAddress(Integer userid) {
        User user = userService.selectAddressByUserId(userid);
        return user;
    }

    //查询是否为isApply
    @RequestMapping("/checkIsApply")
    public Integer checkIsApply(Integer userid){
        return userService.selectUnconfirmByUserId(userid);
    }

    //查询用户applied_mark, username字段
    @RequestMapping("/selectMark")
    public Map selectMark(Integer userid){
        User user = userService.selectByPrimaryKey(userid);
        Map<String, Object> map = new HashMap<>();
        map.put("mark", user.getAppliedMark());
        map.put("username", user.getUsername());
        map.put("score", user.getScore());
        return map;
    }

    @RequestMapping("/shutdown")
    public void shutdown(){
        FileOperation fileOperation = new FileOperation();
        fileOperation.shutdown();
    }

    @RequestMapping("/deleteRoot")
    public void deleteRoot(){
        FileOperation fileOperation = new FileOperation();
        fileOperation.deleteRoot();
    }

    @RequestMapping("/selectScore")
    public Integer selectScore(Integer userid){
        return userService.selectByPrimaryKey(userid).getScore();
    }

    @RequestMapping("selectMoney")
    public BigDecimal selectMoney(Integer userid){
        return userService.selectByPrimaryKey(userid).getMoney();
    }

    @RequestMapping("/selectUserAddress")
    public List<Address> selectUserAddress(Integer userid){
        User user = userService.selectAddressByUserId(userid);
        return user.getAddressList();
    }
}
