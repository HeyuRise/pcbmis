function getUrlParam(name)  {  
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象  
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数  
	if (r!=null) return unescape(r[2]); 
	return null; //返回参数值  
} 
/*我的任务--检验员******************************************/
/*待检验列表*/
$(function () {
	var  type=getUrlParam("type")
	if(type!=null){
		
	}else{
		$('#productList').datagrid({
			url:'/pcbmis/order/preCheck',
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
					return  "<a href='test-num.html?"+"checkNum="+ row.checkNum+"'>检验</a>" 
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
				field:'receviceDate',
				width:150,
				align:'center',
				title:'接收日期'
			},{
				field:'checkContent',
				width:150,
				align:'center',
				title:'检验内容'
			},{
				field:'inspector',
				width:150,
				align:'center',
				title:'检验员'
			},{
				field:'status',
				width:150,
				align:'center',
				title:'状态'
			},
			]],
		});
		$("#checknum").on("input",function(){
			$('#productList').datagrid('load',{
				orderNum: $('#checknum').val(),
			});
		})
		
		
		$('#reportList').datagrid({
			url:'/pcbmis/order/preReport',
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
				align:'center',
				width:150,
				formatter:function(value,row,index){
					return  "<a href='write-report.html?"+"reportNum="+ row.reportNum+"'>创建</a>" 
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
				field:'receiveDate',
				width:150,
				align:'center',
				title:'接收日期'
			},{
				field:'reportor',
				width:150,
				align:'center',
				title:'报告拟制人'
			},{
				field:'status',
				width:150,
				align:'center',
				title:'状态'
			},
			]],
		});
		$("#reportNum").on("input",function(){
			$('#reportList').datagrid('load',{
				orderNum: $('#reportNum').val(),
			});
		})

		
		$('#test_grid').datagrid({
			url:'/pcbmis/order/getLatelyOperate',
			loadMsg:"正在加载数据，请稍等...",	//载入等待时信息
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
			},
			]],
		});
	}

})


