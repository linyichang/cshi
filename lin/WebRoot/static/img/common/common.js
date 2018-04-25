Date.prototype.pattern = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, // 小时
		"H+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	var week = {
		"0" : "/u65e5",
		"1" : "/u4e00",
		"2" : "/u4e8c",
		"3" : "/u4e09",
		"4" : "/u56db",
		"5" : "/u4e94",
		"6" : "/u516d"
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	if (/(E+)/.test(fmt)) {
		fmt = fmt
				.replace(
						RegExp.$1,
						((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f"
								: "/u5468")
								: "")
								+ week[this.getDay() + ""]);
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
}

/**
 * 开发人员：林义昌 创建时间：2017-11-8 时间格式转换
 */
function dateType() {
	var val = document.getElementById("date");
	if (val.value != "") {
		var date = new Date(val.value);
		val.value = date.pattern("yyyy-MM-dd hh:mm:ss");
	}
}

/**
 * 时间标签
 */
layui
		.use(
				[ 'layedit', 'laydate' ],
				function() {
					var form = layui.form, layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;
					// 日期
					// 时间选择器
					laydate.render({
						elem : '#date',
						type : 'datetime'
					});
				});
/**
 * 删除信息
 * 
 * @param url
 */
function deleteDate(url, context) {
	var checkStatus = layui.table.checkStatus('TablReload');
	if (checkStatus.data < 1) {
		layer.alert("对不起！你没有选择数据！");
		return;
	}
	layer.confirm(context, {
		btn : [ 'YES', 'NO' ]
	// 按钮
	}, function() {
		ids = "";
		for (var i = 0; i < checkStatus.data.length; i++) {
			ids += checkStatus.data[i].id + ",";
		}
		window.location.href = url + "?ids=" + ids;
	}, function() {

	});
}

function save(msg, obj) {
	layer.confirm(msg, {
		btn : [ 'YES', 'NO' ]
	// 按钮
	}, function() {
		$(".layui-form").submit();
	}, function() {
		return;
	});
}

/**
 * 构建控件
 */
var layers = {
	alert : null,
	createTips : null,
	openWindow : null,
    openLayer : null
};

/**
 * 实现控件方法
 */
layui.use('layer', function() {
	var layer = layui.layer;
	layers.openLayer = function(obj){
		alert("ok")
	}
	
	
	/**
	 * 弹框提示
	 */
	layers.alert = function(context) {
		layer.alert(context);
	};
	
	/**
	 * 创建一个标签提示
	 */
	layers.createTips = function(context, id, spId) {
		layer.tips(context, id, {
			time : 0,
			id : spId,
			tipsMore : true
		});
	};
    
	
	/**
	 * 打开一个window
	 */
	layers.openWindow = function(url) {
		var index = layer.open({
			type: 1,
		      title: '很多时候，我们想最大化看，比如像这个页面。',
		      shadeClose: true,
		      shade: false,
		      maxmin: false, //开启最大化最小化按钮
		      area: ['893px', '600px'],
		      content: '//fly.layui.com/',
		      anim: 2
		});
		layer.full(index);
	}

})

// layers.alert("你好吗");
