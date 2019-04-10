function getUrlParam(name)  {  
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象  
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数  
	if (r!=null) return unescape(r[2]); 
	return null; //返回参数值  
} 
$(function () {
	var loc=getUrlParam("id");
    var app = angular.module('testApp', []);
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
        $http.get("/pcbmis/col/"+loc+"").success(function (response) {
        	$scope.auditDetail = response.data;
        });
    });

    app.controller('Ctrl', function ($scope, $http) {    	
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


function btn_export() {
	var loc=getUrlParam("id");
	$("#export_excel").form('submit',{
		url:'/pcbmis/col/file/'+loc+'',
		onSubmit:function(param) {
//            param.id=loc;
        },
        dataType:'json',
        success:function(data) {
        	if (!data.result) {
        		$.messager.alert('提示', data.msg, 'error');
            }
        }
    });
}