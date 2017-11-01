<%@ include file="init.jsp"%>
<liferay-ui:error message="${errorMessage}" key="invalid-credential"/>
<style>
.secure_login {
	padding: 0px;
	background: #fff;
	color: #fff;
	/* width: 400px; */
	margin: auto;
	border: 1px solid #f2f2f2;
}

.title {
	background: #365D9E;
	padding: 10px 15px;
}

.secure_login.portlet-title-text {
	padding: 10px 15px;
	margin: 0px;
	max-width: 100%;
}

.secure_login .portlet-content-container {
	padding: 10px 15px;
	background: #fff;
	color: #000;
	text-transform: capitalize;
}

.secure_login .alert-danger {
	margin: 20px;
}

.secure_login span.user {
	color: #000;
	padding: 10px 15px;
	display: block;
	text-transform: capitalize;
}

</style>
<div class="secure_login">
<div class="title"><liferay-ui:message key="secure.login.label" /></div>
	<c:choose>
		<c:when test="${isLoggedIn eq false }">
			<portlet:renderURL var="sendUserNameUrl">
				<portlet:param name="mvcRenderCommandName" value="processForQRCode" />
			</portlet:renderURL>
	
				<aui:form action="${sendUserNameUrl}" method="POST">
					<aui:input name="userName" type="text" label="User Name (${authType})">
						<aui:validator name="required"/>
					</aui:input>
					<aui:input name="password" type="password" label="Password">
						<aui:validator name="required"/>
					</aui:input>
					<aui:button type="submit" value="Send">
					</aui:button>
				</aui:form>
		</c:when>
		<c:otherwise>
			<span class="user"><b><liferay-ui:message key="your.are.loggedin.as"/></b>${userFullName}</span>
		</c:otherwise>
	
	</c:choose>
</div>

