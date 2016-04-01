<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>Insert title here</title>  
<script type="text/javascript" src="http://libs.baidu.com/jquery/1.9.0/jquery.js">  
</script>  
     
    </script>  
    <script type="text/javascript">  
        function userinfo(username,password){  
            this.username=username;  
            this.password=password;  
            }  
        function sendAjax(){  
            var userinfoRef=new userinfo('高玮','12312');  
            var jsonStringRef="['{'username':'XXX' , 'userpwd':'12345'}']"; 
           // alert(jsonStringRef);
            $.post("/ssm/user/login",{ jsonString:jsonStringRef });  
        }  
    </script>  
</head>  
<body>  
    <input type="button" onclick="sendAjax()" value="登录" >  
</body>  