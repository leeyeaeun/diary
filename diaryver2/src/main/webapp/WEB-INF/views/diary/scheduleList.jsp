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
			$('#scheduleAddForm').submit();
		});
	});
</script>
<title>Insert title here</title>
</head>
<body>
<!-- ��ȿ���˻� -->
<div class="container">
	<div>
		<!-- ������ �Է�-->
		<h2>Schedule Add</h2>
		<form id="scheduleAddForm"action="/diary/ScheduleAdd" method="post">
			<input type="hidden" name="srcScheduleDate" id="srcScheduleDate" value="${scheduleDday}"/>
			<table class="table">			
					<tr>
						<td>DATE : </td>
						<td><input type="text" id="scheduleDate" name="scheduleDate" value="${scheduleDday}" readonly="readonly"/>
						<input type="checkbox" id="repeat" name="repeat" value="repeat"/>�ų�ݺ�
						<!-- üũ�ϸ� repeat_schedule���̺��� �� -->
						</td>
					</tr>				
					<tr>
						<td>TITLE</td>
						<td><input type="text" name="scheduleTitle" /></td>
					</tr>
					<tr>
						<td>MEMO</td>
						<td><textarea  name="scheduleMemo" /></textarea></td>
					</tr>
					<tr>
						<td>PLACE</td>
						<td><input type="text" name="schedulePlace" /></td>
					</tr>
					<tr>
						<td>FONT COLOR</td>
						<td><input type="color" name="scheduleFontColor" /></td>
					</tr>		
					<tr>
						<td colspan="2"><input type = "button" id="scheduleAdd" value="�Ϸ�"/></td>
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
						<td>MEMO</td>
						<td>PLACE</td>
						<td>����</td>
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
						<td>${s.scheduleMemo}</td>
						<td>${s.schedulePlace}</td>
						<td><a href="">[����]</a></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		<!-- �����츮��Ʈ ���/����¡���/������ð� -->
	</div>
</div>
</body>
</html>