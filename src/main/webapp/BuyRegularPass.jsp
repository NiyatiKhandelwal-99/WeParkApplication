<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Regular Pass</title>
</head>
<body>

	<h1>Your current active regular passes</h1>

        <table border="1">
            <tr>
                <th>Pass ID</th>
                <th>Parking Lot</th>
                <th>Purchase Date</th>
                <th>Start Date</th>
                <th>Duration (in Days)</th>
                <th>Cost</th>
                <th>Cancel</th>
            </tr>
           
            <c:forEach items="${regularPasses}" var="regularPass" >
                <tr>
                    <td><c:out value="${regularPass.getPassid()}" /></td>
                    <td><c:out value="${regularPass.getParkingLotName()}" /></td>
                    <td><c:out value="${regularPass.getPurchase_date()}" /></td>
                    <td><c:out value="${regularPass.getStart_date()}" /></td>
                    <td><c:out value="${regularPass.getDuration_in_days()}" /></td>
                    <td><c:out value="${regularPass.getCost()}" /></td>
                    <td><form action="cancelregularpass?passid=<c:out value="${regularPass.getPassid()}"/>" method="post">
    					<input type="submit" value="Cancel">    				
  					</form></td>
  					

                </tr>
            </c:forEach>
       </table>

	
	<form action=findparkinglotsforregularpass method="post">
		<h1>Buy Regular Pass</h1>
		<p>
			<label for="zip">Zip Code</label>
			<input id="zip" name="zip" value="${fn:escapeXml(param.zip)}">
		</p>

		<p>
			<input type="submit" value ="Find Parking Lots">
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
                <th>Price (Per Day)</th>
                <th>Remaining Space</th>
                <th>Buy</th>

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
                    <td><form action="regularpassbuybutton?parkingLotId=<c:out value="${parkingLots.getParkinglotid()}"/>&price=<c:out value="${parkingLots.getPrice()}"/>" method="post">
    					<input type="submit" value="Buy">
    				
  					</form></td>

                </tr>
            </c:forEach>
       </table>
</body>
</html>
