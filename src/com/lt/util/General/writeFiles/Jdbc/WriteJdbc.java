package com.lt.util.General.writeFiles.Jdbc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.logging.Logger;

import com.lt.jdbc.JdbcBean;
import com.lt.util.ProjectBean;

public class WriteJdbc {
	/**
	 * 创建文件
	 * 
	 * @return
	 */
	public String writeJdbc(){
		
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/jdbc/jdbc.properties";
		dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/jdbc/";
		try {
			createFiles(dirs,url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			status="failure";
			e.printStackTrace();
		}
		 
		return status;
	}
	/**
	 * 创建
	 * 
	 * @return
	 */
	public  void createFiles(String dirs,String url) throws IOException{
		Logger log = Logger.getLogger("ZeroLog");   
		JdbcBean jb=new JdbcBean();
        File file=new File(url);
        File dir=new File(dirs);
        if(!file.exists()){
        	dir.mkdirs();
            file.createNewFile();
        }
            //FileOutputStream out=new FileOutputStream(file,true);        
            FileOutputStream out=new FileOutputStream(file);        
            StringBuffer sb=new StringBuffer();
             
            sb.append("driverClassName="+jb.getDbdriver()+"\n");
            sb.append("url="+jb.getDburl()+"?useUnicode=true&characterEncoding=utf8&autoReconnect=true&rewriteBatchedStatements=TRUE&allowMultiQueries=TRUE\n");
            sb.append("username="+jb.getDbuser()+"\n");
            sb.append("password="+jb.getDbpassword()+"\n");
            
            out.write(sb.toString().getBytes("utf-8"));
            log.info(sb.toString()) ;      
            log.info("创建文件jdbc.properties 成功！") ;     
        out.close();
    }
 
}
