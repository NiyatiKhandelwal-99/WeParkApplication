<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Find Password</title>
</head>
<body>
  <h1>Find Password</h1>
  <form action="findpassword" method="post">
    <label for="email">Email Address:</label>
    <input type="text" name="email" id="email" required>
    <br>
    <input type="submit" value="Send">
  </form>
</body>
</html>