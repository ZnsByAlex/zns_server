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
	
		function addUserSuccess(data){
			//请求成功,验证信息
			console.log("request success");
			console.log("status:"+data.status);
			data = eval("(" + data + ")");
            alert(data.status);
			if(data.status == "200"){
				//验证成功，跳转到点餐页面
				console.log("验证成功");
				alert("添加成功");
				window.location.href = "../user/user";
			}else{
				//验证失败，弹出错误信息
				console.log("添加失败");
			}
		}
		
		function deleteSingleSuccess(data){
			//请求成功,验证信息
			//var obj = JSON.parse(data);
			data = eval("(" + data + ")");
			if(data.status == "200"){
				//验证成功，跳转到点餐页面
				console.log("验证成功");
				alert("删除成功");
				window.location.href = "../user/user";
			}else{
				//验证失败，弹出错误信息
				console.log("删除失败");
			}
		}
		
		function getUserSuccess(data){
			//请求成功,验证信息
			console.log("request success");
			console.log("status:"+data.status);
			//var obj = JSON.parse(data);
			data = eval("(" + data + ")");
			
			var userInfo = data.userInfo;
			if(data.status == "200"){
				//alert(data.userInfo.userNo);
				//document.getElementById("userNo").value="test";
				//$('#userNo').val("");
				$('#id').val(data.userInfo.id);
				$('#userNo').val(data.userInfo.userNo);
				$('#userName').val(data.userInfo.userName);
				$('#userPwd').val(data.userInfo.userPwd);
				$('#tel').val(data.userInfo.tel);
				$('#school').val(data.userInfo.school);
				$('#type').val(data.userInfo.type);
			}else{
				//验证失败，弹出错误信息
				console.log("获取失败");
			}
		}
		
		function getExamSuccess(data){
			//请求成功,验证信息
			console.log("request success");
			console.log("status:"+data.status);
			//var obj = JSON.parse(data);
			data = eval("(" + data + ")");
			
			if(data.status == "200"){
				$('#examinationtitle').val(data.examInfo[0].examinationtitle);
				var score = data.examInfo[0].score;
				alert(score);
				if(score == null){
					$('#score').val("未打分");
				}else
					$('#score').val(data.examInfo[0].score);
			}else if(data.status == "201"){
				$('#examinationtitle').val("未做题");
			}else{
				//验证失败，弹出错误信息
				console.log("获取失败");
			}
		}
		
		//网络错误
		function onError(data, status){
			//请求失败，弹出错误信息
			alert("失败");
			//console.log("data:"+data+"\nstatus:"+status);
		}
		
		
		function deleteSelectSuccess(data,status){
			if(data == "1"){
				console.log("删除选中项成功！");
				window.location.href = "../user/user";
			}
			console.log(data);
		}
        
        function addSelectFoodSuccess(data,status){
			if(data == "1"){
				console.log("添加选中项成功！");
                alert("添加到菜单成功");
			}
			console.log(data);
		}
		
		function deleteSingleForm(data,status){
			if(data == "1"){
				console.log("删除选中项成功！");
				window.location.href = "../user/user";
			}
			console.log(data);
		}
		
		function deleteSingleFood(foodvalue){
			console.log(foodvalue);
			theData = "Id="+foodvalue;
			console.log("data:"+theData);
			$.ajax({
				type: "POST",
				url: "delete",
				cache: false,
				data: theData,
				dataType: "json",
				success: deleteSingleSuccess,
				error: onError
			});
		}
		
		$(document).ready(function(){
			/*添加单个菜品*/
			$('#deleteAllSelete').bind('click',function(){
				$('#operation').val("deleteFood");
				var allfoodform = $('#allfoodform').serialize();
				console.log(allfoodform);
				$.ajax({
                  type: "POST",
                  url: "deleteall",
                  cache: false,
                  data: allfoodform,
                  dataType: "json",
                  success: deleteSelectSuccess,
                  error: onError
                });
			});
			
			$('#add-user').bind('click',function(){
                if(checkName()&&checkPwd()&&checkUserNo){
                	var userId = $('#id').val();
                	alert(userId);
                	if(userId == ""){
                		insertUser();
                	}else{
                		
                		updateUser();
                	}
                }
				return false;
			});
            
            function insertUser(){
            	var userData = $('#userData').serialize();
				$.ajax({
                  type: "POST",
                  url: "insert",
                  cache: false,
                  data:userData,
                  dataType: "json",
                  success: function(data){
                	  data = eval("(" + data + ")");
          				if(data.status == "200"){
          				//验证成功，跳转到点餐页面
          					console.log("验证成功");
          					alert("添加成功");
          					window.location.href = "../user/user";
          				}else{
          				//验证失败，弹出错误信息
          				console.log("添加失败");
          				}
                  },
                  error: onError
                });
            }
            
            function updateUser(){
            	var userData = $('#userData').serialize();
				$.ajax({
                  type: "POST",
                  url: "update",
                  cache: false,
                  data:userData,
                  dataType: "json",
                  success: function(data){
                	  data = eval("(" + data + ")");
          				if(data.status == "200"){
          				//验证成功，跳转到点餐页面
          					console.log("验证成功");
          					alert("添加成功");
          					window.location.href = "../user/user";
          				}else{
          				//验证失败，弹出错误信息
          				console.log("添加失败");
          				}
                  },
                  error: onError
                });
            }
            
            function checkName()  //检查Name
            {
                var myname=document.getElementById("userName").value;
                var myDivname=document.getElementById("userNameMessage");
                if(myname=="")
                {
                    myDivname.innerHTML="<font color='red'>(User Name Can't Be Empty!)</font>";
                    return false;
                }
                else
                {
                    myDivname.innerHTML="<font color='red'>√</font>";
                    return true;
                }
            }
        
            function checkPwd()  //检查Pwd
            {
                var myname=document.getElementById("userPwd").value;
                var myDivname=document.getElementById("userPwdMessage");
                if(myname=="")
                {
                    myDivname.innerHTML="<font color='red'>(User Password Can't Be Empty!)</font>";
                    return false;
                }
                for(var i=0;i<myname.length;i++)
                {
                    var text=myname.charAt(i);
                    if(!(text<=9&&text>=0))
                    {
                     myDivname.innerHTML="<font color='red'>(User Password Must Be Composed Of Digital!)</font>";
                     break;
                    }
                }
                if(i>=myname.length)
                {
                    myDivname.innerHTML="<font color='red'>√</font>";
                    return true;
                }
            }
        
            function checkUserNo()  //检查foodMeterial
            {
                var myname=document.getElementById("userNo").value;
                var myDivname=document.getElementById("userNoMessage");
                if(myname=="")
                {
                    myDivname.innerHTML="<font color='red'>(User No Can't Be Empty!)</font>";
                    return false;
                }
                else
                {
                    myDivname.innerHTML="<font color='red'>√</font>";
                    return true;
                }
            }
            
            function SelectAll() {
 				var checkboxs=document.getElementsByName("foodCheckbox[]");
 				for (var i=0;i<checkboxs.length;i++) {
  				var e=checkboxs[i];
  				e.checked=!e.checked;
 				}
			}
			
			$('.deleteSingleUser').bind('click',function(){
				theData = $(this).val();
				console.log("data:"+theData);
				$.ajax({
					type: "POST",
					url: "delete",
					cache: false,
                    data: {id:theData},
                    dataType: "json",
					success: deleteSingleSuccess,
					error: onError
				});
			});
			
			$('.editSingleUser').bind('click',function(){
				theData = $(this).val();
				console.log("data:"+theData);
				$.ajax({
					type: "POST",
					url: "selectid",
                    data: {id:theData},
                    dataType: "json",
					success: getUserSuccess,
					error: onError
				});
			});
			
			$('.viewUserScore').bind('click',function(){
				
				theData = $(this).val();
				$('#setScore').val(theData);
				console.log("data:"+theData);
				$.ajax({
					type: "POST",
					url: "selectExam",
                    data: {userNo:theData},
                    dataType: "json",
					success: getExamSuccess,
					error: onError
				});
			});
			
			$('#setScore1').bind('click',function(){

				theData = $(this).val();
				var examUserNo = $('#examUserNo').val();
				console.log("data:"+theData);
				$.ajax({
					type: "POST",
					url: "selectUserExam",
                    data: {userNo:examUserNo},
                    dataType: "json",
					success: function(){
						
					},
					error: onError
				});
			});
            
            $('#preview').bind('click',function(){
				$('#userNo').val("");
                $('#userName').val("");
                $('#userPwd').val("");
			});
            //全选or反选
            $('#selectall').bind('click',function(){
                SelectAll();
			});
		});
	</script>
</head>
<body>
<!-- Header -->
<div id="header">
	<div class="shell">
		<!-- Logo + Top Nav -->
		<div id="top">
			<h1><a href="#">物流管理系统</a></h1>
			<div id="top-navigation">
				欢迎 <a href="#"><strong>admin</strong></a>
				<span>|</span>
				<a href="#">帮助</a>
				<span>|</span>
				<a href="#">设置</a>
				<span>|</span>
				<a href="#">退出登录</a>
			</div>
		</div>
		<!-- End Logo + Top Nav -->
		
		<!-- Main Nav -->
		<div id="navigation">
			<ul>
			    <li><a href="../user/user" class="active"><span>用户列表</span></a></li>
			    <li><a href="../examInfo/getExamInfoList"><span>题目列表</span></a></li>
			    <li><a href="../client/getClientList"><span>客户列表</span></a></li>
			    <li><a href="../zutuoGoods/getZutuoList"><span>货物列表</span></a></li>
			    <li><a href="../material/getMaterialList"><span>物料列表</span></a></li>
			    <li><a href="../order/getOrderList"><span>订单列表</span></a></li>
			</ul>
		</div>
		<!-- End Main Nav -->
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
			
			<!-- Content -->
			<div id="content">
				
				<!-- Box -->
				<div class="box">
					<!-- Box Head -->
					<div class="box-head">
						<h2 class="left">用户列表</h2>
                        <!--
						<div class="right">
							<label>search articles</label>
							<input type="text" class="field small-field" />
							<input type="submit" class="button" value="search" />
						</div>
   						-->
					</div>
					<!-- End Box Head -->	

					<!-- Table -->
					<div class="table">
						<form id="allfoodform" action="#" method="POST" >
							<input id="operation" type="hidden" name="Operation" value="deleteFood">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<th width="13"><input type="checkbox" class="checkbox" id="selectall"/></th>
									<th>用户编号</th>
									<th>用户名</th>
									<th>手机号码</th>
									<th>学校</th>
									<th width="200" class="ac">操作</th>
								</tr>

							<c:forEach items="${User}" var="item">
								<tr class="odd">
									<td><input type="checkbox" class="checkbox" name="foodCheckbox[]" value="${item.id}"/></td>
									<td>${item.userNo}</td>
									<td>${item.userName}</td>
									<td>${item.tel}</td>
									<td>${item.school}</td>
									<td>
									<button id="viewUserScore" class="viewUserScore button" type="button" value="${item.userNo}" style="margin: 0 10px;">查看分数</button>
									<button id="deleteSingleUser" class="deleteSingleUser button" value="${item.id}" style="margin: 0 10px;">删除</button>
									<button id="editSingleUser" class="editSingleUser button" type="button" value="${item.id}">编辑</button></td>
								</tr>
							</c:forEach>
							</table>
						</form>
						
						
					</div>
					<!-- Table -->
					
				</div>
				<!-- End Box -->
				
				<!-- Box -->
				<div class="box">
					<!-- Box Head -->
					<div class="box-head">
						<h2>用户信息</h2>
					</div>
					<!-- End Box Head -->
					
					<form action="#" method="post" id="userData" class="userData" name="userData">
						<!-- Form -->
						<div class="form">
								<input type="hidden" class="field size1" name="id" id="id"/>
                                <p>
                                    <label>用户编号<span id="userNoMessage">(Required Field)</span></label>
                                    <input type="text" class="field size1" name="userNo" id="userNo"/>
                                </p>
								<p>
									<label>用户名<span id="userNameMessage">(Required Field)</span></label>
									<input type="text" class="field size1" name="userName" id="userName"/>
								</p>
                            	<p>
									<label>密码<span id="userPwdMessage">(Required Field)</span></label>
									<input type="text" class="field size1" name="userPwd" id="userPwd"/>
								</p>
                            	<p>
									<label>手机号码<span id="userTelMessage">(Required Field)</span></label>
									<input type="text" class="field size1" name="tel" id="tel"/>
								</p>
                                <p>
									<label>学校<span id="schoolMessage">(Required Field)</span></label>
									<input type="text" class="field size1" name="school" id="school"/>
								</p>
								<p>
									<label>类型<span id="typeMessage">(Required Field)</span></label>
									<select id="type" name="type">
										<option value="1">管理员</option>
										<option value="2">用户</option>
									</select>

								</p>
							
						</div>
						<!-- End Form -->
						
						<!-- Form Buttons -->
						<div class="buttons">
             	 			<input type="hidden" value="insert" name="Operation">
                            <input type="button" class="button" value="重置" id="preview"/>
							<button id="add-user" class="button" data-inline="true">提交</button>
						</div>
						<!-- End Form Buttons -->
					</form>
				</div>
				<!-- End Box -->

			</div>
			<!-- End Content -->
			
			<!-- Sidebar -->
			<div id="sidebar">
				
				<!-- Box -->
				<div class="box">
					
					<!-- Box Head -->
					<div class="box-head">
						<h2>题目信息</h2>
					</div>
					<!-- End Box Head-->
					
					<div class="box-content">
					<div class="form">
						<input type="hidden" class="field size1" name="examUserNo" id="examUserNo"/>
						<input type="hidden" class="field size1" name="examId" id="examId"/>
					 	<p>
							<label>题目标题<span id="2Message"></span></label>
							<input type="text" class="field size4" name="examinationtitle" id="examinationtitle"/>
					 	</p>
					 	<p>
							<label>分数<span id="3Message"></span></label>
							<input type="text" class="field size4" name="score" id="score"/>
					 	</p>
					 </div>	
					<div class="buttons">
					<form id="set" action="selectExamInfo" method="POST" >
						<button id="setScore" name="userNo" value="">打分</button>
					</form>
					</div>
					</div>
				</div>
				<!-- End Box -->
			</div>
			<!-- End Sidebar -->
			
			<div class="cl">&nbsp;</div>			
		</div>
		<!-- Main -->
	</div>
</div>
<!-- End Container -->
</body>
</html>