<?php
// Database connectie met localhost
$dbhost = "localhost"; 
$dbuser = "root"; 
$dbpass = "";
$dbname = ""; 
$db = mysqli_connect($dbhost, $dbuser, $dbpass, $dbname); 

// Test of de verbinding werkt! 
if (mysqli_connect_errno()) {
        die("Database connection failed: " .	
        mysqli_connect_error() . " (" .
        mysqli_connect_errno() . ")" );
} 
?>