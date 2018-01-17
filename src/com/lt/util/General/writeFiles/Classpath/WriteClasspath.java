package com.lt.util.General.writeFiles.Classpath;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import com.lt.jdbc.JdbcBean;
import com.lt.util.ProjectBean;

public class WriteClasspath {

	/**
	 * 创建文件
	 * 
	 * @return
	 */
	public String writeClasspath(){
		
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/.classpath";
		dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/";
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
            sb.append("<classpath>\n");
            sb.append("<classpathentry kind=\"src\" path=\"src\"/>\n");
            sb.append("<classpathentry kind=\"con\" path=\"org.eclipse.jdt.launching.JRE_CONTAINER\"/>\n");
            sb.append("<classpathentry kind=\"con\" path=\"melibrary.com.genuitec.eclipse.j2eedt.core.MYECLIPSE_JAVAEE_5_CONTAINER\"/>\n");
            sb.append("<classpathentry kind=\"con\" path=\"org.eclipse.jst.j2ee.internal.web.container\"/>\n");
            sb.append("<classpathentry kind=\"con\" path=\"org.eclipse.jst.j2ee.internal.module.container\"/>\n");
            sb.append("<classpathentry kind=\"output\" path=\"WebRoot/WEB-INF/classes\"/>\n");
           
            sb.append("</classpath>\n\n\n");

            out.write(sb.toString().getBytes("utf-8"));
                 
            log.info("创建文件.classpath 成功！") ;     
        out.close();
    }
 
}
