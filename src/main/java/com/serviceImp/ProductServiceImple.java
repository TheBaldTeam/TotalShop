package com.serviceImp;

import com.dao.*;
import com.entity.Product;
import com.entity.Seller;
import com.entity.SellerAddress;
import com.entity.Version;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImple implements ProductService {

    @Autowired(required = false)
    private ProductMapper productMapper;
    @Autowired(required = false)
    private VersionMapper versionMapper;
    @Autowired(required = false)
    private SellerMapper sellerMapper;
    @Autowired(required = false)
    private SellerWithProductImgMapper sellerWithProductImgMapper;
    @Autowired(required = false)
    private SellerAddressMapper sellerAddressMapper;

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
    public List<Product> selectAll(Integer classkey) {
        return productMapper.selectAll(classkey);
    }

    @Override
    public int selectKey(Product record) {
        return productMapper.selectKey(record);
    }

    @Override
    public Map selectProductDetail(Integer productid) {
        Map map = new HashMap();
        //查询商品详情+所有图片
        List<Product> productList = productMapper.selectProductWithImg(productid);
        map.put("productList", productList);
        //查询商品型号
        List<Version> versionList = versionMapper.selectVersionByPid(productid);
        map.put("productVersion", versionList);
        //查询seller
        Integer sellerid = sellerWithProductImgMapper.selectByPid(productid).get(0).getSellerId();
        Seller seller = sellerMapper.selectByPrimaryKey(sellerid);
        map.put("sellerid", sellerid);
        map.put("sellerTitleName", seller.getTitleName());
        //查询sellerAddress
        List<SellerAddress> addressList = sellerAddressMapper.selectBySellerid(sellerid);
        map.put("addressList", addressList);
        return map;
    }

    @Override
    public Product selectLevel2P(Integer productid) {
        return productMapper.selectLevel2P(productid);
    }

    @Override
    public List<Product> selectFromSeller(Integer sellerid) {
        return productMapper.selectFromSeller(sellerid);
    }


}
