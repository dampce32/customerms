package org.linys.dao;

import org.linys.model.User;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);
}