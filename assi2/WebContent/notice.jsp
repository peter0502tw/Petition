<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<html>
<head><title>Error</title></head>
<body>
<style>
form {
 width: 500px;
 overflow:hidden;}
label {
 clear: both;
 float: left;
 width: 40%;}
input {
 float: left;
 width: 55%;}
</style>
<h1>Notice</h1>
<%
String errorMsg="";
String errorid=request.getParameter("errorid");
if(errorid!=null){
	if(errorid.equals("1")){
		errorMsg= "Error! unauthourised please sign in ";
	}else if(errorid.equals("2")){
		errorMsg="Password is not right";
	}else if(errorid.equals("3")){
		errorMsg="insert succesfully";
	}
}else if(errorid.equals("4")||errorid.equals("5")){
	 errorMsg="Error! This is because unknown issue";
}

%>
<div>
	<label for="link"><%=errorMsg%></label>
	<label>Please <a href="login.html">go back</a></label>
</div>
</body></html>
