package br.com.sistemaestoque.dao;

import br.com.sistemaestoque.util.Conexao;
import java.sql.*;

public class UsuarioDAO {
    public boolean autenticar(String usuario, String senha) throws SQLException {
        String sql = "SELECT id FROM usuario WHERE email=? AND senha=?";
        try (Connection c=Conexao.conectar(); PreparedStatement ps=c.prepareStatement(sql)) {
            ps.setString(1, usuario); ps.setString(2, senha);
            try (ResultSet rs=ps.executeQuery()) { return rs.next(); }
        }
    }
}
