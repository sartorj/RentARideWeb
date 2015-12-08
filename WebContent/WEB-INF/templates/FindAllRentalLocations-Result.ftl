<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<h1>Currently active rental locations</h1>

<table width="70%"  border="0" cellpadding="1" cellspacing="2" >
  <tr>
    <td><b>Name</b></td>
    <td><b>Address</b></td>
    <td><b>Capacity</b></td>
  </tr>
 <#list rentalLocations as rentalLocation>
  <tr>
    <td>${rentalLocation[0]}</td>
    <td>${rentalLocation[1]}</td>
    <td>${rentalLocation[2]}</td>
  </tr>
 </#list></table>
  
<p><p>Back to the <a href="ShowMainWindow"> main window</a>
  
</body>
</html>
