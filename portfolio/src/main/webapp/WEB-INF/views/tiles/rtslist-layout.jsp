<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html lang="ko">
<!--<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">  -->
<head>
<title><tiles:getAsString name="title" /></title>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0" />
<link type="text/css" rel="stylesheet" href="/resources/css/bootstrap.css?ver=1"/>
<link type="text/css" rel="stylesheet" href="/resources/css/bootstrap.min.css?ver=1"/>
<link type="text/css" rel="stylesheet" href="/resources/css/bootstrap-grid.min.css?ver=1"/>
<link type="text/css" rel="stylesheet" href="/resources/css/main.css?ver=1"/>
<link type="text/css" rel="stylesheet" href="/resources/css/jqgrid/jquery-ui.css?ver=1"/>
<link type="text/css" rel="stylesheet" href="/resources/css/jqgrid/ui.jqgrid.css?ver=1"/>
<link type="text/css" rel="stylesheet" href="/resources/css/jqgrid/ui.jqgrid-bootstrap4.css?ver=1"/>
<script src="<c:url value='/resources/js/jquery-3.3.1.min.js?ver=1'/>" charset="utf-8"></script>
<script src="<c:url value='/resources/js/bootstrap.js?ver=1'/>" charset="utf-8"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js?ver=1'/>" charset="utf-8"></script>
<script src="<c:url value='/resources/js/script.js?ver=1'/>" charset="utf-8"></script>
<script src="<c:url value='/resources/js/common.js?ver=1'/>" charset="utf-8"></script>
<script src="<c:url value='/resources/js/jqgrid/jquery.jqGrid.min.js?ver=1'/>" charset="utf-8"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
</head>
 
 <!--  --> 
			<header id="header">
				<tiles:insertAttribute name="header"/>
			</header>
			<!-- view -->
			<!-- <div style="border: solid red 1px; height:730px; width:1920px; float: left;">
				style="border: solid red 1px; height:730px; width:1920px;" 
			-->
		<body>
			<div class="row" style="height:730px; width:1920px;">
				<section id="leftview">
					<tiles:insertAttribute name="left"/>
				</section>
				<section id="rightview">
					<tiles:insertAttribute name="right"/>
				</section>
				<section id="sidelist">
					<tiles:insertAttribute name="side"/> 
				</section>
			</div>
		</body>	
			<!-- container -->
			<footer id="footer">
				<tiles:insertAttribute name="footer"/>
			</footer>
		
		
</html>