package br.com.sistemaestoque.dao;

import br.com.sistemaestoque.util.Conexao;
import java.sql.*;
import java.math.BigDecimal;
import javax.swing.table.DefaultTableModel;

public class ProdutoDAO {
    public void inserir(String nome,String descricao,BigDecimal custo,BigDecimal venda,int minimo,int atual,int categoria,int fornecedor) throws SQLException {
        String sql="INSERT INTO produto(nome,descricao,preco_custo,preco_venda,estoque_minimo,estoque_atual,categoria_id,fornecedor_id) VALUES(?,?,?,?,?,?,?,?)";
        try(Connection c=Conexao.conectar(); PreparedStatement p=c.prepareStatement(sql)){
            p.setString(1,nome);p.setString(2,descricao);p.setBigDecimal(3,custo);p.setBigDecimal(4,venda);
            p.setInt(5,minimo);p.setInt(6,atual);p.setInt(7,categoria);p.setInt(8,fornecedor);p.executeUpdate();
        }
    }
    public void excluir(int id) throws SQLException {
        try(Connection c=Conexao.conectar(); PreparedStatement p=c.prepareStatement("DELETE FROM produto WHERE id=?")){
            p.setInt(1,id);p.executeUpdate();
        }
    }
    public DefaultTableModel listar() throws SQLException {
        DefaultTableModel m=new DefaultTableModel(new String[]{"ID","Produto","Categoria","Fornecedor","Custo","Venda","Estoque","Mínimo"},0);
        String sql="SELECT p.id,p.nome,c.nome cat,f.nome forn,p.preco_custo,p.preco_venda,p.estoque_atual,p.estoque_minimo FROM produto p JOIN categoria c ON c.id=p.categoria_id JOIN fornecedor f ON f.id=p.fornecedor_id ORDER BY p.nome";
        try(Connection c=Conexao.conectar(); Statement s=c.createStatement(); ResultSet r=s.executeQuery(sql)){
            while(r.next()) m.addRow(new Object[]{r.getInt("id"),r.getString("nome"),r.getString("cat"),r.getString("forn"),r.getBigDecimal("preco_custo"),r.getBigDecimal("preco_venda"),r.getInt("estoque_atual"),r.getInt("estoque_minimo")});
        } return m;
    }
    public DefaultTableModel relatorio() throws SQLException {
        DefaultTableModel m=new DefaultTableModel(new String[]{"Produto","Categoria","Estoque atual","Estoque mínimo","Situação"},0);
        String sql="SELECT p.nome,c.nome,p.estoque_atual,p.estoque_minimo FROM produto p JOIN categoria c ON c.id=p.categoria_id ORDER BY p.nome";
        try(Connection c=Conexao.conectar();Statement s=c.createStatement();ResultSet r=s.executeQuery(sql)){
            while(r.next()){int atual=r.getInt(3),min=r.getInt(4);m.addRow(new Object[]{r.getString(1),r.getString(2),atual,min,atual<=min?"REPOR":"Normal"});}
        }return m;
    }
}
