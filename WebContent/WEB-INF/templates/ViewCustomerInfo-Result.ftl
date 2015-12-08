<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<h1>Customer Info</h1>



<table width="70%"  border="0" cellpadding="1" cellspacing="2" >
  <tr>
    <td><b>First Name</b></td>
    <td><b>Last Name</b></td>
    <td><b>User Name</b></td>
    <td><b>Email Address</b></td>
    <td><b>Password</b></td>
    <td><b>Create Date</b></td>
    <td><b>User Status</b></td>
    <td><b>Residence Address</b></td>
    <td><b>Membership Expiration</b></td>
    <td><b>License State</b></td>
    <td><b>License Number</b></td>
    <td><b>Credit Card Number</b></td>
    
  </tr>
 <#list customers as customer>
  <tr>
    <td>${customer[0]}</td>
    <td>${customer[1]}</td>
    <td>${customer[2]}</td>
    <td>${customer[3]}</td>
    <td>${customer[4]}</td>
    <td>${customer[5]}</td>
    <td>${customer[6]}</td>
    <td>${customer[7]}</td>
    <td>${customer[8]}</td>
    <td>${customer[9]}</td>
    <td>${customer[10]}</td>
    <td>${customer[11]}</td>
   
  </tr>
 </#list></table>
  
<p><p>Back to the <a href="ShowMainWindow"> main window</a>
  
</body>
</html>