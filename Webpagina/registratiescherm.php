<!DOCTYPE html> 
<html> 
<head> 
<title>Register</title> 
</head> 
<body> 
<table>
<form method="POST" action="registratiecheck.php">
<link rel="stylesheet" type="text/css" href="registratieschermstyle.css">
<div ID="Tableregistratie">
<tr><td>
<H2>Registerform</H2>	
</td></tr>
<tr>
<td>Email:</td>
<td><input type="email" name="email" required></td>
</tr>

<tr>
<td>Full Name:</td>
<td><input type="text" name="full_name" required></td>
<tr/>
<tr>
<td>Password:</td>
<td><input type="password" name="Password" required> </td>
<tr/>
<td>Retype Password:</td>
<td><input type="password" name="Password2" required> </td>
<tr/>

<tr><td><input ID="buttonregistratie" type="submit" value="Register"></td></tr>
</form>
<form action="inlogscherm.php">
<br />
<td><input id="Returnbutton" type="submit" Value="Return to login page"></td>
</form>
</div>
<?php include "footer.php"; ?>
</body> 
</html> 