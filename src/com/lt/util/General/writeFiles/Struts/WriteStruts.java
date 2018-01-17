package com.lt.util.General.writeFiles.Struts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import com.lt.util.ProjectBean;

public class WriteStruts {
	/**
	 * 创建文件
	 * 
	 * @return
	 */
	public String writeStruts(){
		
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/struts.xml";
		dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/";
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
		
        File file=new File(url);
        File dir=new File(dirs);
        if(!file.exists()){
        	dir.mkdirs();
            file.createNewFile();
        }
            //FileOutputStream out=new FileOutputStream(file,true);        
            FileOutputStream out=new FileOutputStream(file);        
            StringBuffer sb=new StringBuffer();
            
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
            sb.append("<!DOCTYPE struts PUBLIC \"-//Apache Software Foundation//DTD Struts Configuration 2.0//EN\"\n");
            sb.append("	\"http://struts.apache.org/dtds/struts-2.0.dtd\">\n");
            sb.append("<struts>\n");
            sb.append(" 	<constant name=\"struts.objectFactory\" value=\"spring\"></constant>\n");
            sb.append("	<constant name=\"struts.action.excludePattern\" value=\"/services,/services/.*,/FileUpload\" />\n");
            
            out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;     
        out.close();
    }
 
	/**
	 * 动态添加中间内容
	 * 
	 * @return
	 */
	public  void appendFiles(String tableName) throws IOException{
		Logger log = Logger.getLogger("ZeroLog");   
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/struts.xml";
		 
        File file=new File(url);
         
        if(!file.exists())
            file.createNewFile();
        String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        
	        sb.append("	<package name=\""+lowerName+"\" namespace=\"/\"  extends=\"struts-default\">\n");
	        sb.append("		<action name=\"*"+lowerName+"\" class=\"com."+pb.getProjectName().toLowerCase()+".action."+tableName.toLowerCase()+"."+lowerName+"Action\" method=\"{1}\">\n"); 
	        sb.append("			<result name=\"add\" type=\"redirect\">/list"+lowerName+".action?page=1</result>\n");
	        sb.append("			<result name=\"select\">/pages/"+tableName.toLowerCase()+"/"+tableName.toLowerCase()+"Details.jsp</result>\n");
	        sb.append("			<result name=\"delete\" type=\"redirect\">/list"+lowerName+".action?page=1</result>\n");
	        sb.append("			<result name=\"update\" type=\"redirect\">/list"+lowerName+".action?page=1</result>\n");
	        sb.append("			<result name=\"list\">/pages/"+tableName.toLowerCase()+"/"+tableName.toLowerCase()+"List.jsp</result>\n");
	        sb.append("		</action>\n");
	        sb.append("	</package>\n");
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;       
        out.close();
    }
	
	/**
	 * 添加结尾
	 * 
	 * @return
	 */
	public  void addEnd() throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/struts.xml";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        	sb.append("</struts>\n");
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;      
        log.info("创建文件struts.xml成功！") ;      
        out.close();
    }
 
	//首字母转小写
    public static String toLowerCaseFirstOne(String s)
    {
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    //首字母转大写
    public static String toUpperCaseFirstOne(String s)
    {
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
 
 

}

