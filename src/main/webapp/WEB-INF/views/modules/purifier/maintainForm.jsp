<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同管理</title>
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
		<li><a href="${ctx}/maintain/list/">维护单列表</a></li>
		<li class="active">
			<a href="${ctx}/maintain/form?id=${receivables.id}">维护单录入
			</a>
		</li>
	</ul>
	<br/>
	<form:form id="inputForm" modelAttribute="maintain" action="${ctx}/maintain/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">合同编号:</label>
			<div class="controls">
				<purifier:contractSelect id="contract.id" labelName="contractIdLabel" idValue="${maintain.contract.id}" labelValue="${maintain.contract.contractNo}"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">维护人:</label>
			<div class="controls">
				<purifier:userSelect id="userId.id" labelName="userIdLabel" idValue="${maintain.userId.id}" labelValue="${maintain.userId.name}"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">本次维护时间:</label>
			<div class="controls">
				<input id="mainTime" name="mainTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${maintain.mainTime}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">下次维护时间:</label>
			<div class="controls">
				<input id="nextMainTime" name="nextMainTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${maintain.nextMainTime}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">维护内容:</label>
			<div class="controls">
				<form:input path="mainContent" htmlEscape="false" maxlength="64" class="required"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>