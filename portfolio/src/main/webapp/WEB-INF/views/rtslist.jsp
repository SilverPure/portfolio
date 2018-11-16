<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="/resources/css/list.css?ver=1"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/bootstrap-grid.min.css'/>">
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/jqgrid/ui.jqgrid-bootstrap4.css'/>" />
<%-- <link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/jqgrid/ui.jqgrid.css?ver=1'/> "/> --%>  
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/jqgrid/jquery-ui.css?ver=1'/>" />
<script src="<c:url value='/resources/js/jqgrid/i18n/grid.locale-kr.js?ver=1'/>" charset="utf-8"></script>
<script src="<c:url value='/resources/js/jqgrid/jquery.jqGrid.min.js?ver=1'/>" charset="utf-8"></script>

<body>
<div class="bglist">
	<div id="main">
	  <span style="float:right; font-size:30px;cursor:pointer" onclick="openNav()">&#9776; Open</span>
	</div>
	<div class="l-list">
		<table id="nav-list" width="400" class="table-sm table-bordered">
		    <colgroup>
		        <col width="35px"/>
		        <col width="120px"/>
		    </colgroup>
		    <thead>
		        <tr align="center"  class="table-custom1">
		            <th>순위</th>
		            <th>검색어</th>
		        </tr>
		    </thead>
		    <tbody>
                <c:forEach items="${rtsn}" var="row" end="9">
                    <tr align="center"  class="table-custom2">
    					<td>${row.rank}</td>                
                        <td><a href="javascript:void(0);" onclick="openNav();">${row.keyword}</a></td>
                    </tr>
                </c:forEach>
		    </tbody>
		</table>
	</div>
	<div class="r-list">
		<table border="1" width="400">
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
                <c:forEach items="${rtsd}" var="row" end="9">
                    <tr align="center"  class="table-custom2">
    					<td>${row.rank}</td>                
                        <td><a href="javascript:void(0);">${row.keyword}</a></td>
                    </tr>
                </c:forEach>
		    </tbody>
		</table>
	</div>
</div>

<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
  <table id="jqGrid"></table>
    <div id="pager"></div>
		<script type="text/javascript"> 
			var mydata = ${list};
			var seldata="";
			$(document).ready(function () {
					$('tbody tr').click(function(){
					   var tr = $(this);
				       var td = tr.children();
				       var rank = td.eq(0).text();
				       var keyword = td.eq(1).text();
				       alert('rank:'+rank+'keyword:'+keyword);
				       
				       if(mydata.rank.text==rank){
					   	alert('!!!!!');   
				    	   
				       }
				    });
					
					$("#jqGrid").jqGrid({
					datatype: "local",
					data : mydata,
					colModel: [
						{ label: '언론사', name: 'press', width: 20 },
						{ label: '해드라인', name: 'headline', width: 200 }
					],
					rownumbers: true,
					viewrecords: true, // show the current page, data rang and total records on the toolbar
					width: 750,
					height: 465,
					rowNum: 20,
					loadonce: false, // this is just for the demo
					pager: "#pager"
				});
			});
 		</script>
</div>	
</body>
