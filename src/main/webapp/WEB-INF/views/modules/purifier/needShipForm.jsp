<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>货物申请管理</title>
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
		function setUrl(){
			top.$.jBox.open(
					"iframe:https://m.kuaidi100.com/result.jsp?nu="+$('#logistics').val(),
					"选择用户",
					$(top.document).width()-220,
					$(top.document).height()-220,
					{
						<%--ajaxData:{selectIds: $("#${id}Id").val()},--%>
						buttons:{"确定":"ok","关闭":true},
						loaded:function(h){
							$(".jbox-content", top.document).css("overflow-y","hidden");
						}
					});
		}

	</script>
</head>


<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/goodsApp/needShipList/">货物申请列表</a></li>
		<li class="active">
			<a href="${ctx}/goodsApp/needShipForm?id=${goodsApp.id}">申请单${not empty goodsApp.id?'修改':'添加'}
			</a>
		</li>
	</ul>
	<br/>
	<form:form id="inputForm" modelAttribute="goodsApp" action="${ctx}/goodsApp/save?opt=fh" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">申请编号:</label>
			<div class="controls">
				<form:input path="appNo" htmlEscape="false" maxlength="64" class="required" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请人:</label>
			<div class="controls">
				<purifier:userSelect id="applicantUser.id" labelName="applicantUserLabe" idValue="${goodsApp.applicantUser.id}" labelValue="${goodsApp.applicantUser.name}" disJudgeId="${goodsApp.id}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货人:</label>
			<div class="controls">
				<form:input path="consignee" htmlEscape="false" maxlength="20" class="required" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货人电话:</label>
			<div class="controls">
				<form:input path="consigneePhone" htmlEscape="false" maxlength="11" class="required digits" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货人地址:</label>
			<div class="controls">
				<form:input path="consigneeAddress" htmlEscape="false" maxlength="11" class="required digits" disabled="true"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge" disabled="true"/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">申请商品:</label>
				<div class="controls">
					<input id="goodsBtnSubmit" class="btn btn-primary" type="button" value="添加商品" disabled="true"/>
					<table id="goodsAppTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>商品id</th>
								<th>商品名称</th>
								<th>商品型号</th>
								<th>用途</th>
								<th>数量</th>
							</tr>
						</thead>
						<tbody>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<c:forEach items="${goodsApp.goodList}" var="good" varStatus="s">
						<tr>
							<td><input type="text" readonly='readonly' name="goodList[${s.index }].good.id" value="${good.good.id}" /></td>
							<td><input type="text" readonly='readonly' name="goodList[${s.index }].good.goodName" value="${good.good.goodName}" /></td>
							<td><input type="text" readonly='readonly' name="goodList[${s.index }].good.type" value="${good.good.type}" /></td>
							<td><input type="text" readonly='readonly' name="goodList[${s.index }].usefor" value="${good.usefor}" /></td>
							<td><input type="text" readonly='readonly' name="goodList[${s.index }].appNum" value="${good.appNum}" /></td>
						</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>


		<div class="control-group">
			<label class="control-label">一级审核状态:</label>
			<div class="controls">
				<form:select path="firstExaStatus" htmlEscape="false" maxlength="64" class="required" disabled="true">
					<form:option value="" label="请选择"></form:option>
					<form:option value="1" label="通过"></form:option>
					<form:option value="0" label="不通过"></form:option>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">一级审核内容:</label>
			<div class="controls">
				<form:textarea path="firstExaContent" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge" disabled="true"/>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">二级审核状态:</label>
			<div class="controls">
				<form:select path="secExaStatus" htmlEscape="false" maxlength="64" class="required" disabled="true">
					<form:option value="" label="请选择"></form:option>
					<form:option value="1" label="通过"></form:option>
					<form:option value="0" label="不通过"></form:option>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">二级审核内容:</label>
			<div class="controls">
				<form:textarea path="secExaContent" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge" disabled="true"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">发货仓库:</label>
			<div class="controls">
				<purifier:wareSelect id="ware.id" labelName="shipAddress" disable="false" idValue="${goodsApp.ware.id}" labelValue="${goodsApp.ware.wareName}"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">物流信息:</label>
			<div class="controls">
				<form:input path="logistics" htmlEscape="false" maxlength="120" class="required"/>
				<a id="logisticsButton" onclick="setUrl()" class="btn">&nbsp;<i class="icon-search"></i>&nbsp;</a>
			</div>
		</div>

		<div class="form-actions">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>