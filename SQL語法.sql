create table Members(
id integer primary key not null identity(1,1),
[username] [nvarchar](50) NOT NULL UNIQUE,
[password] [nvarchar](50) NOT NULL,
[auth] [nvarchar](10) NULL,
[activated] [char](10) NULL
);

create table MemberDetails(
id integer primary key not null,
email nvarchar(50)not null,
fullname nvarchar(50)not null,
address nvarchar(50)not null,
phone varchar(10)not null,
birthday date not null,
job nvarchar(20) not null,
FOREIGN KEY (id) REFERENCES Members(id)
);

select * from Members;

select * from MemberDetails;