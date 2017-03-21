<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="idValue" type="java.lang.String" required="false" description="id值"%>
<%@ attribute name="labelName" type="java.lang.String" required="true" description="输入框名称（Name）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="false" description="输入框值"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="smallBtn" type="java.lang.Boolean" required="false" description="缩小按钮显示"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#${labelName}Button,#${labelName}").click(function(){
			// 正常打开
			top.$.jBox.open(
					"iframe:${ctx}/ware/wareSelectList",
					"选择用户",
					$(top.document).width()-220,
					$(top.document).height()-220,
					{
						<%--ajaxData:{selectIds: $("#${id}Id").val()},--%>
						buttons:{"确定":"ok","关闭":true},
						submit:function(v, h, f){
							$("[id='${id}']").val(h.find("iframe")[0].contentWindow.selectId);
							$("[id='${labelName}']").val(h.find("iframe")[0].contentWindow.selectName);
						},
						loaded:function(h){
							$(".jbox-content", top.document).css("overflow-y","hidden");
						}
					});
		});
	});
</script>
<div class="input-append">
	<input id="${id}" name="${id}" class="${cssClass}" type="hidden" value="${idValue}"/>
	<input id="${labelName}" name="${labelName}" type="text" class="${cssClass}" style="${cssStyle}" value="${labelValue}"/>
	<a id="${labelName}Button" href="javascript:" class="btn">&nbsp;<i class="icon-search"></i>&nbsp;</a>&nbsp;&nbsp;
</div>
