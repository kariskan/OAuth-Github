package com.test.oauth.controller;

import com.test.oauth.model.OAuthDto;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@PropertySource(value = "application.properties")
public class OAuthController {

    @Value("${client_id}")
    private String client_id;

    @Value("${client_secret}")
    private String client_secret;

    @GetMapping("/afterlogin")
    public ResponseEntity<String> afterLogin(@RequestParam String code) {
        if (Objects.isNull(code)) {
            throw new IllegalStateException("Github 로그인이 실패했습니다.");
        }
        RestTemplate restTemplate = new RestTemplate();
        OAuthDto oauthDto = new OAuthDto();
        oauthDto.setCode(code);
        oauthDto.setClient_id(client_id);
        oauthDto.setClient_secret(client_secret);
        String accessToken = restTemplate.postForObject("https://github.com/login/oauth/access_token", oauthDto, String.class);
        return ResponseEntity.ok(accessToken);
    }
}
