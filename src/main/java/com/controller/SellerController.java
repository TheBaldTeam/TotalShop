package com.controller;

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
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    //查看当前申请信息
    @RequestMapping("/showapplyMessage")
    public User showapplyMessage(Integer userid){
        User user = userService.selectByPrimaryKey(userid);
        return user;
    }

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
                int isOk2 = sellerService.insertSelective(seller);
                if (isOk2 > 0) {
                    //插入sellerAddress
                    int isOk3 = sellerAddressService.insertSelective(sellerAddress);
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
        } else if (user.getIsSeller() == 1) {
            map.put("status", "no");
            map.put("info", -9);//-9为已是商家，不予重新注册
        }
        return map;
    }

    @RequestMapping(value = "/upload")
    public Map upload(Integer userid, HttpServletRequest request, @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        Map<String, Object> map = new HashMap<>();
        request.setCharacterEncoding("UTF-8");
        if (!image.isEmpty()) {
            String fileName = image.getOriginalFilename();
            String path = null;
            String type = null;
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase()) || "JPEG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath = "D:\\JavaOperation\\IDEA\\TotalShop\\src\\main\\resources\\static\\SellerPhoto\\";
                    File file = new File(realPath);
                    if(!file.exists()){
                        file.mkdirs();
                    }
                    // 自定义的文件名称
                    String uuid = UUID.randomUUID().toString();
                    String trueFileName = uuid + "." + type;
                    // 设置存放图片文件的路径
                    path = realPath + trueFileName;
                    image.transferTo(new File(path));
                    //新增数据库
                    SellerBcImg sellerBcImg = new SellerBcImg();
                    sellerBcImg.setUserId(userid);
                    sellerBcImg.setImg(path);
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
                    map.put("info", -1);//不是我们想要的文件类型,请按要求重新上传
                }
            } else {
                map.put("status", "no");
                map.put("info", -2);//文件类型为空
            }
        } else {
            map.put("status", "no");
            map.put("info", -3);//没有找到相对应的文件
        }
        return map;
    }
}

