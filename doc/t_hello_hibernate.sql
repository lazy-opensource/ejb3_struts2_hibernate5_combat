--创建表
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
--创建删除日志表
create table t_hello_hibernate_log as select * from t_hello_hibernate where 1 = 2;

--创建自增序列
create sequence hello_hibernate_seq
start with 1
increment by 1
maxvalue 9999999999
minvalue 1
nocache
nocycle;

--创建插入触发器
create or replace trigger insert_hello_hibernate_tri
before insert --插入之前
  on t_hello_hibernate for each row --作用于t_hello_hibernate表每一行
  when (new.id is null) --如果id值为空，则使用序列自增
    begin
      select hello_hibernate_seq.nextval into :new.id from dual;
end;

--创建删除记录日志触发器
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
--确定是没有数据的
select * from t_hello_hibernate;
select * from t_hello_hibernate_log;
--测试id自增触发器
insert into t_hello_hibernate(name,address,age,gender)
values('laige','guangdong',25,1);
commit;--提交事务
--查看是否成功新增
select * from t_hello_hibernate;
--测试删除
delete from t_hello_hibernate where id = 1;
commit;--提交事务
--确定结果
select * from t_hello_hibernate;--结果没有数据
select * from t_hello_hibernate_log;--结果有一条数据
