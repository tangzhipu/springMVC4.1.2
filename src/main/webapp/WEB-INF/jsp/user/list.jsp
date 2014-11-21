<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user list</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css" type="text/css" />

</head>
<body>
	<a href="add">添加</a><br/>  ---> 登陆用户: ${loginUser.nickname} <br/>
	<c:forEach items="${users}" var="usermap">
		<a href="${usermap.value.username}">${usermap.value.username}</a> 
		-- ${usermap.value.nickname}
		---${usermap.value.email}
		---${usermap.value.password} ----<a href="${usermap.value.username}/update">修改</a>--
		-<a href="${usermap.value.username}/delete">删除</a>
		<br/>
	</c:forEach>


</body>
</html>