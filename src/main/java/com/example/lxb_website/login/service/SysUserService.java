package com.example.lxb_website.login.service;

import com.example.lxb_website.login.entity.CommonResp;
import com.example.lxb_website.login.entity.SysUserLoginReq;
import com.example.lxb_website.login.entity.SysUserSaveReq;

public interface SysUserService {

    boolean register(SysUserSaveReq req);

    CommonResp login(SysUserLoginReq req);

    CommonResp auth(String token);
}
