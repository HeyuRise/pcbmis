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
	
	
	$('#JournalTable').datagrid({
		url:'/pcbmis/user/getLogList ',
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
			field:'id',
			width:150,
			align:'center',
			title:'日志ID',
		},{
			field:'username',
			width:150,
			align:'center',
			title:'操作用户'
		},{
			field:'operateName',
			width:150,
			align:'center',
			title:'操作名称'
		},{
			field:'operateContent',
			width:200,
			align:'center',
			title:'操作内容'
		},{
			field:'operateTime',
			width:150,
			align:'center',
			title:'操作时间'
		},{
			field:'operateResult',
			width:150,
			align:'center',
			title:'操作结果'
		},{
			field:'exceptionMessage ',
			width:150,
			align:'center',
			title:'异常信息',
		}
		]],
		//显示扩展行
		view:detailview,
		detailFormatter:function(index,row){
			return '<div id="JournalTable'+index+'" style="padding:2px 0"></div>';
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
			jQuery("#JournalTable").datagrid('fixDetailRowHeight',index);
		},
	})
	
})


/*查询*/
document.onkeydown=search;	
function search(event) {
	if(event.keyCode == 13) {
		JournalSearch();
	}
} 
function JournalSearch(){
	$('#JournalTable').datagrid('load',{
		keyWord: $('#keyWord').val(),
		startTime: $('#startTime').datebox('getValue'),
		stopTime: $('#stopTime').datebox('getValue'),
	});
};


//开始小于结束
function onSelect(d) {  
	if($('#startTime').datebox('getValue')!='' && $('#stopTime').datebox('getValue')!=''){
		var ed=new Date($('#stopTime').datebox('getValue')).getTime()
		var sd=new Date($('#startTime').datebox('getValue')).getTime()
//		var issd = this.id == 'sd', sd = issd ? d : new Date($('#start_date').datebox('getValue')), ed = issd ? new Date($('#end_date').datebox('getValue')) : d;  
		if (ed < sd) {  
			parent.$.messager.alert("提示","结束日期小于开始日期!");  
			$('#stopTime').datebox('setValue', '');  
		}  
	}
	
}  

