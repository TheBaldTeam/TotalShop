package com.serviceImp;

import com.dao.SellerMapper;
import com.entity.Seller;
import com.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImple implements SellerService {

    @Autowired(required = false)
    private SellerMapper sellerMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sellerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Seller record) {
        return sellerMapper.insert(record);
    }

    @Override
    public int insertSelective(Seller record) {
        return sellerMapper.insertSelective(record);
    }

    @Override
    public Seller selectByPrimaryKey(Integer id) {
        return sellerMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Seller record) {
        return sellerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Seller record) {
        return sellerMapper.updateByPrimaryKey(record);
    }

    @Override
    public int selectKey(Seller record) {
        return sellerMapper.selectKey(record);
    }

    @Override
    public Seller selectByUserid(Integer userid) {
        return sellerMapper.selectByUserid(userid);
    }

    @Override
    public int deleteByUserid(Integer userid) {
        return sellerMapper.deleteByUserid(userid);
    }
}
