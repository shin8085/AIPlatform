
create database ai_platform;

# 用户表
create table users(
    username varchar(20) primary key not null,
    password varchar(20) not null,
    identity varchar(20) not null
);

# 各用户调用ai次数统计
create table invoking_count(
    username varchar(20) primary key not null,
    face_recognize int(6) default 0 comment '人脸识别',
    age_estimation int(6) default 0 comment '年龄检测',
    object_detection int(6) default 0 comment '目标检测',
    smoke_detection int(6) default 0 comment '烟雾检测',
    distracted_driver_detection int(6) default 0 comment '驾驶员状态检测',
    mask_detection int(6) default 0 comment '口罩检测',
    gender_detection int(6) default 0 comment '年龄检测',
    constraint user_invoking foreign key (username) references users(username)
);

insert into users values ('admin','admin','administrator');