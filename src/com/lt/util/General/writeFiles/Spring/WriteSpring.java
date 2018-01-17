package com.lt.util.General.writeFiles.Spring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.ejb.Remove;

import com.lt.jdbc.JdbcBean;
import com.lt.util.ProjectBean;

public class WriteSpring {
	
	/**
	 * 创建文件
	 * 
	 * @return
	 */
	public String writeSpring(){
		
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/applicationContext.xml";
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
            sb.append("<beans\n");
            sb.append("xmlns=\"http://www.springframework.org/schema/beans\"\n");
            sb.append("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
            sb.append("xmlns:p=\"http://www.springframework.org/schema/p\"\n");
            sb.append("xmlns:tx=\"http://www.springframework.org/schema/tx\"\n");
            sb.append("xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n");
            sb.append("http://www.springframework.org/schema/beans/spring-beans.xsd\n");
            sb.append("http://www.springframework.org/schema/tx\n");
            sb.append("http://www.springframework.org/schema/tx/spring-tx-4.0.xsd\">\n");
            sb.append("<bean id=\"propertyConfigurer\" class=\"org.springframework.beans.factory.config.PropertyPlaceholderConfigurer\">\n");  
            sb.append("        <property name=\"location\" value=\"classpath:jdbc/jdbc.properties\"/>\n");  
            sb.append("</bean>\n");
            sb.append("<!--<bean id=\"dataSource\" class=\"org.springframework.jdbc.datasource.DriverManagerDataSource\">\n"); 
            sb.append("	<property name=\"driverClassName\" value=\"${driverClassName}\"/>\n");
            sb.append("	<property name=\"url\" value=\"${url}\"/>\n"); 
            sb.append("	<property name=\"username\" value=\"${username}\"/>\n"); 
            sb.append("	<property name=\"password\" value=\"${password}\"/>\n"); 
            sb.append("</bean>-->\n");
           
            sb.append("<bean id=\"dataSource\" class=\"org.apache.commons.dbcp.BasicDataSource\">\n"); 
            sb.append("	<property name=\"driverClassName\"><value>${driverClassName}</value></property>\n");
            sb.append("	<property name=\"url\"><value>${url}</value></property> \n");
            sb.append("	<property name=\"username\"><value>${username}</value></property>\n");
            sb.append("	<property name=\"password\"><value>${password}</value></property>\n");
            sb.append("	<property name=\"maxActive\"><value>255</value></property> \n");
            sb.append("	<property name=\"maxIdle\"><value>2</value></property>\n");
            sb.append("	<property name=\"maxWait\"><value>120000</value></property>\n");
            sb.append("</bean>\n");
            
        
            sb.append("<bean id=\"sqlSessionFactory\" class=\"org.mybatis.spring.SqlSessionFactoryBean\">\n"); 
            sb.append("	<property name=\"configLocation\"><value>classpath:Configuration.xml</value></property>\n"); 
            sb.append("	<property name=\"dataSource\"><ref bean=\"dataSource\"/></property>\n"); 
            sb.append(" </bean>\n");
            sb.append("<bean id=\"sqlSessionTemplate_app\" class=\"org.mybatis.spring.SqlSessionTemplate\">\n"); 
            sb.append("	<constructor-arg index=\"0\" ref=\"sqlSessionFactory\" />\n"); 
            sb.append("</bean>\n");
            
            
            sb.append("<!-- 该 BeanPostProcessor 将自动起作用，对标注 @Autowired 的 Bean 进行自动注入 -->\n");
            sb.append("<bean class=\"org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor\"/>\n");
            
            sb.append("<!-- 配置事务管理器 -->\n");
            sb.append("<bean id=\"transactionManager\"\n");
            sb.append("	class=\"org.springframework.jdbc.datasource.DataSourceTransactionManager\"\n");
            sb.append("	p:dataSource-ref=\"dataSource\">\n");
            sb.append("</bean>\n");
            sb.append("<!-- 配置多个事务管理器 -->\n");
            sb.append("<!--<bean id=\"tran_1\"\n");
            sb.append("	class=\"org.springframework.jdbc.datasource.DataSourceTransactionManager\"\n");
            sb.append("	p:dataSource-ref=\"dataSource\">\n");
            sb.append("	<qualifier value=\"tran_1\"/>\n");
            sb.append("</bean>\n");
            sb.append("@Transactional(\"tran_1\")\n");
            sb.append("-->\n");
            sb.append("<!-- enables scanning for @Transactional annotations -->\n");
            sb.append("<tx:annotation-driven transaction-manager=\"transactionManager\"/>\n");
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
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/applicationContext.xml";
		 
        File file=new File(url);
         
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        
        
       sb.append("<bean id=\"i"+lowerName+"Mapper\" class=\"org.mybatis.spring.mapper.MapperFactoryBean\">\n");
       sb.append("	<property name=\"mapperInterface\" value=\"com."+pb.getProjectName().toLowerCase()+".dao."+tableName.toLowerCase()+".I"+lowerName+"Mapper \"/>\n");
       sb.append("	<property name=\"sqlSessionFactory\" ref=\"sqlSessionFactory\" />\n");
       sb.append("</bean>\n");
	        
	   sb.append("<bean id=\"i"+lowerName+"Service\" class=\"com."+pb.getProjectName().toLowerCase()+".service."+tableName.toLowerCase()+"."+lowerName+"ServiceImpl\"></bean>\n");
        	

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
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/applicationContext.xml";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        	sb.append("</beans>\n");
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;      
        log.info("创建文件applicationContext.xml 成功！") ;      
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
