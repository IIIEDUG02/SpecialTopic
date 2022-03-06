create table class(
	cid int IDENTITY(1,1)PRIMARY KEY not null,
	title nvarchar(50) not null,
	type nvarchar(50) not null,
	price int not null,
	photo varbinary,
	uid int not null REFERENCES members(uid)
);

create table orders(
	oid int IDENTITY(1,1)PRIMARY KEY not null,
	orderDate datetime not null,
	cid int not null REFERENCES class(cid),
	uid int not null REFERENCES members(uid)
);


create table classDetails(
	cid int not null PRIMARY KEY REFERENCES class(cid) ,
	descript nvarchar(500) not null,
	needed_tool nvarchar(100) not null,
	stu_reqired nvarchar(100) not null,
	length_min int not null,
	goal nvarchar(200) not null,
	video varchar(100) not null,
);
