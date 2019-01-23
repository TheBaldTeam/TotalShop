package com.entity;

import java.util.List;

public class Seller {
    private Integer id;

    private Long tel;

    private String titleName;

    private Integer userId;

    private List<SellerAddress> sellerAddressList;

    private SellerBcImg sellerBcImg;

    public SellerBcImg getSellerBcImg() {
        return sellerBcImg;
    }

    public void setSellerBcImg(SellerBcImg sellerBcImg) {
        this.sellerBcImg = sellerBcImg;
    }

    public List<SellerAddress> getSellerAddressList() {
        return sellerAddressList;
    }

    public void setSellerAddressList(List<SellerAddress> sellerAddressList) {
        this.sellerAddressList = sellerAddressList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName == null ? null : titleName.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}