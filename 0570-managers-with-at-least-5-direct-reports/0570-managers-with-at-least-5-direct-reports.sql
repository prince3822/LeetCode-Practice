# Write your MySQL query statement below
select e.name
FROM Employee e
JOIN Employee m
ON e.id=m.managerId
GROUP BY e.id, e.name
HAVING COUNT(m.id)>=5