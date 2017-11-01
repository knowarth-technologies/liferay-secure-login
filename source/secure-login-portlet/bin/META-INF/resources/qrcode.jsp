<%@ include file="init.jsp"%>
<liferay-ui:error message="${errorMessage}" key="error.reg.key.not.present"/>

<c:if test="${empty errorMessage}">

	<portlet:resourceURL id="generateQRCode" var="generateQRCodeUrl">
		<portlet:param name="action" value="generateQRCode" />
		<portlet:param name="userName" value="${userName}" />
	</portlet:resourceURL>

	<portlet:resourceURL id="checkIfTokenIsValidated" var="checkIfTokenIsValidatedUrl">
		<portlet:param name="action" value="checkIfTokenIsValidated"/>
		<portlet:param name="userName" value="${userName}" />
	</portlet:resourceURL>
	
<div class="secure_login">
	<div class="title"><liferay-ui:message key="secure.login.label" /></div>

	<span class="user"><b><liferay-ui:message key="user.name.you.choose" /></b>${userName}(${authType})</span>
	
	<div class="timer-container" id="run-the-timer">
		<span><liferay-ui:message key="qrcode.valid.until" /></span>
		<span id="seconds" class="second">60</span> <liferay-ui:message key="qrcode.valid.seconds" />
	</div>
	
	<span id="<portlet:namespace/>msg" class="hide alert alert-danger"></span>

	<div id="qrCodeImage">
		<img src="${generateQRCodeUrl}" alt="ORCode">
	</div>

	<form action="${refreshPageUrl}" method="POST" class="hide" id="<portlet:namespace/>refreshPageFrm">
		<input type="hidden" name="userName" value="${userName}">
	</form>
</div>

<script type="text/javascript">
var secureLoginInterval = null;
$(document).on('ready',function(){
		var timeIntervalID;
		secureLoginInterval = setInterval(ajaxCall,3000);
		
		$("a").click(function(e){
			clearInterval(secureLoginInterval);
		});
		
		startTimer();
});
	

	
	function ajaxCall(){
	    AUI().use('aui-io-request', function(A){
	        A.io.request('${checkIfTokenIsValidatedUrl}', {
	               method: 'post',
	                data: {
	            	   '<portlet:namespace />userName': '${userName}'
	               },
	               on: {
	                   	success: function() {
	                        var msg = this.get('responseData');
	                        if(msg != 'undefined' && msg!='' && msg !=null ){
	                        	var msgJson = JSON.parse(msg);
	                        	if(msgJson.doPolling == false){
	                        		clearInterval(secureLoginInterval);
	                        	}
	                        	var msgNode = A.one('#<portlet:namespace/>msg');
	                            msgNode.setHTML(msgJson.loginMsg);
	                            if(msgJson.loginMsg !=""){
	                            	msgNode.replaceClass('hide', 'show');
	                            	document.getElementById('qrCodeImage').remove();
	                            }else{
	                            	msgNode.replaceClass('show', 'hide');
	                            }
	                        	if(msgJson.isSecureLoginTokenValidated == true){
	                        		clearInterval(timeIntervalID);
	                        		document.getElementById('run-the-timer').remove();
	                        		msgNode.replaceClass('alert-danger', 'alert-success');
	                        		document.getElementById("<portlet:namespace/>refreshPageFrm").submit();
	                        	}else{
	                        		msgNode.replaceClass('alert-success', 'alert-danger');
	                        	}
	                        }
	                    }
	              }
	        });
	    });
	}
	
	function startTimer() {
		var timer = 59;
		timeIntervalID = setInterval(function () {
			minutes = parseInt(timer / 60, 10);
			seconds = parseInt(timer % 60, 10);
			seconds = seconds < 10 ? "0" + seconds : seconds;
		
			if(seconds <= 20){
				$('#seconds').removeClass('second').addClass('redsecond');
				document.querySelector('span.redsecond').textContent = seconds;
			}else{
				document.querySelector('span.second').textContent = seconds;	
			}
		
			if (--timer < 0) {
				timer = 0;
				if (timer == 0) {
					clearInterval(timeIntervalID);
				}
			}
		},1000);
	}
</script>
</c:if>

<style>
.secure_login {
	padding: 0px;
	background: #fff;
	color: #fff;
	width: 400px;
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