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
package org.easy.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.easy.mybatisplus.base.BaseService;
import org.easy.user.entity.User;
import org.easy.user.vo.UserVO;

/**
 *  服务类
 *
 * @author EasyX
 * @since 2020-09-03
 */
public interface IUserService extends BaseService<User> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param user
	 * @return
	 */
	IPage<UserVO> selectUserPage(IPage<UserVO> page, UserVO user);

	User valid(String username,String password);
	Integer resetPassword(String username,String oPassword,String nPassword);

}
