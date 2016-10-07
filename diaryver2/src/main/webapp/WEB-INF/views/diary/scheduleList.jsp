<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$('#repeat').click(function(){
		console.log($('#repeat').is(':checked'));
		if($('#repeat').is(':checked')){
			$('#scheduleDate').val($('#srcScheduleDate').val().substr(5));
		}else{
			$('#scheduleDate').val($('#srcScheduleDate').val());
		}
	});
	
	$('#scheduleAdd').click(function(){
		if($('#scheduleTitle').val()==''){
			$('#scheduleTitle').focus();
			$('#scheduleTitleHelper').text('제목을 입력하세요');
		}else {
			$('#scheduleAddForm').submit();
		}
	});
});
</script>
<title>Insert title here</title>
</head>
<body>
<!-- 유효성검사 -->
<div class="container">
	<div class = "page-header">
	   <h1 align="center"><a href="/diary/index" >CALENDAR</a></h1>
	   <h2 align="center"><small>BEST CALENDAR APP EVER!</small></h2>
	</div>
	<div>
		<!-- 스케쥴 입력-->
		<h2>Schedule Add</h2>
		<form id="scheduleAddForm"action="/diary/ScheduleAdd" method="post">
			<input type="hidden" name="srcScheduleDate" id="srcScheduleDate" value="${scheduleDday}"/>
			<table class="table">			
					<tr>
						<td>DATE : </td>
						<td><input type="text" id="scheduleDate" name="scheduleDate" value="${scheduleDday}" class="form-control" readonly="readonly"/>
						<input type="checkbox" id="repeat" name="repeat" value="repeat"/>매년반복
						<!-- 체크하면 repeat_schedule테이블로 들어감 -->
						</td>
					</tr>	
					<tr>
						<td>TIME</td>
						<td><input type="time" name="scheduleTime"  class="form-control"/></td>			
					<tr>
						<td>TITLE</td>
						<td><span id="scheduleTitleHelper" style="color:red"></span>
						<input type="text" id="scheduleTitle" name="scheduleTitle" class="form-control"/></td>
					</tr>
					<tr>
						<td>MEMO</td>
						<td><textarea  name="scheduleMemo" class="form-control"/></textarea></td>
					</tr>
					<tr>
						<td>PLACE</td>
						<td><input type="text" id="schedulePlace" name="schedulePlace" class="form-control"/></td>
					</tr>
					<tr>
						<td>FONT COLOR</td>
						<td><input type="color" name="scheduleFontColor" /></td>
					</tr>		
					<tr>
						<td colspan="2"><input type = "button" class="btn btn-default btn-lg btn-block" id="scheduleAdd" value="완료"/></td>
					</tr>			
			</table>
		</form>
	</div>
	<div>
		<h2>Schedule List</h2>
		DATE : ${scheduleDday}
			<table class="table">
				<thead>
					<tr>
						<td>NO</td>
						<td>TITLE</td>
						<td>TIME</td>
						<td>MEMO</td>
						<td>PLACE</td>
						<td>수정</td>
						<td>삭제</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="s" items="${scheduleList}">
					<tr>
						<td>${s.scheduleNo}</td>
						<td>
							<c:if test="${s.repeat=='repeat'}">
								<img src="/resources/imgs/1475567016_repeat.png">
							</c:if>
							${s.scheduleTitle}
						</td>
						<td>
							<c:if test="${s.scheduleTime==null}"></c:if>
							<c:if test="${s.scheduleTime!=null}">
								<input type="time" value="${s.scheduleTime.substring(0,5)}" readonly="readonly">
							</c:if>	
						</td>
							
						<td>${s.scheduleMemo}</td>
						<td>${s.schedulePlace}</td>
						<td><a class="btn btn-default" href="/diary/scheduleModifyForm?scheduleNo=${s.scheduleNo}&repeat=${s.repeat}&srcScheduleDate=${scheduleDday}">수정</a></td>
						<td><a class="btn btn-default" href="/diary/scheduleRemove?scheduleNo=${s.scheduleNo}&repeat=${s.repeat}&srcScheduleDate=${scheduleDday}">삭제</a></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		<!-- 페이징기능/스케쥴시간 -->
	</div>
</div>
</body>
</html>