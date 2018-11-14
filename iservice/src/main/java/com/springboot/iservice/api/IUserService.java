package com.springboot.iservice.api;

import com.springboot.entity.api.UserEntity;

import java.util.Date;
import java.util.List;

public interface IUserService {
    UserEntity queryObject();

    List<UserEntity> queryList();

    void deleteCache();

    Date nowTime();
}
