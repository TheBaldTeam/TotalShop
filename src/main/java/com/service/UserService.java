package com.service;

import com.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User login(Map<String, Object> map);

    //查询已提交商家入驻申请但未确认的用户
    List<User> selectUnconfirmed();

    //根据userid关联查询address
    User selectAddressByUserId(Integer userid);

    //查询isSeller为1的User信息+商铺名字
    User selectWithSeller(Integer userid);

    //注册验证手机号码
    User checkTel(Long tel);

    //通过userid查询是否为提交申请用户
    Integer selectUnconfirmByUserId(Integer userid);
}
