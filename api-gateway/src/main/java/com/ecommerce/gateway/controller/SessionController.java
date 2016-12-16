package com.ecommerce.gateway.controller;

import com.ecommerce.gateway.vo.LoginRequest;
import com.ecommerce.gateway.vo.LoginResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/gw/session", method = RequestMethod.POST)
    public LoginResult login(@RequestParam String userName, @RequestParam String password) {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserName(userName);
        loginRequest.setPassword(password);

        MultiValueMap<String, Object> headers = new LinkedMultiValueMap<String, Object>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        HttpEntity request = new HttpEntity(loginRequest, headers);

        return restTemplate.postForObject("http://PASSPORT-SERVICE/session", request, LoginResult.class);
    }
}