import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.BufferedReader;
import java.sql.Types;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class fina {
 // Database credentials
    final static String HOSTNAME = "naji0002-sql-server.database.windows.net";
    final static String DBNAME = "cs-dsa-4513-sql-db";
    final static String USERNAME = "naji0002";
    final static String PASSWORD = "Scareface2010";

    // Database connection string
    final static String URL = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
            HOSTNAME, DBNAME, USERNAME, PASSWORD);

    // Query templates
    final static String QUERY_TEMPLATE_1 = "INSERT INTO customer " + 
                                           "VALUES (?, ?, ?);";
    final static String QUERY_TEMPLATE_2 = "INSERT INTO department " + 
			"VALUES (?, ?);";
final static String QUERY_TEMPLATE_3 = "INSERT INTO Process " + 
"VALUES (?, ?);";
final static String QUERY_TEMPLATE_3_1 = "INSERT INTO fit " + 
			"VALUES (?, ?);";
final static String QUERY_TEMPLATE_3_2 = "INSERT INTO paint " + 
"VALUES (?, ?, ?);";
final static String QUERY_TEMPLATE_3_3 = "INSERT INTO cut " + 
"VALUES (?, ?, ?);";
final static String QUERY_TEMPLATE_3_4 ="INSERT into supervise " +
			"VALUES (?, ?, ?);"; 
final static String QUERY_TEMPLATE_4_1 ="INSERT into assembly " +
"VALUES (?, ?, ?);"; 
final static String QUERY_TEMPLATE_4_2 ="INSERT into order1 " +
"VALUES (?, ?);"; 
final static String QUERY_TEMPLATE_4_3 ="INSERT into passthrough " +
"VALUES (?, ?);"; 
final static String QUERY_TEMPLATE_5_1 ="INSERT into account " +
"VALUES (?, ?);"; 
final static String QUERY_TEMPLATE_5_2 ="INSERT into departmentaccount " +
"VALUES (?, ?);"; 
final static String QUERY_TEMPLATE_5_3 ="INSERT into assemblyaccount " +
"VALUES (?, ?);"; 
final static String QUERY_TEMPLATE_5_4 ="INSERT into processaccount " +
"VALUES (?, ?);"; 
final static String QUERY_TEMPLATE_5_5 ="INSERT into record_dept_cost " +
"VALUES (?, ?);"; 
final static String QUERY_TEMPLATE_5_6 ="INSERT into record_assembly_cost " +
"VALUES (?, ?);"; 
final static String QUERY_TEMPLATE_5_7 ="INSERT into record_process_cost " +
"VALUES (?, ?);"; 
final static String QUERY_TEMPLATE_6_1 ="INSERT into job  " +
"VALUES (?, ?, ?, ?, ?);";
final static String QUERY_TEMPLATE_6_2 ="INSERT into assign  " +
"VALUES (?, ?, ?);";

final static String QUERY_TEMPLATE_7_1 ="UPDATE job " +
		"SET  dateofjobcompleted = (?), job_type = (?), labortime = (?)" +
		"WHERE jobNumber = (?);";
final static String QUERY_TEMPLATE_7_2 ="INSERT into cutjob  " +
"VALUES (?, ?, ?, ?, ?);";
final static String QUERY_TEMPLATE_7_3 ="INSERT into paintjob  " +
"VALUES (?, ?, ?, ?);";
final static String QUERY_TEMPLATE_7_4 ="INSERT into fitjob  " +
"VALUES (?, ?);";
final static String QUERY_TEMPLATE_8_1 ="INSERT into transaction1  " +
"VALUES (?, ?);";
final static String QUERY_TEMPLATE_8_2 ="INSERT into record  " +
"VALUES (?, ?);";
final static String QUERY_TEMPLATE_8_3 ="SELECT Assemblyid, processId from assign  " +
" WHERE jobnomber = (?);";
final static String QUERY_TEMPLATE_8_4 ="SELECT departmentnumber FROM supervise " +
"WHERE processId =(?);";
final static String QUERY_TEMPLATE_8_5 ="SELECT account_num FROM record_dept_cost " +
"WHERE dept_num =(?);";
final static String QUERY_TEMPLATE_8_6 ="SELECT account_num FROM record_assembly_cost " +
"WHERE assembly_id =(?);";
final static String QUERY_TEMPLATE_8_7 ="SELECT account_num FROM record_process_cost " +
"WHERE process_id =(?);";
final static String QUERY_TEMPLATE_8_8 ="SELECT sup_cost FROM departmentaccount " +
"WHERE accountnumber =(?);";
final static String QUERY_TEMPLATE_8_9 ="SELECT sup_cost FROM assemblyaccount " +
"WHERE accountnumber =(?);";
final static String QUERY_TEMPLATE_8_10 ="SELECT sup_cost FROM processaccount " +
"WHERE accountnumber =(?);";
final static String QUERY_TEMPLATE_8_11 = "UPDATE departmentaccount " +
"SET sup_cost = (?)" +
"Where accountnumber = (?);";
final static String QUERY_TEMPLATE_8_12 = "UPDATE processaccount " +
"SET sup_cost = (?)" +
"Where accountnumber = (?);";

final static String QUERY_TEMPLATE_10_1 ="SELECT DISTINCT processId " + 
"FROM supervise WHERE departmentnumber =  (?); ";
final static String QUERY_TEMPLATE_10_2 ="SELECT DISTINCT jobNumber " + 
"FROM assign WHERE processId =  (?); ";
final static String QUERY_TEMPLATE_10_3 ="SELECT labortime, dateofjobcompleted " + 
"FROM Job WHERE jobNumber =  (?); ";
final static String QUERY_TEMPLATE_11 = "select supervise.processId, departmentnumber "
+ "from Assembly, process, supervise "
+ "where supervise.processId = process.processId "
+ "and Assembly.Assemblyid = (?) "
+ "order by dateofordered ASC";

final static String QUERY_TEMPLATE_12 = "SELECT * FROM customer WHERE category >= (?) and category < (?) "   ;

final static String QUERY_TEMPLATE_13_1 = "delete from cutjob Where jobnumber > (?) and jobnumber < (?) " ;
final static String QUERY_TEMPLATE_13_2 = "delete from assign Where jobnumber > (?) and jobnumber < (?) " ;
final static String QUERY_TEMPLATE_13_3 = "delete from job Where jobnumber > (?) and jobnumber < (?) " ;
final static String QUERY_TEMPLATE_14 ="UPDATE paintjob " +
"SET color = (?)" +
"Where jobnumber = (?);";
// User input prompt// 
final static String PROMPT = 
"\nPlease select one of the options below: \n" +
"1) Enter new customer; \n" + 
"2) Enter a new department; \n" + 
"3) Enter a new Process ; \n" +
"4) Enter a new Assembly: \n" +
"5) Enter a new Account Number:\n" + 
"6) Enter a new Job:\n"+
"7) At the completion of a job, enter the date it completed: \n " +
"8) Enter a transaction-no and its sup-cost and update all the costs: \n" + 
"9) Retrieve the total cost incurred on an assembly-id \n " +
"10)Retrieve total labor time within a Department for a given day: \n " +
"11)Retrieve the process through which a given assembly-id has passed: \n" +
"12)Retrieve the customers (in name order) whose category is in a given range: \n" +
"13)Delete all cut-jobs whose job-no is in a given range: \n"+
"14)Change the color of a given paint job\n" +
"15)Import: enter new customers from a data file: \n"+
"16)Export: Retrieve the customers \n" + 
"17) Quit!";

public static void main(String[] args) throws SQLException {

System.out.println("WELCOME TO THE JOB-SHOP ACCOUNTING DATABASE SYSTEM");

final Scanner sc = new Scanner(System.in); // Scanner is used to collect the user input
String option = ""; // Initialize user option selection as nothing
while (!option.equals("3")) { // As user for options until option 3 is selected
System.out.println(PROMPT); // Print the available options
option = sc.next(); // Read in the user option selection

switch (option) { // Switch between different options
case "1": // Insert a new customer
    // Collect customer data from user input
    System.out.println("Please enter customer name:");
    final String cname = sc.next();
    sc.nextLine(); // Consume the newline character left by next()

    System.out.println("Please enter customer address:");
    final String caddress = sc.nextLine(); // Read customer address, allowing white-spaces

    System.out.println("Please enter customer category:");
    final float category = sc.nextFloat(); // Read customer category as a float value
    sc.nextLine(); // Consume the newline character left by nextFloat()

    System.out.println("Connecting to the database...");
    // Establish a database connection and prepare the statement
    try (Connection connection = DriverManager.getConnection(URL);
         PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_1)) {
        // Set parameters for the prepared statement
        statement.setString(1, cname);
        statement.setString(2, caddress);
        statement.setFloat(3, category);

        System.out.println("Dispatching the query...");
        // Execute the query and retrieve the number of rows inserted
        int rowsInserted = statement.executeUpdate();
        System.out.println(String.format("Done. %d rows inserted.", rowsInserted));
    } catch (SQLException e) {
        // Handle any SQL exceptions
        e.printStackTrace();
    }
    break;
case "2":
    System.out.println("Please enter department number");
    final int departmentnumber = sc.nextInt(); // Read in the user input for department number

    // Consuming the leftover newline character from the previous nextInt call
    sc.nextLine();

    System.out.println("Please enter department data:");
    final String departmentdata = sc.nextLine(); // Read in user input for department data

    System.out.println("Connecting to the database...");
    // Attempting to establish a connection to the database
    try (final Connection connection = DriverManager.getConnection(URL)) {
        // Preparing a statement for database insertion
        try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_2)) {
            // Setting the parameters for the prepared statement
            statement.setInt(1, departmentnumber);
            statement.setString(2, departmentdata);

            System.out.println("Dispatching the query...");
            // Executing the update and getting the number of rows affected
            final int rows_inserted = statement.executeUpdate();
            System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
        }
    } catch (SQLException e) {
        // Handling potential SQL exceptions
        e.printStackTrace();
    }

    break;

case "3": // Insert a new process option
    // Collect the new process data from the user
    System.out.println("Please enter process Id:");
    final int processId = sc.nextInt();
    sc.nextLine();  // Consume the leftover newline

    System.out.println("Please enter the process Data:");
    final String processData = sc.nextLine();

    System.out.println("Please enter the Supervising Department ID:");
    final int deptnum = sc.nextInt();
    sc.nextLine();  // Consume the leftover newline

    System.out.println("Please enter the Department data:");
    final String deptData = sc.nextLine();

    System.out.println("Please enter : 1. For Fit , 2. For Paint, 3. for Cut Process:");
    final int processmodel = sc.nextInt();
    sc.nextLine();  // Consume the leftover newline

    String additionalType = "";
    String additionalData1 = "";
    String additionalData2 = "";
    String queryTemplateAdditional = "";

    // Determine the type of process
    if (processmodel == 1) {
        System.out.println("Please enter Fit type:");
        additionalType = "Fit";
        additionalData1 = sc.nextLine();
        queryTemplateAdditional = QUERY_TEMPLATE_3_1;
    } else if (processmodel == 2) {
        System.out.println("Please enter Paint type:");
        additionalType = "Paint";
        additionalData1 = sc.nextLine();
        System.out.println("Please enter Paint method:");
        additionalData2 = sc.nextLine();
        queryTemplateAdditional = QUERY_TEMPLATE_3_2;
    } else {
        System.out.println("Please enter Cutting Type:");
        additionalType = "Cut";
        additionalData1 = sc.nextLine();
        System.out.println("Please enter Machine Type:");
        additionalData2 = sc.nextLine();
        queryTemplateAdditional = QUERY_TEMPLATE_3_3;
    }

    // Connect to the database and execute queries
    try (Connection connection = DriverManager.getConnection(URL)) {
        try (PreparedStatement statement1 = connection.prepareStatement(QUERY_TEMPLATE_3)) {
            statement1.setInt(1, processId);
            statement1.setString(2, processData);
            int rows_inserted = statement1.executeUpdate();
            System.out.println(String.format("Done. %d rows inserted in \"%s\" table.", rows_inserted, additionalType));
        }

        try (PreparedStatement statement2 = connection.prepareStatement(queryTemplateAdditional)) {
            statement2.setInt(1, processId);
            statement2.setString(2, additionalData1);
            if (!additionalData2.isEmpty()) {
                statement2.setString(3, additionalData2);
            }
            int rows_inserted = statement2.executeUpdate();
            System.out.println(String.format("Done. %d rows inserted in \"%s\" process.", rows_inserted, additionalType));
        }

        try (PreparedStatement statement4 = connection.prepareStatement(QUERY_TEMPLATE_3_4)) {
            statement4.setInt(1, processId);
            statement4.setInt(2, deptnum);
            statement4.setString(3, deptData);
            int rows_inserted = statement4.executeUpdate();
            System.out.println(String.format("Done. %d rows inserted in supervise table.", rows_inserted));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    break;

case "4":
    System.out.println("Enter a new Assembly ID:");
    final int assemblyId = sc.nextInt();
    sc.nextLine();

    System.out.println("Please enter the Date of ordered of assembly (yyyy-MM-dd):");
    String dateStr = sc.nextLine();
    java.sql.Date dateOfOrdered = java.sql.Date.valueOf(dateStr); // Convert the date string into a java.sql.Date object

    System.out.println("Please enter the Assembly details:");
    final String assemblyDetails = sc.nextLine();

    System.out.println("Please enter the associated Customer Name:");
    final String assemblyCustomerName = sc.nextLine();

    System.out.println("Please enter the number of associated process IDs with this assembly");
    final int numAssemblies = sc.nextInt();
    sc.nextLine();

    int[] assemblyRep = new int[numAssemblies];
    for (int j = 0; j < numAssemblies; j++) {
        System.out.printf("Enter the %dth process ID:\n", j + 1);
        assemblyRep[j] = sc.nextInt();
        sc.nextLine();
    }

    try (final Connection connection = DriverManager.getConnection(URL)) {
        connection.setAutoCommit(false);

        try (final PreparedStatement statement4 = connection.prepareStatement(QUERY_TEMPLATE_4_1)) {
            statement4.setInt(1, assemblyId);
            statement4.setDate(2, dateOfOrdered);
            statement4.setString(3, assemblyDetails);

            System.out.println("Dispatching the query 4(1)...");
            final int rowsInsertedAssembly = statement4.executeUpdate();
            System.out.printf("Done. %d rows inserted in Assembly table.\n", rowsInsertedAssembly);
        }

        try (final PreparedStatement statement5 = connection.prepareStatement(QUERY_TEMPLATE_4_2)) {
            statement5.setInt(1, assemblyId);
            statement5.setString(2, assemblyCustomerName);

            System.out.println("Dispatching the query 4(2)...");
            final int rowsInsertedOrder1 = statement5.executeUpdate();
            System.out.printf("Done. %d rows inserted in order1 table.\n", rowsInsertedOrder1);
        }

        try (final PreparedStatement statement6 = connection.prepareStatement(QUERY_TEMPLATE_4_3)) {
            for (int assemblyIndex = 0; assemblyIndex < numAssemblies; assemblyIndex++) {
                statement6.setInt(1, assemblyId);
                statement6.setInt(2, assemblyRep[assemblyIndex]);

                System.out.println("Dispatching the query 4(3)...");
                final int rowsInsertedPassthrough = statement6.executeUpdate();
                System.out.printf("Done. %d rows inserted in passthrough table.\n", rowsInsertedPassthrough);
            }
        }

        connection.commit();
    } catch (SQLException e) {
        System.err.println("SQL Exception occurred: " + e.getMessage());
        // Optional: handle rollback logic here if needed
    }
    break;
case "5":
    System.out.println("Enter a new Account number:");
    final int accountNumber = sc.nextInt();
    sc.nextLine();

    System.out.println("Enter the date of account commencement (yyyy-MM-dd):");
    String dateString = sc.nextLine();
    java.sql.Date accountDate = java.sql.Date.valueOf(dateString);

    System.out.println("Enter the account type (1, 2, 3): 1. Department Account, 2. Assembly Account, 3. Process Account:");
    final int accountType = sc.nextInt();
    sc.nextLine();

    int relatedId = 0;
    String queryTemplateForAccount = QUERY_TEMPLATE_5_1;
    String queryTemplateForRelated = "";

    switch (accountType) {
        case 1:
            System.out.println("Enter the department number associated with the account:");
            relatedId = sc.nextInt();
            queryTemplateForRelated = QUERY_TEMPLATE_5_5;
            break;
        case 2:
            System.out.println("Please enter the associated Assembly ID:");
            relatedId = sc.nextInt();
            queryTemplateForRelated = QUERY_TEMPLATE_5_6;
            break;
        case 3:
            System.out.println("Enter the associated Process ID:");
            relatedId = sc.nextInt();
            queryTemplateForRelated = QUERY_TEMPLATE_5_7;
            break;
    }
    sc.nextLine();

    System.out.println("Connecting to the database...");
    try (final Connection connection = DriverManager.getConnection(URL);
         final PreparedStatement statement1 = connection.prepareStatement(queryTemplateForAccount);
         final PreparedStatement statement2 = connection.prepareStatement(QUERY_TEMPLATE_5_2);
         final PreparedStatement statement3 = connection.prepareStatement(queryTemplateForRelated)) {

        statement1.setInt(1, accountNumber);
        statement1.setDate(2, accountDate);
        int rowsInserted = statement1.executeUpdate();
        System.out.println(String.format("Done. %d rows inserted in account table.", rowsInserted));

        statement2.setInt(1, accountNumber);
        statement2.setFloat(2, 0); // Placeholder value
        rowsInserted = statement2.executeUpdate();
        System.out.println(String.format("Done. %d rows inserted in related account table.", rowsInserted));

        statement3.setInt(1, accountNumber);
        statement3.setInt(2, relatedId);
        rowsInserted = statement3.executeUpdate();
        System.out.println(String.format("Done. %d rows inserted in record related cost table.", rowsInserted));
    }
    break;

case "6":
    System.out.println("Please enter new Job Number:");
    final int jobNum = sc.nextInt();
    sc.nextLine(); // to consume the newline character left by nextInt()

    System.out.println("Please enter the date of job commenced (yyyy-MM-dd):");
    String dateJobCommencedStr = sc.nextLine();
    // Convert the date string into a java.sql.Date object
    java.sql.Date dateJobCommenced = java.sql.Date.valueOf(dateJobCommencedStr);

    System.out.println("Please enter the Process ID for this job:");
    final int processID = sc.nextInt();
    sc.nextLine();

    System.out.println("Enter the Assembly ID of the process for this job:");
    final int assemblyID = sc.nextInt();
    sc.nextLine();

    try (final Connection connection = DriverManager.getConnection(URL)) {
        try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_6_1)) {
            statement.setInt(1, jobNum);
            statement.setDate(2, dateJobCommenced); // Set the date of job commenced
            statement.setNull(3, java.sql.Types.DATE); 
            statement.setNull(4, java.sql.Types.INTEGER); // Assuming the correct type for job type
            statement.setNull(5, java.sql.Types.INTEGER); // Assuming the correct type for labor time

            System.out.println("Dispatching the update query...");
            int rowsUpdated = statement.executeUpdate();
            System.out.println(String.format("Done. %d rows updated in Job table.", rowsUpdated));
        }

        try (final PreparedStatement statement2 = connection.prepareStatement(QUERY_TEMPLATE_6_2)) {
            statement2.setInt(1, assemblyID);
            statement2.setInt(2, processID);
            statement2.setInt(3, jobNum);

            System.out.println("Dispatching the query 6(2)...");
            int rowsInserted = statement2.executeUpdate();
            System.out.println(String.format("Done. %d rows inserted in assign table.", rowsInserted));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    break;



case "7":
    System.out.println("Please enter the Job Number which is in case 6:");
    final int jobnum = sc.nextInt();
    sc.nextLine();

    System.out.println("Please enter the date the job completed (yyyy-MM-dd):");
    String dateJobCompletedStr = sc.nextLine();
    java.sql.Date dateJobCompleted = java.sql.Date.valueOf(dateJobCompletedStr);

    System.out.println("Please enter type of Job(1,2,3): 1.Cut Job, 2. Paint Job , 3.Fit Job");
    final int type = sc.nextInt();
    sc.nextLine();

    // Common code for all types
    System.out.println("Please enter the labor time in hours:");
    final int laborTime1 = sc.nextInt();
    sc.nextLine();
    System.out.println("Connecting to the database...");

    try (final Connection connection = DriverManager.getConnection(URL)) {
        try (final PreparedStatement statement1 = connection.prepareStatement(QUERY_TEMPLATE_7_1)) {
            statement1.setDate(1, dateJobCompleted);
            statement1.setInt(2, type);
            statement1.setInt(3, laborTime1);
            statement1.setInt(4, jobnum);
            System.out.println("Dispatching the query 7(1)...");
            final int rows_inserted = statement1.executeUpdate();
            System.out.println(String.format("Done. %d rows inserted in Job table..", rows_inserted));
        }

        if (type == 1) {
            System.out.println("Please enter the type of Machine:");
            final String machinetype = sc.nextLine();
            System.out.println("Enter the amount of time in hours:");
            final int time = sc.nextInt();
            sc.nextLine();
            System.out.println("Please enter the material:");
            final String material = sc.nextLine();

            try (final PreparedStatement statement3 = connection.prepareStatement(QUERY_TEMPLATE_7_2)) {
                statement3.setInt(1, jobnum);
                statement3.setInt(2, laborTime1);
                statement3.setString(3, material);
                statement3.setInt(4, time);
                statement3.setString(5, machinetype);
                System.out.println("Dispatching the query 6(3)...");
                final int rows_inserted = statement3.executeUpdate();
                System.out.println(String.format("Done. %d rows inserted in cutjob table..", rows_inserted));
            }
        } else if (type == 2) {
            System.out.println("Please enter the color:");
            final String color = sc.nextLine();
            System.out.println("Please enter the volume of paint:");
            final int volume = sc.nextInt();
            sc.nextLine();

            try (final PreparedStatement statement3 = connection.prepareStatement(QUERY_TEMPLATE_7_3)) {
                statement3.setString(3, color);
                statement3.setInt(4, volume);
                statement3.setInt(1, jobnum);
                statement3.setInt(2, laborTime1);
                System.out.println("Dispatching the query 7(3)...");
                final int rows_inserted = statement3.executeUpdate();
                System.out.println(String.format("Done. %d rows inserted in paintjob table..", rows_inserted));
            }
        } else if (type == 3) {
            try (final PreparedStatement statement3 = connection.prepareStatement(QUERY_TEMPLATE_7_4)) {
                statement3.setInt(1, jobnum);
                statement3.setInt(2, laborTime1);
                System.out.println("Dispatching the query 7(4)...");
                final int rows_inserted = statement3.executeUpdate();
                System.out.println(String.format("Done. %d rows inserted in fitjob table..", rows_inserted));
            }
        }
    }
    break;

case "11":
System.out.println("Please enter the assembly ID which has passed so far :");
final int aID = sc.nextInt();
sc.nextLine();

try (final Connection connection = DriverManager.getConnection(URL)) {
try (
final PreparedStatement statement1 = connection.prepareStatement(QUERY_TEMPLATE_11)) {
statement1.setInt(1, aID);
System.out.println("Dispatching the query 11...");
ResultSet rs1 = statement1.executeQuery();
System.out.println("Process ID  Department Number");
while(rs1.next()) {
	System.out.println(rs1.getInt(1) + "\t" + rs1.getInt(2)); 
}
}
}
break;

case "10":
    System.out.println("Please enter the Department number that you want find the labor cost:");
    final int department = sc.nextInt();
    sc.nextLine();

    System.out.println("Please enter the date the job completed (yyyy-MM-dd):");
    String comdateStr = sc.nextLine();
    java.sql.Date comdate = java.sql.Date.valueOf(comdateStr);
    sc.nextLine();

    // Determining the Processes supervised by the given Department:
    try (final Connection connection = DriverManager.getConnection(URL)) {
        try (final PreparedStatement statement1 = connection.prepareStatement(QUERY_TEMPLATE_10_1)) {
            // Populate the query template with the data collected from the user
            statement1.setInt(1, department);
            System.out.println("Dispatching the query 1...");
            ResultSet rs = statement1.executeQuery();

            int psize = 0;
            int jsize = 0;
            int[] iarr = new int[77];
            int[] jjj = new int[77];
            int pindex = 0;
            int jindex = 0;
            while (rs.next()) {
                int prcid = rs.getInt("processId");
                iarr[pindex] = prcid;
                pindex++;
                psize++;
            }

            for (int i = 0; i < psize; i++) {
                System.out.println(iarr[i]);
                try (final PreparedStatement statement2 = connection.prepareStatement(QUERY_TEMPLATE_10_2)) {
                    statement2.setInt(1, iarr[i]);
                    System.out.println("Dispatching the query 10(2)...");
                    ResultSet rs1 = statement2.executeQuery();
                    int jobN = 0;
                    while (rs1.next()) {
                        int jobno = rs1.getInt("jobNumber");
                        jjj[jindex] = jobno;
                        jindex++;
                    }
                }
            }

            java.sql.Date[] rrr = new java.sql.Date[jindex];
            int[] trtr = new int[jindex];
            for (int k = 0; k < jindex; k++) {
                System.out.println(jjj[k]);
                try (final PreparedStatement statement4 = connection.prepareStatement(QUERY_TEMPLATE_10_3)) {
                    statement4.setInt(1, jjj[k]);
                    System.out.println("Dispatching the query 10(c)...");
                    ResultSet rs2 = statement4.executeQuery();

                    while (rs2.next()) {
                        trtr[k] = rs2.getInt("labortime");
                        rrr[k] = rs2.getDate("dateofjobcompleted"); // Retrieve the date as java.sql.Date
                    }
                }
            }

            int totalLaborTime = 0;
            for (int l = 0; l < jindex; l++) {
                // Directly compare the two Date objects
                if (rrr[l] != null && rrr[l].equals(comdate)) {
                    totalLaborTime += trtr[l];
                }
            }

            System.out.println("The total labor time for " + department + " department for jobs completed during the " + 
                comdate +  " date is: " + totalLaborTime + " hours.");
        }
    }
    break;

case "12":
System.out.println("enter min for category:");
final int minimum = sc.nextInt();
sc.nextLine();
System.out.println("enter max for category::");
final int maximum = sc.nextInt();
sc.nextLine();
System.out.println("Connecting to the database...");
// Get the database connection, create statement and execute it right away, as no user input need be collected
try (final Connection connection = DriverManager.getConnection(URL)) {
System.out.println("Dispatching the query...");
try (
final PreparedStatement statement2 = connection.prepareStatement(QUERY_TEMPLATE_12)) {

statement2.setInt(1, minimum);
statement2.setInt(2, maximum);
System.out.println("Dispatching the query 13...");
ResultSet rs1 = statement2.executeQuery();
System.out.println("Contents of the customer table:");
System.out.println("name | address | Category");

// Unpack the tuples returned by the database and print them out to the user
while (rs1.next()) {
    
        String name = rs1.getString("cname");
        String address = rs1.getString("caddress");
        int cat = rs1.getInt("category");
        System.out.println(name + "\t" + address + "\t\t" + cat);
        //resultSet.getString(2),
        //resultSet.getString(3)
        
}
}

}
break;
case "13":
System.out.println("minimum for job number:");
final int minijobnum = sc.nextInt();
sc.nextLine();
System.out.println("maximum for job number:");
final int maxjobnum = sc.nextInt();
sc.nextLine();
System.out.println("Connecting to the database...");
// Get the database connection, create statement and execute it right away, as no user input need be collected
try (final Connection connection = DriverManager.getConnection(URL)) {
try (
final PreparedStatement statement1 = connection.prepareStatement(QUERY_TEMPLATE_13_1)) {
// Populate the query template with the data collected from the user

statement1.setInt(1, minijobnum);
statement1.setInt(2, maxjobnum);
System.out.println("Dispatching the query 13(1)...");
// Actually execute the populated query
final int rows_inserted = statement1.executeUpdate();
System.out.println(String.format("Done. %d rows deleted in cutjob table..", rows_inserted));
}

try (
final PreparedStatement statement2 = connection.prepareStatement(QUERY_TEMPLATE_13_2)) {
// Populate the query template with the data collected from the user

statement2.setInt(1, minijobnum);
statement2.setInt(2, maxjobnum);
System.out.println("Dispatching the query 13(2)...");
// Actually execute the populated query
final int rows_inserted = statement2.executeUpdate();
System.out.println(String.format("Done. %d rows deleted in assign table..", rows_inserted));
}
try (
final PreparedStatement statement3 = connection.prepareStatement(QUERY_TEMPLATE_13_3)) {
// Populate the query template with the data collected from the user

statement3.setInt(1, minijobnum);
statement3.setInt(2, maxjobnum);
System.out.println("Dispatching the query 13(3)...");
// Actually execute the populated query
final int rows_inserted = statement3.executeUpdate();
System.out.println(String.format("Done. %d rows deleted in job table..", rows_inserted));
}
}
break;
case "14":
System.out.println("enter job number that you want change color:");
final int jobnumm = sc.nextInt();
sc.nextLine();
System.out.println("enter new color:");
final String color = sc.nextLine();
System.out.println("Connecting to the database...");
// Get the database connection, create statement and execute it right away, as no user input need be collected
try (final Connection connection = DriverManager.getConnection(URL)) {
try (
final PreparedStatement statement1 = connection.prepareStatement(QUERY_TEMPLATE_14)) {
// Populate the query template with the data collected from the user                            
statement1.setString(1, color);
statement1.setInt(2, jobnumm);
System.out.println("Dispatching the query 14...");
// Actually execute the populated query
final int rows_inserted = statement1.executeUpdate();
System.out.println(String.format("Done. %d rows updated in painjob table..", rows_inserted));
}
}
break;
case "15":
System.out.println("Where is path of file you want to enter?:");
sc.nextLine();
final String file = sc.nextLine();
BufferedReader br; 
String line = "";  
String split = ",";
try {
br = new BufferedReader(new FileReader(file));
try (final Connection connection = DriverManager.getConnection(URL)) {
while ((line = br.readLine()) != null)   
{  
String[] customer = line.split(split);      

try (
    final PreparedStatement statement1 = connection.prepareStatement(QUERY_TEMPLATE_1)) {
    // Populate the query template with the data collected from the user                                	
    statement1.setString(1, customer[0]);
    statement1.setString(2, customer[1]);
    statement1.setInt(3, Integer.parseInt(customer[2]));
    
    System.out.println("Dispatching the query 1...");
    
    final int rows_inserted = statement1.executeUpdate();
    System.out.println(String.format("Done. %d rows inserted in customer table..", rows_inserted));
    
}
}

br.close();
}
} catch (FileNotFoundException e)
{

	System.out.println("The specified file was not found: " + e.getMessage());
} catch (IOException e) {
	System.out.println("IO error: " + e.getMessage());
e.printStackTrace();
break;
}
break;
case "16":
System.out.println("enter min for category:");
final int mincategory = sc.nextInt();
sc.nextLine();
System.out.println("enter max for category:");
final int maxcategory = sc.nextInt();
sc.nextLine();
System.out.println("enter output file name");
final String fileName = sc.nextLine();
System.out.println("Connecting to the database...");               
try (final Connection connection = DriverManager.getConnection(URL)) {
System.out.println("Dispatching the query...");
try (
final PreparedStatement statement2 = connection.prepareStatement(QUERY_TEMPLATE_12)) {

statement2.setInt(1, mincategory);
statement2.setInt(2, maxcategory);
System.out.println("Dispatching the query 12...");
ResultSet tre = statement2.executeQuery();

try (PrintWriter rrr = new PrintWriter(fileName + ".csv")) {
    while (tre.next()) {
        StringBuilder sub = new StringBuilder();
        String name = tre.getString("cname");
        sub.append('\"').append(name).append('\"').append(',');
        String address = tre.getString("caddress");
        sub.append('\"').append(address).append('\"').append(',');
        int cat = tre.getInt("category");
        sub.append(cat);
        sub.append('\n');
        rrr.write(sub.toString());
    }
}
catch (FileNotFoundException e) 

{
      System.out.println(e.getMessage());
}
}

}
break;
case "17": 
System.out.println("Exiting! bye!");
System.exit(0);
default: 
System.out.println(String.format(
"Unrecognized option: %s\n" + 
"Please try again!", 
option));
break;

}
}
sc.close(); 
}
}


