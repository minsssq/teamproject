--������̺����
drop table members;
create table members(
    mname varchar2(20) not null,
    mid varchar2(20) primary key,
    pw varchar2(20) not null,
    phone varchar2(13) check(phone like '%-%') not null,
    email varchar2(50) not null unique,
    nickname varchar2(20)not null unique,
    maddress varchar2(50),
    mtype char(1) not null,
    companyname varchar2(20),
    businessnumber varchar2(20) check(businessnumber like '%-%')
);

--ķ�����̺����
drop table camping;
create table camping(
    cnumber number(4) primary key,
    mid varchar2(20) not null,
    cname varchar2(20),
    caddress varchar2(50) unique not null,
    camptel varchar2(20) check(camptel like '%-%'),
    ctype char(1) check(ctype in('g','a')),
    operdate varchar2(20),
    homepage  varchar2(50),
    ctitle varchar2(30),
    ctext clob,
    priceweekday number(6),
    priceweekend number(6),
    toilet char(1) check(toilet in('o','x')),
    mart char(1) check(mart in('o','x')),
    udate date DEFAULT sysdate,
    foreign key (mid) REFERENCES members(mid)
);

--ķ�����̸������̺����
drop table camparea;
create table camparea(
    cnumber number(4),
    area number(2),
    capacitys number(2) not null,
    primary key(cnumber,area),
    foreign key (cnumber) REFERENCES camping(cnumber) ON DELETE CASCADE
);

--�������̺����
drop table orders;
create table orders(
    onumber number(4),
    cnumber number(4) not null,
    area number(2) not null,
    mid varchar2(20) not null,
    phone varchar2(13) check(phone like '%-%') not null,
    headcount number(2) not null,
    checkin varchar2(15) not null,
    checkout varchar2(15) not null,
    primary key(onumber),
    foreign key (cnumber,area) REFERENCES camparea(cnumber,area),
    foreign key (mid) REFERENCES members(mid)
);

--����Ʈ���̺����
drop table post;
create table post(
    pnumber number(4) primary key,
    nickname varchar2(20) not null,
    ptitle varchar2(50) not null,
    ptext clob,
    ptype char(1) check(ptype in('n','f')) not null,
    udate date DEFAULT TO_DATE(sysdate,'yyyy-mm-dd hh24:mi:ss')
);

--�ڸ�Ʈ���̺����
drop table comments;
create table comments(
    conumber number(4) primary key,
    pnumber number(4) not null,
    nickname varchar2(20) not null,
    cotext varchar2(100),
    udate date DEFAULT TO_DATE(sysdate,'yyyy-mm-dd hh24:mi:ss'),
    foreign key (pnumber) REFERENCES post(pnumber) ON DELETE CASCADE
);

--��������
alter table members add check(length(pw) >= 8);
alter table members add check(mtype in('n','b'));
alter table members add check(regexp_like(pw,'[!-/]') or regexp_like(pw,'[:-@]') 
or regexp_like(pw,'[{-~]') or regexp_like(pw,'[[-`]'));
alter table members add unique(businessnumber);

--����������
drop sequence cnumber_seq;
create sequence cnumber_seq;
drop sequence onumber_seq;
create sequence onumber_seq;
drop sequence pnumber_seq;
create sequence pnumber_seq;
drop sequence conumber_seq;
create sequence conumber_seq;

--member ����--
insert into members(mname,mid,pw,phone,email,nickname,mtype,businessnumber)
     values('ȫ�浿','test123', 'test[123899','010-1234-5678','test1@naver.com','����1','n','010-1234-5678');

insert into members(mname,mid,pw,phone,email,nickname,mtype,businessnumber)
     values('ȫ�浿','test124', 'test[123899','010-1234-5678','test2@naver.com','����2','n',null);

insert into members(mname,mid,pw,phone,email,nickname,mtype,businessnumber)
     values('ȫ�浿','test125', 'test[123899','010-1234-5678','test3@naver.com','����3','n',null);

--camping ����--
insert into CAMPING
(cnumber,mid,cname,caddress,camptel,ctype,operdate,homepage,ctitle,ctext,priceweekday,priceweekend,toilet,mart)
values (cnumber_seq.nextval,'test123','KHķ����','��� ���� ������','0000-1234-5678','a','09:00~21:00',
'https://www.khcamping.com','ķ��','testtesttest',30000,40000,'o','x');

--camparea ����--
insert into CAMPAREA
(cnumber,area,capacitys)
values((select max(cnumber) from camping),2,3);

--orders ����--
insert into ORDERS
(onumber,cnumber,area,mid,phone,headcount,checkin,checkout)
values(onumber_seq.nextval,1,2,'test123','010-1234-5678',3,
2023011110,2023011209);

--post ����--
insert into POST
(pnumber,nickname,ptitle,ptext,ptype,udate)
values(pnumber_seq.nextval,'����1','�Խñ�1','testtesttest','n');

--comments ����--
insert into COMMENTs
(conumber,pnumber,nickname,cotext)
values(conumber_seq.nextval,2,'����1','����Դϴ�');

ALTER SESSION SET NLS_DATE_FORMAT='YYYY/MM/DD HH:MI:SS';

select  *  from members;
select  *  from camping;
select  *  from camparea;
select  *  from orders;
select  *  from post;
select  *  from comments;