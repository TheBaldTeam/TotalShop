package com.dao;

import com.entity.ProductCommentFreight;

public interface ProductCommentFreightMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductCommentFreight record);

    int insertSelective(ProductCommentFreight record);

    ProductCommentFreight selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductCommentFreight record);

    int updateByPrimaryKey(ProductCommentFreight record);
}