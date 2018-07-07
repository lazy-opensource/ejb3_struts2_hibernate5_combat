----------------------- plsql �½����� -------------------------------------
--ɾ����
drop table t_hello_plsql;

-- ������t_hello_plsql
create table t_hello_plsql (
       id number(10, 0), constraint t_hello_plsql_id_pk primary key(id),
       first_name varchar2(10) NOT NULL,
       last_name varchar2(10),
       full_name varchar2(20),
       address varchar2(30),
       gender number(1),
       age number(3),
       birthday date default sysdate NOT NULL
);

--��ѯ
select * from t_hello_plsql;

--ɾ������
drop sequence t_hello_plsql_seq;
-- ����������������
create sequence t_hello_plsql_seq
       minvalue 0--��Сֵ
       maxvalue 9999999999--���ֵ
       start with 0--��0��ʼ
       increment by 1--����Ϊ1
       nocycle--��ѭ��
       nocache;--������

commit;
--ͨ�������ֵ�user_sequences��ѯ����
select * from user_sequences where sequence_name = 't_hello_plsql_seq';
--��ձ�
delete from t_hello_plsql;
--truncate table t_hello_plsql;
--����һЩ��������
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'zhang','san','zhangsan','�㶫','23','1',sysdate);
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'wang','wu','wangwu','�Ϻ�','25','1',sysdate);
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'zhao','liu','zhaoliu','����','22','1',sysdate);
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'cheng','li','chengli','��Դ','22','2',sysdate);
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'huang','hong','huanghong','��Դ','21','2',sysdate);
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'cai','li','caili','��ݸ','20','2',sysdate);
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'yang','hong','yanghong','����','12','2',sysdate);
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'huang','wenxi','huangwenxi','��ݸ','16','2',sysdate);
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'lin','biao','linbiao','�麣','40','1',sysdate);
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'zeng','guofan','zengguofan','����','36','1',sysdate);
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'cheng','rijiang','chengrijiang','����','38','1',sysdate);
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'lai','changxi','laichangxi','����','42','1',sysdate);

--�ύ
commit;
select * from t_hello_plsql;

--��¼�����﷨
type record_type is record(
     field_1 type_1,
     field_2 type_2,
     field_3 type_3
     ...      ...
);

--��¼��������һ ʹ�û������Ͷ����¼����
declare 
   --����һ���������ͣ���¼���ͣ�
   type example_record is record(
        last_name varchar2(10),
        first_name varchar2(10),
        age number(3)
   );
     
   --������� ����Ϊ���涨��ĸ�������-��¼����
   v_example example_record;
begin
  v_example.last_name := 'zhiyuan';
  v_example.first_name := 'lai';
  v_example.age := 25;
  dbms_output.put_line('fullName:' || v_example.first_name || 
  v_example.last_name || ' ' || 'age:' || v_example.age);

end;
--��¼�������Ӷ� ʹ�û������Ͷ����¼����
declare
  type hello_plsql_record is record(
       v_id number(30),
       v_first_name varchar2(10),
       v_last_name varchar2(10),
       v_address varchar2(30),
       v_birthday date
  );
  
  v_hello_plsql hello_plsql_record;
begin
  select id, first_name, last_name, address, birthday into v_hello_plsql from t_hello_plsql where id = 1;
  dbms_output.put_line('id:' || v_hello_plsql.v_id || ' ' ||
                        'fullName:' || v_hello_plsql.v_first_name || v_hello_plsql.v_last_name || ' ' ||
                        'address:' || v_hello_plsql.v_address || ' ' ||
                        'birthday:' || v_hello_plsql.v_birthday);
end;

--��¼���������� ʹ�ð����ݱ���(%type)
declare
   v_name varchar2(20);
   type hello_plsql_record is record(
        v_first_name v_name%type,
        v_last_name t_hello_plsql.last_name%type,
        v_birthday t_hello_plsql.birthday%type,
        v_age number(3)
   );
   v_hello_plsql hello_plsql_record;
begin
  select first_name, last_name, birthday, age into v_hello_plsql 
        from t_hello_plsql where id = 2;
  dbms_output.put_line(
        'fullName:' || v_hello_plsql.v_first_name || v_hello_plsql.v_last_name || ' ' ||
        'birthday:' || v_hello_plsql.v_birthday || ' ' ||
        'age:' || v_hello_plsql.v_age
  );
end;

--��¼���������� ʹ�ð󶨱���(%rowtype)
declare
   v_hello_plsql t_hello_plsql%rowtype;
begin
  select * into v_hello_plsql from t_hello_plsql
    where id = 3;
    
  dbms_output.put_line(
     'fullName:' || v_hello_plsql.first_name || v_hello_plsql.last_name || ' ' ||
     'gender:' || v_hello_plsql.gender
  );
end;

select * from t_hello_plsql;

--Ƕ�ױ�����һ
declare
  type hello_table_type is table of t_hello_plsql%rowtype;
  --ʹ�øü��ϵĹ�������ʼ����
  hello_table hello_table_type := hello_table_type();
  v_count number;
begin
  select count(*) into v_count from t_hello_plsql;
  hello_table.extend(v_count);
  
  for i in 1..v_count loop
    select tt.id,tt.first_name,tt.last_name,tt.full_name,tt.address,tt.gender,tt.age,tt.birthday
    into hello_table(i) from (select rownum rn, t.* from t_hello_plsql t)tt where tt.rn = i;
    
    dbms_output.put_line(
      'id:' || hello_table(i).id || '  ' ||
      'fullName:' || hello_table(i).full_name
  );
  end loop;
end;

--Ƕ�ױ����Ӷ�
declare
   type hello_plsql_table_type is table of t_hello_plsql%rowtype;
   hello_table hello_plsql_table_type := hello_plsql_table_type();
begin
  hello_table.extend(5);
  hello_table.trim(2); --ȥ������ĩβ��2��
  
  for i in 1..3 loop
 
    select * into hello_table(i) from t_hello_plsql where id = i;  
  
    dbms_output.put_line('id:' || hello_table(i).id);
  end loop;
end;

--������ֵ����
declare
   v_number number(2) := 10;
   v_string varchar2(20) := 'hello world';
   v_boolean boolean := true;
   v_to_date date := to_date('20170822','yyyy-MM-dd');
   v_to_number number := to_number('9527');
   v_to_char char := to_char('t');
   v_table_id t_hello_plsql.id%type := 20;
   v_table_birthday t_hello_plsql.birthday%type := sysdate;
   v_table_full_name t_hello_plsql.full_name%type;
   v_table_age t_hello_plsql.age%type;
begin 
  select t.first_name || t.last_name, t.age into 
    v_table_full_name, v_table_age from (select rownum rn, tt.* from 
       t_hello_plsql tt)t where t.rn = 1;
       
  dbms_output.put_line('v_number:' || v_number);
  dbms_output.put_line('v_string:' || v_string);
  if v_boolean then
    dbms_output.put_line('true');
    else
      dbms_output.put_line('false');
  end if;
  dbms_output.put_line('v_to_date:' || v_to_date);
  dbms_output.put_line('v_to_number:' || v_to_number);
  dbms_output.put_line('v_to_char:' || v_to_char);
  dbms_output.put_line('v_table_id:' || v_table_id);
  dbms_output.put_line('v_table_birthday:' || v_table_birthday);
  dbms_output.put_line('v_table_full_name:' || v_table_full_name);
  dbms_output.put_line('v_table_age:' || v_table_age);
end;

--����ע������
declare

   v_username varchar2(32) := 'laizhiyuan'; --�û���
   v_password number(20) := 666666; --����
begin
  /*
  ���ǵ�½У��
  �û�������Ϊ:laizhiyuan
  ���������:666666
  */
  
  if (v_username != 'laizhiyuan') then
    dbms_output.put_line('�û�������!');
    elsif (v_password != 666666) then
      dbms_output.put_line('�������!');
      else
         dbms_output.put_line('��¼�ɹ�!');
  end if;

end;

--ʵս-��
declare
   v_id number(10) := t_hello_plsql_seq.nextval;
   v_first_name varchar2(10) := 'lai';
   v_last_name varchar2(10) := 'ge';
   v_address varchar2(30) := '����';
   v_birthday date := to_date('1999.01,01','yyyy-MM-dd');
   v_gender number := 1;
   v_age number := 20;
   
   v_count number;
begin 
  select count(1) into v_count from t_hello_plsql;  
  dbms_output.put_line('����֮ǰ��������' || v_count);
  insert into t_hello_plsql(
    id, 
    first_name, 
    last_name,
    full_name,
    address,
    gender,
    age,
    birthday)
    values(
    v_id, 
    v_first_name, 
    v_last_name, 
    v_first_name || v_last_name,
    v_address,
    v_gender,
    v_age,
    v_birthday);

  select count(1) into v_count from t_hello_plsql;  
  dbms_output.put_line('����֮���������' || v_count);
end;


--ʵս-ɾ
declare
   --��̬ɾ��
   v_id number := &id;
   v_count number;
begin
  select count(1) into v_count from t_hello_plsql;  
  dbms_output.put_line('ɾ��֮ǰ��������' || v_count);
  
  delete from t_hello_plsql where id = v_id;
  
  select count(1) into v_count from t_hello_plsql;  
  dbms_output.put_line('ɾ��֮���������' || v_count);
end;

--if else ����
declare
   v_simple_num number := 10;
   v_result varchar2(30);
begin
  if (v_simple_num > 10) then
    v_result := '����10';
    else
      v_result := 'С��10';
  end if;
  
   dbms_output.put_line(v_result);
end;

select * from t_hello_plsql;

--case...when...then...when...then...else...end ����
declare
   v_age t_hello_plsql.age%type;
   v_id t_hello_plsql.id%type := &id;
   v_result varchar2(30) := null;
begin
   select age into v_age from t_hello_plsql where id = v_id;
  
   case 
     when v_age < 20 then
       v_result := '������';
     when v_age < 30 then
       v_result := '������';
     when v_age < 40 then
       v_result := '������';
     else
       v_result := '���ϲ�������';
    end case;
    dbms_output.put_line(v_result);
end;


--goto ����
declare

begin
   goto test_goto;
   
   <<test_goto>>
   dbms_output.put_line('Hello World��');
end;


--ѭ��-loop
declare
   v_count number := 10;
begin 
  loop
    dbms_output.put_line('count:' || v_count);
    v_count := v_count -1;
    exit when (v_count < 0);
  end loop;
end;

--ѭ��-while
declare
   v_count number := 0;
begin
  while (v_count < 10) loop
    dbms_output.put_line('count:' || v_count);
    v_count := v_count + 1;
  end loop;
end;

--ѭ��-for
declare
   v_count number := 5;
begin
  for i in 1..v_count loop
     dbms_output.put_line('count:' || i);
  end loop;
  
  dbms_output.put_line('---------------reverse--------------');
  
  for i in reverse 1..v_count loop
     dbms_output.put_line('count:' || i);
  end loop;
end;

--��ʾ�α�����һ
declare
  --�����α� ���Դ���
  cursor c_hello_plsql(beginRow in number default 1, endRow in number default 10) 
        is select t.id, t.first_name,
      t.last_name, t.age from (select rownum rn, tt.* from t_hello_plsql tt)t where 
        t.rn > beginRow and t.rn < endRow order by age desc;
        
   v_id t_hello_plsql.id%type;
   v_first_name t_hello_plsql.first_name%type;
   v_last_name t_hello_plsql.last_name%type;
   v_age t_hello_plsql.age%type;
begin
   --���α�
   open c_hello_plsql(2, 9);
   
   --��ȡ�α�����
   fetch c_hello_plsql into v_id, v_first_name, v_last_name,v_age;
   while c_hello_plsql %found loop
     dbms_output.put_line('id:' || v_id || ' ' || 'fullName:' || v_first_name || v_last_name);
     fetch c_hello_plsql into v_id, v_first_name, v_last_name,v_age;
   end loop;
  
   --�ر��α�
   close c_hello_plsql;
end;

--��ʾ�α����Ӷ�
declare
  v_hello_row t_hello_plsql%rowtype;
  cursor c_hello_plsql is select * from t_hello_plsql;
begin
  open c_hello_plsql;
  fetch c_hello_plsql into v_hello_row;
  
  loop 
    dbms_output.put_line('id:' || v_hello_row.id);
    fetch c_hello_plsql into v_hello_row;
    exit when c_hello_plsql%notfound;
  end loop;

  close c_hello_plsql;
end;

--��ʾ�α�������
declare
   v_hello_plsql t_hello_plsql%rowtype;
   cursor c_hello_plsql(beginRow in number default 1,
     endRow in number default 5) is select * from 
   (select rownum r, tt.* from t_hello_plsql tt)t
     where t.r > beginRow and t.r < endRow order by age desc;
begin
  --PL/SQL �����ṩ���α� FOR ѭ�����
  --�Զ�ִ���α�� OPEN��FETCH��CLOSE ����ѭ�����Ĺ���
  for v_hello_plsql in c_hello_plsql(2, 9) loop
    dbms_output.put_line('id:' || v_hello_plsql.id);
  end loop;
  
  --��
  dbms_output.put_line('-----------------------------');
  for v_hello_plsql in (select * from (select rownum r, tt.* 
    from t_hello_plsql tt)t where t.r > 2 and t.r < 9) loop
    dbms_output.put_line('id:' || v_hello_plsql.id);
  end loop;
end;

--��ʽ�α�����һ
declare
   v_id number := 100;
   v_result varchar2(30) := null;
begin 
   update t_hello_plsql set age = 50
     where id = v_id;
     
   if SQL%notfound then
     v_result := '��������!';
     else
       v_result := '���³ɹ�!';
   end if;
   
   dbms_output.put_line(v_result);
end;

--��ʽ�α����Ӷ�
declare 
   v_id number := 100;
   v_result varchar2(30) := null;
begin 
  delete from t_hello_plsql where id = v_id;
  
  if SQL%found then
    v_result := '�ɹ�ɾ��һ�����ݣ�';
    elsif SQL%notfound then
      v_result := '�����ڵ�id!';
  end if;
     dbms_output.put_line(v_result);
end;

--�α�ɾ�����޸Ĳ�������
declare
  v_hello_plsql t_hello_plsql%rowtype;
  v_count number;
  cursor c_hello_plsql is select * from t_hello_plsql for update;
  
begin
  for v_hello_plsql in c_hello_plsql loop
    if v_hello_plsql.age > 25 then
      update t_hello_plsql set age = age + 1 where current of c_hello_plsql;
    end if;
  end loop;
  
  for v_hello_plsql in c_hello_plsql loop
    if v_hello_plsql.id = 1 then
      delete from t_hello_plsql where current of c_hello_plsql;
    end if;
  end loop;
  rollback;
end;

--Ԥ�����쳣��������
declare
  type hello_plsql_table_type is table of t_hello_plsql%rowtype;
  v_hello_plsql_table hello_plsql_table_type := hello_plsql_table_type();
  v_count number;
  v_result varchar2(20) := null;
begin
  select count(1) into v_count from t_hello_plsql;
  v_hello_plsql_table.extend(v_count);
  
  loop
    v_count := v_count - 1;
     select * into v_hello_plsql_table(v_count) from t_hello_plsql tt
     where id = v_count;  
 
     if v_hello_plsql_table(v_count).age > 40 then
        v_result := '����';
        elsif v_hello_plsql_table(v_count).age > 30 then
          v_result := '����';
          elsif v_hello_plsql_table(v_count).age > 20 then
            v_result := '����';
            else
              v_result := 'δ֪';
      end if;
      dbms_output.put_line(v_result);
  exit when v_count = 0;
  end loop;
  
exception
  when no_data_found then
    v_result := 'û������';
  when too_many_rows then
    v_result := '�г�̫�࣬��ʹ���α�';
  when program_error then
    v_result := 'ϵͳ����';
  when not_logged_on then
    v_result := 'û�����ӵ�oracle';
  when storage_error then
    v_result := '�ڴ治��';
  when others then
    v_result := '��������';

if (v_result != null) then
  if v_result != '' then
    dbms_output.put_line(v_result);
  end if;
end if;
end;

--�Զ����쳣��������
declare
   type hello_plsql_rec is record(
      v_id t_hello_plsql.id%type := &id,
      v_first_name t_hello_plsql.first_name%type,
      v_last_name t_hello_plsql.last_name%type,
      v_full_name t_hello_plsql.full_name%type,
      v_age t_hello_plsql.age%type
   );
   
   v_hello_plsql_res hello_plsql_rec;
   
   --�Զ������
   e_primary_clash exception;
   e_not_found exception;
   --�󶨵���ͼ�ƻ�һ��Ψһ�����ƴ�������
   pragma exception_init(e_primary_clash, -0001);
begin 
  v_hello_plsql_res.v_first_name := 'wang';
  v_hello_plsql_res.v_last_name := 'lin';
  v_hello_plsql_res.v_age := 10;
  v_hello_plsql_res.v_full_name := v_hello_plsql_res.v_first_name || v_hello_plsql_res.v_last_name;
  
  insert into t_hello_plsql(id, first_name,last_name,full_name,age)
  values(v_hello_plsql_res.v_id,v_hello_plsql_res.v_first_name,
  v_hello_plsql_res.v_last_name,v_hello_plsql_res.v_full_name,
  v_hello_plsql_res.v_age);
  
  
  update t_hello_plsql set age = 30 where id = v_hello_plsql_res.v_id + 50;
  if sql%notfound then
    --�׳��Զ����쳣
    raise e_not_found;
  end if;
  
exception
  when e_primary_clash then
    dbms_output.put_line('�����ظ�');
    --SQLCODE  ���ش����������   SQLERRM  ���ش�����Ϣ.
    dbms_output.put_line('code:' || SQLCODE || ' errMsg:' || SQLERRM );
  when e_not_found then
    dbms_output.put_line('û�и�����');
        --SQLCODE  ���ش����������   SQLERRM  ���ش�����Ϣ.
    dbms_output.put_line('code:' || SQLCODE || ' errMsg:' || SQLERRM );
  when others then
    --SQLCODE  ���ش����������   SQLERRM  ���ش�����Ϣ.
    dbms_output.put_line('�����쳣' || 'code:' || SQLCODE || ' errMsg:' || SQLERRM );
end;
delete from t_hello_plsql where id = 0;

--�����洢����-��������һ
--OR REPLACE  Ϊ��ѡ.������, ����һ���º��������滻��ͬ���ֵĺ���,��������ֳ�ͻ
create or replace function hello_func(
p_age in t_hello_plsql.age%type default 10,--��������Ĭ��ֵ
p_gender in t_hello_plsql.gender%type,
p_count out number) --�����������
--���巵��ֵ����
return number
is
v_count number;
begin
  select count(*),count(1) into v_count, p_count from t_hello_plsql where 
     age > p_age and gender = p_gender;
  return v_count;
exception
  when no_data_found then
    return 0;
  when others then
    dbms_output.put_line(sqlcode);
    dbms_output.put_line(sqlerrm);
    return -1;
end;

--����
declare
   v_result number;
   v_test_outparam number;
   v_age t_hello_plsql.age%type := 10;
   v_gender t_hello_plsql.gender%type := 1;
begin
  --λ�ñ�ʾ��
  v_result := hello_func(v_age, v_gender, v_test_outparam);
  dbms_output.put_line('���ý��:' || v_result || '  ' || v_test_outparam);
  --���Ʊ�ʾ��
  v_result := hello_func(p_age => v_age, p_gender => v_gender, p_count =>v_test_outparam);
    dbms_output.put_line('���ý��:' || v_result || '  ' || v_test_outparam);
  --��ϱ�ʾ����ʹ��λ�ñ�ʾ�������ݵĲ�������������Ʊ�ʾ�������ݵĲ���ǰ�棩
  --Ҳ����˵�����ۺ������ж��ٸ��� ����ֻҪ������һ������ʹ�����Ʊ�ʾ����������еĲ���������ʹ�����Ʊ�ʾ����
  v_result := hello_func(v_age, p_gender => v_gender, p_count =>v_test_outparam );
    dbms_output.put_line('���ý��:' || v_result || '  ' || v_test_outparam);
    --����Ĭ��ֵ
  v_result := hello_func(p_gender => v_gender, p_count =>v_test_outparam );
    dbms_output.put_line('���ý��:' || v_result || '  ' || v_test_outparam);
end;

--�����洢����-��������һ
create or replace procedure hello_procedure(
   v_id in t_hello_plsql.id%type default t_hello_plsql_seq.nextval,
   v_first_name in t_hello_plsql.first_name%type,
   v_last_name in t_hello_plsql.last_name%type,
   v_age in t_hello_plsql.age%type,
   v_birthday in t_hello_plsql.birthday%type,
   v_gender in t_hello_plsql.gender%type,
   v_full_name in t_hello_plsql.full_name%type,
   v_count out number,
   v_result out varchar2
)
is
   e_primary_clash exception;
   --��Υ��Ψһ��Լ��
   pragma exception_init(e_primary_clash, -1);
begin
  insert into t_hello_plsql(
     id,first_name, last_name,full_name,
     age,gender,birthday
  )values(
     v_id,v_first_name,v_last_name,v_full_name,
     v_age,v_gender,v_birthday
  );

  select count(2) into v_count from t_hello_plsql;
  v_result := '����ɹ�!';
exception
  when e_primary_clash then
    v_result := '�����ظ���';
  when others then
    v_result := 'errCode:' || sqlcode || '<======>' || 'errMsg:' || sqlerrm;
end;

--���ù���
declare
   v_count number;
   v_result varchar2(50);
begin
  hello_procedure(
     --�ſ���ע�Ϳ��Բ��������ظ�����
     --v_id => 1,
     v_age => 18,v_first_name => 'lai',
     v_last_name => 'ge',v_full_name => 'laige',
     v_birthday => to_date('19990102','yyyy-MM-dd'),
     v_gender => 1,
     v_count => v_count,
     v_result => v_result
  );

   dbms_output.put_line('v_count:' || v_count || '<=====>' || 'v_result:' || v_result);
end;

--����������һ
--������ͷ ��ִ��
create or replace package hello_pck 
is
function add(
  id t_hello_plsql.id%type,
  first_name t_hello_plsql.first_name%type,
  last_name t_hello_plsql.last_name%type,
  birthday t_hello_plsql.birthday%type,
  age t_hello_plsql.age%type,
  gender t_hello_plsql.gender%type
  )
  return varchar2;
  
procedure delet(v_id t_hello_plsql.id%type, v_count out number);

end hello_pck;

--��������   ��ִ��
create or replace package body hello_pck 
is
function add(
  id t_hello_plsql.id%type,
  first_name t_hello_plsql.first_name%type,
  last_name t_hello_plsql.last_name%type,
  birthday t_hello_plsql.birthday%type,
  age t_hello_plsql.age%type,
  gender t_hello_plsql.gender%type
  )
  return varchar2
  
  is
  e_primary_clash exception;
  pragma exception_init(e_primary_clash, -1);
  begin
    insert into t_hello_plsql(id,first_name,last_name,age,gender,birthday)
    values(id, first_name,last_name,age,gender,birthday);
    
    return '��ӳɹ�!';
  exception
    when e_primary_clash then
      return '�����ظ�';
    when others then
      return 'errCode:' || sqlcode || '<=====>errMsg' || sqlerrm;
end add;

procedure delet(v_id t_hello_plsql.id%type, v_count out number)
  is
  e_notdata exception;
  begin
    delete from t_hello_plsql where id = v_id;
    if sql%notfound then
      raise e_notdata;
    end if;
    select count(1) into v_count from t_hello_plsql;
  exception
    when e_notdata then
      v_count := -1;
    when others then
      v_count := -2;
end delet;
end hello_pck;

--���ð�  ���ִ��
declare
   type hello_rec is record(
     id t_hello_plsql.id%type,
     first_name t_hello_plsql.first_name%type,
     last_name t_hello_plsql.last_name%type,
     age t_hello_plsql.age%type,
     gender t_hello_plsql.gender%type,
     birthday t_hello_plsql.birthday%type
   );
   hello_table hello_rec;
   v_count number;
   v_result varchar2(20);
begin 
 hello_table.id := t_hello_plsql_seq.nextval;
 hello_table.first_name := 'dian';
 hello_table.last_name := 'nao';
 hello_table.age := 11;
 hello_table.gender := 1;
 hello_table.birthday := sysdate;
 
 v_result := hello_pck.add(hello_table.id,hello_table.first_name,
 hello_table.last_name,hello_table.birthday,
 hello_table.age,hello_table.gender);
 
 dbms_output.put_line('v_result:' || v_result);
 
 hello_pck.delet(hello_table.id, v_count);
 dbms_output.put_line('v_count:' || v_count);

end;

--���Ӷ�
--������ͷ
create or replace package tes
is
 type hello_table is table of t_hello_plsql%rowtype;
  function find(v_table out hello_table) return number;
end tes;

--��������
create or replace package body tes
is
  function find(v_table out hello_table) return number
    is
    v_count number;
    begin
      v_table := hello_table();
      select count(1) into v_count from t_hello_plsql;
      v_table.extend(v_count);
      for i in 1..v_count loop
        select t.id,t.first_name,t.last_name,t.full_name,t.address,t.gender,
        t.age,t.birthday into v_table(i) from (select rownum r, tt.* from t_hello_plsql tt)t 
        where t.r = i;
      end loop;
      return v_count;
   end find;
end tes;

--����
declare 
   v_count number;
   v_table tes.hello_table;
begin
  v_count := tes.find(v_table);
  for i in 1..v_count loop
    dbms_output.put_line('id:' || v_table(i).id || ' ' || 'fullName:' || v_table(i).first_name || v_table(i).last_name);
  end loop;
end;

--������������
----------------------������ͷ  ��ִ��
create or replace package dml_pck
is
type hello_cursor is ref cursor;--����һ�������͵��α꣡
type hello_cursor2 is ref cursor return t_hello_plsql%rowtype;--����һ��ǿ�����α꣡
type hello_table is table of t_hello_plsql%rowtype;--����һ����������-Ƕ�ױ�
type hello_rec is record( --���帴������-��¼
  id t_hello_plsql.id%type,
  first_name t_hello_plsql.first_name%type,
  last_name t_hello_plsql.last_name%type,
  full_name t_hello_plsql.full_name%type,
  address t_hello_plsql.address%type,
  age t_hello_plsql.age%type,
  gender t_hello_plsql.gender%type,
  birthday t_hello_plsql.birthday%type
);
v_count number;--�Զ���������ͱ���
e_primary_clash exception; --�Զ����쳣
pragma exception_init(e_primary_clash, -1); --��Υ��Ψһ��Լ���쳣

--��
function find(v_id in t_hello_plsql.id%type default -1, v_results out hello_table) return number;

--��������
function handleResultSet(v_count in number,v_results in hello_table) return varchar2;

--��
procedure add(v_row in hello_rec, v_result out varchar2);

--ɾ
procedure del(v_id in t_hello_plsql.id%type, v_result out varchar2);

--��
procedure edit(v_id in t_hello_plsql.id%type, v_data in hello_rec, v_result out varchar2);

end dml_pck;

---------------------��������  
create or replace package body dml_pck
is
--��
function find(v_id in t_hello_plsql.id%type default -1, v_results out hello_table) return number
  is
  v_count number;
  begin
    v_results := hello_table();
    if v_id != -1 then
      v_count := 1;
      v_results.extend(v_count);
      select * into v_results(1) from t_hello_plsql where id = v_id;
      else
        select count(*) into v_count from t_hello_plsql;
        v_results.extend(v_count);
        for i in 1..v_count loop
          select t.id,t.first_name,t.last_name,t.full_name,t.address,t.gender,
        t.age,t.birthday  into v_results(i) from (select rownum r, tt.* from t_hello_plsql tt)t
          where t.r = i;
        end loop;
    end if;
    return v_count;
end find;

--��������
function handleResultSet(v_count in number,v_results in hello_table) return varchar2
  is
  begin
    for i in 1..v_count loop
      dbms_output.put_line('id:' || v_results(i).id || '  ' || 'fullName:' || v_results(i).first_name || v_results(i).last_name);
    end loop;
    return '����ɹ���';
end handleResultSet;

--��
procedure add(v_row in hello_rec, v_result out varchar2)
  is
  begin
    insert into t_hello_plsql(id,first_name,last_name,full_name,birthday,age,gender,address)
    values(v_row.id,v_row.first_name,v_row.last_name,v_row.full_name,v_row.birthday,v_row.age,v_row.gender,v_row.address);
    v_result := '�����ɹ�!';
   exception
     when e_primary_clash then
       v_result := '�����ظ�!';
       when others then
         v_result := sqlerrm;
end add;

--ɾ
procedure del(v_id in t_hello_plsql.id%type, v_result out varchar2)
  is
  begin
    delete from t_hello_plsql where id = v_id;
    v_result := 'ɾ���ɹ�!';
    if sql%notfound then
      v_result := '�޴����ݣ�';
    end if;
end del;

--��
procedure edit(v_id in t_hello_plsql.id%type, v_data in hello_rec, v_result out varchar2)
  is
  begin
    update t_hello_plsql set address = v_data.address where id = v_id;
    v_result := '�޸ĳɹ���';
    if sql%notfound then
      v_result := '�޴����ݣ�';
    end if;
end edit;
end dml_pck;

--����
declare
   v_hello_table dml_pck.hello_table;
   v_count number;
   v_result varchar2(30);
   v_rec dml_pck.hello_rec;
   v_row t_hello_plsql%rowtype;
   v_cursor dml_pck.hello_cursor; --ʵ�����α�
begin
  v_count := dml_pck.find(v_results => v_hello_table);
  v_result := dml_pck.handleResultSet(v_count,v_results => v_hello_table);
  dbms_output.put_line('---------------------------------------');
  
  select id,first_name, last_name, full_name,address,
  age,
  gender,
  birthday into v_rec from t_hello_plsql where id = 2;
  dml_pck.add(v_rec, v_result);
  dbms_output.put_line('��ӽ��:' || v_result);
  dbms_output.put_line('---------------------------------------');
  
  dml_pck.del('100',v_result);
  dbms_output.put_line('ɾ�����:' || v_result);
  dbms_output.put_line('---------------------------------------');
  
  dbms_output.put_line('����ʹ�ð���������');
  open v_cursor for select *  from t_hello_plsql;--���α�
  fetch v_cursor into v_row;
  loop
    dbms_output.put_line('id:' || v_row.id || '  ' || 'fullName:' || v_row.first_name || v_row.last_name);
    fetch v_cursor into v_row;
  exit when v_cursor%notfound;
  end loop; 
  close v_cursor;--�ر��α�
end;



drop table t_hello_plsql_history;
create table t_hello_plsql_history as select * from t_hello_plsql where 1 = 2; --������־��

--���������� ɾ����t_hello_plsql����ʱ����
--�������ݣ���ɾ�������ݱ��浽ɾ����־��
create or replace trigger del_hello_tri
before delete 
on t_hello_plsql for each row
begin
  --���η�:oldָ�������֮ǰ�е�ֵ,����ָɾ��ǰÿ���е�ֵ
  --���η�:newָ�������֮���е�ֵ
  insert into t_hello_plsql_history(
  id,
  first_name,
  last_name,
  full_name,
  address,
  age,
  gender,
  birthday)
  values(
  :old.id,
  :old.first_name,
  :old.last_name,
  :old.full_name,
  :old.address,
  :old.age,
  :old.gender,
  :old.birthday);
end;

select * from t_hello_plsql_history;--Ŀǰִ�������û�����ݵ�
delete from t_hello_plsql where id = 2;--����ᴥ��������
select * from t_hello_plsql_history;--��һ�������ˣ����Ǳ�ɾ��������

