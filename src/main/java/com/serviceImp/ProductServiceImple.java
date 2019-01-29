package com.serviceImp;

import com.dao.ProductMapper;
import com.entity.Product;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImple implements ProductService
{

    @Autowired(required = false)
    private ProductMapper productMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Product record) {
        return productMapper.insert(record);
    }

    @Override
    public int insertSelective(Product record) {
        return productMapper.insertSelective(record);
    }

    @Override
    public Product selectByPrimaryKey(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Product record) {
        return productMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Product record) {
        return productMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Product> selectAll() {
        return productMapper.selectAll();
    }

    @Override
    public int selectKey(Product record) {
        return productMapper.selectKey(record);
    }

    @Override
    public Product selectProductDetail(Integer productid) {
        return productMapper.selectProductDetail(productid);
    }


}
