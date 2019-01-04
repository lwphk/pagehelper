package com.github.lwphk.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.lwphk.demo.dao.entity.User;
import com.github.lwphk.demo.dao.entity.UserExample;
import com.github.lwphk.demo.dao.mapper.UserMapper;
import com.github.lwphk.demo.service.IBaseService;
import com.github.lwphk.demo.service.IUserService;
import com.github.lwphk.pagehelper.common.Page;

@RestController
public class PageController {

	
	@Autowired
	UserMapper mapper;
	
	@Autowired
	IBaseService<User, Long, UserExample> service;
	
	@Autowired
	IUserService userService;
	
	@RequestMapping("/list")
	public Page<User> index(@RequestParam(defaultValue="1")Integer pageNo,@RequestParam(defaultValue="5")Integer pageSize) {
		Page<User> page = new Page<>(pageNo, pageSize);
		UserExample example = new UserExample();
		example.setDistinct(true);
		example.setOrderByClause("id desc");
		example.createCriteria().andIdGreaterThanOrEqualTo(1);
		page.setResult(mapper.selectByExample(example, page));
		return page;
	}
	
	@RequestMapping("/list1")
	public Page<User> index1(@RequestParam(defaultValue="1")Integer pageNo,@RequestParam(defaultValue="5")Integer pageSize) {
		
		return service.selectByExample(new UserExample(), pageNo, pageSize);
	}
	
	@RequestMapping("/list2")
	public Page<User> index2(@RequestParam(defaultValue="1")Integer pageNo,@RequestParam(defaultValue="5")Integer pageSize) {
		UserExample example = new UserExample();
		example.createCriteria().andIdEqualTo(1);
		userService.selectByExampleSingleResult(example);
		return userService.selectByExample(new UserExample(), pageNo, pageSize);
	}
}
