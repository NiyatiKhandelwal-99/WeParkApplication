<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="wePark.model.Vehicle" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Buy Regular Pass</title>
</head>
<body>
  <h1>Buy Regular Pass</h1>
 
  <form action="createregularpass?parkingLotId=${parkingLotId}&price=${price}" method="post">
   
      <label for="start_date">Start Date:</label>
      <input type="date" id="start_date" name="start_date"><br><br>
      
      <label for="duration_in_days">Duration (in days):</label>
      <input type="number" id="duration_in_days" name="duration_in_days"><br><br>
      
      
      <input type="submit" value="Submit">
  </form>
  <br>
  <p>
	<span id="successMessage"><b>${messages.success}</b></span>
  </p>
</body>
</html>