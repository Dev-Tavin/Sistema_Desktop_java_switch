package br.com.sistemaestoque.view;

import br.com.sistemaestoque.dao.UsuarioDAO;
import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private final JTextField usuario=new JTextField("admin",18);
    private final JPasswordField senha=new JPasswordField("admin123",18);
    public LoginView(){
        setTitle("Sistema de Gestão de Estoque — Login");setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420,260);setLocationRelativeTo(null);
        JPanel p=new JPanel(new GridBagLayout());p.setBorder(BorderFactory.createEmptyBorder(20,25,20,25));
        GridBagConstraints g=new GridBagConstraints();g.insets=new Insets(7,7,7,7);g.fill=GridBagConstraints.HORIZONTAL;
        JLabel titulo=new JLabel("Sistema de Gestão de Estoque",SwingConstants.CENTER);titulo.setFont(new Font("SansSerif",Font.BOLD,18));
        g.gridx=0;g.gridy=0;g.gridwidth=2;p.add(titulo,g);g.gridwidth=1;
        g.gridy++;g.gridx=0;p.add(new JLabel("Usuário:"),g);g.gridx=1;p.add(usuario,g);
        g.gridy++;g.gridx=0;p.add(new JLabel("Senha:"),g);g.gridx=1;p.add(senha,g);
        JButton entrar=new JButton("Entrar");g.gridy++;g.gridx=0;g.gridwidth=2;p.add(entrar,g);
        entrar.addActionListener(e->login());getRootPane().setDefaultButton(entrar);add(p);
    }
    private void login(){
        try{
            if(new UsuarioDAO().autenticar(usuario.getText().trim(),new String(senha.getPassword()))){
                dispose();new MenuPrincipalView().setVisible(true);
            }else JOptionPane.showMessageDialog(this,"Usuário ou senha inválidos.");
        }catch(Exception ex){JOptionPane.showMessageDialog(this,"Erro ao conectar ao banco: "+ex.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);}
    }
}
