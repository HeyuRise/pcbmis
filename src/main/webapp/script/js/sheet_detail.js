
function getUrlParam(name)  {  
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象  
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数  
	if (r!=null) return unescape(r[2]); 
	return null; //返回参数值  
} 

$(function(){
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
	//上传文件
	$("#upload_attchment").dialog({
		title:'上传文件',
	    width:'360',
	    height: '150',
		modal:true,
		closed:true,
		buttons:[{
			text:'确定上传',
			iconCls:'icon-save',
			handler:function(){
                if($("#uploadForm :file").val() == ""){
                    $.messager.alert("操作提示", "请先选择文件");
                    return;
                }
				$("#uploadForm").form('submit',{
					dataType:'json',
					success:function(data){
						data=$.parseJSON(data);
						if(data.result==0){
							$("#upload_attchment").dialog('close');
							 $.messager.alert("操作提示", "成功上传","ok");
							 $('#upload_table').datagrid('load');
						}else{
							 $.messager.alert("操作提示", data.msg,"info");
						}
					}
				})
			}
		},{
			text:'取消上传',
			iconCls:'icon-cancel',
			handler:function(){
				$("#upload_attchment").dialog('close');
			}
		}]
	})
	load_content()
	var check_num=getUrlParam("Num");
	var serialNumber=getUrlParam("serialNumber")
	$.ajax({  
		 type: "GET",  
		 url: "/pcbmis/unqualified?checkNumber="+check_num+"&serialNumber="+serialNumber,  
		 contentType: 'application/json', 
		 success: function (data) {  
			 if(data.result==0){
				load_data(data.content)
			 }
		 }
	})
})
function load_data(content){
	$("#unqualifiedId").html(content.unqualifiedId)
	$("#serialNumber").html(content.serialNumber);
	$("#revision").html(content.revision);
	$("#documentNumber").html(content.documentNumber);
	$("#orderNumber").html(content.orderNumber);
	$("#boardName").html(content.boardName);
	$("#storage_number").html(content.storage_number);
	$("#BoardNo").html(content.unqualifiedBatch);
	$("#unqualifiedNumber").html(content.unqualifiedNumber);
	$("#factoryName").html(content.factoryName);
	$("#describe").html(content.unqualifiedDesc);
	$("#inspector").html(content.inspector);
	$("#submitTime").html(format(content.submitTime,"yyyyMMddhhmmss"));
	$("input[name='grade'][value="+content.productLevel+"]").attr("checked",true); 
	$("input[name='source'][value="+content.unqualifiedSource+"]").attr("checked",true); 
	$("input[name='genre'][value="+content.productType+"]").attr("checked",true); 
	$("#approval").html(content.approval_personnel)
	for (var i=0;i<content.images.length;i++){
		var html='<li style="width: 145px;min-height: 145px;margin-left:5px;list-style: none;display: inline-block;vertical-align: middle;"><img src="/pcbmis/file/image/'+content.images[i].fileId+'" style="width: 100%;"></li>'
		$("#as").append(html)
	}
	$("#opinions").html(content.adjudicating_opinions);
	var disposalMethod=eval("("+content.disposal_method+")")
	for(var i=0;i<disposalMethod.length;i++){  
         $("input[name='Fruit']").each(function(){  
             if($(this).val()==disposalMethod[i]){  
                 $(this).attr("checked","checked");  
             }  
         })  
     }  
	$("#tria").html(content.trial_personnel)
	$("#start_date").html(format(content.trial_time,"yyyyMMdd"))
	$("#end_date").html(format(content.approval_time,"yyyyMMdd"))
	
	$("#uploadForm").attr("action","/pcbmis/file/upload/file?unqualifiedId="+content.unqualifiedId+"")
	$('#upload_table').datagrid({
		url:'/pcbmis/unqualified/hear/file?unqualifiedId='+$("#unqualifiedId").html(),
		toolbar:'#tb',
		loadMsg:"正在加载数据，请稍等...",
		fit:true,
		fitColumns:true,
		striped:true,
		singleSelect:true,
		nowrap:false,
		method:'get',
//		pagination:true,
//		pageSize:20,
//		pageList:[10,20,30,40,50],
		columns:[[{
			field:'name',
			width:220,
			align:'center',
			title:'名称',
		},{
			field:'create_time',
			width:200,
			align:'center',
			title:'上传时间',
			formatter: function(value,row,index){
				if(row.create_time!=null){
					return "<span>"+format(row.create_time,"yyyyMMdd")+"</span>";
				}
			}
		},{
			field:'username',
			width:150,
			align:'center',
			title:'上传人员'
		}
		]],
	})
}

function format(time, format) {
	var d = "";
	var date = new Date(time);
	if (format == "yyyyMMdd") {
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var day = date.getDate();
			d=year + '-' + p(month) + '-' + p(day);	
		} else if (format == "yyyyMMddhhmmss") {
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var day = date.getDate();
			var Hour = date.getHours();      //获取当前小时数(0-23)
			var Minute = date.getMinutes();   // 获取当前分钟数(0-59)
			var Sec =date.getSeconds();      //获取当前秒数(0-59)
			d=year + '-' + p(month) + '-' + p(day)+' '+p(Hour)+':'+p(Minute)+':'+p(Sec);	       
	    }
	    return d;

	}
function p(s) {
    return s < 10 ? '0' + s: s;
}



function load_content(){
	$.ajax({  
		 type: "GET",  
		 url: "/pcbmis/util/productLevel",  
		 contentType: 'application/json', 
		 success: function (data) {  
			load_garde(data)
		 }
	})
}

function load_garde(data){
	for(var i=0;i<data.length;i++){
		var html='<label class="select_label"><input type="radio" disabled="disabled" name="grade" value="'+data[i].index+'" style="width: 15px;">'+data[i].desc+'</label>'
		$("#grade_content").append(html)
	}
}


//上传
function btn_upload(){
	$("#uploadForm").form('clear');
	$("#upload_attchment").dialog('open');
}

//下载
function btn_download(){
	var row=$("#upload_table").datagrid("getSelected");
	if($(".datagrid-btable tr").length==0){
		$.messager.alert("提示信息", "请先上传文件！","info");
	}else{
		if(row){
			$("#daownload_conetnt").form('submit',{
				url:'/pcbmis/file/'+row.id+'',
				dataType:'json',
				success:function(data) {
					if (!data.result) {
						$.messager.alert('提示', data.msg, 'error');
					}
				}
			});
		}else{
			$.messager.alert("提示信息", "请选择所需要下载的文件！","info");
		}
	}
}

//删除
function btn_delete(){
	var row=$("#upload_table").datagrid("getSelected");
	if(null!=row){
		$.messager.confirm("确认", "您确认删除此文件吗？",function(r){
			if(r){
				$.ajax({  
					type: "DELETE",
					url: '/pcbmis/file/'+row.id+'',
					contentType: "application/json",
					success: function (data) {
						if(data.result==0){
							$.messager.alert("提示", "删除信息成功！"); 
							$("#upload_table").datagrid("reload"); 
						}
						else{
							$.messager.alert("提示", data.msg,'error'); 
						}
					}  
				}) ;
			}
		});
	}else{
		$.messager.alert("提示信息", "请选择所需要删除的文件！","info");
	}
}


//导出
function btn_export() {
	var loc = $("#unqualifiedId").html();
	$("#export_excel").form('submit',{
		url:'/pcbmis/unqualified/export',
		onSubmit:function(param) {
			param.unqualifiedId=loc;
			param.status=3;
		},
		dataType:'json',
		success:function(data) {
			if (!data.result) {
			     $.messager.alert('提示', data.msg, 'error');
			}
		}
	});
}

