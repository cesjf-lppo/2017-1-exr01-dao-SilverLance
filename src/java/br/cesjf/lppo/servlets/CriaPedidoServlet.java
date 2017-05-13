package br.cesjf.lppo.servlets;

import br.ces.lppo.Pedido;
import br.cesjf.lppo.dao.PedidoDAO;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CriaProjetoServlet", urlPatterns = {"/novo.html"})
public class CriaPedidoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/novo-pedido.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        date = null;
        Pedido novoPedido = new Pedido();
        
        novoPedido.setPedido(parseInt(request.getParameter("pedido")));
        novoPedido.setDono(request.getParameter("dono"));
        novoPedido.setValor(parseFloat(request.getParameter("valor")));
        novoPedido.setNome(request.getParameter("nome"));
        novoPedido.setDataHora(date.parse(request.getParameter("dataHora")));

        try {
            PedidoDAO dao = new PedidoDAO();
            dao.cria(novoPedido);
        } catch (Exception ex) {
            request.setAttribute("mensagem", ex);
            request.getRequestDispatcher("WEB-INF/novo-contato.jsp");
            return;
        }

        response.sendRedirect("contatos.html");

    }

}
