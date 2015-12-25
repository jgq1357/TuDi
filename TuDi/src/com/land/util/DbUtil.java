package com.land.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.log4j.Logger;

/**
 * 数据库工具类
 * @author liangjq
 *
 */
public class DbUtil {
	
	private static DataSource ds;
	
	static {
		Properties props = new Properties();
			try{
			props.load(DbUtil.class.getClassLoader().getResourceAsStream("dbcp.properties"));
			ds = BasicDataSourceFactory.createDataSource(props);
			}catch(Exception e){
				Logger log = Logger.getLogger(DbUtil.class);
				log.error("数据库连接池初始化出错。");
			}
		}
	
	/**
	 * 获取db连接
	 * @return db连接对象 connection
	 */
	public static Connection getConnection(){
		try {
			if(ds != null){
				return ds.getConnection();
			}
		} catch (SQLException e) {
			Logger log = Logger.getLogger(DbUtil.class);
			log.error("获取数据库连接出错。");
		}
		return null;
	}
	
	/**
	 * 关闭db连接
	 * @param conn db连接对象
	 */
	public static void closeConnection(Connection conn){
		try {
			if(conn != null && !conn.isClosed()){
					conn.close();
			}
		} catch (SQLException e) {
			Logger log = Logger.getLogger(DbUtil.class);
			log.error("关闭数据库连接出错。");
		}
	}
	
}
