package com.vibhanshu.qpassessment.services;

import com.vibhanshu.qpassessment.entities.User;
import com.vibhanshu.qpassessment.models.AuthRequestModel;
import com.vibhanshu.qpassessment.models.AuthResponseModel;
import com.vibhanshu.qpassessment.models.TokenRefreshModel;

public interface AuthService {
    User signUp(AuthRequestModel model);

    AuthResponseModel signIn(AuthRequestModel model);

    AuthResponseModel refreshToken(TokenRefreshModel tokenRefreshModel);
}
