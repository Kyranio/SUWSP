<?php 
session_start();
?>
<html>
<title>Loggin out</title>
<link href="uitlog.css" rel="stylesheet" type="text/css">
<body>
<?php
session_unset();

session_destroy();

?>
<h1>You have successfully logged out</h1>
<form>
<input type="submit" formaction="index.php" ID="button" value="Back to loginpage">
</form>
<?php
include 'footer.php';
?>
</body>
</html>