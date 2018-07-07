----------------------- plsql 章节内容 -------------------------------------
--删除表
drop table t_hello_plsql;

-- 创建表：t_hello_plsql
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

--查询
select * from t_hello_plsql;

--删除序列
drop sequence t_hello_plsql_seq;
-- 创建主键自增序列
create sequence t_hello_plsql_seq
       minvalue 0--最小值
       maxvalue 9999999999--最大值
       start with 0--从0开始
       increment by 1--步长为1
       nocycle--不循环
       nocache;--不缓存

commit;
--通过数据字典user_sequences查询序列
select * from user_sequences where sequence_name = 't_hello_plsql_seq';
--清空表
delete from t_hello_plsql;
--truncate table t_hello_plsql;
--插入一些测试数据
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'zhang','san','zhangsan','广东','23','1',sysdate);
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'wang','wu','wangwu','上海','25','1',sysdate);
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'zhao','liu','zhaoliu','广州','22','1',sysdate);
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'cheng','li','chengli','河源','22','2',sysdate);
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'huang','hong','huanghong','河源','21','2',sysdate);
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'cai','li','caili','东莞','20','2',sysdate);
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'yang','hong','yanghong','北京','12','2',sysdate);
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'huang','wenxi','huangwenxi','东莞','16','2',sysdate);
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'lin','biao','linbiao','珠海','40','1',sysdate);
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'zeng','guofan','zengguofan','贵州','36','1',sysdate);
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'cheng','rijiang','chengrijiang','云南','38','1',sysdate);
insert into t_hello_plsql(id,first_name,last_name,full_name,address,age,gender,birthday)values(
t_hello_plsql_seq.nextval,'lai','changxi','laichangxi','江西','42','1',sysdate);

--提交
commit;
select * from t_hello_plsql;

--记录类型语法
type record_type is record(
     field_1 type_1,
     field_2 type_2,
     field_3 type_3
     ...      ...
);

--记录类型例子一 使用基本类型定义记录变量
declare 
   --定义一个复合类型（记录类型）
   type example_record is record(
        last_name varchar2(10),
        first_name varchar2(10),
        age number(3)
   );
     
   --定义变量 类型为上面定义的复合类型-记录类型
   v_example example_record;
begin
  v_example.last_name := 'zhiyuan';
  v_example.first_name := 'lai';
  v_example.age := 25;
  dbms_output.put_line('fullName:' || v_example.first_name || 
  v_example.last_name || ' ' || 'age:' || v_example.age);

end;
--记录类型例子二 使用基本类型定义记录变量
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

--记录类型例子三 使用绑定数据变量(%type)
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

--记录类型例子四 使用绑定表行(%rowtype)
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

--嵌套表例子一
declare
  type hello_table_type is table of t_hello_plsql%rowtype;
  --使用该集合的构造器初始化它
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

--嵌套表例子二
declare
   type hello_plsql_table_type is table of t_hello_plsql%rowtype;
   hello_table hello_plsql_table_type := hello_plsql_table_type();
begin
  hello_table.extend(5);
  hello_table.trim(2); --去掉集合末尾的2条
  
  for i in 1..3 loop
 
    select * into hello_table(i) from t_hello_plsql where id = i;  
  
    dbms_output.put_line('id:' || hello_table(i).id);
  end loop;
end;

--变量赋值例子
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

--单行注释例子
declare

   v_username varchar2(32) := 'laizhiyuan'; --用户名
   v_password number(20) := 666666; --密码
begin
  /*
  这是登陆校验
  用户名必须为:laizhiyuan
  密码必须是:666666
  */
  
  if (v_username != 'laizhiyuan') then
    dbms_output.put_line('用户名错误!');
    elsif (v_password != 666666) then
      dbms_output.put_line('密码错误!');
      else
         dbms_output.put_line('登录成功!');
  end if;

end;

--实战-增
declare
   v_id number(10) := t_hello_plsql_seq.nextval;
   v_first_name varchar2(10) := 'lai';
   v_last_name varchar2(10) := 'ge';
   v_address varchar2(30) := '惠州';
   v_birthday date := to_date('1999.01,01','yyyy-MM-dd');
   v_gender number := 1;
   v_age number := 20;
   
   v_count number;
begin 
  select count(1) into v_count from t_hello_plsql;  
  dbms_output.put_line('插入之前的数量：' || v_count);
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
  dbms_output.put_line('插入之后的数量：' || v_count);
end;


--实战-删
declare
   --动态删除
   v_id number := &id;
   v_count number;
begin
  select count(1) into v_count from t_hello_plsql;  
  dbms_output.put_line('删除之前的数量：' || v_count);
  
  delete from t_hello_plsql where id = v_id;
  
  select count(1) into v_count from t_hello_plsql;  
  dbms_output.put_line('删除之后的数量：' || v_count);
end;

--if else 例子
declare
   v_simple_num number := 10;
   v_result varchar2(30);
begin
  if (v_simple_num > 10) then
    v_result := '大于10';
    else
      v_result := '小于10';
  end if;
  
   dbms_output.put_line(v_result);
end;

select * from t_hello_plsql;

--case...when...then...when...then...else...end 例子
declare
   v_age t_hello_plsql.age%type;
   v_id t_hello_plsql.id%type := &id;
   v_result varchar2(30) := null;
begin
   select age into v_age from t_hello_plsql where id = v_id;
  
   case 
     when v_age < 20 then
       v_result := '少年者';
     when v_age < 30 then
       v_result := '青少年';
     when v_age < 40 then
       v_result := '中年者';
     else
       v_result := '不老不少年龄';
    end case;
    dbms_output.put_line(v_result);
end;


--goto 例子
declare

begin
   goto test_goto;
   
   <<test_goto>>
   dbms_output.put_line('Hello World！');
end;


--循环-loop
declare
   v_count number := 10;
begin 
  loop
    dbms_output.put_line('count:' || v_count);
    v_count := v_count -1;
    exit when (v_count < 0);
  end loop;
end;

--循环-while
declare
   v_count number := 0;
begin
  while (v_count < 10) loop
    dbms_output.put_line('count:' || v_count);
    v_count := v_count + 1;
  end loop;
end;

--循环-for
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

--显示游标例子一
declare
  --定义游标 可以传参
  cursor c_hello_plsql(beginRow in number default 1, endRow in number default 10) 
        is select t.id, t.first_name,
      t.last_name, t.age from (select rownum rn, tt.* from t_hello_plsql tt)t where 
        t.rn > beginRow and t.rn < endRow order by age desc;
        
   v_id t_hello_plsql.id%type;
   v_first_name t_hello_plsql.first_name%type;
   v_last_name t_hello_plsql.last_name%type;
   v_age t_hello_plsql.age%type;
begin
   --打开游标
   open c_hello_plsql(2, 9);
   
   --提取游标数据
   fetch c_hello_plsql into v_id, v_first_name, v_last_name,v_age;
   while c_hello_plsql %found loop
     dbms_output.put_line('id:' || v_id || ' ' || 'fullName:' || v_first_name || v_last_name);
     fetch c_hello_plsql into v_id, v_first_name, v_last_name,v_age;
   end loop;
  
   --关闭游标
   close c_hello_plsql;
end;

--显示游标例子二
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

--显示游标例子三
declare
   v_hello_plsql t_hello_plsql%rowtype;
   cursor c_hello_plsql(beginRow in number default 1,
     endRow in number default 5) is select * from 
   (select rownum r, tt.* from t_hello_plsql tt)t
     where t.r > beginRow and t.r < endRow order by age desc;
begin
  --PL/SQL 语言提供了游标 FOR 循环语句
  --自动执行游标的 OPEN、FETCH、CLOSE 语句和循环语句的功能
  for v_hello_plsql in c_hello_plsql(2, 9) loop
    dbms_output.put_line('id:' || v_hello_plsql.id);
  end loop;
  
  --或
  dbms_output.put_line('-----------------------------');
  for v_hello_plsql in (select * from (select rownum r, tt.* 
    from t_hello_plsql tt)t where t.r > 2 and t.r < 9) loop
    dbms_output.put_line('id:' || v_hello_plsql.id);
  end loop;
end;

--隐式游标例子一
declare
   v_id number := 100;
   v_result varchar2(30) := null;
begin 
   update t_hello_plsql set age = 50
     where id = v_id;
     
   if SQL%notfound then
     v_result := '查无数据!';
     else
       v_result := '更新成功!';
   end if;
   
   dbms_output.put_line(v_result);
end;

--隐式游标例子二
declare 
   v_id number := 100;
   v_result varchar2(30) := null;
begin 
  delete from t_hello_plsql where id = v_id;
  
  if SQL%found then
    v_result := '成功删除一条数据！';
    elsif SQL%notfound then
      v_result := '不存在的id!';
  end if;
     dbms_output.put_line(v_result);
end;

--游标删除或修改操作例子
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

--预定义异常处理例子
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
        v_result := '中年';
        elsif v_hello_plsql_table(v_count).age > 30 then
          v_result := '青年';
          elsif v_hello_plsql_table(v_count).age > 20 then
            v_result := '少年';
            else
              v_result := '未知';
      end if;
      dbms_output.put_line(v_result);
  exit when v_count = 0;
  end loop;
  
exception
  when no_data_found then
    v_result := '没有数据';
  when too_many_rows then
    v_result := '列出太多，请使用游标';
  when program_error then
    v_result := '系统错误';
  when not_logged_on then
    v_result := '没有连接到oracle';
  when storage_error then
    v_result := '内存不够';
  when others then
    v_result := '其它错误';

if (v_result != null) then
  if v_result != '' then
    dbms_output.put_line(v_result);
  end if;
end if;
end;

--自定义异常处理例子
declare
   type hello_plsql_rec is record(
      v_id t_hello_plsql.id%type := &id,
      v_first_name t_hello_plsql.first_name%type,
      v_last_name t_hello_plsql.last_name%type,
      v_full_name t_hello_plsql.full_name%type,
      v_age t_hello_plsql.age%type
   );
   
   v_hello_plsql_res hello_plsql_rec;
   
   --自定义错误
   e_primary_clash exception;
   e_not_found exception;
   --绑定到试图破坏一个唯一性限制错误码上
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
    --抛出自定义异常
    raise e_not_found;
  end if;
  
exception
  when e_primary_clash then
    dbms_output.put_line('主键重复');
    --SQLCODE  返回错误代码数字   SQLERRM  返回错误信息.
    dbms_output.put_line('code:' || SQLCODE || ' errMsg:' || SQLERRM );
  when e_not_found then
    dbms_output.put_line('没有该数据');
        --SQLCODE  返回错误代码数字   SQLERRM  返回错误信息.
    dbms_output.put_line('code:' || SQLCODE || ' errMsg:' || SQLERRM );
  when others then
    --SQLCODE  返回错误代码数字   SQLERRM  返回错误信息.
    dbms_output.put_line('其它异常' || 'code:' || SQLCODE || ' errMsg:' || SQLERRM );
end;
delete from t_hello_plsql where id = 0;

--创建存储过程-函数例子一
--OR REPLACE  为可选.有了它, 创建一个新函数或者替换相同名字的函数,而不会出现冲突
create or replace function hello_func(
p_age in t_hello_plsql.age%type default 10,--可以设置默认值
p_gender in t_hello_plsql.gender%type,
p_count out number) --定义输出参数
--定义返回值类型
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

--调用
declare
   v_result number;
   v_test_outparam number;
   v_age t_hello_plsql.age%type := 10;
   v_gender t_hello_plsql.gender%type := 1;
begin
  --位置表示法
  v_result := hello_func(v_age, v_gender, v_test_outparam);
  dbms_output.put_line('调用结果:' || v_result || '  ' || v_test_outparam);
  --名称表示法
  v_result := hello_func(p_age => v_age, p_gender => v_gender, p_count =>v_test_outparam);
    dbms_output.put_line('调用结果:' || v_result || '  ' || v_test_outparam);
  --混合表示法（使用位置表示法所传递的参数必须放在名称表示法所传递的参数前面）
  --也就是说，无论函数具有多少个参 数，只要其中有一个参数使用名称表示法，其后所有的参数都必须使用名称表示法。
  v_result := hello_func(v_age, p_gender => v_gender, p_count =>v_test_outparam );
    dbms_output.put_line('调用结果:' || v_result || '  ' || v_test_outparam);
    --测试默认值
  v_result := hello_func(p_gender => v_gender, p_count =>v_test_outparam );
    dbms_output.put_line('调用结果:' || v_result || '  ' || v_test_outparam);
end;

--创建存储过程-过程例子一
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
   --绑定违反唯一性约束
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
  v_result := '插入成功!';
exception
  when e_primary_clash then
    v_result := '主键重复！';
  when others then
    v_result := 'errCode:' || sqlcode || '<======>' || 'errMsg:' || sqlerrm;
end;

--调用过程
declare
   v_count number;
   v_result varchar2(50);
begin
  hello_procedure(
     --放开该注释可以测试主键重复错误
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

--创建包例子一
--创建包头 先执行
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

--创建包体   再执行
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
    
    return '添加成功!';
  exception
    when e_primary_clash then
      return '主键重复';
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

--调用包  最后执行
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

--例子二
--创建包头
create or replace package tes
is
 type hello_table is table of t_hello_plsql%rowtype;
  function find(v_table out hello_table) return number;
end tes;

--创建包体
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

--调用
declare 
   v_count number;
   v_table tes.hello_table;
begin
  v_count := tes.find(v_table);
  for i in 1..v_count loop
    dbms_output.put_line('id:' || v_table(i).id || ' ' || 'fullName:' || v_table(i).first_name || v_table(i).last_name);
  end loop;
end;

--创建包例子三
----------------------创建包头  先执行
create or replace package dml_pck
is
type hello_cursor is ref cursor;--定义一个弱类型的游标！
type hello_cursor2 is ref cursor return t_hello_plsql%rowtype;--定义一个强类型游标！
type hello_table is table of t_hello_plsql%rowtype;--定义一个复合类型-嵌套表
type hello_rec is record( --定义复合类型-记录
  id t_hello_plsql.id%type,
  first_name t_hello_plsql.first_name%type,
  last_name t_hello_plsql.last_name%type,
  full_name t_hello_plsql.full_name%type,
  address t_hello_plsql.address%type,
  age t_hello_plsql.age%type,
  gender t_hello_plsql.gender%type,
  birthday t_hello_plsql.birthday%type
);
v_count number;--自定义基本类型变量
e_primary_clash exception; --自定义异常
pragma exception_init(e_primary_clash, -1); --绑定违反唯一性约束异常

--查
function find(v_id in t_hello_plsql.id%type default -1, v_results out hello_table) return number;

--处理结果集
function handleResultSet(v_count in number,v_results in hello_table) return varchar2;

--增
procedure add(v_row in hello_rec, v_result out varchar2);

--删
procedure del(v_id in t_hello_plsql.id%type, v_result out varchar2);

--改
procedure edit(v_id in t_hello_plsql.id%type, v_data in hello_rec, v_result out varchar2);

end dml_pck;

---------------------创建包体  
create or replace package body dml_pck
is
--查
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

--处理结果集
function handleResultSet(v_count in number,v_results in hello_table) return varchar2
  is
  begin
    for i in 1..v_count loop
      dbms_output.put_line('id:' || v_results(i).id || '  ' || 'fullName:' || v_results(i).first_name || v_results(i).last_name);
    end loop;
    return '处理成功！';
end handleResultSet;

--增
procedure add(v_row in hello_rec, v_result out varchar2)
  is
  begin
    insert into t_hello_plsql(id,first_name,last_name,full_name,birthday,age,gender,address)
    values(v_row.id,v_row.first_name,v_row.last_name,v_row.full_name,v_row.birthday,v_row.age,v_row.gender,v_row.address);
    v_result := '新增成功!';
   exception
     when e_primary_clash then
       v_result := '主键重复!';
       when others then
         v_result := sqlerrm;
end add;

--删
procedure del(v_id in t_hello_plsql.id%type, v_result out varchar2)
  is
  begin
    delete from t_hello_plsql where id = v_id;
    v_result := '删除成功!';
    if sql%notfound then
      v_result := '无此数据！';
    end if;
end del;

--改
procedure edit(v_id in t_hello_plsql.id%type, v_data in hello_rec, v_result out varchar2)
  is
  begin
    update t_hello_plsql set address = v_data.address where id = v_id;
    v_result := '修改成功！';
    if sql%notfound then
      v_result := '无此数据！';
    end if;
end edit;
end dml_pck;

--调用
declare
   v_hello_table dml_pck.hello_table;
   v_count number;
   v_result varchar2(30);
   v_rec dml_pck.hello_rec;
   v_row t_hello_plsql%rowtype;
   v_cursor dml_pck.hello_cursor; --实例化游标
begin
  v_count := dml_pck.find(v_results => v_hello_table);
  v_result := dml_pck.handleResultSet(v_count,v_results => v_hello_table);
  dbms_output.put_line('---------------------------------------');
  
  select id,first_name, last_name, full_name,address,
  age,
  gender,
  birthday into v_rec from t_hello_plsql where id = 2;
  dml_pck.add(v_rec, v_result);
  dbms_output.put_line('添加结果:' || v_result);
  dbms_output.put_line('---------------------------------------');
  
  dml_pck.del('100',v_result);
  dbms_output.put_line('删除结果:' || v_result);
  dbms_output.put_line('---------------------------------------');
  
  dbms_output.put_line('测试使用包公共变量');
  open v_cursor for select *  from t_hello_plsql;--打开游标
  fetch v_cursor into v_row;
  loop
    dbms_output.put_line('id:' || v_row.id || '  ' || 'fullName:' || v_row.first_name || v_row.last_name);
    fetch v_cursor into v_row;
  exit when v_cursor%notfound;
  end loop; 
  close v_cursor;--关闭游标
end;



drop table t_hello_plsql_history;
create table t_hello_plsql_history as select * from t_hello_plsql where 1 = 2; --创建日志表

--触发器例子 删除表t_hello_plsql数据时触发
--触发内容：将删除的数据保存到删除日志表
create or replace trigger del_hello_tri
before delete 
on t_hello_plsql for each row
begin
  --修饰符:old指操作完成之前列的值,这里指删除前每个列的值
  --修饰符:new指操作完成之后列的值
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

select * from t_hello_plsql_history;--目前执行这个是没有数据的
delete from t_hello_plsql where id = 2;--这里会触发触发器
select * from t_hello_plsql_history;--有一条数据了，就是被删除的那条

