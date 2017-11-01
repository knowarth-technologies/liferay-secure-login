create table SLS_SecureLogin (
	secureLoginId LONG not null primary key,
	userId LONG,
	registrationKey VARCHAR(75) null,
	encodeToken VARCHAR(75) null,
	encodeTokenDate DATE null,
	qrCodeToken VARCHAR(75) null,
	qrCodeTokenDate DATE null,
	tokenValidated VARCHAR(75) null
);