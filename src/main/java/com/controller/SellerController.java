package com.controller;

import com.dao.FileOperation;
import com.entity.Seller;
import com.entity.SellerAddress;
import com.entity.SellerBcImg;
import com.entity.User;
import com.service.SellerAddressService;
import com.service.SellerBcImgService;
import com.service.SellerService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired(required = false)
    private SellerService sellerService;
    @Autowired(required = false)
    private UserService userService;
    @Autowired(required = false)
    private SellerBcImgService sellerBcImgService;
    @Autowired(required = false)
    private SellerAddressService sellerAddressService;

    //提交入驻申请
    @RequestMapping(value = "/applyforSeller")
    public Map applyforSeller(Integer userid, @RequestParam(value = "applyMoney", defaultValue = "0") BigDecimal applyMoney, Seller seller, SellerAddress sellerAddress) {
        Map<String, Object> map = new HashMap<>();
        //查询User信息并修改is_seller字段
        User user = userService.selectByPrimaryKey(userid);
        if (user.getIsApply() == 0) {
            user.setIsApply(1);
            user.setApplyMoney(applyMoney);
            int isOk = userService.updateByPrimaryKeySelective(user);
            if (isOk > 0) {
                //插入Seller数据
                seller.setUserId(userid);
                int isOk2 = sellerService.selectKey(seller);
                if (isOk2 > 0) {
                    //插入sellerAddress
                    sellerAddress.setSellerName(user.getUsername());
                    sellerAddress.setSellerId(seller.getId());
                    int isOk3 = sellerAddressService.insertSelective(sellerAddress);
                    if (isOk3 > 0) {
                        map.put("status", "ok");
                        map.put("info", 1);
                    } else {
                        map.put("status", "no");
                        map.put("info", -1);
                    }
                } else {
                    map.put("status", "no");
                    map.put("info", -1);
                }
            } else {
                map.put("status", "no");
                map.put("info", -1);
            }
        } else if (user.getIsApply() == 1) {
            map.put("status", "no");
            map.put("info", -9);//-9为已提交注册，不予重新注册
        }
        return map;
    }

    @RequestMapping(value = "/upload")
    public Map upload(Integer userid, HttpServletRequest request, @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        Map<String, Object> map = new HashMap<>();
        request.setCharacterEncoding("UTF-8");
        FileOperation fileOperation = new FileOperation();
        String imgName = fileOperation.SellerBcImgAdd(image);
        if (!imgName.equals("error")) {
            //新增数据库
            Seller seller = sellerService.selectByUserid(userid);
            SellerBcImg sellerBcImg = new SellerBcImg();
            sellerBcImg.setUserId(userid);
            sellerBcImg.setSellerId(seller.getId());
            sellerBcImg.setImg(imgName);
            int isOk = sellerBcImgService.insertSelective(sellerBcImg);
            if (isOk > 0) {
                map.put("status", "ok");
                map.put("info", 1);
            } else {
                map.put("status", "no");
                map.put("info", 0);
            }
        } else {
            map.put("status", "no");
            map.put("info", 0);
        }
        return map;
    }

    @RequestMapping("/isSeller")
    public Map checkIsSeller(Integer userid){
        Map<String, Object> map = new HashMap<>();
        User user = userService.selectByPrimaryKey(userid);
        Seller seller = sellerService.selectByUserid(userid);
        map.put("isSeller", user.getIsSeller());
        map.put("sellerId", seller.getId());
        return map;
    }
}

