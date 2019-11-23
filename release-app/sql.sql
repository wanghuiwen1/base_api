create table app
(
    id        bigint auto_increment primary key,
    name      varchar(50)  not null,
    index_url varchar(300) not null
)
    comment 'app上传';

create table ylt.app_history
(
    id          bigint auto_increment
        primary key,
    app_id      bigint       not null,
    url         varchar(300) not null,
    platform    varchar(50)  not null,
    version     varchar(50)  not null,
    info        varchar(500) not null,
    create_date datetime     null,
    qrcode      varchar(500) null,
    md5         varchar(255) not null
)
    comment 'app上传历史';

