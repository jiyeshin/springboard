<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<!-- html 파일에서 경로는  pageContext.request.contextPath 를 붙여서 절대 경로 형태로 사용. 
	상대경로는 헷갈림. -->
	<script src="${pageContext.request.contextPath}/resources/jquery/jquery.js">
	</script>
	<script>
	$(function(){
		alert("jquery setup")
	})
	</script>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html> 
