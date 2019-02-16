package com.dao;

import com.entity.Product;
import com.entity.Seller;

import java.util.List;

public interface SellerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Seller record);

    int insertSelective(Seller record);

    Seller selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Seller record);

    int updateByPrimaryKey(Seller record);

    int selectKey(Seller record);

    Seller selectByUserid(Integer userid);

    int deleteByUserid(Integer userid);

    Seller selectUnconfirmDetial(Integer sellerid);

    //查找商家热销商品前三
    List<Seller> selectSellerTopThree(Integer sellerid);

    //查找商家热销商品前九
    List<Seller> selectSellerTopNine(Integer sellerid);

    //通过sellerClass查找seller
    List<Seller> selectSellerFromSellerClass(String sellerClass);

    //查询所有
    List<Seller> selectAll();
}