package com.ecommerce.passportservice.controller;

import com.ecommerce.passportservice.util.RandomUtil;
import com.ecommerce.passportservice.vo.LoginResult;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PassportController {

    private final Logger logger = LoggerFactory.getLogger(PassportController.class);

    @Autowired
    private EurekaClient eurekaClient;

    @PostMapping(value = "/session", produces = "application/json")
    public LoginResult login(@RequestParam String userName, @RequestParam String password) {

        LoginResult loginResult = new LoginResult();
        loginResult.setUserName(userName);
        loginResult.setSuccess(true);
        loginResult.setSessionToken(RandomUtil.getRandomString(32));

        logger.info(loginResult.toString());

        return loginResult;
    }

}