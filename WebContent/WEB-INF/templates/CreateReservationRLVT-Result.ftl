<html>
<head><title>Create Reservation</title>
</head>
<body>

<h1>Create a Reservation</h1>

<form method=post action="CreateReservation">

<p>Select a Rental Location: 
<select
  name="RL_name" size="1">
  <#list rentalLocations as rentalLocation>
  <option>${rentalLocation}</option>
  </#list>
</select>

<p>Select a Vehicle Type: 
<select
  name="Vehicle_type" size="1">
  <#list vehicleTypes as vehicleType>
  <option>${vehicleType}</option>
  </#list>
</select>

<p>Enter pickup date: <input name="pickup" type=text size="20">

<p>Enter duration: <input name="duration" type=text size="20">

<p><input type=submit> <input type=reset>

</form>

<p><p>Back to the <a href="ShowMainWindow"> main window</a>

</body>
</html>