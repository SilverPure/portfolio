$(function() {
	//팝업 창 시작
	$("#popOpenBtn").click(function(event){  //팝업 Open 버튼 클릭 시 

        $("#popupDiv").css({
           /*"top": (($(window).height()-$("#popupDiv").outerHeight())/2+$(window).scrollTop())+"px",*/
           "left": (($(window).width()-$("#popupDiv").outerWidth())/2+$(window).scrollLeft())+"px"
           //팝업창을 가운데로 띄우기 위해 현재 화면의 가운데 값과 스크롤 값을 계산하여 팝업창 CSS 설정
        
        }); 
       
       $("#popup_mask").css("display","block"); //팝업 뒷배경 display block
       $("#popupDiv").css("display","block"); //팝업창 display block
       
       $("body").css("overflow","hidden");//body 스크롤바 없애기
    });
	
	$("#popCloseBtn").click(function(event){
        $("#popup_mask").css("display","none"); //팝업창 뒷배경 display none
        $("#popupDiv").css("display","none"); //팝업창 display none
        $("body").css("overflow","auto");//body 스크롤바 생성
    });
	//팝업창 끝
	//가입 팝업창 오픈
	/*$('#userReg').click(function(){
		window.open("/loginPop.do", "",
		"width=550, height=730 left=700, top=170 location=no");
	});*/
	
	$("#regSubmit").click(function(){
		var f = $("#frm_pop");
        var $f = jQuery(f);
        var $b = jQuery(this);
        var $t, t;
        var result = true;
        
        $f.find("input, select, textarea").each(function(i) {
        	$t = jQuery(this);
        	if($t.prop("required")) {
        		if(!jQuery.trim($t.val())) {
        			t = jQuery("label[for='"+$t.attr("id")+"']").text();
        			result = false;
        			$t.focus();
        			alert(t+" 필수 입력입니다.");
        			return false;
        		}
        	}
        });
        if(!result)
            return false;
        var result = confirm('가입 하시겠습니까?');
        if(result){
        	var comSubmit = new ComSubmit("frm_pop");
    	     comSubmit.setUrl("/login.do");
    	     comSubmit.submit();
        }else{
        	return;
        }
		
	});
	
	$("#popCloseBtn").click(function(event){
        $("#popup_mask").css("display","none"); //팝업창 뒷배경 display none
        $("#popupDiv").css("display","none"); //팝업창 display none
        $("body").css("overflow","auto");//body 스크롤바 생성
    });
	
	$("#loginBtn").click(function(){
		var f = $("#frm");
        var $f = jQuery(f);
        var $b = jQuery(this);
        var $t, t;
        var result = true;
        
        $f.find("input, select, textarea").each(function(i) {
        	$t = jQuery(this);
        	if($t.prop("required")) {
        		if(!jQuery.trim($t.val())) {
        			t = $("input["+$t.attr("id")+"]").text();
        			result = false;
        			$t.focus();
        			alert(t+" 필수 입력입니다.");
        			return false;
        		}
        	}
        });
        
        if(!result)
            return false;
        
        if(result){
        	var comSubmit = new ComSubmit("frm");
    	     comSubmit.setUrl("/login.do");
    	     comSubmit.submit();
        }else{
        	return;
        }
	});
	
});



    
    
    
    
 

