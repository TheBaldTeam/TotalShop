package com.dao;

import com.entity.SellerBcImg;

public interface SellerBcImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SellerBcImg record);

    int insertSelective(SellerBcImg record);

    SellerBcImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SellerBcImg record);

    int updateByPrimaryKey(SellerBcImg record);
}