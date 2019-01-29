package com.service;

import com.entity.Product;

import java.util.List;

public interface ProductService {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    //查询所有（包括商品封面）
    List<Product> selectAll();

    //selectKey
    int selectKey(Product record);

    //查询商品详情
    Product selectProductDetail(Integer productid);
}
