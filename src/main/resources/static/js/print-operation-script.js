/**
 *  js文件，用于页面发送ajax请求
 */

	$(function(){
		$.ajax({
			type: "GET",
			url : "/printinfo/getPrintInvoiceAll",//NotG 生成发票页操作
			data: {},
			success: function (data) {
				layer.msg("数据加载......");
				printviewmodel.datalist = data;
				console.log(data);
				$("[name=getvalue]:checkbox").prop("checked", false);
				$("#resultID").html("");
				$("#resultvalu").html("");
			}
		});
	});
	
//定义一个avalonjs的控制器
var printviewmodel = avalon.define({
    //id必须和页面上定义的ms-controller名字相同，否则无法控制页面
    $id: "printviewmodel",
    datalist: {},
    datalistfind: {},
    datalistsearch: {},
    text: "重生成发票",
    request: function () {
    	var  _getpageid=$("#resultID").text();
    	if (_getpageid != "") {
    		$.ajax({
    			type: "post",
    			url : "/printinfo/putPritInvoicesSpecificationID/"+_getpageid+"/IsG/PTFP",//向springboot请求数据的url
    			data: {},
    			success: function (data) {
    				printviewmodel.datalist = data;
    				console.log(data);
    				layer.msg("恭喜您！选择数据已成功生成发票");
    				printviewmodel.text = "生成发票";
			        $("[name=getvalue]:checkbox").prop("checked", false);
					$("#resultID").html("");
    			}
    		});
    		
		}else {
			layer.msg("请选择要生成发票的数据...");
		}
    }
});
//复选框数据获取
function getTDtext() {
	var a = document.getElementsByName("getvalue");// 获取所以复选框
	var _idvalutmp="";
	var _idvalu="";
	// 通过checked属性确定被选中的复选框的父节点，遍历父节点下所以的子节点td，当td的name等于某值时，获取该td下的内容
	for (var i = 0; i < a.length; i++) {
		if (a[i].checked) {
			var tddata = a[i].parentNode.parentNode;
			$(tddata).find("td").each(function() {
				//取ID值 拼成字符串
				if (this.getAttribute("name") == "id") {
					_idvalutmp= this.innerHTML;
					_idvalu= _idvalu +_idvalutmp+",";
					$("#resultID").html(_idvalu);
				}
			});
		}else {
			$("#resultID").html(_idvalu);
		}
	}
}

//删除打印的文件
function deleteprintvalue(obj) {
	var _tdvalue = $(obj).parent().parent().find("td");
	var _id = _tdvalue.eq(1).text()
	$.ajax({
		type: "GET",
		url : "/printinfo/delPrintInvoice/"+_id,
		data: {},
		success: function (data) {
			$("#print_details").show();
			$("#footer_print_details").show();
			
			$("#find_details").hide();
			$("#footer_find_details").hide();
			
			console.log(data);
			layer.msg("恭喜您！选择数据已成功删除!");
	        $("[name=getvalue]:checkbox").prop("checked", false);
	        printviewmodel.datalist = data;
		}
	});
}
//查看打印的文件明细
function findprintvalue(obj) {
	var _tdvalue = $(obj).parent().parent().find("td");
	var _id = _tdvalue.eq(1).text()
	layer.msg("数据加载......");
	$.ajax({
		type: "GET",
		url : "/printinfo/fidPrintInvoice/"+_id,
		data: {},
		success: function (data) {
			$("#find_details").show();
			$("#footer_find_details").show();
			
			$("#print_details").hide();
			$("#footer_print_details").hide();
			
			console.log(data);
			printviewmodel.datalist = data;
		}
	});
}

//重置已开发票数据
function go_reset() {
	layer.msg("数据处理......");
	var  _getpageid=$("#resultID").text();
	if (_getpageid != "") {
		$.ajax({
			type: "post",
			url : "/printinfo/resetPrintInvoice/"+_getpageid,
			data: {},
			success: function (data) {
				printviewmodel.datalist = data;
				console.log(data);
				layer.msg("恭喜您！选择数据已成功生成发票");
				printviewmodel.text = "生成发票";
		        $("[name=getvalue]:checkbox").prop("checked", false);
				$("#resultID").html("");
			}
		});
		
	}else {
		layer.msg("请选择要生成发票的数据...");
	}
}


//复选框数据获取
function getTDtext() {
	var a = document.getElementsByName("getvalue");// 获取所以复选框
	var price =0.00;
    var total_price = 0;
	var _idvalutmp="";
	var _idvalu="";
	// 通过checked属性确定被选中的复选框的父节点，遍历父节点下所以的子节点td，当td的name等于某值时，获取该td下的内容
	for (var i = 0; i < a.length; i++) {
		if (a[i].checked) {
			var tddata = a[i].parentNode.parentNode;
			$(tddata).find("td").each(function() {
				//取ID值 拼成字符串
				if (this.getAttribute("name") == "id") {
					_idvalutmp= this.innerHTML;
					_idvalu= _idvalu +_idvalutmp+",";
				}
				if (this.getAttribute("name") == "hsje") {
					price = parseFloat(this.innerHTML);
					total_price = price+total_price;
					//alert(this.innerHTML +" ----  "+total_price);
					//如果总额超过限定金额(1,000,000) 将当前值不作计算，并ID值也不得增加
					if (total_price > 1160000) {
						layer.msg("当前金额:"+total_price.toFixed(2) +",已超过限定金额(1,160,000)", function(){
							//关闭后的操作
							});
							a[i].checked = false;
							total_price = total_price-price;
							_idvalu =_idvalu.substring(0, _idvalu.lastIndexOf(_idvalutmp))
							return false;
					}
					$("#resultvalu").html(total_price.toFixed(2));
					$("#resultID").html(_idvalu);
				}
			});
		}else {
			$("#resultvalu").html(total_price.toFixed(2));
			$("#resultID").html(_idvalu);
		}
	}
}


//查看明细并操作重新生成发票
function findopration() {
	var _getpageid = $("#resultID").text();
	if (_getpageid != "") {
		$.ajax({
			type : "post",
			url : "/invoicesinfo/putInvoicesSpecificationID/"
					+ _getpageid +"/IsG/PTFP",// IsG 表示已生发票操作类型
			data : {},
			success : function(data) {
				$("#print_details").show();
				$("#footer_print_details").show();
				
				$("#find_details").hide();
				$("#footer_find_details").hide();
				$.ajax({
					type: "GET",
					url : "/printinfo/getPrintInvoiceAll",//NotG 生成发票页操作
					data: {},
					success: function (data) {
						layer.msg("数据加载......");
						printviewmodel.datalist = data;
						console.log(data);
						$("[name=getvalue]:checkbox").prop("checked", false);
						$("#resultID").html("");
						$("#resultvalu").html("");
					}
				});
				layer.msg("恭喜您！您选择的己生成发票数据已重新生成发票成功!");
				getTDtext();
				$("[name=getvalue]:checkbox").prop("checked", false);
				$("#resultID").html("");
				$("#resultvalu").html("");
			}
		});

	} else {
		layer.msg("请选择要重新生成发票的数据...");
	}
}
//返回上一层
function go_back() {
	layer.msg("数据加载......");
	$("#print_details").show();
	$("#footer_print_details").show();
	
	$("#find_details").hide();
	$("#footer_find_details").hide();
	$.ajax({
		type: "GET",
		url : "/printinfo/getPrintInvoiceAll",//NotG 生成发票页操作
		data: {},
		success: function (data) {
			printviewmodel.datalist = data;
			console.log(data);
			$("[name=getvalue]:checkbox").prop("checked", false);
			$("#resultID").html("");
			$("#resultvalu").html("");
		}
	});
}



