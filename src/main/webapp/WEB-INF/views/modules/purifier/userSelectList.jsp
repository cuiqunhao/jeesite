<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var selectId,selectName,selectOfficeCode,selectOfficeName
        $(document).ready(function() {
            $("#contentTable tr").click(function () {
                var tr = $(this);
//                top.mainFrame.setData(tr.find("td").eq(1).text(), tr.find("td").eq(5).text());
				selectId=tr.find("td").eq(1).text();
				selectName=tr.find("td").eq(6).text();
				selectOfficeCode = tr.find("td").eq(4).text();
				selectOfficeName = tr.find("td").eq(3).text();

            });
        });

		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sys/user/selectList");
			$("#searchForm").submit();
			return false;
		}
	</script>
</head>
<body>
<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/selectList" method="post" class="breadcrumb form-search ">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
	<ul class="ul-form">
		<li><label>归属公司：</label><sys:treeselect id="company" name="company.id" value="${user.company.id}" labelName="company.name" labelValue="${user.company.name}"
												title="公司" url="/sys/office/treeData?type=1" cssClass="input-small" allowClear="true"/></li>
		<li><label>登录名：</label><form:input path="loginName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
		<li class="clearfix"></li>
		<li><label>归属部门：</label><sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}"
												title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/></li>
		<li><label>姓&nbsp;&nbsp;&nbsp;名：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/></li>
		<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
		<li class="clearfix"></li>
	</ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>选择</th>
		<th style="display:none;">id</th>
		<th>归属公司</th>
		<th>归属部门</th>
		<th style="display:none;">部门编号</th>
		<th class="sort-column login_name">登录名</th>
		<th class="sort-column name">姓名</th>
		<th>电话</th>
		<th>手机</th><%--<th>角色</th> --%>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="user">
		<tr>
			<td><input type="radio" id="${user.id}" name="choose"/></td>
            <td style="display:none;">${user.id}</td>
			<td>${user.company.name}</td>
			<td>${user.office.name}</td>
			<td style="display:none;">${user.office.code}</td>
			<td><a href="${ctx}/sys/user/form?id=${user.id}">${user.loginName}</a></td>
			<td>${user.name}</td>
			<td>${user.phone}</td>
			<td>${user.mobile}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>