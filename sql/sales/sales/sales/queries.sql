select p.name, sum(i.qty) as total
from products p
join items i on p.id = i.product_id
group by p.name
order by total desc;

select c.name, sum(i.qty * p.price) as total_spent
from customers c
join orders o on c.id = o.cust_id
join items i on o.id = i.order_id
join products p on i.product_id = p.id
group by c.name
order by total_spent desc;

select month(o.date) as month, sum(i.qty * p.price) as revenue
from orders o
join items i on o.id = i.order_id
join products p on i.product_id = p.id
group by month(o.date);

select p.cat, sum(i.qty * p.price) as sales
from products p
join items i on p.id = i.product_id
group by p.cat;

select name, city 
from customers 
where id not in (select cust_id from orders);
