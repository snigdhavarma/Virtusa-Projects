insert into customers values 
(1, 'Rahul', 'Mumbai'),
(2, 'Priya', 'Delhi'),
(3, 'Amit', 'Bangalore'),
(4, 'Neha', 'Mumbai'),
(5, 'Ramu', 'Pune');

insert into products values 
(1, 'Laptop', 'Tech', 55000.00),
(2, 'Mouse', 'Tech', 800.00),
(3, 'Desk', 'Furn', 4500.00),
(4, 'Cup', 'Kitchen', 250.00);

insert into orders (id, cust_id, date) values 
(100, 1, '2023-01-10'),
(101, 2, '2023-01-15'),
(102, 1, '2023-02-05'),
(103, 3, '2023-02-20');

insert into items values 
(100, 1, 1), 
(100, 2, 2), 
(101, 3, 1), 
(102, 4, 3), 
(103, 1, 2);
