package com.serviceImp;

import com.dao.SellerBcImgMapper;
import com.entity.SellerBcImg;
import com.service.SellerBcImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerBcImgServiceImple implements SellerBcImgService {

    @Autowired(required = false)
    private SellerBcImgMapper sellerBcImgMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sellerBcImgMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SellerBcImg record) {
        return sellerBcImgMapper.insert(record);
    }

    @Override
    public int insertSelective(SellerBcImg record) {
        return sellerBcImgMapper.insertSelective(record);
    }

    @Override
    public SellerBcImg selectByPrimaryKey(Integer id) {
        return sellerBcImgMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SellerBcImg record) {
        return sellerBcImgMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SellerBcImg record) {
        return sellerBcImgMapper.updateByPrimaryKey(record);
    }


}
