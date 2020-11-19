SELECT Student.studentID,
       Student.studentName,
       Student.cityName,
       Grade.courseName,
       Grade.grade
FROM Student
         LEFT JOIN Grade
                   ON Student.studentID = Grade.studentID;


--List of all students and their grades in their courses:
SELECT s.studentName, g.courseName, COALESCE(g.grade, 'Not graded yet')
FROM Grade AS g
         INNER JOIN Student s
                    on s.studentID = g.studentID
WHERE g.studentID = ?;

--Average grade for SD 2019 autumn:
SELECT c.courseName, AVG(g.grade)
FROM Grade AS g
         INNER JOIN Course AS c
                    ON c.courseName = g.courseName
WHERE c.courseName = 'SD 2019 autumn';


--Average grade for ES1 2019 autumn:
SELECT c.courseName, COALESCE(AVG(grade), 'Not graded yet')
FROM Grade AS g
         INNER JOIN Course AS c
                    ON c.courseName = g.courseName
WHERE c.courseName = 'ES1 2019 autumn';

--Average grade for SD 2020 spring:
SELECT c.courseName, COALESCE(AVG(grade), 'Not graded yet')
FROM Grade AS g
         INNER JOIN Course AS c
                    ON c.courseName = g.courseName
WHERE c.courseName = 'SD 2020 spring';

--List of all grades in a course:
SELECT c.courseName, COALESCE(g.grade, 'Not graded yet')
FROM Course AS c
         INNER JOIN Grade AS g
                    ON c.courseName = c.courseName;

--Calculate the average grade for a specific student:
SELECT s.studentID, s.studentName, AVG(g.grade)
FROM Student AS s
         INNER JOIN Grade AS g
                    ON s.studentID = g.studentID
WHERE g.studentID = ?;

--List of the average grade in a specific city:
SELECT c.cityName, AVG(g.grade)
FROM City AS c
         INNER JOIN Student as s
                    ON c.cityName = s.cityName
         INNER JOIN Grade as g
                    ON g.studentID = s.studentID
WHERE s.cityName = ?;

UPDATE Grade
SET grade = ?
WHERE studentID = ? AND courseName = ?;
