package br.cesjf.lppo.dao;

import br.ces.lppo.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PedidoDAO {

    private final SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final PreparedStatement opListar;
    private final PreparedStatement opNovo;
    private final PreparedStatement opBuscaPorId;
    private final PreparedStatement opAtualiza;
    private final PreparedStatement opExclui;
    
    

    public PedidoDAO() throws Exception {
        Connection conexao = ConnectionFactory.createConnection();
        opListar = conexao.prepareStatement("SELECT * FROM pedido");
        opNovo = conexao.prepareStatement("INSERT INTO pedido(pedido, dono, valor, nome) Values(?, ?, ?, ?)");
        opBuscaPorId = conexao.prepareStatement("SELECT * FROM pedido WHERE id = ?");
        opAtualiza = conexao.prepareStatement("UPDATE pedido SET pedido = ?, dono = ?, valor = ?, nome = ?, atualizacao = ? WHERE id = ?"); // FALTA O CAMPO ATUALIZAÇÃO
        opExclui = conexao.prepareStatement("DELETE FROM pedido WHERE id = ?");
    }

    public List<Pedido> listarTodos() throws Exception {
        try {
            List<Pedido> pedidos = new ArrayList<>();

            ResultSet resultado = opListar.executeQuery();
            while (resultado.next()) {
                Pedido pedidoAtual = new Pedido();
                pedidoAtual.setId(resultado.getLong("id"));
                pedidoAtual.setPedido(resultado.getInt("pedido"));
                pedidoAtual.setDono(resultado.getString("dono"));
                pedidoAtual.setValor(resultado.getFloat("valor"));
                pedidoAtual.setNome(resultado.getString("nome"));
                pedidoAtual.setAtualizacao(resultado.getTimestamp("atualizacao"));
                pedidos.add(pedidoAtual);
            }
            return pedidos;
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar os contatos no banco!", ex);
        }
    }

    public void criarPedido(Pedido novoPedido) throws Exception {
        try {
            opNovo.clearParameters();
            opNovo.setLong(1, novoPedido.getPedido());
            opNovo.setString(2, novoPedido.getDono());
            opNovo.setDouble(3, novoPedido.getValor());
            opNovo.setString(4, novoPedido.getNome());
            opNovo.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception("Erro ao inserir novo pedido!", ex);
        }
    }

    public Pedido buscarPorId(Long id) throws Exception {
        try {
            Pedido pedido = null;
            opBuscaPorId.clearParameters();
            opBuscaPorId.setLong(1, id);
            ResultSet resultado = opBuscaPorId.executeQuery();
            while(resultado.next()){
                pedido = new Pedido();
                pedido.setId(resultado.getLong("id"));
                pedido.setPedido(resultado.getInt("pedido"));
                pedido.setDono(resultado.getString("dono"));
                pedido.setValor(resultado.getFloat("valor"));
                pedido.setNome(resultado.getString("nome"));
                pedido.setAtualizacao(resultado.getTimestamp("atualizacao"));
            }

            return pedido;
        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar pedido no banco!", ex);
        }
    }

    public void atualizarPedido(Pedido pedido) throws Exception {
        try{
            
            opAtualiza.clearParameters();
            opAtualiza.setInt(1, pedido.getPedido());
            opAtualiza.setString(2, pedido.getDono());
            opAtualiza.setFloat(3, pedido.getValor());
            opAtualiza.setString(4, pedido.getNome());
            opAtualiza.setTimestamp(5, new Timestamp(pedido.getAtualizacao().getTime()));// Pega a data e hora e insere como parametro
            opAtualiza.setLong(6, pedido.getId());
            opAtualiza.executeUpdate();
            
        } catch(SQLException ex){
            throw new Exception("Erro ao atualizar pedido!"); 
        }
    }
    
        public void excluiPedido(Long id) throws Exception {
        try{
            opExclui.setLong(1, id);
            opExclui.executeQuery();
            opExclui.executeUpdate();
        } catch(SQLException ex){
            throw new Exception("Erro ao excluir pedido!"); 
        }
    }
}