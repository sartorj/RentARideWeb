<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Find Vehicles</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<h1>Currently active rental locations</h1>

<table width="70%"  border="0" cellpadding="1" cellspacing="2" >
  <tr>
    <td><b>Make</b></td>
    <td><b>Model</b></td>
    <td><b>Year</b></td>
    <td><b>RegistrationTag</b></td>
    <td><b>Mileage</b></td>
    <td><b>LastServiced</b></td>
    <td><b>Status</b></td>
    <td><b>Condition</b></td>
    <td><b>VehicleType</b></td>
    <td><b>RentalLocation</b></td>
    
  </tr>
 <#list vehicles as vehicle>
  <tr>
    <td>${vehicle[0]}</td>
    <td>${vehicle[1]}</td>
    <td>${vehicle[2]}</td>
    <td>${vehicle[3]}</td>
    <td>${vehicle[4]}</td>
    <td>${vehicle[5]}</td>
    <td>${vehicle[6]}</td>
    <td>${vehicle[7]}</td>
    <td>${vehicle[8]}</td>
    <td>${vehicle[9]}</td>
    
  </tr>
 </#list></table>
  
<p><p>Back to the <a href="ShowMainWindow"> main window</a>
  
</body>
</html>