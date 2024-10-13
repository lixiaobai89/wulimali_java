package com.example.lxb_website.login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lxb_website.login.entity.SysUser;
import com.example.lxb_website.login.entity.SysUserLoginReq;
import com.example.lxb_website.login.entity.SysUserSaveReq;
import com.example.lxb_website.login.mapper.SysUserMapper;
import com.example.lxb_website.login.service.SysUserService;
import com.example.lxb_website.login.utils.CopyUtils;
import com.example.lxb_website.login.utils.SnowFlake;
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
    public boolean login(SysUserLoginReq req) {
        SysUser user = CopyUtils.copy(req, SysUser.class);
        SysUser userDb = selectByLoginName(req.getLoginName());
        if (user.getPassword().equals(userDb.getPassword())) {
            return true;
        }
        return false;
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
}
