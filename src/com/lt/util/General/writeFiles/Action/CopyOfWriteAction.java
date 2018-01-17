package com.lt.util.General.writeFiles.Action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException; 
import java.util.logging.Logger; 

import org.springframework.beans.factory.annotation.Autowired;

import com.lt.util.ProjectBean;

public class CopyOfWriteAction {

	
	/**
	 * 添加Action
	 * 
	 * @return
	 */
	public String writeAction(String tableName,String pKey){
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
			+"/action/"+tableName.toLowerCase()+"/"+lowerName+"Action.java";
		dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
			+"/action/"+tableName.toLowerCase()+"/";
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
            
            sb.append("package com."+pb.getProjectName().toLowerCase()+".action."+tableName.toLowerCase()+";\n");
          
            sb.append("import java.text.SimpleDateFormat;\n");
            sb.append("import java.util.Date;\n");
            sb.append("import java.util.HashMap;\n");
            sb.append("import java.util.List;\n");
            sb.append("import java.util.Map;\n");
            sb.append("import javax.servlet.http.HttpServletResponse;\n");
            sb.append("import org.apache.log4j.Logger;\n");
            sb.append("import org.apache.struts2.ServletActionContext;\n");
            sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");
            sb.append("import com.opensymphony.xwork2.Action;\n");
            sb.append("import com."+pb.getProjectName().toLowerCase()+".service."+tableName.toLowerCase()+".I"+lowerName+"Service;\n");
            sb.append("import com."+pb.getProjectName().toLowerCase()+".model."+tableName.toLowerCase()+"."+lowerName+";\n");
            sb.append("public class "+lowerName+"Action implements Action {\n");
            
            sb.append("	private int page;\n");
            sb.append("	private int size;\n");
            sb.append("	private int totalpage;\n");
            sb.append("	private int totalnumber;\n");
            sb.append("	private String message;\n");
            sb.append("	@Autowired\n");
            sb.append("	private I"+lowerName+"Service i"+lowerName+"Service;\n");
            sb.append("	private List<"+lowerName+"> list;\n");
            sb.append("	private "+lowerName+" "+tableName.toLowerCase()+";\n");
            
            sb.append("	public int getPage() {\n");
            sb.append("		return page;\n");
            sb.append("	}\n");
            sb.append("	public void setPage(int page) {\n");
            sb.append("		this.page = page;\n");
            sb.append("	}\n");
           
            sb.append("	public int getSize() {\n");
            sb.append("		return size;\n");
            sb.append("	}\n");
            sb.append("	public void setSize(int size) {\n");
            sb.append("		this.size = size;\n");
            sb.append("	}\n");
           
            sb.append("	public int getTotalpage() {\n");
            sb.append("		return totalpage;\n");
        	sb.append("	}\n");
        	sb.append("	public void setTotalpage(int totalpage) {\n");
        	sb.append("		this.totalpage = totalpage;\n");
        	sb.append("	}\n");

        	sb.append("	public int getTotalnumber() {\n");
        	sb.append("		return totalnumber;\n");
        	sb.append("	}\n");
        	sb.append("	public void setTotalnumber(int totalnumber) {\n");
        	sb.append("		this.totalnumber = totalnumber;\n");
        	sb.append("	}\n");

        	sb.append("	public String getMessage() {\n");
            sb.append("		return message;\n");
            sb.append("	}\n");
            sb.append("	public void setMessage(String message) {\n");
            sb.append("		this.message = message;\n");
            sb.append("	}\n");

        	sb.append("	public "+lowerName+" get"+lowerName+"() {\n");
        	sb.append("		return "+tableName.toLowerCase()+";\n");
        	sb.append("	}\n");
        	sb.append("	public void set"+lowerName+"("+lowerName+" "+tableName.toLowerCase()+") {\n");
        	sb.append("		this."+tableName.toLowerCase()+" = "+tableName.toLowerCase()+";\n");
        	sb.append("	}\n");

        	sb.append("	public List<"+lowerName+"> getList() {\n");
        	sb.append("		return list;\n");
        	sb.append("	}\n");
        	sb.append("	public void setList(List<"+lowerName+"> list) {\n");
        	sb.append("		this.list = list;\n");
        	sb.append("	}\n");

            sb.append("	HttpServletResponse response = ServletActionContext.getResponse(); \n");
            sb.append("	SimpleDateFormat sdf = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");\n");
        	sb.append("	Logger logger = Logger.getLogger(\""+pb.getProjectName()+"Logger\");\n");  
        	
            out.write(sb.toString().getBytes("utf-8"));
            log.info(sb.toString()) ;      
            log.info("创建文件"+lowerName+"Action.java成功！") ;        
        out.close();
    }
 
	/**
	 * 动态添加 Action 属性
	 * 
	 * @return
	 */
	public  void addActionProperties(String tableName,String colName,String type,boolean isEnd) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/action/"+tableName.toLowerCase()+"/"+lowerName+"Action.java";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        if(type.toUpperCase().equals("DATE")||type.toUpperCase().equals("DATETIME")){
        	sb.append("	private String "+colName.toLowerCase()+"From;\n");
        	sb.append("	public String get"+toUpperCaseFirstOne(colName.toLowerCase())+"From() {\n");
        	sb.append("		return "+colName.toLowerCase()+"From;\n");
        	sb.append("	}\n");
        	sb.append("	public void set"+toUpperCaseFirstOne(colName.toLowerCase())+"From(String "+colName.toLowerCase()+"From) {\n");
        	sb.append("		this."+colName.toLowerCase()+"From = "+colName.toLowerCase()+"From;\n");
        	sb.append("	}\n");
        	sb.append("	private String "+colName.toLowerCase()+"To;\n");
        	sb.append("	public String get"+toUpperCaseFirstOne(colName.toLowerCase())+"To() {\n");
        	sb.append("		return "+colName.toLowerCase()+"To;\n");
        	sb.append("	}\n");
        	sb.append("	public void set"+toUpperCaseFirstOne(colName.toLowerCase())+"To(String "+colName.toLowerCase()+"To) {\n");
        	sb.append("		this."+colName.toLowerCase()+"To = "+colName.toLowerCase()+"To;\n");
        	sb.append("	}\n");
       
        }
        if(isEnd){
        	sb.append("	private String "+colName.toLowerCase()+";\n");
        	sb.append("	public String get"+toUpperCaseFirstOne(colName.toLowerCase())+"() {\n");
        	sb.append("		return "+colName.toLowerCase()+";\n");
        	sb.append("	}\n");
        	sb.append("	public void set"+toUpperCaseFirstOne(colName.toLowerCase())+"(String "+colName.toLowerCase()+") {\n");
        	sb.append("		this."+colName.toLowerCase()+" = "+colName.toLowerCase()+";\n");
        	sb.append("	}\n");
        }
        else{
        	sb.append("	private String "+colName.toLowerCase()+";\n");
        	sb.append("	public String get"+toUpperCaseFirstOne(colName.toLowerCase())+"() {\n");
        	sb.append("		return "+colName.toLowerCase()+";\n");
        	sb.append("	}\n");
        	sb.append("	public void set"+toUpperCaseFirstOne(colName.toLowerCase())+"(String "+colName.toLowerCase()+") {\n");
        	sb.append("		this."+colName.toLowerCase()+" = "+colName.toLowerCase()+";\n");
        	sb.append("	}\n");
        }
         
        
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	 
	/**
	 * 添加 add 开始 函数
	 * 
	 * @return
	 */
	public  void addAddFunctionStart(String tableName) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/action/"+tableName+"/"+lowerName+"Action.java";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("	public String add() throws Exception {\n"); 
        sb.append("		response.setCharacterEncoding(\"UTF-8\"); \n"); 
        sb.append("		response.setContentType(\"text/html;charset=UTF-8\"); \n"); 
        sb.append("		"+lowerName+" "+tableName.toLowerCase()+" =new "+lowerName+"(); \n"); 
        
        
        
        
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	/**
	 * 动态 添加bean属性
	 * 
	 * @return
	 */
	public  void addBeanProperties(String tableName,String colName,String type) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/action/"+tableName.toLowerCase()+"/"+lowerName+"Action.java";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        if(type.toUpperCase().equals("DATE")||type.toUpperCase().equals("DATETIME")){
        	sb.append("		if("+colName.toLowerCase()+"!=null&&!"+colName.toLowerCase()+".equals(\"\"))\n");
        	sb.append("		"+tableName.toLowerCase()+".set"+toUpperCaseFirstOne(colName.toLowerCase())+"(sdf.parse("+colName.toLowerCase()+"));\n");
        }
        else if(type.toUpperCase().equals("TINYINT")||type.toUpperCase().equals("SMALLINT")||type.toUpperCase().equals("MEDIUMINT")||
        		type.toUpperCase().equals("INT")||type.toUpperCase().equals("BIGINT")){
        	sb.append("		if("+colName.toLowerCase()+"!=null&&!"+colName.toLowerCase()+".equals(\"\"))\n");
        	sb.append("		"+tableName.toLowerCase()+".set"+toUpperCaseFirstOne(colName.toLowerCase())+"(Long.parseLong("+colName.toLowerCase()+"));\n");
        } 
        else{
        	sb.append("		"+tableName.toLowerCase()+".set"+toUpperCaseFirstOne(colName.toLowerCase())+"("+colName.toLowerCase()+");\n");
        }
        
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	 
	/**
	 * 添加 add 尾部 函数
	 * 
	 * @return
	 */
	public  void addAddFunctionEnd(String tableName,String pKey) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/action/"+tableName.toLowerCase()+"/"+lowerName+"Action.java";
		
		File file=new File(url);
		
		if(!file.exists())
			file.createNewFile();
		
		FileOutputStream out=new FileOutputStream(file,true); 
		StringBuffer sb=new StringBuffer();

		sb.append("		try {\n"); 
		sb.append("			int result = Integer.parseInt(i"+lowerName+"Service.add"+tableName+"("+tableName.toLowerCase()+").toString());\n"); 
		sb.append("			response.getWriter().write(\"添加成功！\"); \n"); 
		sb.append("			message=\"添加成功！\";  \n"); 
		sb.append("			logger.info(result+\"添加成功！。\");\n"); 
		sb.append("		} catch (Exception e) {\n"); 
		sb.append("			message=\"添加失败！\";  \n"); 
		sb.append("			logger.info(\"添加失败！。\");\n"); 
		sb.append("			e.printStackTrace();\n"); 
		sb.append("		}\n"); 
		sb.append("		return \"add\";\n"); 
		sb.append("	}\n"); 
		sb.append("	@SuppressWarnings(\"unchecked\")\n"); 
		sb.append("	public String list() throws Exception {\n\n"); 
		sb.append("		size=15;\n"); 
		sb.append("		Map  paramMap = new HashMap ();\n"); 
		sb.append("		paramMap.put(\"fromPage\",(page-1)*size);\n");  
		sb.append("		paramMap.put(\"toPage\",page*size); \n"); 
		out.write(sb.toString().getBytes("utf-8"));
		log.info(sb.toString()) ;        
		out.close();
	}
	
	/**
	 * 动态  list 函数的参数
	 * 
	 * @return
	 */
	public  void addListFunctionStart(String tableName,String colName,String type) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/action/"+tableName.toLowerCase()+"/"+lowerName+"Action.java";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        if(type.toUpperCase().equals("DATE")||type.toUpperCase().equals("DATETIME")){
        	
        	sb.append("			if("+colName.toLowerCase()+"From!=null&&!"+colName.toLowerCase()+"From.equals(\"\"))\n");
        	sb.append("			paramMap.put(\""+colName.toLowerCase()+"From\", sdf.parse("+colName.toLowerCase()+"From));\n");
        	sb.append("			if("+colName.toLowerCase()+"To!=null&&!"+colName.toLowerCase()+"To.equals(\"\"))\n");
        	sb.append("			paramMap.put(\""+colName.toLowerCase()+"To\", sdf.parse("+colName.toLowerCase()+"To));\n");
        }
        else{
        	sb.append("			paramMap.put(\""+colName.toLowerCase()+"\", "+colName.toLowerCase()+");\n"); 
        }
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	
	/**
	 * 添加 list 尾部 update 头部函数
	 * 
	 * @return
	 */
	public  void addListFunctionEnd(String tableName,String pKey) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/action/"+tableName.toLowerCase()+"/"+lowerName+"Action.java";
		
		File file=new File(url);
		
		if(!file.exists())
			file.createNewFile();
		
		FileOutputStream out=new FileOutputStream(file,true); 
		StringBuffer sb=new StringBuffer();

		sb.append("		try {\n"); 
		sb.append("			list=i"+lowerName+"Service.select"+tableName+"ByParam(paramMap); \n"); 
		sb.append("			totalnumber=i"+lowerName+"Service.selectCount"+tableName+"ByParam(paramMap);\n"); 
		sb.append("			if((totalnumber%size)==0){\n"); 
		sb.append("				totalpage=(totalnumber/size);\n"); 
		sb.append("			}\n"); 
		sb.append("			else{\n"); 
		sb.append("				totalpage=(totalnumber/size)+1;\n"); 
		sb.append("			}	\n"); 
		sb.append("			logger.info(\"获取列表成功！\");\n"); 
		sb.append("		} catch (Exception e) {\n"); 
		sb.append("			logger.info(\"获取列表失败！\"+e);\n"); 
		sb.append("			e.printStackTrace();\n"); 
		sb.append("		}\n"); 
		sb.append("		return \"list\";\n"); 
		sb.append("	}\n\n"); 
		sb.append("	public String update() throws Exception {\n"); 
		sb.append("		response.setCharacterEncoding(\"UTF-8\"); \n"); 
        sb.append("		response.setContentType(\"text/html;charset=UTF-8\"); \n");
		sb.append("		"+lowerName+" "+tableName.toLowerCase()+" =new "+lowerName+"(); \n"); 
		out.write(sb.toString().getBytes("utf-8"));
		log.info(sb.toString()) ;        
		out.close();
	}
	
	
	
	/**
	 * 添加update 尾部select delete 函数
	 * 
	 * @return
	 */
	
	public  void addOthers(String tableName,String pKey) throws IOException{
		Logger log = Logger.getLogger("ZeroLog"); 
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
		+"/action/"+tableName.toLowerCase()+"/"+lowerName+"Action.java";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("		try {\n"); 
        sb.append("			i"+lowerName+"Service.update"+tableName+"("+tableName.toLowerCase()+");\n"); 
        sb.append("			response.getWriter().write(\"更新成功！\"); \n"); 
        sb.append("			message=\"更新成功！\";  \n"); 
        if(pKey==null||pKey.equals("")){
        	 sb.append("			logger.info(\"更新成功！\");\n"); 
        }
        else{
        	 sb.append("			logger.info("+pKey.toLowerCase()+"+\"更新成功！\");\n"); 
        }
       
        sb.append("		} catch (Exception e) {\n"); 
        if(pKey==null||pKey.equals("")){
        	sb.append("			logger.info(\"更新失败！\"+e);\n"); 
        }
        else{
    	    sb.append("			logger.info("+pKey.toLowerCase()+"+\"更新失败！\"+e);\n"); 
        }
        
        sb.append("			message=\"更新失败！\"; \n"); 
        sb.append("			response.getWriter().write(\"更新失败！\"); \n"); 
        sb.append("			e.printStackTrace();\n"); 
        sb.append("		}\n"); 
        sb.append("			return null;\n"); 
        sb.append("	}\n"); 
        sb.append("	public String delete() throws Exception {\n"); 
        sb.append("		response.setCharacterEncoding(\"UTF-8\"); \n"); 
        sb.append("		response.setContentType(\"text/html;charset=UTF-8\"); \n");
        sb.append("		try {\n"); 
        sb.append("			i"+lowerName+"Service.delete"+tableName+"("+pKey.toLowerCase()+");\n"); 
        sb.append("			response.getWriter().write(\"删除成功！\"); \n"); 
        if(pKey==null||pKey.equals("")){
        	sb.append("			logger.info(\"删除成功！\");\n"); 
        }
        else{
    	    sb.append("			logger.info("+pKey.toLowerCase()+"+\"删除成功！\");\n"); 
        }
        
        sb.append("		} catch (Exception e) {\n"); 
        if(pKey==null||pKey.equals("")){
        	 sb.append("			logger.info(\"删除失败！\"+e);\n"); 
        }
        else{
    	    sb.append("			logger.info("+pKey.toLowerCase()+"+\"删除失败！\"+e);\n"); 
        }
       
        sb.append("			response.getWriter().write(\"删除失败！\"); \n"); 
        sb.append("			e.printStackTrace();\n"); 
        sb.append("		}\n"); 
        sb.append("		return null;\n"); 
        sb.append("	}\n"); 
        sb.append("	public String select() throws Exception {\n"); 
        sb.append("		try {\n"); 
        sb.append("			"+tableName.toLowerCase()+"=i"+lowerName+"Service.select"+tableName+"ById("+pKey.toLowerCase()+");\n"); 
        if(pKey==null||pKey.equals("")){
        	sb.append("			logger.info(\"查询成功！\");\n"); 
        }
        else{
    	    sb.append("			logger.info("+pKey.toLowerCase()+"+\"查询成功！\");\n"); 
        }
        
        sb.append("		} catch (Exception e) {\n"); 
        if(pKey==null||pKey.equals("")){
        	sb.append("			logger.info(\"查询失败！\"+e);\n"); 
        }
        else{
        	sb.append("			logger.info("+pKey.toLowerCase()+"+\"查询失败！\"+e);\n");  
        }
        
        sb.append("			e.printStackTrace();\n"); 
        sb.append("		}\n"); 
        sb.append("		return \"select\";\n"); 
        sb.append("	}\n");
        sb.append("    public String execute() throws Exception {\n");
        sb.append("		return null;\n");
        sb.append("	}\n");
        sb.append("}\n");
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
