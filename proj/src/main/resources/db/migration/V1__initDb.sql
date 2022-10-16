create table item_tags (
    id bigserial primary key,
    name varchar(100) unique
);

create table users (
    id bigserial primary key,
    email varchar(100) not null unique,
    password varchar(255),
    provider varchar(100),
    status varchar(50),
    role varchar(50),
    language varchar(50),
    skin varchar(50)
);

create table collections (
    id bigserial primary key,
    name varchar(100),
    description varchar(1000),
    theme varchar(50),
    photo_uri varchar(255),
    user_id bigint,
    constraint fk_colluser foreign key (user_id) references users (id)
);

create table tab_items (
    id bigserial primary key,
    name varchar(255),
    fields jsonb,
    collection_id bigint,
    constraint fk_collitem foreign key (collection_id) references collections(id)
);

create table items_tags (
    tag_id bigint,
    item_id bigint,
    constraint fk_tagitem foreign key (tag_id) references item_tags(id),
    constraint fk_itemteg foreign key (item_id) references tab_items(id)
);

create table comments (
    id bigserial primary key,
    description varchar(1500),
    item_id bigint,
    constraint fk_commitem foreign key (item_id) references tab_items(id),
    user_id bigint,
    constraint fk_commuser foreign key (user_id) references users(id)
);

create table comment_likes (
    id bigserial primary key,
    comment_id bigint,
    constraint fk_likecomm foreign key (comment_id) references comments(id),
    user_id bigint,
    constraint fk_likeuser foreign key (user_id) references users(id)
);


