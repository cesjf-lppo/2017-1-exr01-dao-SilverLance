/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.lppo.servlets;

import br.cesjf.lppo.dao.PedidoDAO;
import br.ces.lppo.Pedido;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DetalhesServlet", urlPatterns = {"/detalhes.html"})
public class DetalhesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        try {
            PedidoDAO dao = new PedidoDAO();
            Pedido pedido = dao.buscarPorId(id);
            request.setAttribute("pedido", pedido);
            request.getRequestDispatcher("WEB-INF/detalhes-pedido.jsp").forward(request, response);
        } catch (NumberFormatException ex) {
            response.sendRedirect("pedidos.html");
        } catch (Exception ex) {
            Logger.getLogger(DetalhesServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("pedidos.html");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            PedidoDAO dao;
            dao = new PedidoDAO();
            Pedido pedido = dao.buscarPorId(id);
            
            pedido.setPedido(Integer.parseInt(request.getParameter("pedido")));
            pedido.setDono(request.getParameter("dono"));
            pedido.setValor(Float.parseFloat(request.getParameter("valor")));
            pedido.setNome(request.getParameter("nome"));
            pedido.setAtualizacao(formataData.parse(request.getParameter("atualizacao")));
            dao.atualizarPedido(pedido);
            response.sendRedirect("pedidos.html");
        } catch (NumberFormatException ex) {
            response.sendRedirect("pedidos.html");
        } catch (Exception ex) {
            Logger.getLogger(DetalhesServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("pedidos.html");
        }

    }

}