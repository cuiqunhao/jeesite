<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仓库管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var selectId,selectName
		$(document).ready(function() {
			$("#contentTable tr").click(function () {
				var tr = $(this);
//                top.mainFrame.setData(tr.find("td").eq(1).text(), tr.find("td").eq(5).text());
				selectId=tr.find("td").eq(1).text();
				selectName=tr.find("td").eq(2).text();

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
<!--tab-->
<ul class="nav nav-tabs">
	<li class="active">
		<a href="${ctx}/ware/wareSelectList/">仓库列表</a>
	</li>
</ul>
<!--tab-->

<!--查询区-->
<form:form id="searchForm" modelAttribute="ware" action="${ctx}/ware/wareSelectList" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

	<div style="margin-top:8px;">
		<label>仓库名称：</label>
		<form:input path="wareName" htmlEscape="false" maxlength="64" class="input-medium"/>
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
		<th>仓库名称</th>
		<th>仓库地址</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="ware">
		<tr>
			<td><input type="radio" name="select" id="${ware.id}"></td>
			<td style="display:none;">${ware.id}</td>
			<td>${ware.wareName}</td>
			<td>${ware.wareAddress}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<!-- 表格内容 -->

<!--分页区域-->
<div class="pagination">${page}</div>

</body>
</html>