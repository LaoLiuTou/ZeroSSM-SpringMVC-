package com.lt.util.General.writeFiles.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;

import com.lt.util.ProjectBean;

public class WriteController {

	
	/**
	 * 添加Action
	 * 
	 * @return
	 */
	public String writeController(String tableName,String pKey){
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
			+"/controller/"+tableName.toLowerCase()+"/"+lowerName+"Controller.java";
		dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
			+"/controller/"+tableName.toLowerCase()+"/";
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
	 * 添加 class
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
            
            sb.append("package com."+pb.getProjectName().toLowerCase()+".controller."+tableName.toLowerCase()+";\n");
            
            sb.append("import java.text.SimpleDateFormat;\n");
            sb.append("import java.util.Date;\n");
            sb.append("import java.util.HashMap;\n");
            sb.append("import java.util.List;\n");
            sb.append("import java.util.Map;\n"); 
            sb.append("import java.io.IOException;\n"); 
            sb.append("import javax.servlet.ServletException;\n");
            sb.append("import javax.servlet.http.HttpServletRequest;\n");
            sb.append("import javax.servlet.http.HttpServletResponse;\n");
            sb.append("import org.apache.log4j.Logger;\n"); 
            sb.append("import org.springframework.beans.factory.annotation.Autowired;\n"); 
            sb.append("import org.springframework.stereotype.Controller;\n"); 
            sb.append("import org.springframework.web.bind.annotation.RequestMapping;\n"); 
            sb.append("import org.springframework.web.bind.annotation.ResponseBody;\n"); 
            sb.append("import com."+pb.getProjectName().toLowerCase()+".service."+tableName.toLowerCase()+".I"+lowerName+"Service;\n");
            sb.append("import com."+pb.getProjectName().toLowerCase()+".model."+tableName.toLowerCase()+"."+lowerName+";\n");
            sb.append("@Controller\n");
            sb.append("public class "+lowerName+"Controller {\n");
            sb.append("	@Autowired\n");
            sb.append("	private I"+lowerName+"Service i"+lowerName+"Service;\n");
           /* sb.append("	private List<"+lowerName+"> list;\n");
            sb.append("	private "+lowerName+" "+tableName.toLowerCase()+";\n");*/
              
            sb.append("	SimpleDateFormat sdf = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");\n");
        	sb.append("	Logger logger = Logger.getLogger(\""+pb.getProjectName()+"Logger\");\n");  
        	
            out.write(sb.toString().getBytes("utf-8"));
            log.info(sb.toString()) ;      
            log.info("创建文件"+lowerName+"Action.java成功！") ;        
        out.close();
    }
 
	/**
	 * 添加 add  函数
	 * 
	 * @return
	 */
	public  void addAddFunction(String tableName,String pKey) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/controller/"+tableName+"/"+lowerName+"Controller.java";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("	@SuppressWarnings({ \"rawtypes\", \"unchecked\" })\n"); 
        sb.append("	@RequestMapping(\"/add"+lowerName+"\")\n"); 
        sb.append("	@ResponseBody\n"); 
        sb.append("	public Map add("+lowerName+" "+tableName.toLowerCase()+"){\n"); 
        sb.append("		Map resultMap=new HashMap();\n"); 
        sb.append("		try {\n"); 
        sb.append("			i"+lowerName+"Service.add"+lowerName+"("+tableName.toLowerCase()+");\n"); 
        sb.append("			resultMap.put(\"status\", \"0\");\n"); 
        sb.append("			resultMap.put(\"msg\", "+tableName.toLowerCase()+".getId());\n"); 
        sb.append("			logger.info(\"新建成功，主键：\"+"+tableName.toLowerCase()+".get"+toUpperCaseFirstOne(pKey.toLowerCase())+"());\n"); 
        sb.append("		} catch (Exception e) {\n"); 
        sb.append("			resultMap.put(\"status\", \"-1\");\n"); 
        sb.append("			resultMap.put(\"msg\", \"新建失败！\");\n"); 
        sb.append("			logger.info(\"新建失败！\"+e.getLocalizedMessage());\n"); 
        sb.append("			e.printStackTrace();\n"); 
        sb.append("		}\n"); 
        sb.append("		return resultMap;\n"); 
        sb.append("	}\n"); 
   
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	
	/**
	 * 添加 select 函数
	 * 
	 * @return
	 */
	public  void addSelectFunction(String tableName,String pKey) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/controller/"+tableName+"/"+lowerName+"Controller.java";
        File file=new File(url);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("	@SuppressWarnings({ \"rawtypes\", \"unchecked\" })\n"); 
        sb.append("	@RequestMapping(\"/select"+lowerName+"\")\n"); 
        sb.append("	@ResponseBody\n"); 
        sb.append("	public Map select("+lowerName+" "+tableName.toLowerCase()+"){\n"); 
        sb.append("		Map resultMap=new HashMap();\n"); 
        sb.append("		try {\n"); 
        sb.append("			if("+tableName.toLowerCase()+".get"+toUpperCaseFirstOne(pKey.toLowerCase())+"()==null){\n"); 
        sb.append("				resultMap.put(\"status\", \"-1\");\n"); 
        sb.append("				resultMap.put(\"msg\", \"参数不能为空！\");\n"); 
        sb.append("			}\n"); 
        sb.append("			else{\n"); 
        sb.append("				"+lowerName+" resultSelect=i"+lowerName+"Service.select"+lowerName+"By"+toUpperCaseFirstOne(pKey.toLowerCase())+"("+tableName.toLowerCase()+".get"+toUpperCaseFirstOne(pKey.toLowerCase())+"()+\"\");\n"); 
        sb.append("				resultMap.put(\"status\", \"0\");\n"); 
        sb.append("				resultMap.put(\"msg\", resultSelect);\n"); 
        sb.append("			}\n"); 
        sb.append("		} catch (Exception e) {\n"); 
        sb.append("			resultMap.put(\"status\", \"-1\");\n"); 
        sb.append("			resultMap.put(\"msg\", \"查询失败！\");\n"); 
        sb.append("			logger.info(\"查询失败！\"+e.getLocalizedMessage());\n"); 
        sb.append("			e.printStackTrace();\n"); 
        sb.append("		}\n"); 
        sb.append("		return resultMap;\n"); 
        sb.append("	}\n");  
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	
	
	/**
	 * 添加 update  函数
	 * 
	 * @return
	 */
	public  void addUpdateFunction(String tableName,String pKey) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/controller/"+tableName+"/"+lowerName+"Controller.java";
        File file=new File(url);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("	@SuppressWarnings({ \"rawtypes\", \"unchecked\" })\n"); 
        sb.append("	@RequestMapping(\"/update"+lowerName+"\")\n"); 
        sb.append("	@ResponseBody\n"); 
        sb.append("	public Map update("+lowerName+" "+tableName.toLowerCase()+"){\n"); 
        sb.append("		Map resultMap=new HashMap();\n"); 
        sb.append("		try {\n"); 
        sb.append("			if("+tableName.toLowerCase()+".get"+toUpperCaseFirstOne(pKey.toLowerCase())+"()==null){\n"); 
        sb.append("				resultMap.put(\"status\", \"-1\");\n"); 
        sb.append("				resultMap.put(\"msg\", \"参数不能为空！\");\n"); 
        sb.append("			}\n"); 
        sb.append("			else{\n"); 
        sb.append("				int resultUpdate=i"+lowerName+"Service.update"+lowerName+"("+tableName.toLowerCase()+");\n");
        sb.append("				resultMap.put(\"status\", \"0\");\n"); 
        sb.append("				resultMap.put(\"msg\", \"更新成功！\");\n"); 
        sb.append("				logger.info(\"更新成功，主键：\"+"+tableName.toLowerCase()+".get"+toUpperCaseFirstOne(pKey.toLowerCase())+"());\n"); 
        sb.append("			}\n"); 
        sb.append("		} catch (Exception e) {\n"); 
        sb.append("			resultMap.put(\"status\", \"-1\");\n"); 
        sb.append("			resultMap.put(\"msg\", \"更新失败！\");\n"); 
        sb.append("			logger.info(\"更新失败！\"+e.getLocalizedMessage());\n"); 
        sb.append("			e.printStackTrace();\n"); 
        sb.append("		}\n"); 
        sb.append("		return resultMap;\n"); 
        sb.append("	}\n");  
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	/**
	 * 添加 delete  函数
	 * 
	 * @return
	 */
	public  void addDeleteFunction(String tableName,String pKey) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/controller/"+tableName+"/"+lowerName+"Controller.java";
        File file=new File(url);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("	@SuppressWarnings({ \"rawtypes\", \"unchecked\" })\n"); 
        sb.append("	@RequestMapping(\"/delete"+lowerName+"\")\n"); 
        sb.append("	@ResponseBody\n"); 
        sb.append("	public Map delete("+lowerName+" "+tableName.toLowerCase()+"){\n"); 
        sb.append("		Map resultMap=new HashMap();\n"); 
        sb.append("		try {\n"); 
        sb.append("			if("+tableName.toLowerCase()+".get"+toUpperCaseFirstOne(pKey.toLowerCase())+"()==null){\n"); 
        sb.append("				resultMap.put(\"status\", \"-1\");\n"); 
        sb.append("				resultMap.put(\"msg\", \"参数不能为空！\");\n"); 
        sb.append("			}\n"); 
        sb.append("			else{\n"); 
        sb.append("				int resultDelete=i"+lowerName+"Service.delete"+lowerName+"("+tableName.toLowerCase()+".get"+toUpperCaseFirstOne(pKey.toLowerCase())+"()"+"+\"\");\n");
        sb.append("				resultMap.put(\"status\", \"0\");\n"); 
        sb.append("				resultMap.put(\"msg\", \"删除成功！\");\n"); 
        sb.append("				logger.info(\"删除成功，主键：\"+"+tableName.toLowerCase()+".get"+toUpperCaseFirstOne(pKey.toLowerCase())+"());\n"); 
        sb.append("			}\n"); 
        sb.append("		} catch (Exception e) {\n"); 
        sb.append("			resultMap.put(\"status\", \"-1\");\n"); 
        sb.append("			resultMap.put(\"msg\", \"删除失败！\");\n"); 
        sb.append("			logger.info(\"删除失败！\"+e.getLocalizedMessage());\n"); 
        sb.append("			e.printStackTrace();\n"); 
        sb.append("		}\n"); 
        sb.append("		return resultMap;\n"); 
        sb.append("	}\n");  
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	
	
	/**
	 * 添加 list  函数
	 * 
	 * @return
	 */
	public  void addListFunctionStart(String tableName,String pKey) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/controller/"+tableName+"/"+lowerName+"Controller.java";
        File file=new File(url);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("	@SuppressWarnings({ \"rawtypes\", \"unchecked\" })\n"); 
        sb.append("	@RequestMapping(\"/list"+lowerName+"\")\n"); 
        sb.append("	@ResponseBody\n"); 
 
        sb.append("	public Map list(HttpServletRequest request, HttpServletResponse response,"+lowerName+" "+tableName.toLowerCase()+")\n"); 
        sb.append("		throws ServletException, IOException {\n"); 
        sb.append("		Map resultMap=new HashMap();\n"); 
        sb.append("		try {\n"); 
        sb.append("			String page=request.getParameter(\"page\");\n"); 
        sb.append("			String size=request.getParameter(\"size\");\n"); 
        sb.append("			if(page!=null&&size!=null){\n"); 
        sb.append("				Map paramMap=new HashMap();\n"); 
        sb.append("				paramMap.put(\"fromPage\",(Integer.parseInt(page)-1)*Integer.parseInt(size));\n"); 
        sb.append("				paramMap.put(\"toPage\",Integer.parseInt(size)); \n"); 
        
        
       
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	/**
	 * 添加 list  函数
	 * 
	 * @return
	 */
	public  void addListFunctionEnd(String tableName,String pKey) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/controller/"+tableName+"/"+lowerName+"Controller.java";
        File file=new File(url);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
         
        
        sb.append("				List<"+lowerName+"> list=i"+lowerName+"Service.select"+lowerName+"ByParam(paramMap);\n"); 
        sb.append("				int totalnumber=i"+lowerName+"Service.selectCount"+lowerName+"ByParam(paramMap);\n"); 
        sb.append("				Map tempMap=new HashMap();\n"); 
        sb.append("				resultMap.put(\"status\", \"0\");\n"); 
        sb.append("				tempMap.put(\"num\", totalnumber);\n"); 
        sb.append("				tempMap.put(\"data\", list);\n"); 
        sb.append("				resultMap.put(\"msg\", tempMap);\n"); 
        sb.append("			}\n"); 
        sb.append("			else{\n"); 
        sb.append("				resultMap.put(\"status\", \"-1\");\n"); 
        sb.append("				resultMap.put(\"msg\", \"分页参数不能为空！\");\n"); 
        sb.append("			}\n"); 
        sb.append("		} catch (Exception e) {\n"); 
        sb.append("			resultMap.put(\"status\", \"-1\");\n"); 
        sb.append("			resultMap.put(\"msg\", \"查询失败！\");\n"); 
        sb.append("			logger.info(\"查询失败！\"+e.getLocalizedMessage());\n"); 
        sb.append("			e.printStackTrace();\n"); 
        sb.append("		}\n"); 
        sb.append("		return resultMap;\n"); 
        sb.append("	}\n");  
        sb.append("}\n"); 
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	
	/**
	 * 动态  list 函数的参数
	 * 
	 * @return
	 */
	public  void addListFunctionParam(String tableName,String colName,String type) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/controller/"+tableName+"/"+lowerName+"Controller.java";
        File file=new File(url);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        if(type.toUpperCase().equals("DATE")||type.toUpperCase().equals("DATETIME")){
        	sb.append("				String "+colName.toLowerCase()+"From=request.getParameter(\""+colName.toLowerCase()+"From\");\n");
        	sb.append("				String "+colName.toLowerCase()+"To=request.getParameter(\""+colName.toLowerCase()+"To\");\n");
        	sb.append("				if("+colName.toLowerCase()+"From!=null&&!"+colName.toLowerCase()+"From.equals(\"\"))\n");
        	sb.append("				paramMap.put(\""+colName.toLowerCase()+"From\", sdf.parse("+colName.toLowerCase()+"From));\n");
        	sb.append("				if("+colName.toLowerCase()+"To!=null&&!"+colName.toLowerCase()+"To.equals(\"\"))\n");
        	sb.append("				paramMap.put(\""+colName.toLowerCase()+"To\", sdf.parse("+colName.toLowerCase()+"To));\n");
        }
        else{
        	sb.append("				paramMap.put(\""+colName.toLowerCase()+"\","+lowerName.toLowerCase()+".get"+toUpperCaseFirstOne(colName.toLowerCase())+"());\n"); 
        }
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
