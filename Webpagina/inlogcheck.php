<html> 
<head> 
</head>
<body>
<link rel="stylesheet" type="text/css" href="inlogschermstyle.css">
<?php
//waarde van het inlog forum meegeven aan variabelen.
$email = $_POST["email"];
$wachtwoordinlog =md5($_POST["wachtwoordinlog"]);

//connectie met de database
include 'Dataconnectie.php';

//de query voor het ophalen van de tabel van wachtwoorden
$query = "
SELECT * 
FROM users
WHERE email = '$email'";

$result = mysqli_query($db, $query);

//wachtwoord ophalen uit de database
while($row = mysqli_fetch_assoc($result)){
	//check of het wachtwoord uit de database die verbonden is aan het opgegeven emailadres overeen komt met het opgegeven wachtwoord
	if($wachtwoordinlog == $row["password"]){
		include "inlogsucces.php";
		session_start();
	}
	else{
	include "inlogfail.php";
	}
	$_SESSION['staff_number'] = $row['staff_number'];
	$_SESSION['role'] = $row['role'];
}
?>
</body>
</html>