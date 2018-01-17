package com.lt.util.General.writeFiles.project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import com.lt.jdbc.JdbcBean;
import com.lt.util.ProjectBean;

public class WriteProject {
	/**
	 * 创建文件
	 * 
	 * @return
	 */
	public String writeProject(){
		
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/.project";
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
            sb.append("<projectDescription>\n");
            sb.append("	<name>"+project+"</name>\n");
            sb.append("	<comment></comment>\n");
            sb.append("	<projects>\n");
            sb.append("	</projects>\n");
            sb.append("	<buildSpec>\n");
            sb.append("<buildCommand>\n");
            sb.append("	<name>org.eclipse.wst.jsdt.core.javascriptValidator</name>\n");
            sb.append("	<arguments>\n");
            sb.append("	</arguments>\n");
            sb.append("</buildCommand>\n");
            sb.append("<buildCommand>\n");
            sb.append("	<name>com.genuitec.eclipse.j2eedt.core.WebClasspathBuilder</name>\n");
            sb.append("	<arguments>\n");
            sb.append("	</arguments>\n");
            sb.append("</buildCommand>\n");
            sb.append("<buildCommand>\n");
            sb.append("	<name>org.eclipse.jdt.core.javabuilder</name>\n");
            sb.append("	<arguments>\n");
            sb.append("	</arguments>\n");
            sb.append("</buildCommand>\n");
            sb.append("<buildCommand>\n");
            sb.append("	<name>com.genuitec.eclipse.j2eedt.core.J2EEProjectValidator</name>\n");
            sb.append("	<arguments>\n");
            sb.append("	</arguments>\n");
            sb.append("</buildCommand>\n");
            sb.append("<buildCommand>\n");
            sb.append("	<name>com.genuitec.eclipse.j2eedt.core.DeploymentDescriptorValidator</name>\n");
            sb.append("	<arguments>\n");
            sb.append("	</arguments>\n");
            sb.append("</buildCommand>\n");
            sb.append("<buildCommand>\n");
            sb.append("	<name>org.eclipse.wst.validation.validationbuilder</name>\n");
            sb.append("	<arguments>\n");
            sb.append("	</arguments>\n");
            sb.append("</buildCommand>\n");
            sb.append("<buildCommand>\n");
            sb.append("	<name>com.genuitec.eclipse.ws.xfire.wsbuilder</name>\n");
            sb.append("	<arguments>\n");
            sb.append("	</arguments>\n");
            sb.append("</buildCommand>\n");
            sb.append("	</buildSpec>\n");
            sb.append("	<natures>\n");
            sb.append("<nature>com.genuitec.eclipse.ws.xfire.wsnature</nature>\n");
            sb.append("<nature>com.genuitec.eclipse.j2eedt.core.webnature</nature>\n");
            sb.append("<nature>org.eclipse.jdt.core.javanature</nature>\n");
            sb.append("<nature>org.eclipse.wst.jsdt.core.jsNature</nature>\n");
            sb.append("	</natures>\n");
            sb.append("</projectDescription>\n");


            out.write(sb.toString().getBytes("utf-8"));
                 
            log.info("创建文件.project 成功！") ;     
        out.close();
    }
 
}
