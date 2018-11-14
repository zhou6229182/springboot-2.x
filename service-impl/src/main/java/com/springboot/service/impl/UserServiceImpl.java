package com.springboot.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.springboot.dao.api.UserDao;
import com.springboot.entity.api.UserEntity;
import com.springboot.iservice.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public UserEntity queryObject() {
        return userDao.queryObject();
    }

    @Override
    @Cacheable(value = "user", key = "'userlist'")
    public List<UserEntity> queryList() {
        PageHelper.startPage(1, 2);
        List<UserEntity> list = userDao.queryList();
        redisTemplate.opsForValue().set("userlist", list);
        return list;
    }

    @Override
    @CacheEvict(value = "user", allEntries = true)
    public void deleteCache() {
    }

    @Override
    @Cacheable(value = "time", key = "'nowtime'")
    public Date nowTime() {
        return new Date();
    }
}
