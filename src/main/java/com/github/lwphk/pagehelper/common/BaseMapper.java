package com.github.lwphk.pagehelper.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;


/**
 * 
 * @author lwp
 * 
 * @param <Model>
 * @param <PK>
 */
public interface BaseMapper<Entity,PK, Example> {

	/**
	 * 数量
	 * @param example
	 * @return
	 */
	long countByExample(Example example);
	
	/**
	 * 插入对象
	 * 
	 * @param model
	 *            对象
	 */
	int insertSelective(Entity entity);

	/**
	 * 更新对象
	 * 
	 * @param model
	 *            对象
	 */
	int updateByPrimaryKeySelective(Entity entity);
	
	/**
	 * 更新对象
	 * @param record
	 * @param example
	 * @return
	 */
	int updateByExampleSelective(@Param("record") Entity record, @Param("example") Example example);

	/**
	 * 通过主键, 删除对象
	 * 
	 * @param id
	 *            主键
	 */
	int deleteByPrimaryKey(PK id);
	
	
	
	int deleteByExample(Example example);

	/**
	 * 通过主键, 查询对象
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	Entity selectByPrimaryKey(PK id);

	/**
	 * 
	 * @param example
	 *            查询封装器
	 * @param page
	 *            分页
	 * @return
	 */
	public List<Entity> selectByExample(Example example, Page<Entity> page);
	
	/**
	 * 返回结果集
	 * @param example 查询封装器
	 * @return
	 */
	public List<Entity> selectByExample(Example example);
	
	
		
}
