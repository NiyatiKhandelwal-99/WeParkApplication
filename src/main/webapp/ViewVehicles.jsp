<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>View Vehicles</title>
</head>
<body>
  <h1>View Vehicles</h1>

  <table border="1">
    <thead>
      <tr>
        <th>Brand</th>
        <th>Year</th>
        <th>Plate Number</th>
        <th>Remove</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${vehicles}" var="vehicle">
        <tr>
          <td><c:out value="${vehicle.getBrand()}" /></td>
          <td><c:out value="${vehicle.getYear()}" /></td>
          <td><c:out value="${vehicle.getPlatenumber()}" /></td>
           <td><form action="deleteVehicle?vehicleId=<c:out value="${vehicle.getVehicleid()}"/>" method="post">
    					<input type="submit" value="Remove">
  				</form></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
	
  <form action="AddVehicle.jsp" method="post">
    <input type="submit" value="Add New Vehicle">
  </form>
  
  <!-- Add a back button to go back to Homepage.jsp -->
  <form action="Homepage.jsp" method="post">
    <input type="submit" value="Back to Homepage">
  </form>
</body>
</html>
