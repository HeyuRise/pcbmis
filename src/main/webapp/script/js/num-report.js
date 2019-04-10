function getUrlParam(name)  {  
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象  
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数  
	if (r!=null) return unescape(r[2]); 
	return null; //返回参数值  
}
$(function () {
	var url=window.location.href;
	var loc = url.substring(url.lastIndexOf('=')+1, url.length);
    var app = angular.module('testApp', []);
    app.controller('testCtrl', function ($scope, $http) {    	
        $http.post("/pcbmis/order/reportDetail?type=3&reportNum=" + loc).success(function (response) {
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
        $http.post("/pcbmis/order/reportDetail?type=3&reportNum=" + loc).success(function (response) {
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
	

	if(getUrlParam("edit")==null){
		$.ajax({  
	        type: "get",  
	        url: "/pcbmis/roleAuth/button?buttonId=7&type=2&orderNum=" + loc,  
	        contentType: 'application/json', 
	        success: function (data) {  
	        	if(data.result==0){
	        		$("#add_html").html('<a  class="easyui-linkbutton edit l-btn" iconCls="icon-edit" href="javascript:void(0)"  onclick="btn_edit()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">编辑</span></span></a>')
	        	}
	        }
		})
	}
	
})
function btn_edit(){
	 window.location.href="numreport_edit.html?reportNum="+getUrlParam("reportNum")
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