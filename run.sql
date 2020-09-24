# 创建数据库
create database cloud;

# 创建user表
create table user
(
    id       int auto_increment
        primary key,
    username varchar(255) not null,
    password varchar(255) not null,
    type     tinyint      not null,
    constraint user_username_uindex
        unique (username)
);

# 创建container表
create table container
(
    id       int auto_increment
        primary key,
    name     varchar(255) not null,
    image    varchar(255) not null,
    uid      int          not null,
    username varchar(255) not null,
    port     varchar(255) null,
    cid      varchar(255) not null,
    constraint container_cid_uindex
        unique (cid)
);

