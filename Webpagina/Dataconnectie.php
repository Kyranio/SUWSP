<?php
// Database connectie met 51.144.163.82
$dbhost = "localhost"; 
$dbuser = "Tim"; 
$dbpass = "TimsWachtwoord1!";
$dbname = "agrididon"; 
$db = mysqli_connect($dbhost, $dbuser, $dbpass, $dbname); 

// Test of de verbinding werkt! 
if (mysqli_connect_errno()) {
        die("Database connection failed: " .	
        mysqli_connect_error() . " (" .
        mysqli_connect_errno() . ")" );
} 
?>