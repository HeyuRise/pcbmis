
/*我的任务--检验员******************************************/
/*待审核列表*/
$(function () {
	$('#reviewList').datagrid({
		url:'/pcbmis/order/preAudit',
		loadMsg:"正在加载数据，请稍等...",	//载入等待时信息
		fit:true,
		fitColumns:true,
		striped:true,
		rownumbers:true,
		singleSelect:true,
		nowrap:false,
		pagination:true,
		pageSize:10,
		pageList:[10,20,30,40,50],
		columns:[[{
			field:'checkNum',
			title:'检验单号',
			width:150,
			align:'center',
			formatter:function(value,row,index){
				return  "<a href='review-num.html?"+"checkNum="+ row.checkNum+"'>审核</a>" 
				
			}
		},{
			field:'orderNum',
			width:150,
			align:'center',
			title:'工单号'
		},{
			field:'boardName',
			width:150,
			align:'center',
			title:'板名'
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
			field:'checkDate',
			width:150,
			align:'center',
			title:'检验日期'
		},{
			field:'inspector',
			width:150,
			align:'center',
			title:'检验员'
		},{
			field:'auditor',
			width:150,
			align:'center',
			title:'审核员'
		},{
			field:'status',
			width:150,
			align:'center',
			title:'状态'
		},
		]],
	});
	$("#checknum").on("input",function(){
		$('#reviewList').datagrid('load',{
			orderNum: $('#checknum').val(),
		});
	})
	
	
	
	
	$('#indexList').datagrid({
		url:'/pcbmis/order/preAuditReport',
		loadMsg:"正在加载数据，请稍等...",	//载入等待时信息
		fit:true,
		fitColumns:true,
		striped:true,
		rownumbers:true,
		singleSelect:true,
		nowrap:false,
		pagination:true,
		pageSize:10,
		pageList:[10,20,30,40,50],
		columns:[[{
			field:'reportNum',
			title:'报告单号',
			width:150,
			align:'center',
			formatter:function(value,row,index){
				return  "<a href='review-report.html?"+"reportNum="+ row.reportNum+"'>审核</a>" 
				
			}
		},{
			field:'orderNum',
			width:150,
			align:'center',
			title:'工单号'
		},{
			field:'boardName',
			width:150,
			align:'center',
			title:'板名'
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
			field:'reportDate',
			width:150,
			align:'center',
			title:'报告拟制日期'
		},{
			field:'reportor',
			width:150,
			align:'center',
			title:'报告拟制人'
		},{
			field:'auditor',
			width:150,
			align:'center',
			title:'审核员'
		},{
			field:'status',
			width:150,
			align:'center',
			title:'状态'
		},
		]],
	});
	$("#reportNum").on("input",function(){
		$('#indexList').datagrid('load',{
			orderNum: $('#reportNum').val(),
		});
	})
	
	$('#test_grid').datagrid({
		url:'/pcbmis/order/getLatelyOperate',
		loadMsg:"正在加载数据，请稍等...",	//载入等待时信息
		title:"最新动态",
		fit:true,
		fitColumns:true,
		striped:true,
		rownumbers:true,
		singleSelect:true,
		nowrap:false,
		pagination:true,
		pageSize:20,
		pageList:[10,20,30,40,50],
		columns:[[{
			field:'operate',
			title:'最新动态',
			width:150,
		},,
		]],
	});
	
	
	
	
})




