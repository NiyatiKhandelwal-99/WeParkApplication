<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add New CreditCard</title>
</head>
<body>
    <h1>Add New CreditCard</h1>
    <form action="addcreditcard" method="post">
        <label for="cardnumber">Card Number:</label>
        <input type="text" name="cardnumber" id="cardnumber" required>
        <br>
        <label for="exiprationdate">Exipration Date:</label>
        <input type="text" name="exiprationdate" id="exiprationdate" required>
        <br>
        <input type="submit" value="Add">
    </form>
    <br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>
