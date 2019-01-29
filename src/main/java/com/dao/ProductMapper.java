package com.dao;

import com.entity.Product;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    //selectKey
    int selectKey(Product record);

    //查询所有商品（商品封面）
    List<Product> selectAll();

    //查询商品详情
    Product selectProductDetail(Integer productid);
}