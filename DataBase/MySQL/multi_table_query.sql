use trial;

create table dept(
                     id   int auto_increment comment 'ID' primary key,
                     name varchar(50) not null comment '部门名称'
)comment '部门表';

create table emp(
                    id  int auto_increment comment 'ID' primary key,
                    name varchar(50) not null comment '姓名',
                    age  int comment '年龄',
                    job varchar(20) comment '职位',
                    salary int comment '薪资',
                    entrydate date comment '入职时间',
                    managerid int comment '直属领导ID',
                    dept_id int comment '部门ID',
                    constraint fk_emp_dept_id foreign key (dept_id) references dept(id)
)comment '员工表';

INSERT INTO dept (id, name) VALUES
    (1, '研发部'), (2, '市场部'),(3, '财务部'), (4, '销售部'), (5, '总经办'), (6, '人事部');

INSERT INTO emp (id, name, age, job,salary, entrydate, managerid, dept_id) VALUES
    (1, '金庸', 66, '总裁',20000, '2000-01-01', null,5),

    (2, '张无忌', 20, '项目经理',12500, '2005-12-05', 1,1),
    (3, '杨逍', 33, '开发', 8400,'2000-11-03', 2,1),
    (4, '韦一笑', 48, '开发',11000, '2002-02-05', 2,1),
    (5, '常遇春', 43, '开发',10500, '2004-09-07', 3,1),
    (6, '小昭', 19, '程序员鼓励师',6600, '2004-10-12', 2,1),

    (7, '灭绝', 60, '财务总监',8500, '2002-09-12', 1,3),
    (8, '周芷若', 19, '会计',48000, '2006-06-02', 7,3),
    (9, '丁敏君', 23, '出纳',5250, '2009-05-13', 7,3),

    (10, '赵敏', 20, '市场部总监',12500, '2004-10-12', 1,2),
    (11, '鹿杖客', 56, '职员',3750, '2006-10-03', 10,2),
    (12, '鹤笔翁', 19, '职员',3750, '2007-05-09', 10,2),
    (13, '方东白', 19, '职员',5500, '2009-02-12', 10,2),

    (14, '张三丰', 88, '销售总监',14000, '2004-10-12', 1,4),
    (15, '俞莲舟', 38, '销售',4600, '2004-10-12', 14,4),
    (16, '宋远桥', 40, '销售',4600, '2004-10-12', 14,4),
    (17, '陈友谅', 42, null,2000, '2011-10-12', 1,null);

-- 内连接：查询每一个员工的姓名及关联部门的名称
-- 隐式内连接
select emp.name,dept.name from emp,dept
    where emp.dept_id=dept.id;
select dept.name,COUNT(*) as '员工人数' from emp,dept
    where emp.dept_id=dept.id group by dept.name;
-- 显示内连接
select e.name,d.name
    from emp e inner join dept d on e.dept_id = d.id;


-- 外连接
-- 左外连接：查询emp表的所有记录及对应部门的信息
select emp.*,dept.name
    from emp left outer join dept on emp.dept_id = dept.id;
-- 右外连接：查询dept表的所有记录及对应员工的信息
select dept.*,emp.*
    from emp right outer join dept on dept.id = emp.dept_id;


-- 自连接
-- 查询员工及其直属领导的名字
select e.name as '员工名字',m.name as '直属领导姓名'
    from emp e join emp m on e.managerid=m.id;
-- 查询员工及其直属领导的名字，如果员工没有领导也要查询出来
select e.name as '员工名字',m.name as '直属领导姓名'
from emp e left join emp m on e.managerid=m.id;


-- 联合查询
-- 将薪资低于5000的员工和年龄大于50的员工全部查询出来
select * from emp where salary<5000 or age>50;
select * from emp where salary<5000
union
select * from emp where age>50;


-- 子查询
-- 1.标量子查询
-- 查询销售部所有员工的信息
select * from emp where emp.dept_id=(
    select dept.id from dept where dept.name='销售部'
);
-- 查询在方东白入职之后的员工信息
select * from emp where emp.entrydate>(
    select emp.entrydate from emp where emp.name='方东白'
);

-- 2.列子查询
-- 查询销售部和市场部的所有员工信息
select * from emp where dept_id in (
    select id from dept where name='销售部' or name='市场部'
);
-- 查询比财务部所有人的工资都高的员工信息
select * from emp where salary > all(
    select salary from emp where emp.dept_id=(
        select id from dept where dept.name='财务部'
    )
);
select * from emp where salary > (
    select MAX(salary) from emp where emp.dept_id=(
        select id from dept where dept.name='财务部'
    )
);
-- 查询比研发部其中任意一人工资高的员工信息
select * from emp where salary > any(
    select salary from emp where emp.dept_id=(
        select id from dept where dept.name='研发部'
    )
);

-- 3.行子查询
-- 查询与张无忌的薪资及直属领导相同的员工信息
select * from emp where (salary,managerid) = (
    select salary,managerid from emp where name='张无忌'
);

-- 4.表子查询
-- 查询与鹿杖客、宋远桥的职位及薪资相同的员工信息
select * from emp where (job,salary) in (
    select job,salary from emp where name in ('鹿杖客','宋远桥')
);
-- 查询入职日期是2006-01-01之后的员工信息及其部门信息
select emp.*,dept.*
    from emp join dept on emp.dept_id = dept.id
    where emp.entrydate>'2006-01-01';


