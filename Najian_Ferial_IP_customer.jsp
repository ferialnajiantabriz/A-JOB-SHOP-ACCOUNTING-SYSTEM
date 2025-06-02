<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Query Result</title>
</head>
    <body>
    <%@page import="finalproject.DataHandler"%>
    <%@page import="java.sql.ResultSet"%>
    <%@page import="java.sql.Array"%>
    <%
    // The handler is the one in charge of establishing the connection.
    DataHandler handler = new DataHandler();

  
    String cname = request.getParameter("cname");
    String caddress = request.getParameter("caddress");
    String category = request.getParameter("category");
  
    if (cname == null || cname.equals("") || 
    	    caddress == null || caddress.equals("") || 
    	    category == null || category.equals("")) {
    	    
response.sendRedirect("Najiantabriz_Ferial_IP_Task7_add_customer.jsp");
    	} else {
    if (cname.equals("") || caddress.equals("") || category.equals("")) {
        
response.sendRedirect("Najiantabriz_Ferial_IP_Task7_add_customer.jsp");
    } else {
       // int duration = Integer.parseInt(durationString);
        
        // Now perform the query with the data from the form.
        boolean success = handler.addcustomer(cname, caddress, category);
        if (!success) { // Something went wrong
            %>
                <h2>There was a problem inserting the course</h2>
            <%
        } else { // Confirm success to the user
            %>
            <h2>The Customer Table:</h2>

            <ul>
                <li>cname: <%=cname%></li>
                <li>caddress: <%=caddress%></li>
                <li>category: <%=category%></li>
               
            </ul>

            <h2>New Customer successfully inserted.</h2>
            
            <a href="get_all_customer.jsp">See all Customers.</a>
            <%
        }}
    }
    %>
    </body>
</html>

