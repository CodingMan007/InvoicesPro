/**
 *  js文件，用于页面发送ajax请求
 */
	$(function(){
		//layer.msg("数据加载中，请稍等...");
		$.ajax({
			type: "get",
			url : "/invoicesinfo/getInvoicesSpecificationAll/IsG",//向springboot请求已生成发票的数据的url
			data: {},
			success: function (data) {
				importviewmodel.datalist = data;
			}
		});
	});
	
// 检测未被消费的数据大小
function checkUndoSize() {
	layer.load();
	$.ajax({
		url : "/getUndoSize",
		type : "GET",
		success : function(data) {
			if (data.code == 0) {
				var result = data.data;
				if (result > 0) {//继续轮询
					layer.closeAll();
					layer.msg("当前仍有" + result + "条数据未处理，请稍等...");
					setTimeout(function() {
						checkUndoSize();
					}, 1000);
				} else {
					layer.closeAll('loading'); //关闭loading
					layer.confirm("数据同步已完成，是否立即查看结果？", {
						icon : 1,
						btn : [ "立即查看", "稍等" ]
					}, function() {
						layer.closeAll();
						toResultHtml();
					}, function() {
						layer.msg("五秒后再次提醒...", {
							icon : 6
						});
						setTimeout(function() {
							checkUndoSize();
						}, 5000);
					});
				}
			} else {
				layer.msg(data.msg);
			}
		},
		error : function() {
			layer.msg("客户端请求有误");
		}
	});
}

// 跳转至同步结果页面
function toResultHtml() {
	var index = layer.open({
		type : 2,
		content : '/toResult',
		area : [ '720px', '450px' ],
		//maxmin: true
		end : function() {
			layer.msg("正在查看导入数据，请稍等...", {
				icon : 1
			});
		}
	});
	layer.restore(index);
}

// 刷新Excel导入页面
function refresh() {
	window.location.href = "/toImport";
}

//跳转至生成发票操作页面
function toInvoicesOperationHtml() {
	var index = layer.open({
		 title: [
		      '未生成票据数据',
		      'background-color: #cd4351; color:#fff;'
		    ],
		type : 2,
		content : '/toOperation',
		area : [ '1240px', '560px' ],
		maxmin: true,
		end : function() {
			layer.msg("正在查看导入数据，请稍等...", {
				icon : 1
			});
		}
	});
	layer.restore(index);
}

// 跳转至生成发票操作页面
function toPrintOperationHtml() {
	var index = layer.open({
		 title: [
		      '未生成票据数据',
		      'background-color: #cd4351; color:#fff;'
		    ],
		type : 2,
		content : '/toPrintOperation',
		area : [ '1230px', '550px' ],
		maxmin: true,
		end : function() {
			layer.msg("正在查看导入数据，请稍等...", {
				icon : 1
			});
		}
	});
	layer.restore(index);
}
// 跳转至查看票据页面
function exportExcelDemo() {
	layer.msg("正在加载已生成发票数据模板，请稍等...", {
		icon : 1
	});
	this.getTDtext();
	$("#resultID").html("");
	$("#resultvalu").html("");
	$("#fileField").hide();
	$("#fileFieldgrid").show();
//	$("#fileFieldinvoices").show();
	//window.location.href = "/doExport?failTypeName=excelDemo";
}

//客户代码 联想提示 选择了客户信息就更新对应的客户数据
function findcustamerbyname(){
	var _customername_select = $("#customercode_select").find("option:selected").text();
	$("#custamercode").val(_customername_select);
	var _dateselect = $("#dateselect").val() ;//日期范围
	if (_dateselect == "") {
		_dateselect = 0;
	}
	this.findcustamercode_additional(_customername_select,_dateselect);
}

//查找 按不同的条件查找
function search(){
	var _custamercode = $("#custamercode").val();
	var _customername_select = $("#customercode_select").find("option:selected").text();
	var _dateselect = $("#dateselect").val() ;//日期范围
	if (_custamercode == "" && _dateselect == ""){
		layer.msg("请输入正确的查找条件!");
		return false;
	}if (_custamercode == "" && _dateselect != ""){
		this.findcustamercode_additional(0,_dateselect);
	}
	if (_dateselect == "") {
		return false;
	}
	if (_customername_select == "") {
		_customername_select = 0;
	}
	if (_customername_select != ""  && _dateselect != "") {
		this.findcustamercode_additional(_customername_select,_dateselect);
	}
	
}

//查找方法要 按不同的条件查找
function findcustamercode_additional(_customercode_select , _dateselect){
	$.ajax({
		type: "POST",
		url : "/invoicesinfo/getcustamercodeAll/"+_customercode_select+"/"+_dateselect+"/IsG",//向springboot请求已生成发票的数据的url
		data: {},
		success: function (data) {
			
			console.log(data);
			if(data==''){
        		layer.msg("输入的客户当前没有数据,请检查!");
            }else{
                //做相关的解析处理
            	$("#findcustamercode1").show();
            	$("#fileField").hide();
            	$("#findcustamercode2").hide();
            	importviewmodel.datalist = data;
            }
		}
	});
}

//客户代码联想提示
function findcustamercode(){
	var custamercodev= $("#custamercode").val()
	$.ajax({
		type: "GET",
		url : "/invoicesinfo/getCusotmerInfoAll",//向springboot请求已生成发票的数据的url
		data: {},
		success: function (data) {
			console.log(data);
			$("#custamercode ").val("");

			$("#fileField").hide();
			$("#findcustamercode1").hide();
			$("#findcustamercode2").show();
			importviewmodel.datalistfind = data;
		}
	});
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




