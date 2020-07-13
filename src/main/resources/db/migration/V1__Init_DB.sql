create sequence hibernate_sequence start 1 increment 1;

create table product (
    id int8 not null,
    filename varchar(255),
    name varchar(255),
    description varchar(2048),
    vendor int8,
    attribute_id int8,
    price int4,
    frame int4,
    primary key (id)
);

create table user_role (
    user_id int8 not null,
    roles varchar(255)
);

create table usr (
    id int8 not null,
    activation_code varchar(255),
    active boolean not null,
    email varchar(255),
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
);

create table attribute (
    id int8 not null,
    product_id int8 not null,
    attribute_id int4 not null,
    group_id int4 not null
);

alter table if exists user_role
    add constraint user_role_user_fk
    foreign key (user_id) references usr;

alter table if exists attribute
    add constraint attribute_product_fk
    foreign key (product_id) references product;
