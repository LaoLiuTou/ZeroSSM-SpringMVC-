package com.lt.util.General.writeFiles.servlet;

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

public class WriteServlet {
	
	/**
	 * 添加Bean
	 * 
	 * @return
	 */
	public static String writeServlet(){
		
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
			+"/servlet/"+"FileUpload.java";
		dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/com/"+pb.getProjectName().toLowerCase()
			+"/servlet/";
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
            
            sb.append("package com."+pb.getProjectName().toLowerCase()+".servlet;\n\n"); 
            sb.append("import java.io.File;\n");
            sb.append("import java.io.FileOutputStream;\n");
            sb.append("import java.io.IOException;\n");
            sb.append("import java.io.InputStream;\n");
            sb.append("import java.io.PrintWriter;\n");
            sb.append("import java.util.UUID;\n");
            sb.append("import java.util.List;\n");
            sb.append("import javax.servlet.ServletException;\n");
            sb.append("import javax.servlet.http.HttpServlet;\n");
            sb.append("import javax.servlet.http.HttpServletRequest;\n");
            sb.append("import javax.servlet.http.HttpServletResponse;\n");
    		sb.append("import net.sf.json.JSONArray;\n");
    		sb.append("import net.sf.json.JSONObject;\n");
    		sb.append("import org.apache.commons.fileupload.FileItem;\n");
    		sb.append("import org.apache.commons.fileupload.FileItemFactory;\n");
    		sb.append("import org.apache.commons.fileupload.disk.DiskFileItemFactory;\n");
    		sb.append("import org.apache.commons.fileupload.servlet.ServletFileUpload;\n");
            
            sb.append("/**\n");
            sb.append(" * @author LT\n");
            sb.append(" */\n");
            sb.append("public class FileUpload extends HttpServlet {\n\n");
            sb.append("	private static final long serialVersionUID = 1L;\n\n");
            sb.append("	protected void service(HttpServletRequest request,\n");
            sb.append("		HttpServletResponse response) throws ServletException, IOException\n");
            sb.append("	{\n\n");
            sb.append("		String fullPath = \"\";\n");
            sb.append("		JSONObject resultJO=new JSONObject();\n");
            sb.append("		request.setCharacterEncoding(\"UTF-8\"); // 设置处理请求参数的编码格式\n");
            sb.append("		response.setContentType(\"text/html;charset=UTF-8\"); // 设置Content-Type字段值\n");
            sb.append("		PrintWriter out = response.getWriter();\n");
            sb.append("		try\n");
            sb.append("		{\n\n");
            sb.append("			FileItemFactory factory = new DiskFileItemFactory(); // 建立FileItemFactory对象\n");
            sb.append("			ServletFileUpload upload = new ServletFileUpload(factory);\n");
            sb.append("			List<FileItem> items = upload.parseRequest(request);\n");
            sb.append("			String uploadPath=request.getSession().getServletContext().getRealPath(\"/\")+\"upload/\";\n");
            sb.append("			File file = new File(uploadPath);\n");
            sb.append("			if (!file.exists())\n");
            sb.append("			{\n");
            sb.append("				file.mkdir();\n");
            sb.append("			}\n");
            sb.append("			InputStream is = null; // 当前上传文件的InputStream对象\n");
            
            
            sb.append("			JSONArray  fileJA = new JSONArray();\n");
		    sb.append("			// 循环处理上传文件\n");
			sb.append("			for (FileItem item : items)\n");
			sb.append("			{\n");
			sb.append("			boolean hasName=true;\n");
			sb.append("			int index= items.indexOf(item);\n");
			sb.append("				// 处理普通的表单域\n");
			sb.append("				if (item.isFormField())\n");
			sb.append("				{\n");
			sb.append("					if (item.getFieldName().equals(\"filename\"))\n");
			sb.append("					{\n");
			sb.append("						// 如果新文件不为空，将其保存在filename中\n");
			sb.append("						if (!item.getString().equals(\"\")){\n");
			sb.append("							fullPath = item.getString(\"UTF-8\");\n");
			sb.append("						}\n");
			sb.append("						else{\n");
			sb.append("							hasName= false;\n");
			sb.append("						}\n");
			sb.append("					}\n");
			sb.append("				}\n");
			sb.append("				// 处理上传文件\n");
			sb.append("				else \n");
			sb.append("				{\n");
			sb.append("					if (item.getName() != null && !item.getName().equals(\"\")){\n");
			sb.append("						// 从客户端发送过来的上传文件路径中截取文件名\n");
			sb.append("						fullPath = item.getName().substring(\n");
			sb.append("								item.getName().lastIndexOf(\"\\\\\") + 1);\n");
			sb.append("						is = item.getInputStream(); // 得到上传文件的InputStream对象\n");
			sb.append("					}\n");
			sb.append("					else{\n");
			sb.append("						hasName= false;\n");
			sb.append("					}\n");
			sb.append("				}\n");
			sb.append("				if(hasName){\n");
			sb.append("					String filename=UUID.randomUUID()+\".\"+fullPath.split(\"\\\\.\")[1];\n");
			sb.append("					// 将路径和上传文件名组合成完整的服务端路径\n");
			sb.append("					fullPath = uploadPath + filename;\n");
			sb.append("					// 如果服务器已经存在和上传文件同名的文件，则输出提示信息\n");
			sb.append("					if (new File(fullPath).exists())\n");
			sb.append("					{\n");
			sb.append("						new File(fullPath).delete();\n");
			sb.append("					}\n");
			sb.append("					// 开始上传文件\n");
			sb.append("					if (!fullPath.equals(\"\"))\n");
			sb.append("					{\n");
			sb.append("						// 用FileOutputStream打开服务端的上传文件\n");
			sb.append("						FileOutputStream fos = new FileOutputStream(fullPath);\n");
			sb.append("						byte[] buffer = new byte[8192]; // 每次读8K字节\n");
			sb.append("						int count = 0;\n");
			sb.append("						// 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中\n");
			sb.append("						while ((count = is.read(buffer)) > 0)\n");
			sb.append("						{\n");
			sb.append("							fos.write(buffer, 0, count); // 向服务端文件写入字节流\n");
			sb.append("						}\n");
			sb.append("						fos.close(); // 关闭FileOutputStream对象\n");
			sb.append("						is.close(); // InputStream对象\n");
			sb.append("						String fileItem = \"{\\\"file\"+(index+1)+\"\\\":\\\"\"+filename+\"\\\"}\";\n");
			sb.append("						fileJA.add(fileItem);\n");
			sb.append("					}\n");
			sb.append("				}\n");
			sb.append("			}\n");
			sb.append("			resultJO.accumulate(\"state\", \"success\");\n");
			sb.append("			resultJO.accumulate(\"msg\", fileJA);\n");
			sb.append("		}\n");
			sb.append("		catch (Exception e)\n");
			sb.append("		{\n");
			sb.append("			resultJO.accumulate(\"state\", \"failure\");\n");
			sb.append("			resultJO.accumulate(\"msg\", \"上传失败!\");\n");
			sb.append("		}\n");
			sb.append("		out.print(resultJO.toString());\n");
			sb.append("	}\n");
			sb.append("}\n");
			
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
