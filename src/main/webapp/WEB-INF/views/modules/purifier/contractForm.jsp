<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同管理</title>
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
		$(document).ready(function() {
			var rowNum = 0;
			$("#goodsBtnSubmit").click(function(){
				// 正常打开
				top.$.jBox.open(
						"iframe:${ctx}/goodsApp/goodsAppRelForm",
						"选择商品",
						$(top.document).width()-220,
						$(top.document).height()-220,
						{
							<%--ajaxData:{selectIds: $("#${id}Id").val()},--%>
							buttons:{"确定":"ok","关闭":true},
							submit:function(v, h, f){
								var a = h.find("iframe")[0].contentWindow.document.getElementById("good.id").value;
								var b = h.find("iframe")[0].contentWindow.document.getElementById("goodLableName").value;
								var c = h.find("iframe")[0].contentWindow.document.getElementById("num").value;
								var d = h.find("iframe")[0].contentWindow.document.getElementById("useFor").value;
								var e = h.find("iframe")[0].contentWindow.document.getElementById("good.type").value;
								var tr = $('#goodsAppTable tbody tr').eq(0).clone();
								rowNum = $('#goodsAppTable tbody tr').length -1;
								$('#goodsAppTable tbody').append(tr);
								tr.children("td:eq(0)").append("<input type='text' readonly='readonly' name='goodList["+rowNum+"].good.id' value='"+a+"' />");
								tr.children("td:eq(1)").append("<input type='text' readonly='readonly' name='goodList["+rowNum+"].good.goodName' value='"+b+"' />");
								tr.children("td:eq(2)").append("<input type='text' readonly='readonly' name='goodList["+rowNum+"].good.type' value='"+e+"' />");
								tr.children("td:eq(3)").append("<input type='text' name='goodList["+rowNum+"].usefor' value='"+d+"' />");
								tr.children("td:eq(4)").append("<input type='text' name='goodList["+rowNum+"].appNum' value='"+c+"' />");

								rowNum++;
							},
							loaded:function(h){
								$(".jbox-content", top.document).css("overflow-y","hidden");
							}
						});
			});
		});
	</script>
</head>


<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/contract/list/">订单列表</a></li>
		<li class="active">
			<a href="${ctx}/contract/form?id=${contract.id}">订单${not empty contract.id?'修改':'添加'}
			</a>
		</li>
	</ul>
	<br/>
	<form:form id="inputForm" modelAttribute="contract" action="${ctx}/contract/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">合同编号:</label>
			<div class="controls">
				<form:input path="contractNo" htmlEscape="false" maxlength="64" class="required" disabled="${contract.id != null || not empty contract.id?true:false}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同名称:</label>
			<div class="controls">
				<form:input path="contractName" htmlEscape="false" maxlength="120" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同金额:</label>
			<div class="controls">
				<form:input path="contractAmount" htmlEscape="false" maxlength="20" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同期限:</label>
			<div class="controls">
				<input id="contractBenginTime" name="contractBenginTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					   value="<fmt:formatDate value="${contract.contractBenginTime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false}); "/>
				<label>&nbsp;--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<input id="contractEndTime" name="contractEndTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					   value="<fmt:formatDate value="${contract.contractEndTime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;&nbsp;
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人:</label>
			<div class="controls">
				<form:input path="contacts" htmlEscape="false" maxlength="64" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话:</label>
			<div class="controls">
				<form:input path="contactsPhone" htmlEscape="false" maxlength="11" class="required digits"/>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">代理商编号:</label>
			<div class="controls">
				<form:input path="salesman.office.code" htmlEscape="false" maxlength="64" readonly="true"/>
			</div>
			<label class="control-label">代理商名称:</label>
			<div class="controls">
				<form:input path="salesman.office.name" htmlEscape="false" maxlength="64" readonly="true"/>
			</div>
			<label class="control-label">业务员:</label>
			<div class="controls">
				<purifier:userSelect id="salesman.id" labelName="salesmanLabe" idValue="${contract.salesman.id}" labelValue="${contract.salesman.name}"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">申请商品:</label>
			<div class="controls">
				<input id="goodsBtnSubmit" class="btn btn-primary" type="button" value="添加商品"  ${contract.id != null || not empty contract.id?"disabled":""}/>
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
					<c:forEach items="${contract.goodList}" var="good" varStatus="s">
						<tr>
							<td><input type="text" readonly='readonly' name="goodList[${s.index }].good.id" value="${good.good.id}" /></td>
							<td><input type="text" readonly='readonly' name="goodList[${s.index }].good.goodName" value="${good.good.goodName}" /></td>
							<td><input type="text" readonly='readonly' name="goodList[${s.index }].good.type" value="${good.good.type}" /></td>
							<td><input type="text"  name="goodList[${s.index }].usefor" value="${good.usefor}" /></td>
							<td><input type="text" name="goodList[${s.index }].appNum" value="${good.appNum}" /></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">所属项目:</label>
			<div class="controls">
				<form:input path="item" htmlEscape="false" maxlength="11" class="required"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">联系地址:</label>
			<div class="controls">
				<form:input path="contactsAddress" htmlEscape="false" maxlength="80" class="required"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">签订时间:</label>
			<div class="controls">
				<input id="contractTime" name="contractTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					   value="<fmt:formatDate value="${contract.contractTime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">收款周期:</label>
			<div class="controls">
				<form:input path="collCycle" htmlEscape="false" maxlength="10" class="required digits"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>

		<c:if test="${not empty contract.id}">
			<div class="control-group">
				<label class="control-label">收款记录:</label>
				<div class="controls">
					<table id="receivablesTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>收款人</th>
								<th>收款时间</th>
								<th>收款金额</th>
								<th>发票信息</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${receivablesList}" var="receivables">
								<tr>
									<td>${receivables.userId.name}</td>
									<td><fmt:formatDate value="${receivables.recTime}" pattern="yyyy-MM-dd"/></td>
									<td>${receivables.amount}</td>
									<td>${receivables.invoice}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
			</div>

			<div class="control-group">
				<label class="control-label">维护记录:</label>
				<div class="controls">
					<table id="maintainTable" class="table table-striped table-bordered table-condensed">
						<thead>
						<tr>
							<th>维护人</th>
							<th>维护时间</th>
							<th>维护内容</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${maintainList}" var="maintain">
							<tr>
								<td>${maintain.userId.name}</td>
								<td><fmt:formatDate value="${maintain.mainTime}" pattern="yyyy-MM-dd"/></td>
								<td>${maintain.mainContent}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:if>
		<div class="form-actions">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>