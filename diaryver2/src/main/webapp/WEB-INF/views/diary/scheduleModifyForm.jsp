<%@ page language="java" contentType="text/html; charset=EUC-KR"  pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	/* 
	 update 할때 화면에 바로 뿌려주고 해당데이터를 삭제하고 form에서변경후 바로 add함
	*/
	 $('#repeat').click(function(){
		console.log($('#repeat').is(':checked'));
		console.log($('#scheduleDate').val().length);
		if($('#repeat').is(':checked')){
			$('#scheduleDate').val($('#srcScheduleDate').val().substr(5));
		}else{
			$('#scheduleDate').val($('#srcScheduleDate').val());
		}
	 });		
	
	$('#scheduleUpdate').click(function(){
		if($('#scheduleTitle').val()==''){
			$('#scheduleTitle').focus();
			$('#scheduleTitleHelper').text('제목을 입력하세요');
		}else {
			$('#scheduleUpdateForm').submit();
		}	
	});
});
</script>
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<div class = "page-header">
	   <h1 align="center"><a href="/diary/index" >CALENDAR</a></h1>
	   <h2 align="center"><small>BEST CALENDAR APP EVER!</small></h2>
	</div>
	<h2>Schedule Update</h2>
	<form id="scheduleUpdateForm" action="/diary/ScheduleAdd" method="post">
		<table class="table">	
			<input type="hidden" name="srcScheduleDate" id="srcScheduleDate" value="${srcScheduleDate}"/>		
			<tr>
				<td>DATE</td>
				<td> <input type="text" id="scheduleDate" name="scheduleDate" value="${scheduleModify.scheduleDate}" readonly="readonly"/>
				<c:if test="${scheduleModify.repeat=='repeat'}">
				<input type="checkbox" id="repeat" name="repeat" value="repeat" checked="checked"/>매년반복	
				</c:if>
				<c:if test="${scheduleModify.repeat==null||repeat==''}">
				<input type="checkbox" id="repeat" name="repeat" value="repeat" />매년반복	
				</c:if>
				</td>
			</tr>				
			<tr>
				<td>TITLE</td>
				<td><input type="text"   id="scheduleTitle"  name="scheduleTitle" class="form-control" value="${scheduleModify.scheduleTitle}"/></td>
			</tr>
			<tr>
				<td>TIME</td>
				<td><input type="time" name="scheduleTime" cleass="form-control" value="${scheduleModify.scheduleTime}"/></td>
			</tr>
			<tr>
				<td>MEMO</td>
				<td><textarea  name="scheduleMemo" class="form-control" />${scheduleModify.scheduleMemo}</textarea></td>
			</tr>
			<tr>
				<td>PLACE</td>
				<td><input type="text" name="schedulePlace" class="form-control" value="${scheduleModify.schedulePlace}"/></td>
			</tr>
			<tr>
				<td>FONT COLOR</td>
				<td><input type="color" name="scheduleFontColor" value="${scheduleModify.scheduleFontColor }" /></td>
			</tr>		
			<tr>
				<td colspan="2"><input type = "button" class="btn btn-default btn-lg btn-block" id="scheduleUpdate" value="완료"/></td>
			</tr>			
		</table>
	</form>
</div>	
</body>
</html>