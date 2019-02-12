package com.service;

import com.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    //查询所有（包括商品封面）
    List<Product> selectAll(Integer classkey);

    //selectKey
    int selectKey(Product record);

    //查询商品详情
    Map selectProductDetail(Integer productid);

    //查询二级目录下的商品和商品封面
    Product selectLevel2P(Integer productid);
}
