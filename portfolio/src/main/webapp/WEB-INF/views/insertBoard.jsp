<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/bootstrap.css"></link>
</head>
<script src="<c:url value='/resources/js/jquery-3.3.1.min.js?ver=1'/>" charset="utf-8"></script>
<script src="<c:url value='/resources/js/bootsrtap.js?ver=1'/>" charset="utf-8"></script>
<script src="<c:url value='/resources/js/script.js?ver=1'/>" charset="utf-8"></script>
<script src="<c:url value='/resources/js/common.js?ver=1'/>" charset="utf-8"></script>

<body>
<h2>스터디 등록</h2>
<div class="col-sm-2"></div>

<div class="col-sm-8">
<form id="frm" name="frm" class="form-horizontal">
	<div class="form-group">
      <label class="control-label col-sm-2" for="email">스터디 일정 : </label>
      <div class="col-sm-10">
		<b>21,22,23일 오후 7시~8시 총 3시간</b><br>
		<b>21일 스프링 구조  1시간</b><br>
		<b>22일 CRUD 작성 1시간</b><br>
		<b>23일 ???     1시간</b>
      </div>
    </div>
	<div class="form-group">
      <label class="control-label col-sm-2" for="email">Email:</label>
      <div class="col-sm-10">
		<input type="text" id="email" name="email" class="form-control_short" placeholder="Enter Email" value="${map.EMAIL}"/>
      </div>
    </div>
    
    <%-- <div class="form-group">
      <label class="control-label col-sm-2" for="idname">아이디:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control_short" id="idname" name="id" value="${map.ID}"/>
        <input type="hidden" id="idx" name="idx" value="${map.IDX}"/>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pw">패스워드:</label>
      <div class="col-sm-10">          
        <input type="password" class="form-control_short" id="pw" name="pw" placeholder="Enter password" value="${map.PW}">
      </div>
    </div> --%>
	<div class="form-group">
      <label class="control-label col-sm-2" for="name">이름:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control_short" id="name" name="name" value="${map.NAME}"/>
      </div>
    </div>
	<%-- <div class="form-group">
      <label class="control-label col-sm-2" for="address">주소:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control_short" id="address" name="address" value="${map.ADDRESS}"/>
      </div>
    </div>	 --%>		
			
	<div class="col-sm-7">	
		<!-- jstl if문 idx값이 있으면 update를 하고 idx값이 없으면 새글이므로 등록 한다. -->	
		<c:set var="map" value="${map.IDX}"/>
			<c:choose>
				<c:when test="${!empty map}">
					<a href="#" id="update" class="btn btn-group-vertical pull-right" >저장</a>
					<a href="#" id="delete" class="btn btn-group-vertical pull-right">삭제</a>
				</c:when>
				<c:when test="${empty map}">
					<a href="#" id="write" class="btn btn-group-vertical pull-right">저장</a>
				</c:when>
			</c:choose>
		<a href="#" id="list" class="btn btn-group-vertical pull-right">리스트</a>
	</div>
</form>
<form id="commonForm" name="commonForm"></form>
</div>
<div class="col-sm-2"></div>
</body>
</html>