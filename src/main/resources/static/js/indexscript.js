/**
 *  js文件，用于页面发送ajax请求
 */

// 刷新Excel导入页面
function refresh() {
	var index = layer.open({
		 title: [
		      '导入发票数据',
		      'background-color: #cd4351; color:#fff;'
		    ],
		type : 2,
		content : '/pages/toImport',
		area : [ '710px', '490px' ],
		maxmin: true,
		end : function() {
			layer.msg("正在查看导入数据，请稍等...", {
				icon : 1
			});
		}
	});
	layer.restore(index);
	
}

//跳转至未生成发票操作页面
function toInvoicesOperationHtml() {
	var index = layer.open({
		 title: [
		      '未生成票据数据',
		      'background-color: #cd4351; color:#fff;'
		    ],
		type : 2,
		content : '/pages/toOperation',
		area : [ '1233px', '550px' ],
		maxmin: true,
		end : function() {
			layer.msg("正在查看导入数据，请稍等...", {
				icon : 1
			});
		}
	});
	layer.restore(index);
}

// 跳转至已生成票据页面
function toGenerateInvoicesOperationHtml() {
	layer.msg("正在加载已生成发票数据模板，请稍等...");
	var index = layer.open({
		 title: [
		      '已生成票',
		      'background-color: #cd4351; color:#fff;'
		    ],
		type : 2,
		content : '/pages/toGenerate',
		area : [ '1230px', '550px' ],
		maxmin: true,
		end : function() {
			layer.msg("正在查看导入数据，请稍等...", {
				icon : 1
			});
		}
	});
	layer.restore(index);
	this.getTDtext();
	$("#resultID").html("");
	$("#resultvalu").html("");
}

//跳转至生成发票操作页面
function toPrintOperationHtml() {
	var index = layer.open({
		 title: [
		      '发票打印状态',
		      'background-color: #cd4351; color:#fff;'
		    ],
		type : 2,
		content : '/pages/toPrintOperation',
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
//跳转至清理已开发票的所有数据操作页面
function toInvoicesDeleteOperationHtml() {
	layer.confirm('您是否真的需要清理原来原始数据吗？', {
		icon : 3,
		time : 10000, //20s后自动关闭
		btn : [ '所有数据', '已开票数据', '取消' ]
	}, function() {
		$.ajax({
			type: "get",
			url : "/invoicesinfo/delInvoicesDeleteAll",//向springboot请求已生成发票的数据的url
			data: {},
			success: function (data) {
					layer.msg("已清理完所有数据", {
						icon : 1
					});
			}
		});
	}, function() {
		$.ajax({
			type: "get",
			url : "/invoicesinfo/delInvoicesDeleteGenerate",//向springboot请求已生成发票的数据的url
			data: {},
			success: function (data) {
					layer.msg("已清理完所有已开票数据", {
						icon : 1
					});
			}
		});
	}, function() {
		layer.msg('您在仔细想想...', {
			icon : 5
		});
	});
}
//跳转至清理已开发票的所有数据操作页面
function toInvoicesDeleteAllOperationHtml() {
	
}
//跳转至清理已开发票的所有数据操作页面
function toInvoicesDeleteGenerateOperationHtml() {
	
}







$(function(){
	$(window).resize(function(){
		var cliWidth = document.body.clientWidth;//浏览器宽
		var cliHeight = document.body.clientHeight;//浏览器高
		
		var divWidth = cliWidth - 2;
		var divHeight = cliHeight - 2;
			$('#div1').css("Width",divWidth+"px");
			$('#div1').css("divHeight",divHeight+"px");
		
	});
});

window.onresize = function (){
	var w=window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
	var h=window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
	document .getElementById("div1").style.width = w+"px";
	document .getElementById("div1").style.height = h+"px";
	
}



