package com.ant.vxserver.wxaapp.api;


import com.ant.vxserver.wxaapp.msg.IMsgParser;
import com.ant.vxserver.wxaapp.msg.JsonMsgParser;
import com.ant.vxserver.wxaapp.msg.XmlMsgParser;

/**
 * 小程序配置工具
 * @author L.cm
 *
 */
public class WxaConfigKit {
    private static WxaConfig wxaConfig;
    /**
     * 小程序消息解析
     */
    private static IMsgParser msgParser = new XmlMsgParser();
    /**
     * 获取小程序消息解析器
     * @return {IMsgParser}
     */
    public static IMsgParser getMsgParser() {
        return msgParser;
    }
    /**
     * 设置小程序消息解析器
     */
    public static void useJsonMsgParser() {
        WxaConfigKit.msgParser = new JsonMsgParser();
    }

    // 开发模式将输出消息交互 xml、json 到控制台
    private static boolean devMode = false;

    public static void setDevMode(boolean devMode) {
        WxaConfigKit.devMode = devMode;
    }

    public static boolean isDevMode() {
        return devMode;
    }

    public static void setWxaConfig(WxaConfig wxaConfig) {
        WxaConfigKit.wxaConfig = wxaConfig;
    }

    public static WxaConfig getWxaConfig() {
        return WxaConfigKit.wxaConfig;
    }
}