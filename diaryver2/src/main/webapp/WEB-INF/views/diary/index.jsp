<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<div class = "page-header">
	   <h1 align="center"><a href="/diary/index" >CALENDAR</a></h1>
	   <h2 align="center"><small>BEST CALENDAR APP EVER!</small></h2>
	</div>
	<h3 align="center">
		<small><a href="/diary/index?ddayYear=${ddayYear}&ddayMonth=${ddayMonth}&ddayOption=prev"class="btn btn-default">[PREV]</a></small>
		${ddayYear}년${ddayMonth+1}월
		<small><a href="/diary/index?ddayYear=${ddayYear}&ddayMonth=${ddayMonth}&ddayOption=next"class="btn btn-default">[NEXT]</a></small>
	</h3>	
	<table class="table table-responsive" >
		<thead>
			<tr>
				<th class="col-sm-1">일</th>
				<th class="col-sm-1">월</th>
				<th class="col-sm-1">화</th>
				<th class="col-sm-1">수</th>
				<th class="col-sm-1">목</th>
				<th class="col-sm-1">금</th>
				<th class="col-sm-1">토</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<c:forEach var="oneDay" items="${oneDayList}" varStatus="i" >	
				<c:if test="${oneDay.day!=today.day}">  
					<td> 
				</c:if>
				<c:if test="${oneDay.day==today.day && oneDay.month!=today.month &&oneDay.year!=today.year }">  
					<td> 
				</c:if>
				<c:if test="${oneDay.day==today.day && oneDay.month==today.month&&oneDay.year==today.year}">
					<td style="background-color:#fef2ff"> 
				</c:if>
						<c:if test="${oneDay.scheduleList == null}">
							<div style="color:#BDBDBD">${oneDay.day}</div>	
						</c:if>	
						<c:if test="${oneDay.scheduleList != null}">
							<a href ="/diary/scheduleList?scheduleDday=${oneDay.year}-${oneDay.month}-${oneDay.day}">
								<c:if test="${oneDay.scheduleList.size()==0}">
								<!-- 일요일 -->
									<c:if test="${i.count%7==1}">
										<div style="color:#ea360e">${oneDay.day}</div>	
									</c:if>
								<!-- 토요일 -->
									<c:if test="${i.count%7==0}">
										<div style="color:#02aef2">${oneDay.day}</div>	
									</c:if>
								<!-- 평일 -->
									<c:if test="${i.count%7 > 1}">
										<div style="color:#000000">${oneDay.day}</div>	
									</c:if>
								</c:if>
								<c:if test="${oneDay.scheduleList.size()>0}">
								
								<!-- 일요일 -->
									<c:if test="${i.count%7==1}">
										<div style="color:#ea360e;font-weight:bold;">${oneDay.day}</div>
									</c:if>
								<!-- 토요일 -->
									<c:if test="${i.count%7==0}">
										<div style="color:#02aef2;font-weight:bold;">${oneDay.day}</div>
									</c:if>
								<!-- 평일 -->
									<c:if test="${i.count%7 > 1}">
										<div style="color:#000000;font-weight:bold;">${oneDay.day}</div>
									</c:if>
									
									<c:forEach var="s" items="${oneDay.scheduleList}" begin="0" end="2">
										<div style="color:${s.scheduleFontColor}">
											<c:if test="${s.repeat=='repeat'}">
												<img src="/resources/imgs/1475567016_repeat.png">
											</c:if>
											<c:if test="${s.scheduleTitle.length()<6}">
												${s.scheduleTitle}
											</c:if>
											<c:if test="${s.scheduleTitle.length()>5}">
												${fn:substring(s.scheduleTitle,0,5)}...  
											</c:if>
										</div>
									</c:forEach>
									<c:if test="${oneDay.scheduleList.size()>3}">
										<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;...${oneDay.scheduleList.size()-3} more</span>  
									</c:if>
								</c:if>
							</a>
						</c:if>	
					</td>
						<c:if test="${i.count%7==0}">
							</tr><tr>
						</c:if>
				</c:forEach>
			</tr>
		</tbody>
	</table>
</div>	
</body>
</html>