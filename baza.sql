-- usuwamy foregin keys
ALTER TABLE SERWIS.PERSONALINFO DROP CONSTRAINT PERSONALINFOUSERID;
ALTER TABLE SERWIS.MAGAZYND DROP CONSTRAINT MAGAZYND_ID_CZESCI;
-- czyścimy tabele
TRUNCATE TABLE SERWIS.PERSONALINFO;
TRUNCATE TABLE SERWIS.USERD;
TRUNCATE TABLE SERWIS.CZESCID;
TRUNCATE TABLE SERWIS.MAGAZYND;
-- przywracamy foregin keys
ALTER TABLE SERWIS.PERSONALINFO 
    ADD CONSTRAINT PERSONALINFOUSERID 
    FOREIGN KEY (USER_ID)
    REFERENCES SERWIS.USERD(ID);
ALTER TABLE SERWIS.MAGAZYND 
    ADD CONSTRAINT MAGAZYND_ID_CZESCI 
    FOREIGN KEY (ID_CZESCI)
    REFERENCES SERWIS.CZESCID(ID);
-- dodajemy dane
-- konta
INSERT INTO SERWIS.USERD (id, username, password_hash, email, isonline, last_login, rmask) VALUES 
( 1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin@serwis.pl', false, '1970-01-01 00:00:00',255),
( 2, 'darek', 'b64a76fbfcbc7b85bc416a53240bb077', 'darek@serwis.pl', false, '1970-01-01 00:00:00',1),
( 3, 'robert', '684c851af59965b680086b7b4896ff98', 'robert@serwis.pl', false, '1970-01-01 00:00:00',2),
( 4, 'jakub', 'eccdacd4709395e97e6b19756c7b45c1', 'jakub@serwis.pl', false, '1970-01-01 00:00:00',8),
( 5, 'julia', 'c2e285cb33cecdbeb83d2189e983a8c0', 'julia@serwis.pl', false, '1970-01-01 00:00:00',0),
( 6, 'wojciech', 'e1b429b19a9e5931d30e72f14616a117', 'wojciech@serwis.pl', false, '1970-01-01 00:00:00',0),
( 7, 'klaudia', 'fc39b096375417b3e2df3d24551cf277', 'klaudia@serwis.pl', false, '1970-01-01 00:00:00',0),
( 8, 'tomasz', '2df8ce7d317c7d89dfa95be7695d2de0', 'tomasz@serwis.pl', false, '1970-01-01 00:00:00',0),
( 9, 'oliwia', '97a5292d4c024ba4b108e27ac98698b8', 'oliwia@serwis.pl', false, '1970-01-01 00:00:00',0),
( 10, 'sebastian', 'c2d628ba98ed491776c9335e988e2e3b', 'sebastian@serwis.pl', false, '1970-01-01 00:00:00',0),
( 11, 'amelia', '176226b2d51002d2590f048881560569', 'amelia@serwis.pl', false, '1970-01-01 00:00:00',0),
( 12, 'piotr', '99fdb06613cd9b8f328b6cadd98b1c23', 'piotr@serwis.pl', false, '1970-01-01 00:00:00',0),
( 13, 'karolina', '605fcc505ec12363ed7d9df00ea3d6c2', 'karolina@serwis.pl', false, '1970-01-01 00:00:00',0),
( 14, 'joanna', 'd979871f68a9e367eb3a5df8be7c4bf4', 'joanna@serwis.pl', false, '1970-01-01 00:00:00',0),
( 15, 'ewelina', '8134fb0ebd9f3de42c48b5022f2c939b', 'ewelina@serwis.pl', false, '1970-01-01 00:00:00',0),
( 16, 'marcin', 'd5fad0cda8f1079681ec510bb20a586c', 'marcin@serwis.pl', false, '1970-01-01 00:00:00',0);
INSERT INTO SERWIS.PERSONALINFO (id, user_id, name, address, phone1, phone2, type) VALUES 
( 1, 1, 'Adminiusz Wszechmocny', 'ul. Procesorowa 10; 51-777 Wrocław', '+48711234567', '', 2),
( 2, 2, 'Dariusz Magazyński', 'ul. Wózkarska 13/2; 51-650 Wrocław', '0048713480000', '', 2),
( 3, 3, 'Robert Psujka', 'al. Rozrabiaki 3/4; 00-777 Warszawa', '+48609123456', '', 0);
-- części (def)
INSERT INTO SERWIS.CZESCID (id, nazwa, model, producent, jednostka, cena_jednostkowa) VALUES
(1,'Procesor AMD Ryzen 3 1200 AM4', 'YD1200BBAEBOX','AMD','szt.',405.00),
(2,'Procesor Intel Core Extreme i9-7980XE 2,6 GHz LGA 2066 BoX (bez chlodzenia)', 'BX80673I97980X','Intel','szt.',7999.00),
(3,'ASUS GeForce GTX 1060 6GB GDDR5 EX-GTX1060-6G', 'EX-GTX1060-6G','ASUS','szt.',1679.00),
(4,'EVGA GeForce GTX 1070 SC GAMING, 08G-P4-6173-KR, 8GB GDDR5, ACX 3.0 & LED', '08G-P4-6173-KR','EVGA','szt.',2699.00),
(5,'Gigabyte GeForce GTX 1070 Aorus 8GB GDDR5 256Bit PCI-E (GV-N1070AORUS-8GD)', 'GV-N1070AORUS-8GD','Gigabyte','szt.',2719.00),
(6,'Monitor BenQ GW2760HS 27"', '9H.L9NLB.RBE','BenQ','szt.',799.00),
(7,'iiyama G-Master GB2560HSU-B1 RED EAGLE 24,5" FHD 1ms 144Hz PIVOT FreeSync', 'GB2560HSU-B1','iiyama','szt.',1049.00);
-- magazyn
INSERT INTO SERWIS.MAGAZYND (id, id_czesci, regal, polka, ilosc) VALUES
(1,1,1,1,5),
(2,1,2,3,2),
(3,2,1,2,3),
(4,5,1,2,4);