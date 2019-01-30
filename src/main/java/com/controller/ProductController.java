package com.controller;

import com.dao.FileOperation;
import com.entity.*;
import com.service.ProductImgService;
import com.service.ProductService;
import com.service.SellerPimgService;
import com.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
/*
 * author:@洪伟
 *
 *
 * */

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private VersionService versionService;
    @Autowired
    private ProductImgService productImgService;
    //关联商品图片/商家
    @Autowired
    private SellerPimgService sellerPimgService;

    @RequestMapping("/getAll")
    @ResponseBody
    public List<Product> getAll() {
        List<Product> productLsit = productService.selectAll();
        return productLsit;
    }

    //添加商品，做商品表和版本表操作
    @RequestMapping(value = "/insertP")
    @ResponseBody
    public Map insertP(Product product, String versionList, Integer sellerid) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(product);
        if (product.getGroupPrice() != 0) {
            product.setIsGroup(1);
        } else {
            product.setIsGroup(0);
        }
        product.setStatus((byte) 1);
        product.setCreated(new Date());
        product.setUpdated(new Date());
        int isOk = productService.selectKey(product);
        if (isOk > 0) {
            SellerWithProductImg sellerWithProductImg = new SellerWithProductImg();
            sellerWithProductImg.setProductId(product.getId());
            sellerWithProductImg.setSellerId(sellerid);
            sellerPimgService.insertSelective(sellerWithProductImg);
            List<String> versions = JSON.parseArray(versionList,String.class);
            System.out.println(versions);
            for (String versionItem : versions) {
                Version version = new Version();
                version.setProductId(product.getId());
                version.setProductName(product.getTitle());
                version.setVersionName(versionItem);
                versionService.insertSelective(version);
            }
            map.put("status", "yes");
            map.put("info", 1);
            map.put("productid", product.getId());
        } else {
            map.put("status", "no");
            map.put("info", -1);
        }
        return map;
    }

    //上传商品图片，做io操作和商品图片表操作
    @RequestMapping("/upload")
    @ResponseBody
    public Map upload(MultipartFile image, HttpServletRequest request, Integer isCover, Integer productid) throws UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<>();
        //添加商品图片
        request.setCharacterEncoding("UTF-8");
        FileOperation fileOperation = new FileOperation();
        String imgName = fileOperation.SellerBcImgAdd(image);
        if (!imgName.equals("error")) {
            //新增数据库
            ProductImg productImg = new ProductImg();
            productImg.setImage(imgName);
            if (isCover == 1) {
                productImg.setIsCover(1);
            } else {
                productImg.setIsCover(0);
            }
            productImg.setProductId(productid);
            productImgService.insertSelective(productImg);
        } else {
            map.put("status", "no");
            map.put("info", 0);
        }
        return map;
    }

    @RequestMapping("selectDetail")
    public Product selectDetail(Integer productid){
        return productService.selectProductDetail(productid);
    }

    @RequestMapping(value = "/testHtml")
    public String testHtml() {
        return "TestHtml";
    }

}
