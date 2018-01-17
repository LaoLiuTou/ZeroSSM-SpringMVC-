package com.lt.util.General.writeFiles.Web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import com.lt.jdbc.JdbcBean;
import com.lt.util.ProjectBean;

public class WriteWeb {
	/**
	 * 创建文件
	 * 
	 * @return
	 */
	public String writeWeb(){
		
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/WebRoot/WEB-INF/web.xml";
		dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/WebRoot/WEB-INF/";
		try {
			createFiles(dirs,url,pb.getProjectName());
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
	public  void createFiles(String dirs,String url,String project) throws IOException{
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
             
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            sb.append("<web-app xmlns=\"http://java.sun.com/xml/ns/javaee\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" version=\"2.5\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee   http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd\">\n");
        	sb.append("	<filter>\n");
        	sb.append("    <filter-name>characterEncodingFilter</filter-name>\n");
        	sb.append("    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>\n");
        	sb.append("    <init-param>\n");
        	sb.append("      <param-name>encoding</param-name>\n");
        	sb.append("      <param-value>UTF-8</param-value>\n");
        	sb.append("    </init-param>\n");
        	sb.append("    <init-param>\n");
        	sb.append("      <param-name>forceEncoding</param-name>\n");
        	sb.append("      <param-value>true</param-value>\n");
        	sb.append("    </init-param>\n");
        	sb.append("	</filter>\n");
        	sb.append("	<filter-mapping>\n");
        	sb.append("    <filter-name>characterEncodingFilter</filter-name>\n");
        	sb.append("    <url-pattern>/*</url-pattern>\n");
        	sb.append("   </filter-mapping>\n");
        	sb.append("   <context-param>\n");
        	sb.append("     <param-name>contextConfigLocation</param-name>\n");
        	sb.append("     <param-value>classpath:applicationContext.xml</param-value>\n");
        	sb.append("   </context-param>\n");
        	sb.append("   <listener>\n");
        	sb.append("  <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>\n");
        	sb.append("  </listener>\n");
        	 
            sb.append("  <context-param>\n");
            sb.append("     <param-name>webAppRootKey</param-name>\n");
            sb.append("     <param-value>"+project+".root</param-value>\n");
            sb.append("   </context-param>\n");
            
            sb.append("  <servlet>\n");
            sb.append("  	<servlet-name>spring</servlet-name>\n");
            sb.append("  	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>\n");
            sb.append("  	<load-on-startup>1</load-on-startup>  \n");
            sb.append("  </servlet>\n");
            sb.append("  <servlet-mapping>\n");
            sb.append("  	<servlet-name>spring</servlet-name>\n");
            sb.append("  	<url-pattern>/</url-pattern>\n");
            sb.append("  </servlet-mapping>\n"); 
            //不使用webservice
            /*sb.append("   <servlet>\n");
            sb.append("    <servlet-name>XFireServlet</servlet-name>\n");
            sb.append("     <servlet-class>org.codehaus.xfire.transport.http.XFireConfigurableServlet</servlet-class>\n");
            sb.append("    <load-on-startup>0</load-on-startup>\n");
            sb.append("   </servlet>\n");
            sb.append("   <servlet-mapping>\n");
            sb.append("     <servlet-name>XFireServlet</servlet-name>\n");
            sb.append("    <url-pattern>/services/*</url-pattern>\n");
            sb.append("  </servlet-mapping>\n");*/
            //不设置session
            /*sb.append("  <session-config>\n");
            sb.append("     <session-timeout>30</session-timeout>\n");
            sb.append("  </session-config>\n");*/
            sb.append("  <welcome-file-list>\n");
            sb.append("     <welcome-file>index.jsp</welcome-file>\n");
            sb.append("  </welcome-file-list>\n");
            sb.append("</web-app>\n");


            out.write(sb.toString().getBytes("utf-8"));
                 
            log.info("创建文件WEB.XML 成功！") ;     
        out.close();
    }
 
}
