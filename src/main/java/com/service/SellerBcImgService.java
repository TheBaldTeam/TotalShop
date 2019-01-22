package com.service;

import com.entity.SellerBcImg;

public interface SellerBcImgService {
    int deleteByPrimaryKey(Integer id);

    int insert(SellerBcImg record);

    int insertSelective(SellerBcImg record);

    SellerBcImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SellerBcImg record);

    int updateByPrimaryKey(SellerBcImg record);


}
