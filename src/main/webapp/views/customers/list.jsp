<%@ page import="co.edu.poli.ces3.customers.entities.Customer" %>
<%@ page import="java.util.ArrayList" %>

<%--
  Created by IntelliJ IDEA.
  User: zibor-ndw
  Date: 17/07/2022
  Time: 2:23 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Customer> listCustomers = (ArrayList<Customer> )request.getAttribute("customers");
%>
<html>
<head>
    <title>Lista de clientes</title>
</head>
<body>
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
        for (Customer x: listCustomers){
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
