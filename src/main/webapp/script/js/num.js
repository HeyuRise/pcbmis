function getUrlParam(name)  {  
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象  
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数  
	if (r!=null) return unescape(r[2]); 
	return null; //返回参数值  
} 
$(function () {
	var loc=getUrlParam("checkNum");
//	if(getUrlParam("bad")!=null){
//		$("#add_html").html('<a  class="easyui-linkbutton add l-btn" iconCls="icon-add" href="javascript:void(0)"  onclick="btn_add()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left">创建不合格品处置单</span></span></a>')
//	}else{
//		$("#add_html").html("")
//	}
	
	if(getUrlParam("edit")==null){
		$.ajax({  
	        type: "get",  
	        url: "/pcbmis/roleAuth/button?buttonId=7&type=1&orderNum=" + loc,  
	        contentType: 'application/json', 
	        success: function (data) {  
	        	if(data.result==0){
	        		$("#add_html").append('<a  class="easyui-linkbutton edit l-btn" iconCls="icon-edit" href="javascript:void(0)"  onclick="btn_edit()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">编辑</span></span></a>')
	        	}
	        }
		})
	}
	


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
        $http.post("/pcbmis/order/detailNew?type=2&checkNum=" + loc).success(function (response) {
        	console.log(response);
        	$scope.auditDetail = response.auditDetail;

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
	var loc=getUrlParam("checkNum");
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

function btn_edit(){
	 window.location.href="num_edit.html?checkNum="+getUrlParam("checkNum")
}

function btn_add(){
	var loc=getUrlParam("checkNum");
	 window.location.href="disposal_sheet.html?checkNum="+loc
}