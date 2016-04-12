<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js" type="text/javascript"></script> 
<title>添加用户</title>  
<script type="text/javascript">  
    $(function(){  
        $("form :button").click(function(){  
            var name = $("#name").val();  
            var age = $("#age").val();  
            $.ajax({  
                   type: "POST",  
                   url: "client/delete",  
                   data: {clientId:"5"},  
                   success:function(data){  
                	   data = eval("(" + data + ")");
                       alert("名字:" + data.status);  
                   },
                   dataType: "json"
            });  
        });  
    });  
</script>  
</head>  
<body>  
    <form action="user/addUser" method="post">  
        用户名:<input type="text" id="name" name="name" /><br/>  
        年龄:<input type="text" id="age" name="age" />  
        <input type="button" value="提交" />  
    </form>  
</body>  
</html>  