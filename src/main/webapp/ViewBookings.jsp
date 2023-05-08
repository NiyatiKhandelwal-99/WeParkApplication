<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Your Active Bookings</title>
</head>
<body>
  <h1>Your Active Bookings</h1>

  <table border="1">
    <thead>
      <tr>
        <th>Booking Id</th>
        <th>Vehicle Id</th>
        <th>Start Time</th>
        <th>Duration (in min)</th>
        <th>Booking Date</th>
        <th>Parking Slot Id</th>
        <th>Edit</th>
        <th>Delete</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${bookings}" var="booking">
        <tr>
          <td><c:out value="${booking.getBooking_id()}" /></td>
          <td><c:out value="${booking.getVehicleid()}" /></td>
          <td><c:out value="${booking.getStart_time()}" /></td>
          <td><c:out value="${booking.getDuration_in_min()}" /></td>
          <td><c:out value="${booking.getBooking_date()}" /></td>
          <td><c:out value="${booking.getParking_slot_id()}" /></td>
          <td><form action="updateBookingPage?bookingId=<c:out value="${booking.getBooking_id()}"/>" method="post">
    					<input type="submit" value="Edit">
  					</form></td>
  		  <td><form action="deleteBooking?parkingSlotId=<c:out value="${booking.getParking_slot_id()}"/>&bookingId=<c:out value="${booking.getBooking_id()}"/>" method="post">
    					<input type="submit" value="Delete">
  				</form></td>
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