package com.controller;

import com.alibaba.fastjson.JSON;
import com.dao.FileOperation;
import com.entity.*;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;
/*
 * author:@洪伟
 *
 *
 * */

@RestController
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
    @Autowired
    private ShopService shopService;


    //通过seller查询已上架商品
    @RequestMapping("/selectFromSeller")
    public List<Product> selectFromSelelr(Integer sellerid) {
        return productService.selectFromSeller(sellerid);
    }

    @RequestMapping("/selectHotP")
    public List<Product> selectHotP() {
        List<Product> productList = productService.selectAll();
        return productList;
    }

    @RequestMapping("/selectLevel1P")
    public List<Product> selectLevel1P(Integer classid) {
        List<Shop> shopList = shopService.selectLevel1and2(classid);
        List list = new ArrayList();
        for (Shop shopTemp: shopList) {
            Integer level2Id = shopTemp.getTowLevelName().get(0).getClassId();
            list.add(productService.selectLevel1P(level2Id));
        }
        return list;
    }

    @RequestMapping("/selectLevel2P")
    public List<Product> selectLevel2P(Integer classid) {
        List<ClassWithProduct> classWithProductList = classWithProductService.selectByClassId(classid);
        List<Product> productList = new ArrayList();
        for (ClassWithProduct classWithProduct : classWithProductList) {
            int productid = classWithProduct.getProductId();
            Product product = productService.selectLevel2P(productid);
            if(product.getStatus()!=1){
                productList.add(product);
            }
        }
        return productList;
    }

    //添加商品，做商品表和版本表操作
    @RequestMapping(value = "/insertP")
    public Map insertP(Product product, String versionList, Integer sellerid, Integer cid) {
        Map<String, Object> map = new HashMap<>();
        //若团购价格不为0则设置为团购商品
        if (product.getGroupPrice() == null) {
            product.setIsGroup(0);
        } else if (product.getGroupPrice().compareTo(BigDecimal.ZERO)!=0) {
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
            List<String> versions = JSON.parseArray(versionList, String.class);
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
    public Map selectDetail(Integer productid) {
        return productService.selectProductDetail(productid);
    }

    @RequestMapping("/productDown")
    public Map productDown(Integer productid) {
        Map<String, Object> map = new HashMap<>();
        Product product = productService.selectByPrimaryKey(productid);
        if (product != null) {
            product.setStatus((byte) 0);
            productService.updateByPrimaryKey(product);
            map.put("status", "ok");
            map.put("info", 1);
        } else {
            map.put("status", "no");
            map.put("info", -1);
        }
        return map;
    }

    @RequestMapping("/serchProduct")
    public List<Product> serchProduct(String pname){
        return productService.serchProduct(pname);
    }
}
