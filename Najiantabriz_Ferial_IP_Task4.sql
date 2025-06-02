CREATE TABLE Process (
  processId INT,
  processData VARCHAR(40),
  PRIMARY KEY (processId)
);
CREATE TABLE fit (
  fittype VARCHAR(40),
  processId INT,
  PRIMARY KEY (processId),
  FOREIGN KEY (processId) REFERENCES Process(processId)
);
CREATE TABLE paint (
  painttype VARCHAR(40),
  paintingmethod VARCHAR(40),
  processId INT,
  PRIMARY KEY (processId),
  FOREIGN KEY (processId) REFERENCES Process(processId)
);
CREATE TABLE cut (
  cuttingtype VARCHAR(40),
  machinetype VARCHAR(40),
  processId INT,
  PRIMARY KEY (processId),
  FOREIGN KEY (processId) REFERENCES Process(processId)
);
CREATE TABLE Job (
  jobNumber INT,
  dateofjobcommenced DATE, 
  dateofjobcompleted DATE,
  labor 
  PRIMARY KEY (jobNumber)
);
CREATE TABLE fitjob (
  jobNumber INT,
  labortime INT,
  PRIMARY KEY (jobNumber),
  FOREIGN KEY (jobNumber) REFERENCES Job(jobNumber)
);
CREATE TABLE paintjob (
  jobNumber INT,
  labortime INT,
  color VARCHAR(30),
  amountoftime INT,
  PRIMARY KEY (jobNumber),
  FOREIGN KEY (jobNumber) REFERENCES Job(jobNumber)
);
CREATE TABLE cutjob (
  jobNumber INT,
  labortime INT,
  color VARCHAR(40),
  amountoftime INT,
  typeofmachine VARCHAR(40),
  PRIMARY KEY (jobNumber),
  FOREIGN KEY (jobNumber) REFERENCES Job(jobNumber)
);
CREATE TABLE Assembly (
  AssemblyId INT,
  address VARCHAR(80),
  dateofordered DATE, 
  AssemblyDetails VARCHAR(80), 
  PRIMARY KEY (AssemblyId)
);
CREATE TABLE passthrough (
  AssemblyId INT,
  processId INT,
  PRIMARY KEY (AssemblyId, processId),
  FOREIGN KEY (AssemblyId) REFERENCES Assembly(AssemblyId),
  FOREIGN KEY (processId) REFERENCES Process(processId)
);
CREATE TABLE account (
  accountnumber INT,
  dateofaccount DATE, 
  PRIMARY KEY (accountnumber)
);
CREATE TABLE departmentaccount (
  accountnumber INT,
  sup_cost REAL,
  PRIMARY KEY (accountnumber),
  FOREIGN KEY (accountnumber) REFERENCES account(accountnumber)
);
CREATE TABLE assemblyaccount (
  accountnumber INT,
  sup_cost REAL,
  PRIMARY KEY (accountnumber),
  FOREIGN KEY (accountnumber) REFERENCES account(accountnumber)
);
CREATE TABLE processaccount (
  accountnumber INT,
  sup_cost REAL,
  PRIMARY KEY (accountnumber),
  FOREIGN KEY (accountnumber) REFERENCES account(accountnumber)
);
CREATE TABLE Transaction1 (
  transactionnumber INT , 
  sup_cost REAL,
  PRIMARY KEY (transactionnumber)
);
CREATE TABLE assign (
  AssemblyId INT,
  processId INT,
  jobNumber INT, 
  PRIMARY KEY (AssemblyId, processId, jobNumber),
  FOREIGN KEY (AssemblyId) REFERENCES Assembly(AssemblyId),
  FOREIGN KEY (processId) REFERENCES Process(processId),
  FOREIGN KEY (jobNumber) REFERENCES Job(jobNumber)
);
CREATE TABLE record(
transactionnumber int,
jobnumber INT,
PRIMARY key(transactionnumber, jobnumber)
);
CREATE TABLE customer(
cname VARCHAR(100),
caddress VARCHAR(40),
category INT,
PRIMARY KEY(cname),
CONSTRAINT CHK_category CHECK (category IN ('1','2','3','4','5','6','7','8','9','10'))
);
CREATE TABLE order1 (
  AssemblyId INT,
  cname VARCHAR(100),
  PRIMARY KEY (AssemblyId, cname),
  FOREIGN KEY (cname) REFERENCES customer(cname),
  FOREIGN KEY (AssemblyId) REFERENCES Assembly(AssemblyId)
);
CREATE TABLE department (
  departmentnumber INT,
  departmentdata VARCHAR(80),
  PRIMARY KEY (departmentnumber)
);
CREATE TABLE supervise (
  processId INT,
  departmentnumber INT,
  PRIMARY KEY (processId, departmentnumber),
  FOREIGN KEY (processId) REFERENCES Process(processId),
  FOREIGN KEY (departmentnumber) REFERENCES department(departmentnumber)
);
CREATE TABLE record_dept_cost (
  account_num INT,
  dept_num INT,
  PRIMARY KEY (account_num, dept_num),
  FOREIGN KEY (account_num) REFERENCES departmentaccount(accountnumber),
  FOREIGN KEY (dept_num) REFERENCES department(departmentnumber)
);

CREATE TABLE update1(
transactionnumber INT,
accountnumber INT,
FOREIGN KEY (transactionnumber) REFERENCES transaction1(transactionnumber),
FOREIGN KEY (accountnumber) REFERENCES account(accountnumber)
);

create table record_cost3(
account_num INT,
process_id INT,
PRIMARY KEY(account_num, process_id),
FOREIGN KEY(account_num) REFERENCES account(accountnumber),
FOREIGN KEY(process_id) REFERENCES process(processId)
);


CREATE TABLE Record_cost2 (
  Account_Number INT NOT NULL,
  Dep_number INT NOT NULL,
  CONSTRAINT PK_Record_cost2 PRIMARY KEY (Account_Number, Dep_number),
  CONSTRAINT FK_Record_cost2_Account FOREIGN KEY (Account_Number) REFERENCES account(accountnumber),
  CONSTRAINT FK_Record_cost2_Department FOREIGN KEY (Dep_number) REFERENCES department(departmentnumber)
);

CREATE TABLE Record_Cost1 (
  Account_Number INT NOT NULL,
  Assembly_id INT NOT NULL,
  CONSTRAINT PK_Record_Cost1 PRIMARY KEY (Account_Number, Assembly_id),
  CONSTRAINT FK_Record_Cost1_Account FOREIGN KEY (Account_Number) REFERENCES account(accountnumber),
  CONSTRAINT FK_Record_Cost1_Assembly FOREIGN KEY (Assembly_id) REFERENCES Assembly(AssemblyId)
);
