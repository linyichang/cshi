<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表</title>
</head>
<body>
	<div class="panel panel-default" style="margin-bottom: 0px; color: #000; padding: 10px; border: 1px solid rgba(0, 0, 0, 0.1);">
		<div class="panel-heading">
			<i class="fa fa-list-alt fa-fw" aria-hidden="true"></i>商品统计
		</div>
		<div style="width: 100%;height:400px; margin-top: 20px;overflow: auto;">
			<div id="main1" style="width: 50%; height: 400px; float: left; "></div>
			<div id="main2" style="width: 50%; height: 400px; float: left; "></div>
		</div>
		
	</div>


</body>
<script type="text/javascript">
	// 基于准备好的dom，初始化echarts实例
	var myChart1 = echarts.init(document.getElementById('main1'));
	var myChart2 = echarts.init(document.getElementById('main2'));
	// 指定图表的配置项和数据
	var option = {
		title : {
			text : '产品销售统计'
		},
		tooltip : {},
		legend : {
			data : [ '销量' ]
		},
		xAxis : {
			data : [ "衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子" ]
		},
		yAxis : {},
		series : [ {
			name : '销量',
			type : 'bar',
			data : [ 5, 20, 36, 10, 10, 20 ]
		} ]
	};
	// 使用刚指定的配置项和数据显示图表。
	myChart1.setOption(option);
	option2 = {
		    title: {
		        text: '广州天气走势',
		        subtext: ''
		    },
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['最高温度','最低温度']
		    },
		    toolbox: {
		        show: true,
		        feature: {
		            dataZoom: {
		                yAxisIndex: 'none'
		            },
		            dataView: {readOnly: false},
		            magicType: {type: ['line', 'bar']},
		            restore: {},
		            saveAsImage: {}
		        }
		    },
		    xAxis:  {
		        type: 'category',
		        boundaryGap: false,
		        data: ${time}
		    },
		    yAxis: {
		        type: 'value',
		        axisLabel: {
		            formatter: '{value} °C'
		        }
		    },
		    series: [
		        {
		            name:'最高温度',
		            type:'line',
		            data:${high},
		            markPoint: {
		                data: [
		                    {type: 'max', name: '最大值'},
		                    {type: 'min', name: '最小值'}
		                ]
		            },
		            markLine: {
		                data: [
		                    {type: 'average', name: '平均值'}
		                ]
		            }
		        },
		        {
		            name:'最低温度',
		            type:'line',
		            data:${low},
		            markPoint: {
		                data: [
		                    {name: '周最低', value: -2, xAxis: 1, yAxis: -2}
		                ]
		            },
		            markLine: {
		                data: [
		                    {type: 'average', name: '平均值'},
		                    [{
		                        symbol: 'none',
		                        x: '90%',
		                        yAxis: 'max'
		                    }, {
		                        symbol: 'circle',
		                        label: {
		                            normal: {
		                                position: 'start',
		                                formatter: '最大值'
		                            }
		                        },
		                        type: 'max',
		                        name: '最高点'
		                    }]
		                ]
		            }
		        }
		    ]
		};

	myChart2.setOption(option2);
</script>
</html>