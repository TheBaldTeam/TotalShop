package com.dao;

import com.entity.ShopOrder;

public interface ShopOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShopOrder record);

    int insertSelective(ShopOrder record);

    ShopOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShopOrder record);

    int updateByPrimaryKey(ShopOrder record);
}