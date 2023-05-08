<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>View CreditCards</title>
</head>
<body>
  <h1>View CreditCards</h1>

  <table border="1">
    <thead>
      <tr>
        <th>Card Number</th>
        <th>Expiration Date</th>
        <th>Remove</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${creditcards}" var="card">
        <tr>
          <td><c:out value="${card.getCardNumber()}" /></td>
          <td><c:out value="${card.getExpirationDate()}" /></td>
          <td><form action="deleteCard?cardNumber=<c:out value="${card.getCardNumber()}"/>" method="post">
    					<input type="submit" value="Remove">
  				</form></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
  
  <form action="AddCreditCard.jsp" method="post">
    <input type="submit" value="Add New CreditCard">
  </form>
  
  <!-- Add a back button to go back to Homepage.jsp -->
  <form action="Homepage.jsp" method="post">
    <input type="submit" value="Back to Homepage">
  </form>
</body>
</html>
