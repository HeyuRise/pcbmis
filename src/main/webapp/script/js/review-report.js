
$(function () {
	var url=window.location.href;
	var loc = url.substring(url.lastIndexOf('=')+1, url.length);
    var app = angular.module('testApp', []);
    app.controller('testCtrl', function ($scope, $http) {    	
        $http.post("/pcbmis/order/reportDetail?reportNum=" + loc).success(function (response) {
        	console.log(response);
        	$scope.reportDetail = response.reportDetail;

        });
    });
    app.filter('trustHtml', function ($sce) {
 		return function (input) {
    		return $sce.trustAsHtml(input);
 		}
	});
    app.controller('ddCtrl', function ($scope, $http) {    	
        $http.post("/pcbmis/order/reportDetail?reportNum=" + loc).success(function (response) {
        	console.log(response);
        	$scope.reportDetail = response.reportDetail;
        	var ctae=response.reportDetail.baseReportInfo.categoryGrade;
        	if(ctae.indexOf("民")!=-1){
        		$("#min").show();
        		$("#jun").hide();
        		
        	}else{
        		$("#min").hide();
        		$("#jun").show();
        	}


        });
    });
    
    
    app.controller('Ctrl', function ($scope, $http) {    	
	    $http.get("/pcbmis/roleAuth/getUserDetail" ).success(function (response) {
	    	$scope.systemsUrl = response.systemsUrl;
	    	$scope.menu = response.menu;
	    	$scope.roles = response.roles;
	    	$scope.userName = response.userName;
	    	$scope.secondMenu = response.menu.secondMenu;
	    })
	});
	
	
    app.controller('aaCtrl', function ($scope, $http) {    	
	    $http.get("/pcbmis/roleAuth/getUserDetail" ).success(function (response) {
	    	$scope.systemsUrl = response.systemsUrl;
	    	$scope.menu = response.menu;
	    	$scope.roles = response.roles;
	    	$scope.userName = response.userName;
	    	$scope.secondMenu = response.menu.secondMenu;
	    })
	});
	
	app.controller('bbCtrl', function ($scope, $http) {    	
	    $http.get("/pcbmis/roleAuth/getUserDetail" ).success(function (response) {
	    	$scope.systemsUrl = response.systemsUrl;
	    	$scope.menu = response.menu;
	    	$scope.roles = response.roles;
	    	$scope.userName = response.userName;
	    	$scope.secondMenu = response.menu.secondMenu;
	    })
	});
	
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
        url: "/pcbmis/reportAudit/reportAuditSubmit?reportNum=" + loc+"&isPass="+isPass,  
        beforeSend:ajaxLoading,
        success: function (data) {  
        	ajaxLoadEnd();
        	if(data.result==0){
    			$.messager.alert("", "提交成功！",'info',function(){
    				 var url=window.location.href;
    				 var loc = url.substring(url.lastIndexOf('=')+1, url.length);
    				 window.location = "num-report.html?reportNum="+loc+"&edit="+loc;	
        		 });  
        	}else{
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
        url: "/pcbmis/reportAudit/reportAuditSubmit?reportNum=" + loc+"&isPass="+isPass, 
        beforeSend:ajaxLoading,
        success: function (data) {
        	ajaxLoadEnd();
        	if(data.result==0){
    			$.messager.alert("", "提交成功！",'info',function(){
    				 var url=window.location.href;
    				 var loc = url.substring(url.lastIndexOf('=')+1, url.length);
    				 window.location = "num-report.html?reportNum="+loc+"&edit="+loc;	
        		 });  
        	}else{
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
          url:'/pcbmis/downLoadFile/downWriteReport',
          onSubmit:function(param) {
            param.reportNum=loc;
          },
          dataType:'json',
          success:function(data) {
            if (!data.result) {
              $.messager.alert('提示', data.msg, 'error');
            }
          }
        });
      }
    
	// 导出合格证
    function btn_export2() {
    	var url=window.location.href;
    	var loc = url.substring(url.lastIndexOf('=')+1, url.length);
    	$("#export_excel").form('submit',{
    		url:'/pcbmis/downLoadFile/downReportCertification',
    		onSubmit:function(param) {
    			param.reportNum=loc;
    		},
    		dataType:'json',
    		success:function(data) {
    			if (!data.result) {
    			     $.messager.alert('提示', data.msg, 'error');
    			}
    		}
    	});
    }
    
    
    function initload(){
    	$("#tbListTM").rowspan(0);
    	$("#tbListTM").rowspan(1);
    }
    (function ($) {
        $.fn.extend({
            //表格合并单元格，colIdx要合并的列序号，从0开始
            "rowspan": function (colIdx) {
                return this.each(function () {
                    var that;
                    $('tr', this).each(function (row) {
                        $('td:eq(' + colIdx + ')', this).filter(':visible').each(function (col) {
                            if (that != null && $(this).html() == $(that).html()) {
                                rowspan = $(that).attr("rowSpan");
                                if (rowspan == undefined) {
                                    $(that).attr("rowSpan", 1);
                                    rowspan = $(that).attr("rowSpan");
                                }
                                rowspan = Number(rowspan) + 1;
                                $(that).attr("rowSpan", rowspan);
                                $(this).hide();
                            } else {
                                that = this;
                            }
                        });
                    });
                });
            }
        });

    })(jQuery);
    
    
    // 导出合格证
    function btn_export2() {
    	var url=window.location.href;
    	var loc = url.substring(url.lastIndexOf('=')+1, url.length);
    	$("#export_excel").form('submit',{
    		url:'/pcbmis/downLoadFile/downReportCertification',
    		onSubmit:function(param) {
    			param.reportNum=loc;
    		},
    		dataType:'json',
    		success:function(data) {
    			if (!data.result) {
    			     $.messager.alert('提示', data.msg, 'error');
    			}
    		}
    	});
    }