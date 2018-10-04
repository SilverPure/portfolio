<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String nickName = (String)session.getAttribute("nickName");
String profileImg = (String) session.getAttribute("profileImg");
//out.print("nickName==> "+nickName);
//out.print("  profileImg==> "+profileImg);

%>
	<!--	
		background-color: #f8f8f8;
  		border-color: #e7e7e7;
  		#f0ad4e
  		
	-->
	
		<nav class="navbar navbar-expand-lg navbar-dark" style="height: 75px; background-color:#f0ad4e; z-index: 1 ">
		  <a class="navbar-brand" href="javascript:void(0)" style="color: black; font-size:x-large;">Logo</a>
		  <!-- Links -->
		  <div class="collapse navbar-collapse" id="navb">
		  <ul class="navbar-nav mr-auto">
		    <li class="nav-item">
		      <a class="nav-link" href="/listRTS.do" style="color: black; font-size:x-large;">실시간검색어</a>
		    </li>
		    <li class="nav-item">
		      <a class="nav-link" href="/selectContent.do" style="color: black; font-size:x-large;">리스트</a>
		    </li>
		
		    <!-- Dropdown -->
		    <li class="nav-item dropdown">
		      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown" style="color: black; font-size:x-large;">개발자는?</a>
		      <div class="dropdown-menu">
		        <a class="dropdown-item" href="/selectRTS.do">수집</a>
		        <a class="dropdown-item" href="#">Link 2</a>
		        <a class="dropdown-item" href="#">Link 3</a>
		      </div>
		    </li>
		  </ul>
		  </div> 
		  <div class="form-inline my-2 my-sm-0">
		  	<div><%=nickName%>님 환영합니다.</div>
		  	<div onclick="logout()" style="width:40px; height: 40px; background-image: url('/resources/images/exit.png'); background-size:40px,40px; z-index: 1"></div>
		    <!-- <input class="form-control mr-sm-2" type="text" placeholder="Search" id="m_search" name="m_search">
		    <button class="btn btn-success" type="submit" onclick="_search()">Search</button> -->
		  </div>
		</nav>
		<script type="text/javascript">
			function logout(){
				var result = false;
				var result = confirm('종료 하시겠습니까?');
				
				if(result){
					location.href="/logout.do";
				}
			}
		</script>
