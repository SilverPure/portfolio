<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<link type="text/css" rel="stylesheet" href="/resources/css/jqgrid/jquery-ui.css?ver=1"/>
<link type="text/css" rel="stylesheet" href="/resources/css/jqgrid/ui.jqgrid.css?ver=1"/>
<script src="<c:url value='/resources/js/jqgrid/i18n/grid.locale-kr.js?ver=1'/>" charset="utf-8"></script>
<script src="<c:url value='/resources/js/jqgrid/jquery.jqGrid.min.js?ver=1'/>" charset="utf-8"></script>
<body>
<div align="center">
<table id="jqGrid"></table>
    <div id="pager"></div>
		<script type="text/javascript"> 
		var mydata = ${list};
		$(document).ready(function () {
		
				$("#jqGrid").jqGrid({
				datatype: "local",
				data : mydata,
				colModel: [
					{ label: '기준시간', name: 'stand_time', align:'center', width: 30},
					{ label: '언론사', name: 'press', align:'center', width: 20 },
					{ label: '해드라인', name: 'content', align:'left', width: 200 },
					{ label: 'URL', name: 'url', align:'left', width: 200 }
				],
				rownumbers: true,
				viewrecords: true, // show the current page, data rang and total records on the toolbar
				width: 1000,
				height: 600,
				rowNum: 25,
				loadonce: false, // this is just for the demo
				pager: "#pager"
			});
		});

 </script>
</div>
</body>
