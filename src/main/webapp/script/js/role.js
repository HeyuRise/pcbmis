$(function () {
	// /pcbmis/user/roleList 
	$('#roleTable').datagrid({
		url:'/pcbmis/akAuth/roles ',
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
			field:'roleName',
			width:150,
			align:'center',
			title:'角色名称',
		},{
			field:'roleAuth',
			width:150,
			align:'center',
			title:'角色权限'
		},{
			field:'enable',
			width:150,
			align:'center',
			title:'启用/禁用'
		},{
			field:'creatorName',
			width:200,
			align:'center',
			title:'创建人'
		},{
			field:'creatTime',
			width:150,
			align:'center',
			title:'创建时间'
		},{
			field:'updateorName',
			width:150,
			align:'center',
			title:'更新人'
		},{
			field:'updateTime',
			width:150,
			align:'center',
			title:'更新时间',
		}
		]],
		//显示扩展行
		view:detailview,
		detailFormatter:function(index,row){
			return '<div id="roleTable'+index+'" style="padding:2px 0"></div>';
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
			jQuery("#roleTable").datagrid('fixDetailRowHeight',index);
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
		roleSearch();
	}
} 

function roleSearch(){
	$('#roleTable').datagrid('load',{
		keyWord: $('#keyWord').val(),
	});
};




