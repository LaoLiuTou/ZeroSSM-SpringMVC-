<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/page-min.css" rel="stylesheet" type="text/css" />   <!-- 下面的样式，仅是为了显示代码，而不应该在项目中使用-->
   <link href="assets/css/prettify.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="assets/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="assets/js/bui-min.js"></script>
	<script type="text/javascript" src="assets/js/common/main-min.js"></script>
	<script type="text/javascript" src="assets/js/config-min.js"></script>
	<style type="text/css">
    code {
      padding: 0px 4px;
      color: #d14;
      background-color: #f7f7f9;
      border: 1px solid #e1e1e8;
    }
   </style>
  </head>
  
  <body>
      <div class="container">
    <div class="row">
     
    <c:choose>
    	<c:when test="${message == '用户名已经存在！'}">
	    	<div class="span10">
	        <div class="tips tips-large tips-warning">
	          <span class="x-icon x-icon-error">×</span>
	          <div class="tips-content">
	            <h2>失败信息</h2>
	            <p class="auxiliary-text">
	              ${message }
	            </p>
	            
	          </div>
	        </div>
	        </div> 
    	</c:when>
    	<c:when test="${message == '添加失败！'}">
	    	<div class="span10">
	        <div class="tips tips-large tips-warning">
	          <span class="x-icon x-icon-error">×</span>
	          <div class="tips-content">
	            <h2>失败信息</h2>
	            <p class="auxiliary-text">
	              ${message }
	            </p>
	            
	          </div>
	        </div>
	        </div> 
    	</c:when>
    	<c:when test="${message == '添加成功！'}">
	    	   <div class="span10">
	        <div class="tips tips-large tips-success">
	          <span class="x-icon x-icon-success"><i class="icon icon-ok icon-white"></i></span>
	          <div class="tips-content">
	            <h2>成功信息</h2>
	            <p class="auxiliary-text">
	            ${message }
	            </p>
	             
	          </div>
	        </div>
	      </div> 
    	</c:when>
    	<c:when test="${message == '更新失败！'}">
	    	<div class="span10">
	        <div class="tips tips-large tips-warning">
	          <span class="x-icon x-icon-error">×</span>
	          <div class="tips-content">
	            <h2>失败信息</h2>
	            <p class="auxiliary-text">
	              ${message }
	            </p>
	            
	          </div>
	        </div>
	        </div> 
    	</c:when>
    	<c:when test="${message == '更新成功！'}">
	    	   <div class="span10">
	        <div class="tips tips-large tips-success">
	          <span class="x-icon x-icon-success"><i class="icon icon-ok icon-white"></i></span>
	          <div class="tips-content">
	            <h2>成功信息</h2>
	            <p class="auxiliary-text">
	            ${message }
	            </p>
	             
	          </div>
	        </div>
	      </div> 
    	</c:when>
    	 
    </c:choose>
    
      
      
    </div>
  </div>
   
  </body>
</html>
