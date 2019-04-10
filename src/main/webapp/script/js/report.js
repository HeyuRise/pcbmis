document.onkeydown=search;	
function search(event) {
	if(event.keyCode == 13) {
		baseSearch();
	}
} 
/*查询*/
function baseSearch(){
	$('#baseTable').datagrid('load',{
		orderNum: $('#orderNum').val(),
		guest: $('#guest').val(),
		boardName: $('#boardName').val(),
		factoryId:$("#factoryId").combobox("getValue"),
		gradeId:$("#gradeId").combobox("getValue"),
		statusId:$("#btn_status").combobox("getValue"),
		makeTimeStart:$("#start_date").datebox('getValue'),
		makeTimeEnd:$("#end_date").datebox('getValue'),
		auditTimeStart:$("#start1_date").datebox('getValue'),
		auditTimeEnd:$("#end1_date").datebox('getValue'),
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
	
	$('#baseTable').datagrid({
		url:'/pcbmis/order/checkReport',
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
			field:'reportNum1',
			width:100,
			align:'center',
			title:'报告单号',
			formatter: function(value,row,index){
				if(row.checkable=="1"){
					return "<a href='"+row.href+"?"+"reportNum="+ row.reportNum+"'>查看</a>";
				}else{
					return "<a href='num-report.html?"+"reportNum="+ row.reportNum+"'>查看</a>";
				}
//				if( (row.status=="待创建" && roles.indexOf('检验员')!=-1 ) || ( row.status=="编辑中" && roles.indexOf('检验员')!=-1)){
//					return "<a href='write-report.html?"+"reportNum="+ row.reportNum+"'>" + row.reportNum +" </a>";
//				}else if((roles.indexOf('审核员')!=-1 && row.status=="待审核" ) || (roles.indexOf('审核员')!=-1 && row.status=="审核中")){
//					return "<a href='review-report.html?"+"reportNum="+ row.reportNum+"'>" + row.reportNum +" </a>";
//				}else if(row.status=="报告已出"){
//					return "<a href='num-report.html?"+"reportNum="+ row.reportNum+"'>" + row.reportNum +" </a>";
//				}
//				else{
//					return "<span>" + row.reportNum +" </span>";
//				}
			}
		},{
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
			field:'productionNumPcs',
			width:120,
			align:'center',
			title:'生产数量PCS'
		},{
			field:'reportMakerName',
			width:100,
			align:'center',
			title:'报告拟制人'
		},{
			field:'makeTime',
			width:120,
			align:'center',
			title:'拟制时间'
		},{
			field:'reportAuditorName',
			width:100,
			align:'center',
			title:'报告审核人'
		},{
			field:'auditDate',
			width:150,
			align:'center',
			title:'审核时间'
		},{
			field:'status',
			width:100,
			align:'center',
			title:'状态'
		}
		]],
		//显示扩展行
		view:detailview,
		detailFormatter:function(index,row){
			return '<div id="baseTable-'+index+'" style="padding:2px 0">板名：<span>'+row.boardName+'</span></div>';
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
//			jQuery("#baseTable").datagrid('fixDetailRowHeight',index);
//		},
		
	})
	
})


//开始小于结束
function onSelect(d) {  
	if($('#start_date').datebox('getValue')!='' && $('#end_date').datebox('getValue')!=''){
		var ed=new Date($('#end_date').datebox('getValue')).getTime()
		var sd=new Date($('#start_date').datebox('getValue')).getTime()
		//var issd = this.id == 'sd', sd = issd ? d : new Date($('#start_date').datebox('getValue')), ed = issd ? new Date($('#end_date').datebox('getValue')) : d;  
		if (ed < sd) {  
			parent.$.messager.alert("提示","结束日期小于开始日期!");  
			$('#end_date').datebox('setValue', '');  
		}  
	}
	
	if($('#start1_date').datebox('getValue')!='' && $('#end1_date').datebox('getValue')!=''){
		var ed1=new Date($('#end1_date').datebox('getValue')).getTime()
		var sd1=new Date($('#start1_date').datebox('getValue')).getTime()
		//var issd = this.id == 'sd', sd = issd ? d : new Date($('#start1_date').datebox('getValue')), ed = issd ? new Date($('#end1_date').datebox('getValue')) : d;  
		if (ed1 < sd1) {  
			parent.$.messager.alert("提示","结束日期小于开始日期!");  
			$('#end1_date').datebox('setValue', '');  
		}  
	}
}  

/*重置*/
function checkRest() {
	$('#baseForm').form('reset');
	$('#baseTable').datagrid('load',{type: ''});
}


/*编辑*/
 function editnum() {
    var row = $("#baseTable").datagrid("getSelected");
    console.info(row);
    if (row) {
          url = "/pcbmis/order/checkReport?id=" + row.reportNum;
          $("#dlg").dialog("open").dialog('setTitle', '修改信息');;
          $("#report").val(row.isReport); 
          $("#fm").form("load", row);

     }else{
           $.messager.alert("提示信息", "请先选中一行！","info");
            }
}

/*保存*/
function confirm(){
	var row = $('#baseTable').datagrid('getSelected');
	 $.ajax({  
	        type: "POST",  
	        url: "/pcbmis/report/editIsReport?reportNum="+row.reportNum,   
	        data:{
	        	isEdit:$("#report option:selected").val(),
	        },
	        success: function (data) {  
	        	if(data.result==0){
	        		console.info(data);
		            $.messager.alert("提示", "编辑信息成功！");  
		            $("#baseTable").datagrid("reload"); 

	        	}
	        	else{
		        
	        		$.messager.alert("提示", data.msg,'error'); 
	        	}
	        }  

	    }) 
	   
	    $('#dlg').dialog('close');
}



