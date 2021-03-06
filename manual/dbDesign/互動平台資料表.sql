CREATE TABLE [dbo].[article](
	[id] [int] IDENTITY(1,1) PRIMARY KEY,
	[uuid] [char](24) NOT NULL,
	[title] [nvarchar](255) NOT NULL,
	[content] [ntext] NOT NULL,
	[page_views] [int] NOT NULL,
	[members_uid] [int] NOT NULL REFERENCES [dbo].[members] ([uid]),
	[publish_time] [date] NOT NULL,
) 

CREATE TABLE [dbo].[article_tag](
	[id] [int] IDENTITY(1,1) PRIMARY KEY,
	[article_id] [int] NOT NULL REFERENCES [dbo].[article] ([id]),
	[tag_id] [int] NOT NULL REFERENCES [dbo].[tag] ([id]),
) 

CREATE TABLE [dbo].[comment](
	[id] [int] IDENTITY(1,1) PRIMARY KEY,
	[uuid] [char](24) NOT NULL,
	[title] [nvarchar](50) NOT NULL,
	[content] [ntext] NOT NULL,
	[type] [varchar](15) NOT NULL,
	[parent_id] [int] NULL REFERENCES [dbo].[comment] ([id]),
	[class_cid] [int] NOT NULL REFERENCES [dbo].[class] ([cid]),
	[members_uid] [int] NOT NULL REFERENCES [dbo].[members] ([uid]),
	[post_time] [datetime] NOT NULL,
	[edit_time] [datetime] NULL,
) 

CREATE TABLE [dbo].[tag](
	[id] [int] IDENTITY(1,1) PRIMARY KEY,
	[name] [nvarchar](20) NOT NULL,
	[category] [varchar](50) NOT NULL,
)
