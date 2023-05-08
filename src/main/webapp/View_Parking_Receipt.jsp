<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Booking History</title>
</head>
<body>
  <h1>Booking History</h1>

  <table border="1">
    <thead>
      <tr>
        <th>Receipt Id</th>
        <th>Booking Id</th>
        <th>Vehicle Id</th>
        <th>Booking Date</th>
        <th>Parking Slot Id</th>
        <th>Actual Entry Time</th>
        <th>Actual Exit Time</th>
        <th>Basic Cost</th>
        <th>Penalty</th>
        <th>Is Paid</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${receipts.entrySet()}" var="receipt">
        <tr>
          <td><c:out value="${receipt.key.getReceiptid()}" /></td>
          <td><c:out value="${receipt.key.getBooking_id()}" /></td>
          <td><c:out value="${receipt.value.getVehicleid()}" /></td>
          <td><c:out value="${receipt.value.getBooking_date()}" /></td>
          <td><c:out value="${receipt.value.getParking_slot_id()}" /></td>
          <td><c:out value="${receipt.key.getActual_entry_time()}" /></td>
          <td><c:out value="${receipt.key.getActual_exit_time()}" /></td>
          <td><c:out value="${receipt.key.getBasic_cost()}" /></td>
          <td><c:out value="${receipt.key.getPenalty()}" /></td>
          <td><c:out value="${receipt.key.getTotal_cost()}" /></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

  <!-- Add a back button to go back to Homepage.jsp -->
  <form action="Homepage.jsp" method="post">
    <input type="submit" value="Back to Homepage">
  </form>
</body>
</html>