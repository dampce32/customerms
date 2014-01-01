package org.linys.dao.system;

import org.linys.model.system.RoleRight;

public interface RoleRightMapper {
    int deleteByPrimaryKey(Integer roleRightId);

    int insert(RoleRight record);

    int insertSelective(RoleRight record);

    int updateByPrimaryKeySelective(RoleRight record);

    int updateByPrimaryKey(RoleRight record);
}