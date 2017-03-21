<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>库存管理</title>
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
		<li><a href="${ctx}/wareGoodsRel/list/">库存列表</a></li>
		<li class="active">
			<a href="${ctx}/wareGoodsRel/form?id=${ware.id}">库存${not empty contract.id?'修改':'添加'}查看
			</a>
		</li>
	</ul>
	<br/>
	<form:form id="inputForm" modelAttribute="wareGoodsRel" action="${ctx}/wareGoodsRel/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">仓库:</label>
			<div class="controls">
				<purifier:wareSelect id="ware.id" labelName="wareLableName" idValue="${wareGoodsRel.ware.id}" labelValue="${wareGoodsRel.ware.wareName}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品:</label>
			<div class="controls">
				<purifier:goodsSelect id="good.id" labelName="goodLableName" idValue="${wareGoodsRel.good.id}" labelValue="${wareGoodsRel.good.goodName}"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">数量:</label>
			<div class="controls">
				<form:input path="num" htmlEscape="false" maxlength="255" class="required"/>
			</div>
		</div>

		<div class="form-actions">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>