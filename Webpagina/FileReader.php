<?php
include "page.php";
?>

<form action="#" method="post">
<select name="Station">
<option value="607100">TABARKA</option>
<option value="607140">BIZERTE</option>
<option value="607150">TUNIS-CARTHAGE</option>
<option value="607200">KELIBIA</option>
<option value="607250">JENDOUBA</option>
<option value="607280">NABEUL</option>
<option value="607340">SILIANA</option>
<option value="607350">KAIROUAN</option>
<option value="607380">THALA</option>
<option value="607400">MONASTIR-SKANES</option>
<option value="607401">HABIB BOURGUIBA</option>
<option value="607403">HABIB BOURGUIBA INT</option>
<option value="607450">GAFSA</option>
<option value="607500">SFAX EL-MAOU</option>
<option value="607600">TOZEUR</option>
<option value="607650">GABES</option>
<option value="607690">DJERBA MELLITA</option>
<option value="607700">MEDENINE</option>
<option value="607750">REMADA</option>
</select>
<input type="submit" name="submit" value="Get Selected Values" />
</form>
<?php
$data = "google.visualization.arrayToDataTable([['Year', 'Temperature', 'Humidity']"; 
		
if(isset($_POST['submit'])){
$station = $_POST['Station'];  // Storing Selected Value In Variable
	
$files = scandir('data/');
foreach($files as $file) {
	error_reporting(E_ERROR | E_PARSE); //ff gare errors weghalen #stukjeductape
	$file = "data/".$file;
	$myfile = fopen($file,'r');
	$file = fgets($myfile); 

	$arr = explode("_", $file);
	
	foreach ($arr as &$value) {
		$ArrayStation = explode("#", $value);
		if($ArrayStation[0] == $station){
			$temperatuur = $ArrayStation[3];
			$time = $ArrayStation[2]; 
			$humidity = $ArrayStation[4]; 
			$time = "'".substr($time,0,2).":".substr($time,2,2)."'"; 
			$data = $data.",[".$time.", ".$temperatuur.",".$humidity."]"; 
		} 
	} 
}
$data = $data."])";
}
?>
   <html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = <?php echo $data ?>;

        var options = {
          title: 'Temperature & Humidity',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
    </script>
  </head>
  <body>
    <div id="curve_chart" style="width: 900px; height: 500px"></div>
  </body>
</html>