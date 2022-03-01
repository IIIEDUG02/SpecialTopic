-- 學生購課紀錄
CREATE TABLE c2b (
	tid int IDENTITY(1,1) not null,
    sid int not null REFERENCES members(uid) ,
    cid int not null REFERENCES class(cid) ,
    completed bit not null DEFAULT 0
    primary key(tid,sid,cid)
);

-- 公司支付老師薪資紀錄
CREATE TABLE b2c (
	tid int not null REFERENCES c2b(tid),
	tid int not null REFERENCES members(uid) ,
	completed bit not null DEFAULT 0
);