package com.ant.vxserver.dao;

import com.ant.vxserver.pojo.FlUser;
import java.util.List;

public interface FlUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FlUser record);

    FlUser selectByPrimaryKey(Integer id);

    List<FlUser> selectAll();

    int updateByPrimaryKey(FlUser record);
}