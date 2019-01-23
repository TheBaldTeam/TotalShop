package com.service;

import com.entity.Seller;

public interface SellerService {
    int deleteByPrimaryKey(Integer id);

    int insert(Seller record);

    int insertSelective(Seller record);

    Seller selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Seller record);

    int updateByPrimaryKey(Seller record);

    //selectKey
    int selectKey(Seller record);

    //selectByUserid
    Seller selectByUserid(Integer userid);

    //deleteByUserid
    int deleteByUserid(Integer userid);
}
