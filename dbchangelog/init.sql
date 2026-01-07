CREATE DATABASE IF NOT EXISTS `ars_notification` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `ars_notification`;

DROP TABLE IF EXISTS notification;
create table notification
(
    id                 int auto_increment primary key,
    type               int                                 not null,
    title              varchar(255)                        null,
    content            text                                null,
    sender_id          int                                 null,
    sender_name        varchar(255)                        null,
    sender_email       varchar(255)                        null,
    receiver_id        int                                 null,
    receiver_name      varchar(255)                        null,
    receiver_email     varchar(255)                        null,
    created_by         varchar(50)                         null,
    last_modified_by   varchar(50)                         null,
    created_date       timestamp default CURRENT_TIMESTAMP null,
    last_modified_date timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
collate = utf8mb4_unicode_ci;
