# Write your MySQL query statement below
SELECT d.name AS Department, e.name as Employee, e.salary as Salary
FROM Department d
LEFT JOIN Employee e
ON e.Departmentid=d.Id
WHERE (e.DepartmentId, e.salary) IN (
    SELECT DepartmentId, MAX(salary)
    FROM Employee
    GROUP BY DepartmentId
);