<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
    font-family: "Lato", sans-serif;
    transition: background-color .5s;
}

.sidenav {
    height: 100%;
    width: 0;
    position: fixed;
    z-index: 1;
    top: 0;
    left: 0;
    background-color: #FFFFFF;
    overflow-x: hidden;
    transition: 0.5s;
    padding-top: 60px;
}

.sidenav a {
    padding: 8px 8px 8px 32px;
    text-decoration: none;
    font-size: 25px;
    color: #818181;
    display: block;
    transition: 0.3s;
}

.sidenav a:hover {
    color: #f1f1f1;
}

.sidenav .closebtn {
    position: absolute;
    top: 0;
    right: 25px;
    font-size: 36px;
    margin-left: 50px;
}

#main {
    transition: margin-left .5s;
    padding: 16px;
}

@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}
</style>
<script src="<c:url value='/resources/js/jqgrid/i18n/grid.locale-kr.js?ver=1'/>" charset="utf-8"></script>
</head>
  

<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
  <table id="jqGrid"></table>
    <div id="pager"></div>
		<script type="text/javascript"> 
		var mydata = ${list};
		$(document).ready(function () {
		
				$("#jqGrid").jqGrid({
				datatype: "local",
				data : mydata,
				colModel: [
					{ label: '언론사', name: 'press', width: 20 },
					{ label: '해드라인', name: 'content', width: 200 }
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


<div id="main">
  <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; open</span>
</div>

<script>
function openNav() {
    document.getElementById("mySidenav").style.width = "760px";
    document.getElementById("main").style.marginLeft = "760px";
    //document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
};

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft= "0";
    document.body.style.backgroundColor = "white";
};
</script>
</html> 
