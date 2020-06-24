package com.lt.util.General.writeFiles.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.lt.util.ProjectBean;

public class WriteServiceImpl {
	/**
	 * 添加DaoImpl
	 * 
	 * @return
	 */
	public String writeServiceImpl(String pKey,String tableName){
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
			+"/service/"+tableName.toLowerCase()+"/"+lowerName+"ServiceImpl.java";
		dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
			+"/service/"+tableName.toLowerCase()+"/";
		try {
			createFiles(dirs,url,pKey,tableName);
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
	public  void createFiles(String dirs,String url,String pKey,String tableName) throws IOException{
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
            
            sb.append("package com."+pb.getProjectName().toLowerCase()+".service."+tableName.toLowerCase()+";\n"); 
            sb.append("import java.util.List;\n");
            sb.append("import java.util.Map;\n");
            sb.append("import org.springframework.transaction.annotation.Transactional;\n");
            sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");
            sb.append("import com."+pb.getProjectName().toLowerCase()+".dao."+tableName.toLowerCase()+".I"+lowerName+"Mapper;\n");
            sb.append("import com."+pb.getProjectName().toLowerCase()+".model."+tableName.toLowerCase()+"."+lowerName+";\n");
            sb.append("public class "+lowerName+"ServiceImpl  implements I"+lowerName+"Service {\n\n");
            sb.append("	@Autowired\n");
            sb.append("	private I"+lowerName+"Mapper i"+lowerName+"Mapper;\n");
            sb.append("	/**\n");
            sb.append("	* 通过id选取\n");
            sb.append("	* @return\n");
            sb.append("	*/\n");
            //sb.append(" @Transactional\n");
            sb.append("	public "+lowerName+" select"+lowerName+"By"+toUpperCaseFirstOne(pKey.toLowerCase())+"(String id){\n");
            sb.append("		return i"+lowerName+"Mapper.select"+tableName+"By"+toUpperCaseFirstOne(pKey.toLowerCase())+"(id);\n");  
            sb.append("	}\n\n");
            sb.append("	/**\n");
            sb.append("	* 通过查询参数获取信息\n");
            sb.append("	* @return\n");
            sb.append("	*/ \n");
            sb.append("	@SuppressWarnings(\"rawtypes\")\n");
            //sb.append(" @Transactional\n");
            sb.append("	public List<"+lowerName+"> select"+lowerName+"ByParam(Map paramMap){ \n");
            sb.append("		return i"+lowerName+"Mapper.select"+tableName+"ByParam(paramMap);\n");  
            sb.append("	}\n\n");
            sb.append("	/**\n"); 
            sb.append("	* 通过查询参数获取总条数\n"); 
            sb.append("	* @return\n"); 
            sb.append("	*/ \n"); 
            sb.append("	@SuppressWarnings(\"rawtypes\")\n");
            //sb.append(" @Transactional\n");
            sb.append("	public int selectCount"+lowerName+"ByParam(Map paramMap){ \n"); 
            sb.append("		return i"+lowerName+"Mapper.selectCount"+tableName+"ByParam(paramMap);\n");  
            sb.append("	}\n\n"); 
            sb.append("	/**\n");
            sb.append("	* 更新 \n");
            sb.append("	* @return \n"); 
            sb.append("	*/ \n"); 
            sb.append("	@Transactional\n");
            sb.append("	public  int update"+lowerName+"("+lowerName+" "+tableName.toLowerCase()+"){\n");
            sb.append("		return i"+lowerName+"Mapper.update"+tableName+"("+tableName.toLowerCase()+");\n"); 
            sb.append("	}\n\n");
            sb.append("	/**\n");
            sb.append("	* 添加 \n");
            sb.append("	* @return\n");  
            sb.append("	*/ \n"); 
            sb.append("	@Transactional\n");
            sb.append("	public  int add"+lowerName+"("+lowerName+" "+tableName.toLowerCase()+"){\n");
            sb.append("		return i"+lowerName+"Mapper.add"+tableName+"("+tableName.toLowerCase()+");\n"); 
            sb.append("	}\n\n");
            sb.append("	/**\n");
            sb.append("	* 批量添加 \n");
            sb.append("	* @return\n");  
            sb.append("	*/ \n"); 
            sb.append("	@Transactional\n");
            sb.append("	public  int muladd"+lowerName+"(List<"+lowerName+"> list){\n");
            sb.append("		return i"+lowerName+"Mapper.muladd"+tableName+"(list);\n"); 
            sb.append("	}\n\n");
            sb.append("	/**\n");
            sb.append("	* 删除 \n");
            sb.append("	* @return \n"); 
            sb.append("	*/ \n"); 
            sb.append("	@Transactional\n");
            sb.append("	public  int delete"+lowerName+"(String id){\n");
            sb.append("		return i"+lowerName+"Mapper.delete"+tableName+"(id);\n");  
            sb.append("	}\n\n");
            sb.append("}\n\n");
            
            out.write(sb.toString().getBytes("utf-8"));
            log.info(sb.toString()) ;      
            log.info("创建文件"+toUpperCaseFirstOne(tableName.toLowerCase())+"ServiceImpl.java成功！") ;        
        out.close();
    }
 
	 
	//首字母转小写
    public static String toLowerCaseFirstOne(String s)
    {
        if(s.length()==0||Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    //首字母转大写
    public static String toUpperCaseFirstOne(String s)
    {
        if(s.length()==0||Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
 

}
