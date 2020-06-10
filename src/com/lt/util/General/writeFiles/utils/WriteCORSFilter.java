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

public class WriteCORSFilter {
	
	/**
	 * 添加Bean
	 * 
	 * @return
	 */
	public static String writeCORSFilter(){
		
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
			+"/utils/"+"CORSFilter.java";
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
            sb.append("import javax.servlet.Filter;\n");
            sb.append("import javax.servlet.FilterChain;\n");
            sb.append("import javax.servlet.FilterConfig;\n");
            sb.append("import javax.servlet.ServletException;\n");
            sb.append("import javax.servlet.ServletRequest;\n");
            sb.append("import javax.servlet.ServletResponse;\n");
            sb.append("import javax.servlet.http.HttpServletRequest;\n");
            sb.append("import javax.servlet.http.HttpServletResponse;\n\n");
            sb.append("public class CORSFilter implements Filter { \n\n"); 
            sb.append("	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {\n");  
            sb.append("		HttpServletRequest request = (HttpServletRequest) req;\n");
            sb.append("		HttpServletResponse response = (HttpServletResponse) res;  \n");
            sb.append("		request.setCharacterEncoding(\"UTF-8\"); \n");
            sb.append("		response.setCharacterEncoding(\"UTF-8\"); \n");
            //sb.append("		response.setContentType(\"text/html;charset=UTF-8\"); \n");
            sb.append("		//CORS跨域\n");
            sb.append("		response.setHeader(\"Access-Control-Allow-Origin\", \"*\");  \n");
            sb.append("		response.setHeader(\"Access-Control-Allow-Methods\", \"POST, GET, OPTIONS, DELETE\");\n");  
            sb.append("		response.setHeader(\"Access-Control-Max-Age\", \"3600\");  \n");
            sb.append("		response.setHeader(\"Access-Control-Allow-Headers\",\"x-requested-with,content-type,token,timesamp,sign,source\");\n");
            sb.append("		chain.doFilter(request, response);\n");
            sb.append("	} \n\n"); 
            sb.append("	public void init(FilterConfig filterConfig) {}\n\n");  
            sb.append("	public void destroy() {}\n\n");  
            sb.append("}\n\n"); 
			
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
