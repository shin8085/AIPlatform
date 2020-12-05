
create database ai_platform;

create table users(
    username varchar(20) primary key not null,
    password varchar(20) not null
);

insert into users values ('user','user');