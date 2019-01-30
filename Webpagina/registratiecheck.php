<!DOCTYPE html> 
<html> 
<head> 
<title>registratie check</title> 
</head>
<link rel="stylesheet" type="text/css" href="registratiescermstyle.css"> 
<body> 
<?php
include "Dataconnectie.php";
$email = $_POST["email"];
$full_name=ucwords ($_POST["full_name"]);
$password= $_POST['Password'];
$md5password = md5($wachtwoord);
$password2= $_POST['Password2'];
$Error="";

If($_SERVER["REQUEST_METHOD"] == "POST"){
	//checken of de naam alleen lettets, hoofdletters en spaties heeft
	if (!preg_match("/^[a-zA-Z ]*$/",$full_name)){
		$Error .= "Your name has unknown characters <br/>";
	}
	//check of de wachtwoorden overeen komen
	if($wachtwoord==$password2){
	}
	else{
		$Error .= "The passwords entered does not match. .</br>";
	}
}

$emailquery = '
SELECT email 
FROM users 
WHERE email="$email"';

$result = mysqli_query($db, $emailquery);

while($row = mysqli_fetch_assoc($result)){
	if($email == $row["email"]){
		$Error .="This email is already in use on this site";
	}
}

	//als alles goed is ingevuld en de email nog niet in de database staat wordt het toegevoegd aan de database en dan krijgt de klant de optie om naar het homescreen te gaan of in te loggen. 
	if ($Error == ""){
		$query = "INSERT INTO `users` (`staff_number`, `full_name`, `email`, `password`) VALUES ('','$full_name', '$email', '$md5password')";
		mysqli_query($db, $query);
		include "registratiecheckbuttons.php";
	}
	//weergave van alle errors als die er zijn
	else{
		echo "Uw error(s):<br/> $Error";
		include "registratiefail.php";
	}
//}
include "Dataeinde.php";

?>
</body> 
</html> 
