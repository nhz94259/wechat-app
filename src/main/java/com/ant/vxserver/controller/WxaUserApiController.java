/*
package com.ant.vxserver.controller;

import com.ant.vxserver.sdk.api.ApiConfigKit;
import com.ant.vxserver.sdk.api.ApiResult;
import com.ant.vxserver.sdk.cache.IAccessTokenCache;
import com.ant.vxserver.wxaapp.api.WxaUserApi;
import com.ant.vxserver.wxaapp.jfinal.WxaController;
import com.jfinal.aop.Duang;
import com.jfinal.kit.Kv;
import com.jfinal.kit.StrKit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


*/
/**
 * 微信小程序用户api接口
 * @author L.cm
 *//*

@RestController
@RequestMapping("/api/vxuser/")
public class WxaUserApiController extends WxaController {
	// 微信用户接口api
	protected WxaUserApi wxaUserApi = Duang.duang(WxaUserApi.class);
	
	*/
/**
	 * 登陆接口
	 *//*

	@RequestMapping(value = "login_by_weixin.do",method = RequestMethod.POST)
	@ResponseBody
	public void login() {
		String jsCode = getRequest().getParameter("code");//getPara("code");
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
		IAccessTokenCache accessTokenCache = ApiConfigKit.getAccessTokenCache();
		String sessionId = StrKit.getRandomUUID();
		
		accessTokenCache.set("wxa:session:" + sessionId, apiResult.getJson());
		renderJson("sessionId", sessionId);
	}
	
	*/
/**
	 * 服务端解密用户信息接口
	 * 获取unionId
	 *//*

	@RequestMapping(value = "info.do",method = RequestMethod.POST)
	@ResponseBody
	public void info() {
		String signature = getPara("signature");
		String rawData = getPara("rawData");
		
		String encryptedData = getPara("encryptedData");
		String iv = getPara("iv");
		
		// 参数空校验 不做演示
		// 利用 appId 与 accessToken 建立关联，支持多账户
		IAccessTokenCache accessTokenCache = ApiConfigKit.getAccessTokenCache();
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
		ApiResult sessionResult = ApiResult.create(sessionJson);
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
		ApiResult apiResult = wxaUserApi.getUserInfo(sessionKey, encryptedData, iv);
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
*/
