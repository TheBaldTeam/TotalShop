package com.controller;

import com.entity.Product;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * author:@洪伟
 *
 *
 *
 * */

@Controller
@RequestMapping("/product")
public class ProductShowController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/getAll")
    @ResponseBody
    public List<Product> getAll(){
        List<Product> productLsit =  productService.selectAll();
        return productLsit;
    }

    @RequestMapping(value = "/insertP", method = RequestMethod.GET)
    @ResponseBody
    public Map insertP(Product product){
        int isOk = productService.insertSelective(product);
        Map<String, Object> map = new HashMap<>();
        if (isOk > 0){
            map.put("status", "ok");
            map.put("info", 1);
        }else {
            map.put("status", "no");
            map.put("info", -1);
        }
        return map;
    }

    @RequestMapping(value = "/testHtml")
    public String testHtml(){
        return "TestHtml";
    }

    @RequestMapping(value = "/addFile", method = RequestMethod.POST)
    @ResponseBody
    public String addFile(HttpServletRequest request){

        return "";
    }


}
