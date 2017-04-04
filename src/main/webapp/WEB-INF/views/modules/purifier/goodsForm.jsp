<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>


<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/goods/list/">商品列表</a></li>
		<li class="active">
			<a href="${ctx}/goods/form?id=${goods.id}">商品${not empty contract.id?'修改':'添加'}
			</a>
		</li>
	</ul>
	<br/>
	<form:form id="inputForm" modelAttribute="goods" action="${ctx}/goods/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">商品名称:</label>
			<div class="controls">
				<form:input path="goodName" htmlEscape="false" maxlength="64" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品型号:</label>
			<div class="controls">
				<form:input path="type" htmlEscape="false" maxlength="64" class="required"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">商品图片:</label>
			<div class="controls">
				<form:hidden  id="nameImage" path="photo" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="nameImage" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="150" maxHeight="150"/>
			</div>
		</div>

		<div class="form-actions">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>