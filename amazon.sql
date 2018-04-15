drop table if exists belongTo;
drop table if exists similarTo;
drop table if exists review;
drop table if exists product;
drop table if exists customer;
drop table if exists category;



create table product(
    asin varchar(30) not null,
    title varchar(500),
    salerank int,
    type varchar(30),
    primary key(asin)
);

create table customer(
    cid varchar(30) not null primary key
);

create table review(
    id int not null auto_increment,
    asin varchar(30) not null,
    cid varchar(30),
    review_date varchar(30),
    rating int,
    vote int,
    primary key(id),
    foreign key(asin) references product(asin),
    foreign key(cid) references customer(cid)
);

create table category(
    cat_id int not null primary key,
    name varchar(30) not null
);

create table belongTo(
    asin varchar(30) not null,
    cat_id int not null,
    primary key(asin, cat_id),
    foreign key(asin) references product(asin),
    foreign key(cat_id) references category(cat_id)
);

create table similarTo(
    asin1 varchar(30) not null,
    asin2 varchar(30) not null,
    primary key(asin1, asin2),
    foreign key(asin1) references product(asin),
    foreign key(asin2) references product(asin)
);
