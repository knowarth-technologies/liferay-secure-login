SecureLogin
A two-factor secure login (With Your Mobile device) component for Liferay 6.2/DXP platform
Accessing any system with just single Password is an old way today. Single password authentication is not strong enough and hacker can steal it with multiple methods. One workaround is to create and remember complex passwords but then it’s not good idea and users are not willing enough to use it. This is the reason Two Factor Authentication (2FA) is becoming so essential today.
We at KNOWARTH created a 2FA component called “SecureLogin” which is useful for easy and secure login on Liferay platform with your mobile device.

Problem
	Out of the box, Liferay doesn’t provide 2FA mechanism.

Pre-Requisite
	We have developed mobile app to securely login with 2FA mechanism into Liferay. You need to install and configure mobile app.

Solution
	This component is not an option of existing login mechanism of Liferay platform. Instead we have used Liferay auto login feature to implement 2FA and make existing login mechanism more secure and ease of use.

How does it work?
	Smartphone becomes unique identification for any individual today. We have developed mobile App (for Android and IOS) to securely login on Liferay.
	User need to first install the mobile app, register that app (by just scanning QR code) with Liferay and you are ready to login. Your mobile app (which is uniquely installed on your mobile device) would work as a second authentication factor while login.

Below are the steps to configure secure login with 2FA on Liferay platform.
	Copy the. lpkg (Jars) file in deploy folder of Tomcat Application Server
	Copy the. apk file in your android mobile and install it.
	Set the secure user pin for app.
	Create following public pages
	Device Registration
	Place Device Registration Portlet on this page.
	Secure User Login.
	Place Secure Login Portlet on this page.
	Registration
	You need to first provide Liferay credential. Next screen shows a QR code. You can register your mobile app by scanning this QR code. It basically stores following 3 things in your app.
	Email Address (You can’t modify)
	Registration Key (You can see last few characters of it).
	Host of your Liferay server. (In your Mobile App, you can update the host in case if it’s required)
	Secure Login
	Once registration is done, you can use your mobile app to securely login.
	To make sure 2FA is implemented, system will first ask for credentials. You need to give your Liferay credentials.
	Next screen shows QR code (for login) which you need to scan through your mobile app.
	Once validated, Liferay is automatically logged in and you no need to press any key or button on screen.
	This way, only you who has registered mobile app securely can login and Liferay credentials alone is not enough to login into system.
	You can set 4-digit pin on your mobile app to make it even more secure.
