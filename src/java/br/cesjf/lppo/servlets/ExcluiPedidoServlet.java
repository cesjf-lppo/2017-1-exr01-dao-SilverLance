/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.lppo.servlets;

import br.cesjf.lppo.dao.PedidoDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Adriano
 */
@WebServlet(name = "ExcluiPedidoServlet", urlPatterns = {"/exclui.html"})
public class ExcluiPedidoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        try {
            PedidoDAO dao = new PedidoDAO();
            dao.excluiPedido(id);
            response.sendRedirect("pedidos.html");
        } catch (NumberFormatException ex) {
            response.sendRedirect("pedidos.html");
        } catch (Exception ex) {
            Logger.getLogger(DetalhesServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("pedidos.html");
        }

    }

}
