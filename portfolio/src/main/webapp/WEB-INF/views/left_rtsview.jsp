<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div align="right" 
style="background-image: url('/resources/images/bgbot.png'); 
background-position:530px; 
width:935px;
height: 700px;"
>
<h6>&nbsp;</h6>
	<div>
		<table border="0" style="font-size: 0.7rem; margin-top: 180px;">
		    <colgroup>
		        <col width="35px"/>
		        <col width="120px"/>
		    </colgroup>
		    <thead>
		        <tr align="center">
		            <th>순위</th>
		            <th>검색어</th>
		        </tr>
		    </thead>
		    <tbody>
	             <c:choose>
		            <c:when test="${fn:length(rtsn) > 0}">
		                <c:forEach items="${rtsn}" var="row">
		                    <tr>
		    					<td>${row.rank}</td>                
		                        <td><a href="javascript:void(0);" onclick="openNav();">${row.keyword}</a></td>
		                    </tr>
		                </c:forEach>
		            </c:when>
		            <c:otherwise>
		                <tr>
		                    <td colspan="5">조회된 결과가 없습니다.</td>
		                </tr>
		            </c:otherwise>
		        </c:choose>
		    </tbody>
		</table>
	</div>
</div>