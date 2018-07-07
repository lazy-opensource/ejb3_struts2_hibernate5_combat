--������
create table t_hello_hibernate(
id number(10),constraint t_hello_hibernate_pk primary key(id),
name varchar2(20),
address varchar2(30),
age number(3),
gender number(2),
birthday date default sysdate not null,
create_time date default sysdate not null,
update_time date default sysdate not null
);
--����ɾ����־��
create table t_hello_hibernate_log as select * from t_hello_hibernate where 1 = 2;

--������������
create sequence hello_hibernate_seq
start with 1
increment by 1
maxvalue 9999999999
minvalue 1
nocache
nocycle;

--�������봥����
create or replace trigger insert_hello_hibernate_tri
before insert --����֮ǰ
  on t_hello_hibernate for each row --������t_hello_hibernate��ÿһ��
  when (new.id is null) --���idֵΪ�գ���ʹ����������
    begin
      select hello_hibernate_seq.nextval into :new.id from dual;
end;

--����ɾ����¼��־������
create or replace trigger del_hello_hibernate_tri
before delete
on t_hello_hibernate for each row
begin
  insert into t_hello_hibernate_log(id,name,address,age,gender,birthday,create_time,update_time)
  values(
  :old.id,
  :old.name,
  :old.address,
  :old.age,
  :old.gender,
  :old.birthday,
  :old.create_time,
  :old.update_time
  );
end;
--ȷ����û�����ݵ�
select * from t_hello_hibernate;
select * from t_hello_hibernate_log;
--����id����������
insert into t_hello_hibernate(name,address,age,gender)
values('laige','guangdong',25,1);
commit;--�ύ����
--�鿴�Ƿ�ɹ�����
select * from t_hello_hibernate;
--����ɾ��
delete from t_hello_hibernate where id = 1;
commit;--�ύ����
--ȷ�����
select * from t_hello_hibernate;--���û������
select * from t_hello_hibernate_log;--�����һ������
