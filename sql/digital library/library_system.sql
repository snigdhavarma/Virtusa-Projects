
DROP TABLE IF EXISTS issued_books;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS books;

-- STUDENTS TABLE
CREATE TABLE students (
    id INT PRIMARY KEY,
    fname VARCHAR(50),
    lname VARCHAR(50),
    email VARCHAR(100),
    join_date DATE
);

-- BOOKS TABLE
CREATE TABLE books (
    id INT PRIMARY KEY,
    title VARCHAR(200),
    author VARCHAR(100),
    category VARCHAR(50)
);

-- ISSUED BOOKS TABLE
CREATE TABLE issued_books (
    id INT PRIMARY KEY,
    student_id INT,
    book_id INT,
    issue_date DATE,
    return_date DATE,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);


-- Insert Students
INSERT INTO students (id, fname, lname, email, join_date) VALUES
(1, 'Rahul', 'Sharma', 'rahul.sharma@example.com', '2020-01-15'),
(2, 'Priya', 'Patel', 'priya.patel@example.com', '2023-08-20'),
(3, 'Anjali', 'Gupta', 'anjali.gupta@example.com', '2019-05-10');

-- Insert Books
INSERT INTO books (id, title, author, category) VALUES
(101, 'The Guide', 'R.K. Narayan', 'Fiction'),
(102, 'Ignited Minds', 'A.P.J. Abdul Kalam', 'Science'),
(103, 'A Brief History of Modern India', 'Rajiv Ahir', 'History'),
(104, 'The God of Small Things', 'Arundhati Roy', 'Fiction');

-- Insert Issued Books
INSERT INTO issued_books (id, student_id, book_id, issue_date, return_date) VALUES
(1, 1, 101, CURRENT_DATE - INTERVAL 20 DAY, NULL),
(2, 2, 102, CURRENT_DATE - INTERVAL 5 DAY, NULL),  
(3, 1, 103, CURRENT_DATE - INTERVAL 40 DAY, CURRENT_DATE - INTERVAL 10 DAY),
(4, 2, 104, CURRENT_DATE - INTERVAL 25 DAY, NULL);

-- Students who did not return books within 14 days

SELECT 
    s.fname,
    s.lname,
    b.title,
    ib.issue_date
FROM students s
JOIN issued_books ib 
    ON s.id = ib.student_id
JOIN books b 
    ON ib.book_id = b.id
WHERE ib.return_date IS NULL
AND ib.issue_date < CURRENT_DATE - INTERVAL 14 DAY;


-- Most borrowed categories

SELECT 
    b.category,
    COUNT(ib.id) AS borrows
FROM books b
JOIN issued_books ib 
    ON b.id = ib.book_id
GROUP BY b.category
ORDER BY borrows DESC;


-- Delete inactive students who haven't borrowed books in 3 years

DELETE FROM students
WHERE id NOT IN (
    SELECT DISTINCT student_id
    FROM issued_books
    WHERE issue_date >= CURRENT_DATE - INTERVAL 3 YEAR
);
