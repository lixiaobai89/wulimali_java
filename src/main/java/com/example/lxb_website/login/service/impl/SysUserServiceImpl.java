package com.example.lxb_website.login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lxb_website.login.entity.CommonResp;
import com.example.lxb_website.login.entity.SysUser;
import com.example.lxb_website.login.entity.SysUserLoginReq;
import com.example.lxb_website.login.entity.SysUserSaveReq;
import com.example.lxb_website.login.mapper.SysUserMapper;
import com.example.lxb_website.login.service.SysUserService;
import com.example.lxb_website.login.utils.CopyUtils;
import com.example.lxb_website.login.utils.JwtUtil;
import com.example.lxb_website.login.utils.SnowFlake;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Override
    public boolean register(SysUserSaveReq req) {
        SysUser user = CopyUtils.copy(req, SysUser.class);
        SysUser userDb = selectByLoginName(req.getLoginName());
        if (ObjectUtils.isEmpty(userDb)) {
            user.setId(snowFlake.nextId());
            sysUserMapper.insert(user);
            return true;
        }
        return false;
    }

    @Override
    public CommonResp login(SysUserLoginReq req) {
        SysUser user = CopyUtils.copy(req, SysUser.class);
        SysUser userDb = selectByLoginName(req.getLoginName());
        CommonResp resp = new CommonResp();
        if (user.getPassword().equals(userDb.getPassword())) {
            //生成token
            String token = JwtUtil.toToken(req.getLoginName());
            resp.setSuccess(true);
            resp.setContent(token);
            return resp;
        }
         return resp;
    }

    public SysUser selectByLoginName(String loginName) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUser::getLoginName, loginName);
        List<SysUser> users = sysUserMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        } else {
            return users.get(0);
        }
    }

    /**
     * 令牌验证
     * @param token 令牌
     * @return Result
     */
    @Override
    public CommonResp auth(String token) {
        CommonResp resp = new CommonResp();
        Claims claims = JwtUtil.fromToken(token);
        if (claims == null) {
            resp.setSuccess(false);
            return resp;
        }
        resp.setSuccess(true);
        //生成token
        String refToken = JwtUtil.toToken((String) claims.get(JwtUtil.ACC_NAME));
        resp.setContent(refToken);
        return resp;
    }
}
