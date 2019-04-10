$(function () {
	// '/pcbmis/user/userList '
	$('#userTable').datagrid({
		url:'/pcbmis/akAuth/users ',
		method:'GET',
		toolbar:'#tb',
		loadMsg:"正在加载数据，请稍等...",
		rownumbers:true,
		fit:true,
		fitColumns:true,
		striped:true,
		singleSelect:true,
		nowrap:false,
		pagination:true,
		pageSize:30,
		pageList:[10,20,30,40,50],
		columns:[[{
			field:'account',
			width:150,
			align:'center',
			title:'用户名',
		},{
			field:'userName',
			width:150,
			align:'center',
			title:'姓名'
		},{
			field:'department',
			width:150,
			align:'center',
			title:'部门'
		},{
			field:'role',
			width:200,
			align:'center',
			title:'已赋角色'
		},{
			field:'enable',
			width:150,
			align:'center',
			title:'启用/禁用'
		}
		]],
		//显示扩展行
		view:detailview,
		detailFormatter:function(index,row){
			return '<div id="userTable-'+index+'" style="padding:2px 0"></div>';
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
			jQuery("#userTable").datagrid('fixDetailRowHeight',index);
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
})


/*查询*/
document.onkeydown=search;	
function search(event) {
	if(event.keyCode == 13) {
		userSearch();
	}
} 

function userSearch(){
	$('#userTable').datagrid('load',{
		keyWord: $('#keyWord').val(),
	});
};



/*编辑*/
 function editnum() {
    var row = $("#userTable").datagrid("getSelected");
    console.info(row);
    if (row) {
          url = "/pcbmis/user/userList?id=" + row.account;
          $("#dlg").dialog("open").dialog('setTitle', '修改信息');
          $("#userName").val(row.userName); 
          $("#roleName").val(row.role);
          $("#enable").val(row.enable);
          $("#role").combobox('clear','none');
          $("#role").combobox('setText',row.role);
     }else{
           $.messager.alert("提示信息", "请先选中一行！","info");
     }
}

 
/*保存*/
function saveuser(){
	 var row = $("#userTable").datagrid("getSelected");
	 if (row){
			var account=row.account;
		}
	 
	 var jsonstr = "";
	   	 jsonstr = JSON.stringify({
	   		enable:$("#enable").val(),
	   		userRoleId:$("#role").combobox("getValues"),
	  })
	 if($('#role').combobox('getText') == "" ||  $("#enable option:selected").text()==""){
			$.messager.alert('提示',"信息填写不完整",'info');
	 }else{
		 $.ajax({  
		        type: "PUT",  
		        url: "/pcbmis/akAuth/user?account="+account,  // "/pcbmis/user/editUser?account="+account
		        data:jsonstr,
		        contentType: "application/json",
		        success: function (data) {  
	   	        	if(data.result==0){
	   	   	            console.info(data)
	   	   	            $.messager.alert("提示", "保存成功！");  
	   	   	            $("#userTable").datagrid("reload");

	   	        	}else{
	   	        		$.messager.alert("提示", data.msg,'error'); 

	   	        	}
	   	        	 
		         
		        }  

		    }) 
		    $('#dlg').dialog('close');
	 }
}

