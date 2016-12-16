package com.ecommerce.passportservice.controller;

import com.ecommerce.eurekaserver.vo.LoginRequest;
import com.ecommerce.eurekaserver.vo.LoginResult;
import com.ecommerce.passportservice.util.RandomUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PassportController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/session" ,method = RequestMethod.POST)
    public LoginResult login(@RequestBody LoginRequest loginRequest) {
        ServiceInstance instance = client.getLocalServiceInstance();
        LoginResult loginResult = new LoginResult();
        loginResult.setUserName(loginRequest.getUserName());
        loginResult.setSuccess(true);
        loginResult.setSessionToken(RandomUtil.getRandomString(32));

        logger.info("loginResult:" + loginResult.getSuccess()
                + ", userName:" + loginResult.getUserName()
                + ", sessionToken:" + loginResult.getSessionToken()
                + ", return from ["
                + instance.getHost() + ":"
                + instance.getPort()
                + ",serviceId:"
                + instance.getServiceId()
                + "]");

        return loginResult;
    }

}