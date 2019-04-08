package com.panda.security.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

//spring security强制要求提供密码编码器
//自定义密码编码器：以明文形式存密码。
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
