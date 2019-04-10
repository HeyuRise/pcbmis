var cate;
var allAuditDetail;
$(function () {
		var url=window.location.href;
		var loc = url.substring(url.lastIndexOf('=')+1, url.length);
		
	    var app = angular.module('testApp', []);

	    app.filter('trustHtml', function ($sce) {
	 		return function (input) {
	    		return $sce.trustAsHtml(input);
	 		}
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
		
	    app.controller('newCtrl', function ($scope, $http) {    	
	    	$http.post("/pcbmis/order/detailNew?checkNum=" + loc).success(function (response) {
	    		$scope.auditDetail = response.auditDetail;
	    		allAuditDetail = response.auditDetail;
		    })
		})
		
	
		app.controller('testCtrl', function ($scope, $http) {   
			$http.get("/pcbmis/util/template").success(function (response) {
	    		$scope.data = response;
		    })
		   
		    $scope.change_templateId = function () {  
				console.info($scope.selected);  
				$http.post("/pcbmis/order/detailNew?checkNum=" + loc+"&templateId="+$scope.selected).success(function (response) {
					$scope.auditDetail = response.auditDetail;
					allAuditDetail = response.auditDetail;
				})
			}  
			
	    	$http.post("/pcbmis/order/detailNew?checkNum=" + loc).success(function (response) {
	        	$scope.checkState = "";
	        	$scope.auditDetail = response.auditDetail;
	        	$scope.selected=response.auditDetail.checkHead.templateId; 
	        	allAuditDetail = response.auditDetail;
	        	if (response.boardTolerance != "") {
		        	$scope.boardTolerance = response.boardTolerance;	        		
	        	}
	        	var aa="";
	        	var cate="";
	        	if (response.auditDetail != "") {
		        	aa=response.auditDetail.factory.result ;
		        	cate=response.auditDetail.baseOrderInfo.categoryGrade;	
		        	$scope.checkState = response.auditDetail.checkState.state;
		        	$scope.boardTolerance = response.auditDetail.boardTolerance;
	        	} else {
	        		$(".input").val("");
	        	}
	        	
	        	$("#Num").html(response.auditDetail.baseOrderInfo.amountCheckoutPcs);
	        	table()
	        	var change_falseExposedCopper;
	        	var change_nodulesBurrs;
	        	var change_darkOfHoleTinLead;
	        	var change_haloRing;
	        	var change_outerRingWidth;
	        	var change_solderInHole;

	        	if(aa==""){//初始化
//		        	if(cate.indexOf('民')==-1 && cate.indexOf('-')==-1){  //军品
//		        		/*假性露铜*/
//		        		change_falseExposedCopper='<select class="select" id="falseExposedCopper_result" name="falseExposedCopper_result">'+
//													'<option>无假性露铜</option>'+
//													'<option>过孔假性露铜</option>'+
//													'<option>线路假性露铜</option>'+
//										           '</select>'
//						$("#change_falseExposedCopper").html(change_falseExposedCopper)
//						
//						/*结瘤毛刺*/
//						change_nodulesBurrs='<select class="select" id="nodulesBurrs_result" name="nodulesBurrs_result">'+
//													'<option>无结瘤，无毛刺</option>'+
//													'<option>结瘤，毛刺不影响孔径</option>'+
//													'<option>结瘤，毛刺影响孔径</option>'+
//										     '</select>'
//						$("#change_nodulesBurrs").html(change_nodulesBurrs)
//						
//						/*铅锡发暗*/
//						change_darkOfHoleTinLead='<select class="select" id="darkOfHoleTinLead_result" name="darkOfHoleTinLead_result">'+
//													'<option>无铅锡发暗</option>'+
//													'<option>有铅锡发暗</option>'+
//												'</select>'
//						$("#change_darkOfHoleTinLead").html(change_darkOfHoleTinLead)
//						
//						/*晕圈*/
//						change_haloRing='<select class="select" id="haloRing_result" name="haloRing_result">'+
//												'<option>无晕圈</option>'+
//												'<option>有晕圈</option>'+
//											'</select>'
//					    $("#change_haloRing").html(change_haloRing)
//					    
//					    /*外层环宽*/
//						change_outerRingWidth='<select  class="select" id="outerRingWidth_result"  name="outerRingWidth_result" >'+
//												'<option>≥0.05mm</option>'+
//												'<option><0.05mm,但未破坏</option>'+
//												'<option>破坏</option>'+
//											'</select>'
//					    $("#change_outerRingWidth").html(change_outerRingWidth)
//					    $("#outerRingWidth_result").val(response.auditDetail.outerRingWidth.require)
//					    /* 锡珠入孔*/
//					    change_solderInHole=' <select class="select" id="solderInHole_result"name="solderInHole_result">'+
//												'<option>无锡珠入孔</option>'+
//												'<option>锡珠入孔</option>'+
//											'</select>'
//						$("#change_solderInHole").html(change_solderInHole);
//	        	  }else{//民品
//	        		    /*假性露铜*/
//	        		    change_falseExposedCopper='<input  class="input" type="text"id="falseExposedCopper_result"name="falseExposedCopper_result" >'
//						$("#change_falseExposedCopper").html(change_falseExposedCopper)
//						
//						/*结瘤毛刺*/
//						 change_nodulesBurrs='<input  class="input"type="text" id="nodulesBurrs_result"name="nodulesBurrs_result">'
//						$("#change_nodulesBurrs").html(change_nodulesBurrs)
//						
//						/*铅锡发暗*/
//						change_darkOfHoleTinLead='<select class="select" id="darkOfHoleTinLead_result" name="darkOfHoleTinLead_result">'+
//													'<option>无铅锡发暗</option>'+
//													'<option>有铅锡发暗</option>'+
//												'</select>'
//						$("#change_darkOfHoleTinLead").html(change_darkOfHoleTinLead)
//						
//						/*晕圈*/
//						change_haloRing='<input type="text"class="input"id="haloRing_result"name="haloRing_result">'
//						 $("#change_haloRing").html(change_haloRing)
//						 
//						/*外层环宽*/
//						change_outerRingWidth='<select  class="select" id="outerRingWidth_result"  name="outerRingWidth_result" >'+
//												'<option>破环≤90°</option>'+
//												'<option>破环＞90°</option>'+
//											'</select>'
//					    $("#change_outerRingWidth").html(change_outerRingWidth)
//					    $("#outerRingWidth_result").val(response.auditDetail.outerRingWidth.require)
//					    /* 锡珠入孔*/
//					    change_solderInHole=' <select class="select" id="solderInHole_result"name="solderInHole_result">'+
//												'<option>无锡珠入孔</option>'+
//												'<option>单面≤3处，双面≤5处</option>'+
//												'<option>单面＞3处，双面＞5处</option>'+
//											'</select>'
//						$("#change_solderInHole").html(change_solderInHole)
//	        	  }
	        	}else{//二次编辑
	            	var falseExposedCopperresult=response.auditDetail.falseExposedCopper.result
		        	var nodulesBurrsresult=response.auditDetail.nodulesBurrs.result;
		        	var darkOfHoleTinLeadresult=response.auditDetail.darkOfHoleTinLead.result;
		        	var haloRingresult=response.auditDetail.haloRing.result;
	        		
//	        		if(cate.indexOf('民')==-1 && cate.indexOf('-')==-1){
//		        		/*假性露铜*/
//		        		change_falseExposedCopper='<select class="select" id="falseExposedCopper_result" name="falseExposedCopper_result">'+
//													'<option>无假性露铜</option>'+
//													'<option>过孔假性露铜</option>'+
//													'<option>线路假性露铜</option>'+
//										           '</select>'
//						$("#change_falseExposedCopper").html(change_falseExposedCopper);
//						/*结瘤毛刺*/
//						change_nodulesBurrs='<select class="select" id="nodulesBurrs_result" name="nodulesBurrs_result">'+
//													'<option>无结瘤，无毛刺</option>'+
//													'<option>结瘤，毛刺不影响孔径</option>'+
//													'<option>结瘤，毛刺影响孔径</option>'+
//										     '</select>'
//						$("#change_nodulesBurrs").html(change_nodulesBurrs);
//						/*铅锡发暗*/
//						change_darkOfHoleTinLead='<select class="select" id="darkOfHoleTinLead_result" name="darkOfHoleTinLead_result">'+
//													'<option>无铅锡发暗</option>'+
//													'<option>有铅锡发暗</option>'+
//												'</select>'
//						$("#change_darkOfHoleTinLead").html(change_darkOfHoleTinLead);
//						/*晕圈*/
//						change_haloRing='<select class="select" id="haloRing_result" name="haloRing_result">'+
//												'<option>无晕圈</option>'+
//												'<option>有晕圈</option>'+
//											'</select>'
//					    $("#change_haloRing").html(change_haloRing);
//					    /*外层环宽*/
//						change_outerRingWidth='<select  class="select" id="outerRingWidth_result"  name="outerRingWidth_result" >'+
//												'<option>≥0.05mm</option>'+
//												'<option><0.05mm,但未破坏</option>'+
//												'<option>破坏</option>'+
//											'</select>'
//					    $("#change_outerRingWidth").html(change_outerRingWidth);
//					   /* 锡珠入孔*/
//					    change_solderInHole=' <select class="select" id="solderInHole_result"name="solderInHole_result">'+
//												'<option>无锡珠入孔</option>'+
//												'<option>锡珠入孔</option>'+
//											'</select>'
//					    $("#change_solderInHole").html(change_solderInHole);
//		        		
//		        		$("#falseExposedCopper_result").val(response.auditDetail.falseExposedCopper.result);
//		        		$("#nodulesBurrs_result").val(response.auditDetail.nodulesBurrs.result);
//		        		$("#darkOfHoleTinLead_result").val(response.auditDetail.darkOfHoleTinLead.result);
//		        		$("#haloRing_result").val(response.auditDetail.haloRing.result);
//	        	  }else{
//	        		    /*假性露铜*/
//	        		    change_falseExposedCopper='<input  class="input" type="text"id="falseExposedCopper_result"name="falseExposedCopper_result" value="'+falseExposedCopperresult+'">'
//						$("#change_falseExposedCopper").html(change_falseExposedCopper)
//						
//						/*结瘤毛刺*/
//						change_nodulesBurrs='<input  class="input"type="text" id="nodulesBurrs_result"name="nodulesBurrs_result" value="'+nodulesBurrsresult+'">'
//						$("#change_nodulesBurrs").html(change_nodulesBurrs)
//						
//						/*铅锡发暗*/
//						change_darkOfHoleTinLead='<select class="select" id="darkOfHoleTinLead_result" name="darkOfHoleTinLead_result">'+
//													'<option>无铅锡发暗</option>'+
//													'<option>有铅锡发暗</option>'+
//												'</select>'
//						$("#change_darkOfHoleTinLead").html(change_darkOfHoleTinLead);
//						
//						/*晕圈*/
//						change_haloRing='<input type="text"class="input"id="haloRing_result"name="haloRing_result" value="'+haloRingresult+'">'
//						 $("#change_haloRing").html(change_haloRing)
//						 
//						/*外层环宽*/
//						change_outerRingWidth='<select  class="select" id="outerRingWidth_result"  name="outerRingWidth_result" >'+
//												'<option>破环≤90°</option>'+
//												'<option>破环＞90°</option>'+
//											'</select>'
//					    $("#change_outerRingWidth").html(change_outerRingWidth)
//					    
//					    /* 锡珠入孔*/
//					    change_solderInHole=' <select class="select" id="solderInHole_result"name="solderInHole_result">'+
//												'<option>无锡珠入孔</option>'+
//												'<option>单面≤3处，双面≤5处</option>'+
//												'<option>单面＞3处，双面＞5处</option>'+
//											'</select>'
//						$("#change_solderInHole").html(change_solderInHole)
//	        	  }
	        		
	        		$("#factory_bad_num").val(response.auditDetail.factory.badNum);
		            /*外层环宽*/
		            $("#outerRingWidth_result").val(response.auditDetail.outerRingWidth.result);
		            $("#outerRingWidth_judge_result").val(response.auditDetail.outerRingWidth.judgeResult);
		            if( $("#outerRingWidth_judge_result").get(0).selectedIndex=="1"){
		            	$("#outerRingWidth_bad_num").show();
		            	$("#outerRingWidth_bad_num").attr("class","badnum");
		            }
		            
		            /* 锡珠入孔*/
		            $("#solderInHole_result").val(response.auditDetail.solderInHole.result);
		            $("#solderInHole_judge_result").val(response.auditDetail.solderInHole.judgeResult);
		            if( $("#solderInHole_judge_result").get(0).selectedIndex=="1"){
		            	$("#solderInHole_bad_num").show();
		            	$("#solderInHole_bad_num").attr("class","badnum");
		            }
		            
		        	/*生产厂家*/
		            $("#factory_judge_result").val(response.auditDetail.factory.judgeResult);
		            if( $("#factory_judge_result").get(0).selectedIndex=="1"){
		            	$("#factory_bad_num").show();
		            	$("#factory_bad_num").attr("class","badnum");
		            }
		            
		            /*毛刺*/
		            $("#burrs_result").val(response.auditDetail.burrs.result);
		            $("#burrs_judge_result").val(response.auditDetail.burrs.judgeResult);
		            if( $("#burrs_judge_result").get(0).selectedIndex=="1"){
		            	$("#burrs_bad_num").show();
		            	$("#burrs_bad_num").attr("class","badnum");
		            }
		            
		            /*缺口*/
		            $("#gap_result").val(response.auditDetail.gap.result);
		            $("#gap_judge_result").val(response.auditDetail.gap.judgeResult);
		            if( $("#gap_judge_result").get(0).selectedIndex=="1"){
		            	$("#gap_bad_num").show();
		            	$("#gap_bad_num").attr("class","badnum");
		            }
		            
		            /*露铜*/
		            $("#exposedCopper_result").val(response.auditDetail.exposedCopper.result);
		            $("#exposedCopper_judge_result").val(response.auditDetail.exposedCopper.judgeResult);
		            if( $("#exposedCopper_judge_result").get(0).selectedIndex=="1"){
		            	$("#exposedCopper_bad_num").show();
		            	$("#exposedCopper_bad_num").attr("class","badnum");
		            }
		            
		            /*其他缺陷*/
		            $("#printed_board_else_judge_result").val(response.auditDetail.printed_board_else.judgeResult);
		            if( $("#printed_board_else_judge_result").get(0).selectedIndex=="1"){
		            	$("#printed_board_else_bad_num").show();
		            	$("#printed_board_else_bad_num").attr("class","badnum");
		            }
		            
		            //露织物、显布纹
		            $("#fabricTexture_result").val(response.auditDetail.fabricTexture.result);
		            $("#fabricTexture_judge_result").val(response.auditDetail.fabricTexture.judgeResult);
		            if( $("#fabricTexture_judge_result").get(0).selectedIndex=="1"){
		            	$("#fabricTexture_bad_num").show();
		            	$("#fabricTexture_bad_num").attr("class","badnum");
		            }
		            
		            /* 麻点和空洞*/
		            $("#pitVoid_judge_result").val(response.auditDetail.pitVoid.judgeResult);
		            if( $("#pitVoid_judge_result").get(0).selectedIndex=="1"){
		            	$("#pitVoid_bad_num").show();
		            	$("#pitVoid_bad_num").attr("class","badnum");
		            }
		            
		            /*分层、起泡*/
		            $("#delaminationFoaming_result").val(response.auditDetail.delaminationFoaming.result);
		            $("#delaminationFoaming_judge_result").val(response.auditDetail.delaminationFoaming.judgeResult);
		            if( $("#delaminationFoaming_judge_result").get(0).selectedIndex=="1"){
		            	$("#delaminationFoaming_bad_num").show();
		            	$("#delaminationFoaming_bad_num").attr("class","badnum");
		            }
		            
		            /*白斑、微裂纹*/
		            $("#spotCrack_judge_result").val(response.auditDetail.spotCrack.judgeResult);
		            if( $("#spotCrack_judge_result").get(0).selectedIndex=="1"){
		            	$("#spotCrack_bad_num").show();
		            	$("#spotCrack_bad_num").attr("class","badnum");
		            }
		            
		            /*印刷面*/
		            $("#board_prevent_smt_judge_result").val(response.auditDetail.board_prevent_smt.judgeResult);
		            if( $("#board_prevent_smt_judge_result").get(0).selectedIndex=="1"){
		            	$("#board_prevent_smt_bad_num").show();
		            	$("#board_prevent_smt_bad_num").attr("class","badnum");
		            }
		            
		            /*外来夹杂物*/
		            $("#foreignImpurity_judge_result").val(response.auditDetail.foreignImpurity.judgeResult);
		            if( $("#foreignImpurity_judge_result").get(0).selectedIndex=="1"){
		            	$("#foreignImpurity_bad_num").show();
		            	$("#foreignImpurity_bad_num").attr("class","badnum");
		            }
		            
		            /*其他缺陷*/
		            $("#base_material_else_judge_result").val(response.auditDetail.base_material_else.judgeResult);
		            if( $("#base_material_else_judge_result").get(0).selectedIndex=="1"){
		            	$("#base_material_else_bad_num").show();
		            	$("#base_material_else_bad_num").attr("class","badnum");
		            }
		            /*  颜色*/
		            $("#prevent_smt_color_judge_result").val(response.auditDetail.prevent_smt_color.judgeResult);
		            if( $("#prevent_smt_color_judge_result").get(0).selectedIndex=="1"){
		            	$("#prevent_smt_color_bad_num").show();
		            	$("#prevent_smt_color_bad_num").attr("class","badnum");
		            }
		            
		            /*覆盖度、附着力*/
		            $("#coverageAdhesion_judge_result").val(response.auditDetail.coverageAdhesion.judgeResult);
		            if( $("#coverageAdhesion_judge_result").get(0).selectedIndex=="1"){
		            	$("#coverageAdhesion_bad_num").show();
		            	$("#coverageAdhesion_bad_num").attr("class","badnum");
		            }
		            /*重合度*/
		            $("#coincidenceDegree_judge_result").val(response.auditDetail.coincidenceDegree.judgeResult);
		            if( $("#coincidenceDegree_judge_result").get(0).selectedIndex=="1"){
		            	$("#coincidenceDegree_bad_num").show();
		            	$("#coincidenceDegree_bad_num").attr("class","badnum");
		            }
		            
		            /*起泡、分层*/
		            $("#foamingLayering_result").val(response.auditDetail.foamingLayering.result);
		            $("#foamingLayering_judge_result").val(response.auditDetail.foamingLayering.judgeResult);
		            if( $("#foamingLayering_judge_result").get(0).selectedIndex=="1"){
		            	$("#foamingLayering_bad_num").show();
		            	$("#foamingLayering_bad_num").attr("class","badnum");
		            }
		            
		            /*波纹、皱褶*/
		            $("#corrugation_result").val(response.auditDetail.corrugation.result);
		            $("#corrugation_judge_result").val(response.auditDetail.corrugation.judgeResult);
		            if( $("#corrugation_judge_result").get(0).selectedIndex=="1"){
		            	$("#corrugation_bad_num").show();
		            	$("#corrugation_bad_num").attr("class","badnum");
		            }
		            
		            /*假性露铜*/
		            $("#falseExposedCopper_judge_result").val(response.auditDetail.falseExposedCopper.judgeResult);
		            if( $("#falseExposedCopper_judge_result").get(0).selectedIndex=="1"){
		            	$("#falseExposedCopper_bad_num").show();
		            	$("#falseExposedCopper_bad_num").attr("class","badnum");
		            }
		            
		            /* 掉桥、掉坝*/
		            $("#falseBridgeDam_result").val(response.auditDetail.falseBridgeDam.result);
		            $("#falseBridgeDam_judge_result").val(response.auditDetail.falseBridgeDam.judgeResult);
		            if( $("#falseExposedCopper_judge_result").get(0).selectedIndex=="1"){
		            	$("#falseBridgeDam_bad_num").show();
		            	$("#falseBridgeDam_bad_num").attr("class","badnum");
		            }
		            
		            /*色差*/
		            $("#chromaticAberration_result").val(response.auditDetail.chromaticAberration.result);
		            $("#chromaticAberration_judge_result").val(response.auditDetail.chromaticAberration.judgeResult);
		            if( $("#chromaticAberration_judge_result").get(0).selectedIndex=="1"){
		            	$("#chromaticAberration_bad_num").show();
		            	$("#chromaticAberration_bad_num").attr("class","badnum");
		            }
		            /*其他缺陷*/
		            $("#soldermask_else_judge_result").val(response.auditDetail.soldermask_else.judgeResult);
		            if( $("#soldermask_else_judge_result").get(0).selectedIndex=="1"){
		            	$("#soldermask_else_bad_num").show();
		            	$("#soldermask_else_bad_num").attr("class","badnum");
		            }
		            
		            /*印刷面*/
		            $("#board_character_judge_result").val(response.auditDetail.board_character.judgeResult);
		            if( $("#board_character_judge_result").get(0).selectedIndex=="1"){
		            	$("#board_character_bad_num").show();
		            	$("#board_character_bad_num").attr("class","badnum");
		            }
		            
		            /*颜色*/
		            $("#character_color_judge_result").val(response.auditDetail.character_color.judgeResult);
		            if( $("#character_color_judge_result").get(0).selectedIndex=="1"){
		            	$("#character_color_bad_num").show();
		            	$("#character_color_bad_num").attr("class","badnum");
		            }
		            
		            /* 覆盖度、附着力*/
		            $("#identificationAdhesion_judge_result").val(response.auditDetail.identificationAdhesion.judgeResult);
		            if( $("#identificationAdhesion_judge_result").get(0).selectedIndex=="1"){
		            	$("#identificationAdhesion_bad_num").show();
		            	$("#identificationAdhesion_bad_num").attr("class","badnum");
		            }
		            
		            /*Logo*/
		            $("#logo_judge_result").val(response.auditDetail.logo.judgeResult);
		            if( $("#logo_judge_result").get(0).selectedIndex=="1"){
		            	$("#logo_bad_num").show();
		            	$("#logo_bad_num").attr("class","badnum");
		            }
		            
		            /*批次号*/
		            $("#batch_number_judge_result").val(response.auditDetail.batch_number.judgeResult);
		            if( $("#batch_number_judge_result").get(0).selectedIndex=="1"){
		            	$("#batch_number_bad_num").show();
		            	$("#batch_number_bad_num").attr("class","badnum");
		            }
		            
		            /*特殊板号*/
		            $("#special_board_num_judge_result").val(response.auditDetail.special_board_num.judgeResult);
		            if( $("#special_board_num_judge_result").get(0).selectedIndex=="1"){
		            	$("#special_board_num_bad_num").show();
		            	$("#special_board_num_bad_num").attr("class","badnum");
		            }
		            
		            /*其他缺陷*/
		            $("#mark_else_judge_result").val(response.auditDetail.mark_else.judgeResult);
		            if( $("#mark_else_judge_result").get(0).selectedIndex=="1"){
		            	$("#mark_else_bad_num").show();
		            	$("#mark_else_bad_num").attr("class","badnum");
		            }
		            
		        	/*结瘤、毛刺*/
		            $("#nodulesBurrs_judge_result").val(response.auditDetail.nodulesBurrs.judgeResult);
		            if( $("#nodulesBurrs_judge_result").get(0).selectedIndex=="1"){
		            	$("#nodulesBurrs_bad_num").show();
		            	$("#nodulesBurrs_bad_num").attr("class","badnum");
		            }
		            
		            /*孔内铅锡发暗*/
		            $("#darkOfHoleTinLead_judge_result").val(response.auditDetail.darkOfHoleTinLead.judgeResult);
		            if( $("#darkOfHoleTinLead_judge_result").get(0).selectedIndex=="1"){
		            	$("#darkOfHoleTinLead_bad_num").show();
		            	$("#darkOfHoleTinLead_bad_num").attr("class","badnum");
		            }
		            
		            /*焊盘起翘*/
		            $("#padCocked_result").val(response.auditDetail.padCocked.result);
		            $("#padCocked_judge_result ").val(response.auditDetail.padCocked.judgeResult);
		            if( $("#padCocked_judge_result").get(0).selectedIndex=="1"){
		            	$("#padCocked_bad_num").show();
		            	$("#padCocked_bad_num").attr("class","badnum");
		            }
		            /*晕圈*/
		            $("#haloRing_judge_result").val(response.auditDetail.haloRing.judgeResult);
		            if( $("#haloRing_judge_result").get(0).selectedIndex=="1"){
		            	$("#haloRing_bad_num").show();
		            	$("#haloRing_bad_num").attr("class","badnum");
		            }
		            
		            /*堵孔*/
		            $("#clogHole_result").val(response.auditDetail.clogHole.result);
		            $("#clogHole_judge_result").val(response.auditDetail.clogHole.judgeResult);
		            if( $("#clogHole_judge_result").get(0).selectedIndex=="1"){
		            	$("#clogHole_bad_num").show();
		            	$("#clogHole_bad_num").attr("class","badnum");
		            }
		            
		            /*线宽、间距等*/
		            $("#lineWidthSpacing_judge_result").val(response.auditDetail.lineWidthSpacing.judgeResult);
		            if( $("#lineWidthSpacing_judge_result").get(0).selectedIndex=="1"){
		            	$("#lineWidthSpacing_bad_num").show();
		            	$("#lineWidthSpacing_bad_num").attr("class","badnum");
		            }
		            
		            /*其他缺陷*/
		            $("#conductive_pattern_else_judge_result").val(response.auditDetail.conductive_pattern_else.judgeResult);
		            if( $("#conductive_pattern_else_judge_result").get(0).selectedIndex=="1"){
		            	$("#conductive_pattern_else_bad_num").show();
		            	$("#conductive_pattern_else_bad_num").attr("class","badnum");
		            }
		            
		            /*表面处理*/
		            $("#surface_process_judge_result").val(response.auditDetail.surface_process.judgeResult);
		            if( $("#surface_process_judge_result").get(0).selectedIndex=="1"){
		            	$("#surface_process_bad_num").show();
		            	$("#surface_process_bad_num").attr("class","badnum");
		            }
		            
		            $("#boardLong_judge_result").val(response.auditDetail.boardLong.judgeResult);
		            if( $("#boardLong_judge_result").get(0).selectedIndex=="1"){
		            	$("#boardLong_bad_num").show();
		            	$("#boardLong_bad_num").attr("class","badnum");
		            }
		            
		            $("#boardPly_judge_result").val(response.auditDetail.boardPly.judgeResult);
		            if( $("#boardPly_judge_result").get(0).selectedIndex=="1"){
		            	$("#boardPly_bad_num").show();
		            	$("#boardPly_bad_num").attr("class","badnum");
		            }
		            $("#boardWide_judge_result").val(response.auditDetail.boardWide.judgeResult);
		            if( $("#boardWide_judge_result").get(0).selectedIndex=="1"){
		            	$("#boardWide_bad_num").show();
		            	$("#boardWide_bad_num").attr("class","badnum");
		            }
		        	/*弓曲高度*/
		            $("#warp_height_judge_result ").val(response.auditDetail.warp_height.judgeResult);

		            /*扭曲高度*/
		            $("#lay_height_judge_result").val(response.auditDetail.Lay_height.judgeResult);
		            if( $("#lay_height_judge_result").get(0).selectedIndex=="1"){
		            	$("#lay_height_bad_num").show();
		            	$("#lay_height_bad_num").attr("class","badnum");
		            }		            
		            
		            //过孔处理
		            $("#viaHoleProcess_judge_result").val(response.auditDetail.viaHoleProcess.judgeResult);
		            if( $("#viaHoleProcess_judge_result").get(0).selectedIndex=="1"){
		            	$("#viaHoleProcess_badNum").show();
		            	$("#viaHoleProcess_badNum").attr("class","badnum");
		            }	
		            
	        	}

	        });
	    });

})
	

$(function(){
	$("#products_num").validatebox({
		validType:"number",
		required:'true',
	})
	$("#products_describe").validatebox({
		required:'true',
	})
	$.extend($.fn.validatebox.defaults.rules,{  
		number : {  
           validator : function(value) {
               return/^\+?[1-9]\d*$/i.test(value);
           },
           message : '请输入正整数'
		},
	  })
      
      
	$("#submission_confirmation").dialog({
	    title: '提交确认',
	    width:'440',
	    height: 'auto',
	    closed: true,
	    closable:false,
	    modal: true,
	    buttons:[{
	    	text:'保存',
	    	iconCls:'icon-save',
	    	handler:function(){
	    		if($("#submission_confirmation_form").form("validate")){
		    		var url=window.location.href;
					var loc = url.substring(url.lastIndexOf('=')+1, url.length);
					$.ajax({  
	    				 type: "POST",  
	    				 url: "/pcbmis/unqualified?check_num="+loc+"&desc="+$("#products_describe").val()+"&number="+$("#products_num").val() ,  
	    				 contentType: 'application/json', 
	    				 success: function (data) {  
	    					 if(data.result==0){
	    						window.location = "num.html?bad=1&checkNum="+loc;
	    					 }else{
	    						 $.messager.alert("提示信息", data.msg,"error");
	    					 }
	    				 }
	    			})

	    		}

	    		
	    	}
	    },{
	    	text:'取消',
	    	iconCls:'icon-cancel',
	    	handler:function(){
	    		$("#submission_confirmation").dialog("close");
			 	var url=window.location.href;
				var loc = url.substring(url.lastIndexOf('=')+1, url.length);
	    		window.location = "num.html?checkNum="+loc;
	    	}
	    }]
	})

	


})

//过孔处理
function OnInput (event) {
	$("#viaHoleProcess_result").val(event.target.value)
	//alert ("The new content: " + event.target.value);
}
function OnPropChanged (event) {
	if (event.propertyName.toLowerCase () == "value") {
		$("#viaHoleProcess_result").val(event.srcElement.value)
		//alert ("The new content: " + event.srcElement.value);
	}
}

var submitFlag = 0;
/*检验记录表详情-提交校验*/
function testtijiao(){
	if (submitFlag==1) {
		return;
	}
	submitFlag=1;
 	var flag = true;
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
	
//	翘曲度>=0数字校验
	$(".border").each(function(){
		 var border=/^[0-9]\d*(\.\d+)?$/;
		 $(this).removeClass("red") ;
		  if($(this).val()=="" ){
			  $(this).addClass("red")
			  flag = false;
		  };
		  if(!border.test($(this).val()) ){
			  $(this).addClass("red")
			  flag = false;
		  };
	})
	
//    不良数量
	$(".badnum").each(function (){
		 var badnumcheck=/^\+?[1-9]\d*$/;
		 $(this).removeClass("red") ;
		  if($(this).val()=="" ){
			  $(this).addClass("red")
			  flag = false;
		  };
		  if(!badnumcheck.test($(this).val()) ){
			  $(this).addClass("red")
			  flag = false;
		  };

	})
	
	
//	select --1
	$(".select").each(function(){
		 $(this).removeClass("red") ;
		  if($(this)[0].selectedIndex=="-1"){
			  $(this).addClass("red")
			  flag = false;
		  };
	})
	
	
//	动态获取select集合--0 
//	$(".select0").each(function(){
//		 $(this).removeClass("red") ;
//		  if($(this)[0].selectedIndex=="0"){
//			  $(this).addClass("red")
//			  flag = false;
//		  };
//	})

	 if(flag){ 
		 	var url=window.location.href;
			var loc = url.substring(url.lastIndexOf('=')+1, url.length);
			var jsonstr = "";
		    var sizeAndWarpDegree = [];
			//var N = parseInt(document.getElementById("Num").innerHTML);
		    var N=$("#tablerecord").find("tr").length-7;
			for (var i =0; i <N; i++) {
			    sizeAndWarpDegree[i]={}		
				sizeAndWarpDegree[i].boardLong=$("#boardLong"+i+"").val();
			    sizeAndWarpDegree[i].boardNum=$("#boardNum"+i+"").val();
				sizeAndWarpDegree[i].boardWide=$("#boardWide"+i+"").val();
				sizeAndWarpDegree[i].boardPly=$("#boardPly"+i+"").val();
				sizeAndWarpDegree[i].layHeight=$("#layHeight"+i+"").val();
				sizeAndWarpDegree[i].warpHeight=$("#WarpHeight"+i+"").val();
				sizeAndWarpDegree[i].judge=$("#judge"+i+"").find("option:selected").text();
				if($("#judge"+i+"").find("option:selected").text()=="不合格"){
					$("#judg_bad").val("0")
				}
			 }
			 var viaHoleProcess={}
			 viaHoleProcess.badNum=$("#viaHoleProcess_badNum").val();
			 viaHoleProcess.judge=$("#viaHoleProcess_judge_result").val();
			 viaHoleProcess.require=$("#viaHoleProcess_require").val();
			 viaHoleProcess.result=$("#viaHoleProcess_result").val();
			 var note={}
			 note.note=$("#note_note").html();
			 jsonstr = JSON.stringify({
				 	// 固定信息
				 	burrs_require:allAuditDetail.burrs.require,
				 	gap_require:allAuditDetail.gap.require,
				 	exposedCopper_require:allAuditDetail.exposedCopper.require,
				 	fabricTexture_require:allAuditDetail.fabricTexture.require,
				 	pitVoid_require:allAuditDetail.pitVoid.require,
				 	spotCrack_require:allAuditDetail.spotCrack.require,
				 	delaminationFoaming_require:allAuditDetail.delaminationFoaming.require,
				 	foreignImpurity_require:allAuditDetail.foreignImpurity.require,
				 	coverageAdhesion_require:allAuditDetail.coverageAdhesion.require,
				 	coincidenceDegree_require:allAuditDetail.coincidenceDegree.require,
				 	foamingLayering_require:allAuditDetail.foamingLayering.require,
				 	corrugation_require:allAuditDetail.corrugation.require,
				 	falseExposedCopper_require:allAuditDetail.falseExposedCopper.require,
				 	falseBridgeDam_require:allAuditDetail.falseBridgeDam.require,
				 	chromaticAberration_require:allAuditDetail.chromaticAberration.require,
				 	identificationAdhesion_require:allAuditDetail.identificationAdhesion.require,
				 	nodulesBurrs_require:allAuditDetail.nodulesBurrs.require,
				 	darkOfHoleTinLead_require:allAuditDetail.darkOfHoleTinLead.require,
				 	padCocked_require:allAuditDetail.padCocked.require,
				 	haloRing_require:allAuditDetail.haloRing.require,
				 	outerRingWidth_require:allAuditDetail.outerRingWidth.require,
				 	solderInHole_require:allAuditDetail.solderInHole.require,
				 	clogHole_require:allAuditDetail.clogHole.require,
				 	lineWidthSpacing_require:allAuditDetail.lineWidthSpacing.require,
				 	boardLong_result:allAuditDetail.boardLong.result,
				 	boardWide_result:allAuditDetail.boardWide.result,
				 	boardPly_result:allAuditDetail.boardPly.result,
				 	lay_height_result:allAuditDetail.Lay_height.result,
				 	warp_height_result:allAuditDetail.warp_height.result,

				 	viaHoleProcess:viaHoleProcess,
				 	
				 	factory_result:$("#factory_result").val(),
			    	factory_bad_num:$("#factory_bad_num").val(),
			    	factory_judge_result:$("#factory_judge_result  option:selected").text(),
			    	
			    	burrs_result:$("#burrs_result").val(),
		        	burrs_bad_num:$("#burrs_bad_num").val(), 
		        	burrs_judge_result:$("#burrs_judge_result option:selected").text(),
		        	
		        	gap_result:$("#gap_result").val(),
		        	gap_bad_num:$("#gap_bad_num").val(), 
		        	gap_judge_result:$("#gap_judge_result option:selected").text(),
		        	
		        	exposedCopper_result:$("#exposedCopper_result").val(),
		        	exposedCopper_bad_num:$("#exposedCopper_bad_num").val(), 
		        	exposedCopper_judge_result:$("#exposedCopper_judge_result option:selected").text(),
		        	
		        	printed_board_else_require:$("#printed_board_else_require").val(),
		        	printed_board_else_result:$("#printed_board_else_result").val(), 
		        	printed_board_else_bad_num:$("#printed_board_else_bad_num").val(), 
		        	printed_board_else_judge_result:$("#printed_board_else_judge_result option:selected").text(),
		        	
		            /*基材*/
		        	fabricTexture_result:$("#fabricTexture_result").val(),
		        	fabricTexture_bad_num:$("#fabricTexture_bad_num").val(),
		        	fabricTexture_judge_result:$("#fabricTexture_judge_result option:selected").text(),
		        	
		        	pitVoid_result:$("#pitVoid_result").val(),
		        	pitVoid_bad_num:$("#pitVoid_bad_num").val(),
		        	pitVoid_judge_result:$("#pitVoid_judge_result option:selected").text(),
		        	
		        	spotCrack_result:$("#spotCrack_result").val(),
		        	spotCrack_bad_num:$("#spotCrack_bad_num").val(),
		        	spotCrack_judge_result:$("#spotCrack_judge_result option:selected").text(),
		        	
		        	delaminationFoaming_result:$("#delaminationFoaming_result").val(),
		        	delaminationFoaming_bad_num:$("#delaminationFoaming_bad_num").val(),
		        	delaminationFoaming_judge_result:$("#delaminationFoaming_judge_result option:selected").text(),
		        	
		        	foreignImpurity_result:$("#foreignImpurity_result").val(),
		        	foreignImpurity_bad_num:$("#foreignImpurity_bad_num").val(),
		        	foreignImpurity_judge_result:$("#foreignImpurity_judge_result option:selected").text(),
		        	
		        	base_material_else_require:$("#base_material_else_require").val(),
		        	base_material_else_result:$("#base_material_else_result").val(),
		        	base_material_else_bad_num:$("#base_material_else_bad_num").val(),
		        	base_material_else_judge_result:$("#base_material_else_judge_result option:selected").text(),
		        	
		        	//阻焊膜
		        	board_prevent_smt_result:$("#board_prevent_smt_result").val(),
		        	board_prevent_smt_bad_num:$("#board_prevent_smt_bad_num").val(),
		        	board_prevent_smt_judge_result:$("#board_prevent_smt_judge_result option:selected").text(),
		        	
		        	
		        	prevent_smt_color_result:$("#prevent_smt_color_result").val(),
		        	prevent_smt_color_bad_num:$("#prevent_smt_color_bad_num").val(),
		        	prevent_smt_color_judge_result:$("#prevent_smt_color_judge_result option:selected").text(),
		        	
		        	board_prevent_smt_result:$("#board_prevent_smt_result").val(),
		        	board_prevent_smt_bad_num:$("#board_prevent_smt_bad_num").val(),
		        	board_prevent_smt_judge_result:$("#board_prevent_smt_judge_result option:selected").text(),
		        	
		        	
		        	coverageAdhesion_result:$("#coverageAdhesion_result").val(),
		        	coverageAdhesion_bad_num:$("#coverageAdhesion_bad_num").val(),
		        	coverageAdhesion_judge_result:$("#coverageAdhesion_judge_result option:selected").text(),
		        	
		        	
		        	coincidenceDegree_result:$("#coincidenceDegree_result").val(),
		        	coincidenceDegree_bad_num:$("#coincidenceDegree_bad_num").val(),
		        	coincidenceDegree_judge_result:$("#coincidenceDegree_judge_result option:selected").text(),
		        	
		        	foamingLayering_result:$("#foamingLayering_result").val(),
		        	foamingLayering_bad_num:$("#foamingLayering_bad_num").val(),
		        	foamingLayering_judge_result:$("#foamingLayering_judge_result option:selected").text(),
		        	
		        	corrugation_result:$("#corrugation_result").val(),
		        	corrugation_bad_num:$("#corrugation_bad_num").val(),
		        	corrugation_judge_result:$("#corrugation_judge_result option:selected").text(),
		        	
		        	falseExposedCopper_result:$("#falseExposedCopper_result").val(),
		        	falseExposedCopper_bad_num:$("#falseExposedCopper_bad_num").val(),
		        	falseExposedCopper_judge_result:$("#falseExposedCopper_judge_result option:selected").text(),
		        	
		        	
		        	falseBridgeDam_result:$("#falseBridgeDam_result").val(),
		        	falseBridgeDam_bad_num:$("#falseBridgeDam_bad_num").val(),
		        	falseBridgeDam_judge_result:$("#falseBridgeDam_judge_result option:selected").text(),
		        	
		        	chromaticAberration_result:$("#chromaticAberration_result").val(),
		        	chromaticAberration_bad_num:$("#chromaticAberration_bad_num").val(),
		        	chromaticAberration_judge_result:$("#chromaticAberration_judge_result option:selected").text(),
		        	
		        	soldermask_else_require:$("#soldermask_else_require").val(),
		        	soldermask_else_result:$("#soldermask_else_result").val(),
		        	soldermask_else_bad_num:$("#soldermask_else_bad_num").val(),
		        	soldermask_else_judge_result:$("#soldermask_else_judge_result option:selected").text(),
		        	
		        	
		        	//标记
		        	board_character_result:$("#board_character_result").val(),
		        	board_character_bad_num:$("#board_character_bad_num").val(),
		        	board_character_judge_result:$("#board_character_judge_result option:selected").text(),
		        	
		        	character_color_result:$("#character_color_result").val(),
		        	character_color_bad_num:$("#character_color_bad_num").val(),
		        	character_color_judge_result:$("#character_color_judge_result option:selected").text(),
		        	
		        	identificationAdhesion_result:$("#identificationAdhesion_result").val(),
		        	identificationAdhesion_bad_num:$("#identificationAdhesion_bad_num").val(),
		        	identificationAdhesion_judge_result:$("#identificationAdhesion_judge_result option:selected").text(),
		        	
		        	logo_result:$("#logo_result").val(),
		        	logo_bad_num:$("#logo_bad_num").val(),
		        	logo_judge_result:$("#logo_judge_result option:selected").text(),
		        	
		        	
		        	batch_number_result:$("#batch_number_result").val(),
		        	batch_number_bad_num:$("#batch_number_bad_num").val(),
		        	batch_number_judge_result:$("#batch_number_judge_result option:selected").text(),
		        	
		        	special_board_num_require:$("#special_board_num_require").val(),
		        	special_board_num_result:$("#special_board_num_result").val(),
		        	special_board_num_bad_num:$("#special_board_num_bad_num").val(),
		        	special_board_num_judge_result:$("#special_board_num_judge_result option:selected").text(),
		        	
		        	mark_else_require:$("#mark_else_require").val(),
		        	mark_else_result:$("#mark_else_result").val(),
		        	mark_else_bad_num:$("#mark_else_bad_num").val(),
		        	mark_else_judge_result:$("#mark_else_judge_result option:selected").text(),
		        	
		        	
		        	//导电图形
		        	nodulesBurrs_result:$("#nodulesBurrs_result").val(),
		        	nodulesBurrs_bad_num:$("#nodulesBurrs_bad_num").val(),
		        	nodulesBurrs_judge_result:$("#nodulesBurrs_judge_result option:selected").text(),
		        	
		        	darkOfHoleTinLead_result:$("#darkOfHoleTinLead_result").val(),
		        	darkOfHoleTinLead_bad_num:$("#darkOfHoleTinLead_bad_num").val(),
		        	darkOfHoleTinLead_judge_result:$("#darkOfHoleTinLead_judge_result option:selected").text(),
		        	
		        	padCocked_result:$("#padCocked_result").val(),
		        	padCocked_bad_num:$("#padCocked_bad_num").val(),
		        	padCocked_judge_result:$("#padCocked_judge_result option:selected").text(),
		        	
		        	haloRing_result:$("#haloRing_result").val(),
		        	haloRing_bad_num:$("#haloRing_bad_num").val(),
		        	haloRing_judge_result:$("#haloRing_judge_result option:selected").text(),

		        	
		        	outerRingWidth_result:$("#outerRingWidth_result").val(),
		        	outerRingWidth_bad_num:$("#outerRingWidth_bad_num").val(),
		        	outerRingWidth_judge_result:$("#outerRingWidth_judge_result option:selected").text(),
		        	
		        	
		        	solderInHole_result:$("#solderInHole_result").val(),
		        	solderInHole_bad_num:$("#solderInHole_bad_num").val(),
		        	solderInHole_judge_result:$("#solderInHole_judge_result option:selected").text(),
		        	
		        	
		        	clogHole_result:$("#clogHole_result").val(),
		        	clogHole_bad_num:$("#clogHole_bad_num").val(),
		        	clogHole_judge_result:$("#clogHole_judge_result option:selected").text(),
		        	
		        	
		        	lineWidthSpacing_result:$("#lineWidthSpacing_result").val(),
		        	lineWidthSpacing_bad_num:$("#lineWidthSpacing_bad_num").val(),
		        	lineWidthSpacing_judge_result:$("#lineWidthSpacing_judge_result option:selected").text(),
		        	
		        	conductive_pattern_else_require:$("#conductive_pattern_else_require").val(),
		        	conductive_pattern_else_result:$("#conductive_pattern_else_result").val(),
		        	conductive_pattern_else_bad_num:$("#conductive_pattern_else_bad_num").val(),
		        	conductive_pattern_else_judge_result:$("#conductive_pattern_else_judge_result option:selected").text(),
		        	
		        	//表面处理
		        	surface_process_result:$("#surface_process_result").val(),
		        	surface_process_bad_num:$("#surface_process_bad_num").val(),
		        	surface_process_judge_result:$("#surface_process_judge_result option:selected").text(),
		        	
		        	//外形尺寸
		        	boardLong_bad_num:$("#boardLong_bad_num").val(),
		        	boardLong_judge_result:$("#boardLong_judge_result option:selected").text(),
		        	
		        	boardWide_bad_num:$("#boardWide_bad_num").val(),
		        	boardWide_judge_result:$("#boardWide_judge_result option:selected").text(),
		        	
		        	boardPly_bad_num:$("#boardPly_bad_num").val(),
		        	boardPly_judge_result:$("#boardPly_judge_result option:selected").text(),
		        	
		        	lay_height_bad_num:$("#lay_height_bad_num").val(),
		        	lay_height_judge_result:$("#lay_height_judge_result option:selected").text(),
		        		
		        	warp_height_bad_num:$("#warp_height_bad_num").val(),
		        	warp_height_judge_result:$("#warp_height_judge_result option:selected").text(), 	
     
				    sizeAndWarpDegree:sizeAndWarpDegree,
	
				    note:note,
				     
		     });
			 var templateId=$("#templateId").val();
			 if($("#save_judge").val()==""){
				 $.messager.alert("操作提示", "页面加载完成后才能提交！","info");
			 }else{
				 $.ajax({  
				        type: "PUT",  
				        url: "/pcbmis/modify/check?checkNum=" + loc,  
				        data:jsonstr,
				        contentType: 'application/json', 
				        beforeSend:ajaxLoading,
				        success: function (data) {  
				        	ajaxLoadEnd();
				        	if(data.result==0){
				        		$.messager.alert("", "提交成功！",'info',function(){
				        			// if($("input").hasClass("badnum") || $("#judg_bad").val()=="0"){
				        			//	 $("#submission_confirmation_form").form("reset")
				        			//	 $("#submission_confirmation").dialog("open");
				        			 //}else{
				        				 window.location = "num.html?checkNum="+loc+"&edit="+loc;
				        			 //}
				        			 	
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

			    return;
	} else{
	   $.messager.alert("操作提示", "信息填写不完整或者填写不规范！","info");
	}
	submitFlag=0; 
}




function table(){
	var url=window.location.href;
	var loc = url.substring(url.lastIndexOf('=')+1, url.length);
	var flag=true;
	$.ajax({  
	    type: "POST",  
	    url: "/pcbmis/order/detailNew?checkNum=" + loc,  
	    contentType: 'application/json', 
	    success: function (data) {  
			var sizeAndWarpDegrees = data.auditDetail.sizeAndWarpDegrees;
			var auditDetail=data.auditDetail
			testtable(sizeAndWarpDegrees,auditDetail);
		}
	})
}
function testtable(sizeAndWarpDegrees,auditDetail){
	var grade=auditDetail.baseOrderInfo.categoryGrade;	
	var number=document.getElementById("Num").innerHTML;
	var index=""
	if(sizeAndWarpDegrees==""){
		index=0;
    }else{
    	index=1;
    }
	if(grade=="民品 2级品" || grade=="民品" || grade=="-"){//民2或民
		var Num = ""
		if(parseInt(1)<=parseInt(number)  && parseInt(number)<=parseInt(8)){
			Num=parseInt(number);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else if(parseInt(9)<=parseInt(number) && parseInt(number)<=parseInt(50)){
			Num=parseInt(8);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else if(parseInt(51)<=parseInt(number) && parseInt(number)<=parseInt(90)){
			Num=parseInt(13);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else if(parseInt(91)<=parseInt(number) && parseInt(number)<=parseInt(280)){
			Num=parseInt(19);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else if(parseInt(281)<=parseInt(number) && parseInt(number)<=parseInt(500)){
			Num=parseInt(21);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else if(parseInt(501)<=parseInt(number) && parseInt(number)<=parseInt(1200)){
			Num=parseInt(27);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else if(parseInt(1201)<=parseInt(number) && parseInt(number)<=parseInt(3200)){
			Num=parseInt(35);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else if(parseInt(3201)<=parseInt(number) && parseInt(number)<=parseInt(10000)){
			Num=parseInt(37);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else if(parseInt(number)>=10001){
			Num=parseInt(46);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else{}
	}else if(grade=="民品 3级品"){//民3
		var Num = ""
		if(parseInt(1)<=parseInt(number)  && parseInt(number)<=parseInt(13)){
			Num=parseInt(number);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else if(parseInt(14)<=parseInt(number) && parseInt(number)<=parseInt(90)){
			Num=parseInt(13);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else if(parseInt(91)<=parseInt(number) && parseInt(number)<=parseInt(150)){
			Num=parseInt(19);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else if(parseInt(151)<=parseInt(number) && parseInt(number)<=parseInt(500)){
			Num=parseInt(29);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else if(parseInt(501)<=parseInt(number) && parseInt(number)<=parseInt(1200)){
			Num=parseInt(34);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else if(parseInt(1201)<=parseInt(number) && parseInt(number)<=parseInt(3200)){
			Num=parseInt(42);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else if(parseInt(3201)<=parseInt(number) && parseInt(number)<=parseInt(10000)){
			Num=parseInt(50);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else if(parseInt(number)>=10001){
			Num=parseInt(60);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else{}				
	}else{//军
		var Num = ""
		if(parseInt(1)<=parseInt(number)  && parseInt(number)<=parseInt(5)){
			Num=parseInt(number);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else if(parseInt(6)<=parseInt(number) && parseInt(number)<=parseInt(50)){
			Num=parseInt(5);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else if(parseInt(51)<=parseInt(number) && parseInt(number)<=parseInt(90)){
			Num=parseInt(7);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else if(parseInt(91)<=parseInt(number) && parseInt(number)<=parseInt(150)){
			Num=parseInt(11);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else if(parseInt(151)<=parseInt(number) && parseInt(number)<=parseInt(280)){
			Num=parseInt(13);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else if(parseInt(281)<=parseInt(number) && parseInt(number)<=parseInt(500)){
			Num=parseInt(16);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else if(parseInt(501)<=parseInt(number) && parseInt(number)<=parseInt(1200)){
			Num=parseInt(19);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else if(parseInt(number)>=1201){
			Num=parseInt(23);
			tablegrade(sizeAndWarpDegrees,auditDetail,Num,index)
		}else{}		
	}
}
//计算弓曲高度
function calculation(i){
	return;
	if($("#boardNum"+i+"").val()!='' && $("#boardLong"+i+"").val()!=''  && $("#boardWide"+i+"").val()!='' && $("#boardPly"+i+"").val()!=''){
		var layHeight=(Math.max($("#boardLong"+i+"").val(),$("#boardWide"+i+"").val()))*0.0075;
		$("#layHeight"+i+"").val(layHeight.toFixed(2));
		var WarpHeight=(Math.sqrt(Math.pow($("#boardLong"+i+"").val(),2)+Math.pow($("#boardWide"+i+"").val(),2)))*0.0075;
		$("#WarpHeight"+i+"").val(WarpHeight.toFixed(2));
	}
}

function tablegrade(sizeAndWarpDegrees,auditDetail,Num,index){
	$("#min_Num").html(Num)
	if(index==0){//无值（初始化）
		var data = ""; 
		data += " <table id='tablerecord' class='guding-table' ellspacing='0' cellpadding='0' >"; 
		data += "	<tr>" +
		  "	<th colspan='1'>NO：</th>" +
		  "	<th colspan='2'>"+auditDetail.checkHead.serialNumber+"</th>" +
		  "	<th colspan='2'>版次：</th>" +
		  " <th colspan='2' >"+auditDetail.checkHead.revision+"</th>" +
		  "	<th colspan='2'>编号：</th>" +
		  " <th colspan='2' >"+auditDetail.checkHead.document_number+"</th>" +
		  " </tr>" +
		  " <tr>" +
		  "	<th colspan='1' >工单号</th>" +
		  "	<th colspan='4'>"+auditDetail.baseOrderInfo.orderNum+"</th>" +
		  "	<th colspan='2'>验收标准</th>" +
		  "	<th colspan='4'>"+auditDetail.checkHead.checkStandard+"</th>" +
		  " </tr>" +
		  " <tr>" +
		  "	<th colspan='1'>来料数量块</th>" +
		  "	<th colspan='4'>"+auditDetail.baseOrderInfo.amountCheckoutPcs+"</th>" +
		  "	<th colspan='2'>抽检数量块</th>" +
		  "	<th colspan='4'>"+auditDetail.checkHead.spotCheckNum+"</th>" +
		  " </tr>" +
		  " <tr>" +
		  " <th colspan='1'>顾客单位</th>" +
		  " <th colspan='4'>"+auditDetail.baseOrderInfo.guestName+"</th>" +
		  " <th colspan='2'>板名</th>" +
		  " <th colspan='4'>"+auditDetail.baseOrderInfo.boardName+"</th>" +
		  " </tr>" +
		" <tr>" + 
		" <th style='width:10%'  rowspan='3'>板号</th>" + 
		" <th colspan='2' style='width:20%'>板长</th>" + 
		" <th colspan='2' style='width:20%'>板宽</th>" + 
		" <th colspan='2' style='width:20%'>板厚</th>"+
		" <th rowspan='2' style='width:8%'>弓曲高度</th>"+
		" <th rowspan='2' style='width:8%'>扭曲高度</th>"+
		" <th rowspan='3' style='width:9%'>判定</th>"+
		" <th rowspan='3' style='width:5%;'>操作</th>"+
		"</tr>" +
		" <tr>" + 
		" <th>板长</th>" + 
		" <th>公差</th>" + 
		" <th>板宽</th>"+
		" <th>公差</th>"+
		" <th>板厚</th>"+
		" <th>公差</th>"+
		"</tr>" +
		" <tr>" + 
		" <th>"+auditDetail.boardLong.require+"</th>" + 
		" <th>"+auditDetail.boardLong.boardTolerance+"</th>" + 
		" <th>"+auditDetail.boardWide.require+"</th>"+
		" <th>"+auditDetail.boardWide.boardTolerance+"</th>"+
		" <th>"+auditDetail.boardPly.require+"</th>"+
		" <th>"+auditDetail.boardPly.boardTolerance+"</th>"+
		" <th>"+auditDetail.Lay_height.require+"</th>"+
		" <th>"+auditDetail.warp_height.require+"</th>"+
		"</tr>" ; 
		for (var i = 0; i <Num; i++) { 
		data += "<tr>"; 
		data += "<td><input type='text' oninput='calculation("+i+")' class='input' id = 'boardNum"+ i +"' name='boardNum'  ></td>"; 
		data += "<td colspan='2'><input type='number' oninput='calculation("+i+")' class='inputborder' id = 'boardLong"+ i +"' name='boardLong'></td>"; 
		data += "<td colspan='2'><input type='number' oninput='calculation("+i+")' class='inputborder' id = 'boardWide"+ i +"' name='boardWide'></td>"; 
		data += "<td colspan='2'><input type='number' oninput='calculation("+i+")' class='inputborder' id = 'boardPly"+ i +"'  name='boardPly' ></td>"; 
		data += "<td><input type='text' id = 'layHeight"+ i +"' name='layHeight' value=''></td>"; 
		data += "<td><input type='text' id = 'WarpHeight"+ i +"' name='WarpHeight' value=''></td>"; 
		data += "<td><select id = 'judge"+ i +"' name='judge'><option>合格</option><option>不合格</option></select></td>"; 
		data +=	"<td style='text-align:center'><img src='../script/images/delete.png' class='red_img'    id='lay_remove"+i+"'  onclick='return remove("+i+")'></td>"
		data += "</tr>"; 
		} 
		data += "</table>"; 
		document.getElementById("table1").innerHTML = data; 
		$("#save_judge").val(0)
	}else{//二次编辑
		var length =sizeAndWarpDegrees.length;
		var data = "";
		data += " <table id='tablerecord' class='guding-table' ellspacing='0' cellpadding='0' >"; 
		data += "	<tr>" +
		  "	<th colspan='1'>NO：</th>" +
		  "	<th colspan='2'>"+auditDetail.checkHead.serialNumber+"</th>" +
		  "	<th colspan='2'>版次：</th>" +
		  " <th colspan='2' >"+auditDetail.checkHead.revision+"</th>" +
		  "	<th colspan='2'>编号：</th>" +
		  " <th colspan='2' >"+auditDetail.checkHead.document_number+"</th>" +
		  " </tr>" +
		  " <tr>" +
		  "	<th colspan='1' >工单号</th>" +
		  "	<th colspan='4'>"+auditDetail.baseOrderInfo.orderNum+"</th>" +
		  "	<th colspan='2'>验收标准</th>" +
		  "	<th colspan='4'>"+auditDetail.checkHead.checkStandard+"</th>" +
		  " </tr>" +
		  " <tr>" +
		  "	<th colspan='1'>来料数量块</th>" +
		  "	<th colspan='4'>"+auditDetail.baseOrderInfo.amountCheckoutPcs+"</th>" +
		  "	<th colspan='2'>抽检数量块</th>" +
		  "	<th colspan='4'>"+auditDetail.checkHead.spotCheckNum+"</th>" +
		  " </tr>" +
		  " <tr>" +
		  " <th colspan='1'>顾客单位</th>" +
		  " <th colspan='4'>"+auditDetail.baseOrderInfo.guestName+"</th>" +
		  " <th colspan='2'>板名</th>" +
		  " <th colspan='4'>"+auditDetail.baseOrderInfo.boardName+"</th>" +
		  " </tr>" +
		" <tr>" + 
		" <th style='width:10%'  rowspan='3'>板号</th>" + 
		" <th colspan='2' style='width:20%'>板长</th>" + 
		" <th colspan='2' style='width:20%'>板宽</th>" + 
		" <th colspan='2' style='width:20%'>板厚</th>"+
		" <th rowspan='2' style='width:8%'>弓曲高度</th>"+
		" <th rowspan='2' style='width:8%'>扭曲高度</th>"+
		" <th rowspan='3' style='width:9%'>判定</th>"+
		" <th rowspan='3' style='width:5%;'>操作</th>"+
		"</tr>" +
		" <tr>" + 
		" <th>板长</th>" + 
		" <th>公差</th>" + 
		" <th>板宽</th>"+
		" <th>公差</th>"+
		" <th>板厚</th>"+
		" <th>公差</th>"+
		"</tr>" +
		" <tr>" + 
		" <th>"+auditDetail.boardLong.require+"</th>" + 
		" <th>"+auditDetail.boardLong.boardTolerance+"</th>" + 
		" <th>"+auditDetail.boardWide.require+"</th>"+
		" <th>"+auditDetail.boardWide.boardTolerance+"</th>"+
		" <th>"+auditDetail.boardPly.require+"</th>"+
		" <th>"+auditDetail.boardPly.boardTolerance+"</th>"+
		" <th>"+auditDetail.Lay_height.require+"</th>"+
		" <th>"+auditDetail.warp_height.require+"</th>"+
		"</tr>" ; 
		for (var i = 0; i <length; i++) { 
		data += "<tr>"; 
		data += "<td><input type='text' oninput='calculation("+i+")'  class='input' id = 'boardNum"+ i +"' name='boardNum' value='" + sizeAndWarpDegrees[i].boardNum+"'></td>"; 
		data += "<td colspan='2'><input type='number' oninput='calculation("+i+")'  class='inputborder' id = 'boardLong"+ i +"' name='boardLong'  value='" + sizeAndWarpDegrees[i].boardLong+"'></td>"; 
		data += "<td colspan='2'><input type='number' oninput='calculation("+i+")'   class='inputborder' id = 'boardWide"+ i +"' name='boardWide'  value='" + sizeAndWarpDegrees[i].boardWide+"'></td>"; 
		data += "<td colspan='2'><input type='number' oninput='calculation("+i+")'  class='inputborder' id = 'boardPly"+ i +"'  name='boardPly'   value='" + sizeAndWarpDegrees[i].boardPly+"'></td>"; 
		data += "<td><input type='text' id = 'layHeight"+ i +"' name='layHeight'  value='" + sizeAndWarpDegrees[i].layHeight+"'></td>"; 
		data += "<td><input type='text' id = 'WarpHeight"+ i +"' name='WarpHeight'   value='" + sizeAndWarpDegrees[i].warpHeight+"'></td>"; 
		data += "<td><select id = 'judge"+ i +"' name='judge'><option>合格</option><option>不合格</option></select></td>"; 
		data +=	"<td style='text-align:center'><img src='../script/images/delete.png'  id='lay_remove"+i+"'   class='red_img'   onclick='return remove("+i+")'></td>";
		data += "</tr>"; 
		} 
		data += "</table>"; 
		document.getElementById("table1").innerHTML = data;
		for (var i = 0; i <length; i++) { 
			$("#judge"+i).val(sizeAndWarpDegrees[i].judge);
	    }
		$("#save_judge").val(1);
	}

}


//增加翘曲度
function btn_add(){
	var length=$('#tablerecord tr').length-7;
	var max_num=$("#Num").html()
	if(max_num<=length){
		$.messager.alert('提示', '不能大于来料数量', 'info');
	}else{
		var data="";
		var i=$("#tablerecord").find("tr").length-7;
		data += "<tr>"; 
		data += "<td><input type='text' oninput='calculation("+i+")'  class='input' id = 'boardNum"+ i +"' name='boardNum' ></td>"; 
		
		data += "<td colspan='2'><input type='number' oninput='calculation("+i+")'  class='inputborder' id = 'boardLong"+ i +"' name='boardLong' ></td>"; 
		
		data += "<td colspan='2'><input type='number' oninput='calculation("+i+")'   class='inputborder' id = 'boardWide"+ i +"' name='boardWide'  ></td>"; 
		
		
		data += "<td colspan='2'><input type='number' oninput='calculation("+i+")'  class='inputborder' id = 'boardPly"+ i +"'  name='boardPly'   ></td>"; 
		
		data += "<td><input type='text' id = 'layHeight"+ i +"' name='layHeight' value=''></td>"; 
		data += "<td><input type='text' id = 'WarpHeight"+ i +"' name='WarpHeight' value=''></td>"; 
		
		data += "<td><select id = 'judge"+ i +"' name='judge'><option>合格</option><option>不合格</option></select></td>"; 
		data +=	"<td style='text-align:center'><img src='../script/images/delete.png'   class='red_img'   id='lay_remove"+i+"'  onclick='return remove("+i+")'></td>";
		data += "</tr>"; 
		$("#tablerecord").append(data)
	}

}
//删除
function remove(i){
	var length=$('#tablerecord tr').length-7;
	var remove_num=parseInt(document.getElementById("min_Num").innerHTML)
	if(length>remove_num){
		var selement = 'lay_remove' + i;  
		var scurrentTr = $('#'+selement).parent().parent();  
		scurrentTr.remove();  
		$('#tablerecord tr').each(function(index){   
			if($(this).has('input')){
			    $(this).find("input[name='boardNum']").attr("id","boardNum"+(index-7));
			    $(this).find("input[name='boardNum']").attr("oninput","calculation("+(index-7)+")");
			    
			    $(this).find("input[name='boardLong']").attr("id","boardLong"+(index-7));
			    $(this).find("input[name='boardLong']").attr("oninput","calculation("+(index-7)+")");
			    
			    $(this).find("input[name='boardWide']").attr("id","boardWide"+(index-7));
			    $(this).find("input[name='boardWide']").attr("oninput","calculation("+(index-7)+")");
			   
			    $(this).find("input[name='boardPly']").attr("id","boardPly"+(index-7));
			    $(this).find("input[name='boardPly']").attr("oninput","calculation("+(index-7)+")");
			    
			    $(this).find("input[name='layHeight']").attr("id","layHeight"+(index-7));
			    $(this).find("input[name='WarpHeight']").attr("id","WarpHeight"+(index-7));
			    
			    $(this).find("select").attr("id","judge"+(index-7));
			}

		 });  
	}else{
		$.messager.alert('提示', '至少填写'+remove_num+'条信息', 'info');
	}

}





/*手动判断合格*/
function handle(judge,badnum){
	var OIndex=document.getElementById(judge).selectedIndex;
	var num=document.getElementById(badnum);
	if(OIndex == 1 ){
		 document.getElementById(judge).options[1].selected = true;
		 num.style.display="block";
		 num.setAttribute("class","badnum");
	}else{
		 //document.getElementById(judge).options[0].selected = true;
		 num.value="";
		 num.style.display="none";
		 num.removeAttribute("class","badnum");
	}
}


/*导出*/
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

//批次号=logo
function change_logo_result(){
	var batch_number_require=$("#logo_result").val()
	$("#batch_number_require").html(batch_number_require)
}

//抽检数监听
function btn_changeNum(){
	var status=false;
	if(!/^[+]{0,1}(\d+)$/.test($("#detail_check").val())){
		$.messager.alert('提示', '请填写正整数', 'info');
		$("#detail_check").val("");
		return;
	}
	if(parseInt($("#detail_check").val())<=parseInt($("#detail_CheckoutPcs").html())  &&  parseInt($("#min_Num").html())<=parseInt($("#detail_check").val())){//小于等于来料数量并且大于等于最小抽检数
		//var list = $('#tablerecord input');
		var i=parseInt($("#tablerecord").find("tr").length)-parseInt(7);
		var size=parseInt($("#detail_check").val())-parseInt(i)
		if(size>parseInt(0)){//比原来的大
			for(j=0;j<size;j++){
				var data="";
				data += "<tr>"; 
				data += "<td><input type='text' oninput='calculation("+(i+j)+")'  class='input' id = 'boardNum"+ (i+j) +"' name='boardNum' ></td>"; 
				
				data += "<td colspan='2'><input type='number' oninput='calculation("+(i+j)+")'  class='inputborder' id = 'boardLong"+ (i+j) +"' name='boardLong' ></td>"; 
				
				data += "<td colspan='2'><input type='number' oninput='calculation("+(i+j)+")'   class='inputborder' id = 'boardWide"+ (i+j) +"' name='boardWide'  ></td>"; 
				
				
				data += "<td colspan='2'><input type='number' oninput='calculation("+(i+j)+")'  class='inputborder' id = 'boardPly"+ (i+j) +"'  name='boardPly'   ></td>"; 
				
				data += "<td><input type='text' id = 'layHeight"+ (i+j) +"' name='layHeight' value=''></td>"; 
				data += "<td><input type='text' id = 'WarpHeight"+ (i+j) +"' name='WarpHeight' value=''></td>"; 
				
				data += "<td><select id = 'judge"+ (i+j) +"' name='judge'><option>合格</option><option>不合格</option></select></td>"; 
				data +=	"<td style='text-align:center'><img src='../script/images/delete.png'   class='red_img'   id='lay_remove"+(i+j)+"'  onclick='return remove("+(i+j)+")'></td>";
				data += "</tr>"; 
				$("#tablerecord").append(data)
			}
		}else if(size==parseInt(0)){//相等
			
		}else{//比原来的小
			for(var j=0;j<(-size);j++){
				var index=parseInt(6)+parseInt(i)-parseInt(j);
				$('#tablerecord').find('tr').eq(index).remove();
			}
		}
	}else{
		$.messager.alert('提示', '不能大于来料数量并且小于等于最小抽检数'+$("#min_Num").html()+'', 'info');
		$("#detail_check").val("");
	}
}
