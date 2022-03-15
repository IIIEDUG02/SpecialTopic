


create table class(
	cid int IDENTITY(1,1)PRIMARY KEY not null,
	title nvarchar(50) not null,
	classType nvarchar(50) not null,
	price int not null,
	photo varbinary(max),
	id int not null REFERENCES members(id)
);

create table curriculum(
	cuid int IDENTITY(1,1)PRIMARY KEY not null,
	chapter nvarchar(50) not null,
	videoPath varchar(200),
	cuTitle bit  not null,
	cid int not null REFERENCES class(cid)

);
create table classDetails(
	cid int not null PRIMARY KEY REFERENCES class(cid) ,
	descript nvarchar(500) not null,
	needed_tool nvarchar(100) not null,
	stu_required nvarchar(100) not null,
	length_min int not null,
	goal nvarchar(200) not null,
	video varchar(200) ,
);


