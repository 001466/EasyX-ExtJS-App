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
package org.easy.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.easy.mybatisplus.support.Condition;
import org.easy.mybatisplus.support.Query;
import org.easy.system.entity.Dict;
import org.easy.system.service.IDictService;
import org.easy.system.vo.DictVO;
import org.easy.system.wrapper.DictWrapper;
import org.easy.tool.util.Func;
import org.easy.tool.web.R;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

//import org.springblade.system.feign.IDictClient;

/**
 * 字典管理 控制器
 *
 * @author EasyX Ó¢Ê¶ (240018840@qq.com)
 * @since 2020-09-04
 */
@RestController
@AllArgsConstructor
@RequestMapping("/system/v1/dict")
@Api(value = "字典管理", tags = "字典管理")
public class DictController {

	private IDictService dictService;

	//private IDictClient dictClient;

	/**
	* 详情
	*/
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入dict", position = 1)
	public R<DictVO> detail(Dict dict) {
		Dict detail = dictService.getOne(Condition.getQueryWrapper(dict));
		DictWrapper dictWrapper = new DictWrapper();
		return R.success(dictWrapper.entityVO(detail));
	}

	/**
	* 列表 
	*/
	@GetMapping("/list")
	@ApiOperation(value = "列表", notes = "传入dict", position = 2)
	public R<List<DictVO>> list(Dict dict) {
		List<Dict> list = dictService.list(Condition.getQueryWrapper(dict));
		DictWrapper dictWrapper = new DictWrapper();
		return R.success(dictWrapper.listVO(list));
	}

	/**
	* 自定义分页 
	*/
	@GetMapping("/page")
	@ApiOperation(value = "分页1", notes = "传入dict", position = 3)
	public R<IPage<DictVO>> page(DictVO dict, Query query) {
		query.setDescs("code");
		IPage<DictVO> pages = dictService.selectDictPage(Condition.getPage(query), dict);
		return R.success(pages).setMessage("v1");
	}

//	/**
//	 * 自定义分页
//	 */
//	@GetMapping("/page")
//	@ApiOperation(value = "分页2", notes = "传入dict", position = 3)
//	public R<IPage<DictVO>> page2(DictVO dict, Query query) {
//		IPage<DictVO> pages = dictService.selectDictPage(Condition.getPage(query), dict);
//		return R.success(pages).setMessage("v2");
//	}

	/**
	* 新增 
	*/
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入dict", position = 4)
	public R save(@Valid @RequestBody Dict dict) {
		return R.status(dictService.save(dict));
	}

	/**
	* 修改 
	*/
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入dict", position = 5)
	public R update(@Valid @RequestBody Dict dict) {
		return R.status(dictService.updateById(dict));
	}

	/**
	* 新增或修改 
	*/
	@PostMapping(path = "/submit",consumes = {"application/x-www-form-urlencoded"},produces = {"application/json;charset=UTF-8"})
	@ApiOperation(value = "新增或修改", notes = "传入dict", position = 6)
	public R submit(@Valid @ModelAttribute Dict dict) {
		return R.status(dictService.saveOrUpdate(dict));
	}

	
	/**
	* 删除 
	*/
	@PostMapping("/remove")
	@ApiOperation(value = "删除1", notes = "传入ids", position = 7)
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(dictService.removeByIds(Func.toIntList(ids)));
	}

	@RequestMapping (value="/remove/{id}",method = RequestMethod.POST)
	@ApiOperation(value = "册除2", notes = "册除")
	@ApiImplicitParam(name="id",value="id",required=true,paramType="path")
	public R<Integer> remove(@PathVariable("id") Long id, HttpServletRequest request) {
		dictService.removeById(id);
		return R.success();
	}

	
}
