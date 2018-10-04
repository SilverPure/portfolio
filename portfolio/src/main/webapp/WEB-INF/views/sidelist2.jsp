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

</head>
  

<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <div id="jsGrid" class="jsgrid">
  	<div class="jsgrid-pager"></div>
  </div>
  <script>
  $(window.document).ready(function() {
	  $("#jsGrid").jsGrid({
        width: "800px",
        height: "770px",
        datatype: "json",
        
        //filtering: true,
        //editing: true,
        sorting: true,
        //paging: true,
        autoload: true,
 
        //pageSize: 10,
        //pageButtonCount: 10,
        
        data: ${list},
 
        fields: [
        { name: "title", type: "text" },
        { name: "press", type: "text", width:30},
        { name: "url", type: "text" }
   		 ],

    });
    //var rsJlist=${list};
    //var jlist=JSON.stringify(rsJlist);
	//alert(jlist);
 // 스크립트 변수에 담겨있는 json데이터의 길이만큼
  /*  for(var i=0;i<=jlist.length;i++){
            //jqgrid의 addRowData를 이용하여 각각의 row에 gridData변수의 데이터를 add한다
            jQuery("#jsGrid").jsGrid('addRowData',i+1,jlist[i]);
    } */
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
    document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
};

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft= "0";
    document.body.style.backgroundColor = "white";
};
</script>
</html> 
