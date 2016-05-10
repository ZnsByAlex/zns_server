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
				window.location.href = "user";
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
				window.location.href = "user";
			}else{
				//验证失败，弹出错误信息
				console.log("删除失败");
			}
		}
		
		function getInfoSuccess(data){
			//请求成功,验证信息
			console.log("request success");
			console.log("status:"+data.status);
			//var obj = JSON.parse(data);
			data = eval("(" + data + ")");
			
			var examInfoInfo = data.examInfoInfo;
			if(data.status == "200"){
				//alert(data.userInfo.userNo);
				//document.getElementById("userNo").value="test";
				//$('#userNo').val("");
				$('#recordid').val(data.examInfoInfo.recordid);
				$('#examinationno').val(data.examInfoInfo.examinationno);
				$('#examinationtitle').val(data.examInfoInfo.examinationtitle);
				$('#examinfourl').val(data.examInfoInfo.examinfourl);
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
				window.location.href = "user";
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
				window.location.href = "user";
			}
			console.log(data);
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
			
			$('#add').bind('click',function(){
                if(checkA()&&checkB()&&checkC()){
                	var id = $('#recordid').val();
                	alert(id);
                	if(id == ""){
                		insertInfo();
                	}else{
                		updateInfo();
                	}
                }
				return false;
			});
            
            function insertUser(){
            	var myData = $('#myData').serialize();
				$.ajax({
                  type: "POST",
                  url: "insert",
                  cache: false,
                  data:myData,
                  dataType: "json",
                  success: function(data){
                	  data = eval("(" + data + ")");
          				if(data.status == "200"){
          				//验证成功，跳转到点餐页面
          					console.log("验证成功");
          					alert("添加成功");
          					window.location.href = "user";
          				}else{
          				//验证失败，弹出错误信息
          				console.log("添加失败");
          				}
                  },
                  error: onError
                });
            }
            
            function updateInfo(){
            	var myData = $('#myData').serialize();
				$.ajax({
                  type: "POST",
                  url: "update",
                  cache: false,
                  data:myData,
                  dataType: "json",
                  success: function(data){
                	  data = eval("(" + data + ")");
          				if(data.status == "200"){
          				//验证成功，跳转到点餐页面
          					console.log("验证成功");
          					alert("添加成功");
          					window.location.href = "user";
          				}else{
          				//验证失败，弹出错误信息
          				console.log("添加失败");
          				}
                  },
                  error: onError
                });
            }
            
            function checkA()  //检查Name
            {
                var myname=document.getElementById("examinationno").value;
                var myDivname=document.getElementById("aMessage");
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
        
            function checkB()  //检查Pwd
            {
                var myname=document.getElementById("examinationtitle").value;
                var myDivname=document.getElementById("bMessage");
                if(myname=="")
                {
                    myDivname.innerHTML="<font color='red'>(User Password Can't Be Empty!)</font>";
                    return false;
                }
                else
                {
                    myDivname.innerHTML="<font color='red'>√</font>";
                    return true;
                }
            }
        
            function checkC()  //检查foodMeterial
            {
                var myname=document.getElementById("examinfourl").value;
                var myDivname=document.getElementById("cMessage");
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
			
			$('.deleteSingleInfo').bind('click',function(){
				console.log("a的值为:"+$('.deleteSingleInfo').val());
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
			
			$('.editSingleInfo').bind('click',function(){
				console.log("a的值为:"+$('.editSingleInfo').val());
				theData = $(this).val();
				console.log("data:"+theData);
				$.ajax({
					type: "POST",
					url: "selectid",
                    data: {recordid:theData},
                    dataType: "json",
					success: getInfoSuccess,
					error: onError
				});
			});
			
			$('#viewMaterial').bind('click',function(){
				theData = $(this).val();
				console.log("data:"+theData);
				$.ajax({
					type: "POST",
					url: "selectMateriaById",
                    data: {recordid:theData},
                    dataType: "json",
					success: viewSuccess,
					error: onError
				});
			});
			
			$('#viewZutuo').bind('click',function(){
				theData = $(this).val();
				console.log("data:"+theData);
				$.ajax({
					type: "POST",
					url: "selectGoodById",
                    data: {recordid:theData},
                    dataType: "json",
					success: getInfoSuccess,
					error: onError
				});
			});
			
			$('#viewOrder').bind('click',function(){
				theData = $(this).val();
				console.log("data:"+theData);
				$.ajax({
					type: "POST",
					url: "selectOrderById",
                    data: {recordid:theData},
                    dataType: "json",
					success: getInfoSuccess,
					error: onError
				});
			});
			
			$('.viewExam').bind('click',function(){
				
				theData = $(this).val();
				console.log("data:"+theData);
				$.ajax({
					type: "POST",
					url: "selectid",
                    data: {userNo:theData},
                    dataType: "json",
					success: getExamSuccess,
					error: onError
				});
			});
			
			function viewSuccess(data){
				if(data.status == "200"){
					window.location.href = "exam";
				}else{
					console.log("无数据");
				}
			}
            
            $('#preview').bind('click',function(){
            	$('#clientId').val("");
				$('#clientName').val("");
                $('#clientShortName').val("");
                $('#clientType').val("");
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
			    <li><a href="../user/user"><span>用户列表</span></a></li>
			    <li><a href="../examInfo/getExamInfoList" class="active"><span>题目列表</span></a></li>
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
						<h2 class="left">题目列表</h2>
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
									<th>题目编号</th>
									<th>题目标题</th>
									<th>说明网页</th>
									<th width="110" class="ac">操作</th>
								</tr>

							<c:forEach items="${ExamInfo}" var="item">
								<tr class="odd">
									<td><input type="checkbox" class="checkbox" name="foodCheckbox[]" value="${item.recordid}"/></td>
									<td>${item.examinationno}</td>
									<td>${item.examinationtitle}</td>
									<td>${item.examinfourl}</td>
									<td>

									<button class="deleteSingleInfo button" value="${item.recordid}" style="margin: 0 10px;">删除</button>
									<button class="editSingleInfo button" type="button" value="${item.recordid}">编辑</button></td>
								</tr>
							</c:forEach>
							</table>
						</form>
						
						<!-- Pagging 
						<div class="pagging">
							<div class="left">Showing 1-12 of 44</div>
							<div class="right">
								<a href="#">Previous</a>
								<a href="#">1</a>
								<a href="#">2</a>
								<a href="#">3</a>
								<a href="#">4</a>
								<a href="#">245</a>
								<span>...</span>
								<a href="#">Next</a>
								<a href="#">View all</a>
							</div>
						</div>
						 End Pagging -->
						
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
					
					<form action="#" method="post" id="myData" class="myData" name="myData">
						<!-- Form -->
						<div class="form">
								<input type="hidden" class="field size1" name="recordid" id="recordid"/>
                                <p>
                                    <label>题目编号<span id="aMessage">(Required Field)</span></label>
                                    <input type="text" class="field size1" name="examinationno" id="examinationno"/>
                                </p>
								<p>
									<label>题目标题<span id="bMessage">(Required Field)</span></label>
									<input type="text" class="field size1" name="examinationtitle" id="examinationtitle"/>
								</p>
                            	<p>
									<label>说明网站<span id="cMessage">(Required Field)</span></label>
									<input type="text" class="field size1" name="examinfourl" id="examinfourl"/>
								</p>
							
						</div>
						<!-- End Form -->
						
						<!-- Form Buttons -->
						<div class="buttons">
             	 			<input type="hidden" value="insert" name="Operation">
                            <input type="button" class="button" value="重置" id="preview"/>
							<button id="add" class="button" data-inline="true">提交</button>
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
						<h2>题目列表</h2>
					</div>
					<!-- End Box Head-->
					
					<div class="box-content">
						
						<c:forEach items="${ExamInfo}" var="item">
						<form id="select" action="selecExamInfo" method="POST">
						<div float:right;>
						<span>${item.examinationtitle}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<button id="view" name="examId" value="${item.recordid}">题目内容</button>
						</div>
						</form>
		
						<div class="sort">
						</div>
						
						</c:forEach>
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