
$(document).ready(function(){
    $("#list").on("click", function(e){ //목록으로 버튼
        e.preventDefault();
        fn_openBoardList();
    });
    
    $("#view").on("click", function(e){ //뷰 버튼
    	e.preventDefault();
        fn_viewBoard();
    });
     
    $("#write").on("click", function(e){ //작성하기 버튼
        e.preventDefault();
        fn_insertBoard();
    });
    
    $("#update").on("click", function(e){ //작성하기 버튼
        e.preventDefault();
        fn_updateBoard();
    });
    
    $("#delete").on("click", function(e){ //삭제하기 버튼
        var result = confirm('삭제 하시겠습니까?');
        
        if(result){
        	e.preventDefault();
        	fn_deleteBoard();
        }else{
        	return;
        }
    });
});

/*deltet*/
function fn_deleteBoard(){
	var comSubmit = new ComSubmit("frm");
    comSubmit.setUrl("/deleteTemplateBoard.do");
    //comSubmit.addParam("IDX", $("#IDX").val());
    comSubmit.submit();
}
/*list select*/
function fn_openBoardList(){
    var comSubmit = new ComSubmit;
    comSubmit.setUrl("/sqlTemplat.do");
    comSubmit.submit();
}

/*입력시 빈페이지로 이동*/
function fn_viewBoard(){
	location.href="/viewTemplateBoard.do";
}

/*update proc*/
function fn_updateBoard(){
    var comSubmit = new ComSubmit("frm");
    comSubmit.setUrl("/updateTemplateBoard.do");
    comSubmit.submit();
}

/*insert proc*/
function fn_insertBoard(){
    var comSubmit = new ComSubmit("frm");
    comSubmit.setUrl("/insertTemplateBoard.do");
    comSubmit.submit();
}

/*google site */
function g_click(){
	var _val="";
	_val = $('#google').val();
	if(_val=='off'){
		$('#google').val('https://www.google.co.kr');
	}else{
		$('#google').val('off');
	}
}
/*naver site*/
function n_click(){
	var _val="";
	_val = $('#naver').val();
	if(_val=='off'){
		$('#naver').val('https://www.naver.co.kr');
	}else{
		$('#naver').val('off');
	}
}
/*proc_run*/
function proc_run(){
	//var aa = $(':checked').attr('id');
	//alert("aa: "+aa);
	var browser = navigator.userAgent;
	
	var comSubmit = new ComSubmit("frm");
	comSubmit.addParam('browser',browser);

    comSubmit.setUrl("/createCrawling.do");
    comSubmit.submit();
}


