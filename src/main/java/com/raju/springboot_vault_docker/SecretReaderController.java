package com.raju.springboot_vault_docker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
public class SecretReaderController {

    @Value("${app.secret}")
    private String appSecret;

    @GetMapping("/secret")
    public String getSecret() {
        return appSecret;
    }
}

