$(function () {
	$('#listTable').datagrid({
		url:'/pcbmis/default-person',
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
			field:'name',
			width:200,
			align:'center',
			title:'表单名称',
		},{
			field:'fieldName',
			width:150,
			align:'center',
			title:'字段名称',
		},{
			field:'defaultName',
			width:200,
			align:'center',
			title:'默认人名',
		}
		]],
		//显示扩展行
		view:detailview,
		detailFormatter:function(index,row){
			return '<div id="listTable-'+index+'" style="padding:2px 0"></div>';
		},
	})

 	
 	$("#addform").dialog({
	    title: '编辑',
	    closed: true,
	    modal: true,
	    buttons:[{
	    	text:'保存',
	    	iconCls:'icon-save',
	    	handler:function(){
	    		if($("#fm").form('validate')){
	    			var valueField = $("#userCode").combobox("options").valueField;
    	            var val = $("#userCode").combobox("getValue");  //当前combobox的值
    	            var allData = $("#userCode").combobox("getData");   //获取combobox所有数据
    	            var result = true;      //为true说明输入的值在下拉框数据中不存在
    	            for (var i = 0; i < allData.length; i++) {
    	                if (val == allData[i][valueField]) {
    	                    result = false;
    	                }
    	            }
    	            if (result) {
    	                $("#userCode").combobox("clear");
    	                $.messager.alert("提示", "请从默认人员下拉框中选择",'error'); 
    	                return;
    	            }      
	    	            
		       	 	$.ajax({  
				        type: "PUT",  
				        url: "/pcbmis/default-person/"+$("#id").val()+"?userCode="+$("#userCode").combobox('getValue'),  
				        success: function (data) {  
				        	if(data.result==0){
					            $.messager.alert("提示", "编辑成功！");  
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
//新增
function btn_edit() {
	var row = $("#listTable").datagrid("getSelected");
	if(row){
		$("#addform").dialog("open");
		$("#fm").form("clear");
		$("#userCode").combobox('setValue', row.userCode);
		$("#id").val(row.id);
	}else{
		$.messager.alert("提示信息", "请先选中一行！","info");
	}

}

