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

    List<Product> selectAll(Integer classkey);

    int selectKey(Product record);

    List<Product> selectProductWithImg(Integer productid);

    Product selectLevel2P(Integer productid);

    //查询商户内商品
    List<Product> selectFromSeller(Integer sellerid);
}