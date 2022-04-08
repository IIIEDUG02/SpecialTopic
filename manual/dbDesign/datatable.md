# 資料表設計

## 會員管理
- 登入資料
```sql
CREATE TABLE members (
	uid int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	username nvarchar(50) NOT NULL UNIQUE,
	password varchar(255) NULL,
	activated bit NULL,
);
```
- 會員明細
```sql
CREATE TABLE member_details (
	uid int NOT NULL PRIMARY KEY,
	email nvarchar(50) NOT NULL,
	gender int NULL,
	fullname nvarchar(50) NOT NULL,
	address nvarchar(50) NOT NULL,
	phone varchar(10) NOT NULL,
	birthday date NOT NULL,
	passportname nvarchar(50),
	identitycard nvarchar(10),
	photo nvarchar(50),
	job nvarchar(50) 
);
```
- 身份
```sql
CREATE TABLE member_roles (
	id int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	uid int NOT NULL REFERENCES members(uid),
	role nvarchar(50) NOT NULL,
	UNIQUE (uid,role)
);
```

## 課程管理
- 課程
```sql
create table class(
	cid int IDENTITY(1,1)PRIMARY KEY not null,
	title nvarchar(50) not null,
	class_type nvarchar(50) not null,
	price int not null,
	photo nvarchar(200),
	uid int not null REFERENCES members(uid)
);
```
- 章節
```sql
create table curriculum(
	cuid int IDENTITY(1,1)PRIMARY KEY not null,
	chapter nvarchar(50) not null,
	video_path varchar(100),
	cid int not null REFERENCES class(cid)
);
```
- 課程資料
```sql
create table class_details(
	cid int not null PRIMARY KEY REFERENCES class(cid) ,
	descript nvarchar(500) not null,
	needed_tool nvarchar(100) not null,
	stu_required nvarchar(100) not null,
	length_min int not null,
	goal nvarchar(200) not null,
	video varchar(200) ,
);
```
- 已上線課程
```sql
create table class_online(
	cid int not null PRIMARY KEY REFERENCES class(cid) ,
	online bit not null
);
```
- 學生購課紀錄
```sql
create table classmanagement(
	cmid int IDENTITY(1,1)PRIMARY KEY not null,
	status int not null,
	cid int not null REFERENCES class_online(cid),
	uid int not null REFERENCES members(uid),
	tid varchar(255) not null REFERENCES ecpay_record(order_id)
);
```
- 學生影片觀看進度
```sql
create table progress_record(
	id int IDENTITY(1,1)PRIMARY KEY not null,
	status bit not null,
	cuid int not null REFERENCES curriculum(cuid),
	uid int not null REFERENCES members(uid),
	time_sum float not null 
);
```
- 認證系統
```sql
create table certification_name(
	cert_id int IDENTITY(1,1)PRIMARY KEY not null,
	cert_name nvarchar(255) not null
);
```
- 系統所需課程
```sql
create table certification_classes(
	id int IDENTITY(1,1)PRIMARY KEY not null ,
	cert_id int not null REFERENCES certification_name(cert_id),
	cid int not null REFERENCES class_online(cid),
);
```


## 交易系統
- 購物車紀錄
```sql
CREATE TABLE shopping_cart (
	id int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	uid int NOT NULL,
	cid int NOT NULL,
	UNIQUE (uid,cid),
    FOREIGN KEY (cid) REFERENCES class(cid),
    FOREIGN KEY (uid) REFERENCES members(uid)
);
```
- 綠界支付紀錄
```sql
CREATE TABLE ecpay_record (
	order_id varchar(255) NOT NULL PRIMARY KEY,
	uid int NOT NULL,
	cids varchar(255) NOT NULL,
	trade_amount int NOT NULL,
	rtn_msg varchar(255) NULL,
	order_date datetime NULL,
	payment_type varchar(255) NULL,
);
```


## 互動系統
- 文章
```sql
CREATE TABLE article(
	id int IDENTITY(1,1) PRIMARY KEY,
	uuid char(24) NOT NULL,
	title nvarchar(255) NOT NULL,
	content ntext NOT NULL,
	page_views int NOT NULL,
	members_uid int NOT NULL,
	publish_time date NOT NULL,
	FOREIGN KEY members_uid REFERENCES members(uid),
) 
```
- 標籤
```sql
CREATE TABLE tag(
	id int IDENTITY(1,1) PRIMARY KEY,
	name nvarchar(20) NOT NULL,
	category varchar(50) NOT NULL,
)
```
- 文章標籤對照表
```sql
CREATE TABLE article_tag(
	id int IDENTITY(1,1) PRIMARY KEY,
	article_id int NOT NULL,
	tag_id int NOT NULL,
	FOREIGN KEY article_id REFERENCES article(id),
	FOREIGN KEY tag_id REFERENCES tag(id)
) 
```
- 留言
```sql
CREATE TABLE comment(
	id int IDENTITY(1,1) PRIMARY KEY,
	uuid char(24) NOT NULL,
	title nvarchar(50) NOT NULL,
	content ntext NOT NULL,
	type varchar(15) NOT NULL,
	parent_id int,
	class_cid int,
	members_uid int NOT NULL,
	post_time datetime NOT NULL,
	edit_time datetime NULL,
	FOREIGN KEY parent_id REFERENCES comment(id),
	FOREIGN KEY class_cid REFERENCES class(cid),
	FOREIGN KEY members_uid REFERENCES members(uid)
) 
```

## 數據分析
- 年度熱門課程
```sql
CREATE TABLE ypClass (
	ID int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	classID int NULL REFERENCES class_online(cid),
	[year] int NULL,
	yearAmount int NULL,
	priority nvarchar(50),
);
```
- 每月熱門課程
```sql
CREATE TABLE mpClass (
	ID int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	classID int NULL class_online(cid);,
	[month] int NULL,
	monthAmount int NULL,
);
```
- 學員資料分析
```sql
CREATE TABLE student_analysis (
	id int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	cid int NULL,
	avg_age float NULL,
	job nvarchar(50)  NULL,
	classtitle nvarchar(50) NULL,
);
```
<script src="https://code.jquery.com/jquery-3.6.0.slim.js" integrity="sha256-HwWONEZrpuoh951cQD1ov2HUK5zA5DwJ1DNUXaM6FsY=" crossorigin="anonymous"></script>

<script>
$(document).ready(function() {
  $('h2').each(function(index) {
    $(this).html((index + 1) + '. ' + $(this).html());
  });
});
</script>