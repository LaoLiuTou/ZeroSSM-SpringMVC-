<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>后台管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/> 
    <link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/main-min.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="header">

    <div class="dl-title" style="margin-top: 5px;">
        <font color="#ffffff" size="6">平安吉机管理后台</font>
    </div>

    <div class="dl-log">欢迎您，<span class="dl-log-user">${session_user.username }</span>
    <a href="logoutUsertable" onclick="return confirm('确定要退出登录么？')"; title="退出系统" class="dl-log-quit">[退出]</a>
      <a id="sessionOut" href="login.html"  style="display:none;"  >退出</a>
      <c:if test="${session_user.username == null}">
    
    <script Language="JavaScript">
		alert("登陆已过期，请重新登录!");
		document.getElementById("sessionOut").click();
	</script>  
	  
    </c:if>
    </div>
</div>
<div class="content">
    <div class="dl-main-nav">
        <div class="dl-inform"><div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div></div>
        <ul id="J_Nav"  class="nav-list ks-clear">
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-home">系统管理</div></li>		<li class="nav-item dl-selected"><div class="nav-item-inner nav-order">内容管理</div></li>

        </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">

    </ul>
</div>
<script type="text/javascript" src="assets/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="assets/js/bui-min.js"></script>
<script type="text/javascript" src="assets/js/common/main-min.js"></script>
<script type="text/javascript" src="assets/js/config-min.js"></script>

<script>
 
    BUI.use('common/main',function(){
        var config = [{id:'1',
                        menu:[{text:'系统管理',
                           items:[//{id:'111',text:'角色管理',href:'Role/index.html'},
                                  {id:'111',text:'用户管理',href:'listUsertable?page=1'}//,
                                  //{id:'113',text:'菜单管理',href:'Menu/index.html'}
                           ]
                         },
                         {text:'短信平台',
                           items:[//{id:'111',text:'角色管理',href:'Role/index.html'},
                                  {id:'121',text:'通讯录',href:'listPhonebook?page=1'},
                                  {id:'113',text:'发送短信',href:'sendPhonebook'}
                           ]
                         }]
                       },
                       {id:'2',
                         //homePage : '9',
                         menu:[{text:'吉机动态',
                            items:[{id:'211',text:'行政管理',href:'listContenttable.action?page=1&plateid=10001'},
                           		   {id:'212',text:'文件通知',href:'listContenttable.action?page=1&plateid=10002'},
                           		   {id:'213',text:'热点关注',href:'listContenttable.action?page=1&plateid=10003'}
                            ]
                         },
                         {text:'党群纵横',
                            items:[{id:'221',text:'党的知识',href:'listContenttable.action?page=1&plateid=10004'},
                           		   {id:'222',text:'月度例会',href:'listContenttable.action?page=1&plateid=10005'},
                           		   {id:'223',text:'企业文化',href:'listContenttable.action?page=1&plateid=10006'},
                           		   {id:'223',text:'青工风采',href:'listContenttable.action?page=1&plateid=10007'}
                            ]
                         },
                         {text:'安全视野',
                            items:[{id:'231',text:'天气预警',href:'listContenttable.action?page=1&plateid=10008'},
                           		   {id:'232',text:'规章制度',href:'listContenttable.action?page=1&plateid=10009'},
                           		   {id:'233',text:'重点事项',href:'listContenttable.action?page=1&plateid=10010'}
                            ]
                         },
                         {text:'警示之窗',
                            items:[{id:'241',text:'曝光台',href:'listContenttable.action?page=1&plateid=10011'},
                           		   {id:'242',text:'典型案例',href:'listContenttable.action?page=1&plateid=10012'},
                           		   {id:'243',text:'廉政聚焦',href:'listContenttable.action?page=1&plateid=10013'}
                            ]
                         },
                         {text:'创优争先',
                            items:[{id:'251',text:'岗区评比',href:'listContenttable.action?page=1&plateid=10014'},
                           		   {id:'252',text:'季度点评',href:'listContenttable.action?page=1&plateid=10015'},
                           		   {id:'253',text:'先进模范',href:'listContenttable.action?page=1&plateid=10016'}
                            ]
                         },
                         {text:'经营策划',
                            items:[{id:'261',text:'成本控制',href:'listContenttable.action?page=1&plateid=10017'},
                           		   {id:'262',text:'修旧利废',href:'listContenttable.action?page=1&plateid=10018'}
                            ]
                         },
                          {text:'吉机营业厅',
                            items:[{id:'271',text:'营业动态',href:'listContenttable.action?page=1&plateid=10019'},
                           		   {id:'272',text:'机型展示',href:'listContenttable.action?page=1&plateid=10020'},
                           		   {id:'273',text:'促销活动',href:'listContenttable.action?page=1&plateid=10021'}
                            ]
                         }]
                        }
                           
                       ];
                           
                 
                           
                           
        new PageUtil.MainPage({
            modulesConfig : config
        });
    });
</script>
<div style="text-align:center;">

</div>
</body>
</html>