# SQL Interview Cheat Sheet

This cheat sheet provides a quick overview of essential SQL commands, concepts, and functions frequently encountered in interviews.

---

## 1. Basic Commands

* **`SELECT`**: Retrieves data from a database.

    ```sql
    SELECT column1, column2 FROM TableName;
    SELECT * FROM TableName; -- Selects all columns

    ```

* **`FROM`**: Specifies the table(s) from which to retrieve data.

    ```sql
    SELECT column FROM TableName;

    ```

* **`AS`**: Renames a column or table with an alias.

    ```sql
    SELECT FIRST_NAME AS WorkerName FROM Worker;
    SELECT w.FIRST_NAME FROM Worker AS w;

    ```

* **`DISTINCT`**: Returns only unique values.

    ```sql
    SELECT DISTINCT DEPARTMENT FROM Worker;

    ```

* **`ORDER BY`**: Sorts the result-set in ascending (ASC) or descending (DESC) order.

    ```sql
    SELECT SALARY FROM Worker ORDER BY SALARY DESC;
    SELECT FIRST_NAME, DEPARTMENT FROM Worker ORDER BY DEPARTMENT ASC, FIRST_NAME DESC;

    ```

* **`LIMIT` / `OFFSET`**: Restricts the number of rows returned by a query. Useful for Nth highest/lowest.

    * **Syntax for Nth Highest (N-1 offset)**

        ```sql
        SELECT DISTINCT SALARY FROM Worker ORDER BY SALARY DESC LIMIT 1 OFFSET (N-1);
        -- Example for 2nd highest salary:
        SELECT DISTINCT SALARY FROM Worker ORDER BY SALARY DESC LIMIT 1 OFFSET 1;

        ```

## 2. Filtering Data (`WHERE` Clause)

* **`WHERE`**: Filters records based on specified conditions.

    ```sql
    SELECT * FROM Worker WHERE DEPARTMENT = 'HR';

    ```

* **Logical Operators (`AND`, `OR`, `NOT`)**: Combine conditions.

    ```sql
    SELECT * FROM Worker WHERE SALARY > 100000 AND DEPARTMENT = 'HR';
    SELECT * FROM Worker WHERE DEPARTMENT = 'HR' OR DEPARTMENT = 'Admin';
    SELECT * FROM Worker WHERE NOT DEPARTMENT = 'HR';

    ```

* **`IN` / `NOT IN`**: Specifies multiple possible values for a column.

    ```sql
    SELECT * FROM Worker WHERE DEPARTMENT IN ('HR', 'Admin');
    SELECT * FROM Worker WHERE DEPARTMENT NOT IN ('HR', 'Admin');

    ```

* **`BETWEEN`**: Selects values within a given range (inclusive).

    ```sql
    SELECT * FROM Worker WHERE SALARY BETWEEN 50000 AND 100000;

    ```

* **`LIKE`**: Searches for a specified pattern in a column.

    * `%`: Represents zero or more characters.

    * `_`: Represents a single character.

    ```sql
    SELECT * FROM Worker WHERE FIRST_NAME LIKE 'A%'; -- Starts with 'A'
    SELECT * FROM Worker WHERE FIRST_NAME LIKE '%a'; -- Ends with 'a'
    SELECT * FROM Worker WHERE FIRST_NAME LIKE '%a%'; -- Contains 'a'
    SELECT * FROM Worker WHERE FIRST_NAME LIKE '_____h'; -- Exactly 6 characters, ends with 'h'

    ```

* **`IS NULL` / `IS NOT NULL`**: Tests for NULL values.

    ```sql
    SELECT * FROM Worker WHERE DEPARTMENT IS NULL;

    ```

## 3. Aggregate Functions & Grouping

* **Aggregate Functions**: Perform calculations on a set of rows and return a single summary row.

    * `COUNT()`: Number of rows.

        ```sql
        SELECT COUNT(*) FROM Worker WHERE DEPARTMENT = 'Admin';

        ```

    * `SUM()`: Total sum of a numeric column.

        ```sql
        SELECT SUM(SALARY) FROM Worker;

        ```

    * `AVG()`: Average value of a numeric column.

        ```sql
        SELECT AVG(SALARY) FROM Worker;

        ```

    * `MIN()`: Minimum value of a column.

        ```sql
        SELECT MIN(SALARY) FROM Worker;

        ```

    * `MAX()`: Maximum value of a column.

        ```sql
        SELECT MAX(SALARY) FROM Worker;

        ```

* **`GROUP BY`**: Groups rows that have the same values in specified columns into summary rows.

    ```sql
    SELECT DEPARTMENT, COUNT(WORKER_ID) FROM Worker GROUP BY DEPARTMENT;

    ```

* **`HAVING`**: Filters groups created by `GROUP BY` (similar to `WHERE` but for groups).

    ```sql
    SELECT DEPARTMENT, COUNT(WORKER_ID) AS TotalWorkers
    FROM Worker
    GROUP BY DEPARTMENT
    HAVING TotalWorkers < 4;

    ```

## 4. Joins

Combine rows from two or more tables based on a related column between them.

* **`INNER JOIN`**: Returns rows when there is a match in *both* tables.

    ```sql
    SELECT W.FIRST_NAME, T.WORKER_TITLE
    FROM Worker W
    INNER JOIN Title T ON W.WORKER_ID = T.WORKER_REF_ID;

    ```

* **`LEFT JOIN` (or `LEFT OUTER JOIN`)**: Returns all rows from the left table, and the matching rows from the right table. If no match, NULLs from the right table.

    ```sql
    SELECT W.FIRST_NAME, B.BONUS_AMOUNT
    FROM Worker W
    LEFT JOIN Bonus B ON W.WORKER_ID = B.WORKER_REF_ID;

    ```

* **`RIGHT JOIN` (or `RIGHT OUTER JOIN`)**: Returns all rows from the right table, and the matching rows from the left table. If no match, NULLs from the left table.
    *(Note: SQLite does not directly support `RIGHT JOIN`. You can achieve the same result with a `LEFT JOIN` by swapping table order.)*

    ```sql
    -- Conceptual example (not direct SQLite syntax often):
    -- SELECT W.FIRST_NAME, B.BONUS_AMOUNT FROM Worker W RIGHT JOIN Bonus B ON W.WORKER_ID = B.WORKER_REF_ID;
    -- In SQLite, you'd reverse the join:
    SELECT W.FIRST_NAME, B.BONUS_AMOUNT
    FROM Bonus B
    LEFT JOIN Worker W ON W.WORKER_ID = B.WORKER_REF_ID;

    ```

* **`FULL OUTER JOIN` (or `FULL JOIN`)**: Returns all rows when there is a match in *either* left or right table.
    *(Note: SQLite does not directly support `FULL OUTER JOIN`. It's often simulated using `UNION ALL` with `LEFT JOIN` and `RIGHT JOIN` excluding common rows.)*

    ```sql
    -- Conceptual Example for Full Outer Join:
    -- SELECT * FROM TableA FULL OUTER JOIN TableB ON TableA.id = TableB.id;

    ```

## 5. Subqueries (Nested Queries)

A query nested inside another query.

* **In `WHERE` clause**:

    ```sql
    SELECT * FROM Worker
    WHERE SALARY = (SELECT MAX(SALARY) FROM Worker);

    ```

* **In `FROM` clause (Derived Tables/Inline Views)**:

    ```sql
    SELECT T.DEPARTMENT, T.MaxSalary
    FROM (SELECT DEPARTMENT, MAX(SALARY) AS MaxSalary FROM Worker GROUP BY DEPARTMENT) AS T;

    ```

* **Correlated Subquery (for Nth Max/Min)**:

    ```sql
    -- Nth highest salary
    SELECT DISTINCT SALARY
    FROM Worker w1
    WHERE (N - 1) = (SELECT COUNT(DISTINCT w2.SALARY) FROM Worker w2 WHERE w2.SALARY > w1.SALARY);
    -- Example for 5th highest salary:
    SELECT DISTINCT SALARY FROM Worker w1 WHERE 4 = (SELECT COUNT(DISTINCT w2.SALARY) FROM Worker w2 WHERE w2.SALARY > w1.SALARY);

    ```

## 6. Set Operators

Combine the result sets of two or more `SELECT` statements. All `SELECT` statements must have the same number of columns and compatible data types.

* **`UNION`**: Returns distinct rows from both queries.

    ```sql
    SELECT FIRST_NAME FROM Worker
    UNION
    SELECT FIRST_NAME FROM WorkerClone;

    ```

* **`UNION ALL`**: Returns all rows from both queries, including duplicates.

    ```sql
    SELECT FIRST_NAME FROM Worker
    UNION ALL
    SELECT FIRST_NAME FROM WorkerClone;

    ```

* **`INTERSECT`**: Returns only rows that are common to both queries.
    *(Note: SQLite does not directly support `INTERSECT`. It's often simulated with `INNER JOIN` or `EXISTS`.)*

    ```sql
    -- Simulation in SQLite using INNER JOIN:
    SELECT W.* FROM Worker W INNER JOIN WorkerClone WC ON W.WORKER_ID = WC.WORKER_ID;

    ```

* **`EXCEPT` (or `MINUS` in some DBs)**: Returns rows from the first query that are not in the second query.
    *(Note: SQLite supports `EXCEPT`.)*

    ```sql
    SELECT WORKER_ID FROM Worker
    EXCEPT
    SELECT WORKER_REF_ID FROM Bonus; -- Workers who do not get bonus

    ```

    * **Simulation for `MINUS` (using `LEFT JOIN`):**

        ```sql
        SELECT W.*
        FROM Worker W
        LEFT JOIN WorkerClone WC ON W.WORKER_ID = WC.WORKER_ID
        WHERE WC.WORKER_ID IS NULL;

        ```

## 7. Data Definition Language (DDL)

Used to define, modify, or drop database objects.

* **`CREATE TABLE`**: Creates a new table.

    ```sql
    CREATE TABLE NewTable (
        ID INT PRIMARY KEY,
        Name VARCHAR(100)
    );

    ```

* **`ALTER TABLE`**: Modifies an existing table (e.g., add/drop columns, change data type).

    ```sql
    ALTER TABLE NewTable ADD COLUMN Age INT;

    ```

* **`DROP TABLE`**: Deletes an existing table.

    ```sql
    DROP TABLE NewTable;

    ```

* **`TRUNCATE TABLE`**: Deletes all data from a table, but keeps the table structure. (DDL because it implicitly commits)

    ```sql
    TRUNCATE TABLE NewTable;

    ```

## 8. Data Manipulation Language (DML)

Used for managing data within schema objects.

* **`INSERT INTO`**: Adds new rows to a table.

    ```sql
    INSERT INTO Worker (WORKER_ID, FIRST_NAME, SALARY, DEPARTMENT)
    VALUES (9, 'New', 'User', 70000, 'IT');

    ```

* **`UPDATE`**: Modifies existing data in a table.

    ```sql
    UPDATE Worker SET SALARY = 110000 WHERE FIRST_NAME = 'Monika';

    ```

* **`DELETE FROM`**: Deletes rows from a table.

    ```sql
    DELETE FROM Worker WHERE FIRST_NAME = 'New User';

    ```

## 9. Common Functions (SQLite Specific Examples)

* **String Functions**:

    * `UPPER(string)`: Converts to uppercase.

        ```sql
        SELECT UPPER(FIRST_NAME) FROM Worker;

        ```

    * `LOWER(string)`: Converts to lowercase.

    * `LENGTH(string)`: Returns the length of a string.

        ```sql
        SELECT DEPARTMENT, LENGTH(DEPARTMENT) FROM Worker;

        ```

    * `SUBSTR(string, start, length)`: Extracts a substring. (Often `SUBSTRING` in other DBs)

        ```sql
        SELECT SUBSTR(FIRST_NAME, 1, 3) FROM Worker;

        ```

    * `REPLACE(string, old_string, new_string)`: Replaces occurrences of a substring.

        ```sql
        SELECT REPLACE(FIRST_NAME, 'a', 'A') FROM Worker;

        ```

    * `INSTR(string, substring)`: Returns the starting position of the first occurrence of `substring` within `string`. (Case-sensitive depends on collation)

        ```sql
        SELECT INSTR(FIRST_NAME, 'B') FROM Worker WHERE FIRST_NAME = 'Amitabh';

        ```

    * `RTRIM(string)`: Removes trailing spaces.

    * `LTRIM(string)`: Removes leading spaces.

    * `TRIM(string)`: Removes both leading and trailing spaces.

    * `||` (Concatenation): Joins strings. (Often `CONCAT()` in other DBs)

        ```sql
        SELECT FIRST_NAME || ' ' || LAST_NAME AS COMPLETE_NAME FROM Worker;

        ```

* **Numeric Functions**:

    * `ABS(number)`: Absolute value.

    * `ROUND(number, decimals)`: Rounds to nearest integer or specified decimal places.

    * `MOD(number, divisor)`: Returns the remainder of a division. (Often `%` operator in SQLite)

        ```sql
        SELECT * FROM Worker WHERE WORKER_ID % 2 != 0; -- Odd rows

        ```

* **Date & Time Functions (SQLite)**:

    * `DATE('now')`: Current date.

    * `DATETIME('now')`: Current date and time.

    * `STRFTIME(format, date_string)`: Formats dates.

        ```sql
        -- Workers who joined in Feb 2014
        SELECT * FROM Worker WHERE STRFTIME('%Y', JOINING_DATE) = '2014' AND STRFTIME('%m', JOINING_DATE) = '02';

        ```

## 10. Advanced Concepts (Brief Mention)

* **Window Functions**: Perform calculations across a set of table rows that are related to the current row. E.g., `ROW_NUMBER()`, `RANK()`, `DENSE_RANK()`, `NTILE()`, `LAG()`, `LEAD()`. Often used for Nth highest/lowest in more complex scenarios or for ranking within groups.

* **Common Table Expressions (CTEs) (`WITH` clause)**: Temporary, named result sets that you can reference within a single `SELECT`, `INSERT`, `UPDATE`, or `DELETE` statement. Improves readability and can be used for recursive queries.

---

Remember, practice is key! Use the provided SQL environment to try out these commands and variations. Good luck with your interview!