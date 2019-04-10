/*查询*/
document.onkeydown=search;	
function search(event) {
	if(event.keyCode == 13) {
		listSearch();
	}
}
function listSearch(){
	$('#listTable').datagrid('load',{
		serialNumber: $('#serialNumber').val(),
		orderNumber: $('#orderNumber').val(),
		guestName: $('#guestName').val(),
		productNum:$("#productNum").val(),
		boardName: $('#boardName').val(),
		contactName: $('#contactName').val(),
		inspector: $('#inspector').val(),
		reInspector:$("#reInspector").val(),
		productDateStart:$("#start_date").datebox('getValue'),
		prodcutDateEnd:$("#end_date").datebox('getValue'),
		orderStatus:$("#orderStatus").combobox("getText"),
		reportStatus:$("#reportStatus").combobox("getValue"),
	
	});
}


$(function () {
	$('#listTable').datagrid({
		url:'/pcbmis/col',
		loadMsg:"正在加载数据，请稍等...",
		toolbar:'#tb',
		fit:true,
		fitColumns:true,
		striped:true,
		singleSelect:true,
		nowrap:false,
		pagination:true,
		pageSize:30,
		pageList:[10,20,30,40,50],
		method:'get',
		columns:[[{
			field:'serialNumber',
			width:200,
			align:'center',
			title:'流水号',
			formatter:function(value,row,index){
				return '<a href="col_detail.html?id='+row.id+'">'+value+'</a>'
			}
		},{
			field:'orderNumber',
			width:200,
			align:'center',
			title:'工单号'
		},{
			field:'guestName',
			width:150,
			align:'center',
			title:'客户'
		},{
			field:'productNum',
			width:100,
			align:'center',
			title:'生产数量'
		},{
			field:'boardName',
			align:'center',
			title:'板名',
			formatter:function(value){
				return '<div style="overflow: hidden;text-overflow:ellipsis;white-space: nowrap;width:200px">'+value+'</div>'
			}
		},{
			field:'contactName',
			width:150,
			align:'center',
			title:'经手人'
		},{
			field:'productDate',
			width:130,
			align:'center',
			title:'投产日期'
		},{
			field:'inspector',
			width:150,
			align:'center',
			title:'检验员'
		},{
			field:'reInspector',
			width:250,
			align:'center',
			title:'复检员',
		},{
			field:'orderStatus',
			width:100,
			align:'center',
			title:'工单状态'
		},{
			field:'reportStatus',
			width:120,
			align:'center',
			title:'报告状态'
		}
		]],
		//显示扩展行
		view:detailview,
		detailFormatter:function(index,row){
			return '<div id="listTable-'+index+'" style="padding:2px 0">板名：<span>'+row.boardName+'</span></div></div>';
		},

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

	
	$.extend($.fn.validatebox.defaults.rules, { 
		minnumber: { 
			validator : function(value){ 
		        return /^[1-9]\d{0,4}$/.test(value); 
			},
	 		message : '最多5位正整数',
		}, 
	});
 	$("#receiving_product").dialog({
	    title: '接收产品',
	    width:'340',
	    height: '190',
	    closed: true,
	    modal: true,
	    buttons:[{
	    	text:'保存',
	    	iconCls:'icon-save',
	    	handler:function(){
	    		if($("#receiving_form").form("validate")){
	    			var orderNumber=$("#orderNumber").val()
	    			var jsonstr = "";
	    			jsonstr = JSON.stringify({
//	    				receiveDate:$("#receive_date").datetimebox('getValue'),
	    				contentIds:$("#btn_select").combobox('getValues'),
	    				inStoragePcs:$("#receive_number").val(),
	    				receiveOrderType:$("#sourcetype").combobox('getValue'),
	    					
	    			})
	    			$.ajax({  
	    				 type: "POST",  
	    				 url: "/pcbmis/inStorage/productNew?orderNumber=" + orderNumber,  
	    				 data:jsonstr,
	    				 contentType: 'application/json', 
	    				 success: function (data) {  
	    					 if(data.result==0){
//	    						 localStorage.setItem('receive_date', $('#receive_date').datetimebox('getValue'));
	    						 $("#receiving_product").dialog("close");
	    						 $('#listTable').datagrid('load',{type: ''});
	    						 $.messager.alert("提示信息", "接收产品成功！","ok");
	    					 }else{
	    						 $.messager.alert("提示信息", data.msg,"error",function(){
	    							 $("#receiving_product").dialog("close");
	    						 });
	    					 }
	    				 }
	    			})
	    		}

	    	}
	    },{
	    	text:'取消',
	    	iconCls:'icon-cancel',
	    	handler:function(){
	    		 $("#receiving_product").dialog("close");
	    	}
	    }]
	}) 
	$("#edit_border").dialog({
		 title: '编辑板名',
		 width:'auto',
		 height: 'auto',
		 closed: true,
		 modal: true,
		    buttons:[{
		    	text:'保存',
		    	iconCls:'icon-save',
		    	handler:function(){
		    		if($("#edit_border_form").form("validate")){
		    			var row=$("#listTable").datagrid("getSelected");
		    			var boardName=$("#edit_bordername").val();
		    			$.ajax({  
		    				 type: "PUT",  
		    				 url: "/pcbmis/inStorage/boardName?orderNumber=" + row.inStorageNum+"&boardName="+boardName,  
		    				 contentType: 'application/json', 
		    				 success: function (data) {  
		    					 if(data.result==0){
		    						 $("#edit_border").dialog("close");
		    						 $('#listTable').datagrid('load',{type: ''});
		    						 $.messager.alert("提示信息", "编辑成功！","ok");
		    					 }else{
		    						 $.messager.alert("提示信息", data.msg,"error",function(){
		    							 $("#edit_border").dialog("close");
		    						 });
		    					 }
		    				 }
		    			})
		    		}

		    	}
		    },{
		    	text:'取消',
		    	iconCls:'icon-cancel',
		    	handler:function(){
		    		 $("#edit_border").dialog("close");
		    	}
		    }]
	})
})

//开始小于结束
function onSelect(d) {  
	if($('#start_date').datebox('getValue')!='' && $('#end_date').datebox('getValue')!=''){
		var ed=new Date($('#end_date').datebox('getValue')).getTime()
		var sd=new Date($('#start_date').datebox('getValue')).getTime()
//		var issd = this.id == 'sd', sd = issd ? d : new Date($('#start_date').datebox('getValue')), ed = issd ? new Date($('#end_date').datebox('getValue')) : d;  
		if (ed < sd) {  
			parent.$.messager.alert("提示","结束日期小于开始日期!");  
			$('#end_date').datebox('setValue', '');  
		}  
	}
	
}  

/*重置*/
function listReset() {
	$('#listForm').form('reset');
	$('#listTable').datagrid('load',{type: ''});
}

//接收产品
function editnum() {
    var row = $("#listTable").datagrid("getSelected");
    if (row){
    	callback_editnum(row);
  	  	$("#orderNumber").val(row.inStorageNum);
  	  	$("#receive_number").val("");
     }else{
           $.messager.alert("提示信息", "请先选中一行！","info");
     }
}
function callback_editnum(data){
	$("#select_conetnt").html("检验内容")
	$('#btn_select').combobox('setValues',['1','2']);
	$("#sourcetype").combobox('setValue','3');
	$("#receiving_product").dialog("open");
}

//编辑板名
function btn_edit(){
	var row= $("#listTable").datagrid("getSelected");
	 if (row){
		 $("#edit_border").dialog("open");
		 $("#edit_bordername").val(row.boardNameNew);
	 }else{
		 $.messager.alert("提示信息", "请先选中一行！","info");
	 }
}

