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
									tr.children("td:eq(4)").append("<input type='text' name='goodList["+rowNum+"].usefor' value='"+d+"' />");
									tr.children("td:eq(3)").append("<input type='text' name='goodList["+rowNum+"].appNum' value='"+c+"' />");

									rowNum++;
								},
								loaded:function(h){
									$(".jbox-content", top.document).css("overflow-y","hidden");
								}
							});
				});
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
	<li><a href="${ctx}/goodsApp/list/">申请列表</a></li>
	<li class="active">
		<a href="${ctx}/goodsApp/form?id=${goodsApp.id}">申请单${not empty goodsApp.id?'修改':'添加'}
		</a>
	</li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="goodsApp" action="${ctx}/goodsApp/save" method="post" class="form-horizontal">
	<form:hidden path="id"/>
	<sys:message content="${message}"/>
	<div class="control-group">
		<label class="control-label">申请编号:</label>
		<div class="controls">
			<form:input path="appNo" htmlEscape="false" maxlength="64" class="required" disabled="${goodsApp.id != null || not empty goodsApp.id ?true:false}"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">申请人:</label>
		<div class="controls">
			<purifier:userSelect id="applicantUser.id" labelName="applicantUserLabe" idValue="${goodsApp.applicantUser.id}" labelValue="${goodsApp.applicantUser.name}"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">收货人:</label>
		<div class="controls">
			<form:input path="consignee" htmlEscape="false" maxlength="20" class="required"  />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">收货人电话:</label>
		<div class="controls">
			<form:input path="consigneePhone" htmlEscape="false" maxlength="11" class="required digits"  />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">收货人地址:</label>
		<div class="controls">
			<form:input path="consigneeAddress" htmlEscape="false" maxlength="11" class="required digits" />
		</div>
	</div>

	<div class="control-group">
		<label class="control-label">备注:</label>
		<div class="controls">
			<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">申请商品:</label>
		<div class="controls">
			<input id="goodsBtnSubmit" class="btn btn-primary" type="button" value="添加商品"  ${goodsApp.id != null || not empty goodsApp.id?"disabled":""}/>
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
						<td><input type="text"  name="goodList[${s.index }].usefor" value="${good.usefor}" /></td>
						<td><input type="text" name="goodList[${s.index }].appNum" value="${good.appNum}" /></td>
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
			<purifier:wareSelect id="ware.id" labelName="shipAddress" disable="true" idValue="${goodsApp.ware.id}" labelValue="${goodsApp.ware.wareName}"/>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label">物流信息:</label>
		<div class="controls">
			<form:input path="logistics" htmlEscape="false" maxlength="120" class="required" disabled="true"/>
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