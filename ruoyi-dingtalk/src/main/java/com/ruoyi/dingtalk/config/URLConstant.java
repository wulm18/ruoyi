package com.ruoyi.dingtalk.config;

public class URLConstant {
    /**
     * 钉钉网关gettoken地址
     */
    public static final String URL_GET_TOKKEN = "https://oapi.dingtalk.com/gettoken";

    /**
     *获取用户在企业内userId的接口URL
     */
    public static final String URL_GET_USER_INFO = "https://oapi.dingtalk.com/user/getuserinfo";

    /**
     *获取用户姓名的接口url
     */
    public static final String URL_USER_GET = "https://oapi.dingtalk.com/user/get";

    /**
     * 获取用户某个日志数据
     */
    public static final String URL_REPORT_GET = "https://oapi.dingtalk.com/topapi/report/list";
    /**
     * 发送群消息
     */
    public static final String URL_CHAT_SEND = "https://oapi.dingtalk.com/chat/send";

}
