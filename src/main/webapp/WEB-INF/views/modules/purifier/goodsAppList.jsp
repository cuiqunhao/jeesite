<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>货物申请管理</title>
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
		<a href="${ctx}/goodsApp/list/">货物申请列表</a>
	</li>
	<li>
		<a href="${ctx}/goodsApp/form">货物申请</a>
	</li>
</ul>
<!--tab-->

<!--查询区-->
<form:form id="searchForm" modelAttribute="goodsApp" action="${ctx}/goodsApp/list" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<div style="margin-top:8px;">
		<label>申请时间：&nbsp;</label>
		<input id="appTimeStart" name="contractTimeBegin" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
			   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
		<label>&nbsp;--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input id="appTimeEnd" name="contractTimeEnd" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
			   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;&nbsp;
	</div>
	<div style="margin-top:8px;">
		<label>申请编号：</label>
		<form:input path="appNo" htmlEscape="false" maxlength="64" class="input-medium"/>
		<label>申请人：</label>
		<%--<form:input path="salesman.id" htmlEscape="false" maxlength="10" class="input-medium"/>--%>
		<purifier:userSelect id="applicantUser.id" labelName="applicantUserLabe"/>
		<label>收货人：</label>
		<form:input path="consignee" htmlEscape="false" maxlength="32" class="input-medium"/>
		<label>收货人电话：</label>
		<form:input path="consigneePhone" htmlEscape="false" maxlength="64" class="input-medium"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</div>
</form:form>
<!--查询区-->

<sys:message content="${message}"/>

<!-- 表格内容 -->
<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>申请编号</th>
		<th>申请日期</th>
		<th>申请人</th>
		<th>收货人</th>
		<th>收货人电话</th>
		<th>一级审核状态</th>
		<th>一级审核时间</th>
		<th>二级审核状态</th>
		<th>二级审核时间</th>
		<th>发货地址</th>
		<th>发货时间</th>
		<th>收货状态</th>
		<th>收货时间</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="goodsApp">
		<tr>
			<td><a href="${ctx}/goodsApp/form?id=${goodsApp.id}">${goodsApp.appNo}</a></td>
			<td><fmt:formatDate value="${goodsApp.appTime}" pattern="yyyy-MM-dd"/></td>
			<td>${goodsApp.applicantUser.name}</td>
			<td>${goodsApp.consignee}</td>
			<td>${goodsApp.consigneePhone}</td>
			<td>
				<c:choose>
					<c:when test="${goodsApp.firstExaStatus eq 1}">通过</c:when>
					<c:when test="${empty goodsApp.firstExaStatus}">未审核</c:when>
					<c:when test="${goodsApp.firstExaStatus eq 0}">未通过</c:when>
				</c:choose>
			</td>
			<td><fmt:formatDate value="${goodsApp.firstExaTime}" pattern="yyyy-MM-dd"/></td>
			<td>
			<c:choose>
				<c:when test="${goodsApp.secExaStatus eq 1}">通过</c:when>
				<c:when test="${empty goodsApp.secExaStatus}">未审核</c:when>
				<c:when test="${goodsApp.secExaStatus eq 0}">未通过</c:when>
			</c:choose>
			</td>
			<td><fmt:formatDate value="${goodsApp.secExaTime}" pattern="yyyy-MM-dd"/></td>
			<td>${goodsApp.shipAddress}</td>
			<td><fmt:formatDate value="${goodsApp.shipTime}" pattern="yyyy-MM-dd"/></td>
			<td>${goodsApp.consigneeStatus eq '1'?'已发货':'未发货'}</td>
			<td><fmt:formatDate value="${goodsApp.consigneeTime}" pattern="yyyy-MM-dd"/></td>
			<td>
				<a href="${ctx}/goodsApp/form?id=${goodsApp.id}">修改</a>
				<a href="${ctx}/goodsApp/delete?id=${goodsApp.id}" onclick="return confirmx('确认要删除该合同吗？', this.href)">删除</a>
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