package com.service;

import com.entity.Seller;

import java.util.List;

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

    //查询未审批详情
    Seller selectUnconfirmDetial(Integer sellerid);

    //根据selerClass查询seller
    List<Seller> selectSellerClass(String sellerClass);
}
