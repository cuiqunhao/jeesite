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
		<a href="${ctx}/contract/list/">订单列表</a>
	</li>
	<li>
		<a href="${ctx}/contract/form">订单录入</a>
	</li>
</ul>
<!--tab-->

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
		<purifier:userSelect id="salesman.id" labelName="salesmanLabe"/>
		<label>代理商：</label>
		<purifier:treeselect id="salesman.office.code" name="salesman.office.code" value="" labelName="salesmanLabe" labelValue=""
							 title="部门" url="/sys/office/treeData?type=2"  notAllowSelectParent="true" allowClear="true"/>
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
		<th>合同编号</th>
		<th>合同名称</th>
		<th>合同类型</th>
		<th>联系人</th>
		<th>联系电话</th>
		<th>业务员</th>
		<th>所属项目</th>
		<th>合同金额</th>
		<th>合同收款周期</th>
		<th>签订时间</th>
		<th>安装人员</th>
		<th>维护周期</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="contract">
		<tr>
			<td><a href="${ctx}/contract/form?id=${contract.id}">${contract.contractNo}</a></td>
			<td>${contract.contractName}</td>
			<td>
				<c:choose>
					<c:when test="${contract.contractType eq 1}">家用</c:when>
					<c:when test="${empty contract.contractType}"></c:when>
					<c:when test="${contract.contractType eq 2}">商用</c:when>
				</c:choose>

			</td>
			<td>${contract.contacts}</td>
			<td>${contract.contactsPhone}</td>
			<td>${contract.salesman.name}</td>
			<td>${contract.item}</td>
			<td>${contract.contractAmount}</td>
			<td>
				<c:choose>
					<c:when test="${not empty contract.collCycle}">${contract.collCycle}天</c:when>
					<c:when test="${empty contract.collCycle}">未维护</c:when>
				</c:choose>

			</td>
			<td><fmt:formatDate value="${contract.contractTime}" pattern="yyyy-MM-dd"/></td>
			<td>
				<c:choose>
					<c:when test="${not empty contract.installer.name}">${contract.installer.name}</c:when>
					<c:when test="${empty contract.installer.name}">未安装</c:when>
				</c:choose>

			</td>
			<td>

				<c:choose>
					<c:when test="${not empty contract.mianCycle}">${contract.mianCycle}天</c:when>
					<c:when test="${empty contract.mianCycle}">未维护</c:when>
				</c:choose>
			</td>
			<td>
				<a href="${ctx}/contract/form?id=${contract.id}">修改</a>
				<a href="${ctx}/contract/delete?id=${contract.id}" onclick="return confirmx('确认要删除该合同吗？', this.href)">删除</a>
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