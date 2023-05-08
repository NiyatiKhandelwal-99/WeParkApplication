<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Vehicle</title>
</head>
<body>
    <h1>Add New Vehicle</h1>
    <form action="addvehicle" method="post">
        <label for="brand">Brand:</label>
        <input type="text" name="brand" id="brand" required>
        <br>
        <label for="year">Year:</label>
        <input type="text" name="year" id="year" required>
        <br>
        <label for=platenumber>Plate Number:</label>
        <input type="text" name="platenumber" id="platenumber" required>
        <br>
        <input type="submit" value="Add">
    </form>
    <br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>
