create database webnews;
use webnews;

create table user(
userID int primary key  unique not null,
userName varchar(20) not null,
userPassword varchar(20) not null,
sex varchar(10) not null,
userEmail varchar(20) not null
);

create table admin(
adminID int primary key unique not null,
adminName varchar(20) not null,
adminPassword varchar(20) not null
);

create table roles(
roleID int primary key not null,
roleName varchar(20) not null
);

create table news(
newsID int primary key not null,
newsTitle varchar(50) not null,
newsContent text not null,
newsDate timestamp,
newsDesc varchar(50) not null,
newsImagePath varchar(50),
newsRate int not null,
newsIscheck bit not null,
newsIstop bit not null
);

create table category(
categoryID int primary key unique not null,
categoryName varchar(50) not null,
categoryDesc varchar(50) not null 
);

create table comment(
commentID int primary key unique not null,
commentTitle varchar(50) not null,
commentContent text not null,
commentDate datetime
);

create table admin_Roles(
aRID int primary key unique not null,
adminID int not null,
roleID int not null
);

create table news_comment(
nCommentID int primary key unique not null,
newsID int not null,
commentID int not null
);

create table users_comment(
uCID int primary key unique not null,
userID int not null,
commentID int not null
);

create index index_new_title on news(newsTitle);
create index index_new_date on news(newsDate);
alter table news add index index_new_rate(newsRate); 

create index index_category_name on category(categoryName);
create index index_comment_title on comment(commentTitle);
create index index_comment_date on comment(commentDate);

create view news_view as select c.commentID,n.newsID, n.newsRate,
n.newsTitle, n.newsContent, n.newsDate from news_Comment c ,news n 
where c.newsID=n.newsID
;

delimiter &&
create trigger update_newsID after update on news
for each row 
begin
update news_Comment set newsID=new.newsID;
end
&&
delimiter ;


delimiter &&
create trigger deleteuser after delete on user
for each row 
begin
delete from users_Comment where  userID=old.userID;
end
&&
delimiter ;