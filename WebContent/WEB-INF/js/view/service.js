define([ 'backbone', 'jquery' ], function(Backbone, $) {
	var ServiceView = Backbone.View.extend({
		el : $('#service'),
		initialize : function() {
			_.bindAll(this, 'render');
		},
		events : {
			'click button[name=jssubmit]' : "jssubmit",
			'click button[name=submit]' : 'submit'
		// 'click button[name=clear]' : 'clear'
		},
		render : function() {
			_this = this;
		},
		submit : function() {
			var _this = this;
			$.ajax({
				url : 'InternationalTrade/declare',
				type : 'post',
				data : $(_this.$el.find('form')).serialize(),
				dataType : 'JSON',
				success : function(result) {
					$('#result').find('.panel-body').html('');
					var str = "result=" + result.result + "</br>";
					if (result.hasOwnProperty('errorCode')) {
						str += "errorCode=" + result.errorCode + "</br>";
					}
					str += 'description=' + '<xmp>' + result.description + '</xmp>';
					$('#result').find('.panel-body').html(str);
				}
			});
		},
		jssubmit : function() {
			var _this = this;
			$.ajax({
				url : 'http://10.1.52.101:8080/HttpsServer/InternationalTrade/declare',
				type : 'post',
				data : $(_this.$el.find('form')).serialize(),
				dataType : 'JSON',
				success : function(result) {
					$('#result').find('.panel-body').html('');
					var str = "result=" + result.result + "</br>";
					if (result.hasOwnProperty('errorCode')) {
						str += "errorCode=" + result.errorCode + "</br>";
					}
					str += 'description=' + '<xmp>' + result.description + '</xmp>';
					$('#result').find('.panel-body').html(str);
				}
			});

		}
	});
	return new ServiceView;
});