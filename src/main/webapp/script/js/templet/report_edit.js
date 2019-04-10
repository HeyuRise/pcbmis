function getUrlParam(name)  {  
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象  
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数  
	if (r!=null) return unescape(r[2]); 
	return null; //返回参数值  
} 
$(function(){
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
	app.controller('testCtrl', function ($scope, $http) {    	
		$http.get("/pcbmis/template/report/"+getUrlParam('id')).success(function (response) {
			$scope.auditDetail = response.data;
        });
    });
})
//提交
function btn_save(){
// 	var flag = true;
// 	// 	不为空
//	$(".input").each(function (){
//		 $(this).removeClass("red") 
//		  if($(this).val()==""){
//			  $(this).addClass("red")
//			  flag = false;
//		  }
//	})
//	 if(flag){ 
		 var jsonstr = "";
		 jsonstr = JSON.stringify({
			 templateName: $("#templateName").html(),
			 baseMaterialAppearance: $("#baseMaterialAppearance").val(),
			 conductivePattern: $("#conductivePattern").val(),
			 preventSmtAppearance:  $("#preventSmtAppearance").val(),
			 characterAppearance: $("#characterAppearance").val(),
			 externalCoatingAdhesion:  $("#externalCoatingAdhesion").val(),
			 preventSmtCharacterAdhesion:  $("#preventSmtCharacterAdhesion").val(),
			 solderability:  $("#solderability").val(),
			 preventSmtType:  $("#preventSmtType").val(),
			 characterType:  $("#characterType").val(),
			 aperture:  $("#aperture").val(),
			 specialDimension:  $("#specialDimension").val(),
			 circuit:  $("#circuit").val(),
			 specialImpedance: $("#specialImpedance").val(),
		 })
		  $.ajax({  
			  type: "put",  
			  url: "/pcbmis/template/report/" + getUrlParam("id"),  
			  data:jsonstr,
			  contentType: 'application/json', 
			  beforeSend:ajaxLoading,
			  success: function (data) {  
				  ajaxLoadEnd();
				  if(data.result==0){
					  window.location = "report_review.html?id="+getUrlParam("id");
				  }else{
					  $.messager.alert("提示", data.msg,'error');
				  }
			  }
		  })
				        	
//	 }else{
//		 $.messager.alert("操作提示", "信息填写不完整或者填写不规范！","info");
//	 }
}