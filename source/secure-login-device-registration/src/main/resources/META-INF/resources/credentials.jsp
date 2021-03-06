<%@ include file="init.jsp"%>
<liferay-ui:error message="${errorMessage}" key="invalid-credential" />
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

.secure_login .timer-container {
	text-align: center;
	padding: 10px 5px;
	color: #000;
}

.secure_login .timer-container span:first-child {
	display: block;
	text-align: left;
}

.secure_login .timer-container span.second {
	background: green;
	color: #fff;
	padding: 10px;
	border-radius: 50px;
	display: inline-block;
	min-width: 60px;
	text-align: center;
	font-size: 28px;
}

.secure_login .timer-container span.redsecond {
	background: red;
	color: #fff;
	padding: 10px;
	border-radius: 50px;
	display: inline-block;
	min-width: 60px;
	text-align: center;
	font-size: 28px;
}

.secure_login #qrCodeImage {
	text-align: center;
}

.secure_login .alert-danger {
	margin: 20px;
}

.secure_login .timer-container {
	margin-top: 0px;
	border-top: 1px solid #f2f2f2;
	padding-top: 10px;
	margin: 10px;
}

.secure_login span.user {
	color: #000;
	padding: 10px 15px;
	display: block;
	text-transform: capitalize;
}
</style>
<portlet:actionURL var="processRegistrationUrl" name="processRegistration">
</portlet:actionURL>

<div class="secure_login">
	<div class="title"><liferay-ui:message key="device.registration.label" /></div>
		<aui:form action="${processRegistrationUrl}" method="POST">
			<aui:input name="userName" type="text" label="User Name (${authType})">
				<aui:validator name="required"/>
			</aui:input>
			<aui:input name="password" type="password" label="Password">
				<aui:validator name="required"/>
			</aui:input>
			<aui:button type="submit" value="Send">
			</aui:button>
		</aui:form>
</div>
	