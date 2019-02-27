package com.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ShopOrderMapper;
import com.dao.UserMapper;
import com.entity.ShopOrder;
import com.entity.User;
import com.service.ShopOrderService;

@Service
public class ShopOrderServiceImple implements ShopOrderService{
	@Autowired(required = false)
	private ShopOrderMapper shopOrderMapper;
	@Autowired(required = false)
	private UserMapper userMapper;
	
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
		User user = userMapper.selectByPrimaryKey(so.getuserId());
		user.setIsVip(1);
		Integer code = shopOrderMapper.updateByPrimaryKeySelective(so);
		Integer code1 = userMapper.updateByPrimaryKeySelective(user);
		if(code==1 && code==1) {
			return 1;
		}
		return 0;
	}

}
