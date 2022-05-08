<!DOCTYPE html>
<html>
<head>
  <title>Customers</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<h2>List of Customers</h2>
<table>
  <tr>
    <th>Id</th>
    <th>Name</th>
    <th>Phone</th>
  </tr>
  <#list customers as customer>
      <tr>
        <td>${customer.id}</td>
        <td>${customer.name}</td>
        <td>${customer.phone_number}</td>
      </tr>
  </#list>
</table>
</body>
</html>