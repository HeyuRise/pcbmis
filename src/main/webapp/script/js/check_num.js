function getUrlParam(name)  {  
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象  
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数  
	if (r!=null) return unescape(r[2]); 
	return null; //返回参数值  
} 
$(function () {
	$("#btn_save").hide();
	$("#btn_edit").show();
	$(".tr_view").show();
	$(".tr_edit").hide();
	
	
	var loc=getUrlParam("checkNum");
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
        	$scope.auditDetail = response.auditDetail;
        });
        $scope.$on('ngRepeatFinished', function (ngRepeatFinishedEvent) {
        	for(var i=0;i<$scope.auditDetail.sizeAndWarpDegrees.length;i++){
        		$("#judge"+i+"").val($scope.auditDetail.sizeAndWarpDegrees[i].judge);
        	}
       })
    });
    app.directive('onFinishRenderFilters',function($timeout){
        return {
            link: function(scope,element,attr){
                console.log(scope.$index)
                if(scope.$last == true){
                	$timeout(function() {
                		scope.$emit('ngRepeatFinished');//注册一个事件
                    });
                }
            }
        }
    })

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
function btn_edit(){
	$("#btn_save").show();
	$("#btn_edit").hide();
	$(".tr_view").hide();
	$(".tr_edit").show();
}


function btn_save(){
	var flag=true;
	
	// 	不为空
	$(".input").each(function (){
		 $(this).removeClass("red") 
		  if($(this).val()==""){
			  $(this).addClass("red")
			  flag = false;
		  }
	})
	
	//	板长>0数字校验
	$(".inputborder").each(function(){
		 var inputborder=/^[0-9]\d*(\.\d+)?$/;
		 $(this).removeClass("red") ;
		  if($(this).val()=="" || $(this).val()==0){
			  $(this).addClass("red")
			  flag = false;
		  };
		  if(!inputborder.test($(this).val()) ){
			  $(this).addClass("red")
			  flag = false;
		  };
	})
	
	if(flag){ 
		var url=window.location.href;
		var loc = url.substring(url.lastIndexOf('=')+1, url.length);
		var jsonstr = "";
	    var sizeAndWarpDegree = [];
	    var N=($("#tablerecord").find("tr").length-7)/2;
	    console.log(N);
		for (var i =0; i <N; i++) {
		    sizeAndWarpDegree[i]={}		
			sizeAndWarpDegree[i].boardLong=$("#boardLong"+i+"").val();
		    sizeAndWarpDegree[i].boardNum=$("#boardNum"+i+"").val();
			sizeAndWarpDegree[i].boardWide=$("#boardWide"+i+"").val();
			sizeAndWarpDegree[i].boardPly=$("#boardPly"+i+"").val();
			sizeAndWarpDegree[i].layHeight=$("#layHeight"+i+"").val();
			sizeAndWarpDegree[i].warpHeight=$("#WarpHeight"+i+"").val();
			sizeAndWarpDegree[i].judge=$("#judge"+i+"").val();
		 }
		jsonstr = JSON.stringify(sizeAndWarpDegree)
		 $.ajax({  
		        type: "PUT",  
		        url: "/pcbmis/check/size?checkNum=" + loc,  
		        data:jsonstr,
		        contentType: 'application/json', 
		        beforeSend:ajaxLoading,
		        success: function (data) {  
		        	ajaxLoadEnd();
		        	if(data.result==0){
		        		$.messager.alert("", "提交成功！",'info',function(){
		        			window.location = "num.html?checkNum="+loc;
		        		 });  
		        	}else{
		        		$.messager.alert("提示", data.msg,'error');
		        	
		        	}
		        },
		    })	
	}else{
		 $.messager.alert("操作提示", "信息填写不完整或者填写不规范！","info");
	}
}

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