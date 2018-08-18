/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.ant.vxserver.wxaapp.api;

import com.ant.vxserver.sdk.api.ApiResult;
import com.ant.vxserver.sdk.api.TemplateMsgApi;
import com.ant.vxserver.sdk.utils.HttpUtils;

/**
 * 微信小程序模版消息
 * @author L.cm
 *
 */
public class WxaTemplateApi {
    private static String sendApiUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=";

    /**
     * 发送模板消息
     * @param jsonStr 模版json
     * @return {ApiResult}
     */
    public ApiResult send(String jsonStr) {
        String jsonResult = HttpUtils.post(sendApiUrl + com.ant.vxserver.wxaapp.api.WxaAccessTokenApi.getAccessTokenStr(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 发送模板消息
     * @param template 模版对象
     * @return {ApiResult}
     */
    public ApiResult send(com.ant.vxserver.wxaapp.api.WxaTemplate template) {
        return this.send(template.build());
    }
    
}
