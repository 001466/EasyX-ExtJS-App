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
package org.easy.word.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.easy.word.dto.WordDTO;
import org.easy.word.entity.Word;
import org.easy.word.vo.WordVO;

/**
 *  服务类
 *
 * @author EasyX è±è¯ (240018840@qq.com)
 * @since 2020-12-31
 */
public interface IWordService extends IService<Word> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param word
	 * @return
	 */
	IPage<WordVO> selectWordPage(IPage<WordVO> page, WordDTO word);

	boolean saveOrUpdate(WordDTO entity);


}
