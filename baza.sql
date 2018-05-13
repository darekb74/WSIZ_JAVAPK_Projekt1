-- usuwamy foregin key
ALTER TABLE SERWIS.PERSONALINFO DROP CONSTRAINT PERSONALINFOUSERID;
TRUNCATE TABLE SERWIS.PERSONALINFO;
TRUNCATE TABLE SERWIS.USERD;
-- przywracamy foregin key
ALTER TABLE SERWIS.PERSONALINFO 
    ADD CONSTRAINT PERSONALINFOUSERID 
    FOREIGN KEY (USER_ID)
    REFERENCES SERWIS.USERD(ID); 
INSERT INTO SERWIS.USERD (id, username, password_hash, email, isonline, last_login, rmask) VALUES 
( 1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin@serwis.pl', false, '1970-01-01 00:00:00',255),
( 2, 'darek', 'b64a76fbfcbc7b85bc416a53240bb077', 'darek@serwis.pl', false, '1970-01-01 00:00:00',1),
( 3, 'robert', '684c851af59965b680086b7b4896ff98', 'robert@serwis.pl', false, '1970-01-01 00:00:00',0);
INSERT INTO SERWIS.PERSONALINFO (id, user_id, name, address, phone1, phone2, type) VALUES 
( 1, 1, 'Adminiusz Wszechmocny', 'ul. Procesorowa 10; 51-777 Wrocław', '+48711234567', '', 2),
( 2, 2, 'Dariusz Magazyński', 'ul. Wózkarska 13/2; 51-650 Wrocław', '0048713480000', '', 2),
( 3, 3, 'Robert Psujka', 'al. Rozrabiaki 3/4; 00-777 Warszawa', '+48609123456', '', 0);