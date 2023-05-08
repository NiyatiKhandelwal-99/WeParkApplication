<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="wePark.model.Vehicle" %>
<%@ page import="wePark.model.Booking" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Update Booking</title>
</head>
<body>
  <h1>Update Booking</h1>
 
  <form action="updateBooking?bookingid=${booking.getBooking_id()}" method="post">
	<label for="vehicleid">Vehicle Number:</label>       
       <select id="vehicleid" name="vehicleid">
        <c:forEach var="vehicle" items="${vehicles}">
          <option value="<c:out value="${vehicle.getVehicleid()}"/>"><c:out value="${vehicle.getPlatenumber()}"/></option>
        </c:forEach>
      </select><br><br>
   
      <label for="start_time">Start Time:</label>
      <input type="datetime-local" id="start_time" name="start_time" value = "${booking.getStart_time().toString()}T08:30"><br><br>
      
      <label for="duration_in_min">Duration (in minutes):</label>
      <input type="number" id="duration_in_min" name="duration_in_min" value = "${booking.getDuration_in_min()}"><br><br>
      
      <input type="submit" value="Submit">
  </form>
  <br>
  <p>
	<span id="successMessage"><b>${messages.success}</b></span>
  </p>
</body>
</html>