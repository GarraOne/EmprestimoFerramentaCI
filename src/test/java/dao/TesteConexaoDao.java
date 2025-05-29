package dao;

import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class TesteConexaoDao {

    @Test
    void testDriverNaoEncontrado() {
        ConexaoDAO dao = new ConexaoDAO() {
            @Override
            protected void carregarDriver() throws ClassNotFoundException {
                throw new ClassNotFoundException("Driver falso");
            }
        };
        
        Connection conexao = dao.getConexao();
        assertNull(conexao, "A conexão deve ser nula quando o driver não é encontrado");
    }
    
    @Test
    void testFalhaAoConectar() {
        ConexaoDAO dao = new ConexaoDAO() {
            @Override
            protected Connection criarConexao() throws SQLException {
                throw new SQLException("Erro simulado de conexão");
            }
        };
        
        Connection conexao = dao.getConexao();
        assertNull(conexao, "A conexão deve ser nula quando ocorre uma SQLException.");
    }
}
