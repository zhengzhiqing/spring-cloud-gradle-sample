package com.ecommerce.gateway.controller;

import com.ecommerce.gateway.vo.LoginResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zhengzhiqing on 16/12/15.
 */
@RestController
public class SessionController {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/api/session", produces = "application/json", method = RequestMethod.POST)
    public LoginResult login(String userName, String password) {

        MultiValueMap<String, String> reqMap= new LinkedMultiValueMap<String, String>();
        reqMap.add("userName", userName);
        reqMap.add("password", password);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Accept", "application/json");

        HttpEntity<MultiValueMap<String,String>> httpEntity = new HttpEntity<MultiValueMap<String,String>>(reqMap, headers);

        return restTemplate.postForObject("http://PASSPORT-SERVICE/session", httpEntity, LoginResult.class);
    }
}