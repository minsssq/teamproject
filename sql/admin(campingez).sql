drop user c##campingez;
--계정생성
CREATE USER c##campingez IDENTIFIED BY campingez
    DEFAULT TABLESPACE users
    TEMPORARY TABLESPACE temp
    PROFILE DEFAULT;
--권한부여
GRANT CONNECT, RESOURCE TO c##campingez;
GRANT CREATE VIEW, CREATE SYNONYM TO c##campingez;
GRANT UNLIMITED TABLESPACE TO c##campingez;
--락 풀기
ALTER USER c##campingez ACCOUNT UNLOCK;