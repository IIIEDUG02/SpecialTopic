-- 學生購課紀錄
-- 2022/3/3更新說明：因為之前是用複合主鍵，在設定Hibernate @Annotation極為複雜，故僅設tid為pk，把uid+cid做複合唯一
--                 Hibernate在執行CRUD時，會預設使用 order_date作為欄位名，導致欄位找不到
use db;
CREATE TABLE c2b (
	tid int unique IDENTITY(1,1) not null,
    uid int not null REFERENCES members(uid) ,
    cid int not null REFERENCES class(cid) ,
    order_date datetime not null,
    completed bit not null DEFAULT 0,
    primary key(tid),
    UNIQUE (uid,cid)
);

-- 公司支付老師薪資紀錄
CREATE TABLE b2c (
	tid int not null REFERENCES c2b(tid),
	order_date datetime not null,
	completed bit not null DEFAULT 0
	primary key(tid)
);