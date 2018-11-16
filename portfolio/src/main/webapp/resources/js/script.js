$(function(){
   $('tbody tr').click(function(){
	   var tr = $(this);
       var td = tr.children();
       var rank = td.eq(0).text();
       var keyword = td.eq(1).text();
      
   });
});

function openSide(rank,keyword){
	var r = rank;
	var k = keyword;
	
}

function openNav() {
    document.getElementById("mySidenav").style.width = "760px";
    document.getElementById("main").style.marginLeft = "760px";
    //document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
	//$('#mySidenav').style.width = "760px";
	//$('#main').style.marginLeft = "760px";
};

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft= "0";
    document.body.style.backgroundColor = "white";
	//$('#mySidenav').style.width = "0px";
	//$('#main').style.marginLeft = "0px";
};




