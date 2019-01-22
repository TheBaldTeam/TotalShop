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
        record.setPassword(md5Util.md5Encoder(record.getPassword()));
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
        map.put("password", md5Util.md5Encoder(map.get("password").toString()));
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

}
