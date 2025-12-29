use trial;
create table account(
    id int primary key AUTO_INCREMENT comment 'ID',
    name varchar(10) comment '姓名',
    money double(10,2) comment '余额'
) comment '账户表';
insert into account(name, money) VALUES
     ('张三',2000), ('李四',2000);

-- 转账操作：张三给李四转账1000
-- 1.查询张三账户余额
select money from account where name='张三';
-- 2.如果张三余额大于1000，则将张三余额-1000
update account set money=money-1000 where name='张三' and money>1000;
模拟程序出现异常
-- 3.将李四余额+1000
update account set money=money+1000 where name='李四';

select @@autocommit;
set @@autocommit=0;-- 设置为手动提交
select money from account where name='张三';
update account set money=money-1000 where name='张三' and money>1000;
模拟程序出现异常
update account set money=money+1000 where name='李四';
commit;
rollback;

set @@autocommit=1;

start transaction;
select money from account where name='张三';
update account set money=money-1000 where name='张三' and money>1000;
模拟程序出现异常
update account set money=money+1000 where name='李四';
commit;
rollback;


-- 查看事务隔离级别
select @@transaction_isolation;
-- 设置事务隔离级别
set session transaction isolation level read committed;


