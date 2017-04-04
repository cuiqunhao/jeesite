<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>申请商品管理</title>
	<meta name="decorator" content="default"/>
</head>


<body>
<ul class="nav nav-tabs">
	<li>添加商品</li>
</ul>
<form:form id="inputForm" method="post" class="form-horizontal">
	<input type="hidden" id="id" name="id"/>
	<div class="control-group">
		<label class="control-label">商品:</label>
		<div class="controls">
			<purifier:goodsSelect id="good.id" labelName="goodLableName" type="good.type" />
		</div>
	</div>

	<div class="control-group">
		<label class="control-label">数量:</label>
		<div class="controls">
			<input type="text" id="num" htmlEscape="false" class="required" size="64"/>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label">用途:</label>
		<div class="controls">
			<input type="text" id="useFor" htmlEscape="false" class="required" size="64"/>
		</div>
	</div>
</form:form>
</body>
</html>