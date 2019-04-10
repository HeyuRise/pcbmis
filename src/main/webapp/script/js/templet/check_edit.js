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
		$http.get("/pcbmis/template/check/"+getUrlParam('id') + "?type=1").success(function (response) {
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
//	$(".div").each(function (){
//		 $(this).removeClass("red") 
//		  if($(this).html()==""){
//			  $(this).addClass("red")
//			  flag = false;
//		  }
//	})
//	 if(flag){ 
		 var jsonstr = "";
		 jsonstr = JSON.stringify({
			 templateName: $("#templateName").html(),
			 burrs: $("#burrs").val().replace(new RegExp("\n", "g"), "<br>"),
			 gap: $("#gap").val(),
			 exposedCopper: $("#exposedCopper").val(),
			 fabricTexture: $("#fabricTexture").val(),
			 pitVoid: $("#pitVoid").val().replace(new RegExp("\n", "g"), "<br>"),
			 spotCrack:$("#spotCrack").val().replace(new RegExp("\n", "g"), "<br>"),
			 delaminationFoaming:$("#delaminationFoaming").val(),
			 foreignImpurity: $("#foreignImpurity").val().replace(new RegExp("\n", "g"), "<br>"),
			 coverageAdhesion: $("#coverageAdhesion").val().replace(new RegExp("\n", "g"), "<br>"),
			 coincidenceDegree: $("#coincidenceDegree").val().replace(new RegExp("\n", "g"), "<br>"),
			 foamingLayering:$("#foamingLayering").val(),
			 corrugation: $("#corrugation").val(),
			 falseExposedCopper:$("#falseExposedCopper").val(),
			 falseBridgeDam: $("#falseBridgeDam").val(),
			 chromaticAberration:$("#chromaticAberration").val(),
			 identificationAdhesion:$("#identificationAdhesion").val(),
			 nodulesBurrs: $("#nodulesBurrs").val(),
			 darkOfHoleTinLead: $("#darkOfHoleTinLead").val(),
			 padCocked:$("#padCocked").val(),
			 haloRing:$("#haloRing").val(),
			 outerRingWidth: $("#outerRingWidth").val(),
			 solderInHole:$("#solderInHole").val(),
			 clogHole: $("#clogHole").val(),
			 lineWidthSpacing: $("#lineWidthSpacing").val(),
			 printedBoardElse:$("#printedBoardElse").val(),
			 baseMaterialElse: $("#baseMaterialElse").val(),
			 solderMaskElse: $("#solderMaskElse").val(),
			 specialBoardNum: $("#specialBoardNum").val(),
			 markElse: $("#markElse").val(),
			 conductivePatternElse: $("#conductivePatternElse").val(),
			 boardLongResult:$("#boardLongResult").val(),
			 boardWideResult:$("#boardWideResult").val(),
			 boardPlyResult: $("#boardPlyResult").val(),
			 smoothnessResult: $("#smoothnessResult").val(),
		 })
		  $.ajax({  
			  type: "put",  
			  url: "/pcbmis/template/check/" + getUrlParam("id"),  
			  data:jsonstr,
			  contentType: 'application/json', 
			  beforeSend:ajaxLoading,
			  success: function (data) {  
				  ajaxLoadEnd();
				  if(data.result==0){
					  window.location = "check_review.html?id="+getUrlParam("id");
				  }else{
					  $.messager.alert("提示", data.msg,'error');
				  }
			  }
		  })
				        	
//	 }else{
//		 $.messager.alert("操作提示", "信息填写不完整或者填写不规范！","info");
//	 }
}