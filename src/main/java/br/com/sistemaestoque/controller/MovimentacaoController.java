package br.com.sistemaestoque.controller;

import br.com.sistemaestoque.dao.MovimentacaoDAO;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class MovimentacaoController {
    private final MovimentacaoDAO dao=new MovimentacaoDAO();
    public void registrar(String tipo,String produto,String quantidade,String observacao)throws SQLException{
        dao.registrar(tipo,Integer.parseInt(produto),Integer.parseInt(quantidade),observacao,1);
    }
    public DefaultTableModel listar()throws SQLException{return dao.listar();}
}
