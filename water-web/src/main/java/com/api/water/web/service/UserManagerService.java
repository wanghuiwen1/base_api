package com.api.water.web.service;

import com.api.core.response.Result;


import java.util.Date;

public interface UserManagerService {

    Result registered(String username,
                      String password,
                      String mobileNumber,
                      Byte gender,
                      String email,
                      String nickname,
                      String avatar,
                      String photoUrl,
                      String idcard,
                      Integer age,
                      Date birthday,String address);
}
