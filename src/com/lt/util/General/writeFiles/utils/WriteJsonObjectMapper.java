package com.lt.util.General.writeFiles.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
 

import com.lt.util.ProjectBean;

public class WriteJsonObjectMapper {
	
	/**
	 * 添加Bean
	 * 
	 * @return
	 */
	public static String writeJsonObjectMapper(){
		
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
			+"/utils/"+"JsonObjectMapper.java";
		dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
			+"/utils/";
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
	 * 添加
	 * 
	 * @return
	 */
	public static  void createFiles(String dirs,String url) throws IOException{
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
            
            sb.append("package com."+pb.getProjectName().toLowerCase()+".utils;\n\n"); 
            sb.append("import java.io.IOException;\n");
            sb.append("import com.fasterxml.jackson.core.JsonGenerator;\n");
            sb.append("import com.fasterxml.jackson.core.JsonProcessingException;\n");
            sb.append("import com.fasterxml.jackson.databind.JsonSerializer;\n");
            sb.append("import com.fasterxml.jackson.databind.ObjectMapper;\n");
            sb.append("import com.fasterxml.jackson.databind.SerializerProvider;\n\n");

            sb.append("/**\n");
            sb.append("* @description: 转换null对象为空字符串\n");
            sb.append("*/ \n");
            sb.append("public class JsonObjectMapper extends ObjectMapper { \n");
            sb.append("	private static final long serialVersionUID = 1L; \n");
            sb.append("	public JsonObjectMapper() { \n");
            sb.append("		super(); \n");
            sb.append("		// 空值处理为空串 \n");
            sb.append("		this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() { \n");
            sb.append("		@Override \n");
            sb.append("		public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {\n"); 
            sb.append("			jg.writeString(\"\"); \n");
            sb.append("		} \n");
            sb.append("	}); \n");
            sb.append("	} \n");
            sb.append("} \n");
			
            out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ; 
        log.info("创建文件"+"FileUpload.java 成功！") ; 
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
