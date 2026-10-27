package br.com.sistemaestoque.dao;

import br.com.sistemaestoque.util.Conexao;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class CadastroDAO {
    public void adicionarCategoria(String nome, String descricao) throws SQLException {
        try(Connection c=Conexao.conectar(); PreparedStatement p=c.prepareStatement("INSERT INTO categoria(nome,descricao) VALUES(?,?)")){
            p.setString(1,nome); p.setString(2,descricao); p.executeUpdate();
        }
    }
    public void adicionarFornecedor(String nome, String contato, String telefone, String email, String endereco) throws SQLException {
        String sql="INSERT INTO fornecedor(nome,contato,telefone,email,endereco) VALUES(?,?,?,?,?)";
        try(Connection c=Conexao.conectar(); PreparedStatement p=c.prepareStatement(sql)){
            p.setString(1,nome); p.setString(2,contato); p.setString(3,telefone); p.setString(4,email); p.setString(5,endereco); p.executeUpdate();
        }
    }
    public DefaultTableModel listarCategorias() throws SQLException {
        DefaultTableModel m=new DefaultTableModel(new String[]{"ID","Nome","Descrição"},0);
        try(Connection c=Conexao.conectar(); Statement s=c.createStatement(); ResultSet r=s.executeQuery("SELECT * FROM categoria ORDER BY nome")){
            while(r.next()) m.addRow(new Object[]{r.getInt("id"),r.getString("nome"),r.getString("descricao")});
        } return m;
    }
    public DefaultTableModel listarFornecedores() throws SQLException {
        DefaultTableModel m=new DefaultTableModel(new String[]{"ID","Nome","Contato","Telefone","E-mail"},0);
        try(Connection c=Conexao.conectar(); Statement s=c.createStatement(); ResultSet r=s.executeQuery("SELECT * FROM fornecedor ORDER BY nome")){
            while(r.next()) m.addRow(new Object[]{r.getInt("id"),r.getString("nome"),r.getString("contato"),r.getString("telefone"),r.getString("email")});
        } return m;
    }
    public DefaultTableModel listarCategoriasCombo() throws SQLException { return listarCategorias(); }
}
