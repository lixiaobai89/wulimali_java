package com.example.lxb_website.login.controller;

import com.example.lxb_website.login.entity.CommonResp;
import com.example.lxb_website.login.entity.SysUserLoginReq;
import com.example.lxb_website.login.entity.SysUserSaveReq;
import com.example.lxb_website.login.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys-user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("register")
    public CommonResp register(@RequestBody SysUserSaveReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp();
        boolean success = sysUserService.register(req);
        resp.setSuccess(success);
        return resp;
    }

    @PostMapping("login")
    public CommonResp login(@RequestBody SysUserLoginReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        return sysUserService.login(req);
    }

    /**
     * token验证
     *
     * @param token 令牌
     * @return Result
     */
    @PostMapping(value = "/auth/{token}")
    @ResponseBody
    public CommonResp auth(@PathVariable String token) {
        return sysUserService.auth(token);
    }
}
