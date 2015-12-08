<html>




<head><title>Customer Interface</title>
</head>
<body>


<#if username == "admin">
<head><title>Admin Interface</title>
</head>
<body>

<h1>Welcome to RentARide Admin Management System</h1>

<h3>You are logged in as ${username}

<p>You may:
<ol>
<li>
List all <a href="FindAllRentalLocations"> rental locations</a>
</li>
<li>
Create <a href="CreateRentalLocation.html">
a new rental location record</a>
</li>
<li>
Create <a href="CreateVehicle.html"> 
a new vehicle record </a>
</li>
<li>
Find all <a href="FindAllVehicleTypes"> vehicle types </a>
</li>
<li>
Create <a href="CreateVehicleType.html"> vehicle type</a>
</li>
<li>
Find all <a href="FindAllVehicles"> vehicles</a>
</li>
<li>
View <a href="ViewAllCustomers"> all customers</a>
</li>

<li>
<a href="Logout"> Logout</a> from the RentARide system.
</li>
</ol> 
</body>

<#else>

<body>

<h1>Welcome to RentARide Customer Management System</h1>

<h3>You are logged in as ${username}
<p>You may:
<ol>
<li>
List all <a href="FindAllRentalLocations"> rental locations</a>
</li>
<li>
Create <a href="CreateReservationRLVT"> 
new reservation </a>
</li>
<li>
View <a href="ViewCustomerInfo"> customer information</a>
</li>
<li>
<a href="Logout"> Logout</a> from the RentARide system.
</li>
</ol> 
</body>

</#if>
</html>
