package com.dao;

import com.entity.SellerWithProductImg;

public interface SellerWithProductImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SellerWithProductImg record);

    int insertSelective(SellerWithProductImg record);

    SellerWithProductImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SellerWithProductImg record);

    int updateByPrimaryKey(SellerWithProductImg record);
}