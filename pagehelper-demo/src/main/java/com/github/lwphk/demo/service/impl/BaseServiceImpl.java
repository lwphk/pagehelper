package com.github.lwphk.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.lwphk.demo.service.IBaseService;
import com.github.lwphk.pagehelper.common.BaseMapper;
import com.github.lwphk.pagehelper.common.Page;

@Service
public class BaseServiceImpl<Entity,PK, Example> implements IBaseService<Entity,PK, Example> {

	@Autowired
	public BaseMapper<Entity, PK,Example> mapper;
	

	@Override
	public Entity insert(Entity entity) {
		mapper.insertSelective(entity);
		return entity;
	}

	@Override
	public int updateByPrimaryKeySelective(Entity entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public int deleteByPrimaryKey(PK id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public Entity selectByPrimaryKey(PK id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<Entity> selectByExample(Example example, int pageNo, int pageSize) {
		Page<Entity> page = new Page<Entity>(pageNo, pageSize);
		page.setResult(mapper.selectByExample(example, page));
		return page;
	}

	@Override
	public long count(Example example) {
		return mapper.countByExample(example);
	}

	@Override
	public int updateByExampleSelective(Entity record, Example example) {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public List<Entity> selectByExample(Example example) {
		
		return mapper.selectByExample(example);
	}

	@Override
	public Entity selectByExampleSingleResult(Example example) {
		List<Entity> result = mapper.selectByExample(example);
		return result.isEmpty() ? null : result.get(0);
	}

	
}
