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
		<a href="${ctx}/maintain/list/">维护单列表</a>
	</li>
	<li>
		<a href="${ctx}/maintain/form">维护单录入</a>
	</li>
</ul>
<!--tab-->

<!--查询区-->
<form:form id="searchForm" modelAttribute="maintain" action="${ctx}/maintain/list" method="post" class="breadcrumb form-search">
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
		<th>维护人</th>
		<th>维护日期</th>
		<th>下次维护时间</th>
		<th>维护内容</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="maintain">
		<tr>
			<td><a href="${ctx}/maintain/form?id=${maintain.id}">${maintain.contract.contractNo}</a></td>
			<td>${maintain.userId.name}</td>
			<td><fmt:formatDate value="${maintain.mainTime}" pattern="yyyy-MM-dd"/></td>
			<td><fmt:formatDate value="${maintain.nextMainTime}" pattern="yyyy-MM-dd"/></td>
			<td>${maintain.mainContent}</td>
			<td>
				<a href="${ctx}/maintain/form?id=${maintain.id}">修改</a>
				<a href="${ctx}/maintain/delete?id=${maintain.id}" onclick="return confirmx('确认要删除该合同吗？', this.href)">删除</a>
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