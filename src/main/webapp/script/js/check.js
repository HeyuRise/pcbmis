
$(function () {
	
	$("#btn_select").combobox("clear");	
	$('#checkTable').datagrid({
		url:'/pcbmis/order/checkList ',
		toolbar:'#tb',
		loadMsg:"正在加载数据，请稍等...",
		fit:true,
		fitColumns:true,
		striped:true,
		singleSelect:true,
		nowrap:false,
/*		 sortName : 'crtTime',
	    sortOrder : 'desc',*/
		pagination:true,
		pageSize:30,
		pageList:[10,20,30,40,50],
		columns:[[{
			field:'checkNum',
			width:100,
			align:'center',
			title:'检验单号',
			formatter: function(value,row,index){
				if(row.checkable=="1" && row.status!="已检验"){
					return "<a href='"+row.href+"?"+"checkNum="+ row.checkNum+"'>查看</a>";
				}else{
					return "<a href='num.html?"+"checkNum="+ row.checkNum+"'>查看</a>";
				}
//				if((row.status=="待检验" && roles.indexOf('检验员')!=-1)  || (row.status=="检验中"  && roles.indexOf('检验员')!=-1)){
//					return "<a href='test-num.html?"+"checkNum="+ row.checkNum+"'>" + row.checkNum +" </a>";
//				}else if((row.status=="待审核" && roles.indexOf('审核员')!=-1) ||(row.status=="审核中"  && roles.indexOf('审核员')!=-1)  ){
//					return "<a href='review-num.html?"+"checkNum="+ row.checkNum+"'>" + row.checkNum +" </a>";
//				}else if(row.status=="已检验"){
//					return "<a href='num.html?"+"checkNum="+ row.checkNum+"'>" + row.checkNum +" </a>";
//				}else{
//					return "<span>" + row.checkNum +" </span>";
//				}
			}
		},{
			field:'orderNum',
			width:200,
			align:'center',
			title:'工单号'
		},{
			field:'guestName',
			width:150,
			align:'center',
			title:'客户'
		},{
			field:'factoryName',
			width:150,
			align:'center',
			title:'生产厂家'
		},{
			field:'productionNumPcs',
			width:130,
			align:'center',
			title:'生产数量PCS'
		},{
			field:'amountCheckoutPcs',
			width:130,
			align:'center',
			title:'来料数量（块）'
		},{
			field:'spotCheckNumPcs',
			width:130,
			align:'center',
			title:'抽检数量（块）'
		},{
			field:'receviceDate',
			width:150,
			align:'center',
			title:'接收时间',
		},{
			field:'checkContent',
			width:100,
			align:'center',
			title:'检验内容'
		},{
			field:'status',
			width:100,
			align:'center',
			title:'状态'
		},{
			field:'inspector',
			width:100,
			align:'center',
			title:'检验员'
		},{
			field:'checkDate',
			width:150,
			align:'center',
			title:'检验时间'
		},{
			field:'auditor',
			width:150,
			align:'center',
			title:'审核员'
		},{
			field:'auditDate',
			width:150,
			align:'center',
			title:'审核时间',
		}
		]],
		//显示扩展行
		view:detailview,
		detailFormatter:function(index,row){
//			var length=row.boardLong 
//			var f="*"
//			var wide=row.boardWide 
//			var boardPly=row.boardPly
//			var html=""
//			html=length+f+wide+f+boardPly;
//			return html; 
			
			
			return '<table id="checkTable-'+index+'" style="padding:2px 0" class="none_table">'+
						'<tr>' +
							'<td>板名:</td>' +
							'<td><span>'+row.boardName+'</span></td>' +
						'</tr>'+
						'<tr>' +
							'<td>等级:</td>' +
							'<td><span>'+row.categoryGrade+'</span></td>' +
						'</tr>'+
						'<tr>' +
							'<td>规格:</td>' +
							'<td><span>'+row.boardLong+'*'+row.boardWide+'*'+row.boardPly+'</span></td>' +
						'</tr>'+
						'<tr>' +
							'<td>生产数量set:</td>' +
							'<td><span>'+row.productionNumSet+'</span></td>' +
						'</tr>'+
					'</table>';
		},
//		onExpandRow: function(index,row){
//			var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
//			ddv.panel({
//				border:false,
//				cache:false,
//				content:function(){
//					var content="";
//				},
//			});
//			jQuery("#checkTable").datagrid('fixDetailRowHeight',index);
//		},
	
	})
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
 	
	//打印合格证
	 $("#print0").dialog({
	    title: '打印合格证',
	    width:'380',
	    height: 'auto',
	    closed: true,
	    modal: true,
	    buttons:[{
	    	text:'打印',
	    	iconCls:'icon-print',
	    	handler:function(){
	    		var html=""
	    			html='<table class="guding-table" style="width:500px;margin: 0 auto;">'+
	    					'<tr>'+
	    						'<td colspan="2"  style="text-align: center;line-height: 30px;" >'+
    								'<span id="companyName0"></span></br>'+
    								'<span id="categoryGrade0" style="border-bottom: 1px solid black;"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span>产品合格证</span>'+
    							'</td>'+
	    					'</tr>'+
	    					'<tr>'+
    	    				'<td><span  id="print_value0"></span></td>'+
    	    				'<td><span  id="print_orderNum0"></span></td>'+
	    	    			'</tr>'+
	    	    			'<tr>'+
		    				'<td>产品名称</td>'+
		    				'<td><span  id="print_boardName0"></span></td>'+
		    				'</tr>'+
	    					'<tr>'+
	    	    				'<td>生产日期</td>'+
	    	    				'<td><span  id="productDate0"></span></td>'+
	    	    			'</tr>'+
	    	    			'<tr>'+
	    	    				'<td>检验日期</td>'+
	    	    				'<td><span id="checkDate0"></span></td>'+
	    	    			'</tr>'+
		    	    		'<tr>'+
		    	    			'<td>包装日期</td>'+
		    	    			'<td><span id="packageDate0"></span></td>'+
		    	    		'</tr>'+
		    	    		'<tr>'+
		    	    			'<td>检验员</td>'+
		    	    			'<td><span id="inspector0"></span></td>'+
		    	    		'</tr>'+
		    	    		'<tr>'+
		    	    			'<td>保质期</td>'+
		    	    			'<td><span id="expirationDate0"></span></td>'+
		    	    		'</tr>'+
		    	    		'<tr>'+
		    	    			'<td>数量</td>'+
		    	    			'<td><span id="spotCheckNumPcs0" ></span></td>'+
		    	    		'</tr>'+
		    	    		'<tr>'+
		    	    			'<td>批号</td>'+
		    	    			'<td><span id="batchNumber0" ></span></td>'+
		    	    		'</tr>'+
		    	    		'<tr>'+
		    	    			'<td>规格</td>'+
		    	    			'<td><span id="size0"></span></td>'+
		    	    		'</tr>'+
	    	    		'</table>'
	    	    $("#print_content").html(html);	
	    		$("#companyName0").html($("#companyName").html())
	    		$("#print_value0").html($("#print_value").val())
	    		$("#print_orderNum0").html($("#print_orderNum").html())
	    		$("#print_boardName0").html($("#print_boardName").html())
	    		$("#categoryGrade0").html($("#categoryGrade").html())
	    		$("#productDate0").html($("#productDate").datebox("getValue"))
	    		$("#checkDate0").html($("#checkDate").datebox("getValue"))
	    		$("#packageDate0").html($("#packageDate").datebox("getValue"))
	    		$("#inspector0").html($("#inspector").val())
	    		$("#expirationDate0").html($("#expirationDate").val())
	    		$("#spotCheckNumPcs0").html($("#spotCheckNumPcs").val())
	    		$("#batchNumber0").html($("#batchNumber").val())
	    		$("#size0").html($("#size").val())
	    		setTimeout(function () { 
	    			$("#print_content").jqprint({});
	    		}, 500);
	    	}
	    },{
	    	text:'取消',
	    	iconCls:'icon-cancel',
	    	handler:function(){
	    		 $("#print0").dialog("close");
	    	}
	    }]
	})
	
	//打印标签---20
	$("#print1").dialog({
	    title: '打印标签',
	    width:'380',
	    height: 'auto',
	    closed: true,
	    modal: true,
	    buttons:[{
	    	text:'打印',
	    	iconCls:'icon-print',
	    	handler:function(){
	    		var html=""
	    			html='<table class="guding-table" style="width:500px;margin: 0 auto;">'+
//	    					'<tr>'+
//		    					'<td  colspan="2"  style="text-align: center;" >'+
//		    						'<img src="../script/images/logo.png" style="vertical-align: middle;">'+
//		    						'<span id="print10_companyName"></span></br>'+
//		    					'</td>'+
//				    		'</tr>'+
//				    		'<tr>'+
//				    			'<td>客户</td>'+
//				    			'<td><span id="print10_guestName"></span></td>'+
//				    		'</tr>'+
				    		'<tr>'+
				    			'<td>板名</td>'+
				    			'<td><span id="print10_boardName"></span></td>'+
				    		'</tr>'+
				    		'<tr>'+
				    			'<td>数量</td>'+
				    			'<td><span id="print10_amountCheckoutPcs"></span></td>'+
				    		'</tr>'+
				    		'<tr>'+
				    			'<td>保质期至</td>'+
				    			'<td><span id="print10_expirationDate" ></span></td>'+
				    		'</tr>'+
				    		'<tr>'+
				    			'<td>生产日期</td>'+
				    			'<td><span id="print10_productDate"></span></td>'+
				    		'</tr>'+
				    		'<tr>'+
				    			'<td>检验日期</td>'+
				    			'<td><span  id="print10_checkDate"></span></td>'+
				    		'</tr>'+
	    	    		 '</table>'
				$("#print_content").html(html);	
//	    		$("#print10_companyName").html($("#print1_companyName").html())
//	    		$("#print10_guestName").html($("#print1_guestName").html())
	    		$("#print10_boardName").html($("#print1_boardName").html())
	    		$("#print10_amountCheckoutPcs").html($("#print1_amountCheckoutPcs").val())
	    		$("#print10_expirationDate").html($("#print1_expirationDate").datebox("getValue"))
	    		$("#print10_productDate").html($("#print1_productDate").datebox("getValue"))
	    		$("#print10_checkDate").html($("#print1_checkDate").datebox("getValue"))
	    		setTimeout(function () { 
	    			$("#print_content").jqprint({});
	    		}, 500);
	    	}
	    },{
	    	text:'取消',
	    	iconCls:'icon-cancel',
	    	handler:function(){
	    		 $("#print1").dialog("close");
	    	}
	    }]
	})
	
	//打印标签---普通
	$("#print2").dialog({
	    title: '打印标签',
	    width:'380',
	    height: 'auto',
	    closed: true,
	    modal: true,
	    buttons:[{
	    	text:'打印',
	    	iconCls:'icon-print',
	    	handler:function(){
	    		var html=""
	    			html='<table class="guding-table" style="width:500px;margin: 0 auto;">'+
	    					'<tr>'+
				    			'<td colspan="2"  style="text-align: center;" >'+
				    				'<img src="../script/images/logo.png" style="vertical-align: middle;">'+
				    				'<span id="print20_companyName"></span></br>'+
				    			'</td>'+
				    		'</tr>'+
				    		'<tr>'+
				    			'<td>客户</td>'+
				    			'<td><span id="print20_guestName"></span></td>'+
				    		'</tr>'+
				    		'<tr>'+
				    			'<td>板名</td>'+
				    			'<td><span id="print20_boardName"></span></td>'+
				    		'</tr>'+
				    		'<tr>'+
				    			'<td>工单号</td>'+
				    			'<td><span id="print20_checkNum"></span></td>'+
				    		'</tr>'+
				    		'<tr>'+
				    			'<td>数量</td>'+
				    			'<td><span id="print20_amountCheckoutPcs"></span></td>'+
				    		'</tr>'+
				    		'<tr>'+
				    			'<td>日期</td>'+
				    			'<td><span id="print20_checkDate"></span></td>'+
				    		'</tr>'+
	    	    		 '</table>'
				$("#print_content").html(html);	
	    		$("#print20_companyName").html($("#print2_companyName").html())
	    		$("#print20_guestName").html($("#print2_guestName").html())
	    		$("#print20_boardName").html($("#print2_boardName").html())
	    		$("#print20_checkNum").html($("#print2_checkNum").html())
	    		$("#print20_amountCheckoutPcs").html($("#print2_amountCheckoutPcs").val())
	    		$("#print20_checkDate").html($("#print2_checkDate").datebox("getValue"))
	    		$("#size0").html($("#size").val())
	    		setTimeout(function () { 
	    			$("#print_content").jqprint({});
	    		}, 500);
	    	}
	    },{
	    	text:'取消',
	    	iconCls:'icon-cancel',
	    	handler:function(){
	    		 $("#print2").dialog("close");
	    	}
	    }]
	})
	
})

/*查询*/
document.onkeydown=search;	
function search(event) {
	if(event.keyCode == 13) {
		checkSearch();
	}
}
function checkSearch(){
	$('#checkTable').datagrid('load',{
		orderNum: $('#orderNum').val(),
		guest: $('#guest').val(),
		boardName: $('#baordName').val(),
		content: $('#btn_select').combobox("getValue"),
		factoryId:$("#factoryId").combobox("getValue"),
		gradeId:$("#gradeId").combobox("getValue"),
		statusId:$("#btn_status").combobox("getValue"),
		checkStart:$("#start_date").datebox('getValue'),
		checkEnd:$("#end_date").datebox('getValue'),
		auditStart:$("#start1_date").datebox('getValue'),
		auditEnd:$("#end1_date").datebox('getValue'),
	});
};

/*重置*/
function checkRest() {
	$('#checkForm').form('reset');
	$("#btn_select").combobox("clear");
	$('#checkTable').datagrid('load',{type: ''});
}

//开始小于结束
function onSelect(d) {  
	if($('#start_date').datebox('getValue')!='' && $('#end_date').datebox('getValue')!=''){
		var ed=new Date($('#end_date').datebox('getValue')).getTime()
		var sd=new Date($('#start_date').datebox('getValue')).getTime()
		//var issd = this.id == 'sd', sd = issd ? d : new Date($('#start_date').datebox('getValue')), ed = issd ? new Date($('#end_date').datebox('getValue')) : d;  
		if (ed < sd) {  
			parent.$.messager.alert("提示","结束日期小于开始日期!");  
			$('#end_date').datebox('setValue', '');  
		}  
	}
	
	if($('#start1_date').datebox('getValue')!='' && $('#end1_date').datebox('getValue')!=''){
		var ed1=new Date($('#end1_date').datebox('getValue')).getTime()
		var sd1=new Date($('#start1_date').datebox('getValue')).getTime()
		//var issd = this.id == 'sd', sd = issd ? d : new Date($('#start1_date').datebox('getValue')), ed = issd ? new Date($('#end1_date').datebox('getValue')) : d;  
		if (ed1 < sd1) {  
			parent.$.messager.alert("提示","结束日期小于开始日期!");  
			$('#end1_date').datebox('setValue', '');  
		}  
	}
}  


//打印
function btn_print(obj){
	var index=$(obj).attr("index");
	var row = $("#checkTable").datagrid("getSelected");
	if(row){
		if(row.companyName=="同步科技"){
			$("#companyName").html("无锡市同步电子科技有限公司")
			$("#print1_companyName").html("无锡市同步电子科技有限公司")
			$("#print2_companyName").html("无锡市同步电子科技有限公司")
		}else if(row.companyName=="同步电子"){
			$("#companyName").html("无锡市同步电子有限公司")
			$("#print1_companyName").html("无锡市同步电子有限公司")
			$("#print2_companyName").html("无锡市同步电子有限公司")
		}else{}
		
		if(index=="0"){
			if(row.categoryGrade.indexOf("民")!=-1 || row.categoryGrade.indexOf("-")!=-1){
				$("#categoryGrade").html("民品印制板")
			}else{
				$("#categoryGrade").html("军品印制板")
			}
			$('#print_orderNum').html(row.orderNum);  
			$('#print_boardName').html(row.boardName); 
			$('#checkDate').datebox('setValue', row.checkDate);  
			$('#productDate').datebox('setValue', row.productDate); 
			$("#inspector").val(row.inspector);
			$("#spotCheckNumPcs").val(row.amountCheckoutPcs+"PCS")
			$("#size").val(row.boardLong+"*"+row.boardWide+"*"+row.boardPly)
			$.ajax({  
			        type: "get",  
			        url: "/pcbmis/check/certificate?checkNum=" + row.checkNum,  
			        contentType: 'application/json', 
			        success: function (data) {  
			        	if(data.certificate.orderNumName==""){
			        		$("#print_value").val("工单号");
			        	}else{
			        		$("#print_value").val(data.certificate.orderNumName);
			        	}
			        	$("#packageDate").datebox('setValue', data.certificate.packageDate); 
			        	$("#expirationDate").val(data.certificate.expirationDate); 
			        	$("#batchNumber").val(data.certificate.batchNumber)
			        }
			})
			
			$("#print0").dialog("open");
		}else{
			if(row.guestName=="20所"){//20所
				$("#print1_guestName").html(row.guestName);
				$("#print1_boardName").html(row.boardName);
				$("#print1_amountCheckoutPcs").val(row.amountCheckoutPcs+"PCS")
				$('#print1_productDate').datebox('setValue', row.productDate); 
				$('#print1_checkDate').datebox('setValue', row.checkDate);  
				$("#print1").dialog("open");
			}else{//普通
				$("#print2_guestName").html(row.guestName);
				$("#print2_boardName").html(row.boardName);
				$("#print2_checkNum").html(row.orderNum);
				$("#print2_amountCheckoutPcs").val(row.amountCheckoutPcs+"PCS");
				$('#print2_checkDate').datebox('setValue', row.checkDate);  
				$("#print2").dialog("open");
			}
			
		}
	}else{
		 $.messager.alert("提示信息", "请先选中一行！","info");
	}

}




//
///*编辑*/
// function editnum() {
//    var row = $("#checkTable").datagrid("getSelected");
//    if (row) {
//          url = "/pcbmis/order/checkList?id=" + row.checkNum;
//          $("#dlg").dialog("open").dialog('setTitle', '修改信息');
//          $("#spotCheckNumPcs").val(row.spotCheckNumPcs);  
//          $("#isCheck").val(row.isCheck);   
//          $("#checkNum").val(row.checkNum);
///*          $('[name="receviceDate"]').val();*/
//          $("#fm").form("load", row);
//          if(row.isCheck=="是"){
//        	  $("#fitem-spotCheckNumPcs").show()
//          }else{
//        	   $("#fitem-spotCheckNumPcs").hide()
//          }
//          if(row.receviceDate==""){
//        	  $("#fitem-receviceDate").show()
//          }else{
//        	   $("#fitem-receviceDate").hide()
//          }
//          
//     }else{
//           $.messager.alert("提示信息", "请先选中一行！","info");
//     }
//}
// 
// 
///*保存*/
//function saveuser(){
//    var row = $("#checkTable").datagrid("getSelected");
//    var amountCheckoutPcs=row.amountCheckoutPcs;
//    console.log(amountCheckoutPcs)
//	var reg=/^[1-9]+\d*$/;
//    
//    if($("#isCheck").val()=="是"){
//    	if($("#spotCheckNumPcs").val()=="" ||  $('[name="receviceDate"]').val()==""  ){
//        	$.messager.alert("提示", "信息填写不完整不能提交！","info");
//        }else{
//        	 if(reg.test($("#spotCheckNumPcs").val())){
//    			  if($("#spotCheckNumPcs").val()<=amountCheckoutPcs){
//    				  $.ajax({  
//    				        type: "POST",  
//    				        url: "/pcbmis/check/editOrder",  
//    				        data:{
//    				        	checkNum:$("#checkNum").val(),
//    				        	spotCheckNumPcs:$("#spotCheckNumPcs").val(),
//    				        	receviceDate:$("#receviceDate").datebox('getValue'),
//    				        	isCheck:$("#isCheck").val(),
//    						},
//    				        success: function (data) {  
//    				        	if(data.result==0){
//    				        		console.info(data);
//    					            $.messager.alert("提示", "保存成功！");  
//    					            $("#checkTable").datagrid("reload");
//    					            $(".combo-arrow").remove();
//    				        	}
//    				        	else{
//    				        		$.messager.alert("提示", data.msg,'error');
//    				        		
//    				        	}
//    				           
//    				        }  
//
//    				    }) 
//    				    $('#dlg').dialog('close');
//			            $("#receviceDate").hide();
//    			  }else{
//    				  $.messager.alert("提示", "抽检数量不能大于来料数量！","info");
//    			  }
//    		   }else{
//    			   $.messager.alert("提示", "请填写正整数！","info");
//    		 }
//        }
//    }else{
//    	if( $('[name="receviceDate"]').val()==""  ){
//        	$.messager.alert("提示", "信息填写不完整不能提交！","info");
//        }else{
//       	 	$.ajax({  
//		        type: "POST",  
//		        url: "/pcbmis/check/editOrder",  
//		        data:{
//		        	checkNum:$("#checkNum").val(),
//		        	receviceDate:$("#receviceDate").datebox('getValue'),
//		        	isCheck:$("#isCheck").val(),
//				},
//		        success: function (data) {  
//		        	if(data.result==0){
//		        		console.info(data);
//			            $.messager.alert("提示", "保存成功！");  
//			            $("#checkTable").datagrid("reload"); 
//		        		
//		        	}
//		        	else{
//		        		$.messager.alert("提示", data.msg,'error'); 
//		        	}
//		           
//		        }  
//
//		    }) 
//		    $('#dlg').dialog('close');
//        }
//
//    }
//}
//
//
///*是否切换抽检数量*/
//function Check(){
//	  if($("#isCheck").val()=="是"){
//    	  $("#fitem-spotCheckNumPcs").show()
//      }else{
//    	   $("#fitem-spotCheckNumPcs").hide()
//      }
//}

/*导出*/
function btn_export() {
	$("#export_excel").form('submit',{
		url:'/pcbmis/downLoadFile/downCheckList',
		dataType:'json',
		type:'get',
		onSubmit:function(param) {
			param.orderNum = $('#orderNum').val(),
			param.guest = $('#guest').val(),
			param.boardName = $('#baordName').val(),
			param.content = $('#btn_select').combobox("getValue"),
			param.factoryId = $("#factoryId").combobox("getValue"),
			param.gradeId = $("#gradeId").combobox("getValue"),
			param.statusId = $("#btn_status").combobox("getValue"),
			param.checkStart = $("#start_date").datebox('getValue'),
			param.checkEnd = $("#end_date").datebox('getValue'),
			param.auditStart = $("#start1_date").datebox('getValue'),
			param.auditEnd = $("#end1_date").datebox('getValue');
		},
		success:function(data) {
			if (!data.result) {
				$.messager.alert('提示', data.msg, 'error');
			}
		}
	});
}

