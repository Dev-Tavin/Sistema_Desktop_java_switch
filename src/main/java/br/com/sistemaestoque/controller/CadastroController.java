package br.com.sistemaestoque.controller;

import br.com.sistemaestoque.dao.CadastroDAO;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class CadastroController {
    private final CadastroDAO dao=new CadastroDAO();
    public void categoria(String nome,String descricao)throws SQLException{
        if(nome.isBlank())throw new IllegalArgumentException("Informe o nome da categoria.");
        dao.adicionarCategoria(nome.trim(),descricao.trim());
    }
    public void fornecedor(String nome,String contato,String telefone,String email,String endereco)throws SQLException{
        if(nome.isBlank())throw new IllegalArgumentException("Informe o nome do fornecedor.");
        dao.adicionarFornecedor(nome.trim(),contato.trim(),telefone.trim(),email.trim(),endereco.trim());
    }
    public DefaultTableModel categorias()throws SQLException{return dao.listarCategorias();}
    public DefaultTableModel fornecedores()throws SQLException{return dao.listarFornecedores();}
}
