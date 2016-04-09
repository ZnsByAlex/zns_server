<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/css/style.css" type="text/css" media="all" />
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- Header -->
<div id="header">
	<div class="shell">
		<!-- Logo + Top Nav -->
		<div id="top">
			<h1><a href="#">FoodleManage</a></h1>
			<div id="top-navigation">
				Welcome <a href="#"><strong>admin</strong></a>
				<span>|</span>
				<a href="#">Help</a>
				<span>|</span>
				<a href="#">Profile Settings</a>
				<span>|</span>
				<a href="#">Log out</a>
			</div>
		</div>
		<!-- End Logo + Top Nav -->
		
		<!-- Main Nav -->
		<div id="navigation">
			<ul>
			    <li><a href="foodList.php" class="active"><span>Food List</span></a></li>
			    <li><a href="menu.php"><span>Menu Today</span></a></li>
			    <li><a href="orderList.php"><span>Order List</span></a></li>
			    <li><a href="orderAmount.php"><span>Order Amount</span></a></li>
			    <li><a href="editFood.php"><span>Edit Food</span></a></li>
			    <li><a href="canteen.php"><span>Canteen</span></a></li>
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
						<h2 class="left">Food List</h2>
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
									<th>User No</th>
									<th>Name</th>
									<th>tel</th>
									<th width="110" class="ac">Content Control</th>
								</tr>

							<c:forEach items="${User}" var="item">
								<tr class="odd">
									<td><input type="checkbox" class="checkbox" name="foodCheckbox[]" value="${item.id}"/></td>
									<td><h3><a href="#">${item.userName}</a></h3></td>
									<td>${item.tel}</td>
									<td><button class="deleteSingleFood ico edit" value="${item.id}" style="margin: 0 10px;">Delete</button>
									<button class="editSingleFood ico edit" value="${item.id}"><a href="#">Edit</a></button></td>
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
						<h2>Add New Food</h2>
					</div>
					<!-- End Box Head -->
					
					<form action="#" method="post" id="foodData">
						<!-- Form -->
						<div class="form">
								<p>
									<label>Food Name<span id="foodNameMessage">(Required Field)</span></label>
									<input type="text" class="field size1" name="foodName" id="foodName"/>
								</p>
                            	<p>
									<label>Food Price<span id="foodPriceMessage">(Required Field)</span></label>
									<input type="text" class="field size1" name="foodPrice" id="foodPrice"/>
								</p>
                            	<p>
									<label>Food Meterial<span id="foodMeterialMessage">(Required Field)</span></label>
									<input type="text" class="field size1" name="foodMeterial" id="foodMeterial"/>
								</p>
                            
							
						</div>
						<!-- End Form -->
						
						<!-- Form Buttons -->
						<div class="buttons">
              <input type="hidden" value="insert" name="Operation">
                            <input type="button" class="button" value="preview" id="preview"/>
							<button id="add-food" class="button" data-inline="true">submit</button>
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
						<h2>Management</h2>
					</div>
					<!-- End Box Head-->
					
					<div class="box-content">
                        <a href="#" class="add-button" id="addAllToMenu"><span>Add To Menu</span></a>
						<div class="cl">&nbsp;</div>

						<p><button id="deleteAllSelete">delete select</button></p>
						
						<!-- Sort -->
						<div class="sort">
							<label>Sort by</label>
							<select class="field">
								<option value="">Title</option>
							</select>
							<select class="field">
								<option value="">Date</option>
							</select>
							<select class="field">
								<option value="">Author</option>
							</select>
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