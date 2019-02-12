package com.dao;

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

    List<Seller> selectSellerClass(String sellerClass);
}