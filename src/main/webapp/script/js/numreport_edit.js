var allReportContent;
$(function () {

	var url=window.location.href;
	var loc = url.substring(url.lastIndexOf('=')+1, url.length);
	var app = angular.module('testApp', []);
	app.controller('Ctrl', function ($scope, $http) {    	
	    $http.get("/pcbmis/roleAuth/getUserDetail" ).success(function (response) {
	    	$scope.systemsUrl = response.systemsUrl;
	    	$scope.menu = response.menu;
	    	$scope.roles = response.roles;
	    	$scope.userName = response.userName;
	    	$scope.secondMenu = response.menu.secondMenu;
	    })
	})
	
	app.controller('aaCtrl', function ($scope, $http) {    	
	    $http.get("/pcbmis/roleAuth/getUserDetail" ).success(function (response) {
	    	$scope.systemsUrl = response.systemsUrl;
	    	$scope.menu = response.menu;
	    	$scope.roles = response.roles;
	    	$scope.userName = response.userName;
	    	$scope.secondMenu = response.menu.secondMenu;
	    })
	})
	
	app.controller('bbCtrl', function ($scope, $http) {    	
	    $http.get("/pcbmis/roleAuth/getUserDetail" ).success(function (response) {
	    	$scope.systemsUrl = response.systemsUrl;
	    	$scope.menu = response.menu;
	    	$scope.roles = response.roles;
	    	$scope.userName = response.userName;
	    	$scope.secondMenu = response.menu.secondMenu;
	    })
	})
	
	app.filter('trustHtml', function ($sce) {
	 		return function (input) {
	    		return $sce.trustAsHtml(input);
	 		}
		});
	app.controller('testCtrl', function ($scope, $http) {    
		$scope.delete_certification=function (index) {				
			delete_certification(index);
			console.log(index);
       };
       	$http.get("/pcbmis/util/template").success(function (response) {
   			$scope.data = response;
	    })
	   
//	    $scope.change_templateId = function () {  
//			console.info($scope.selected);  
//			$http.post("/pcbmis/order/reportDetail?reportNum=" + loc+"&templateId="+$scope.selected).success(function (response) {
//				$scope.reportDetail = response.reportDetail;
//			})
//		}  
//       
		$http.post("/pcbmis/order/reportDetail?reportNum=" + loc).success(function (response) {
			allReportContent = response.reportDetail.report_content;
        	$scope.reportDetail = response.reportDetail;
        	$scope.selected=response.reportDetail.report_intro.templateId; 
        	var base_material_typescheckResult=response.reportDetail.report_content.base_material_types.checkResult;
        	var ctae=response.reportDetail.baseReportInfo.categoryGrade;
        	$("#Num").val(response.reportDetail.baseReportInfo.spotCheckNumPcs);
        	var amountCheckoutPcs_num=response.reportDetail.baseReportInfo.amountCheckoutPcs;
        	$("#amountCheckoutPcs").val(amountCheckoutPcs_num+"PCS")
        	var num = response.reportDetail.baseReportInfo.spotCheckNumPcs;
			var apertureCheck = response.reportDetail.aperture_check;
			var differential_characteristic_impedance = response.reportDetail.differential_characteristic_impedance;
			var dlength=differential_characteristic_impedance.length;
			var single_ended_impedances = response.reportDetail.single_ended_impedances;
			var special_dimensions=response.reportDetail.special_dimensions
			var slength=single_ended_impedances.length;
			test(num,apertureCheck,differential_characteristic_impedance,single_ended_impedances,special_dimensions);
			
        	if(ctae.indexOf("民")!=-1){
        		$("#min").show();
        		$("#jun").hide();
        	}else{
        		$("#min").hide();
        		$("#jun").show();
        	}
        	
        	if(response.reportDetail.report_intro.dispatchDate == ''){
           	 	$('#dispatchDate').datebox('setValue', getCurentDateStr());
        	}else{
        		$('#dispatchDate').datebox('setValue', response.reportDetail.report_intro.dispatchDate);
        	}
        	 if(base_material_typescheckResult==""){
        		 if(ctae.indexOf("民")!=-1){
        			 $("#ring_width").html('<input  class="other" id="min_annular_ring-checkRequire"  type="text"  value="不破环">')
             	}else{
             		$("#ring_width").html('<input  class="other" id="min_annular_ring-checkRequire"  type="text"  value="≥0.05mm">')
             	}
        		 $("#base_material_types-checkResult").val(response.reportDetail.report_content.base_material_types.checkRequire);
        		 $("#prevent_smt_type-checkResult").val(response.reportDetail.report_content.prevent_smt_type.checkRequire);
        		 $("#surface_process-checkResult").val(response.reportDetail.report_content.surface_process.checkRequire);
        		 $("#regular_label-checkResult").val(response.reportDetail.report_content.regular_label.checkRequire);
        		 $("#expirationDate").combobox('setValue', "");
        	 }else{
	             /*	 修改时获取数据*/
        		 $("#unit").val(response.reportDetail.report_intro.unit)
        		 $("#ring_width").html('<input  class="other" id="min_annular_ring-checkRequire"  type="text">')
        		 $("#min_annular_ring-checkRequire").val(response.reportDetail.report_content.min_annular_ring.checkRequire)
	        	 $("#base_material_types-judgeResult").val(response.reportDetail.report_content.base_material_types.judgeResult);
	        	 $("#base_material_appearance-judgeResult").val(response.reportDetail.report_content.base_material_appearance.judgeResult);
	        	 $("#conductive_pattern-judgeResult").val(response.reportDetail.report_content.conductive_pattern.judgeResult);
	        	 $("#prevent_smt_type-judgeResult").val(response.reportDetail.report_content.prevent_smt_type.judgeResult);
	        	 $("#prevent_smt_appearance-judgeResult").val(response.reportDetail.report_content.prevent_smt_appearance.judgeResult);
	        	 $("#character_type-judgeResult").val(response.reportDetail.report_content.character_type.judgeResult);
	        	 $("#character_appearance-judgeResult ").val(response.reportDetail.report_content.character_appearance.judgeResult);
	        	 $("#surface_process-judgeResult").val(response.reportDetail.report_content.surface_process.judgeResult);
	        	 $("#regular_label-judgeResult").val(response.reportDetail.report_content.regular_label.judgeResult);
	        	 $("#produce_period-judgeResult").val(response.reportDetail.report_content.produce_period.judgeResult);
	        	 $("#external_coating_adhesion-judgeResult").val(response.reportDetail.report_content.external_coating_adhesion.judgeResult);
	        	 $("#prevent_smt_character_adhesion-judgeResult").val(response.reportDetail.report_content.prevent_smt_character_adhesion.judgeResult);
	        	 $("#min_line_width-judgeResult ").val(response.reportDetail.report_content.min_line_width.judgeResult);
	        	 $("#min_line_distance-judgeResult").val(response.reportDetail.report_content.min_line_distance.judgeResult);
	        	 $("#min_annular_ring-judgeResult").val(response.reportDetail.report_content.min_annular_ring.judgeResult);
	        	 $("#board_ply-judgeResult").val(response.reportDetail.report_content.board_ply.judgeResult);
	        	 $("#warping_degree-judgeResult").val(response.reportDetail.report_content.warping_degree.judgeResult);
	        	 $("#board_long-judgeResult ").val(response.reportDetail.report_content.board_long.judgeResult);
	        	 $("#board_wide-judgeResult ").val(response.reportDetail.report_content.board_wide.judgeResult);
	        	 $("#aperture-judgeResul").val(response.reportDetail.report_content.aperture.judgeResult);
	        	 $("#v_cut-judgeResult").val(response.reportDetail.report_content.v_cut.judgeResult);
	        	 $("#circuit_connectivity-judgeResult").val(response.reportDetail.report_content.circuit_connectivity.judgeResult);
	        	 $("#circuit_insulativity-judgeResult").val(response.reportDetail.report_content.circuit_insulativity.judgeResult);
	        	 $("#special_impedance-judgeResult").val(response.reportDetail.report_content.special_impedance.judgeResult);
	        	 $("#solderability-judgeResult").val(response.reportDetail.report_content.solderability.judgeResult);
	        	 $("#result").val(response.reportDetail.report_integrity.result);
	        	 $("#packagingDate").datebox('setValue',''+response.reportDetail.certification.packagingDate+'');
	        	 $("#expirationDate").combobox('setValue',"");
	        	 $("#expirationDate").combobox('setText', response.reportDetail.certification.expirationDate);
        	 }
        	 
        });
    });

 	app.controller('ddCtrl', function ($scope, $http) {    	
        $http.post("/pcbmis/order/reportDetail?reportNum=" + loc).success(function (response) {
        	allReportContent = response.reportDetail.report_content;
        	$scope.reportDetail = response.reportDetail;
        });
    });
 	
 	
	$(".combo").click(function(event){
		if(event.target.tagName == "SPAN"){
			return false;
		} 
		if ($(this).prev().combobox("panel").is(":visible")) {
			$(this).prev().combobox("hidePanel");
		} else {
			$(this).prev().combobox("showPanel");
		}
	})
})



var submitFlag=0;
function writetijiao(){
	if (submitFlag==1) {
		return;
	}
	submitFlag=1;
	var flag = true;
	/*不为空校验*/
	$(".other").each(function (){
		 $(this).removeClass("red") ;
		  if($(this).val()=="" ){
			  $(this).addClass("red")
			  flag = false;
		  };
	})
	
	if($('[name="packagingDate"]').val()==""){
		$.messager.alert("操作提示", "请填写包装日期！","info");  
		submitFlag=0; 
		return;
	}
	
	if($("#expirationDate").combobox('getText')==""){
		$.messager.alert("操作提示", "请填写保质期！","info");  
		submitFlag=0; 
		return;
	}
	
	if(flag){
		var url=window.location.href;
		var loc = url.substring(url.lastIndexOf('=')+1, url.length);
	
		var jsonstr = "";
		
		var reportIntro={}
		reportIntro.number=$("#number").val();
		reportIntro.unit=$("#unit").val();
		reportIntro.newDateKey=$("#newDateKey").val();
		reportIntro.newDateValue=$("#newDateValue").val();
		reportIntro.productPeriod=$("#productPeriod").val();
//		reportIntro.templateId=$("#acceptanceStandard").val();
		reportIntro.dispatchDate=$("#dispatchDate").datebox('getValue');
		
		var base_material_types={}
		base_material_types.checkRequire=$("#base_material_types-checkRequire").val();
		base_material_types.checkResult=$("#base_material_types-checkResult").val();
		base_material_types.judgeResult=$("#base_material_types-judgeResult option:selected").text();
		
		var base_material_appearance={}
		base_material_appearance.checkRequire = allReportContent.base_material_appearance.checkRequire;
		base_material_appearance.checkResult=$("#base_material_appearance-checkResult").val();
		base_material_appearance.judgeResult=$("#base_material_appearance-judgeResult option:selected").text();
		
		var conductive_pattern={};
		conductive_pattern.checkRequire = allReportContent.conductive_pattern.checkRequire;
		conductive_pattern.checkResult=$("#conductive_pattern-checkResult").val();
		conductive_pattern.judgeResult=$("#conductive_pattern-judgeResult option:selected").text();
	
		var prevent_smt_type={}
		prevent_smt_type.checkRequire=$("#prevent_smt_type-checkRequire").val();
		prevent_smt_type.checkResult=$("#prevent_smt_type-checkResult").val();
		prevent_smt_type.judgeResult=$("#prevent_smt_type-judgeResult option:selected").text();
		
		var prevent_smt_appearance={}
		prevent_smt_appearance.checkRequire = allReportContent.prevent_smt_appearance.checkRequire;
		prevent_smt_appearance.checkResult=$("#prevent_smt_appearance-checkResult").val();
		prevent_smt_appearance.judgeResult=$("#prevent_smt_appearance-judgeResult option:selected").text();
		
		var character_type={}
		character_type.checkRequire=$("#character_type-checkRequire").val();
		character_type.checkResult=$("#character_type-checkResult").val();
		character_type.judgeResult=$("#character_type-judgeResult option:selected").text();
		
		var character_appearance={}
		character_appearance.checkRequire = allReportContent.character_appearance.checkRequire;
		character_appearance.checkResult=$("#character_appearance-checkResult").val();
		character_appearance.judgeResult=$("#character_appearance-judgeResult option:selected").text();
		
		var surface_process={}
		surface_process.checkResult=$("#surface_process-checkResult").val();
		surface_process.judgeResult=$("#surface_process-judgeResult option:selected").text();
		
		var regular_label={}
		regular_label.checkRequire=$("#regular_label-checkRequire").val();
		regular_label.checkResult=$("#regular_label-checkResult").val();
		regular_label.judgeResult=$("#regular_label-judgeResult option:selected").text();
		
		var produce_period={}
		produce_period.checkRequire=$("#produce_period-checkRequire").val();
		produce_period.checkResult=$("#produce_period-checkResult").val();
		produce_period.judgeResult=$("#produce_period-judgeResult option:selected").text();
		
		var external_coating_adhesion={}
		external_coating_adhesion.checkRequire = allReportContent.external_coating_adhesion.checkRequire;
		external_coating_adhesion.checkResult=$("#external_coating_adhesion-checkResult").val();
		external_coating_adhesion.judgeResult=$("#external_coating_adhesion-judgeResult option:selected").text();
		
		var prevent_smt_character_adhesion={}
		prevent_smt_character_adhesion.checkRequire = allReportContent.prevent_smt_character_adhesion.checkRequire;
		prevent_smt_character_adhesion.checkResult=$("#prevent_smt_character_adhesion-checkResult").val();
		prevent_smt_character_adhesion.judgeResult=$("#prevent_smt_character_adhesion-judgeResult option:selected").text();
		
		var min_line_width={}
		min_line_width.checkRequire=$("#min_line_width-checkRequire").val();
		min_line_width.checkResult=$("#min_line_width-checkResult").val();
		min_line_width.judgeResult=$("#min_line_width-judgeResult option:selected").text();
		
		var min_line_distance={}
		min_line_distance.checkRequire=$("#min_line_distance-checkRequire").val();
		min_line_distance.checkResult=$("#min_line_distance-checkResult").val();
		min_line_distance.judgeResult=$("#min_line_distance-judgeResult option:selected").text();
		
		var min_annular_ring={}
		min_annular_ring.checkRequire=$("#min_annular_ring-checkRequire").val();
		min_annular_ring.checkResult=$("#min_annular_ring-checkResult").val();
		min_annular_ring.judgeResult=$("#min_annular_ring-judgeResult option:selected").text();
		
		var board_ply={}
		board_ply.checkRequire = allReportContent.board_ply.checkRequire;
		board_ply.boardTolerance=$("#board_ply-boardTolerance").val();
		board_ply.checkResult=$("#board_ply-checkResult").val();
		board_ply.judgeResult=$("#board_ply-judgeResult option:selected").text();
		
		
		var warping_degree={}
		warping_degree.checkRequire = allReportContent.warping_degree.checkRequire;
		warping_degree.checkResult=$("#warping_degree-checkResult").val();
		warping_degree.judgeResult=$("#warping_degree-judgeResult option:selected").text();
		
		var board_long={}
		board_long.checkRequire = allReportContent.board_long.checkRequire;
		board_long.boardTolerance=$("#board_long-boardTolerance").val();
		board_long.checkResult=$("#board_long-checkResult").val();
		board_long.judgeResult=$("#board_long-judgeResult option:selected").text();
		
		var board_wide={}
		board_wide.checkRequire = allReportContent.board_wide.checkRequire;
		board_wide.boardTolerance=$("#board_wide-boardTolerance").val();
		board_wide.checkResult=$("#board_wide-checkResult").val();
		board_wide.judgeResult=$("#board_wide-judgeResult option:selected").text();
		

		
		
		var aperture={}
		aperture.checkRequire = allReportContent.aperture.checkRequire;
		aperture.judgeResult=$("#aperture-judgeResult option:selected").text();
		
		var v_cut={}
		v_cut.checkRequire = allReportContent.v_cut.checkRequire;
		v_cut.judgeResult=$("#v_cut-judgeResult option:selected").text();
		
		var circuit_connectivity={}
		circuit_connectivity.checkRequire = allReportContent.circuit_connectivity.checkRequire;
		circuit_connectivity.judgeResult=$("#circuit_connectivity-judgeResult option:selected").text();

		var circuit_insulativity={}
		circuit_insulativity.checkRequire = allReportContent.circuit_insulativity.checkRequire;
		circuit_insulativity.judgeResult=$("#circuit_insulativity-judgeResult option:selected").text();
		
		var special_impedance={}
		special_impedance.checkRequire = allReportContent.special_impedance.checkRequire;
		special_impedance.judgeResult=$("#special_impedance-judgeResult option:selected").text();
		
		var solderability={}
		solderability.checkRequire = allReportContent.solderability.checkRequire;
		solderability.checkResult=$("#solderability-checkResult").val();
		solderability.judgeResult=$("#solderability-judgeResult option:selected").text();
		
		var microsectioning={}
		microsectioning.checkRequire=$("#microsectioning-checkRequire").val();
		microsectioning.judgeResult=$("#microsectioning-judgeResult option:selected").text();
		
		/*合格证*/
		var reportCertification={}
		var reportCertification_add=[]
		for (var i =0; i <$("#add_certification_table tr").length-11; i++) {
			reportCertification_add[i]={}	
			reportCertification_add[i].key=$("#add_certification_th"+i+"").val();
			reportCertification_add[i].value=$("#add_certification_td"+i+"").val();
		}
		reportCertification.packagingDate=$("#packagingDate").datebox('getValue');
		reportCertification.expirationDate=$("#expirationDate").combobox('getText');
		reportCertification.orderNumName=$("#listnum").val();
		reportCertification.numberPcs=$("#numberPcs").val();
		reportCertification.batchNumber=$("#batchNumber").val();
		reportCertification.add=reportCertification_add;

		
		
		/*完善性报告*/
		var reportIntegrity={}
		reportIntegrity.checkInstrument=$("#checkInstrument").val();
		reportIntegrity.checkVoltage=$("#checkVoltage").val();
		reportIntegrity.netNum=$("#netNum").val();
		reportIntegrity.checkPointNum=$("#checkPointNum").val();
		reportIntegrity.connectedResistance=$("#connectedResistance").val();
		reportIntegrity.insulatedResistance=$("#insulatedResistance").val();
		reportIntegrity.connectedNetNum=$("#connectedNetNum").val();
		reportIntegrity.result=$("#result").val();
		
		
		
		/*孔径*/
		var reportApertureChecks=[]
		for (var i =0; i <$("#check tr").length-1; i++) {
			reportApertureChecks[i]={}	
	//		reportApertureChecks[i].ID=$("#ID"+i+"").val();
			reportApertureChecks[i].pN=$("#pN"+i+"").val();
			reportApertureChecks[i].require=$("#require"+i+"").val();
			reportApertureChecks[i].requireTolerance=$("#pN_require"+i+"").val();
			reportApertureChecks[i].realCheck=$("#realCheck"+i+"").val();
			reportApertureChecks[i].judge=$("#judge"+i+"").find("option:selected").text();
		}
		/*阻抗*/
		/*2差分阻抗*/
		var differential_characteristic_impedance=[]
		for (var j =0; j <$("#mm tr").length-1; j++) {
			differential_characteristic_impedance[j]={}		
			differential_characteristic_impedance[j].layer=$("#layerNumL"+j+"").val()
			differential_characteristic_impedance[j].layerNum=$("#layerNum"+j+"").val();
			differential_characteristic_impedance[j].require=$("#drequire"+j+"").val();
			differential_characteristic_impedance[j].requireTolerance=$("#drequire_ten"+j+"").val();
			differential_characteristic_impedance[j].realCheck=$("#drealCheck"+j+"").val();
			differential_characteristic_impedance[j].judge=$("#djudge"+j+"").find("option:selected").text();
		}
		
		/*1单*/
		var single_ended_impedances=[]
		for (var z =0; z <$("#tbListTM tr").length-1; z++) {
			single_ended_impedances[z]={}		
			single_ended_impedances[z].layer=$("#slayerL"+z+"").val()
			single_ended_impedances[z].layerNum=$("#slayerNum"+z+"").val();
			single_ended_impedances[z].require=$("#srequire"+z+"").val();
			single_ended_impedances[z].requireTolerance=$("#srequire_ten"+z+"").val();
			single_ended_impedances[z].realCheck=$("#srealCheck"+z+"").val();
			single_ended_impedances[z].judge=$("#sjudge"+z+"").find("option:selected").text();
		}
		
		
		
		var vCutResidualThickness={}// v_cut残厚
		vCutResidualThickness.itemName="";
		vCutResidualThickness.require=$("#vCutResidualThickness_require").val();
		vCutResidualThickness.unit=$("#vCutResidualThickness_unit").val();
		vCutResidualThickness.realCheck=$("#vCutResidualThickness_realCheck").val();
		vCutResidualThickness.judge=$("#vCutResidualThickness_judge").val();
		
		var vCutDeviation={}// v_cut偏差
		vCutDeviation.itemName="";
		vCutDeviation.require=$("#vCutDeviation_require").val();
		vCutDeviation.unit=$("#vCutDeviation_unit").val();
		vCutDeviation.realCheck=$("#vCutDeviation_realCheck").val();
		vCutDeviation.judge=$("#vCutDeviation_judge").val();
		
		var goldfingerBevelDepth={}// 金手指倒角深度
		goldfingerBevelDepth.itemName="";
		goldfingerBevelDepth.require=$("#goldfingerBevelDepth_require").val();
		goldfingerBevelDepth.unit=$("#goldfingerBevelDepth_unit").val();
		goldfingerBevelDepth.realCheck=$("#goldfingerBevelDepth_realCheck").val();
		goldfingerBevelDepth.judge=$("#goldfingerBevelDepth_judge").val();
		
		var goldfingerResidualThickness={}//金手指残厚
		goldfingerResidualThickness.itemName="";
		goldfingerResidualThickness.require=$("#goldfingerResidualThickness_require").val();
		goldfingerResidualThickness.unit=$("#goldfingerResidualThickness_unit").val();
		goldfingerResidualThickness.realCheck=$("#goldfingerResidualThickness_realCheck").val();
		goldfingerResidualThickness.judge=$("#goldfingerResidualThickness_judge").val();
		
		var stepWidth={}//台阶
		stepWidth.itemName="";
		stepWidth.require=$("#stepWidth_require").val();
		stepWidth.unit=$("#stepWidth_unit").val();
		stepWidth.realCheck=$("#stepWidth_realCheck").val();
		stepWidth.judge=$("#stepWidth_judge").val();
		
		var stepResidualThickness={}//台阶残厚
		stepResidualThickness.itemName="";
		stepResidualThickness.require=$("#stepResidualThickness_require").val();
		stepResidualThickness.unit=$("#stepResidualThickness_unit").val();
		stepResidualThickness.realCheck=$("#stepResidualThickness_realCheck").val();
		stepResidualThickness.judge=$("#stepResidualThickness_judge").val();
		
		var steppedHoleAperture={}//台阶孔孔径
		steppedHoleAperture.itemName="";
		steppedHoleAperture.require=$("#steppedHoleAperture_require").val();
		steppedHoleAperture.unit=$("#steppedHoleAperture_unit").val();
		steppedHoleAperture.realCheck=$("#steppedHoleAperture_realCheck").val();
		steppedHoleAperture.judge=$("#steppedHoleAperture_judge").val();
		
		var steppedHoleHoleDepth={}//台阶孔孔径
		steppedHoleHoleDepth.itemName="";
		steppedHoleHoleDepth.require=$("#steppedHoleHoleDepth_require").val();
		steppedHoleHoleDepth.unit=$("#steppedHoleHoleDepth_unit").val();
		steppedHoleHoleDepth.realCheck=$("#steppedHoleHoleDepth_realCheck").val();
		steppedHoleHoleDepth.judge=$("#steppedHoleHoleDepth_judge").val();
		
		var rests=[];
		//alert($("#add_special tr").length)
		for(var i=0;i<$("#add_special tr").length;i++){
			rests[i]={}
			rests[i].itemName=$("#itemName"+i+"").val();
			rests[i].require=$("#rests_require"+i+"").val();
			rests[i].unit=$("#rests_unit"+i+"").val();
			rests[i].realCheck=$("#rests_realCheck"+i+"").val();
			rests[i].judge=$("#rests_judge"+i+"").val();
		}
		
		var reportSpecialDimension={
			vCutResidualThickness:vCutResidualThickness,			
			vCutDeviation:vCutDeviation,					
			goldfingerBevelDepth:goldfingerBevelDepth,				
			goldfingerResidualThickness:goldfingerResidualThickness,			
			stepWidth:stepWidth,						
			stepResidualThickness:stepResidualThickness,				
			steppedHoleAperture:steppedHoleAperture,					
			steppedHoleHoleDepth:steppedHoleHoleDepth,	
			rests:rests
		}
		
		//v-cut报告
		var reportVcut={}
		reportVcut.requireAngle=$("#requireAngle").val();				// &要求   										
		reportVcut.requireH=$("#requireH").val();						// h要求
		reportVcut.requireB=$("#requireB").val();						// b要求
		reportVcut.requireBtolerance=$("#requireBtolerance").val();		// b要求公差
		reportVcut.realCheakAngle=$("#realCheakAngle").val();		    // &实测
		reportVcut.realCheckH=$("#realCheckH").val();					// h实测
		reportVcut.realCheckB=$("#realCheckB").val();					// b实测
		reportVcut.judgeAngle=$("#judgeAngle").val();					// &判定
		reportVcut.judgeH=$("#judgeH").val();							// h判定
		reportVcut.judgeB=$("#judgeB").val();							// b判定
		
		var note={}
		note.note=$("#note_note").html();
		note.supplierMistakeNote=$("#supplierMistakeNote").html();
		
		 jsonstr = JSON.stringify({
			reportIntro:reportIntro,
			base_material_types:base_material_types,
			base_material_appearance:base_material_appearance,
			conductive_pattern:conductive_pattern,
			prevent_smt_type:prevent_smt_type,
			prevent_smt_appearance:prevent_smt_appearance,
			character_type:character_type,
			character_appearance:character_appearance,
			surface_process:surface_process,
			regular_label:regular_label,
			produce_period:produce_period,
			external_coating_adhesion:external_coating_adhesion,
			prevent_smt_character_adhesion:prevent_smt_character_adhesion,
			min_line_width:min_line_width,
			min_line_distance:min_line_distance,
			min_annular_ring:min_annular_ring,
			board_ply:board_ply,
			warping_degree:warping_degree,
			board_long:board_long,
			board_wide:board_wide,
			aperture:aperture,
			v_cut:v_cut,
			circuit_connectivity:circuit_connectivity,
			circuit_insulativity:circuit_insulativity,
			special_impedance:special_impedance,
			solderability:solderability,
			microsectioning:microsectioning,
			differential_characteristic_impedance:differential_characteristic_impedance,
			single_ended_impedances:single_ended_impedances,
			reportCertification:reportCertification,
			reportIntegrity:reportIntegrity,
			reportApertureChecks:reportApertureChecks,
			reportSpecialDimension:reportSpecialDimension,
			reportVcut:reportVcut,
			note:note,
		})
		$.ajax({  
	        type: "PUT",  
	        url: "/pcbmis/modify/report?reportNum=" + loc,  
	        data:jsonstr,
	        contentType: 'application/json', 
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
	    return;    
	 }else{
		 submitFlag=0; 
		 $.messager.alert("操作提示", "信息填写不完整或者填写不规范！","info");  
	 }
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

/*新增一行*/
function  add(){
	var tr = "<tr><th><input class='other' type='text' id='newDateKey' ng-value='reportDetail.report_intro.newDateKey'></th><td><input  class='other' type='text' id='newDateValue' ng-value='reportDetail.report_intro.newDateValue'></td></tr>";
	$("#report tr:eq(2)").after(tr);
	$("#add-report").css('display','none');
	$("#delete-report").css('display','inline-block');
}
function delet(){
	$("#report tr:eq(3)").remove();
	$("#delete-report").css('display','none');
	$("#add-report").css('display','inline-block');
}

/*差分阻抗*/
function addlist(){
	var id= $("#mm").find("tr").length-1 ;
	$("#mm").append("<tr><td style='width: 5%'>2</td><td style='width: 15%'>差分阻抗</td><td style='width: 10%'><input type='text' id='layerNumL"+id+"' value='L'></td><td style='width: 10%'><input name='layerNum' type='text' id='layerNum"+id+"'></td><td style='width: 10%'><input type='text'name='drequire'  id='drequire"+id+"'></td><td style='width: 10%'><input type='text'  id='drequire_ten"+id+"' value='±10%' ></td><td style='width: 20%'><input  type='text' name='drealCheck' id='drealCheck"+id+"'></td><td style='width: 15%'><select   id='djudge"+id+"'><option>合格</option><option>不合格</option><option>/</option></select></td><td style='width: 5%;text-align: center;'><img src='../script/images/delete.png' class='red_min'    id='dremove"+id+"'    onclick='return dremove("+id+")'></td></tr>")
}
function dremove(id){
	var mm=$("#mm tr").length;
	if($('#mm tr').length>2){
		var delement = 'dremove' + id;  
		var dcurrentTr = $('#'+delement).parent().parent();  
		dcurrentTr.remove();  
		$('#mm tr').each(function(index){   
		    $(this).find("input[name='layerNum']").attr("id","layerNum"+(index-1));
		    $(this).find("input[name='drequire']").attr("id","drequire"+(index-1));
		    $(this).find("input[name='drealCheck']").attr("id","drealCheck"+(index-1));
		    $(this).find("select").attr("id","djudge"+(index-1));
		 });  
	}else{
		$.messager.alert('提示', '至少填写一条信息', 'info');
	}

}

/*单端阻抗*/
function addlist2(){
	var d= $("#tbListTM").find("tr").length-1;
	$("#tbListTM").append("<tr><td style='width: 5%'>1</td><td style='width: 15%'>单端阻抗</td><td style='width: 10%'><input type='text' id='slayerL"+d+"' value='L'></td><td style='width: 10%'><input name='slayerNum' type='text' id='slayerNum"+d+"'></td><td style='width: 10%'><input  type='text' name='srequire' id='srequire"+d+"'></td><td style='width: 10%'><input type='text'  id='srequire_ten"+d+"' value='±10%' ></td><td style='width: 20%'><input   type='text' name='srealCheck' id='srealCheck"+d+"'></td><td style='width: 15%'><select   id='sjudge"+d+"'><option>合格</option><option>不合格</option><option>/</option></select></td><td style='width: 5%;text-align: center;'><img src='../script/images/delete.png' class='red_min'   id='sremove"+d+"'   onclick='return remove("+d+")'></td></tr>")
}
function remove(d){
	var tb=$("#tbListTM tr").length;
	if($('#tbListTM tr').length>2){
		var selement = 'sremove' + d;  
		var scurrentTr = $('#'+selement).parent().parent();  
		scurrentTr.remove();  
		$('#tbListTM tr').each(function(index){   
		    $(this).find("input[name='slayerNum']").attr("id","slayerNum"+(index-1));
		    $(this).find("input[name='srequire']").attr("id","srequire"+(index-1));
		    $(this).find("input[name='srealCheck']").attr("id","srealCheck"+(index-1));
		    $(this).find("select").attr("id","sjudge"+(index-1));
		 });  
	}else{
		$.messager.alert('提示', '至少填写一条信息', 'info');
	}

}

//新增孔径检测
function addcheck(){
	var dd= $("#check").find("tr").length-1;
	var id=dd+1;
	var html=""
		html+="<tr>"
		html+="<td>"+id+"</td>"
		html+="<td><input   name='pN' type='text' index='"+dd+"' id='pN"+dd+"' list='data_pN"+dd+"'  oninput='btn_pN(this)'><datalist id='data_pN"+dd+"'><option value='P'><option value='N'><option value='Via'></datalist></td>"
		html+="<td><input   name='require' type='text' id='require"+dd+"'></td>"
		html+="<td><input type='text' id ='pN_require"+dd+"' name='pN_require'  list='list_require"+dd+"'><datalist id='list_require"+dd+"' ><option value='±0.075'><option value='±0.050'></datalist></td>"
		html+="<td><input  name='realCheck' type='text'  id='realCheck"+dd+"'></td>"
		html+="<td>"
		html+=	"<select   id='judge"+dd+"'>"
		html+=		"<option>合格</option><"
		html+=		'<option>不合格</option>'
		html+=		'<option>/</option>'
		html+=	"</select>"
		html+="</td>"
		html+="<td style='text-align: center;'><img src='../script/images/delete.png' class='red_min'  id='premove"+dd+"'  onclick='return premove("+dd+")'></td>"
		html+='</tr>'
	$("#check").append(html)
	//$("#check").append("<tr><td>"+id+"</td><td><input   name='pN' type='text' id='pN"+dd+"'></td><td><input   name='require' type='text' id='require"+dd+"'></td><td><input  name='realCheck' type='text'  id='realCheck"+dd+"'></td><td><select   id='judge"+dd+"'><option>合格</option><option>不合格</option></select></td><td><a  id='premove"+dd+"'  class='easyui-linkbutton remove' iconCls='icon-remove' href='javascript:void(0)'  onclick='return premove("+dd+")'>删除</a></td></tr>")
}
function premove(dd){ 
	var length=$('#check tr').length;
	if($('#check tr').length>2){
		 	var element = 'premove' + dd;  
		    //获取删除按钮对应的当前行  
		    var currentTr = $('#'+element).parent().parent();  
		    //删除当前行  
		    currentTr.remove();  
		    //删除成功之后进行排序  
		    $('#check tr').each(function(index){  
		        //循环每一个tr，给每行序号重新赋值  
		        $(this).find('td:first').text(index); 
		        $(this).find("input[name='pN']").attr("index",(index-1));
		        $(this).find("input[name='pN']").attr("id","pN"+(index-1));
		        $(this).find("input[name='require']").attr("id","require"+(index-1));
		        $(this).find("input[name='pN_require']").attr("id","pN_require"+(index-1));
		        $(this).find("input[name='realCheck']").attr("id","realCheck"+(index-1));
		        $(this).find("select").attr("id","judge"+(index-1));
		    });  
	}else{
		$.messager.alert('提示', '至少填写一条信息', 'info');
	}
   
}  



//新增特尺寸
function special_size(){
	var dd= $("#add_special").find("tr").length;
	var index=dd+5;
	var html=""
		html+='<tr>'
		html+='<td style="width:5%">'+index+'</td>'
		html+='<td colspan="2" style="width: 10%"><input type="text" id="itemName'+dd+'" name="itemName"></td>'
		html+='<td style="width: 20%"><input type="text" id="rests_require'+dd+'" name="rests_require" ></td>'
		html+='<td style="width: 20%"><input type="text" id="rests_unit'+dd+'" name="rests_unit"></td>'
		html+='<td style="width: 20%"><input type="text" id="rests_realCheck'+dd+'" name="rests_realCheck"></td>'
		html+='<td style="width: 20%">'
		html+=	'<select id="rests_judge'+dd+'">'
		html+=		'<option value=""></option>'
		html+=		'<option>合格</option>'
		html+=		'<option>不合格</option>'
		html+=	'</select>'
		html+='</td>'
		html+='<td style="text-align: center;width:5%"><img src="../script/images/delete.png" class="red_min"  id="special_remove'+dd+'"  onclick="return special_remove('+dd+')"></td>'
		html+='</tr>'
	$("#add_special").append(html)
}
function special_remove(dd){ 
	var element = 'special_remove' + dd;  
    //获取删除按钮对应的当前行  
    var currentTr = $('#'+element).parent().parent();  
    currentTr.remove();  
    $('#add_special tr').each(function(index){  
    	$(this).find('td:first').text((index+8)); 
    	$(this).find("input[name='itemName']").attr("id","itemName"+index);
    	$(this).find("input[name='rests_require']").attr("id","rests_require"+index);
    	$(this).find("input[name='rests_unit']").attr("id","rests_unit"+index);
    	$(this).find("input[name='rests_realCheck']").attr("id","rests_realCheck"+index);
    	$(this).find("select").attr("id","rests_judge"+index);
    });  
   
}  





//获取当前日期
function getCurentDateStr(){   
    var now = new Date();  
    var year = now.getFullYear();       
    var month = now.getMonth() + 1;    
    var day = now.getDate();          
    var clock = year + "-";  
    if(month < 10) clock += "0";         
    clock += month + "-";  
    if(day < 10) clock += "0";   
    clock += day;  
    return clock;   
}  
//input按下键盘判断是否为空，有则加上mm
function isNUll(obj){
	if($(obj).val()!=""){
		if($(obj).parent("td").find("span").length=='0'){
			$(obj).parent("td").append("<span>mm</span>")
		}
		btn_result(obj);
	}else{
		$(obj).parent("td").find("span").remove();
	}
}
function isNUll_per(obj){
	if($(obj).val()!=""){
		if($(obj).parent("td").find("span").length=='0'){
			$(obj).parent("td").append("<span>%</span>");
		}
		btn_result(obj);
	}else{
		$(obj).parent("td").find("span").remove();
	}
}


//孔径检测选择p/N
function btn_pN(obj){
	var id=$(obj).attr("index");
	if($(obj).val()=="P"){
		$("#pN_require"+id+"").val("±0.075")
	}else if($(obj).val()=="N"){
		$("#pN_require"+id+"").val("±0.050")
	}else{
		$("#pN_require"+id+"").val("")
	}
}




function test(Num,apertureCheck,differential_characteristic_impedance,single_ended_impedances,special_dimensions){
    //初始化
    if(differential_characteristic_impedance==""){
    	//孔径检测
    	for(var i=0;i<=19;i++){
	    	data='<tr>'+
    		'<td style="width: 5%"></td>'+
	    	'<td style="width: 15%"><input type="text" index="'+i+'"  id ="pN'+i+'" name="pN"  list="data_pN'+i+'" oninput="btn_pN(this)"><datalist id="data_pN'+i+'" ><option value="P"><option value="N"><option value="Via"></datalist></td>'+
	    	'<td style="width: 20%"><input type="text" name="require" id="require'+i+'" ></td>'+
	    	'<td style="width: 15%"><input type="text" id ="pN_require'+i+'" name="pN_require"  list="list_require'+i+'"><datalist id="list_require'+i+'" ><option value="±0.075"><option value="±0.050"></datalist></td>'+
	    	'<td style="width: 15%"><input type="text" name="realCheck" id="realCheck'+i+'" class=""></td>'+
			'<td style="width: 15%">'+
			   '<select id="judge'+i+'"><option>合格</option><option>不合格</option><option>/</option></select>'+
		    '</td>'	+
		    '<td style="width: 15%;text-align: center;">'+
			   '<img src="../script/images/delete.png" class="red_min"   id="premove'+i+'"    onclick="return premove('+i+')">'+
		    '</td>'	+
		    '</tr>' 
		    $("#check-tr").append(data);
		    console.log(data)
	    }
    	
	    var len = $('#check tr').length;
	    for(var i = 1;i<len;i++){
	    	$('#check tr:eq('+i+') td:first').text(i);
	    }

	    
	    //单端阻抗
	    for(var j=0;j<=single_ended_impedances.length;j++){
	    	var bb="";
	    	bb=	'<tr>'+
    		'<td style="width: 5%">1</td>'+
			'<td style="width: 15%">单端阻抗</td>'+
	    	'<td style="width: 10%"><input type="text" id="slayerL'+j+'" value="L"></td>'+
	    	'<td style="width: 10%"><input type="text" name="slayerNum" id="slayerNum'+j+'"></td>'+
	    	'<td style="width: 10%"><input type="text" name="srequire" id="srequire'+j+'"></td>'+
	    	'<td style="width: 10%"><input type="text"  id="srequire_ten'+j+'" value="±10%" ></td>'+
	    	'<td style="width: 20%"><input type="text" name="srealCheck" id="srealCheck'+j+'" ></td>'+
			'<td style="width: 15%">'+
			   '<select id="sjudge'+j+'"><option>合格</option><option>不合格</option><option>/</option></select>'+
		    '</td>'	+
		    '<td style="width: 5%;text-align: center;">'+
			   '<img src="../script/images/delete.png" class="red_min"    id="sremove'+j+'"   onclick="return remove('+j+')">'+
		    '</td>'	+
		    '</tr>' 
		    $("#tbListTM-tr").append(bb);
		    console.log(bb)
	    }
	    
	    //差分阻抗
	    for(var z=0;z<=differential_characteristic_impedance.length;z++){
	    	var cc="";
	    	cc=	'<tr>'+
    		'<td style="width: 5%">2</td>'+
			'<td style="width: 15%">差分阻抗</td>'+
	    	'<td style="width: 10%"><input type="text" id="layerNumL'+z+'" value="L"></td>'+
	    	'<td style="width: 10%"><input type="text"name="layerNum"  id="layerNum'+z+'"></td>'+
	    	'<td style="width: 10%"><input type="text" name="drequire" id="drequire'+z+'" ></td>'+
	    	'<td style="width: 10%"><input type="text"  id="drequire_ten'+z+'"value="±10%" ></td>'+
	    	'<td style="width: 20%"><input type="text" name="drealCheck" id="drealCheck'+z+'" class=""></td>'+
			'<td style="width: 15%">'+
			   '<select id="djudge'+z+'"><option>合格</option><option>不合格</option><option>/</option></select>'+
		    '</td>'	+
		    '<td style="width: 5%;text-align: center;">'+
			   '<img src="../script/images/delete.png" class="red_min"    id="dremove'+z+'"    onclick="return dremove('+z+')">'+
		    '</td>'	+
		    '</tr>' 
		    $("#mmtr").append(cc);
		    console.log(cc)
	    }
    }else{
	    
    	$("#vCutResidualThickness_judge").val(special_dimensions.vCutResidualThickness.judge)
    	$("#vCutDeviation_judge").val(special_dimensions.vCutDeviation.judge)
    	$("#goldfingerBevelDepth_judge").val(special_dimensions.goldfingerBevelDepth.judge)
    	$("#goldfingerResidualThickness_judge").val(special_dimensions.goldfingerResidualThickness.judge)
    	$("#stepWidth_judge").val(special_dimensions.stepWidth.judge)
    	$("#stepResidualThickness_judge").val(special_dimensions.stepResidualThickness.judge)
    	$("#steppedHoleAperture_judge").val(special_dimensions.steppedHoleAperture.judge)
    	$("#steppedHoleHoleDepth_judge").val(special_dimensions.steppedHoleHoleDepth.judge)
    	
    	for(var i=0;i<special_dimensions.rests.length;i++){
    		var special_dimensions_html=''
    		special_dimensions_html+='<tr>'
    		special_dimensions_html+=	'<td style="width: 5%">'+(i+5)+'</td>'
    		special_dimensions_html+=	'<td colspan="2" style="width: 10%"><input type="text" name="itemName"  value="'+special_dimensions.rests[i].itemName+'" id="itemName'+i+'"></td>'
    		special_dimensions_html+=	'<td style="width: 20%"><input type="text" name="rests_require" value="'+special_dimensions.rests[i].require+'" id="rests_require'+i+'" ></td>'
    		special_dimensions_html+=	'<td style="width: 20%"><input type="text" name="rests_unit" value="'+special_dimensions.rests[i].unit+'" id="rests_unit'+i+'"></td>'
    		special_dimensions_html+=	'<td style="width: 20%"><input type="text" name="rests_realCheck" value="'+special_dimensions.rests[i].realCheck+'" id="rests_realCheck'+i+'"></td>'
    		special_dimensions_html+=	'<td style="width: 20%"> '
    		special_dimensions_html+=		'<select id="rests_judge'+i+'">'
    		special_dimensions_html+=			'<option value=""></option>'
    		special_dimensions_html+=			'<option>合格</option> '
    		special_dimensions_html+=			'<option>不合格</option> '
    		special_dimensions_html+=		'</select>'
    		special_dimensions_html+=	'</td> '
    		if(i>=3){
    			special_dimensions_html+='<td style="width: 5%;text-align: center;" >'
    			special_dimensions_html+=	'<img src="../script/images/delete.png" class="red_min"   id="special_remove'+i+'"   onclick="return special_remove('+i+')">'
    			special_dimensions_html+='</td>'
    		}else{
    			special_dimensions_html+='<td style="width: 5%;text-align: center;"></td>'
	    		
    		}
    		special_dimensions_html+='</tr>'
    		
			$("#add_special").append(special_dimensions_html);
    	}
    	for(var i=0;i<special_dimensions.rests.length;i++){
			$("#rests_judge"+i).val(special_dimensions.rests[i].judge);
		}
    	
    	for(var i=0;i<apertureCheck.length;i++){			    			    
    		data='<tr>'+
    		'<td style="width: 5%"></td>'+
	    	'<td style="width: 15%"><input type="text" index="'+i+'"  id ="pN'+i+'" name="pN" value="'+apertureCheck[i].pN+'" list="data_pN'+i+'"  oninput="btn_pN(this)"><datalist id="data_pN'+i+'"><option value="P"><option value="N"><option value="Via"></datalist></td>'+
	    	'<td style="width: 20%"><input type="text" name="require" id="require'+i+'"   value="'+apertureCheck[i].require+'"></td>'+
	    	'<td style="width: 15%"><input type="text" id ="pN_require'+i+'" name="pN_require" value="'+apertureCheck[i].requireTolerance+'"  list="list_require'+i+'"><datalist id="list_require'+i+'" ><option value="±0.075"><option value="±0.050"></datalist></td>'+
	    	'<td style="width: 15%"><input type="text" name="realCheck" id="realCheck'+i+'" value="'+apertureCheck[i].realCheck+'"></td>'+
			'<td style="width: 15%">'+
			   '<select id="judge'+i+'"><option>合格</option><option>不合格</option><option>/</option></select>'+
		    '</td>'	+
		    '<td style="width: 15%;text-align: center;">'+
			   '<img src="../script/images/delete.png" class="red_min"    id="premove'+i+'" onclick="return premove('+i+')">'+
		    '</td>'	+
		    '</tr>' 
		    $("#check-tr").append(data);
		    //console.log(data)
	    }
	    var len = $('#check tr').length;
	    for(var i = 1;i<=len;i++){
	        $('#check tr:eq('+i+') td:first').text(i);
	    }
	    
    	for(var i=0;i<apertureCheck.length;i++){
			$("#judge"+i).val(apertureCheck[i].judge);
		}		    
	    for(var j=0;j<single_ended_impedances.length;j++){
	    	var html="";		    
	    	html='<tr>'+
    		'<td style="width: 5%">1</td>'+
			'<td style="width: 15%">单端阻抗</td>'+
			'<td style="width: 10%"><input type="text" id="slayerL'+j+'" value="'+single_ended_impedances[j].layer+'"></td>'+
	    	'<td style="width: 10%"><input type="text" name="slayerNum" id="slayerNum'+j+'" value="'+single_ended_impedances[j].layerNum+'" class=""></td>'+
	    	'<td style="width: 10%"><input type="text" name="srequire" id="srequire'+j+'"  value="'+single_ended_impedances[j].require+'" class=""></td>'+
	    	'<td style="width: 10%"><input type="text"  id="srequire_ten'+j+'" value="'+single_ended_impedances[j].requireTolerance+'" ></td>'+
	    	'<td style="width: 20%"><input type="text" name="srealCheck" id="srealCheck'+j+'"  value="'+single_ended_impedances[j].realCheck+'" class=""></td>'+
			'<td style="width: 15%">'+
			   '<select id="sjudge'+j+'"><option>合格</option><option>不合格</option><option>/</option></select>'+
		    '</td>'	+
		    '<td style="width: 5%;text-align: center;">'+
			   '<img src="../script/images/delete.png" class="red_min"    id="sremove'+j+'"  onclick="return remove('+j+')">'+
		    '</td>'	+
		    '</tr>'
		    $("#tbListTM-tr").append(html);
	    }

	    for(var j=0;j<single_ended_impedances.length;j++){
			$("#sjudge"+j).val(single_ended_impedances[j].judge);
		}
	    for(var z=0;z<differential_characteristic_impedance.length;z++){
	    	var aa="";
	    	aa=	'<tr>'+
    		'<td style="width: 5%">2</td>'+
			'<td style="width: 15%">差分阻抗</td>'+
			'<td style="width: 10%"><input type="text" id="layerNumL'+z+'" value="'+differential_characteristic_impedance[z].layer+'"></td>'+
	    	'<td style="width: 10%"><input type="text" name="layerNum" id="layerNum'+z+'" value="'+differential_characteristic_impedance[z].layerNum+'" class=""></td>'+
	    	'<td style="width: 10%"><input type="text" name="drequire" id="drequire'+z+'"  value="'+differential_characteristic_impedance[z].require+'"class=""></td>'+
	    	'<td style="width: 10%"><input type="text"  id="drequire_ten'+z+'"value="'+differential_characteristic_impedance[z].requireTolerance+'" ></td>'+
	    	'<td style="width: 20%"><input type="text" name="drealCheck" id="drealCheck'+z+'"  value="'+differential_characteristic_impedance[z].realCheck+'" class=""></td>'+
			'<td style="width: 15%">'+
			   '<select id="djudge'+z+'"><option>合格</option><option>不合格</option><option>/</option></select>'+
		    '</td>'	+
		    '<td style="width: 5%;text-align: center;">'+
			   '<img src="../script/images/delete.png" class="red_min"    id="dremove'+z+'"  onclick="return dremove('+z+')">'+
		    '</td>'	+
		    '</tr>' 
		    $("#mmtr").append(aa);
	    }
	    for(var z=0;z<differential_characteristic_impedance.length;z++){ 
			$("#djudge"+z).val(differential_characteristic_impedance[z].judge);
		}	
    }
}




//新增---报告
function add_certification(){
	var length=$("#add_certification_table").find('tr').length;
	var i=parseInt(length)-parseInt('11');
	var html=""
		html+='<tr>'
		html+='<td><input type="text" id="add_certification_th'+i+'"></td>'
		html+='<td><input type="text" id="add_certification_td'+i+'"></td>'
		html+='<td style="text-align: center;line-height: 25px;"><img src="../script/images/delete.png" class="red_min"  id="delete_certification_td'+i+'"  onclick="delete_certification('+i+')"></td>'
		html+='</tr>'
	$("#add_certification_table").append(html);
}
//删除---报告
function delete_certification(id){	
	var element = 'add_certification_th' + id;  
    var currentTr = $('#'+element).parent().parent();  
    currentTr.remove();  
    $('#add_certification_table tr').each(function(index){  
    	if(index>=11){
    		//alert((index-11))
    		$(this).find("td").eq(0).children("input").attr("id","add_certification_th"+(index-11));
    		$(this).find("td").eq(1).children("input").attr("id","add_certification_td"+(index-11));
    		$(this).find("img").attr("id","delete_certification_td"+(index-11));
    		$(this).find("img").attr("onclick","delete_certification("+(index-11)+")");
    	}
    });  
}

//检验结果为/，判定为/
function btn_result(obj){
	if($(obj).val()=="/"){
		$(obj).parent("td").parent("tr").find("td:last").find("select").val("/");
	}else{
		$(obj).parent("td").parent("tr").find("td:last").find("select").val("合格");
	}
	//常规标记要求=检验结果
	if($(obj).attr("id")=="regular_label-checkResult"){
		$("#regular_label-checkRequire").val($("#regular_label-checkResult").val());
	}
}
//合格证批号=检验报告生产周期
function btn_productPeriod(){
	$("#batchNumber").val($("#productPeriod").val());
}