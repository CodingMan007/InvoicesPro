/**
 *  js文件，用于页面发送ajax请求
 */

function initloading(){
	$(function(){
		$.ajax({
			type: "GET",
			url : "/invoicesinfo/getInvoicesIndentAll",//NotG 生成发票页 未开的单号
			data: {},
			success: function (data) {
				console.log(data);
				viewmodel.datalistindent = data;

				layer.msg("数据加载中，请等待......");
				if (data ='') {
					layer.msg("当前没有未生成数据！");
				}else{
					$("[name=getvalue]:checkbox").prop("checked", false);
					$("#resultID").html("");
					$("#resultvalu").html("");
					
					$("#findcustamercode1").show();
					$("#findcustamercode2").hide();
					$("#initpagesdata").show();
					$("#searchpagesdata").hide();
				}
			}
		});
	});
}

$.ajax({
	type: "GET",
	url : "/invoicesinfo/getInvoicesSpecificationAll/NotG",//NotG 生成发票页操作
	data: {},
	success: function (data) {
		viewmodel.datalist = data;
		console.log(data);
		layer.msg("数据加载中，请等待......");
		if (data="") {
			layer.msg("当前没有未生成数据！");
		}else{
	        $("[name=getvalue]:checkbox").prop("checked", false);
			$("#resultID").html("");
			$("#resultvalu").html("");
	
			$("#findcustamercode1").show();
			$("#findcustamercode2").hide();
			$("#initpagesdata").show();
			$("#searchpagesdata").hide();
			
			$a = $(window).height();
		    $("#left").height($a);
		    $("#btn").click(function(){
		        $("#left").animate({left:'-200px'});
		        $("#btnb").delay(500).animate({left:'0'});
		    });
		    $("#btnb").click(function(){
		        $("#btnb").animate({left:'-50px'});
		        $("#left").delay(500).animate({left:'0'});
		    });
		}
	}
});


//客户代码 联想提示
function findcustamercode(){
	var custamercodev= $("#custamercode").val()
	$.ajax({
		type: "GET",
		url : "/invoicesinfo/getCusotmerInfoAll",//向springboot请求已生成发票的数据的url
		data: {},
		success: function (data) {
			console.log(data);
			$("#custamercode ").val("");
			$("#findcustamercode1").hide();
			$("#findcustamercode2").show();
			viewmodel.datalistfind = data;
		}
	});
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
function findcustamercode_additional(_customername_select , _dateselect){
	$.ajax({
		type: "POST",
		url : encodeURI("/invoicesinfo/getcustamercodeAll/"+_customername_select+"/"+_dateselect+"/NotG", "UTF-8"),//向springboot请求已生成发票的数据的url
		data: {},
		success: function (data) {
			console.log(data);
			if(data==''){
        		layer.msg("输入的客户当前没有数据,请检查!");
            }else{
                //做相关的解析处理
            	$("#findcustamercode1").show();
            	$("#searchpagesdata").show();
            	$("#initpagesdata").hide();
            	$("#findcustamercode2").hide();
            	viewmodel.datalistsearch = data;
            	viewmodel.datalist = data;
            }
			
		}
	});
}


//定义一个avalonjs的控制器
var viewmodel = avalon.define({
    //id必须和页面上定义的ms-controller名字相同，否则无法控制页面
    $id: "viewmodel",
    datalist: {},
    datalistfind: {},
    datalistsearch: {},
    datalistindent: {},
    text: "生成发票",
    request: function () {
    	var  _getpageid=$("#resultID").text();
    	//alert(_getpageid);
    	if (_getpageid != "") {
    		$.ajax({
    			type: "post",
    			url : "/invoicesinfo/putInvoicesSpecificationID/"+_getpageid+"/NotG/PTFP",//向springboot请求数据的url
    			data: {},
    			success: function (data) {
    				viewmodel.datalist = data;
    				console.log(data);
    				layer.msg("恭喜您！选择数据已成功生成发票");
    				viewmodel.text = "生成发票";
			        $("[name=getvalue]:checkbox").prop("checked", false);
					$("#resultID").html("");
					$("#resultvalu").html("");

					$("#findcustamercode1").show();
					$("#findcustamercode2").hide();
//					$("#fileField").hide();
					$("#initpagesdata").show();
					$("#searchpagesdata").hide();
					viewmodel.datalistsearch = data;
					
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
					_idvalutmp=this.innerHTML;
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
					$("#resultID").html(_idvalu);
					$("#resultvalu").html(total_price.toFixed(2));
				}
			});
		}else {
			$("#resultID").html(_idvalu);
			$("#resultvalu").html(total_price.toFixed(2));
		}
	}
}
//单号数据获取
function getindentnumberValue(obj) {
	var _frist =$("#resultID").html()
	var indentNumber = $(obj).html();
	var ci = document.getElementsByName("getvalue");// 获取所以复选框
	$(function(){
		$.ajax({
			type: "GET",
			url : "/invoicesinfo/getInvoicesIndentNumber/"+indentNumber,//NotG 生成发票页操作
			data: {},
			success: function (data) {
				if (data != "") {
					viewmodel.datalist = data;
					console.log(data);
					viewmodel.datalistsearch = data;
					for(var i = 0;i < ci.length;i++){
						if(ci[i].checked==true){
							ci[i].checked = false;
						}
					}
					$("#resultID").html(_frist);
				}else{
					layer.msg(indentNumber +" 单号的发票已生成！");
				}
			}
		});
	});
}
//跳转至未生成发票数据  下载
function exportExcel(obj) {
	layer.msg("正在加载未生成发票数据，请稍等...", {
		icon : 1
	});
	window.location.href = "/doExport?failTypeName=db";
}

