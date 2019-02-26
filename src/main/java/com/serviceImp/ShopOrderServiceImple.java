package com.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ShopOrderMapper;
import com.entity.ShopOrder;
import com.service.ShopOrderService;

@Service
public class ShopOrderServiceImple implements ShopOrderService{
	@Autowired(required = false)
	private ShopOrderMapper shopOrderMapper;
	
	@Override
	public Integer vipPay(ShopOrder shopOrder) {
		// TODO Auto-generated method stub
		if(shopOrderMapper.insertSelective(shopOrder)>0) {
			return 1;
		}
		return null;
	}

	@Override
	public Integer setPayStatus(String orderSn, Integer status) {
		ShopOrder so = shopOrderMapper.selectByOrderSn(orderSn);
		so.setOrderStatus(status);
		return shopOrderMapper.updateByPrimaryKeySelective(so);
	}

}
