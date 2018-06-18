package com.ecommerce.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhengzhiqing on 16/12/17.
 */
@RefreshScope
@RestController
public class ConfigController {

    @Value("${message: hello world default}")
    private String message;

    @GetMapping("/message")
    String getMessage() {
        return this.message;
    }
}
