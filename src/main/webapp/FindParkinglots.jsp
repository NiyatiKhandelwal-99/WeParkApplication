<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a User</title>
</head>
<body>

	
	<form action="findparkinglots" method="post">
		<h1>Find Parking Lot</h1>
		<p>
			<label for="zip">Zip Code</label>
			<input id="zip" name="zip" value="${fn:escapeXml(param.zip)}">
		</p>
		<p>
			<label for="sortBy">Sort By</label>
			<select id="sort" name="sort">
			<option value = "price">Price</option>
			<option value = "remain_space">Availability</option>
			</select>
			
		</p>
		
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>

	<h1>Parking lots near you</h1>

        <table border="1">
            <tr>
                <th>Name</th>
                <th>Address</th>
                <th>City</th>
                <th>State</th>
                <th>Zip Code</th>
                <th>Is Open</th>
                <th>Price</th>
                <th>Remaining Space</th>
                <th>Book</th>

            </tr>
           
            <c:forEach items="${parkingLots}" var="parkingLots" >
                <tr>
                    <td><c:out value="${parkingLots.getName()}" /></td>
                    <td><c:out value="${parkingLots.getAddress()}" /></td>
                    <td><c:out value="${parkingLots.getCity()}" /></td>
                    <td><c:out value="${parkingLots.getState()}" /></td>
                    <td><c:out value="${parkingLots.getZipcode()}" /></td>
                    <td><c:out value="${parkingLots.isIs_open()}" /></td>
                    <td><c:out value="${parkingLots.getPrice()}" /></td>
                    <td><c:out value="${parkingLots.getRemian_space()}" /></td>
                    <td><form action="Bookings?parkingLotId=<c:out value="${parkingLots.getParkinglotid()}"/>" method="post">
    					<input type="submit" value="Book">
    				
  					</form></td>

                </tr>
            </c:forEach>
       </table>
</body>
</html>
