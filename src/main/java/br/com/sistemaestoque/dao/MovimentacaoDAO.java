package br.com.sistemaestoque.dao;

import br.com.sistemaestoque.util.Conexao;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class MovimentacaoDAO {
    public void registrar(String tipo,int produtoId,int quantidade,String observacao,int usuarioId) throws SQLException {
        if(quantidade<=0) throw new SQLException("A quantidade deve ser maior que zero.");
        try(Connection c=Conexao.conectar()){
            c.setAutoCommit(false);
            try {
                int saldo;
                try(PreparedStatement p=c.prepareStatement("SELECT estoque_atual FROM produto WHERE id=? FOR UPDATE")){
                    p.setInt(1,produtoId);try(ResultSet r=p.executeQuery()){if(!r.next())throw new SQLException("Produto não encontrado.");saldo=r.getInt(1);}
                }
                if(tipo.equals("SAIDA") && saldo<quantidade) throw new SQLException("Estoque insuficiente.");
                int novo=tipo.equals("ENTRADA")?saldo+quantidade:saldo-quantidade;
                try(PreparedStatement p=c.prepareStatement("UPDATE produto SET estoque_atual=? WHERE id=?")){
                    p.setInt(1,novo);p.setInt(2,produtoId);p.executeUpdate();
                }
                try(PreparedStatement p=c.prepareStatement("INSERT INTO movimentacao(tipo,quantidade,observacao,usuario_id,produto_id) VALUES(?,?,?,?,?)")){
                    p.setString(1,tipo);p.setInt(2,quantidade);p.setString(3,observacao);p.setInt(4,usuarioId);p.setInt(5,produtoId);p.executeUpdate();
                }
                c.commit();
            } catch(SQLException e){c.rollback();throw e;} finally {c.setAutoCommit(true);}
        }
    }
    public DefaultTableModel listar() throws SQLException {
        DefaultTableModel m=new DefaultTableModel(new String[]{"ID","Data","Tipo","Produto","Quantidade","Observação","Usuário"},0);
        String sql="SELECT m.id,m.data_hora,m.tipo,p.nome,m.quantidade,m.observacao,u.nome usuario FROM movimentacao m JOIN produto p ON p.id=m.produto_id JOIN usuario u ON u.id=m.usuario_id ORDER BY m.data_hora DESC";
        try(Connection c=Conexao.conectar();Statement s=c.createStatement();ResultSet r=s.executeQuery(sql)){
            while(r.next())m.addRow(new Object[]{r.getInt(1),r.getTimestamp(2),r.getString(3),r.getString(4),r.getInt(5),r.getString(6),r.getString(7)});
        }return m;
    }
}
