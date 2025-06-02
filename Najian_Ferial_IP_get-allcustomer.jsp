<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
        <title>Customer Database</title>
    </head>
    <body>
        <%@page import="finalproject.DataHandler"%>
        <%@page import="java.sql.ResultSet"%>
        <%
            // We instantiate the data handler here, and get all the 
movies from the database
            final DataHandler handler = new DataHandler();
            final ResultSet customers = handler.getAllCustomer();
        %>
        <!-- The table for displaying all the customer records -->
        <table cellspacing="2" cellpadding="2" border="1">
            <tr> <!-- The table headers row -->
              <td align="center">
                <h4>cname</h4>
              </td>
              <td align="center">
                <h4>caddress</h4>
              </td>
              <td align="center">
                <h4>category</h4>
              </td>
              
             
            </tr>
            <%
               while(customers.next()) { // For each movie_night record 
returned...
                   // Extract the attribute values for every row returned
                   //final String time = movies.getString("start_time");
                   //final String name = movies.getString("movie_name");
                   //final String duration = 
movies.getString("duration_min");
                   //final String guest1 = movies.getString("guest_1");
                   //final String guest2 = movies.getString("guest_2");
                   //final String guest3 = movies.getString("guest_3");
                   //final String guest4 = movies.getString("guest_4");
                   //final String guest5 = movies.getString("guest_5");
                   final String cname = customers.getString("cname");
                   final String caddress = 
customers.getString("caddress");
                   final String category = 
customers.getString("category");
                  
                   
                   out.println("<tr>"); // Start printing out the new 
table row
                   out.println( // Print each attribute value
                        "<td align=\"center\">" + cname +
                        "</td><td align=\"center\"> " + caddress +
                        "</td><td align=\"center\"> " + category +
                     "</td>");
                   out.println("</tr>");
               }
               %>
          </table>
    </body>
</html>

