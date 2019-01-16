package com.lt.util.General.writeFiles.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import com.lt.util.ProjectBean;

public class WriteLogger {
	/**
	 * 创建文件
	 * 
	 * @return
	 */
	public String writeLogger(){
		
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/src/log4j.properties";
		dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/src/";
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
		ProjectBean pb=new ProjectBean();
        File file=new File(url);
        File dir=new File(dirs);
        if(!file.exists()){
        	dir.mkdirs();
            file.createNewFile();
        }
            //FileOutputStream out=new FileOutputStream(file,true);        
            FileOutputStream out=new FileOutputStream(file);        
            StringBuffer sb=new StringBuffer();
             
            sb.append("log4j.rootCategory=ERROR, STDOUT \n");  
            sb.append("log4j.appender.STDOUT=org.apache.log4j.ConsoleAppender\n");  
            sb.append("log4j.appender.STDOUT.layout=org.apache.log4j.PatternLayout\n");  
            sb.append("log4j.appender.STDOUT.layout.ConversionPattern=["+pb.getProjectName()+"] %p [%d] | %m%n\n");   
            
            sb.append("log4j.logger."+pb.getProjectName()+"Logger=INFO,A\n");   
            sb.append("log4j.appender.A=org.apache.log4j.DailyRollingFileAppender\n");   
            sb.append("log4j.appender.A.File=${catalina.home}/logs/"+pb.getProjectName()+"Logger.log\n");   
            sb.append("log4j.appender.A.layout=org.apache.log4j.PatternLayout\n");   
            sb.append("log4j.appender.A.layout.ConversionPattern=["+pb.getProjectName()+"](%F:%L)%d|%m%n\n");  
             
            sb.append("log4j.logger.com."+pb.getProjectName().toLowerCase()+"=INFO,B\n");   
            sb.append("log4j.appender.B=org.apache.log4j.DailyRollingFileAppender\n");   
            sb.append("log4j.appender.B.File=${catalina.home}/logs/"+pb.getProjectName()+"Logger_sql.log\n");   
            sb.append("log4j.appender.B.layout=org.apache.log4j.PatternLayout\n");   
            sb.append("log4j.appender.B.layout.ConversionPattern=["+pb.getProjectName()+"](%F:%L)%d|%m%n\n");  
             		
            out.write(sb.toString().getBytes("utf-8"));
                 
            log.info("创建文件log4j.properties 成功！") ;     
        out.close();
    }
 
}
