package com.lt.util.General.writeFiles.WebService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import com.lt.util.ProjectBean;

public class WriteWSXml {
	/**
	 * 创建文件
	 * 
	 * @return
	 */
	public String writeWSXml(){
		
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"WebServices/services.xml";
		dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"WebServices/";
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
            
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            sb.append("<beans >\n");
            
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
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"WebServices/services.xml";
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
        File file=new File(url);
         
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        
	        sb.append("	<service xmlns=\"http://xfire.codehaus.org/config/1.0\">\n");
	        sb.append("		<name>"+lowerName+"WS</name>\n");
	        sb.append("		<serviceClass>com."+pb.getProjectName().toLowerCase()+".webService."+tableName.toLowerCase()+".I"+lowerName+"WS</serviceClass>\n");
	        sb.append("		<implementationClass>\n");
	        sb.append("			com."+pb.getProjectName().toLowerCase()+".webService."+tableName.toLowerCase()+"."+lowerName+"WSImpl\n");
	        sb.append("		</implementationClass>\n");
	        sb.append("		<style>wrapped</style>\n");
	        sb.append("		<use>literal</use>\n");
	        sb.append("		<scope>application</scope>\n");
	        sb.append("	</service>\n");
        	

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
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"WebServices/services.xml";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        	sb.append("</beans>\n");
        	
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;      
        log.info("创建文件services.xml 成功！") ;      
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
