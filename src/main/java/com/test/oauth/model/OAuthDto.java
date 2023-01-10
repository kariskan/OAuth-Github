package com.test.oauth.model;

import lombok.Data;

@Data
public class OAuthDto {
    private String code;
    private String client_id;
    private String client_secret;
}
