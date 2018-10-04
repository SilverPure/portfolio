<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<div class="group-table-01 mt-2">
		<form id="frm" name="frm">
		  <div>Site</div>
		  <div class="d-flex flex-wrap align-content-center">
		  	<!-- Rounded switch -->
			<label class="switch">
			  <input type="checkbox" value="off" id="google" name="google" onclick="g_click();"/>
			  <span class="slider round"/>
			</label>
			<img alt="google" src="/resources/images/googlelogo.png" width="202px" height="46px">
			
			<label class="switch">
			  <input type="checkbox" value="off" id="naver" name="naver" onclick="n_click();"/>
			  <span class="slider round"/>
			</label>
			<img alt="naver" src="/resources/images/naver.png" width="202px" height="46px">
		  </div>
		  <div class=" ml-3">
		  <div>Checked Group</div>
		  	  <div class="custom-control custom-checkbox custom-control-inline">
				<input type="checkbox" class="custom-control-input" id="allchk" name="allchkval">
				<label class="custom-control-label" for="allchk">전체</label>
			  </div>
			  <div class="custom-control custom-checkbox custom-control-inline">
				<input type="checkbox" class="custom-control-input" id="imgproc" name="imgval">
				<label class="custom-control-label" for="imgproc">이미지</label>
			  </div>
			  <div class="custom-control custom-checkbox custom-control-inline">
				<input type="checkbox" class="custom-control-input" id="moveproc" name="moveval">
				<label class="custom-control-label" for="moveproc">동영상</label>
			  </div>
			  <div class="custom-control custom-checkbox custom-control-inline">
				<input type="checkbox" class="custom-control-input" id="newsproc" name="newsval">
				<label class="custom-control-label" for="newsproc">뉴스</label>
			  </div>
			  <div class="custom-control custom-checkbox custom-control-inline">
				<input type="checkbox" class="custom-control-input" id="korlongproc" name="korval">
				<label class="custom-control-label" for="korlongproc">한국어</label>
			  </div>
			  <div class="custom-control custom-radio custom-control-inline">
			    <input type="radio" class="custom-control-input" id="matchproc1" name="matchval" value="all">
			    <label class="custom-control-label" for="matchproc1">모든 결과</label>
			  </div>
			  <div class="custom-control custom-radio custom-control-inline">
			    <input type="radio" class="custom-control-input" id="matchproc2" name="matchval" value="exact">
			    <label class="custom-control-label" for="matchproc2">완전일치</label>
			  </div> 
			  <div class="custom-control custom-control-inline">
		  	    <select name="totime" class="custom-select mb-1">
			        <option selected value="none">모든 날짜</option>
			        <option value="h">1시간</option>
			        <option value="d">1일</option>
			        <option value="week">1주</option>
	            </select>
	          </div>
		  </div>
		  <div class="ml-3">
		  	  <div>Checked Group2</div>
		  	  <div class="custom-control custom-checkbox custom-control-inline">
				<input type="checkbox" class="custom-control-input" id="cafeproc" name="cafeval">
				<label class="custom-control-label" for="cafeproc">카페</label>
			  </div>
			  <div class="custom-control custom-checkbox custom-control-inline">
				<input type="checkbox" class="custom-control-input" id="blogproc" name="blogval">
				<label class="custom-control-label" for="blogproc">블로그</label>
			  </div>
			  <div class="custom-control custom-checkbox custom-control-inline">
				<input type="checkbox" class="custom-control-input" id="customCheck2-3" name="example7">
				<label class="custom-control-label" for="customCheck2-3">코드선택3</label>
			  </div>
			  <div class="custom-control custom-checkbox custom-control-inline">
				<input type="checkbox" class="custom-control-input" id="customCheck2-4" name="example2">
				<label class="custom-control-label" for="customCheck2-4">코드선택4</label>
			  </div>
		  </div>
		<div class="d-flex flex-row-reverse">
			<div class="col-3">
				<input type="text" class="form-control" name="keyword" placeholder="Search or KeyWord" >
			</div>
		</div>
		</form>
		<div class="d-flex flex-row-reverse mr-3">
			<button type="button" class="btn btn-secondary" onclick="proc_run();">실행</button>
		</div>
	</div>
</body>
</html>