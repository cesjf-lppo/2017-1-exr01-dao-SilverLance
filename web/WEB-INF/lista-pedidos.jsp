<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Pedidos</title>
    </head>
    <body>
        <%@include file="jspf/menu.jspf" %>
        <h1>Lista de Pedidos</h1>
        <div style="color: red;">${mensagem}</div>
        <table>
            <thead>
                <tr>
                <th>ID</th>
                <th>Pedido</th>
                <th>Dono</th>
                <th>Valor</th>
                <th>Nome</th>
                <th>Data e Hora</th>
                </tr>
            </thead>    
            <tbody>
                <c:forEach var="pedidos" items="${pedidos}">    
                <tr>
                    <td><a href="detalhes.html?id=${pedido.id}">${pedido.id}</a></td>
                    <td>${pedido.pedido}</td>
                    <td>${pedido.dono}</td>
                    <td>${pedido.valor}</td>
                    <td>${pedido.nome}</td>
                    <td>${pedido.DataHora}</td>
                </tr>
                </c:forEach>
            </tbody>
    </body>
</html>
