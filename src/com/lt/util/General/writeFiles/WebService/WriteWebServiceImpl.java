package com.lt.util.General.writeFiles.WebService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
 

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lt.util.ProjectBean;

public class WriteWebServiceImpl {
	/**
	 * 添加WsImpl
	 * 
	 * @return
	 */
	public String writeWSImpl(String tableName,String pKey){
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
			+"/webService/"+tableName.toLowerCase()+"/"+lowerName+"WSImpl.java";
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
            sb.append("import java.text.ParseException;\n");
            sb.append("import java.text.SimpleDateFormat;\n");
            sb.append("import java.util.HashMap;\n");
            sb.append("import java.util.List;\n");
            sb.append("import java.util.Map;\n");
            sb.append("import java.util.Date;\n");
            sb.append("import net.sf.json.JSONArray;\n");
            sb.append("import net.sf.json.JSONObject;\n");
            sb.append("import net.sf.json.JsonConfig;\n");
            sb.append("import net.sf.json.processors.JsonValueProcessor;\n");
            sb.append("import org.apache.log4j.Logger;\n");
            sb.append("import org.springframework.context.ApplicationContext;\n");
            sb.append("import org.springframework.context.support.ClassPathXmlApplicationContext;\n");
            sb.append("import com."+pb.getProjectName().toLowerCase()+".service."+tableName.toLowerCase()+".I"+lowerName+"Service;\n");
            sb.append("import com."+pb.getProjectName().toLowerCase()+".model."+tableName.toLowerCase()+"."+lowerName+";\n");
            sb.append("public class "+lowerName+"WSImpl implements I"+lowerName+"WS {\n");
            if(!pKey.equals("")&&pKey!=null){
	            sb.append(" 	/**\n");
	            sb.append(" 	 * 根据ID查询\n");
	            sb.append(" 	 * @return\n");
	            sb.append(" 	 */\n");
	            sb.append("	public String get"+tableName+"ById(String id) {\n");
	            sb.append("		Logger log = Logger.getLogger(\""+pb.getProjectName()+"Logger\");\n");   
	            sb.append("		StringBuffer msg = new StringBuffer(\"{\\\"state\\\":\");\n");  
	            sb.append("		ApplicationContext context = new ClassPathXmlApplicationContext(\"applicationContext.xml\");\n"); 
	            sb.append("		I"+lowerName+"Service i"+lowerName+"Service = (I"+lowerName+"Service)context.getBean(\"i"+lowerName+"Service\");\n");  
	            sb.append("		"+lowerName+" "+tableName.toLowerCase()+"=new "+lowerName+"();\n");
	            sb.append("		try {\n");
	            sb.append("			final SimpleDateFormat sdf = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");  \n");
	            sb.append("			"+tableName.toLowerCase()+"=i"+lowerName+"Service.select"+tableName+"ById(id);\n");
	            sb.append("			JsonConfig jsonConfig = new JsonConfig();\n");
	    		sb.append("			jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {\n");
	    		sb.append("				public Object processArrayValue(Object value, JsonConfig jsonConfig) {\n");
	    		sb.append("					return value;  \n");
	    		sb.append("				} \n");
	    		sb.append("			public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) { \n");
	    		sb.append("				if(value instanceof Date){ \n");
	    		sb.append("					return sdf.format((Date)value);\n");
	    		sb.append("				}\n");
	    		sb.append("				return value; \n");
	    		sb.append("			}\n");
	    		sb.append("			});\n");
	            sb.append("			msg.append(\"\\\"success\\\",\\\"msg\\\":\");\n");
	            sb.append("			msg.append(JSONObject.fromObject("+tableName.toLowerCase()+", jsonConfig));\n");
	            sb.append("		} catch (Exception e) {\n");
	            sb.append("			msg.append(\"\\\"failure\\\",\\\"msg\\\":\");\n");
	            sb.append("			msg.append(\"\\\"查询失败.\\\"\");\n");
	            sb.append("			log.info(\"查询失败.ID：\"+id+\";E:\"+e);\n");
	            sb.append("			e.printStackTrace();\n");
	            sb.append("		}\n");	
	            sb.append("		msg.append(\"}\");\n");
	            sb.append("		return msg.toString();\n");
	            sb.append("	}\n\n\n");
	            
	            sb.append("		/**\n");
	            sb.append("		 * 根据ID删除\n");
	            sb.append("		 * \n");
	            sb.append("		 * @return\n");
	            sb.append("		 */\n");
	            sb.append("	public String delete"+tableName+"ById(String id) {\n");
	            sb.append("		Logger log = Logger.getLogger(\""+pb.getProjectName()+"Logger\");\n");  
	            sb.append("		StringBuffer msg = new StringBuffer(\"{\\\"state\\\":\");\n");  
	            sb.append("		ApplicationContext context = new ClassPathXmlApplicationContext(\"applicationContext.xml\");\n"); 
	            sb.append("		I"+lowerName+"Service i"+lowerName+"Service = (I"+lowerName+"Service)context.getBean(\"i"+lowerName+"Service\");\n");  
	            sb.append("		try {\n");
	            sb.append("			i"+lowerName+"Service.delete"+tableName+"(id);\n");
	            sb.append("			msg.append(\"\\\"success\\\",\\\"msg\\\":\");\n");
	            sb.append("			msg.append(\"\\\"删除成功.ID：\"+id+\"\\\"\");\n");
	            sb.append("		} catch (Exception e) {\n");
	            sb.append("			msg.append(\"\\\"failure\\\",\\\"msg\\\":\");\n");
	            sb.append("			msg.append(\"\\\"删除失败.\\\"\");\n");
	            sb.append("			log.info(\"删除失败.ID：\"+id+\";E:\"+e);\n");
	            sb.append("			e.printStackTrace();\n");
	            sb.append("		}\n");	
	            sb.append("		msg.append(\"}\");\n");
	            sb.append("		return msg.toString();\n");
	            sb.append("	}\n\n\n");
	            
            }
            sb.append("		/**\n");
            sb.append("		 * 根据查询条件查询\n");
            sb.append("		 * @return\n");
            sb.append("		 */\n");
            sb.append("		@SuppressWarnings(\"unchecked\")\n");
            sb.append("	public String get"+tableName+"ByParam(");
        	
            out.write(sb.toString().getBytes("utf-8"));
            log.info(sb.toString()) ;      
            log.info("创建文件"+lowerName+"WSImpl.java成功！") ;        
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
		+"/webService/"+tableName.toLowerCase()+"/"+lowerName+"WSImpl.java";
		 
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
		+"/webService/"+tableName+"/"+toUpperCaseFirstOne(tableName.toLowerCase())+"WSImpl.java";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("int page,int size){\n");
        sb.append("		Logger log = Logger.getLogger(\""+pb.getProjectName()+"Logger\");\n");   
        sb.append("		StringBuffer msg = new StringBuffer(\"{\\\"state\\\":\");\n"); 
        sb.append("		ApplicationContext context = new ClassPathXmlApplicationContext(\"applicationContext.xml\");\n"); 
        sb.append("		I"+lowerName+"Service i"+lowerName+"Service = (I"+lowerName+"Service)context.getBean(\"i"+lowerName+"Service\");\n"); 
        sb.append("		List<"+lowerName+"> list;\n");	
        sb.append("		try {\n");
        sb.append("		final SimpleDateFormat sdf = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");  \n");
        sb.append("			Map paramMap = new HashMap();\n");
        
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	/**
	 * 动态 添加select 函数
	 * 
	 * @return
	 */
	public  void addSelectByParam3(String tableName,String colName,String type) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/webService/"+tableName.toLowerCase()+"/"+lowerName+"WSImpl.java";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        if(type.toUpperCase().equals("DATE")||type.toUpperCase().equals("DATETIME")){
        	
        	sb.append("			if("+colName.toLowerCase()+"From!=null&&!"+colName.toLowerCase()+"From.equals(\"\"))\n");
        	sb.append("			paramMap.put(\""+colName.toLowerCase()+"From\", sdf.parse("+colName.toLowerCase()+"From));\n");
        	sb.append("			if("+colName.toLowerCase()+"To!=null&&!"+colName.toLowerCase()+"To.equals(\"\"))\n");
        	sb.append("			paramMap.put(\""+colName.toLowerCase()+"To\", sdf.parse("+colName.toLowerCase()+"To));\n");
        }
        sb.append("			paramMap.put(\""+colName.toLowerCase()+"\", "+colName.toLowerCase()+");\n"); 
        
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	 
	/**
	 * 动态 添加select 函数
	 * 
	 * @return
	 */
	public  void addSelectByParam4(String tableName) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/webService/"+tableName.toLowerCase()+"/"+lowerName+"WSImpl.java";
		
		File file=new File(url);
		
		if(!file.exists())
			file.createNewFile();
		
		FileOutputStream out=new FileOutputStream(file,true); 
		StringBuffer sb=new StringBuffer();
		
		sb.append("			paramMap.put(\"fromPage\",(page-1)*size);\n"); 
		sb.append("			paramMap.put(\"toPage\",page*size);\n"); 
		sb.append("			list=i"+lowerName+"Service.select"+tableName+"ByParam(paramMap);\n");
		sb.append("			JsonConfig jsonConfig = new JsonConfig();\n");
		sb.append("			jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {\n");
		sb.append("				public Object processArrayValue(Object value, JsonConfig jsonConfig) {\n");
		sb.append("					return value;  \n");
		sb.append("				} \n");
		sb.append("			public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) { \n");
		sb.append("				if(value instanceof Date){ \n");
		sb.append("					return sdf.format((Date)value);\n");
		sb.append("				}\n");
		sb.append("				return value; \n");
		sb.append("			}\n");
		sb.append("			});\n");
		sb.append("			msg.append(\"\\\"success\\\",\\\"msg\\\":\");\n");
		sb.append("			msg.append(JSONArray.fromObject(list, jsonConfig));\n");
		sb.append("		} catch (Exception e) {\n");
		sb.append("			msg.append(\"\\\"failure\\\",\\\"msg\\\":\");\n");
		sb.append("			msg.append(\"\\\"查询失败.\\\"\");\n");
		sb.append("			log.info(\"查询失败.\"+e);\n");
		sb.append("			e.printStackTrace();\n");
		sb.append("		}\n");	
		sb.append("		msg.append(\"}\");\n");
		sb.append("		return msg.toString();\n");
		sb.append("	}\n");
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
		+"/webService/"+tableName.toLowerCase()+"/"+lowerName+"WSImpl.java";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        if(isEnd){
        	sb.append("String "+colName.toLowerCase()+"");
        }
        else{
        	sb.append("String "+colName.toLowerCase()+",");
        }
         
        
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	/**
	 *  添加spring 配置 
	 * 
	 * @return
	 */
	public  void addSpring(String tableName) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/webService/"+tableName.toLowerCase()+"/"+lowerName+"WSImpl.java";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        
        sb.append("){\n");
        sb.append("		Logger log = Logger.getLogger(\""+pb.getProjectName()+"Logger\");\n");  
        sb.append("		StringBuffer msg = new StringBuffer(\"{\\\"state\\\":\");\n");  
        sb.append("		ApplicationContext context = new ClassPathXmlApplicationContext(\"applicationContext.xml\");\n"); 
        sb.append("		I"+lowerName+"Service i"+lowerName+"Service = (I"+lowerName+"Service)context.getBean(\"i"+lowerName+"Service\");\n");  
        sb.append("		SimpleDateFormat sdf = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");\n");
        sb.append("		"+lowerName+" "+tableName.toLowerCase()+"=new "+lowerName+"();\n");
        
        
        
        
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	
	/**
	 * 动态 添加 Bean 属性
	 * 
	 * @return
	 */
	public  void addClassParam(String tableName,String colName,String type) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/webService/"+tableName.toLowerCase()+"/"+lowerName+"WSImpl.java";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        
        if(type.toUpperCase().equals("TINYINT")||type.toUpperCase().equals("SMALLINT")||type.toUpperCase().equals("MEDIUMINT")||
        		type.toUpperCase().equals("INT")||type.toUpperCase().equals("BIGINT")){
        	sb.append("		if ("+colName.toLowerCase()+" != null && !"+colName.toLowerCase()+".equals(\"\"))\n");  
            sb.append("			"+tableName.toLowerCase()+".set"+toUpperCaseFirstOne(colName.toLowerCase())+"(Long.parseLong("+colName.toLowerCase()+"));\n"); 
        }
        else if(type.toUpperCase().equals("DATE")||type.toUpperCase().equals("DATETIME")){
        	sb.append("		try {\n");
        	sb.append("		if ("+colName.toLowerCase()+" != null && !"+colName.toLowerCase()+".equals(\"\"))\n");   
        	sb.append("			"+tableName.toLowerCase()+".set"+toUpperCaseFirstOne(colName.toLowerCase())+"(sdf.parse("+colName.toLowerCase()+"));\n");
        	sb.append("		} catch (ParseException e1) {\n");
        	sb.append("			e1.printStackTrace();\n");
        	sb.append("		}\n"); 
        }
        else{
        	sb.append("		"+tableName.toLowerCase()+".set"+toUpperCaseFirstOne(colName.toLowerCase())+"("+colName.toLowerCase()+");\n"); 
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
	public  void addInsert(String tableName) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/webService/"+tableName.toLowerCase()+"/"+lowerName+"WSImpl.java";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        
        sb.append("		try {\n");
        sb.append("			int result = Integer.parseInt(i"+lowerName+"Service.add"+tableName+"("+tableName.toLowerCase()+").toString());\n");
        sb.append("			msg.append(\"\\\"success\\\",\\\"msg\\\":\");\n");
        //sb.append("			msg.append(\"\\\"添加成功.\\\"\");\n");
        sb.append("			msg.append(\"\\\"\"+result+\"\\\"\");\n");
        sb.append("		} catch (Exception e) {\n");
        sb.append("			msg.append(\"\\\"failure\\\",\\\"msg\\\":\");\n");
        sb.append("			msg.append(\"\\\"添加失败.\\\"\");\n");
        sb.append("			log.info(\"添加失败.\"+e);\n");
        sb.append("			e.printStackTrace();\n");
        sb.append("		}\n");	
        sb.append("		msg.append(\"}\");\n");
		sb.append("		return msg.toString();\n");
		sb.append("	}\n");
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
	public  void addUpdate(String tableName) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/webService/"+tableName.toLowerCase()+"/"+lowerName+"WSImpl.java";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        
        sb.append("		try {\n");
        sb.append("			i"+lowerName+"Service.update"+tableName+"("+tableName.toLowerCase()+");\n");
        sb.append("			msg.append(\"\\\"success\\\",\\\"msg\\\":\");\n");
        sb.append("			msg.append(\"\\\"更新成功.\\\"\");\n");
        sb.append("		} catch (Exception e) {\n");
        sb.append("			msg.append(\"\\\"failure\\\",\\\"msg\\\":\");\n");
        sb.append("			msg.append(\"\\\"更新失败.\\\"\");\n");
        sb.append("			log.info(\"更新失败.\"+e);\n");
        sb.append("			e.printStackTrace();\n");
        sb.append("		}\n");	
        sb.append("		msg.append(\"}\");\n");
		sb.append("		return msg.toString();\n");
		sb.append("	}\n\n");
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

