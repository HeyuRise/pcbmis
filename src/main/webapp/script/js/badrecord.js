/*查询*/
document.onkeydown=search;	
function search(event) {
	if(event.keyCode == 13) {
		listSearch();
	}
}
function listSearch(){ 
//	var disposal1=$('#disposal').combobox("getValues");
//	var disposal=JSON.stringify(disposal1);
	$('#listTable').datagrid('load',{
		orderNumber: $('#orderNumber').val(),
		customer: $('#customer').val(),
		boardName: $('#boardName').val(),
		factoryId: $('#factoryId').combobox("getValue"),
		submitStartTime:$("#submitStartTime").datebox('getValue'),
		submitEndTime:$("#submitEndTime").datebox('getValue'),
		belongCompanyId: $('#belongCompanyId').combobox("getValue"),
		productLevel: $('#productLevel').combobox("getValue"),
		inspector: $('#inspector').val(),
		productType: $('#productType').combobox("getValue"),
		disposal:$('#disposal').combobox("getValue"),
	});
}
function format(time, format) {
	var d = "";
	var date = new Date(time);
	if (format == "yyyyMMdd") {
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var day = date.getDate();
			d=year + '-' + p(month) + '-' + p(day);	
		} else if (format == "yyyyMMddhhmmss") {
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var day = date.getDate();
			var Hour = date.getHours();      //获取当前小时数(0-23)
			var Minute = date.getMinutes();   // 获取当前分钟数(0-59)
			var Sec =date.getSeconds();      //获取当前秒数(0-59)
			d=year + '-' + p(month) + '-' + p(day)+' '+p(Hour)+':'+p(Minute)+':'+p(Sec);	       
	    }
	    return d;

	}
function p(s) {
    return s < 10 ? '0' + s: s;
}

$(function () {
	$("#belongCompanyId").combobox("clear")
	$('#listTable').datagrid({
		url:'/pcbmis/unqualified/list',
		loadMsg:"正在加载数据，请稍等...",
		toolbar:'#tb',
		fit:true,
		fitColumns:true,
		striped:true,
		singleSelect:true,
		nowrap:false,
		pagination:true,
		method:'get',
		pageSize:30,
		pageList:[10,20,30,40,50],
		columns:[[{
			field:'serial_number',
			width:200,
			align:'center',
			title:'不合格品处置单流水号',
			formatter: function(value,row,index){
				if(row.status=="1"){
					return "<a href='disposal_sheet.html?checkNum="+row.check_num+"'>null</a>"
				}else if(row.status=="2"){
					return "<a href='disposal_sheet_detail.html?Num="+row.check_num+"&serialNumber=' >"+row.serial_number+"</a>"
				}else if(row.status=="3"){
					return "<a href='sheet_detail.html?Num="+row.check_num+"&serialNumber='>"+row.serial_number+"</a>"
				}else{
					 
				}
//				if(row.serial_number==null ||row.serial_number==undefined){
//					return "<a href='disposal_sheet_detail.html?Num="+row.check_num+"' target='_blank'>null</a>"
//				}else{
//					return "<a href='disposal_sheet_detail.html?Num="+row.check_num+"' target='_blank'>"+row.serial_number+"</a>"
//				}
				
			}
		},{
			field:'submit_time',
			width:200,
			align:'center',
			title:'拟制日期',
			formatter: function(value,row,index){
				if(row.submit_time==null  ||row.submit_time==undefined){
					return "<span></span>"
				}else{
					return "<span>"+format(row.submit_time,"yyyyMMdd")+"</span>"
				}
			}
		},{
			field:'company_name',
			width:150,
			align:'center',
			title:'所属公司'
		},{
			field:'order_number',
			width:200,
			align:'center',
			title:'工单号'
		},{
			field:'guest_name',
			width:150,
			align:'center',
			title:'客户'
		},{
			field:'product_type',
			width:250,
			align:'center',
			title:'产品类型',
		},{
			field:'product_level',
			width:100,
			align:'center',
			title:'产品等级',
		},{
			field:'factory_name',
			width:100,
			align:'center',
			title:'生产厂家'
		},{
			field:'storage_number',
			width:120,
			align:'center',
			title:'来料数量'
		},{
			field:'unqualified_number',
			width:130,
			align:'center',
			title:'不合格品数量'
		},{
			field:'username',
			width:150,
			align:'center',
			title:'检验员'
		},{
			field:'disposal_method',
			width:150,
			align:'center',
			title:'处置方式'
		}
		]],
		//显示扩展行
		view:detailview,
		detailFormatter:function(index,row){
			return '<div id="listTable-'+index+'" style="padding:2px 0">板名：<span>'+row.board_name+'</span></div>';
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
//			jQuery("#listTable").datagrid('fixDetailRowHeight',index);
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
 	
 	$("#receiving_product").dialog({
	    title: '接收产品',
	    width:'340',
	    height: '180',
	    closed: true,
	    modal: true,
	    buttons:[{
	    	text:'保存',
	    	iconCls:'icon-save',
	    	handler:function(){
	    		
	    	}
	    },{
	    	text:'取消',
	    	iconCls:'icon-cancel',
	    	handler:function(){
	    		 $("#receiving_product").dialog("close");
	    	}
	    }]
	})  
})

//开始小于结束
function onSelect(d) {  
	if($('#submitStartTime').datebox('getValue')!='' && $('#submitEndTime').datebox('getValue')!=''){
		var ed=new Date($('#submitEndTime').datebox('getValue')).getTime()
		var sd=new Date($('#submitStartTime').datebox('getValue')).getTime()
		//var issd = this.id == 'sd', sd = issd ? d : new Date($('#submitStartTime').datebox('getValue')), ed = issd ? new Date($('#submitEndTime').datebox('getValue')) : d;  
		if (ed < sd) {  
			parent.$.messager.alert("提示","结束日期小于开始日期!");  
			$('#submitEndTime').datebox('setValue', '');  
		}  
	}
	
}  

/*重置*/
function listReset() {
	$('#listForm').form('reset');
	$("#belongCompanyId").combobox("clear")
	$('#listTable').datagrid('load',{type: ''});
}

/*导出*/
function btn_export() {
//	var check = $('#check').val();
	$("#export_excel").form('submit',{
		url:'/pcbmis/downLoadFile/excelList',
		method:'get',
		onSubmit:function(param) {
			//param.check=check;
			param.orderNumber=$('#orderNumber').val();
			param.customer=$('#customer').val();
			param.boardName= $('#boardName').val();
			param.factoryId= $('#factoryId').combobox("getValue");
			param.submitStartTime=$("#submitStartTime").datebox('getValue');
			param.submitEndTime=$("#submitEndTime").datebox('getValue');
			param.belongCompanyId= $('#belongCompanyId').combobox("getValue");
			param.productLevel= $('#productLevel').combobox("getValue");
			param.inspector= $('#inspector').val();
			param.productType= $('#productType').combobox("getValue");
			param.disposal=$('#disposal').combobox("getValue");
		},
		dataType:'json',
		success:function(data) {
			if (!data.result) {
				$.messager.alert('提示', data.msg, 'error');
			}
		}
	});
}