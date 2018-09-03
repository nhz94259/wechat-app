package com.ant.vxserver.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by wolf   2018/8/16
 */
public class ApiBaseAction  {

    protected Logger logger = Logger.getLogger(getClass());

    /**
     * 得到request对象
     */
    @Autowired
    protected HttpServletRequest request;
    /**
     * 得到response对象
     */
    @Autowired
    protected HttpServletResponse response;

    /**
     * 得到URL访问
     */
    @Autowired
    protected RestTemplateBuilder restTemplate  ;

    @Bean
    public RestTemplate restTemplate() {
        return restTemplate.build();
    }

    public  String getPara(String  para){
        return      request.getParameter(para);
    }

}
