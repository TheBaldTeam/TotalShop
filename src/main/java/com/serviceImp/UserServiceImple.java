package com.serviceImp;

import com.dao.Md5Util;
import com.dao.UserMapper;
import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImple implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        Md5Util md5Util = new Md5Util();
        record.setPassword(md5Util.md5(record.getPassword()));
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public User login(Map<String, Object> map) {
        Md5Util md5Util = new Md5Util();
        map.put("password", md5Util.md5(map.get("password").toString()));
        return userMapper.login(map);
    }

    @Override
    public List<User> selectUnconfirmed() {
        return userMapper.selectUnconfirmed();
    }

    @Override
    public User selectAddressByUserId(Integer userid) {
        return userMapper.selectAddressByUserId(userid);
    }

    @Override
    public User selectWithSeller(Integer userid) {
        return userMapper.selectWithSeller(userid);
    }

    @Override
    public User checkTel(Long tel) {
        return userMapper.checkTel(tel);
    }

    @Override
    public Integer selectUnconfirmByUserId(Integer userid) {
        User user = userMapper.selectUnconfirmByUserId(userid);
        if(user==null){
            return -1;
        }else{
            return user.getIsApply();
        }
    }

    @Override
    public Integer selectKey(User record) {
        return userMapper.selectKey(record);
    }

}
