<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>库存管理</title>
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
		<a href="${ctx}/wareGoodsRel/list/">库存列表</a>
	</li>
	<li>
		<a href="${ctx}/wareGoodsRel/form">库存维护</a>
	</li>
</ul>
<!--tab-->

<!--查询区-->
<form:form id="searchForm" modelAttribute="wareGoodsRel" action="${ctx}/wareGoodsRel/list" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

	<div style="margin-top:8px;">
		<label>仓库：</label>
		<purifier:wareSelect id="ware.id" labelName="wareLableName" />
		<label>商品：</label>
		<purifier:goodsSelect id="good.id" labelName="goodLableName" type="good.type"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</div>
</form:form>
<!--查询区-->

<sys:message content="${message}"/>

<!-- 表格内容 -->
<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>仓库名称</th>
		<th>仓库地址</th>
		<th>商品名称</th>
		<th>商品型号</th>
		<th>数量</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="wareGoodsRel">
		<tr>
			<td>${wareGoodsRel.ware.wareName}</td>
			<td>${wareGoodsRel.ware.wareAddress}</td>
			<td>${wareGoodsRel.good.goodName}</td>
			<td>${wareGoodsRel.good.type}</td>
			<td>${wareGoodsRel.num}</td>
			<td>
				<a href="${ctx}/wareGoodsRel/form?wareId=${wareGoodsRel.ware.id}&goodId=${wareGoodsRel.good.id}">修改</a>
				<a href="${ctx}/wareGoodsRel/delete?wareId=${wareGoodsRel.ware.id}&goodId=${wareGoodsRel.good.id}" onclick="return confirmx('确认要删除该合同吗？', this.href)">删除</a>
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