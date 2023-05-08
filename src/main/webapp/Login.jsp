<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
</head>
<body>
  <h1>Login</h1>
  <p>
  	<span id="successMessage"><b>${messages.fail}</b></span>
  </p>
  <form action="login" method="post">
    <label for="username">Username:</label>
    <input type="text" name="username" id="username" required>
    <br>
    <label for="password">Password:</label>
    <input type="password" name="password" id="password" required>
    <br>
    <input type="submit" value="Login">
    <p><a href="UserCreate.jsp">New user? Create one</a></p>
    <p><a href="FindPassword.jsp">Forgot password?</a></p>
  </form>
</body>
</html>
