
$(function () {
	var url=window.location.href;
	var loc = url.substring(url.lastIndexOf('=')+1, url.length);
    var app = angular.module('testApp', []);
    
    app.filter('trustHtml', function ($sce) {
 		return function (input) {
    		return $sce.trustAsHtml(input);
 		}
	});
    
    app.filter('trustHtml', function ($sce) {
 		return function (input) {
    		return $sce.trustAsHtml(input);
 		}
	});
    
    app.filter('ntobr', function(){
        var filter = function(input){
        	return input.replace(/\n/g,"<\/br>").replace(/ /g,"&nbsp;");
        };
        return filter;
    });
    app.controller('testCtrl', function ($scope, $http) {    	
        $http.post("/pcbmis/order/auditDetail?checkNum=" + loc).success(function (response) {
        	console.log(response);
        	$scope.auditDetail = response.auditDetail;

        })
    });
    
    app.controller('Ctrl', function ($scope, $http) {    	
	    $http.get("/pcbmis/roleAuth/getUserDetail",{timeout:8000} ).success(function (response) {
	    	$scope.systemsUrl = response.systemsUrl;
	    	$scope.menu = response.menu;
	    	$scope.roles = response.roles;
	    	$scope.userName = response.userName;
	    	$scope.secondMenu = response.menu.secondMenu;
	    }).error(function(data,status){ 
	        if(status=='timeout'){
	          	 window.location.href="/pcbmis/j_spring_cas_security_logout"
	          }
	          
	      });
	})
	
	
    app.controller('aaCtrl', function ($scope, $http) {    	
	    $http.get("/pcbmis/roleAuth/getUserDetail" ,{timeout:8000}).success(function (response) {
	    	$scope.systemsUrl = response.systemsUrl;
	    	$scope.menu = response.menu;
	    	$scope.roles = response.roles;
	    	$scope.userName = response.userName;
	    	$scope.secondMenu = response.menu.secondMenu;
	    }).error(function(data,status){ 
	        if(status=='timeout'){
	          	 window.location.href="/pcbmis/j_spring_cas_security_logout"
	          }
	          
	      });
	})
	
	app.controller('bbCtrl', function ($scope, $http) {    	
	    $http.get("/pcbmis/roleAuth/getUserDetail",{timeout:8000} ).success(function (response) {
	    	$scope.systemsUrl = response.systemsUrl;
	    	$scope.menu = response.menu;
	    	$scope.roles = response.roles;
	    	$scope.userName = response.userName;
	    	$scope.secondMenu = response.menu.secondMenu;
	    }).error(function(data,status){ 
	        if(status=='timeout'){
	          	 window.location.href="/pcbmis/j_spring_cas_security_logout"
	          }
	          
	      });
	})
    
})


var submitFlag=0;
function reviewone(){
	if (submitFlag==1) {
		return;
	}
	submitFlag=1;
	
	var isPass="1";
	var url=window.location.href;
	var loc = url.substring(url.lastIndexOf('=')+1, url.length);
	$.ajax({  
        type: "POST",  
        url: "/pcbmis/audit/auditSubmit?checkNum=" + loc+"&isPass="+isPass,  
        beforeSend:ajaxLoading,
        success: function (data) {  
        	ajaxLoadEnd();
        	if(data.result==0){
                $.messager.alert("", "提交成功！",'info',function(){
                	window.location = "num.html?checkNum="+loc+"&edit="+loc;
//        			 var lastPage = localStorage.getItem("last_page");
//        			 if (lastPage != null && lastPage != "undefined" && lastPage != "") {
//        				 window.location = lastPage;
//        			 } else {
//        				 window.location = "Check.html";			        				 
//        			 }
        		 });  
//                $("#reviewone").css('display','none');
//                $("#reviewfirst").css('display','none');

        	}
        	else{
        		$.messager.alert("提示", data.msg,'error'); 
        		submitFlag=0;
	        }
      },
      error: function (data) {
    	  submitFlag=0;
      }
   })
}
    
function reviewfirst(){
	if (submitFlag==1) {
		return;
	}
	submitFlag=1;
	
	var isPass="0";
	var url=window.location.href;
	var loc = url.substring(url.lastIndexOf('=')+1, url.length);
	$.ajax({  
        type: "POST",  
        url: "/pcbmis/audit/auditSubmit?checkNum=" + loc+"&isPass="+isPass, 
        beforeSend:ajaxLoading,
        success: function (data) { 
        	ajaxLoadEnd();
        	if(data.result==0){
                $.messager.alert("", "提交成功！",'info',function(){
                	window.location = "num.html?checkNum="+loc+"&edit="+loc;
//        			 var lastPage = localStorage.getItem("last_page");
//        			 if (lastPage != null && lastPage != "undefined" && lastPage != "") {
//        				 window.location = lastPage;
//        			 } else {
//        				 window.location = "Check.html";			        				 
//        			 }
        		 });   
//                $("#reviewone").css('display','none');
//                $("#reviewfirst").css('display','none');
        	}
        	else{
          		$.messager.alert("提示", data.msg,'error'); 
        		submitFlag=0;
        	}
        },
        error: function (data) {
      	  submitFlag=0;
        }

    })
}
    
    
    function btn_export() {
        var url=window.location.href;
        var loc = url.substring(url.lastIndexOf('=')+1, url.length);
        $("#export_excel").form('submit',{
          url:'/pcbmis/downLoadFile/downTestNum',
          onSubmit:function(param) {
            param.checkNum=loc;
          },
          dataType:'json',
          success:function(data) {
            if (!data.result) {
              $.messager.alert('提示', data.msg, 'error');
            }
          }
        });
      }