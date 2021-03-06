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
					// var str = "result=" + result.result + "</br>";
					// if (result.hasOwnProperty('errorCode')) {
					// str += "errorCode=" + result.errorCode + "</br>";
					// }
					// str += 'description=' + '<xmp>' + result.description +
					// '</xmp>';
					// $('#result').find('.panel-body').html(str);
					$('#result').find('.panel-body').html(
							JSON.stringify(result));
				}
			});
		},
		jssubmit : function() {
			var _this = this;
			$.ajax({
				url : _this.$el.find('input[name=url]').val(),
				type : 'post',
				data : $(_this.$el.find('form')).serialize(),
				dataType : 'JSON',
				success : function(result) {
					$('#result').find('.panel-body').html('');
					var str = "result=" + result.result + "</br>";
					if (result.hasOwnProperty('errorCode')) {
						str += "errorCode=" + result.errorCode + "</br>";
					}
					str += 'description=' + '<xmp>' + result.description
							+ '</xmp>';
					$('#result').find('.panel-body').html(str);
				}
			});

		}
	});
	return new ServiceView;
});