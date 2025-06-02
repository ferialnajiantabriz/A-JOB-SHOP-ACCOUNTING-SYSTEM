INSERT INTO customer (cname, caddress, category) 
VALUES ('ferial', 'tehran', 3);

INSERT INTO department (departmentnumber, departmentdata) 
VALUES (110, 'Computer Science');


INSERT INTO Process (processId, processData) 
VALUES (1234, 'new process');


INSERT INTO fit (fittype, processId) 
VALUES ('new', 1234);

INSERT INTO paint (painttype, paintingmethod, processId) 
VALUES ('new Paint Type', 'new Painting Method', 1234);


INSERT INTO cut (cuttingtype, machinetype, processId) 
VALUES ('new Cutting Type', 'new Machine Type', 1234);


INSERT INTO supervise (processId, departmentnumber) 
VALUES (1234, 110);

--4


INSERT INTO Assembly (AssemblyId, address, dateofordered, AssemblyDetails)
VALUES (110, 'tehran', 2020-10-10 , 'new Assembly Details');


INSERT INTO customer (cname, caddress, category)
VALUES ('Customer 110', '110 Customer Address',1);

INSERT INTO order1 (AssemblyId, cname)
VALUES (110, 'Customer 110');

INSERT INTO passthrough (AssemblyId, processId)
VALUES (110, '1234');

--5




INSERT INTO account (accountnumber, dateofaccount)
VALUES (555, '2023-05-01');

--link with process
INSERT INTO record_cost3 (account_num, process_id)
VALUES (555, 1234);

INSERT INTO processaccount (accountnumber, sup_cost)
VALUES (555, 1500.00);

--link with assembley
INSERT INTO assemblyaccount (accountnumber, sup_cost)
VALUES (555, 3000.00);

INSERT INTO Record_Cost1 (Account_Number, Assembly_id) 
VALUES (555, 110);

--
INSERT INTO departmentaccount (accountnumber, sup_cost)
VALUES (555, 4500.00);

INSERT INTO Record_cost2 (Account_Number, Dep_number) VALUES (555, 110);
DELETE FROM assemblyaccount WHERE accountnumber = 1234 AND sup_cost = 3000;
DELETE FROM departmentaccount WHERE accountnumber = 1234 AND sup_cost = 5000;

--6


INSERT INTO Job (jobNumber, dateofjobcommenced)
VALUES (777, '2023-04-01');



INSERT INTO assign (AssemblyId, processId, jobNumber)
VALUES (110, '1234', 777);

--7
UPDATE Job
SET dateofjobcompleted = '2023-04-15'
WHERE jobNumber = 777;

INSERT INTO fitjob (jobNumber, labortime) 
VALUES (777, 14);



INSERT INTO cutjob (jobNumber, labortime, color, amountoftime, typeofmachine) 
VALUES (777, 15 , 'red', 12 , 'newtype');


insert into paintjob (jobNumber, labortime, color, amountoftime)
values (777, 123,'blue', 567)


--8 

INSERT INTO Transaction1 (transactionnumber, sup_cost)
VALUES (555, 500);

UPDATE departmentaccount
SET sup_cost = sup_cost + 500
WHERE accountnumber = 555;

UPDATE assemblyaccount
SET sup_cost = sup_cost + 500
WHERE accountnumber = 555;

UPDATE processaccount
SET sup_cost = sup_cost + 500
WHERE accountnumber = 555;

--9

SELECT 
    SUM(labor_cost) AS total_labor_cost
FROM (
    SELECT labortime * 12 AS labor_cost FROM fitjob WHERE jobNumber IN (SELECT jobNumber FROM assign WHERE AssemblyId = 110)
    UNION ALL
    SELECT labortime * 12 AS labor_cost FROM paintjob WHERE jobNumber IN (SELECT jobNumber FROM assign WHERE AssemblyId = 110)
    UNION ALL
    SELECT labortime * 12 AS labor_cost FROM cutjob WHERE jobNumber IN (SELECT jobNumber FROM assign WHERE AssemblyId = 110)
) AS labor_costs;

--total supply costs associated with the assembly 

SELECT 
    SUM(sup_cost) AS total_supply_cost
FROM 
    assemblyaccount
WHERE 
    accountnumber IN (SELECT Account_Number FROM Record_Cost1 WHERE Assembly_id = 110);


SELECT 
    (SELECT SUM(labortime * 12) 
     FROM (SELECT labortime FROM fitjob WHERE jobNumber IN (SELECT jobNumber FROM assign WHERE AssemblyId = 110)
           UNION ALL
           SELECT labortime FROM paintjob WHERE jobNumber IN (SELECT jobNumber FROM assign WHERE AssemblyId = 110)
           UNION ALL
           SELECT labortime FROM cutjob WHERE jobNumber IN (SELECT jobNumber FROM assign WHERE AssemblyId = 110)
     ) AS labor_costs
    ) 
    +
    (SELECT SUM(sup_cost) 
     FROM assemblyaccount 
     WHERE accountnumber IN (SELECT Account_Number FROM Record_Cost1 WHERE Assembly_id = 110)
    ) 
AS total_cost;

--10


SELECT 
    SUM(labor_time) AS total_labor_time
FROM (
    SELECT f.labortime AS labor_time 
    FROM fitjob f
    INNER JOIN assign a ON f.jobNumber = a.jobNumber
    INNER JOIN supervise s ON a.processId = s.processId
    INNER JOIN Job j ON f.jobNumber = j.jobNumber
    WHERE s.departmentnumber = 110 AND j.dateofjobcompleted = '2023-04-15'
    UNION ALL
    SELECT p.labortime 
    FROM paintjob p
    INNER JOIN assign a ON p.jobNumber = a.jobNumber
    INNER JOIN supervise s ON a.processId = s.processId
    INNER JOIN Job j ON p.jobNumber = j.jobNumber
    WHERE s.departmentnumber = 110 AND j.dateofjobcompleted = '2023-04-15'
    UNION ALL
    SELECT c.labortime 
    FROM cutjob c
    INNER JOIN assign a ON c.jobNumber = a.jobNumber
    INNER JOIN supervise s ON a.processId = s.processId
    INNER JOIN Job j ON c.jobNumber = j.jobNumber
    WHERE s.departmentnumber = 110 AND j.dateofjobcompleted = '2023-04-15'
) AS combined_labor_times;


--query 11

SELECT 
    p.processId, 
    p.processData, 
    d.departmentnumber, 
    d.departmentdata, 
    j.dateofjobcommenced
FROM 
    passthrough pt
INNER JOIN 
    Process p ON pt.processId = p.processId
INNER JOIN 
    assign a ON pt.processId = a.processId AND pt.AssemblyId = a.AssemblyId
INNER JOIN 
    Job j ON a.jobNumber = j.jobNumber
LEFT JOIN 
    supervise s ON p.processId = s.processId
LEFT JOIN 
    department d ON s.departmentnumber = d.departmentnumber
WHERE 
    pt.AssemblyId = 110
ORDER BY 
    j.dateofjobcommenced;


--query 12:

SELECT cname 
FROM customer 
WHERE category BETWEEN 1 AND 10 
ORDER BY cname;

--query 13 :

DELETE FROM cutjob
WHERE jobNumber BETWEEN 100 AND 200;


--query 14:

UPDATE paintjob 
SET color = 'yellow' 
WHERE jobNumber = 777;
