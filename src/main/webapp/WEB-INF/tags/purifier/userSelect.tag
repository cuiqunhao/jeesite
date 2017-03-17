<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="labelName" type="java.lang.String" required="true" description="输入框名称（Name）"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="smallBtn" type="java.lang.Boolean" required="false" description="缩小按钮显示"%>
<script type="text/javascript">
	function setData(id,name){
		alert("ccc");
		$("#${id}").val(id);
		$("#${labelName}").val(name);
	}
	$(document).ready(function() {
		alert("aaaa");
		$("#salesmanButton,#salesmanName").click(function(){
			alert("bbb");
			// 正常打开
			top.$.jBox.open(
					"iframe:${ctx}/sys/user/selectList",
					"选择用户",
					$(top.document).width()-220,
					$(top.document).height()-220,
					{
						<%--ajaxData:{selectIds: $("#${id}Id").val()},--%>
						buttons:{"确定":"ok","关闭":true},
						submit:function(v, h, f){

						},
						loaded:function(h){
							$(".jbox-content", top.document).css("overflow-y","hidden");
						}
					});
		});
	});
</script>
<div class="input-append">
	<input id="${id}" name="${id}" class="${cssClass}" type="hidden" />
	<input id="${labelName}" name="${labelName}" type="text" class="${cssClass}" style="${cssStyle}"/>
	<a id="${labelName}Button" href="javascript:" class="btn">&nbsp;<i class="icon-search"></i>&nbsp;</a>&nbsp;&nbsp;
</div>
