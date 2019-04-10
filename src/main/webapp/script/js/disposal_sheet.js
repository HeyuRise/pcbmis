$(function(){
	$("#BoardNo").validatebox({
		required:'true',
	})
	$("#describe").validatebox({
		required:'true',
	})
	load_content()
	var check_num=getUrlParam("checkNum");
	var serialNumber=""
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
	$("#BoardNo").val(content.unqualifiedBatch);
	$("#unqualifiedNumber").html(content.unqualifiedNumber);
	$("#factoryName").html(content.factoryName);
	$("#describe").val(content.unqualifiedDesc);
	$("#inspector").html(content.inspector);
	//$("#submitTime").html(content.submitTime);
	$("input[name='grade'][value="+content.productLevel+"]").attr("checked",true); 
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
		var html='<label class="select_label"><input type="radio" name="grade" value="'+data[i].index+'" style="width: 15px;">'+data[i].desc+'</label>'
		$("#grade_content").append(html)
	}
}


function getUrlParam(name)  {  
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象  
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数  
	if (r!=null) return unescape(r[2]); 
	return null; //返回参数值  
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



function btn_save(){
	if($("#submission_confirmation_form").form("validate")){
		if($("#filePicker2").next('div').is(".state-ready")){
			 $.messager.alert("操作提示", "请先上传图片","info");
		}else{
			var  jsonstr=""
			var images=[]
			for(var i=0;i<$("#Gallery li").length;i++){
				images[i]=$($("#Gallery li")[i]).attr("index")
			}
			var timestamp=new Date().getTime();
			var submitTime=format(timestamp,"yyyyMMddhhmmss")
			jsonstr = JSON.stringify({
			   unqualifiedId:$("#unqualifiedId").html(),
			   orderNumber:$("#orderNumber").html(),
			   unqualifiedDesc:$("#describe").val(),
			   inspector:$("#inspector").html(),
			   unqualifiedSource:$("input[name='source']:checked").val(),
			   submitTime:submitTime,
			   productType:$("input[name='genre']:checked").val(),
			   productLevel:$("input[name='grade']:checked").val(),
			   images:images,
			   unqualifiedBatch:$("#BoardNo").val(),
		
			})
			$.ajax({  
				 type: "PUT",  
				 url: "/pcbmis/unqualified",  
				 data:jsonstr,
				 contentType: 'application/json', 
				 success: function (data) {  
					 if(data.result==0){
						 $.messager.alert("操作提示", "提交成功",'ok',function(){
							 var checkNum=getUrlParam("checkNum")
							 var serialNumber=""
							 window.location.href="disposal_sheet_detail.html?Num="+checkNum+"&serialNumber="+serialNumber
						 });
					 }else{
						 $.messager.alert("提示信息", data.msg,"error");
					 }
				 }
			})

		}
	}

}



function btn_cancel(){
	window.history.back();
//	var checkNum=getUrlParam("checkNum")
//	window.location = "num.html?bad=1&checkNum="+checkNum;
}