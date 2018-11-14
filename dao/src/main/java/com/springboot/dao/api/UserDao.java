package com.springboot.dao.api;

import com.springboot.entity.api.UserEntity;

import java.util.List;

public interface UserDao {

    UserEntity queryObject();

    List<UserEntity> queryList();
}
