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
package org.easy.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.easy.user.entity.User;

/**
 * 视图实体类
 *
 * @author EasyX
 * @since 2020-09-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserVO对象", description = "UserVO对象")
public class UserVO extends User {
	private static final long serialVersionUID = 1L;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return access_token;
	}

	public void setToken(String token) {
		this.access_token = token;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public UserVO(String username, String token) {
		this.username = username;
		this.access_token = token;
	}

	@ApiModelProperty(value = "登陆帐号",required=true,position=0,hidden=false)
	String username;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@ApiModelProperty(value = "登陆凭证",required=true,position=0,hidden=false)
	String access_token;
}
