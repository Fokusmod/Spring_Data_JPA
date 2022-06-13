create table products
(
    id    bigserial primary key,
    title varchar(255),
    price int
);
insert into products (title, price)
values ('Apple', 150),
       ('Banana', 100),
       ('Orange', 120),
       ('Papaya', 120),
       ('Milk', 140),
       ('Cheese', 270),
       ('Cheeps', 100),
       ('Potato', 70),
       ('Chicken egg', 120),
       ('Meat', 300),
       ('Chocolate', 130),
       ('Kiwi', 140),
       ('Tomato', 120),
       ('Pineapple', 200),
       ('Bread', 300),
       ('Tea', 250),
       ('Coffee', 300),
       ('Cacao', 150),
       ('Bear', 210),
       ('Fish', 300);
