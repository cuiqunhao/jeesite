<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}
	</script>
</head>
<body>
<!--tab-->
<ul class="nav nav-tabs">
	<li class="active">
		<a href="${ctx}/reveivables/list/">收款单列表</a>
	</li>
	<li>
		<a href="${ctx}/reveivables/form">收款单录入</a>
	</li>
</ul>
<!--tab-->

<!--查询区-->
<form:form id="searchForm" modelAttribute="receivables" action="${ctx}/reveivables/list" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<div style="margin-top:8px;">
		<label>合同编号：</label>
		<form:input path="contract.contractNo" htmlEscape="false" maxlength="64" class="input-medium"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</div>
</form:form>
<!--查询区-->

<sys:message content="${message}"/>

<!-- 表格内容 -->
<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>合同编号</th>
		<th>收款人</th>
		<th>收款日期</th>
		<th>发票信息</th>
		<th>收款金额</th>
		<th>下次收款时间</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="reveivables">
		<tr>
			<td><a href="${ctx}/reveivables/form?id=${reveivables.id}">${reveivables.contract.contractNo}</a></td>
			<td>${reveivables.userId.name}</td>
			<td><fmt:formatDate value="${reveivables.recTime}" pattern="yyyy-MM-dd"/></td>
			<td>${reveivables.invoice}</td>
			<td>${reveivables.amount}</td>
			<td><fmt:formatDate value="${reveivables.nextRecTime}" pattern="yyyy-MM-dd"/></td>
			<td>
				<a href="${ctx}/reveivables/form?id=${reveivables.id}">修改</a>
				<a href="${ctx}/reveivables/delete?id=${reveivables.id}" onclick="return confirmx('确认要删除该合同吗？', this.href)">删除</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<!-- 表格内容 -->

<!--分页区域-->
<div class="pagination">${page}</div>

</body>
</html>