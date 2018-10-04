<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html lang="ko">
<!--  -->
<head>
<title><tiles:getAsString name="title" /></title>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0" />
<link rel="stylesheet" href="/resources/css/bootstrap.css?ver=1"/>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css?ver=1"/>
<link rel="stylesheet" href="/resources/css/main.css?ver=1">
<script src="<c:url value='/resources/js/jquery-3.3.1.min.js?ver=1'/>" charset="utf-8"></script>
<script src="<c:url value='/resources/js/bootstrap.js?ver=1'/>" charset="utf-8"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js?ver=1'/>" charset="utf-8"></script>
<script src="<c:url value='/resources/js/script.js?ver=1'/>" charset="utf-8"></script>
<script src="<c:url value='/resources/js/common.js?ver=1'/>" charset="utf-8"></script>
</head>
 
 <!--  --> 
		
			<header id="header">
				<tiles:insertAttribute name="header"/>
			</header>
			<!-- headerBox -->
			<%-- <section id="sidemenu">
				<tiles:insertAttribute name="sidemenu"/> 
			</section> --%>
			<section id="siteContent">
				<tiles:insertAttribute name="contents"/>
			</section>
			<!-- container -->
			<footer id="footer">
				<tiles:insertAttribute name="footer"/>
			</footer>
		
		
</html>