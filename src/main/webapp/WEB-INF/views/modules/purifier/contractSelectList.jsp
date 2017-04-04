<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var selectId,selectName,collCycle,mianCycle
		$(document).ready(function() {
			$("#contentTable tr").click(function () {
				var tr = $(this);
//                top.mainFrame.setData(tr.find("td").eq(1).text(), tr.find("td").eq(5).text());
				selectId=tr.find("td").eq(1).text();
				selectName=tr.find("td").eq(2).text();
				collCycle=tr.find("td").eq(9).text();
				mianCycle=tr.find("td").eq(12).text();

			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}
	</script>
</head>
<body>

<!--查询区-->
<form:form id="searchForm" modelAttribute="contract" action="${ctx}/contract/list" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<div style="margin-top:8px;">
		<label>签订时间：&nbsp;</label>
		<input id="contractTimeBegin" name="contractTimeBegin" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
			   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
		<label>&nbsp;--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input id="contractTimeEnd" name="contractTimeEnd" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
			   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;&nbsp;
	</div>
	<div style="margin-top:8px;">
		<label>合同编号：</label>
		<form:input path="contractNo" htmlEscape="false" maxlength="64" class="input-medium"/>
		<label>业务员：</label>
		<%--<form:input path="salesman.id" htmlEscape="false" maxlength="10" class="input-medium"/>--%>
		<purifier:userSelect id="salesman.id" labelName="salesmanLabe"/>
		<label>联系人：</label>
		<form:input path="contacts" htmlEscape="false" maxlength="32" class="input-medium"/>
		<label>合同名称：</label>
		<form:input path="contractName" htmlEscape="false" maxlength="64" class="input-medium"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</div>
</form:form>
<!--查询区-->

<sys:message content="${message}"/>

<!-- 表格内容 -->
<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>选择</th>
		<th style="display:none;">id</th>
		<th>合同编号</th>
		<th>合同名称</th>
		<th>联系人</th>
		<th>联系电话</th>
		<th>业务员</th>
		<th>所属项目</th>
		<th>合同金额</th>
		<th>合同收款周期(天)</th>
		<th>签订时间</th>
		<th>安装人员</th>
		<th>维护周期(天)</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="contract">
		<tr>
			<td><input type="radio" id="${contract.id}" name="choose"/></td>
			<td style="display:none;">${contract.id}</td>
			<td>${contract.contractNo}</td>
			<td>${contract.contractName}</td>
			<td>${contract.contacts}</td>
			<td>${contract.contactsPhone}</td>
			<td>${contract.salesman.name}</td>
			<td>${contract.item}</td>
			<td>${contract.contractAmount}</td>
			<td>${contract.collCycle}</td>
			<td><fmt:formatDate value="${contract.contractTime}" pattern="yyyy-MM-dd"/></td>
			<td>${contract.installer.name}</td>
			<td>${contract.mianCycle}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<!-- 表格内容 -->

<!--分页区域-->
<div class="pagination">${page}</div>

</body>
</html>