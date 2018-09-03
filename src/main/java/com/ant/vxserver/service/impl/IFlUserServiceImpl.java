package com.ant.vxserver.service.impl;

import com.ant.vxserver.commons.Const;
import com.ant.vxserver.commons.ServerResponse;

import com.ant.vxserver.pojo.FlUser;
import com.ant.vxserver.service.IFlUserService;
import org.springframework.stereotype.Service;

/**
 * Created by wolf
 */
@Service("iFlUserService")
public class IFlUserServiceImpl implements IFlUserService {

//    @Autowired
//    FlUserMapper fluserMapper;


    @Override
    public ServerResponse getByFlUsername(String fl_username) {
        return null;
    }

    @Override
    public ServerResponse register(FlUser user) {
        /*ServerResponse validResponse = this.checkValid(user.getUsername(), Const.USERNAME);
        if(!validResponse.isSuccess()){
            return validResponse;
        }
        validResponse = this.checkValid(user.getEmail(),Const.EMAIL);
        if(!validResponse.isSuccess()){
            return validResponse;
        }*/
        user.setRole(Const.Role.ROLE_CUSTOMER);


       // int resultCount = fluserMapper.insert(user);
//        if(resultCount == 0){
//            return ServerResponse.createByErrorMessage("注册失败");
//        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse getTeamNum(String fl_username) {
        return null;
    }

    @Override
    public ServerResponse getFluserByweichatNum(String weichatname) {
        return null;
    }

    //通过自己的返利账号或者手机号或者微信号 去鉴别 是否已经注册
/*    public ServerResponse<String> checkValid(String str,String type){
        if(org.apache.commons.lang3.StringUtils.isNotBlank(type)){
            //开始校验
            if(Const.WECHATNAME.equals(type)){
                int resultCount = fluserMapper.checkWeChatName(str);
                if(resultCount > 0 ){
                    return ServerResponse.createByErrorMessage("微信用户名已存在");
                }
            }
            if(Const.PHONE.equals(type)){
                int resultCount = fluserMapper.checkPhone(str);
                if(resultCount > 0 ){
                    return ServerResponse.createByErrorMessage("手机号已存在");
                }
            }
            if(Const.QQ.equals(type)){
                int resultCount = fluserMapper.checkQQ(str);
                if(resultCount > 0 ){
                    return ServerResponse.createByErrorMessage("消费者账号已存在");
                }
            }
        }else{
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }*/
}
