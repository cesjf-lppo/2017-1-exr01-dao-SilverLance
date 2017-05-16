package br.cesjf.lppo.dao;

import br.ces.lppo.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    private final PreparedStatement opListar;
    private final PreparedStatement opNovo;
    private final PreparedStatement opAtualiza;
    private final PreparedStatement opBuscaPorId;
    

    public PedidoDAO() throws Exception {
        Connection conexao = ConnectionFactory.createConnection();
        opListar = conexao.prepareStatement("SELECT * FROM pedido");
        opBuscaPorId = conexao.prepareStatement("SELECT * FROM pedido WHERE id =?");
        opNovo = conexao.prepareStatement("INSERT INTO pedido(pedido, dono, valor, nome, atualizacao) VALUES(?,?,?,?,?)");
        opAtualiza = conexao.prepareStatement("UPDATE pedido SET pedido = ?, dono = ?, valor=?, nome = ?, atualizacao = ? WHERE id = ?");
        
    }

    public List<Pedido> listAll() throws Exception {
        try {
            List<Pedido> pedidos = new ArrayList<>();

            ResultSet resultado = opListar.executeQuery();
            while (resultado.next()) {
                Pedido novoPedido = new Pedido();
                novoPedido.setId(resultado.getLong("id"));
                novoPedido.setPedido(resultado.getInt("pedido"));
                novoPedido.setDono(resultado.getString("dono"));
                novoPedido.setValor(resultado.getFloat("valor"));
                novoPedido.setNome(resultado.getString("nome"));
                novoPedido.setDataHora(resultado.getTimestamp("atualizacao"));
                pedidos.add(novoPedido);
            }

            return pedidos;
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar os pedidos no banco!", ex);
        }
    }

    public Pedido getById(Long id) throws Exception {
        try {
            Pedido pedido = null;
               opBuscaPorId.clearParameters();
               opBuscaPorId.setLong(1, id);
            ResultSet resultado = opBuscaPorId.executeQuery();
            if (resultado.next()) {
                pedido  = new Pedido();
                pedido.setId(resultado.getLong("id"));
                pedido.setPedido(resultado.getInt("pedido"));
                pedido.setDono(resultado.getString("dono"));
                pedido.setValor(resultado.getFloat("valor"));
                pedido.setNome(resultado.getString("nome"));
                pedido.setDataHora(resultado.getTimestamp("atualizacao"));
            }
            return pedido;
        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar o pedido no banco!", ex);
        }
    }
    
    public void cria(Pedido novoPedido) throws Exception {
        try {
            
            opNovo.clearParameters();
            opNovo.setInt(1, novoPedido.getPedido());
            opNovo.setString(2, novoPedido.getDono());
            opNovo.setFloat(3, novoPedido.getValor());
            opNovo.setString(4, novoPedido.getNome());
            opNovo.setTimestamp(5, new Timestamp (novoPedido.getDataHora().getTime()));
            opNovo.executeUpdate();


        } catch (SQLException ex) {
            throw new Exception("Erro ao inserir novo pedido", ex);
        }
    }
    
       public void atualiza(Pedido pedido) throws Exception {
        try {
            
            opAtualiza.clearParameters();
            opAtualiza.setLong(1, pedido.getId());
            opAtualiza.setInt(2, pedido.getPedido());
            opAtualiza.setString(3, pedido.getDono());
            opAtualiza.setFloat(4, pedido.getValor());
            opAtualiza.setString(5, pedido.getNome());
            opAtualiza.setTimestamp(6, (Timestamp) pedido.getDataHora());
            opAtualiza.executeUpdate();


        } catch (SQLException ex) {
            throw new Exception("Erro ao inserir novo pedido", ex);
        }
        
}
}