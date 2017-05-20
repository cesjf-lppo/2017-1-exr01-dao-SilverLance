<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Novo Contato</title>
    </head>
    <body>
        <%@include file="jspf/menu.jspf" %>
        <h1>Detalhes Pedido</h1>
        <div style="color: red;">${mensagem}</div>
        <form method="post">
            <input type="hidden" value="${pedido.id}" name="id"/>
            <div><label> Pedido: <input type="text" name="pedido" value="${pedido.pedido}" /></label> </div>
            <div><label> Dono: <input type="text" name="dono" value="${pedido.dono}" /></label> </div>
            <div><label> Valor: <input type="text" name="valor" value="${pedido.valor}" /></label> </div>
            <div><label> Nome: <input type="text" name="nome" value="${pedido.nome}" /></label> </div>
            <div><label> Data e Hora: <input type="text" name="atualizacao" value="${pedido.atualizacao}" /></label> </div>
            <div><input type="submit" /></div>   
        </form>
    </body>
</html>