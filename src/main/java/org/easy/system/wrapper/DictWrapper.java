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
package org.easy.system.wrapper;

import lombok.AllArgsConstructor;
import org.easy.mybatisplus.support.BaseEntityWrapper;
import org.easy.system.entity.Dict;
import org.easy.system.vo.DictVO;
import org.easy.tool.util.BeanUtil;

//import org.easy.system.feign.IDictClient;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author EasyX Ó¢Ê¶ (240018840@qq.com)
 * @since 2020-09-04
 */
@AllArgsConstructor
public class DictWrapper extends BaseEntityWrapper<Dict, DictVO>  {

	//private IDictClient dictClient;

	@Override
	public DictVO entityVO(Dict dict) {
		DictVO dictVO = BeanUtil.copy(dict, DictVO.class);

		/*R<String> dict = dictClient.getValue("dict" , dictVO.getCategory());
		if (dict.isSuccess()) {
			String categoryName = dict.getData();
			dictVO.setCategoryName(categoryName);
		}*/

		return dictVO;
	}

}
