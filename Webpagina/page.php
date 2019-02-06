
<!DOCTYPE html>
<html>
<head>
    <title>Homepage</title>
    <link rel="stylesheet" type="text/css" href="inlogschermstyle.css">
    <img src="logo-agrididon.png" alt="logo" style="width: 10%;">
    <div ID="Tableinlog"><H2>Agri Didon weather monitoring system</H2>
        </head>

<body>
</form>
<td><input id="buttoninlog" type="button" Value="Home"onclick="window.location.href='Homepage.php'"></td>
<td><input id="buttoninlog" type="button" value="Temperature and humidity" onclick="window.location.href='FileReader.php'"></td>
<td><input id="buttoninlog" type="button" value="Contact" onclick="window.location.href='contact.php'"></td>
<td><input id="buttoninlog" type="button" value="log out" onclick="window.location.href='uitloggen.php'"></td>
</form>

</form>
</table>
</div>
<?php

if(isset($_POST["login"])){
    include "inlogcheck.php";
}
?>
<?php include "footer.php";?>
<!--</fieldset>-->
</body>
</html>

