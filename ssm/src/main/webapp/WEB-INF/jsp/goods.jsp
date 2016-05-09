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
			
			var zutuoGoodsInfo = data.zutuoGoodsInfo;
			if(data.status == "200"){
				//alert(data.userInfo.userNo);
				//document.getElementById("userNo").value="test";
				//$('#userNo').val("");
				$('#goodsno').val(data.zutuoGoodsInfo.goodsno);
				$('#goodsname').val(data.zutuoGoodsInfo.goodsname);
				$('#goodsnum').val(data.zutuoGoodsInfo.goodsnum);
				$('#receptorderno').val(data.zutuoGoodsInfo.receptorderno);
				$('#orderfromno').val(data.zutuoGoodsInfo.orderfromno);
				$('#goodsunit').val(data.zutuoGoodsInfo.goodsunit);
				$('#spec').val(data.zutuoGoodsInfo.spec);
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
                	var id = $('#goodsno').val();
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
                var myname=document.getElementById("goodsname").value;
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
                var myname=document.getElementById("goodsnum").value;
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
                var myname=document.getElementById("receptorderno").value;
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
				theData = $(this).val();
				var id = 1;
				console.log("data:"+theData);
				$.ajax({
					type: "POST",
					url: "selectid",
                    data: {examid:id,goodsno:theData},
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
			    <li><a href="../user/testUser" class="active"><span>用户列表</span></a></li>
			    <li><a href="../examInfo/getExamInfoList"><span>题目列表</span></a></li>
			    <li><a href="../client/getClientList"><span>客户列表</span></a></li>
			    <li><a href="../zutuoGoods/getZutuoList"><span>货物列表</span></a></li>
			    <li><a href="editFood.php"><span>物料列表</span></a></li>
			    <li><a href="canteen.php"><span></span></a></li>
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
						<h2 class="left">物料列表</h2>
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
									<th>物料名称</th>
									<th>物料数量</th>
									<th>入库单编号</th>
									<th>订单编号</th>
									<th>单位</th>
									<th>规格</th>
									<th width="110" class="ac">操作</th>
								</tr>

							<c:forEach items="${ZutuoGoods}" var="item">
								<tr class="odd">
									<td><input type="checkbox" class="checkbox" name="foodCheckbox[]" value="${item.goodsno}"/></td>
									<td>${item.goodsname}</td>
									<td>${item.goodsnum}</td>
									<td>${item.receptorderno}</td>
									<td>${item.orderfromno}</td>
									<td>${item.goodsunit}</td>
									<td>${item.spec}</td>
									<td>

									<button class="deleteSingleInfo button" value="${item.goodsno}" style="margin: 0 10px;">删除</button>
									<button class="editSingleInfo button" type="button" value="${item.goodsno}">编辑</button></td>
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
						<h2>添加货物</h2>
					</div>
					<!-- End Box Head -->
					
					<form action="#" method="post" id="myData" class="myData" name="myData">
						<!-- Form -->
						<div class="form">
								<input type="hidden" class="field size1" name="goodsno" id="goodsno"/>
                                <p>
                                    <label>客户名称<span id="aMessage">(Required Field)</span></label>
                                    <input type="text" class="field size1" name="goodsname" id="goodsname"/>
                                </p>
								<p>
									<label>客户简称<span id="bMessage">(Required Field)</span></label>
									<input type="text" class="field size1" name="goodsnum" id="goodsnum"/>
								</p>
                            	<p>
									<label>客户类型<span id="cMessage">(Required Field)</span></label>
									<input type="text" class="field size1" name="receptorderno" id="receptorderno"/>
								</p>
                            	<p>
									<label>地址<span id="dMessage">(Required Field)</span></label>
									<input type="text" class="field size1" name="orderfromno" id="orderfromno"/>
								</p>
                                <p>
									<label>联系方式<span id="eMessage">(Required Field)</span></label>
									<input type="text" class="field size1" name="goodsunit" id="goodsunit"/>
								</p>
								<p>
									<label>订单编号<span id="fMessage">(Required Field)</span></label>
									<input type="text" class="field size1" name="spec" id="spec"/>
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
						<h2>信息</h2>
					</div>
					<!-- End Box Head-->
					
					<div class="box-content">
                        <a href="#" class="add-button" id="addAllToMenu"><span>暂无信息</span></a>
						<div class="cl">&nbsp;</div>

						<p><button id="deleteAllSelete">删除信息</button></p>
						
						<!-- Sort -->
						<div class="sort">
							<label></label>
							
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