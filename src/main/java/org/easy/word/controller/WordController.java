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
package org.easy.word.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.easy.mybatisplus.support.Condition;
import org.easy.mybatisplus.support.Query;
import org.easy.tool.util.Func;
import org.easy.tool.web.R;
import org.easy.word.dto.WordDTO;
import org.easy.word.entity.Word;
import org.easy.word.service.IWordService;
import org.easy.word.vo.WordVO;
import org.easy.word.wrapper.WordWrapper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

//import org.springblade.system.feign.IDictClient;

/**
 * 花样文字 控制器
 *
 * @author EasyX è±è¯ (240018840@qq.com)
 * @since 2020-12-31
 */
@RestController
@AllArgsConstructor
@RequestMapping("/word/word")
@Api(value = "花样文字", tags = "花样文字")
public class WordController {

	private IWordService wordService;

	//private IDictClient dictClient;

	/**
	* 详情
	*/
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入word", position = 1)
	public R<WordVO> detail(WordDTO word) {
		Word detail = wordService.getOne(Condition.getQueryWrapper(word));
		WordWrapper wordWrapper = new WordWrapper();
		return R.success(wordWrapper.entityVO(detail));
	}

	/**
	* 列表 
	*/
	@GetMapping("/list")
	@ApiOperation(value = "列表", notes = "传入word", position = 2)
	public R<List<WordVO>> list(WordDTO word) {
		List<Word> list = wordService.list(Condition.getQueryWrapper(word));
		WordWrapper wordWrapper = new WordWrapper();
		return R.success(wordWrapper.listVO(list));
	}

	/**
	* 自定义分页 
	*/
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入word", position = 3)
	public R<IPage<WordVO>> page(WordDTO word, Query query) {
		IPage<WordVO> pages = wordService.selectWordPage(Condition.getPage(query), word);
		return R.success(pages);
	}

	/**
	* 新增 
	*/
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入word", position = 4)
	public R save(@Valid @RequestBody WordDTO word) {
		return R.status(wordService.save(word));
	}

	/**
	* 修改 
	*/
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入word", position = 5)
	public R update(@Valid @RequestBody WordDTO word) {
		return R.status(wordService.updateById(word));
	}

	/**
	* 新增或修改 
	*/
	@PostMapping(path = "/submit",consumes = {"application/x-www-form-urlencoded"},produces = {"application/json;charset=UTF-8"})
	@ApiOperation(value = "新增或修改", notes = "传入word", position = 6)
	public R submit(@Valid @ModelAttribute WordDTO word) {
		return R.status(wordService.saveOrUpdate(word));
	}

	
	/**
	* 删除 
	*/
	@PostMapping("/remove")
	@ApiOperation(value = "删除", notes = "传入ids", position = 7)
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(wordService.removeByIds(Func.toIntList(ids)));
	}

	@RequestMapping (value="/remove/{id}",method = RequestMethod.POST)
	@ApiOperation(value = "册除2", notes = "册除")
	@ApiImplicitParam(name="id",value="id",required=true,paramType="path")
	public R<Integer> remove(@PathVariable("id") Long id, HttpServletRequest request) {
		wordService.removeById(id);
		return R.success();
	}
	
}
