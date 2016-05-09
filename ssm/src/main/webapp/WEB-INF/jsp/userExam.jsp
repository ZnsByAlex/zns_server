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
		
		function addScoreSuccess(data){
            alert("添加成功");
            window.location.href = "../user/user";
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
		
		function getInfoSuccess(data){
			//请求成功,验证信息
			console.log("request success");
			console.log("status:"+data.status);
			//var obj = JSON.parse(data);
			data = eval("(" + data + ")");
			
			var clientInfo = data.clientInfo;
			if(data.status == "200"){
				//alert(data.userInfo.userNo);
				//document.getElementById("userNo").value="test";
				//$('#userNo').val("");
				$('#clientId').val(data.clientInfo.clientId);
				$('#clientName').val(data.clientInfo.clientName);
				$('#clientShortName').val(data.clientInfo.clientShortName);
				$('#clientType').val(data.clientInfo.clientType);
				$('#clientAddress').val(data.clientInfo.clientAddress);
				$('#orderFromNo').val(data.clientInfo.orderFromNo);
				$('#clientTel').val(data.clientInfo.clientTel);
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
			
			$('#add').bind('click',function(){
                if(checkA()&&checkB()&&checkC()){
                	alert("====");
                	var id = $('#clientId').val();
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
          					window.location.href = "../user/user";
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
          					window.location.href = "../user/user";
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
                var myname=document.getElementById("clientName").value;
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
                var myname=document.getElementById("clientShortName").value;
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
                var myname=document.getElementById("clientType").value;
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
                    data: {clientId:theData},
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
			
			$('#setScore').bind('click',function(){
				var theScore = $('#score').val();
				var theUserNo = $('#examUserNo').val();
				var theExamId = 1;
				alert(theUserNo);
				$.ajax({
                    type: "POST",
                    url: "../userExam/updataScoreByNo",
                    cache: false,
                    data: {userNo:theUserNo,examinationid:theExamId,score:theScore},
                    dataType: "json",
                    success: addScoreSuccess,
                    error: onError
                }); 
			});
			
			$('#addAllToMenu').bind('click',function(){
				$('#operation').val("addMenu");
				//console
				var allfoodform = $('#allfoodform').serialize();
				console.log(allfoodform);
				$.ajax({
                    type: "POST",
                    url: "operationFood.php",
                    cache: false,
                    data: allfoodform,
                    dataType: "json",
                    success: addSelectFoodSuccess,
                    error: onError
                });
			});
            
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
						<h2 class="left">当前题目</h2>
                        <!--
						<div class="right">
							<label>search articles</label>
							<input type="text" class="field small-field" />
							<input type="submit" class="button" value="search" />
						</div>
   						-->
					</div>
					<!-- End Box Head -->	

					
				</div>
				<!-- End Box -->			
			
			
				
				<!-- Box -->
				<div class="box">
					<!-- Box Head -->
					<div class="box-head">
						<h2 class="left">优先级分析</h2>
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
									<th>路线花费</th>
									<th>客户判断依据</th>
									<th>客户优先级</th>
									<th>订单有效性</th>
									<th>物料ABC分类</th>
									<th>路线</th>
								</tr>

							<c:forEach items="${AnalysisResultInfo}" var="item">
								<tr class="odd">
									<td><input type="checkbox" class="checkbox" name="foodCheckbox[]" value="${item.questioneid}"/></td>
									<td>${item.costSaving}</td>
									<td>${item.customerBasis}</td>
									<td>${item.customerSequenceUser}</td>
									<td>${item.effectiveUser}</td>
									<td>${item.examinationUserAbc}</td>
									<td>${item.optimallineUser}</td>
									
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
						<h2 class="left">入库分析</h2>
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
									<th>托盘编号</th>
									<th>物料名称</th>
									<th>物料数量</th>
									<th>仓位编号</th>
									<th>入库单编号</th>
									<th>订单编号</th>
								</tr>

							<c:forEach items="${TrayInfo}" var="item">
								<tr class="odd">
									<td><input type="checkbox" class="checkbox" name="foodCheckbox[]" value="${item.trayno}"/></td>
									<td>${item.trayno}</td>
									<td>${item.goodsname}</td>
									<td>${item.goodsnum}</td>
									<td>${item.slcationno}</td>
									<td>${item.receptorderno}</td>
									<td>${item.orderFromNo}</td>
									
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
						<h2 class="left">出库分析</h2>
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
									<th>入库编号</th>
									<th>货物名称</th>
									<th>货物数量</th>
									<th>货物单位</th>
								</tr>

							<c:forEach items="${StorageInfo}" var="item">
								<tr class="odd">
									<td><input type="checkbox" class="checkbox" name="foodCheckbox[]" value="${item.storageno}"/></td>
									<td>${item.storageno}</td>
									<td>${item.goodsname}</td>
									<td>${item.goodsnum}</td>
									<td>${item.goodsunit}</td>
									
								</tr>
							</c:forEach>
							</table>
						</form>
					</div>
					<!-- Table -->
					
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
                        <input type="hidden" class="field size1" name="examUserNo" id="examUserNo" value="${userNo}"/>
					 	<p>
							<label>题目标题<span id="2Message"></span></label>
							<input type="text" class="field size4" name="examinationtitle" id="examinationtitle"/>
					 	</p>
					 	<p>
							<label>分数<span id="3Message"></span></label>
							<input type="text" class="field size4" name="score" id="score"/>
					 	</p>
						
						<!-- Sort -->
						<div class="sort">
							<button id="setScore" name="userNo" value="" class="setScore">确定</button>
						</div>
						<!-- End Sort -->
						
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