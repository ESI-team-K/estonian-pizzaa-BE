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
    <th>Address</th>
    <th>Contacts</th>
  </tr>
  <#list publishers as publisher>
      <tr>
        <td>${publisher.id}</td>
        <td>${publisher.name}</td>
        <td>${publisher.publisher_address}</td>
        <td>${publisher.publisher_contact}</td>
      </tr>
  </#list>
</table>
</body>
</html>