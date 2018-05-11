TRUNCATE TABLE SERWIS.USERD;
INSERT INTO SERWIS.USERD (id, username, password_hash, email, isonline, last_login, rmask) VALUES 
( 1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin@serwis.pl', false, '1970-01-01 00:00:00',255),
( 2, 'darek', 'b64a76fbfcbc7b85bc416a53240bb077', 'darek@serwis.pl', false, '1970-01-01 00:00:00',1),
( 3, 'robert', '684c851af59965b680086b7b4896ff98', 'robert@serwis.pl', false, '1970-01-01 00:00:00',0);