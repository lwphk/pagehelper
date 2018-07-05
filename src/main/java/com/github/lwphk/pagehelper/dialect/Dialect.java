package com.github.lwphk.pagehelper.dialect;

/**
 * 
 * @author lwp
 *
 */
public abstract class Dialect {

	/**
	 * 得到分页sql
	 * 
	 * @param sql
	 * @param offset
	 * @param limit
	 * @return
	 */
	public abstract String getLimitString(String sql, int offset, int limit);

	/**
	 * 得到总数量 sql
	 * 
	 * @param sql
	 * @return
	 */
	public abstract String getCountString(String sql);

}
