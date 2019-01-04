package com.github.lwphk.demo.service;

import java.util.List;

import com.github.lwphk.pagehelper.common.Page;

public interface IBaseService<Entity,PK,Example> {

	/**
	 * 数量
	 * @param example
	 * @return
	 */
	long count(Example example);
	
	/**
	 * 插入对象
	 * 
	 * @param Entity
	 *            对象
	 */
	Entity insert(Entity entity);

	/**
	 * 更新对象
	 * 
	 * @param Entity
	 *            对象
	 */
	int updateByPrimaryKeySelective(Entity entity);
	
	
	/**
	 * 更新对象
	 * @param record
	 * @param example
	 * @return
	 */
	int updateByExampleSelective(Entity record, Example example);

	/**
	 * 通过主键, 删除对象
	 * 
	 * @param id
	 *            主键
	 */
	int deleteByPrimaryKey(PK id);

	/**
	 * 通过主键, 查询对象
	 * 
	 * @param id
	 *            主键
	 * @return Entity 对象
	 */
	Entity selectByPrimaryKey(PK id);

	/**
	 * 
	 * @param example
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public Page<Entity> selectByExample(Example example, int pageNo, int pageSize);
	
	/**
	 * 
	 * @param example
	 * @return
	 */
	public List<Entity> selectByExample(Example example);
	
	/**
	 * 
	 * @param example
	 * @return
	 */
	public Entity selectByExampleSingleResult(Example example);
}
