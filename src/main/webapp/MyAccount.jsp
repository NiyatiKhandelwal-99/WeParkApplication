<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="wePark.model.Contact" %>
<!DOCTYPE html>
<html>
<head>
  <title>My Account</title>
</head>
<body>
  <h1>My Account</h1>
  <p>
	<span id="successMessage"><b>${messages.success}</b></span>
  </p>	

  <%-- Retrieve the Contact object from the request attribute --%>
  <% Contact contact = (Contact) request.getAttribute("contact"); %>

  <form action="updatecontact" method="post">
    <table>
      <%-- Add input fields for each contact attribute --%>
      <tr>
        <td>First Name:</td>
        <td><input type="text" name="firstname" value="<%=contact.getFirstname()%>"></td>
      </tr>
      <tr>
        <td>Last Name:</td>
        <td><input type="text" name="lastname" value="<%=contact.getLastname()%>"></td>
      </tr>
      <tr>
        <td>Address 1:</td>
        <td><input type="text" name="address1" value="<%=contact.getAddress1()%>"></td>
      </tr>
      <tr>
        <td>Address 2:</td>
        <td><input type="text" name="address2" value="<%=contact.getAddress2()%>"></td>
      </tr>
      <tr>
        <td>City:</td>
        <td><input type="text" name="city" value="<%=contact.getCity()%>"></td>
      </tr>
      <tr>
        <td>State:</td>
        <td><input type="text" name="state" value="<%=contact.getState()%>"></td>
      </tr>
      <tr>
        <td>Zip Code:</td>
        <td><input type="text" name="zipcode" value="<%=contact.getZipcode()%>"></td>
      </tr>
      <tr>
        <td>Email:</td>
        <td><input type="text" name="email" value="<%=contact.getEmail()%>"></td>
      </tr>
      <tr>
        <td>Phone:</td>
        <td><input type="text" name="phone" value="<%=contact.getPhone()%>"></td>
      </tr>
      <tr>
        <td>Driver License:</td>
        <td><input type="text" name="driver_license" value="<%=contact.getDriver_license()%>"></td>
      </tr>
      <tr>
        <td>Driver License State:</td>
        <td><input type="text" name="driver_license_state" value="<%=contact.getDriver_license_state()%>"></td>
      </tr>
      <tr>
        <td colspan="2" style="text-align: center;"><input type="submit" value="Update"></td>
      </tr>
    </table>
  </form>
  
  <%-- Add the "Back" button --%>
  <form action="Homepage.jsp" method="post">
    <input type="submit" value="Back">
  </form>
</body>
</html>
