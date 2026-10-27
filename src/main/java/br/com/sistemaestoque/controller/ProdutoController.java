package br.com.sistemaestoque.controller;

import br.com.sistemaestoque.dao.ProdutoDAO;
import java.sql.SQLException;
import java.math.BigDecimal;
import javax.swing.table.DefaultTableModel;

public class ProdutoController {
    private final ProdutoDAO dao=new ProdutoDAO();
    public void salvar(String nome,String descricao,String custo,String venda,String minimo,String atual,String categoria,String fornecedor) throws SQLException {
        if(nome.isBlank()) throw new IllegalArgumentException("Informe o nome do produto.");
        BigDecimal c=new BigDecimal(custo.replace(',','.')),v=new BigDecimal(venda.replace(',','.'));
        int min=Integer.parseInt(minimo),est=Integer.parseInt(atual),cat=Integer.parseInt(categoria),forn=Integer.parseInt(fornecedor);
        if(c.signum()<0||v.signum()<0||min<0||est<0) throw new IllegalArgumentException("Preços e estoque não podem ser negativos.");
        dao.inserir(nome,descricao,c,v,min,est,cat,forn);
    }
    public void excluir(int id)throws SQLException{dao.excluir(id);}
    public DefaultTableModel listar()throws SQLException{return dao.listar();}
    public DefaultTableModel relatorio()throws SQLException{return dao.relatorio();}
}
