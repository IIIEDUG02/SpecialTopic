


create table class(
	cid int IDENTITY(1,1)PRIMARY KEY not null,
	title nvarchar(50) not null,
	class_type nvarchar(50) not null,
	price int not null,
	photo nvarchar(200),
	uid int not null REFERENCES members(uid)
);

create table curriculum(
	cuid int IDENTITY(1,1)PRIMARY KEY not null,
	chapter nvarchar(50) not null,
	video_path varchar(100),
	cid int not null REFERENCES class(cid)

);
create table class_details(
	cid int not null PRIMARY KEY REFERENCES class(cid) ,
	descript nvarchar(500) not null,
	needed_tool nvarchar(100) not null,
	stu_required nvarchar(100) not null,
	length_min int not null,
	goal nvarchar(200) not null,
	video varchar(200) ,
);

create table class_online(
	cid int not null PRIMARY KEY REFERENCES class(cid) ,
	online bit not null
);

create table classmanagement(
	cmid int IDENTITY(1,1)PRIMARY KEY not null,
	status int not null,
	cid int not null REFERENCES class_online(cid),
	uid int not null REFERENCES members(uid),
	tid int not null REFERENCES tid(tid)
);

create table progress_record(
	id int IDENTITY(1,1)PRIMARY KEY not null,
	status bit not null,
	cuid int not null REFERENCES curriculum(cuid),
	uid int not null REFERENCES members(uid),
	time_sum float not null 
);

create table certification_name(
	cert_id int IDENTITY(1,1)PRIMARY KEY not null,
	cert_name nvarchar(255) not null
);

create table certification_classes(
	id int IDENTITY(1,1)PRIMARY KEY not null ,
	cert_id int not null REFERENCES certification_name(cert_id),
	cid int not null REFERENCES class_online(cid),
);

	