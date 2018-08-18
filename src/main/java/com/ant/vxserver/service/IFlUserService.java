package com.ant.vxserver.service;

import com.ant.vxserver.commons.ServerResponse;
import com.ant.vxserver.pojo.FlUser;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by wolf
 */
public interface IFlUserService {

    //Main Description：为微信公众账号 创建关于返利联盟的用户类 主要实现功能如下：

    //Description：在微信公众号上实现注册自己的信息
    ServerResponse register(FlUser fl_user);

    //Description：通过返利用户的账号获取leader群账号以及微信账号  fl_username是返利的会员会
    ServerResponse getByFlUsername(String fl_username);

    //Description：查看自己的团队人数
    ServerResponse getTeamNum(String fl_username);

    //Description：通过微信查询成员返利网账号
    ServerResponse getFluserByweichatNum(String weichatname);
    
}
