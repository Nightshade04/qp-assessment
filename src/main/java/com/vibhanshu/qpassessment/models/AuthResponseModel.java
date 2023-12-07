package com.vibhanshu.qpassessment.models;

import lombok.Data;

@Data
public class AuthResponseModel {
    private String token;
    private String refreshToken;
}
