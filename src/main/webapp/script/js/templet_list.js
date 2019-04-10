/*查询*/
document.onkeydown=search;	
function search(event) {
	if(event.keyCode == 13) {
		listSearch();
	}
}
function listSearch(){ 
	$('#listTable').datagrid('load',{
		name: $('#listname').val(),
	});
}
/*重置*/
function listReset() {
	$('#listForm').form('reset');
	$('#listTable').datagrid('load',{type: ''});
}
$(function () {
	$('#listTable').datagrid({
		url:'/pcbmis/template',
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
			field:'NO',
			width:200,
			align:'center',
			title:'序号',
			formatter: function(value,row,index){
				return "<span>"+(index+1)+"</span>"
			}
		},{
			field:'name',
			width:200,
			align:'center',
			title:'验收标准',
		},{
			field:'id',
			width:150,
			align:'center',
			title:'检验记录表模板',
			formatter: function(value,row,index){
				return "<span><a href='check_review.html?"+"id="+ row.id+"'>查看</a><a  href='check_edit.html?"+"id="+ row.id+"' style='margin-left: 20px;'>编辑</a></span>"
			}
		},{
			field:'order_number',
			width:200,
			align:'center',
			title:'检验报告模板',
			formatter: function(value,row,index){
				return "<span><a href='report_review.html?"+"id="+ row.id+"'>查看</a><a  href='report_edit.html?"+"id="+ row.id+"' style='margin-left: 20px;'>编辑</a></span>"
			}
		},{
			field:'enable',
			width:150,
			align:'center',
			title:'启用/禁用'
		},{
			field:'creator',
			width:250,
			align:'center',
			title:'创建人员',
		},{
			field:'createTime',
			width:100,
			align:'center',
			title:'创建日期',
		},{
			field:'updater',
			width:100,
			align:'center',
			title:'更新人员'
		},{
			field:'updateTime',
			width:120,
			align:'center',
			title:'更新时间'
		}
		]],
		//显示扩展行
		view:detailview,
		detailFormatter:function(index,row){
			return '<div id="listTable-'+index+'" style="padding:2px 0"></div>';
		},
	})

 	
 	$("#addform").dialog({
	    title: '新增',
	    closed: true,
	    modal: true,
	    buttons:[{
	    	text:'保存',
	    	iconCls:'icon-save',
	    	handler:function(){
	    		if($("#fm").form('validate')){
		       	 	$.ajax({  
				        type: "POST",  
				        url: "/pcbmis/template",  
				        data:{
				        	name:$("#addName").val(),
						},
				        success: function (data) {  
				        	if(data.result==0){
					            $.messager.alert("提示", "保存成功！");  
					            $("#addform").dialog("close");
					            $("#listTable").datagrid("reload"); 	
				        	}
				        	else{
				        		$.messager.alert("提示", data.msg,'error'); 
				        	}
				           
				        } 
		       	 	})
	    		}
	    	}
	    },{
	    	text:'取消',
	    	iconCls:'icon-cancel',
	    	handler:function(){
	    		$("#addform").dialog("close");
	    	}
	    }]
	})  
})
//新增
function btn_add() {
	$("#addform").dialog("open")
	$("#fm").form("clear");
}
//启用禁用
function btn_enable(){
  var row = $("#listTable").datagrid("getSelected");
  if(row.enable!="启用"){
	  enable="启用"
  }else{
	  enable="禁用"
  }
  if (row) {
	  $.messager.confirm('确认', '此验收标准确定'+enable+'?', function(r){
			if (r){
				$.ajax({  
			        type: "put",  
			        url: "/pcbmis/template/"+row.id,  
			        success: function (data) {  
			        	if(data.result==0){
				            //$.messager.alert('提示', ''+enable+'成功！');  
				            $("#listTable").datagrid("reload"); 	
			        	}
			        	else{
			        		$.messager.alert("提示", data.msg,'error'); 
			        	}
			           
			        } 
	       	 	})
			}
		});
   }else{
         $.messager.alert("提示信息", "请先选中一行！","info");
   }
}
   
