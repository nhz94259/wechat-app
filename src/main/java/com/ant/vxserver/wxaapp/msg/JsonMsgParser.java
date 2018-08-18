/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.ant.vxserver.wxaapp.msg;


import com.ant.vxserver.sdk.utils.JsonUtils;
import com.ant.vxserver.wxaapp.msg.bean.WxaMsg;

public class JsonMsgParser extends MsgModelParser implements IMsgParser {

	@Override
	public WxaMsg parser(String msgStr) {
		MsgModel msgModel = JsonUtils.parse(msgStr, MsgModel.class);
		return parserMsg(msgModel);
	}

}
