package com.lin.DriverUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.lin.domain.ZtreeBean;

public class DriverUtils {

	private static String url = "jdbc:mysql://localhost/jeesite";
	private static String dri = "com.mysql.jdbc.Driver";
	private static String password = "root";
	private static String user = "root";
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet rs = null;
	
	public static Statement basedriver(){
		try {
			Class.forName(dri);
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statement;
	}
	
	public static List<ZtreeBean> getFNodes(String id){
		List<ZtreeBean> beans = new ArrayList<ZtreeBean>();
		try {
			rs = basedriver().executeQuery("select * from sys_menu where parent_id = '"+id+"'");
			while(rs.next()){
				ZtreeBean bean = new ZtreeBean();
				bean.setId(rs.getString(1));
				bean.setName(rs.getString(4));
				bean.setpId(rs.getString(2));
				bean.setisParent(basedriver().executeQuery("select * from sys_menu where parent_id = '"+rs.getString(1)+"'").next());
				beans.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				statement.close();
				connection.close();
			} catch (Exception e2) {
			e2.printStackTrace();
			}
		}
		
		return beans;
	}
	
	
	public static void main(String[] args) {
		Gson gson = new Gson();
		System.out.println(gson.toJson(getFNodes("0")));
	}
}
