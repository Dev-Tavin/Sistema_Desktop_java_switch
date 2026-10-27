package br.com.sistemaestoque.view;

import br.com.sistemaestoque.controller.*;
import javax.swing.*;
import java.awt.*;

public class MenuPrincipalView extends JFrame {
    private final ProdutoController produtos=new ProdutoController();
    private final CadastroController cadastros=new CadastroController();
    private final MovimentacaoController movimentos=new MovimentacaoController();
    private final JTable tabelaProdutos=new JTable(), tabelaCategorias=new JTable(), tabelaFornecedores=new JTable(), tabelaMovimentos=new JTable(), tabelaRelatorio=new JTable();

    public MenuPrincipalView(){
        setTitle("Sistema de Gestão de Estoque");setDefaultCloseOperation(EXIT_ON_CLOSE);setSize(1050,650);setLocationRelativeTo(null);
        JTabbedPane tabs=new JTabbedPane();
        tabs.addTab("Início",painelInicio());
        tabs.addTab("Produtos",painelProdutos());
        tabs.addTab("Categorias",painelCategorias());
        tabs.addTab("Fornecedores",painelFornecedores());
        tabs.addTab("Movimentações",painelMovimentos());
        tabs.addTab("Relatórios",painelRelatorios());
        add(tabs);atualizarTudo();
    }
    private JPanel painelInicio(){
        JPanel p=new JPanel(new BorderLayout(10,10));p.setBorder(BorderFactory.createEmptyBorder(35,35,35,35));
        JLabel titulo=new JLabel("<html><h1>Bem-vindo ao Sistema de Estoque</h1><p>Controle produtos, fornecedores e movimentações.</p></html>");
        p.add(titulo,BorderLayout.NORTH);
        JTextArea info=new JTextArea("Use as abas acima para:\n\n• Cadastrar categorias e fornecedores antes de cadastrar produtos.\n• Registrar entradas e saídas para manter o estoque atualizado.\n• Consultar o relatório para identificar produtos que precisam de reposição.");
        info.setEditable(false);info.setFont(new Font("SansSerif",Font.PLAIN,16));info.setOpaque(false);p.add(info,BorderLayout.CENTER);return p;
    }
    private JPanel painelProdutos(){
        JPanel p=new JPanel(new BorderLayout(8,8));p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JPanel form=new JPanel(new GridLayout(0,4,7,7));
        JTextField nome=new JTextField(),desc=new JTextField(),custo=new JTextField("0.00"),venda=new JTextField("0.00"),min=new JTextField("0"),atual=new JTextField("0"),cat=new JTextField("1"),forn=new JTextField("1");
        addField(form,"Nome",nome);addField(form,"Descrição",desc);addField(form,"Preço de custo",custo);addField(form,"Preço de venda",venda);
        addField(form,"Estoque mínimo",min);addField(form,"Estoque inicial",atual);addField(form,"ID categoria",cat);addField(form,"ID fornecedor",forn);
        JButton salvar=new JButton("Salvar produto"), excluir=new JButton("Excluir selecionado"), atualizar=new JButton("Atualizar lista");
        JPanel botoes=new JPanel(new FlowLayout(FlowLayout.LEFT));botoes.add(salvar);botoes.add(excluir);botoes.add(atualizar);
        JPanel topo=new JPanel(new BorderLayout());topo.add(form,BorderLayout.CENTER);topo.add(botoes,BorderLayout.SOUTH);
        p.add(topo,BorderLayout.NORTH);p.add(new JScrollPane(tabelaProdutos),BorderLayout.CENTER);
        salvar.addActionListener(e->executar(()->{produtos.salvar(nome.getText(),desc.getText(),custo.getText(),venda.getText(),min.getText(),atual.getText(),cat.getText(),forn.getText());limpar(nome,desc);atualizarTudo();JOptionPane.showMessageDialog(this,"Produto cadastrado.");}));
        excluir.addActionListener(e->{int row=tabelaProdutos.getSelectedRow();if(row<0){aviso("Selecione um produto.");return;}int id=(int)tabelaProdutos.getValueAt(row,0);if(JOptionPane.showConfirmDialog(this,"Excluir produto ID "+id+"?")==JOptionPane.YES_OPTION)executar(()->{produtos.excluir(id);atualizarTudo();});});
        atualizar.addActionListener(e->atualizarTudo());return p;
    }
    private JPanel painelCategorias(){
        JPanel p=new JPanel(new BorderLayout(8,8));p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JTextField nome=new JTextField(),desc=new JTextField();JPanel f=new JPanel(new GridLayout(1,4,8,8));addField(f,"Nome",nome);addField(f,"Descrição",desc);
        JButton salvar=new JButton("Cadastrar categoria"),atualizar=new JButton("Atualizar");JPanel b=new JPanel(new FlowLayout(FlowLayout.LEFT));b.add(salvar);b.add(atualizar);
        JPanel n=new JPanel(new BorderLayout());n.add(f,BorderLayout.CENTER);n.add(b,BorderLayout.SOUTH);p.add(n,BorderLayout.NORTH);p.add(new JScrollPane(tabelaCategorias),BorderLayout.CENTER);
        salvar.addActionListener(e->executar(()->{cadastros.categoria(nome.getText(),desc.getText());nome.setText("");desc.setText("");atualizarTudo();}));
        atualizar.addActionListener(e->atualizarTudo());return p;
    }
    private JPanel painelFornecedores(){
        JPanel p=new JPanel(new BorderLayout(8,8));p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JTextField nome=new JTextField(),contato=new JTextField(),tel=new JTextField(),email=new JTextField(),endereco=new JTextField();
        JPanel f=new JPanel(new GridLayout(0,4,7,7));addField(f,"Nome",nome);addField(f,"Contato",contato);addField(f,"Telefone",tel);addField(f,"E-mail",email);addField(f,"Endereço",endereco);
        JButton salvar=new JButton("Cadastrar fornecedor"),atualizar=new JButton("Atualizar");JPanel b=new JPanel(new FlowLayout(FlowLayout.LEFT));b.add(salvar);b.add(atualizar);
        JPanel n=new JPanel(new BorderLayout());n.add(f,BorderLayout.CENTER);n.add(b,BorderLayout.SOUTH);p.add(n,BorderLayout.NORTH);p.add(new JScrollPane(tabelaFornecedores),BorderLayout.CENTER);
        salvar.addActionListener(e->executar(()->{cadastros.fornecedor(nome.getText(),contato.getText(),tel.getText(),email.getText(),endereco.getText());nome.setText("");atualizarTudo();}));
        atualizar.addActionListener(e->atualizarTudo());return p;
    }
    private JPanel painelMovimentos(){
        JPanel p=new JPanel(new BorderLayout(8,8));p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JComboBox<String> tipo=new JComboBox<>(new String[]{"ENTRADA","SAIDA"});JTextField produto=new JTextField("1",5),qtd=new JTextField("1",5),obs=new JTextField();
        JPanel f=new JPanel(new FlowLayout(FlowLayout.LEFT));f.add(new JLabel("Tipo:"));f.add(tipo);f.add(new JLabel("ID produto:"));f.add(produto);f.add(new JLabel("Quantidade:"));f.add(qtd);f.add(new JLabel("Observação:"));f.add(obs);
        JButton registrar=new JButton("Registrar movimentação"),atualizar=new JButton("Atualizar");JPanel b=new JPanel(new FlowLayout(FlowLayout.LEFT));b.add(registrar);b.add(atualizar);
        JPanel n=new JPanel(new BorderLayout());n.add(f,BorderLayout.CENTER);n.add(b,BorderLayout.SOUTH);p.add(n,BorderLayout.NORTH);p.add(new JScrollPane(tabelaMovimentos),BorderLayout.CENTER);
        registrar.addActionListener(e->executar(()->{movimentos.registrar((String)tipo.getSelectedItem(),produto.getText(),qtd.getText(),obs.getText());atualizarTudo();JOptionPane.showMessageDialog(this,"Movimentação registrada.");}));
        atualizar.addActionListener(e->atualizarTudo());return p;
    }
    private JPanel painelRelatorios(){
        JPanel p=new JPanel(new BorderLayout(8,8));p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JButton atualizar=new JButton("Gerar relatório");JPanel top=new JPanel(new FlowLayout(FlowLayout.LEFT));top.add(atualizar);p.add(top,BorderLayout.NORTH);p.add(new JScrollPane(tabelaRelatorio),BorderLayout.CENTER);
        atualizar.addActionListener(e->executar(()->tabelaRelatorio.setModel(produtos.relatorio())));return p;
    }
    private void addField(JPanel p,String label,JTextField campo){JPanel x=new JPanel(new BorderLayout(3,3));x.add(new JLabel(label),BorderLayout.NORTH);x.add(campo,BorderLayout.CENTER);p.add(x);}
    private void limpar(JTextField... campos){for(JTextField c:campos)c.setText("");}
    private void aviso(String s){JOptionPane.showMessageDialog(this,s);}
    private void executar(Acao a){try{a.run();}catch(Exception ex){JOptionPane.showMessageDialog(this,ex.getMessage(),"Atenção",JOptionPane.WARNING_MESSAGE);}}
    private void atualizarTudo(){
        try{
            tabelaProdutos.setModel(produtos.listar());tabelaCategorias.setModel(cadastros.categorias());
            tabelaFornecedores.setModel(cadastros.fornecedores());tabelaMovimentos.setModel(movimentos.listar());
            tabelaRelatorio.setModel(produtos.relatorio());
        }catch(Exception ex){/* Banco ainda pode não estar configurado; mensagem aparece ao tentar usar uma função. */}
    }
    @FunctionalInterface private interface Acao{void run()throws Exception;}
}
