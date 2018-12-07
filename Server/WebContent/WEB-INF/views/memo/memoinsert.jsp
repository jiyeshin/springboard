<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메모 작성</title>
</head>
<body>
	<form enctype="multipart/form-data" method="post">
		<p align="center">
		<table border="1" width="50%" height="80%" align='center'>
			<tr>
				<td colspan="3" align="center"><h2>메모 작성</h2></td>
			</tr>
			<tr>
				<td rowspan="5" align="center">
					<p></p> <img id="img" width="100" height="100" border="1" /> <br />
					<br /> <input type='file' id="image_path" name="image_path" /><br />
				</td>
			</tr>

			<tr>
				<td bgcolor="#f5f5f5"><font size="2">&nbsp;&nbsp;&nbsp;&nbsp;제목</font></td>
				<td>&nbsp;&nbsp;&nbsp; <input type="text" name="title"
					id="title" size="30" maxlength=50 required="required" />
				</td>
			</tr>

			<tr>
				<td bgcolor="#f5f5f5"><font size="2">&nbsp;&nbsp;&nbsp;&nbsp;내용</font></td>
				<td>&nbsp;&nbsp;&nbsp; <textarea rows="10" cols="30"
						name="contents" id="contents"></textarea>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="3">
					<p></p> <input type="button" value="메모저장" id="insertbtn" /> 
					<input type="button" value="목록보기" onclick="javascript:window.location='../'" />
					<p></p>
				</td>
			</tr>
		</table>
	</form>
	<br />
	<br />
</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	var filename = ''
		//파일 선택 버튼에서 파일의 내용이 변경될 때 이미지 파일인 경우
		//이미지 파일의 내용을 img 태그에 출력하도록 해주는 스크립트
	document.getElementById("image_path").addEventListener('change',function(e) {
						//선택된 파일이 있다면
						//어떤 프로그래밍 언어에서는 숫자의 경우 0이 아니면 true로 간주
						//참조형의 경우는 null이 아니면 true로 간주
						if (this.files && this.files[0]) {
							filename = this.files[0].name;
							var ext = filename.substr(filename.length - 3, filename.length);
							var isCheck = false;
							if ((ext.toLowerCase() == "jpg" || ext.toLowerCase() == "gif" || ext.toLowerCase() == "png")) {
								isCheck = true;
							}

							if (isCheck == false) {
								// 이 작업을 하지 않으면 선택한 파일의 경로가 남아있게 된다. 
								alert("jpg나 gif, png 만 업로드가 가능합니다.");
								document.getElementById('image_path').value = ""
								return;
							}
							
							//파일 내용을 읽을 수 있는 객체를 생성
							var reader = new FileReader()
							
							//파일의 내용을 읽기를 요청 - 비동기 요청
							reader.readAsDataURL(this.files[0])
							
							//파일의 내용을 전부 읽으면 호출되는 콜백 처리
							reader.onload = function(e){
								document.getElementById("img").src = e.target.result;
							}
						}
					});
	document.getElementById("insertbtn").addEventListener("click", function() {
						var formdata = new FormData()
						formdata.append("title", document.getElementById("title").value)
						formdata.append("contents", document.getElementById("contents").value)
						formdata.append("image_path", document.getElementById("image_path").files[0])

						$.ajax({
							url 		: "memoinsert",
							data 		: formdata,
							processData : false,
							contentType : false,
							type		 : 'POST',
							success 	: function(data) {
								if (data.result == "success")
									location.href = "../"
								else
									alert("삽입실패")
							}
						});
					});
</script>
</html>











