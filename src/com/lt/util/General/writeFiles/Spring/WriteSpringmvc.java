package com.lt.util.General.writeFiles.Spring;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import com.lt.jdbc.JdbcBean;
import com.lt.util.ProjectBean;

public class WriteSpringmvc {
	/**
	 * 创建文件
	 * 
	 * @return
	 */
	public String writeSpringmvc(){
		
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/WebRoot/WEB-INF/spring-servlet.xml";
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
		ProjectBean pb=new ProjectBean();
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
            sb.append("<beans xmlns=\"http://www.springframework.org/schema/beans\"\n");
            sb.append("	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
            sb.append("	xmlns:context=\"http://www.springframework.org/schema/context\"\n");
            sb.append("	xmlns:mvc=\"http://www.springframework.org/schema/mvc\"\n");
            sb.append("	xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd\n");
            sb.append("		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd\n");
            sb.append("		http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.0.xsd\">\n");
            sb.append("	<!-- 自动扫描controller包下的所有类，使其认为是spring mvc的控制器 路径即为类路径 -->\n");
            sb.append("	<context:component-scan base-package=\"com."+pb.getProjectName().toLowerCase()+".controller\"></context:component-scan>\n");
            sb.append("	<mvc:annotation-driven>  \n");
            sb.append("	<!-- 处理responseBody 里面日期类型 -->\n");
            sb.append("		<mvc:message-converters>  \n");
            sb.append("			<bean class=\"org.springframework.http.converter.json.MappingJackson2HttpMessageConverter\">  \n");
            sb.append("				<property name=\"objectMapper\"> \n");
            sb.append("					<bean class=\"com.fasterxml.jackson.databind.ObjectMapper\">\n");
            sb.append("						<property name=\"dateFormat\">\n");
            sb.append("							<bean class=\"java.text.SimpleDateFormat\">\n");
            sb.append("								<constructor-arg type=\"java.lang.String\" value=\"yyyy-MM-dd HH:mm:ss\" />\n");
            sb.append("							</bean>\n");
            sb.append("						</property>\n");
            sb.append("					</bean>\n");
            sb.append("				</property>\n");
            sb.append("			</bean>\n");
            sb.append("		</mvc:message-converters>\n");
            sb.append("	</mvc:annotation-driven>\n");
             
            sb.append("	<!-- 配置视图解析器 如何把handler 方法返回值解析为实际的物理视图  根据控制器返回的字符串拼接成jsp路径：/WEB-INF/jsp/xx.jsp -->\n");
            sb.append("	<bean class=\"org.springframework.web.servlet.view.InternalResourceViewResolver\">\n");
            sb.append("		<property name = \"prefix\" value=\"/WEB-INF/jsp/\"></property>\n");
            sb.append("		<property name = \"suffix\" value = \".jsp\"></property>\n");
            sb.append("	</bean>\n");
            sb.append("</beans>\n");

          

            out.write(sb.toString().getBytes("utf-8"));
                 
            log.info("创建文件WEB.XML 成功！") ;     
        out.close();
    }
 
}
