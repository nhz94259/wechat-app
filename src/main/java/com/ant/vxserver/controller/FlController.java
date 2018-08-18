package com.ant.vxserver.controller;

import com.alibaba.fastjson.JSONObject;

import com.ant.vxserver.commons.ServerResponse;
import com.ant.vxserver.pojo.WeichatCode;
import com.ant.vxserver.sdk.api.ApiResult;
import com.ant.vxserver.service.IFlUserService;
import com.ant.vxserver.utils.ApiBaseAction;
import com.ant.vxserver.utils.wechat.ApiUserUtils;
import com.jfinal.aop.Duang;
import com.jfinal.kit.Kv;
import com.jfinal.kit.StrKit;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by wolf   2018/8/15
 */
@RestController
@RequestMapping("api/user/")
public class FlController extends ApiBaseAction {

protected com.ant.vxserver.wxaapp.api.WxaUserApi wxaUserApi = Duang.duang(  com.ant.vxserver.wxaapp.api.WxaUserApi.class);

    @Autowired
    protected RestTemplateBuilder restTemplate  ;

    @Autowired
    private IFlUserService iFlUserService;


     private static Logger logger = Logger.getLogger(FlController.class);

    //微信授权认证方法
    @RequestMapping(value = "login_by_weixin.do",method = RequestMethod.POST)
    public     ServerResponse<WeichatCode>  openId(){ // 小程序端获取的CODE
        WeichatCode result  = new WeichatCode();

        String code = getPara("code");

         logger.info("code:"+code);
        String responseData= "";
        try {
            boolean check = (org.apache.commons.lang3.StringUtils.isEmpty(code)) ? true : false;
            if (check)
            {
                throw new Exception("参数异常");
            }
            String requestUrl = ApiUserUtils.getWebAccess(code);
            responseData= restTemplate().getForObject(requestUrl, String.class);
        } catch (Exception e) {
            result.setRemark(e.getMessage());
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage(result.getRemark());
        }
        if ( org.apache.commons.lang3.StringUtils.isBlank(JSONObject.parseObject(responseData).getString("openid") ) ){
            System.out.println(JSONObject.parseObject(responseData).getString("errcode") );
            System.out.println(JSONObject.parseObject(responseData).getString("errmsg"));
            result.setCode(JSONObject.parseObject(responseData).getString("errcode") );
            result.setRemark(JSONObject.parseObject(responseData).getString("errmsg"));
            return ServerResponse.createByError(result);
        }else{
            result.setOpenId(JSONObject.parseObject(responseData).getString("openid"));
            result.setSessionKey(JSONObject.parseObject(responseData).getString("session_key"));
            result.setCode("0");

        }
        logger.info("weichat-info:"+ responseData );
        return ServerResponse.createBySuccess(result);
    }
    @RequestMapping(value = "login_by_weixin1.do",method = RequestMethod.POST)
    @ResponseBody
    public void login() {
        String jsCode = getPara("code");
        if (StrKit.isBlank(jsCode)) {
            Kv data = Kv.by("errcode", 500)
                    .set("errmsg", "code is blank");
            renderJson(data);
            return;
        }
        // 获取SessionKey
         ApiResult apiResult = wxaUserApi.getSessionKey(jsCode);
        // 返回{"session_key":"nzoqhc3OnwHzeTxJs+inbQ==","expires_in":2592000,"openid":"oVBkZ0aYgDMDIywRdgPW8-joxXc4"}
        if (!apiResult.isSucceed()) {
            renderJson(apiResult.getJson());
            return;
        }
        // 利用 appId 与 accessToken 建立关联，支持多账户
        com.ant.vxserver.sdk.cache.IAccessTokenCache accessTokenCache = com.ant.vxserver.sdk.api.ApiConfigKit.getAccessTokenCache();
        String sessionId = StrKit.getRandomUUID();

        accessTokenCache.set("wxa:session:" + sessionId, apiResult.getJson());
        renderJson("sessionId", sessionId);
    }

    /**
     * 服务端解密用户信息接口
     * 获取unionId
     */
    public void info() {
        String signature = getPara("signature");
        String rawData = getPara("rawData");

        String encryptedData = getPara("encryptedData");
        String iv = getPara("iv");

        // 参数空校验 不做演示
        // 利用 appId 与 accessToken 建立关联，支持多账户
        com.ant.vxserver.sdk.cache.IAccessTokenCache accessTokenCache = com.ant.vxserver.sdk.api.ApiConfigKit.getAccessTokenCache();
        String sessionId = getHeader("wxa-sessionid");
        if (StrKit.isBlank(sessionId)) {
            Kv data = Kv.by("errcode", 500)
                    .set("errmsg", "wxa_session Header is blank");
            renderJson(data);
            return;
        }
        String sessionJson = accessTokenCache.get("wxa:session:" + sessionId);
        if (StrKit.isBlank(sessionJson)) {
            Kv data = Kv.by("errcode", 500)
                    .set("errmsg", "wxa_session sessionJson is blank");
            renderJson(data);
            return;
        }
     ApiResult sessionResult = com.ant.vxserver.sdk.api.ApiResult.create(sessionJson);
        // 获取sessionKey
        String sessionKey = sessionResult.get("session_key");
        if (StrKit.isBlank(sessionKey)) {
            Kv data = Kv.by("errcode", 500)
                    .set("errmsg", "sessionKey is blank");
            renderJson(data);
            return;
        }
        // 用户信息校验
        boolean check = wxaUserApi.checkUserInfo(sessionKey, rawData, signature);
        if (!check) {
            Kv data = Kv.by("errcode", 500)
                    .set("errmsg", "UserInfo check fail");
            renderJson(data);
            return;
        }
        // 服务端解密用户信息
        com.ant.vxserver.sdk.api.ApiResult apiResult = wxaUserApi.getUserInfo(sessionKey, encryptedData, iv);
        if (!apiResult.isSucceed()) {
            renderJson(apiResult.getJson());
            return;
        }
        // 如果开发者拥有多个移动应用、网站应用、和公众帐号（包括小程序），可通过unionid来区分用户的唯一性
        // 同一用户，对同一个微信开放平台下的不同应用，unionid是相同的。
        String unionId = apiResult.get("unionId");
        renderJson("{}");
    }

}
