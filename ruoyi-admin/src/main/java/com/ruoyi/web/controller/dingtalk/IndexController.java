package com.ruoyi.web.controller.dingtalk;

import com.dingtalk.api.request.OapiReportListRequest;
import com.dingtalk.api.response.OapiReportListResponse;
import com.ruoyi.dingtalk.config.URLConstant;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.taobao.api.ApiException;
import com.ruoyi.dingtalk.util.AccessTokenUtil;
import com.ruoyi.dingtalk.util.ServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 企业内部E应用Quick-Start示例代码 实现了最简单的免密登录（免登）功能
 */
@RestController
@RequestMapping("/dingtalk")
public class IndexController {
    private static final Logger bizLogger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ISysUserService userService;

    /**
     * 欢迎页面,通过url访问，判断后端服务是否启动
     */
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }

    /**
     * 钉钉用户登录，显示当前登录用户的userId和名称
     *
     * @param requestAuthCode 免登临时code
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResult login(@RequestParam(value = "authCode") String requestAuthCode) {
        //获取accessToken,注意正是代码要有异常流处理
        String accessToken = AccessTokenUtil.getToken();
        bizLogger.info(accessToken);
        //获取用户信息
        DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_GET_USER_INFO);
        OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
        request.setCode(requestAuthCode);
        request.setHttpMethod("GET");

        OapiUserGetuserinfoResponse response;
        try {
            response = client.execute(request, accessToken);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
        //3.查询得到当前用户的userId
        // 获得到userId之后应用应该处理应用自身的登录会话管理（session）,避免后续的业务交互（前端到应用服务端）每次都要重新获取用户身份，提升用户体验
        String userId = response.getUserid();

        String userMobile = getUserMobile(accessToken, userId);
        System.out.println(userMobile);
        SysUser sysUser = userService.selectUserByPhoneNumber(userMobile);

        //返回结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("userMobile", userMobile);
        resultMap.put("loginName",sysUser.getLoginName());

        ServiceResult serviceResult = ServiceResult.success(resultMap);
        return serviceResult;
    }

    /**
     * 获取用户手机号
     *
     * @param accessToken
     * @param userId
     * @return
     */
    private String getUserMobile(String accessToken, String userId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_USER_GET);
            OapiUserGetRequest request = new OapiUserGetRequest();
            request.setUserid(userId);
            request.setHttpMethod("GET");
            OapiUserGetResponse response = client.execute(request, accessToken);
            return response.getMobile();
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     *获取某日志数据
     *
     * @param accessToken
     * @param userId
     * @return
     */
    private String getReportList(String accessToken, String userId){
        try{

            DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_REPORT_GET);
            OapiReportListRequest request = new OapiReportListRequest();
            request.setUserid(userId);
            request.setTemplateName("本单位业务发展日报");
            request.setStartTime(System.currentTimeMillis()- TimeUnit.DAYS.toMillis(1));
            request.setEndTime(System.currentTimeMillis());
            request.setCursor(0L);//初始传入0，后续从上一次的返回值中获取
            request.setSize(10L);//max 20
            OapiReportListResponse response = client.execute(request, accessToken);
            return response.getMsg();
        }catch (ApiException e){
            e.printStackTrace();
            return  null;
        }
    }

}


