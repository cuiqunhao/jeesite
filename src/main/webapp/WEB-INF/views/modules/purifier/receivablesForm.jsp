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
		function calNext(){
			var collCycle = parseInt($("#collCycle").val());
			var thisTime = $("#recTime").val();
			var date = new Date(thisTime);
			// 加五天.
			date.setDate(date.getDate() + collCycle);

			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			if(m <= 9) m = "0"+m;
			if(d <= 9) d = "0"+d;
			var cdate = y+"-"+m+"-"+d;

			$("#nextRecTime").val(cdate);
		}



	</script>
</head>


<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/reveivables/list/">收款单列表</a></li>
		<li class="active">
			<a href="${ctx}/reveivables/form?id=${receivables.id}">收款单录入
			</a>
		</li>
	</ul>
	<br/>
	<form:form id="inputForm" modelAttribute="receivables" action="${ctx}/reveivables/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">合同编号:</label>
			<div class="controls">
				<purifier:contractSelect id="contract.id" labelName="contractIdLabel" collCycleValue="${receivables.contract.collCycle}" idValue="${receivables.contract.id}" labelValue="${receivables.contract.contractNo}"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">收款金额:</label>
			<div class="controls">
				<form:input path="amount" htmlEscape="false" maxlength="10" class="required"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">收款人:</label>
			<div class="controls">
				<purifier:userSelect id="userId.id" labelName="userIdLabel" idValue="${receivables.userId.id}" labelValue="${receivables.userId.name}"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">本次收款时间:</label>
			<div class="controls">
				<input id="recTime" name="recTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${receivables.recTime}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" onchange="calNext()"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">下次收款时间:</label>
			<div class="controls">
				<input id="nextRecTime" name="nextRecTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${receivables.nextRecTime}" pattern="yyyy-MM-dd"/>"
				/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">是否开票:</label>
			<div class="controls">
				<form:checkbox path="isInvoice" value="1" htmlEscape="false"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">发票信息:</label>
			<div class="controls">
				<form:input path="invoice" htmlEscape="false" maxlength="64" class="required"/>
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