


$(function () {
	$("#receiveTypeId").combobox("clear");	
	$('#listTable').datagrid({
		url:'/pcbmis/receive',
		loadMsg:"正在加载数据，请稍等...",
		toolbar:'#tb',
		method:'get',
		fit:true,
		fitColumns:true,
		striped:true,
		singleSelect:true,
		nowrap:false,
		pagination:true,
		pageSize:30,
		pageList:[10,20,30,40,50],
		columns:[[{
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
			field:'grade',
			width:150,
			align:'center',
			title:'等级'
		},{
			field:'size',
			width:250,
			align:'center',
			title:'规格',
		},{
			field:'productNumSET',
			width:100,
			align:'center',
			title:'生产数量SET'
		},{
			field:'productNumPCS',
			width:100,
			align:'center',
			title:'生产数量PCS'
		},{
			field:'content',
			width:120,
			align:'center',
			title:'检验内容'
		},{
			field:'receiveNum',
			width:130,
			align:'center',
			title:'接收数量'
		},{
			field:'receiveType',
			width:130,
			align:'center',
			title:'来料类型',
		},{
			field:'receiveDate',
			width:130,
			align:'center',
			title:'接收日期',
		},{
			field:'receiveTime',
			width:130,
			align:'center',
			title:'接收时间',
		},{
			field:'receiver',
			width:130,
			align:'center',
			title:'接收人',
		}
		]],

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
	    			var row = $("#listTable").datagrid("getSelected");
	    			var jsonstr = "";
	    			jsonstr = JSON.stringify({
	    				contentIds:$("#btn_select").combobox('getValues'),
//	    				inStoragePcs:$("#receive_number").val(),
	    				receiveOrderType:$("#sourcetype").combobox('getValue'),
	    			})
	    			$.ajax({  
	    				 type: "PUT",  
	    				 url: "/pcbmis/receive?receiveId=" + row.id,  
	    				 data:jsonstr,
	    				 contentType: 'application/json', 
	    				 success: function (data) {  
	    					 if(data.result==0){
	    						 $("#receiving_product").dialog("close");
	    						 $('#listTable').datagrid('load',{type: ''});
	    						 $.messager.alert("提示信息", "编辑产品成功！","ok");
	    					 }else{
	    						 $.messager.alert("提示信息", data.msg,"error")
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
})

//开始小于结束
function onSelect(d) {  
	if($('#start_date').datebox('getValue')!='' && $('#end_date').datebox('getValue')!=''){
		var ed=new Date($('#end_date').datebox('getValue')).getTime()
		var sd=new Date($('#start_date').datebox('getValue')).getTime()
		if (ed < sd) {  
			parent.$.messager.alert("提示","结束日期小于开始日期!");  
			$('#end_date').datebox('setValue', '');  
		}  
	}
}  


//接收产品
function editnum() {
    var row = $("#listTable").datagrid("getSelected");
    if (row){
    	$("#orderNumber").val(row.orderNum);
    	$("#receive_number").val(row.receiveNum);
    	$("#btn_select").combobox('setValues',row.contentIds);
    	$("#sourcetype").combobox("setValue",row.receiveTypeId);
  	   	$("#receiving_product").dialog("open");
  	}else{
        $.messager.alert("提示信息", "请先选中一行！","info");
    }
}

/*查询*/
document.onkeydown=search;	
function search(event) {
	if(event.keyCode == 13) {
		checkSearch();
	}
}
function checkSearch(){
	$('#listTable').datagrid('load',{
		orderNum: $('#orderNum').val(),
		guestName: $('#guest').val(),
		contentId: $('#contentId').combobox("getValue"),
		factoryId:$("#factoryId").combobox("getValue"),
		gradeId:$("#gradeId").combobox("getValue"),
		receiveTypeId:$("#receiveTypeId").combobox("getValue"),
		receiveDateStart:$("#start_date").datebox('getValue'),
		receiveDateEnd:$("#end_date").datebox('getValue'),
		productNumSet:$("#productNumSet").val(),
		productNumPcs:$("#productNumPcs").val(),
		receiveNum:$("#receiveNum").val(),
		receiver:$("#receiver").val(),
	});
};

/*重置*/
function checkRest() {
	$('#checkForm').form('reset');
	$("#receiveTypeId").combobox("clear");
	$('#listTable').datagrid('load',{type: ''});
}
function btn_delete(){
	 var row = $('#listTable').datagrid('getSelected');
	 if (row) {
		 $.messager.confirm('Confirm', '你确定删除这条记录吗?', function (r) {
			 if (r) {	
				 $.ajax({  
	           		 type: "DELETE",
	        	     url: '/pcbmis/receive?receiveId='+row.id ,
	        	     contentType: "application/json",
	        	     success: function (data) {
	        	    	 if(data.result==0){
	        	    		 $.messager.alert("提示", "删除信息成功！"); 
	        	    		 $("#listTable").datagrid("reload"); 
	        	         }
	        	    	 else{
        	        		$.messager.alert("提示", data.msg,'error'); 
	        	         }
	        	        }  
	        	   }) ;
	            }
	        });
	 }else{
	    $.messager.alert("提示信息", "请先选中一行！","info");
	}
}