package com.example.community.dto;

import lombok.Data;

/*
created by angelee 20230707
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;


}
