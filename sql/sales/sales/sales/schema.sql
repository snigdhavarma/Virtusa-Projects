drop table if exists items;
drop table if exists orders;
drop table if exists products;
drop table if exists customers;

create table customers (
    id int primary key,
    name varchar(100),
    city varchar(100)
);

create table products (
    id int primary key,
    name varchar(100),
    cat varchar(50),
    price decimal(10,2)
);

create table orders (
    id int primary key,
    cust_id int,
    date date,
    foreign key(cust_id) references customers(id)
);

create table items (
    order_id int,
    product_id int,
    qty int,
    primary key(order_id, product_id),
    foreign key(order_id) references orders(id),
    foreign key(product_id) references products(id)
);
