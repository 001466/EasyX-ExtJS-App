/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.easy.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.easy.mybatisplus.base.BaseServiceImpl;
import org.easy.user.entity.User;
import org.easy.user.mapper.UserMapper;
import org.easy.user.service.IUserService;
import org.easy.user.vo.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 *  服务实现类
 *
 * @author EasyX
 * @since 2020-09-03
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

	@Override
	public IPage<UserVO> selectUserPage(IPage<UserVO> page, UserVO user) {
		return page.setRecords(baseMapper.selectUserPage(page, user));
	}

	@Override
	public User valid(String username, String password) {
		QueryWrapper<User> wrapper=new QueryWrapper<>();
		wrapper.eq("account",username).eq("password",password);
		List<User> list= baseMapper.selectList(wrapper);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		else{
			return list.get(0);
		}
	}

	@Override
	public Integer resetPassword(String username, String oPassword, String nPassword) {
		User user=valid(username,oPassword);
		return baseMapper.updateById(user);
	}

}
