/*查询*/
document.onkeydown=search;	
function search(event) {
	if(event.keyCode == 13) {
		listSearch();
	}
}
function listSearch(){
	$('#listTable').datagrid('load',{
		list_gong: $('#list_gong').val(),
		list_customer: $('#list_customer').val(),
		factoryId: $('#factoryId').combobox("getValue"),
	});
}


$(function () {
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
	
	$('#listTable').datagrid({
		url:'/pcbmis/order/orderList',
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
			field:'boardName',
			width:200,
			align:'center',
			title:'板名'
		},{
			field:'factoryName',
			width:150,
			align:'center',
			title:'生产厂家'
		},{
			field:'categoryGrade',
			width:150,
			align:'center',
			title:'等级'
		},{
			field:'orderNum1 ',
			width:250,
			align:'center',
			title:'规格',
			formatter:function(val,rec){
				var length=rec.boardLong 
				var f="*"
				var wide=rec.boardWide 
				var boardPly=rec.boardPly
 				var html=""
				html=length+f+wide+f+boardPly;
				return html; 
			}
		},{
			field:'metallographyRequire',
			width:100,
			align:'center',
			title:'金相要求'
		},{
			field:'impedanceRequire',
			width:100,
			align:'center',
			title:'阻抗要求'
		},{
			field:'productionNumSet',
			width:120,
			align:'center',
			title:'生产数量SET'
		},{
			field:'productionNumPcs',
			width:130,
			align:'center',
			title:'生产数量PCS'
		},{
			field:'orderDate',
			width:150,
			align:'center',
			title:'投产日期'
		},{
			field:'surfaceProcessName',
			width:150,
			align:'center',
			title:'表面处理'
		},{
			field:'deliveryMode',
			width:150,
			align:'center',
			title:'出货方式'
		},{
			field:'joinBoardWayName',
			width:150,
			align:'center',
			title:'拼板方式'
		},{
			field:'belongCompanyName',
			width:150,
			align:'center',
			title:'所属公司'
		}
		]],
		//显示扩展行
		view:detailview,
		detailFormatter:function(index,row){
			return '<div id="listTable-'+index+'" style="padding:2px 0"></div>';
		},
		onExpandRow: function(index,row){
			var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
			ddv.panel({
				border:false,
				cache:false,
				content:function(){
					var content="";
				},
			});
			jQuery("#listTable").datagrid('fixDetailRowHeight',index);
		},
		

	})
	
	
 	$("#editdialog").dialog({
	    title: '编辑',
	    width:'340',
	    height: 'auto',
	    closed: true,
	    modal: true,
	    buttons:[{
	    	text:'保存',
	    	iconCls:'icon-save',
	    	handler:function(){
	    		if($("#edit_form").form("validate")){
	    			var orderNumber=$("#orderNumber").val()
	    			var jsonstr = "";
	    			jsonstr = JSON.stringify({
	    				
	    				
	    			})
	    			$.ajax({  
	    				 type: "POST",  
	    				 url: "/pcbmis/inStorage/product?orderNumber=" + orderNumber,  
	    				 data:jsonstr,
	    				 contentType: 'application/json', 
	    				 success: function (data) {  
	    					 if(data.result==0){
	    						 $("#editdialog").dialog("close");
	    						 $('#listTable').datagrid('load',{type: ''});
	    						 $.messager.alert("提示信息", "编辑成功！","ok");
	    					 }else{
	    						 $.messager.alert("提示信息", data.msg,"error",function(){
	    							 $("#editdialog").dialog("close");
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
	    		 $("#editdialog").dialog("close");
	    	}
	    }]
	}) 
})


//编辑
function btn_edit() {
    var row = $("#listTable").datagrid("getSelected");
    if (row){
    	$("#orderNumber").val(row.orderNum);
    	$("#edit_boardname").val(row.boardName1);
  	   	$("#editdialog").dialog("open");
  	}else{
        $.messager.alert("提示信息", "请先选中一行！","info");
    }
}


/*重置*/
function listReset() {
	$('#listForm').form('reset');
	$('#listTable').datagrid('load',{type: ''});
}
