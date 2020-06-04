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
     * 发起钉钉待办
     */
    public static final String URL_WORK_RECORD__ADD = "https://oapi.dingtalk.com/topapi/workrecord/add";

    /**
     * 更新钉钉待办
     */
    public static final String URL_WORK_RECORD__UPDATE = "https://oapi.dingtalk.com/topapi/workrecord/update";
}
