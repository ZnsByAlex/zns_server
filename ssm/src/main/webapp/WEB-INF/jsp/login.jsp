<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/ssm/static/css/style.css" type="text/css" media="all" />
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js" type="text/javascript"></script>
<script>
	
		function loginSuccess(data){
			data = eval("(" + data + ")");
            alert(data.status);
			if(data.status == "200"){
				window.location.href = "../user/user";
			}else{
				//验证失败，弹出错误信息
				console.log("账号密码错误！");
				alert("账号密码错误！");
			}
		}

		//网络错误
		function onError(data, status){
			//请求失败，弹出错误信息
			alert("失败");
			//console.log("data:"+data+"\nstatus:"+status);
		}
		
		$(document).ready(function(){	
			$('#toLogin').bind('click',function(){
				var theUserNo = $('#userNo').val();
				var thePwd = $('#password').val();
				$.ajax({
                    type: "POST",
                    url: "adminLogin",
                    cache: false,
                    data: {userNo:theUserNo,userPwd:thePwd},
                    dataType: "json",
                    success: loginSuccess,
                    error: onError
                });
			});
		});	

	</script>
</head>
<body>
<!-- Header -->
<div id="header">
	<div class="shell">

	</div>
</div>
<!-- End Header -->
<div class="copyrights">Collect from <a href="http://www.mycodes.net/" ></a></div>

<!-- Container -->
<div id="container">
	<div class="shell">	
		
		<!-- Main -->
		<div id="main">
			<div class="cl">&nbsp;</div>
				
				<!-- Box -->
				<div class="sbox size5 ac">
					<!-- Box Head -->
					<div class="box-head">
						<h2 class="jz">登录</h2>

					</div>
					<!-- End Box Head -->	
					<p>
                    	<div class="p10">用户名</div>
                       	<input type="text" class="field size6 p10" name="userNo" id="userNo"/>
                   	</p>
					<p>
						<div class="p10">密码</div>
						<input type="password" class="field size6 p10" name="password" id="password"/>
					</p>
					<div>&nbsp;</div>
					<p>
					<button id="toLogin" name="toLogin" class="toLogin button2">登录</button>
					</p>
					<div>&nbsp;</div>
				</div>
				<!-- End Box -->
						
		</div>
		<!-- Main -->
	</div>
</div>
<!-- End Container -->
</body>
</html>