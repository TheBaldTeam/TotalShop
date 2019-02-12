package com.controller;

import com.alibaba.fastjson.JSON;
import com.dao.FileOperation;
import com.entity.*;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;
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
    @Autowired
    private ClassWithProductService classWithProductService;



    @RequestMapping("/selectLevel1P")
    @ResponseBody
    public List<Product> selectLevel1P(Integer classkey){
        List<Product> productList = productService.selectAll(classkey);
        return productList;
    }

    @RequestMapping("/selectLevel2P")
    @ResponseBody
    public List<Product> selectLevel2P(Integer classid){
        List<ClassWithProduct> classWithProductList = classWithProductService.selectByClassId(classid);
        List<Product> productList = new ArrayList();
        for (ClassWithProduct classWithProduct: classWithProductList) {
            int productid = classWithProduct.getProductId();
            Product product = productService.selectLevel2P(productid);
            productList.add(product);
        }
        return productList;
    }

    //添加商品，做商品表和版本表操作
    @RequestMapping(value = "/insertP")
    @ResponseBody
    public Map insertP(Product product, String versionList, Integer sellerid, Integer cid) {
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
            //插入关联表
            ClassWithProduct classWithProduct = new ClassWithProduct();
            classWithProduct.setLevel2ClassId(cid);
            classWithProduct.setProductId(product.getId());
            classWithProductService.insertSelective(classWithProduct);
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

    @RequestMapping("/selectDetail")
    @ResponseBody
    public Map selectDetail(Integer productid){
        return productService.selectProductDetail(productid);
    }

}
