define([ 'underscore', 'jquery' ], function(_, $) {
	_.templateSettings = {
		evaluate : /\{\{(.+?)\}\}/g,
		interpolate : /\{\{=(.+?)\}\}/g,
		escape : /\{\{-(.+?)\}\}/g
	};
});