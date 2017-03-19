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
		<li><a href="${ctx}/contract/notMainList/">合同列表</a></li>
		<li class="active">
			<a href="${ctx}/contract/notMainForm?id=${contract.id}">设定维护周期
			</a>
		</li>
	</ul>
	<br/>
	<form:form id="inputForm" modelAttribute="contract" action="${ctx}/contract/saveMain" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">维护周期:</label>
			<div class="controls">
				<form:input path="mianCycle" htmlEscape="false" maxlength="10" class="required"/>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">安装人员:</label>
			<div class="controls">
				<purifier:userSelect id="installer.id" labelName="installerLabel" idValue="${contract.installer.id}" labelValue="${contract.installer.name}"/>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">合同编号:</label>
			<div class="controls">
				<form:input path="contractNo" htmlEscape="false" maxlength="64" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同名称:</label>
			<div class="controls">
				<form:input path="contractName" htmlEscape="false" maxlength="120" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同金额:</label>
			<div class="controls">
				<form:input path="contractAmount" htmlEscape="false" maxlength="20" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同期限:</label>
			<div class="controls">
				<input id="contractBenginTime" name="contractBenginTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					   value="<fmt:formatDate value="${contract.contractBenginTime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false}); "/>
				<label>&nbsp;--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<input id="contractEndTime" name="contractEndTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					   value="<fmt:formatDate value="${contract.contractEndTime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;&nbsp;
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人:</label>
			<div class="controls">
				<form:input path="contacts" htmlEscape="false" maxlength="64" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话:</label>
			<div class="controls">
				<form:input path="contactsPhone" htmlEscape="false" maxlength="11" class="required digits"/>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">业务员:</label>
			<div class="controls">
				<purifier:userSelect id="salesman.id" labelName="salesmanLabel" idValue="${contract.salesman.id}" labelValue="${contract.salesman.name}"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">所属项目:</label>
			<div class="controls">
				<form:input path="item" htmlEscape="false" maxlength="11" class="required"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">联系地址:</label>
			<div class="controls">
				<form:input path="contactsAddress" htmlEscape="false" maxlength="80" class="required"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">签订时间:</label>
			<div class="controls">
				<input id="contractTime" name="contractTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					   value="<fmt:formatDate value="${contract.contractTime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">收款周期:</label>
			<div class="controls">
				<form:input path="collCycle" htmlEscape="false" maxlength="10" class="required digits"/>
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