drop user c##campingez;
--��������
CREATE USER c##campingez IDENTIFIED BY campingez
    DEFAULT TABLESPACE users
    TEMPORARY TABLESPACE temp
    PROFILE DEFAULT;
--���Ѻο�
GRANT CONNECT, RESOURCE TO c##campingez;
GRANT CREATE VIEW, CREATE SYNONYM TO c##campingez;
GRANT UNLIMITED TABLESPACE TO c##campingez;
--�� Ǯ��
ALTER USER c##campingez ACCOUNT UNLOCK;