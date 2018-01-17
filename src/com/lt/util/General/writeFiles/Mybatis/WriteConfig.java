package com.lt.util.General.writeFiles.Mybatis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import com.lt.util.ProjectBean;

public class WriteConfig {
	
	/**
	 * 创建文件
	 * 
	 * @return
	 */
	public String writeSqlmap(){
		
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/Configuration.xml";
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
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            sb.append("<!DOCTYPE configuration PUBLIC \"-//mybatis.org//DTD Config 3.0//EN\"\n");
            sb.append("    \"http://mybatis.org/dtd/mybatis-3-config.dtd\">\n");
            sb.append("<configuration>\n");
            sb.append("	<settings>\n");
            
            sb.append("		<setting name=\"logImpl\" value=\"STDOUT_LOGGING\"/> \n");
            sb.append("		<setting name=\"cacheEnabled\" value=\"true\"/> \n");
            sb.append("		<setting name=\"lazyLoadingEnabled\" value=\"true\"/> \n");
            sb.append("		<setting name=\"multipleResultSetsEnabled\" value=\"true\"/> \n");
            sb.append("		<setting name=\"useColumnLabel\" value=\"true\"/> \n");
            sb.append("		<setting name=\"useGeneratedKeys\" value=\"false\"/> \n");
            sb.append("		<setting name=\"autoMappingBehavior\" value=\"PARTIAL\"/> \n");
            sb.append("		<setting name=\"defaultExecutorType\" value=\"SIMPLE\"/> \n");
            sb.append("		<setting name=\"defaultStatementTimeout\" value=\"25\"/> \n");
            sb.append("		<setting name=\"safeRowBoundsEnabled\" value=\"false\"/> \n");
            sb.append("		<setting name=\"mapUnderscoreToCamelCase\" value=\"false\"/> \n");
            sb.append("		<setting name=\"localCacheScope\" value=\"SESSION\"/> \n");
            sb.append("		<setting name=\"jdbcTypeForNull\" value=\"OTHER\"/> \n");
            sb.append("		<setting name=\"lazyLoadTriggerMethods\" value=\"equals,clone,hashCode,toString\"/> \n");
            sb.append("	</settings> \n");
            
      	    
            out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;     
        out.close();
    }
 
	/**
	 * typeAliases  head
	 * 
	 * @return
	 */
	public  void appendAliaseshead() throws IOException{
		Logger log = Logger.getLogger("ZeroLog");   
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/Configuration.xml";
		
		File file=new File(url);
		
		if(!file.exists())
			file.createNewFile(); 
		FileOutputStream out=new FileOutputStream(file,true); 
		StringBuffer sb=new StringBuffer();
		sb.append("	<typeAliases>\n");
		out.write(sb.toString().getBytes("utf-8"));
		log.info(sb.toString()) ;       
		out.close();
	}
   
	/**
	 * 动态添加 typeAlias
	 * 
	 * @return
	 */
	public  void appendAliasesFiles(String tableName) throws IOException{
		Logger log = Logger.getLogger("ZeroLog");   
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/Configuration.xml";
		 
        File file=new File(url);
         
        if(!file.exists())
            file.createNewFile();
        String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
          sb.append("		<typeAlias alias=\""+toUpperCaseFirstOne(tableName)+"\" type=\"com."+pb.getProjectName().toLowerCase()+".model."+tableName.toLowerCase()+"."+lowerName+"\" /> \n");
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;       
        out.close();
    }
	/**
	 * typeAlias end
	 * 
	 * @return
	 */
	public  void appendAliasesend() throws IOException{
		Logger log = Logger.getLogger("ZeroLog");   
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/Configuration.xml";
		File file=new File(url);
		if(!file.exists())
			file.createNewFile();
		FileOutputStream out=new FileOutputStream(file,true); 
		StringBuffer sb=new StringBuffer();
		sb.append("	</typeAliases>\n");
		out.write(sb.toString().getBytes("utf-8"));
		log.info(sb.toString()) ;       
		out.close();
	}
	
	
	/**
	 * mappers  head
	 * 
	 * @return
	 */
	public  void appendMappershead() throws IOException{
		Logger log = Logger.getLogger("ZeroLog");   
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/Configuration.xml";
		
		File file=new File(url);
		
		if(!file.exists())
			file.createNewFile(); 
		FileOutputStream out=new FileOutputStream(file,true); 
		StringBuffer sb=new StringBuffer();
		sb.append("	<mappers>\n");
		out.write(sb.toString().getBytes("utf-8"));
		log.info(sb.toString()) ;       
		out.close();
	}
   
	/**
	 * 动态添加 mappers
	 * 
	 * @return
	 */
	public  void appendMappersFiles(String tableName) throws IOException{
		Logger log = Logger.getLogger("ZeroLog");   
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/Configuration.xml";
		 
        File file=new File(url);
         
        if(!file.exists())
            file.createNewFile();
        String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
          sb.append("		<mapper resource=\"com/"+pb.getProjectName().toLowerCase()+"/model/"+tableName.toLowerCase()+"/"+lowerName+".xml\"/> \n");
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;       
        out.close();
    }
	/**
	 * typeAlias end
	 * 
	 * @return
	 */
	public  void appendMappersend() throws IOException{
		Logger log = Logger.getLogger("ZeroLog");   
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/Configuration.xml";
		File file=new File(url);
		if(!file.exists())
			file.createNewFile();
		FileOutputStream out=new FileOutputStream(file,true); 
		StringBuffer sb=new StringBuffer();
		sb.append("	</mappers>  \n");
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
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/Configuration.xml";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("</configuration>\n");
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;      
        log.info("创建文件Configuration.xml 成功！") ;      
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
