<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var goodsId,goodsName,goodsType
		$(document).ready(function() {
			$("#contentTable tr").click(function () {
				var tr = $(this);
				goodsId=tr.find("td").eq(1).text();
				goodsName=tr.find("td").eq(2).text();
				goodsType=tr.find("td").eq(3).text();

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
		<a href="${ctx}/goods/list/">仓库列表</a>
	</li>
</ul>
<!--tab-->

<!--查询区-->
<form:form id="searchForm" modelAttribute="goods" action="${ctx}/goods/list" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

	<div style="margin-top:8px;">
		<label>商品名称：</label>
		<form:input path="goodName" htmlEscape="false" maxlength="64" class="input-medium"/>
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
		<th style="display: none"></th>
		<th>商品名称</th>
		<th>商品型号</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="good">
		<tr>
			<td><input type="radio" name="select" id="${good.id}"></td>
			<td style="display: none">${good.id}</td>
			<td>${good.goodName}</td>
			<td>${good.type}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<!-- 表格内容 -->

<!--分页区域-->
<div class="pagination">${page}</div>

</body>
</html>