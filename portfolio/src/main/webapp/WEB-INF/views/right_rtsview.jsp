<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div align="left" 
style="background-image: url('/resources/images/bgbot.png'); 
background-position:505px; 
width:580px;
height: 700px;">
<h6>&nbsp;</h6>
	<div>
		<table id="daum_rts_table" border="0" style="font-size: 0.7rem; margin-top: 180px;">
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
		            <c:when test="${fn:length(rtsd) > 0}">
		                <c:forEach items="${rtsd}" var="row">
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
<script>
/* $('a').click(function(event){
	event.preventDefault();
	
	openNav();
}); */

//테이블의 Row 클릭시 값 가져오기
$("#daum_rts_table").click(function(){     
    var str = ""
    var tdArr = new Array();    // 배열 선언
    
    // 현재 클릭된 Row(<tr>)
    var tr = $(this);
    var td = tr.children();
    
    // tr.text()는 클릭된 Row 즉 tr에 있는 모든 값을 가져온다.
    console.log("클릭한 Row의 모든 데이터 : "+tr.text());
    
    // 반복문을 이용해서 배열에 값을 담아 사용할 수 도 있다.
    td.each(function(i){
        tdArr.push(td.eq(i).text());
    });
    
    console.log("배열에 담긴 값 : "+tdArr);
    
    // td.eq(index)를 통해 값을 가져올 수도 있다.
    var rank = td.eq(0).text();
    var keyword = td.eq(1).text();
    //var name = td.eq(2).text();
    //var email = td.eq(3).text();
    
    
    /* str +=    " * 클릭된 Row의 td값 = No. : <font color='red'>" + no + "</font>" +
            ", 아이디 : <font color='red'>" + rank + "</font>" +
            ", 이름 : <font color='red'>" + name + "</font>" +
            ", 이메일 : <font color='red'>" + email + "</font>"; */
            str +=    " * 클릭된 Row의 td값 = 랭킹. : " + rank + ", 키워드 :" + keyword ;
    console.log(str);
    //$("#ex1_Result1").html(" * 클릭한 Row의 모든 데이터 = " + tr.text());        
    //$("#ex1_Result2").html(str);
});



</script>