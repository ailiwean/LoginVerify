package com.ailiwean.loginverify;

import com.ailiwean.lib.LoginChunk;

public class MyLogin implements LoginChunk {


    //验证是否登陆
    @Override
    public boolean verifyLogin() {
        return false;
    }


    //去登陆
    @Override
    public void goLogin() {

    }

    @Override
    public long breakTime() {
        return 1000;
    }

}
