package com.example.lxb_website.login.service;

import com.example.lxb_website.login.entity.SysUserLoginReq;
import com.example.lxb_website.login.entity.SysUserSaveReq;

public interface SysUserService {
    boolean register(SysUserSaveReq req);

    boolean login(SysUserLoginReq req);
}
