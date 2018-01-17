package com.lt.util.General.writeFiles.WebService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import com.lt.util.ProjectBean;

public class WriteWebService {
	/**
	 * 添加webservice
	 * 
	 * @return
	 */
	public String writeWebService(String tableName,String pKey){
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
			+"/webService/"+tableName.toLowerCase()+"/I"+lowerName+"WS.java";
		dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
			+"/webService/"+tableName.toLowerCase()+"/";
		try {
			createFiles(dirs,url,tableName,pKey);
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
	public  void createFiles(String dirs,String url,String tableName,String pKey) throws IOException{
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
            
            sb.append("package com."+pb.getProjectName().toLowerCase()+".webService."+tableName.toLowerCase()+";\n");
            sb.append("public interface I"+lowerName+"WS{\n");
             
            if(!pKey.equals("")&&pKey!=null){
	            sb.append(" 	/**\n");
	            sb.append(" 	 * 根据ID查询\n");
	            sb.append(" 	 * @return\n");
	            sb.append(" 	 */\n");
	            sb.append("	public String get"+tableName+"ById(String id);\n\n");
	            
	            sb.append("		/**\n");
	            sb.append("		 * 根据ID删除\n");
	            sb.append("		 * \n");
	            sb.append("		 * @return\n");
	            sb.append("		 */\n");
	            sb.append("	public String delete"+tableName+"ById(String id);\n\n");
	           
	            
            }
            sb.append("		/**\n");
            sb.append("		 * 根据查询条件查询\n");
            sb.append("		 * @return\n");
            sb.append("		 */\n");
            sb.append("		@SuppressWarnings(\"unchecked\")\n");
            sb.append("	public String get"+tableName+"ByParam(");
        	
            out.write(sb.toString().getBytes("utf-8"));
            log.info(sb.toString()) ;      
            log.info("创建文件"+lowerName+"WS.java成功！") ;        
        out.close();
    }
 
	/**
	 * 动态 添加select 函数
	 * 
	 * @return
	 */
	public  void addSelectByParam1(String tableName,String colName,String type,boolean isEnd) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/webService/"+tableName.toLowerCase()+"/I"+lowerName+"WS.java";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        if(type.toUpperCase().equals("DATE")||type.toUpperCase().equals("DATETIME")){
        	sb.append("String "+colName.toLowerCase()+"From,");
        	sb.append("String "+colName.toLowerCase()+"To,");
        }
        if(isEnd){
        	sb.append("String "+colName.toLowerCase()+",");
        }
        else{
        	sb.append("String "+colName.toLowerCase()+",");
        }
         
        
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	 
	/**
	 * 动态 添加select 函数
	 * 
	 * @return
	 */
	public  void addSelectByParam2(String tableName) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/webService/"+tableName.toLowerCase()+"/I"+lowerName+"WS.java";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("int page,int size);\n\n");
        
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	 
	/**
	 * 动态 添加Insert 函数
	 * 
	 * @return
	 */
	public  void addInsert(String tableName) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/webService/"+tableName.toLowerCase()+"/I"+lowerName+"WS.java";
		
		File file=new File(url);
		
		if(!file.exists())
			file.createNewFile();
		
		FileOutputStream out=new FileOutputStream(file,true); 
		StringBuffer sb=new StringBuffer();
		
		sb.append("		/**\n");
		sb.append("		 * 添加\n");
		sb.append("		 * @return\n");
		sb.append("		 * @throws ParseException\n"); 
		sb.append("		 */\n");
		sb.append("	public String add"+tableName+"(");

		out.write(sb.toString().getBytes("utf-8"));
		log.info(sb.toString()) ;        
		out.close();
	}
	
	/**
	 * 动态 添加 Update insert 函数的参数
	 * 
	 * @return
	 */
	public  void addParam(String tableName,String colName,String type,boolean isEnd) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/webService/"+tableName.toLowerCase()+"/I"+lowerName+"WS.java";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        if(isEnd){
        	sb.append("String "+colName.toLowerCase()+");\n");
        }
        else{
        	sb.append("String "+colName.toLowerCase()+",");
        }
         
        
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	
	/**
	 * 动态 添加insert 函数
	 * 
	 * @return
	 */
	public  void addUpdate(String tableName) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/webService/"+tableName.toLowerCase()+"/I"+lowerName+"WS.java";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
 
        sb.append("		/**\n");
		sb.append("		 * 更新\n");
		sb.append("		 * @return\n");
		sb.append("		 */\n");
		sb.append("	public String update"+tableName+"(");
        
        
        
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	
	/**
	 * 动态 添加update 函数
	 * 
	 * @return
	 */
	public  void addEnd(String tableName) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/webService/"+tableName.toLowerCase()+"/I"+lowerName+"WS.java";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
         
		sb.append("}\n\n");
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
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

