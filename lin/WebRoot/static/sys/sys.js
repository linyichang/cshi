$(function(){
     
})
   var sys = {
       load : null,
       deleteOne: null,
       deleteS :　null
   };
  
   $(function(){
   	  //显示加载层
     sys.load = function(){
          layer.load(1, {shade: [0.1,'#fff']});
      }
      
      //删除一条数据
     sys.deleteOne = function(context,url){
      	 layer.confirm(context, {
			btn : [ 'YES', 'NO' ]
		}, function() {
			sys.load();
			window.location.href = url;
		});
     }
      
      //删除多条数据
     sys.deleteS = function(context,url){
      	layer.confirm(context, {
			btn : [ 'YES', 'NO' ]
		}, function() {
		 sys.load();
      	 var data = new Array();
         var ch = document.getElementsByName("allOew");
         if(ch.checked.length < 1) {
           layer.alert("对不起，你没有选择数据！");
         }  else {
             for (var i = 0; i < ch.length; i++) {
				if(ch[i].checked == true) {
					data.push(ch[i].value);
				}
		     }
         }	
		   window.location.href = url+"?ids="+data+"";
		});
      }
      
		/*
		 * 适用：a标签发起请求
		 * 显示加载图片，在便签class加入LoadImg即可显示
		 */
	   $(".LoadImg").click(function(){
	      layer.load(1, {shade: [0.1,'#fff']});
	   })
   })
   
   
  
   
   