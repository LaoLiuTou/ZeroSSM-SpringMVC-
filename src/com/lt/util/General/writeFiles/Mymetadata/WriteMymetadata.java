package com.lt.util.General.writeFiles.Mymetadata;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import com.lt.jdbc.JdbcBean;
import com.lt.util.ProjectBean;

public class WriteMymetadata {
	/**
	 * 创建文件
	 * 
	 * @return
	 */
	public String writeMymetadata(){
		
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/.mymetadata";
		dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/";
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
            sb.append("<project-module\n");
            sb.append("  type=\"WEB\"\n");
            sb.append("  name=\""+project+"\"\n");
            sb.append("  id=\"myeclipse.1413159514515\"\n");
            sb.append("  context-root=\"/"+project+"\"\n");
            sb.append("  j2ee-spec=\"5.0\"\n");
            sb.append("  archive=\""+project+".war\">\n");
            sb.append("  <attributes>\n");
            sb.append("    <attribute name=\"webrootdir\" value=\"WebRoot\" />\n");
            sb.append("  </attributes>\n");
            sb.append("</project-module>\n");


            out.write(sb.toString().getBytes("utf-8"));
                 
            log.info("创建文件.project 成功！") ;     
        out.close();
    }
 
}
