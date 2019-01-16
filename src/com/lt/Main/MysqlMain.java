package com.lt.Main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.lt.jdbc.JdbcBean;
import com.lt.util.CopyFiles;
import com.lt.util.ProjectBean;
import com.lt.util.General.writeFiles.Action.WriteAction;
import com.lt.util.General.writeFiles.Classpath.WriteClasspath;
import com.lt.util.General.writeFiles.Controller.WriteController;
import com.lt.util.General.writeFiles.Java.WriteBean;
import com.lt.util.General.writeFiles.Java.WriteMapper;
import com.lt.util.General.writeFiles.Jdbc.WriteJdbc;
import com.lt.util.General.writeFiles.Logger.WriteLogger;
import com.lt.util.General.writeFiles.Mybatis.WriteConfig;
import com.lt.util.General.writeFiles.Mybatis.WriteXml;
import com.lt.util.General.writeFiles.Mymetadata.WriteMymetadata;
import com.lt.util.General.writeFiles.Service.WriteService;
import com.lt.util.General.writeFiles.Service.WriteServiceImpl;
import com.lt.util.General.writeFiles.Spring.WriteSpring;
import com.lt.util.General.writeFiles.Spring.WriteSpringmvc;
import com.lt.util.General.writeFiles.Struts.WriteStruts;
import com.lt.util.General.writeFiles.Web.WriteWeb;
import com.lt.util.General.writeFiles.WebService.WriteWSXml;
import com.lt.util.General.writeFiles.WebService.WriteWebService;
import com.lt.util.General.writeFiles.WebService.WriteWebServiceImpl;
import com.lt.util.General.writeFiles.project.WriteProject;
import com.lt.util.General.writeFiles.servlet.WriteServlet;
import com.lt.util.General.writeFiles.utils.WriteCORSFilter;
import com.lt.util.General.writeFiles.utils.WriteFileUpload;
import com.lt.util.General.writeFiles.utils.WriteJsonObjectMapper;
 

public class MysqlMain {
		static String dbType="mysql";
		static int size = getTables().size();
		static List tableList = getTables();
		/**
		 * @param args
		 * @throws IOException 
		 */
		public static void main(String[] args) throws IOException {
			// TODO Auto-generated method stub
			Logger log = Logger.getLogger("ZeroLog");   
			//copyFiles
			copyFiles();
			
			//.classpath
			classpath();
			
			//.project
			project();
			
			//.mymetadata
			mymetadata();
			
			//Spring 
			spring();
			
			//writeLogger
			writeLogger();
			
			//sqlmap
			sqlmap();
			
			//jdbc 
			jdbc();
			
			
			//Mabatis table xml
			xml();
			
			//Java  Bean
			bean();
			
			//Java Dao
			dao();
			
			//Java DaoImpl
			//daoImpl();
			
			//Java service
			service();
			
			//Java serviceImpl
			serviceImpl();
			
			//WebService 
			//webservice(); 
			
			//WebServiceImpl
			//webserviceImpl();
			
			//xfire Xml
			//writeWSXml();
			
			//web.xml
			writeWeb();
			
			//Springmvc
			writeSpringmvc();
			//struts
			//struts();
			
			//Action
			//writeAction();
			
			//controller
			writeController();
			//servlet
			//WriteServlet.writeServlet();
			
			//utils  and upload 
			WriteCORSFilter.writeCORSFilter();
			
			WriteJsonObjectMapper.writeJsonObjectMapper();
			
			WriteFileUpload.writeFileUpload();
			
			log.info("Over!!!"); 
		}
		/**
		 * 复制文件
		 * @return
		 * @throws IOException 
		 */
		public static void copyFiles() throws IOException{
			
			//copy files
			Logger log = Logger.getLogger("ZeroLog");   
			ProjectBean pb=new ProjectBean();
			CopyFiles cf=new CopyFiles();
			cf.copyDir(System.getProperty("user.dir")+"/WebRoot",pb.getProjectUrl()+"/"+pb.getProjectName()+"/WebRoot");  
			cf.copyDir(System.getProperty("user.dir")+"/.settings",pb.getProjectUrl()+"/"+pb.getProjectName()+"/.settings");  
			//cf.copyfile(System.getProperty("user.dir")+"/.project",pb.getProjectUrl()+"/"+pb.getProjectName()+"/.project");  
			//cf.copyfile(System.getProperty("user.dir")+"/1.txt",pb.getProjectUrl()+"/"+pb.getProjectName()+"/1.txt");  
			//cf.copyDir(System.getProperty("user.dir")+"/WebServices",pb.getProjectUrl()+"/"+pb.getProjectName()+"/WebServices");  
			//http://www.ip138.com/
		}
		 
		/**
		 * 写.classpath
		 * @return
		 */
		public static void classpath() {
			//.classpath
			Logger log = Logger.getLogger("ZeroLog");   
			WriteClasspath wcp =new WriteClasspath();
			wcp.writeClasspath();
		}
	 
		/**
		 * 写.project
		 * @return
		 */
		public static void project()  {
			//.project
			Logger log = Logger.getLogger("ZeroLog"); 
			WriteProject wp = new WriteProject();
			wp.writeProject();
		}
		
		/**
		 * 写.mymetadata
		 * @return
		 */
		public static void mymetadata()  {
			//.mymetadata
			Logger log = Logger.getLogger("ZeroLog"); 
			WriteMymetadata wmd = new WriteMymetadata();
			wmd.writeMymetadata();
		}
		
		/**
		 * 写spring
		 * @return
		 * @throws IOException 
		 */
		public static void spring() throws IOException  {
			//Spring 
			Logger log = Logger.getLogger("ZeroLog"); 
			WriteSpring wsp=new WriteSpring();
			wsp.writeSpring();
			for(int i=0;i<size;i++){
				wsp.appendFiles(tableList.get(i).toString()) ;
			}
			wsp.addEnd();
		}
		
		/**
		 * 写struts
		 * @return
		 * @throws IOException 
		 */
		public static void struts() throws IOException  {
			//Spring 
			Logger log = Logger.getLogger("ZeroLog"); 
			WriteStruts wst=new WriteStruts();
			wst.writeStruts();
			for(int i=0;i<size;i++){
				wst.appendFiles(tableList.get(i).toString()) ;
			}
			wst.addEnd();
		}
		
		/**
		 * 写sqlmap
		 * @return
		 * @throws IOException 
		 */
		public static void sqlmap() throws IOException  {
			//Ibatis
			Logger log = Logger.getLogger("ZeroLog"); 
			WriteConfig ws=new WriteConfig();
			ws.writeSqlmap();
			ws.appendAliaseshead();
			for(int i=0;i<size;i++){
				ws.appendAliasesFiles(tableList.get(i).toString()) ;
			}
			ws.appendAliasesend();
			ws.appendMappershead();
			for(int i=0;i<size;i++){
				ws.appendMappersFiles(tableList.get(i).toString()) ;
			}
			ws.appendMappersend();
			ws.addEnd();
		}
		
		/**
		 * 写jdbc
		 * @return
		 */
		public static void jdbc()  {
			//jdbc 
			Logger log = Logger.getLogger("ZeroLog"); 
			WriteJdbc wj=new WriteJdbc();
			wj.writeJdbc();
			
		}
		
		/**
		 * 写Ibatis  xml
		 * @return
		 * @throws IOException 
		 */
		public static void xml() throws IOException  {
			//Ibatis table xml
			WriteXml wx=new WriteXml();
			for(int i=0;i<size;i++){
				String tableName=tableList.get(i).toString();
				String pKey=getPKey(tableName);
				wx.writeXml(tableName,pKey,dbType) ; 
				int columnSize = getColumn(tableName).size();
				Map columnMap = getColumn(tableName);
				//循环列
				for(int j=0;j<columnSize;j++){
		        	List l=(List) columnMap.get(j+1);
		        	wx.appendSelectFiles(tableName, (String)l.get(0), (String)l.get(2),dbType) ; 
				}
				wx.addSelectEnd(tableName, pKey,dbType);
				//循环列
				for(int j=0;j<columnSize;j++){
		        	List l=(List) columnMap.get(j+1);
		        	wx.appendSelectFiles(tableName, (String)l.get(0), (String)l.get(2),dbType) ; 
				}
				wx.addCountEnd(tableName, pKey);
				//循环列
				for(int j=0;j<columnSize;j++){
		        	List l=(List) columnMap.get(j+1);
		        	//if(!pKey.equals((String)l.get(0)))
					wx.appendUpdateFiles(tableName, (String)l.get(0)) ;
				}
				wx.addUpdateEnd(tableName, pKey);
				//循环列
				for(int j=0;j<columnSize;j++){
		        	List l=(List) columnMap.get(j+1);
		        	if(!((String)l.get(0)).toUpperCase().equals(pKey.toUpperCase()))
					wx.appendInsertFiles1(tableName, (String)l.get(0));
				}
				wx.appendInsertFiles2(tableName,pKey);
				//循环列
				for(int j=0;j<columnSize;j++){
		        	List l=(List) columnMap.get(j+1);
		        	if(!((String)l.get(0)).toUpperCase().equals(pKey.toUpperCase()))
					wx.appendInsertFiles3(tableName, (String)l.get(0));
				}
				//wx.addInsertEnd(tableName,pKey,dbType);
				
				wx.addmulStart(tableName, pKey);
				//循环列
				for(int j=0;j<columnSize;j++){
		        	List l=(List) columnMap.get(j+1);
		        	//if(!((String)l.get(0)).toUpperCase().equals(pKey.toUpperCase()))
					wx.addmulInsertFiles1(tableName, (String)l.get(0));
				}
				wx.addmulInsertFiles2(tableName,pKey);
				//循环列
				for(int j=0;j<columnSize;j++){
		        	List l=(List) columnMap.get(j+1);
		        	//if(!((String)l.get(0)).toUpperCase().equals(pKey.toUpperCase()))
					wx.addmulInsertFiles3(tableName, (String)l.get(0));
				}
				wx.addmulInsertEnd(tableName,pKey,dbType);
			}
			
		}
		
		/**
		 * 写JavaBean  xml
		 * @return
		 * @throws IOException 
		 */
		public static void bean() throws IOException  {
			//Java  Bean
			WriteBean wb = new WriteBean();
			//循环table
			for(int i=0;i<size;i++){
				String tableName=tableList.get(i).toString();
				wb.writeBean(tableName) ;
				int columnSize = getColumn(tableName).size();
				Map columnMap = getColumn(tableName);
				//循环列
				for(int j=0;j<columnSize;j++){
		        	List l=(List) columnMap.get(j+1);
					wb.appendFiles(tableName, (String)l.get(0), (String)l.get(1), (String)l.get(2));
				}
				wb.addEnd(tableName);
			}
		}
		
		/**
		 * 写java dao
		 * @return
		 */
		public static void dao()  {
			WriteMapper wd = new WriteMapper();
			for(int i=0;i<size;i++){
				String tableName=tableList.get(i).toString();
				String pKey=getPKey(tableName);
				wd.writeDao(pKey,tableName);
			}
		}
		
		/**
		 * 写java daoImpl
		 * @return
		 */
		/*public static void daoImpl()  {
			WriteDaoImpl wdi = new WriteDaoImpl();
			for(int i=0;i<size;i++){
				String tableName=tableList.get(i).toString();
				wdi.writeDaoImpl(tableName);
			}
		}*/
		
		/**
		 * 写java service
		 * @return
		 */
		public static void service()  {
			WriteService wd = new WriteService();
			for(int i=0;i<size;i++){
				String tableName=tableList.get(i).toString();
				String pKey=getPKey(tableName);
				wd.writeService(pKey,tableName);
			}
		}
		
		/**
		 * 写java service Impl
		 * @return
		 */
		public static void serviceImpl()  {
			WriteServiceImpl wdi = new WriteServiceImpl();
			for(int i=0;i<size;i++){
				String tableName=tableList.get(i).toString();
				String pKey=getPKey(tableName);
				wdi.writeServiceImpl(pKey,tableName);
			}
		}
		
		/**
		 * 写webservice
		 * @return
		 * @throws IOException 
		 */
		public static void webservice() throws IOException  {
			WriteWebService wws = new WriteWebService();
			for(int i=0;i<getTables().size();i++){
				String tableName=getTables().get(i).toString();
				String pKey=getPKey(tableName);
				int columnSize = getColumn(tableName).size();
				Map columnMap = getColumn(tableName);
				
				wws.writeWebService(tableName, pKey);
				//循环列
				for(int j=0;j<columnSize;j++){
					 
		        	List l=(List) columnMap.get(j+1);
		        	if(j==(columnSize-1)) { 
		        		wws.addSelectByParam1(tableName, (String)l.get(0), (String)l.get(2), true);
		        	}
		        	else{
		        		wws.addSelectByParam1(tableName, (String)l.get(0), (String)l.get(2), false);
		        	}
				}
				wws.addSelectByParam2(tableName);
				wws.addInsert(tableName);
				//循环列
				for(int j=0;j<columnSize;j++){
					 
		        	List l=(List) columnMap.get(j+1);
		        	if(!l.get(0).toString().toUpperCase().equals(pKey.toUpperCase())){
			        	if(j==(columnSize-1)) { 
			        		wws.addParam(tableName, (String)l.get(0), (String)l.get(2), true);
			        	}
			        	else{
			        		
			        		wws.addParam(tableName, (String)l.get(0), (String)l.get(2), false);
			        	}
		        	} 
				}
				wws.addUpdate(tableName); 
				//循环列
				for(int j=0;j<columnSize;j++){
					 
		        	List l=(List) columnMap.get(j+1);
		        	if(j==(columnSize-1)) { 
		        		wws.addParam(tableName, (String)l.get(0), (String)l.get(2), true);
		        	}
		        	else{
		        		wws.addParam(tableName, (String)l.get(0), (String)l.get(2), false);
		        	}
					 
				}
				wws.addEnd(tableName);
			}
		}
		
		/**
		 * 写webserviceImpl
		 * @return
		 * @throws IOException 
		 */
		public static void webserviceImpl() throws IOException  {
			//webserviceImpl
			WriteWebServiceImpl wwsi = new WriteWebServiceImpl();
			for(int i=0;i<size;i++){
				String tableName=tableList.get(i).toString();
				String pKey=getPKey(tableName);
				int columnSize = getColumn(tableName).size();
				Map columnMap = getColumn(tableName);
				
				wwsi.writeWSImpl(tableName, pKey);
				//循环列
				for(int j=0;j<columnSize;j++){
					 
		        	List l=(List) columnMap.get(j+1);
		        	if(j==(columnSize-1)) { 
		        		wwsi.addSelectByParam1(tableName, (String)l.get(0), (String)l.get(2), true);
		        	}
		        	else{
		        		wwsi.addSelectByParam1(tableName, (String)l.get(0), (String)l.get(2), false);
		        	}
				}
				wwsi.addSelectByParam2(tableName);
				//循环列
				for(int j=0;j<columnSize;j++){
					List l=(List) columnMap.get(j+1);
		        	wwsi.addSelectByParam3(tableName, (String)l.get(0), (String)l.get(2));
				}
				wwsi.addSelectByParam4(tableName);
				//循环列
				for(int j=0;j<columnSize;j++){
					 
		        	List l=(List) columnMap.get(j+1);
		        	if(!l.get(0).toString().toUpperCase().equals(pKey.toUpperCase())){
			        	if(j==(columnSize-1)) { 
			        		wwsi.addParam(tableName, (String)l.get(0), (String)l.get(2), true);
			        	}
			        	else{
			        		wwsi.addParam(tableName, (String)l.get(0), (String)l.get(2), false);
			        	}
		        	}
				}
				wwsi.addSpring(tableName);
				//循环列
				for(int j=0;j<columnSize;j++){
					List l=(List) columnMap.get(j+1);
					if(!l.get(0).toString().toUpperCase().equals(pKey.toUpperCase())){
						wwsi.addClassParam(tableName, (String)l.get(0), (String)l.get(2));
					}	
				}
				wwsi.addInsert(tableName);
				//循环列
				for(int j=0;j<columnSize;j++){
					 
		        	List l=(List) columnMap.get(j+1);
		        	if(j==(columnSize-1)) { 
		        		wwsi.addParam(tableName, (String)l.get(0), (String)l.get(2), true);
		        	}
		        	else{
		        		wwsi.addParam(tableName, (String)l.get(0), (String)l.get(2), false);
		        	}
				}
				wwsi.addSpring(tableName);
				//循环列
				for(int j=0;j<columnSize;j++){
					List l=(List) columnMap.get(j+1);
		        	wwsi.addClassParam(tableName, (String)l.get(0), (String)l.get(2));
				}
				wwsi.addUpdate(tableName);
			}
		}
		/**
		 * 写Xfire xml
		 * @return
		 * @throws IOException 
		 */
		public static void writeWSXml() throws IOException  {
			WriteWSXml wwx = new WriteWSXml();
			wwx.writeWSXml();
			for(int i=0;i<size;i++){
				String tableName=tableList.get(i).toString();
				wwx.appendFiles(tableName);
			}
			wwx.addEnd();
		}
		/**
		 * 写Logger
		 * @return
		 */
		public static void writeLogger() throws IOException  {
			WriteLogger wl = new WriteLogger();
			wl.writeLogger();
		}
		
		/**
		 * 写web.xml
		 * @return
		 */
		public static void writeWeb() throws IOException  {
			WriteWeb ww = new WriteWeb();
			ww.writeWeb();
		}
		/**
		 * 写spring-servlet.xml
		 * @return
		 */
		public static void writeSpringmvc() throws IOException  {
			WriteSpringmvc ws = new WriteSpringmvc();
			ws.writeSpringmvc();
		}
		
		/**
		 * 写Action
		 * @return
		 */
		public static void writeAction() throws IOException  {
			WriteAction wa = new WriteAction();
			for(int i=0;i<size;i++){
				String tableName=tableList.get(i).toString();
				String pKey=getPKey(tableName);
				int columnSize = getColumn(tableName).size();
				Map columnMap = getColumn(tableName);
				wa.writeAction(tableName, pKey);
				
				//循环列
				for(int j=0;j<columnSize;j++){
					 
		        	List l=(List) columnMap.get(j+1);
		        	if(j==(columnSize-1)) { 
		        		wa.addActionProperties(tableName, (String)l.get(0), (String)l.get(2), true);
		        	}
		        	else{
		        		wa.addActionProperties(tableName, (String)l.get(0), (String)l.get(2), false);
		        	}
				}
				wa.addAddFunctionStart(tableName);
				//循环列
				for(int j=0;j<columnSize;j++){
					List l=(List) columnMap.get(j+1);
		        	wa.addBeanProperties(tableName, (String)l.get(0), (String)l.get(2));
				}
				wa.addAddFunctionEnd(tableName,pKey);
				//循环列
				for(int j=0;j<columnSize;j++){
					List l=(List) columnMap.get(j+1);
					wa.addListFunctionStart(tableName, (String)l.get(0), (String)l.get(2));
				}
				wa.addListFunctionEnd(tableName,pKey);
				
				//循环列
				for(int j=0;j<columnSize;j++){
					List l=(List) columnMap.get(j+1);
		        	wa.addBeanProperties(tableName, (String)l.get(0), (String)l.get(2));
				}
				
				wa.addOthers(tableName,pKey);
				
				wa.addAllFunctionStart(tableName,pKey);
				//循环列
				for(int j=0;j<columnSize;j++){
					List l=(List) columnMap.get(j+1);
					wa.addListFunctionStart(tableName, (String)l.get(0), (String)l.get(2));
				}
				wa.addAllFunctionEnd(tableName,pKey);
			 
			}
		}
		/**
		 * 写controller
		 * @return
		 */
		public static void writeController() throws IOException  {
			WriteController wc = new WriteController();
			for(int i=0;i<size;i++){
				String tableName=tableList.get(i).toString();
				String pKey=getPKey(tableName);
				int columnSize = getColumn(tableName).size();
				Map columnMap = getColumn(tableName);
				wc.writeController(tableName, pKey);
				wc.addAddFunction(tableName, pKey);
				wc.addmulAddFunction(tableName, pKey);
				wc.addDeleteFunction(tableName, pKey);
				wc.addSelectFunction(tableName, pKey);
				wc.addUpdateFunction(tableName, pKey);
				wc.addListFunctionStart(tableName, pKey);
				for(int j=0;j<columnSize;j++){
					List l=(List) columnMap.get(j+1);
					wc.addListFunctionParam(tableName, (String)l.get(0), (String)l.get(2));
				}
				wc.addListFunctionEnd(tableName, pKey);
				 
			 
			}
		}
		
		
		
		
		/////////////////////////////////////////////////////////
		/**
		 * 选取tables
		 * 
		 * @return
		 */
		public static List getTables(){
			List tableList = new ArrayList();
			/*JdbcBean jb=new JdbcBean();
			 
			Connection con = null; //表示数据库的连接对象  
	        Statement stmt = null;  //表示数据库的更新操作  
	        ResultSet result = null; //表示接收数据库的查询结果  
	        try {
				Class.forName(jb.getDbdriver());
		        con = DriverManager.getConnection(jb.getDburl(),jb.getDbuser(),jb.getDbpassword()); //2、连接数据库  
		        
		        stmt = con.createStatement(); //3、Statement 接口需要通过Connection 接口进行实例化操作  
		        result = stmt.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '"
		        		+jb.getDburl().split("/")[jb.getDburl().split("/").length-1]+"'"); //执行SQL 语句，查询数据库  
		        
		        while (result.next()){  
		           
		        	String table_name=result.getString("table_name");
		        	tableList.add(table_name);
		        	
		            
		        }  
	                result.close();  
	                con.close(); // 4、关闭数据库  
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */
			
			
			//tableList.add("order_num"); 
			//tableList.add("rl_lov"); 
			//tableList.add("sickness"); 
			 
			//tableList.add("area"); 
			
			//tableList.add("group_status"); 
			 
			/*tableList.add("member"); 
			tableList.add("expert"); 
			tableList.add("illness"); 
			tableList.add("problem"); 
			tableList.add("drive"); */
			tableList.add("income_dtl"); 

		 
			
			
			
	        return tableList;
			
		}
		/**
		 * 选取tables的列
		 * 
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public static Map getColumn(String tableName){
			Map colMap = new HashMap();
			JdbcBean jb=new JdbcBean();
			 
			Connection con = null;   
	        Statement stmt = null;    
	        ResultSet result = null;  
	        try {
				Class.forName(jb.getDbdriver());
		        con = DriverManager.getConnection(jb.getDburl(),jb.getDbuser(),jb.getDbpassword());  
		        stmt = con.createStatement();  
		        /*result = stmt.executeQuery("SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '"
		        		+tableName+"' and table_schema = '"+"jb_af_10005"+"'");*/
		        
		        result = stmt.executeQuery("SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '"
		        		+tableName+"' AND TABLE_SCHEMA ='"+jb.getDburl().split("/")[jb.getDburl().split("/").length-1]+"'");
		        while (result.next()){  
		           
		        	String data_type=result.getString("DATA_TYPE");
		        	String column_name=result.getString("COLUMN_NAME");
		        	String comments=result.getString("COLUMN_COMMENT");
		        	 
		        	List colList = new ArrayList(); 
		        	colList.add(column_name);
		        	colList.add(comments);
		        	colList.add(data_type);
		        	
		        	colMap.put(result.getRow(), colList);
		        	
		            
		        }  
	                result.close();  
	                con.close(); //关闭数据库  
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return colMap;
			
		}

		/**
		 * 选取tables的主键
		 * 
		 * @return
		 */
		public static String getPKey(String tableName){
			String column_name = "";
			JdbcBean jb=new JdbcBean();
			 
			Connection con = null;   
	        Statement stmt = null;    
	        ResultSet result = null;  
	        try {
				Class.forName(jb.getDbdriver());
		        con = DriverManager.getConnection(jb.getDburl(),jb.getDbuser(),jb.getDbpassword());  
		        stmt = con.createStatement();  
		        result = stmt.executeQuery("select COLUMN_KEY,COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='"
		        		+tableName+"' AND COLUMN_KEY='PRI'  AND TABLE_SCHEMA ='"+jb.getDburl().split("/")[jb.getDburl().split("/").length-1]+"'"); 
		        
		        while (result.next()){  
		        	 
		        	column_name=result.getString("COLUMN_NAME");
		             
		        }  
	                result.close();  
	                con.close(); //关闭数据库  
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return column_name;
			
		}

	}
