<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<form id="frm" name="frm">
<div>
<h2>게시판 목록</h2>
<div class="table-responsive-02">
	<table class="table">
	    <colgroup>
	        <col width="10%"/>
	        <col width="25%"/>
	        <col width="15%"/>
	        <col width="15%"/>
	        <col width="*"/>
	    </colgroup>
	    <thead>
	        <tr>
	            <th scope="col">글번호</th>
	            <th scope="col">이름</th>
	            <th scope="col">아이디</th>
	            <!-- <th scope="col">패스워드</th>
	            <th scope="col">주소</th> -->
	        </tr>
	    </thead>
	    <tbody>
	        <c:choose>
	            <c:when test="${fn:length(list) > 0}">
	                <c:forEach items="${list }" var="row">
	                    <tr>
	    					<td>${row.idx}</td>                
	                        <td>${row.email}</a></td>
		                    <td>${row.name}</td>
	                       <%--  <td>${row.PW}</td>
	                        <td>${row.ADDRESS}</td> --%>
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
 <a href="#" id="view">글쓰기</a>
</div>
</form>
