<%@ page import="co.edu.poli.ces3.customers.entities.Customer" %>
<%@ page import="static co.edu.poli.ces3.customers.servlets.SrvlCustomer.CUSTOMER" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Clientes</title>
</head>
<body>
<h1><%= "Clientes" %>
</h1>
<br/>
<table id="tbl_customers">
    <thead>
    <tr>
        <th>Identificación</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Celular</th>
        <th>Correo</th>
        <th>Ciudad</th>
        <th>Direccion</th>
        <th>Nombre contacto</th>
        <th>Celular Contacto</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Customer x: CUSTOMER){
    %>
    <tr>
        <td><%= x.getDocument() %></td>
        <td><%= x.getName() %></td>
        <td><%= x.getLastName() %></td>
        <td><%= x.getCellPhone() %></td>
        <td><%= x.getEmail() %></td>
        <td><%= x.getMunicipality() %></td>
        <td><%= x.getAddress() %></td>
        <td><%= x.getContactName() %></td>
        <td><%= x.getCellPhoneContact() %></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>