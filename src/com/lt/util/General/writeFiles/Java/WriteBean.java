package com.lt.util.General.writeFiles.Java;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.format.annotation.DateTimeFormat;

import com.lt.util.ProjectBean;

public class WriteBean {
	
	/**
	 * 添加Bean
	 * 
	 * @return
	 */
	public String writeBean(String tableName){
		
		String status="success";
		String url,dirs;
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
			+"/model/"+tableName.toLowerCase()+"/"+lowerName+".java";
		dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
			+"/model/"+tableName.toLowerCase()+"/";
		try {
			createFiles(dirs,url,tableName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			status="failure";
			e.printStackTrace();
		}
		 
		return status;
	}
	/**
	 * 添加
	 * 
	 * @return
	 */
	public  void createFiles(String dirs,String url,String tableName) throws IOException{
		Logger log = Logger.getLogger("ZeroLog");   
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
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
            
            sb.append("package com."+pb.getProjectName().toLowerCase()+".model."+tableName.toLowerCase()+";\n"); 
            sb.append("import java.util.Date;\n");
            sb.append("import org.springframework.format.annotation.DateTimeFormat;\n");
            sb.append("/**\n");
            sb.append(" * @author LT\n");
            sb.append(" */\n");
            sb.append("public class "+lowerName+" {\n\n");
            
            
            
            out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;     
        out.close();
    }
 
	/**
	 * 动态添加 get set
	 * 
	 * @return
	 */
	public  void appendFiles(String tableName,String colName,String comments,String type) throws IOException{
		Logger log = Logger.getLogger("ZeroLog");   
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/model/"+tableName.toLowerCase()+"/"+lowerName+".java";
		 
        File file=new File(url);
         
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        if(type.toUpperCase().equals("TINYINT")||type.toUpperCase().equals("SMALLINT")||type.toUpperCase().equals("MEDIUMINT")||
        		type.toUpperCase().equals("INT")||type.toUpperCase().equals("BIGINT")){
	        sb.append("	/** "+comments+" */\n"); 
	        sb.append("	private  Long "+colName.toLowerCase()+";\n");
	        sb.append("	public Long get"+toUpperCaseFirstOne(colName.toLowerCase())+"() {\n");
	        sb.append("		return "+colName.toLowerCase()+";\n");
	        sb.append("	}\n");
	        sb.append("	public void set"+toUpperCaseFirstOne(colName.toLowerCase())+"(Long "+colName.toLowerCase()+") {\n");
	        sb.append("		this."+colName.toLowerCase()+" = "+colName.toLowerCase()+";\n");
	        sb.append("	}\n");
        }
        else if(type.toUpperCase().equals("DATE")){
	        sb.append("	/** "+comments+" */\n"); 
	        sb.append("	@DateTimeFormat(pattern=\"yyyy-MM-dd\")\n");
	        sb.append("	private  Date "+colName.toLowerCase()+";\n");
	        sb.append("	public Date get"+toUpperCaseFirstOne(colName.toLowerCase())+"() {\n");
	        sb.append("		return "+colName.toLowerCase()+";\n");
	        sb.append("	}\n");
	        sb.append("	public void set"+toUpperCaseFirstOne(colName.toLowerCase())+"(Date "+colName.toLowerCase()+") {\n");
	        sb.append("		this."+colName.toLowerCase()+" = "+colName.toLowerCase()+";\n");
	        sb.append("	}\n");
        	
        }
        else if(type.toUpperCase().equals("DATETIME")){
	        sb.append("	/** "+comments+" */\n"); 
	        sb.append("	@DateTimeFormat(pattern=\"yyyy-MM-dd HH:mm:ss\")\n");
	        sb.append("	private  Date "+colName.toLowerCase()+";\n");
	        sb.append("	public Date get"+toUpperCaseFirstOne(colName.toLowerCase())+"() {\n");
	        sb.append("		return "+colName.toLowerCase()+";\n");
	        sb.append("	}\n");
	        sb.append("	public void set"+toUpperCaseFirstOne(colName.toLowerCase())+"(Date "+colName.toLowerCase()+") {\n");
	        sb.append("		this."+colName.toLowerCase()+" = "+colName.toLowerCase()+";\n");
	        sb.append("	}\n");
        	
        }
        else {
	        sb.append("	/** "+comments+" */\n"); 
	        sb.append("	private  String "+colName.toLowerCase()+";\n");
	        sb.append("	public String get"+toUpperCaseFirstOne(colName.toLowerCase())+"() {\n");
	        sb.append("		return "+colName.toLowerCase()+";\n");
	        sb.append("	}\n");
	        sb.append("	public void set"+toUpperCaseFirstOne(colName.toLowerCase())+"(String "+colName.toLowerCase()+") {\n");
	        sb.append("		this."+colName.toLowerCase()+" = "+colName.toLowerCase()+";\n");
	        sb.append("	}\n");
        	
        }
        
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;       
        out.close();
    }
	
	/**
	 * 添加结尾
	 * 
	 * @return
	 */
	public  void addEnd(String tableName) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/model/"+tableName.toLowerCase()+"/"+lowerName+".java"; 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("\n\n\n}\n");
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;      
        log.info("创建文件"+tableName+".java 成功！") ;      
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
