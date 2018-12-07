<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<html>
<head>
	<title>메인화면</title>
	
</head>
<body>
	<input type="button" value="삽입" id="insertbtn" />
	<div id = "detaildisplay"></div>
	<div id = "listdisplay"></div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script>
document.getElementById("insertbtn").addEventListener("click", function(){
	location.href = "memo/memoinsert"
})
	//ajax로 memo/memolist 요청을 수행 
	function memolist(){
		$.ajax({		
			url			: 'memo/memolist',
			dataType	: 'json',			
			success: function(memolist){
				// listdisplay라는 id를 가진 DOM 객체를 찾아오는 것.
				// value 속성은 입력한 값을 가져오는 속성
				// innerHTML은 태그와 태그 사이의 내용을 가져오거나 설정하는 속성 
				var listdiplay = document.getElementById("listdisplay");
				
				//제목 출력 
				listdisplay.innerHTML = "<h3 align='center'> 메모 목록 </h3>"
				
				// 데이터 개수 출력 
				//listdisplay.innerHTML +="<p> 메모개수: " + memolist.totalcount + "</p>" 
				var display = "<table border = '1'>"
				display += "<tr><th>메모번호</th><th> 메모 제목 </th><th>작성일</th></tr> "
				
				var ar = memolist.memos;
				
				
				//배열 순회: 임시 변수에 인덱스가 대입 됨. 
				for (var a in ar){
					var record = ar[a]
					display += "<tr><td>" + record.NUM + "</td>";
					display += "<td><a href='#' onclick='detail(" + record.NUM + ")'/>" + record.TITLE + "</td>";					
					display += "<td>" + record.REGDATE + "</td></tr>" 
				}
				
				display += "</table>"
				listdisplay.innerHTML += display
				
			}		
		})
	}
	
	function detail(num){
		$.ajax({
			url			: "memo/memodetail",
			data		: {"num" : num},
			type		: "GET",
			dataType	: "json",
			success		: function(data){
				// DOM(Document Object Model): html 문서내에 있는 객체 
				var detaildisplay = document.getElementById("detaildisplay");
				detaildisplay.innerHTML = "<h3>메모 작성 시간: " + data.REGDATE + "</h3>"; 
				detaildisplay.innerHTML += "<p><b>메모 제목: " + data.TITLE + "</b></p>"
				detaildisplay.innerHTML += "<p><b>메모 내용: " + data.CONTENTS + "</b></p>"
				
				if(data.IMAGE_PATH != " "){
					detaildisplay.innerHTML += "<img src = 'http://localhost:8080/springboard/memoimage/" + data.IMAGE_PATH + "'/><br/>"
				}			
				detaildisplay.innerHTML += "<input type = 'button' value = '삭제' onclick = 'del(" + data.NUM + ")'/>" 
			}		
		});	
	}
	
	function del(num){
		$.ajax({
			url			: "memo/memodelete",
			data		: {"num" : num},
			type		: "POST",
			dataType	: "json",
			success		: function(map){
				if(map.result == "SUCCESS"){
					// 데이터 다시 출력
					memolist()
					document.getElementById("detaildisplay") = "";
					
				}else{
					alert("삭제 실패 ")
				}			
			}		
		})		
	}
	
	//jQuery에서 문서가 시작되자마자 수행
	$(function(){
		memolist()
	})
</script>

</html>
