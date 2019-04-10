/*查询*/
document.onkeydown=search;	
function search(event) {
	if(event.keyCode == 13) {
		listSearch();
	}
}
function listSearch(){
	$('#listTable').datagrid('load',{
		orderNum: $('#orderNum').val(),
		guestName: $('#guestName').val(),
		boardName: $('#boardName').val(),
		inAmountNum:$("#inAmountNum").val(),
		inStorageDateStart:$("#start_date").datebox('getValue'),
		inStorageDateEnd:$("#end_date").datebox('getValue'),
		factoryId:$("#factoryId").combobox("getValue"),
		gradeId:$("#gradeId").combobox("getValue"),
	
	});
}


$(function () {
	$('#listTable').datagrid({
		url:'/pcbmis/inStorage',
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
		columns:[[{
			field:'inStorageNum',
			width:200,
			align:'center',
			title:'入库单号'
		},{
			field:'productOrderNum',
			width:200,
			align:'center',
			title:'工单号'
		},{
			field:'boardName',
			align:'center',
			title:'原板名',
			formatter:function(value){
				return '<div style="overflow: hidden;text-overflow:ellipsis;white-space: nowrap;width:200px">'+value+'</div>'
			}
		},{
			field:'boardNameNew',
			align:'center',
			title:'现板名',
			formatter:function(value){
				return '<div style="overflow: hidden;text-overflow:ellipsis;white-space: nowrap;width:200px">'+value+'</div>'
			}
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
			field:'grade',
			width:150,
			align:'center',
			title:'等级'
		},{
			field:'boardSize',
			width:250,
			align:'center',
			title:'规格',
		},{
			field:'productNumSet',
			width:100,
			align:'center',
			title:'生产数量SET'
		},{
			field:'productNumPCS',
			width:100,
			align:'center',
			title:'生产数量PCS'
		},{
			field:'inAmountPCS',
			width:120,
			align:'center',
			title:'入库数量PCS'
		},{
			field:'inStorageDate',
			width:130,
			align:'center',
			title:'入库日期'
		}
		]],
		//显示扩展行
		view:detailview,
		detailFormatter:function(index,row){
			return '<div id="listTable-'+index+'" style="padding:2px 0"><div>原板名：<span>'+row.boardName+'</span></div><div>现板名：<span>'+row.boardNameNew+'</span></div></div>';
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

