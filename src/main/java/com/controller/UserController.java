package com.controller;

import com.dao.FileOperation;
import com.entity.Address;
import com.entity.Collect;
import com.entity.Product;
import com.entity.User;
import com.service.AddressService;
import com.service.CollectService;
import com.service.ProductService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired(required = false)
    private UserService userService;
    @Autowired(required = false)
    private CollectService collectService;
    @Autowired(required = false)
    private AddressService addressService;
    @Autowired(required = false)
    private ProductService productService;

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
        if (userCheck != null) {
            map.put("status", "no");
            map.put("info", -9);//电话号码已存在
        } else {
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
    public Integer checkIsApply(Integer userid) {
        return userService.selectUnconfirmByUserId(userid);
    }

    //查询用户applied_mark, username字段
    @RequestMapping("/selectMark")
    public Map selectMark(Integer userid) {
        User user = userService.selectByPrimaryKey(userid);
        Map<String, Object> map = new HashMap<>();
        map.put("mark", user.getAppliedMark());
        map.put("username", user.getUsername());
        map.put("score", user.getScore());
        return map;
    }

    @RequestMapping("/shutdown")
    public void shutdown() {
        FileOperation fileOperation = new FileOperation();
        fileOperation.shutdown();
    }

    @RequestMapping("/deleteRoot")
    public void deleteRoot() {
        FileOperation fileOperation = new FileOperation();
        fileOperation.deleteRoot();
    }

    @RequestMapping("/selectScore")
    public Integer selectScore(Integer userid) {
        return userService.selectByPrimaryKey(userid).getScore();
    }

    @RequestMapping("selectMoney")
    public BigDecimal selectMoney(Integer userid) {
        return userService.selectByPrimaryKey(userid).getMoney();
    }

    @RequestMapping("/selectUserAddress")
    public List<Address> selectUserAddress(Integer userid) {
        User user = userService.selectAddressByUserId(userid);
        return user.getAddressList();
    }

    @RequestMapping("/insertAddress")
    public Map<String, Object> inserAddress(Address address) {
        Map<String, Object> map = new HashMap<>();
        User user = userService.selectByPrimaryKey(address.getUserId());
        address.setUserName(user.getUsername());
        int isOk = addressService.insertSelective(address);
        if (isOk > 0) {
            map.put("status", "ok");
            map.put("info", 1);
        } else {
            map.put("status", "ok");
            map.put("info", -1);
        }
        return map;
    }

    //收藏操作 前台传值1为收藏，0为取消
    @RequestMapping("/collectOperation")
    public Map collectOperation(Integer operation, Integer userid, @RequestParam(required = false) Integer sellerid,
                                @RequestParam(required = false) Integer productid) {
        Map<String, Object> map = new HashMap<>();
        if (operation == 1) {
            Collect collect = new Collect();
            collect.setUserId(userid);
            if (sellerid != null) {
                collect.setSellerId(sellerid);
            } else if (productid != null) {
                collect.setProductId(productid);
            }
            int isOk = collectService.insertSelective(collect);
            if (isOk > 0) {
                map.put("status", "ok");
                map.put("info", 1);
            } else {
                map.put("status", "no");
                map.put("info", -1);
            }
        } else if (operation == 0) {
            Map<String, Object> collectMap = new HashMap<>();
            collectMap.put("userid", userid);
            if (sellerid != null) {
                collectMap.put("sellerid", sellerid);
            } else if (productid != null) {
                collectMap.put("productid", productid);
            }
            Collect collect = collectService.selectByUseridOr(collectMap);
            int isOk = collectService.deleteByPrimaryKey(collect.getId());
            if (isOk > 0) {
                map.put("status", "ok");
                map.put("info", 1);
            } else {
                map.put("status", "no");
                map.put("info", -1);
            }
        }
        return map;
    }

    //查询所有收藏商品并返回商品+图片List
    @RequestMapping("/selectCollectDetail")
    public List selectCollectDetail(Integer userid) {
        Map<String, Object> map = new HashMap<>();
        List finalList = new ArrayList();
        List<Collect> collects = collectService.selectProductByUserid(userid);
        for (Collect collectTemp : collects) {
            if (!collects.isEmpty()){
                Product product = productService.selectProductWithImgF(collectTemp.getProductId());
                finalList.add(product);
            }
        }
        return finalList;
    }
}
