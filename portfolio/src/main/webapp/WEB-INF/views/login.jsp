<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<% String chkUser=(String)session.getId();%>
<!DOCTYPE html>
<html lang="ko">

<head>
<title>로그인</title>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0" />
<link type="text/css" rel="stylesheet" href="/resources/css/bootstrap.css?ver=1" />
<link type="text/css" rel="stylesheet" href="/resources/css/bootstrap.min.css?ver=1" />
<link type="text/css" rel="stylesheet" href="/resources/css/main.css?ver=1">
<!-- <link type="text/css" rel="stylesheet" href="/resources/css/login.css?ver=1"/> -->
<script src="<c:url value='/resources/js/jquery-3.3.1.min.js?ver=1'/>" charset="utf-8"></script>
<script src="<c:url value='/resources/js/bootstrap.js?ver=1'/>" charset="utf-8"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js?ver=1'/>" charset="utf-8"></script>
<script src="<c:url value='/resources/js/script.js?ver=1'/>" charset="utf-8"></script>
<script src="<c:url value='/resources/js/common.js?ver=1'/>" charset="utf-8"></script>
<script src="<c:url value='/resources/js/login.js?ver=1'/>" charset="utf-8"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<style>
    #popupDiv {  /* 팝업창 css */
    top : 0px;
    position: absolute;
    background: white;
    width: 500px;
    height: 500px;
    display: none; 
    }
    
    #popup_mask { /* 팝업 배경 css */
        position: fixed;
        width: 100%;
        height: 1000px;
        top: 0px;
        left: 0px;
        display: none; 
        background-color:#000;
        opacity: 0.8;
    }
    .container_pop {
    	font-family: Arial, Helvetica, sans-serif;
    	font-size:0.8em;
	    padding: 16px;
	    background-color: white;
	}
    /* Full-width input fields */
	input[type=text], input[type=password] {
	    width: 100%;
	    padding: 15px;
	    margin: 5px 0 22px 0;
	    display: inline-block;
	    border: none;
	    background: #f1f1f1;
	}
	input[type=text]:focus, input[type=password]:focus {
    background-color: #ddd;
    outline: none;
	}
	.registerbtn {
	    background-color: #e0af3d;
	    color: white;
	    padding: 16px 20px;
	    margin: 8px 0;
	    border: none;
	    cursor: pointer;
	    width: 100%;
	    opacity: 0.9;
	}
	.registerbtn:hover {
    opacity: 1;
	}
 </style>
</head>
<script type="text/javascript">
//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
function sample4_execDaumPostcode() {
  new daum.Postcode({
      oncomplete: function(data) {
          // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

          // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
          // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
          var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
          var extraRoadAddr = ''; // 도로명 조합형 주소 변수

          // 법정동명이 있을 경우 추가한다. (법정리는 제외)
          // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
          if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
              extraRoadAddr += data.bname;
          }
          // 건물명이 있고, 공동주택일 경우 추가한다.
          if(data.buildingName !== '' && data.apartment === 'Y'){
             extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
          }
          // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
          if(extraRoadAddr !== ''){
              extraRoadAddr = ' (' + extraRoadAddr + ')';
          }
          // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
          if(fullRoadAddr !== ''){
              fullRoadAddr += extraRoadAddr;
          }

          // 우편번호와 주소 정보를 해당 필드에 넣는다.
          document.getElementById('post').value = data.zonecode; //5자리 새우편번호 사용
          document.getElementById('addr1').value = fullRoadAddr;
          document.getElementById('addr2').value = data.jibunAddress;

          // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
          if(data.autoRoadAddress) {
              //예상되는 도로명 주소에 조합형 주소를 추가한다.
              var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
              document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

          } else if(data.autoJibunAddress) {
              var expJibunAddr = data.autoJibunAddress;
              document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';

          } else {
              document.getElementById('guide').innerHTML = '';
          }
      }
  }).open();
}
</script>
<body id="LoginForm" onload="" style="max-height: 90%;">
	<div class="container">
		<div class="login-form" style="margin-top: 170px;">
			<div class="main-div" style="opacity: 0.6;">
				<div class="panel">
					<h2>로그인</h2>
					<p>Please enter your email and password</p>
				</div>
				<form id="frm" name="frm">
					<div class="form-group">
						<input type="email" class="form-control" id="Email" name="email" placeholder="Email Address" required>
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="Password" name="pass" placeholder="Password" required>
					</div>
					<!-- <div class="forgot">
						<a href="javascript:void(0);">Forgot password?</a>
					</div> -->
					<div>
						<a id="kakao-login-btn"></a><a href="http://developers.kakao.com/logout"></a>
						<button type="button" id="loginBtn"class="btn btn-primary" >Login</button>
						<button type="button" id="popOpenBtn" class="btn btn-primary mt-2" >회원가입</button>
					</div>
					<script type='text/javascript'>
						//<![CDATA[
						// 사용할 앱의 JavaScript 키를 설정해 주세요.
						Kakao.init('f10e207f908a7b3e29b339cf7e2df13f'); //여기서 아까 발급받은 키 중 javascript키를 사용해준다.
						// 카카오 로그인 버튼을 생성합니다.
						Kakao.Auth.createLoginButton({
									container : '#kakao-login-btn',
									success : function(authObj) {
										//alert(JSON.stringify(authObj));
										// 로그인 성공시, API를 호출합니다. kdj:904196976,yes:864505251
										Kakao.API.request({
													url : '/v2/user/me',
													success : function(res) {
														var kakao_id=JSON.stringify(res.id);
														var kakao_pimg=JSON.stringify(res.properties.profile_image);
														var kakao_nick_name=JSON.stringify(res.properties.nickname);
														
														var comSubmit = new ComSubmit("frm");
														comSubmit.addParam('kakao_id',kakao_id);
														comSubmit.addParam('kakao_pimg',kakao_pimg);
														comSubmit.addParam('kakao_nick_name',kakao_nick_name);
													    comSubmit.setUrl("/login.do");
													    comSubmit.submit();
														
													},
													fail : function(error) {
														alert(JSON.stringify(error));
													}
												});

									},
									fail : function(err) {

										alert(JSON.stringify(err));
									}
								});
						Kakao.Auth.logout()
						//]]>
					</script>
				</form>
			</div>

		</div>
	</div>

	<div id="popup_mask"></div>
	<!-- 팝업 배경 DIV -->
	
	<div id="popupDiv"><!-- 팝업창 -->
		
		<div class="container_pop">
			
			<h4>회원가입</h4>
			
			<div id="popCloseBtn" class="offset-9" style="width:100px; height:38px; background-image: url('/resources/images/close_bg.jpg'); background-size: 100px,40px;"></div>
			<form id="frm_pop" name="frm_pop">
				<label for="email"><b>이메일</b></label> 
				<input type="text" placeholder="Enter Email" id="email" name="email" required> 
				<label for="nickname"><b>닉네임</b></label>
				<input type="text" placeholder="Enter NickName" name="nickname"> 
				<label for="psw"><b>패스워드</b></label> 
				<input type="password" placeholder="Enter Password"	id="pass" name="pass" required> 
				<label for="psw-repeat"><b>패스워드확인</b></label>
				<input type="password" placeholder="Repeat Password" id="psw-repeat" name="psw-repeat" required>
				<div>
					<label for="addr"><b>주소</b></label><br> <label>우편번호</label> 
					<input type="text" id="post" name="_post" style="width: 70px;">
					<button style="padding: 15px; margin: 5px 0 22px 0;" onclick="sample4_execDaumPostcode();">우편번호 검색</button> <br> 
					<label>도로명 주소</label> 
					<input type="text" id="addr1" name="_addr1"> 
					<label>지번 주소</label> 
					<input type="text" placeholder="Enter Addr" id="addr2" name="_addr2">
				</div>
				<button type="button" id="regSubmit" name="regSubmit"	class="registerbtn">저 장</button>
			</form>
		</div>
	</div>
	<!-- 팝업창 끝-->
	
</body>
</html>