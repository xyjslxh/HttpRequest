<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>在线授权平台 V3.0</title>
<link href="css/lc.css" rel="stylesheet">
<link href="css/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="js/lib/min/html5shiv.js"></script>
      <script src="js/lib/min/respond.js"></script>
    <![endif]-->
<link rel="stylesheet" href="css/lib/jquery-ui.css">
</head>
<body style="padding-top: 50px">
	<div class="container-fluid">
		<div class="row-fluid">
			<div id="service" class="span12">
				<h3 class="text-center">HTTP请求模拟器</h3>
				<form id="service-form" class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="url">HTTP请求URL</label>
						<div class="col-sm-10">
							<input name="url" type="text" class="form-control" />
						</div>
					</div>
<!-- 					<div class="form-group">
						<label class="col-sm-2 control-label" for="clientId">备案号</label>
						<div class="col-sm-10">
							<input name="clientId" type="text" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="key">密钥</label>
						<div class="col-sm-10">
							<input name="key" type="text" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="messageType">报文类型</label>
						<div class="col-sm-10">
							<input name="messageType" type="text" class="form-control" />
						</div>
					</div> -->
					<div class="form-group">
						<label class="col-sm-2 control-label" for="messageText">报文</label>
						<div class="col-sm-10">
							<textarea name="messageText" rows="20" class="form-control"></textarea>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-10">
							<button name="submit" type="button" class="btn">后台方式提交</button>
							<button name="reset" type="reset" class="btn">清空</button>
							<button name="jssubmit" type="button" class="btn">js方式提交</button>
						</div>
					</div>
				</form>
				<div>
					<label class="col-sm-2 control-label"></label>
					<div id="result" class="panel panel-default col-sm-10">
						<div class="panel-heading">
							<h3 class="panel-title">HTTP请求结果</h3>
						</div>
						<div class="panel-body"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script data-main="js/main.js" src="js/lib/require.js" defer="defer" async="async"></script>
</body>
</html>