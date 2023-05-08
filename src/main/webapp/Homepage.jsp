<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>HomePage</title>
</head>
<body>
  <h1>Home Page</h1>
  
  <%-- Retrieve the username from the session --%>
  <% String username = (String) session.getAttribute("username"); %>
  <p>Welcome, <%=username%>!</p>
  
  <form action="myaccount" method="post">
    <input type="submit" value="My Account">
  </form>
  <form action="creditcards" method="post">
    <input type="submit" value="View Payment Method">
  </form>
  <form action="viewvehicle" method="post">
    <input type="submit" value="View Vechiles">
  </form>
  <form action="findparkinglots" method="post">
    <input type="submit" value="Find Parking">
  </form>
  <form action="bookingList" method="post">
    <input type="submit" value="Your Bookings">
  </form>
  <form action="parkingreceipt" method="post">
    <input type="submit" value="View Parking Receipts">
  </form>
   <form action="regularpass" method="post">
    <input type="submit" value="Buy Regular Pass">
  </form>
  <br>
</body>
</html>