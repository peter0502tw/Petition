<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<title>User Registration</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style type="text/css">
<!--
@import url("style.css");
-->
</style>

<body>
	<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" import="bean.*,main.*"%>
	<%
		HttpSession se = request.getSession();
		USER_property u = null;	
		String usernameCookie = null;
		String passwordCookie = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
		for (Cookie cookie : cookies) {
		if ("SESSION_LOGIN_USERNAME".equals(cookie.getName())) {usernameCookie = cookie.getValue();
		System.out.print("SESSION_LOGIN_USERNAME".equals(cookie.getName()));}}}
		if (se.getAttribute("USER_property") != null) {
			u = (USER_property) se.getAttribute("USER_property");}
		 
		
		if (u != null||usernameCookie != null) {
			String httpname=u.get_name();
			String title="";
			String PM;
			if(u.get_PM()==0)PM="style=\"display:none\"";
			else PM="style=\"display:yes\"";
	%>

	<div class="jumbotron text-center">
		<h1>
			Welcome,<%=httpname%>!
		</h1>

	</div>
<button type="button" class="btn btn-default" name="show_insert" id="show_insert" >insert</button>
	<div class="container">



			<div class="form-group">	
				
					<input  type="text" class="form-control" placeholder="title" name="title" id="title" style="display:none" required/>
					<input type="text" class="form-control" placeholder="content" name="content" id="content" style="display: none" required/>
				<button type="button" class="btn btn-default" name="insert" id="insert" style="display: none" >&nbsp; insert</button>		
				<div class="alert alert-success" id="insert_notice" style="display:none">
				
			</div>
			
			
			<div class="alert alert-warning" id="notice_test" style="display:none">
			</div>
			<div id="test_name" style="display:yes"></div>
			</div>
			</div>

			<div class="form-group">
				<button type="button" class="btn btn-default" name="show_all" id="show_all">&nbsp; show petition</button>
				 <table id="hor-minimalist-a" summary="All petitions"  style="display:none">
    <thead>
    	<tr>
        	<th scope="col">TITLE</th>
            <th scope="col">CONTENT</th>
            <th scope="col">DATE</th>
            <th scope="col">SIGN</th>
            <th scope="col">SIGN</th>
            <th scope="col">COMMENT</th>
            
        </tr>
        
        
        
    </thead>
    <tbody id="tbody">
	
   
    </tbody>
     
</table>
<table style="border-top:3px #FFD382 solid;border-bottom:3px #82FFFF solid;" cellpadding="10" border='0'>
<tr><td id="comment" style="display:none"></td></tr>
</table>
				
			</div>
			
<div id="comment_div">
<button type="button" class="btn btn-default" name="commentPM" id="commentPM"  <%=PM%>>&nbsp; comment</button>

<select id='dropdown' name='dropdown' style="display:none">
<optgroup id="opti"></optgroup>
</select>
<textarea id='textbox_comment' style="display:none" required></textarea>
<button type="button" class="btn btn-default" name="commentPM_insert" id="commentPM_insert" style="display:none">Insert</button>

</div>			
			
		<div class="form-group" id="get_all"></div>

<script type="text/javascript">
function comment(title) {
	$.ajax({
		type: "POST",
		url:"./servlets/Comment",
		data:{title:title},
		success: function(data)
		{
			$("#comment").html(data);
			}
		});
	};
</script>

<script type="text/javascript">
var user="<%=httpname%>";
function click_num(title) {
	         $.ajax({
		     type: "POST",
			 url:"./servlets/Update_sign",
			 data:{user:user,title:title},
			 success: function(data){
				 var result=parseInt(data)
					if(result==99){
						
						alert("cannot sign this petition more than once");}
					else if(result==1){
						alert("thanks for signing");}
			 }
			 });
			 };    
</script>



<script type="text/javascript">
$(document).ready(
function() {
	var user="<%=httpname%>";
		$("#show_insert").click(
				function() {
					$("#title").toggle();
					$("#content").toggle();
					$("#insert").toggle();
					$("#insert_notice").toggle();
					$("#notice_test").toggle();
					
				});
		
		
		
		 $("#insert").click(function(){
				
				var title=$("#title").val();
				var content=$("#content").val();
				var notice=$("#insert_notice");
				//$("#notice_test").html(user);
				
				//notice.html(user);
	         $.ajax({
		     type: "POST",
			 url: " ./servlets/Insert_petition",
			 data:{user:user,title:title,content:content},
			 success: function(data){		 
				 var result=parseInt(data)
				 if(result==0){notice.html("failed");}
				 else if(result==1){notice.html("succesfully");}
			 },
	         error: function(){alert("failure");}
			 });    
	         });
		
		
});	
</script>

   
<script type="text/javascript">
		$(document).ready(
				function() {
					var user="<%=httpname%>";
					$("#show_all").click(
							function() {
								$("#hor-minimalist-a").toggle();
								$("#comment").toggle();
								var html = "";
								$.ajax({
									type : "GET",
									url : "./servlets/Get_Petition",
									data:{user:user},
									success : function(result) {
										
										$('#tbody').html(result);
										$('#hide_all').show;

										
									},

									error : function() {
										alert("failure");
									}
								});

							});

				});
	</script>


<script type="text/javascript">
$(document).ready(function() {
		$("#commentPM").click(
				function() {
					$("#dropdown").toggle();
					$("#textbox_comment").toggle();	
					$("#commentPM_insert").toggle();
		     $.ajax({
			     type: "POST",
				 url: " ./servlets/Dropdown_list",
				 success: function(data){		 
					$("#opti").html(data);
					 
				 },
		         error: function(){alert("failure");}
				 });
			
		});
});	
</script>

<script type="text/javascript">
		$(document).ready(
				function() {
					$("#commentPM_insert").click(
							function() {
								var user="<%=httpname%>";
								var comment=$("#textbox_comment").val();
								var id=$("#dropdown option:selected").val();
								$.ajax({
									type : "GET",
									url : "./servlets/Insert_comment",
									data:{comment:comment,id:id,user:user},
									success : function(result) {
									var data=parseInt(result);
										
										if(data==1)alert("successfully");
										else alert("try it again");
									},

									error : function() {
										alert("failure");
									}
								});

							});

				});
	</script>


	<div>
		<a href="Logout">Logout</a>
	</div>
<%}else {response.sendRedirect("notice.jsp?errorid=1");}%>
</body>
</html>

