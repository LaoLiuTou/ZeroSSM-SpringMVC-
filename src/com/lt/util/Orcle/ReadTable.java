package com.lt.util.Orcle;
 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.lt.jdbc.JdbcBean;

public class ReadTable {
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    JdbcBean jb=new JdbcBean();
		 
		Connection con = null; //表示数据库的连接对象  
        Statement stmt = null;  //表示数据库的更新操作  
        ResultSet result = null; //表示接收数据库的查询结果  
        ResultSet resultP = null; //表示接收数据库的查询结果  
        try {
			Class.forName(jb.getDbdriver());
		 //1、使用CLASS 类加载驱动程序  
        con = DriverManager.getConnection(jb.getDburl(),jb.getDbuser(),jb.getDbpassword()); //2、连接数据库  
        stmt = con.createStatement(); //3、Statement 接口需要通过Connection 接口进行实例化操作  
        result = stmt.executeQuery("select t.column_name,t.DATA_TYPE,c.COMMENTS from user_tab_columns t,user_col_comments c " +
        		"where t.table_name = c.table_name and t.column_name = c.column_name and t.table_name ='ABCDEF'"); //执行SQL 语句，查询数据库  
        
        //resultP = stmt.executeQuery("select cu.* from user_cons_columns cu, user_constraints au " +
        //		"where cu.constraint_name = au.constraint_name and au.constraint_type = 'P' and au.table_name ='ABCDEF'"); //执行SQL 语句，查询数据库  
        
        while (result.next()){  
             
        	String data_type=result.getString("data_type");
        	String column_name=result.getString("column_name");
        	String comments=result.getString("comments");
        	System.out.println(result.getRow());
            System.out.println(data_type+"XXX"+column_name+"YYY"+comments);  
        } 
        /*while (resultP.next()){  
             
        	String column_name=resultP.getString("column_name");
            System.out.println(column_name+"YYY");  
        } 
        		resultP.close(); */
                result.close();  
                con.close(); // 4、关闭数据库  .
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  
 
}
