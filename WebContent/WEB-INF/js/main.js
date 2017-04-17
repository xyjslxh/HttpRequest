/*RequireJS 2.0.*中，shim配置中的"exports"属性可以是一个函数而不是字串。这种情况下它就起到上述示例中的"init"属性的功能。 RequireJS 2.1.0+中加入了"init"承接库加载后的初始工作，以使exports作为字串值被enforceDefine所使用。
那些仅作为jQuery或Backbone的插件存在而不导出任何模块变量的"模块"们，shim配置可简单设置为依赖数组*/
require.config({
	baseUrl : './js/',
	paths : {
		'jquery' : [ 'lib/jquery-1.11.0' ],// 不能添加文件名（.js）后缀，因为默认是js文件
		'zebra_dialog' : [ 'lib/zebra_dialog' ],
		'backbone' : [ 'lib/backbone' ],
		'underscore' : [ 'lib/underscore' ],
		'bootstrap' : [ 'lib/bootstrap' ],
		'init' : [ 'init' ],
		'service' : [ 'view/service' ],
		'jquery.validate' : [ 'lib/jquery-validate/jquery.validate' ],
		'messages_zh' : [ 'lib/jquery-validate/messages_zh' ],
		'jquery.ui' : [ 'lib/jquery.ui' ]
	},
	shim : {
		'zebra_dialog' : {
			deps : [ 'jquery' ],
			exports : '$.Zebra_Dialog'
		},
		'backbone' : {
			deps : [ 'underscore', 'jquery' ],
			exports : 'Backbone'
		},
		'underscore' : {
			exports : '_'
		},
		'bootstrap' : {
			deps : [ 'jquery' ],
			exports : 'bootstrap'
		},
		'jquery.validate' : [ 'jquery' ],
		'messages_zh' : [ 'jquery' ],
		'jquery.ui' : [ 'jquery' ]
	}
})

// jQuery从1.7后开始支持AMD规范，即如果jQuery作为一个AMD模块运行时，它的模块名是“jquery”。注意“jquery”是固定的，不能写“jQuery”或其它。
require([ 'jquery', 'bootstrap', 'service', 'jquery.validate', 'messages_zh' ], function($, bootstrap) {
	$(function() {

		// 对Date的扩展，将 Date 转化为指定格式的String
		// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
		// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
		// 例子：
		// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02
		// 08:09:04.423
		// (new Date()).Format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
		Date.prototype.Format = function(fmt) { // author: meizz
			var o = {
				"M+" : this.getMonth() + 1, // 月份
				"d+" : this.getDate(), // 日
				"h+" : this.getHours(), // 小时
				"m+" : this.getMinutes(), // 分
				"s+" : this.getSeconds(), // 秒
				"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
				"S" : this.getMilliseconds()
			// 毫秒
			};
			if (/(y+)/.test(fmt))
				fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
			for ( var k in o)
				if (new RegExp("(" + k + ")").test(fmt))
					fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
							.substr(("" + o[k]).length)));
			return fmt;
		}

	});
});