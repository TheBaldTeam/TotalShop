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

    //根据selerid查找商家旗下前三热销商品
    List<Seller> selectSellerTopThree(Integer sellerid);

    //通过sellerClass查找seller
    List<Seller> selectSellerFromSellerClass(String sellerClass);

    //查询所有
    List<Seller> selectAll();
}
