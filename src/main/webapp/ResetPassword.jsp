<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Reset Password</title>
</head>
<body>
  <h1>Reset Password</h1>
  <form action="resetpassword" method="post">
    <label for="username">Username:</label>
    <input type="text" name="username" id="username" required>
    <br>
    <label for="password">Enter New Password:</label>
    <input type="password" name="password" id="password" required>
    <br>
    <input type="submit" value="Submit">
  </form>
  <br>
  <p>
	<span id="successMessage"><b>${messages.success}</b></span>
  </p>
</body>
</html>