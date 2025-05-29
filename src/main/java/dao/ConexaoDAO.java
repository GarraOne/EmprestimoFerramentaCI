package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoDAO {

    private static final Logger logger = Logger.getLogger(ConexaoDAO.class.getName());

    /**
     * Estabelece uma conexão com o banco de dados de empréstimos.
     *
     * @return Conexão com o banco de dados ou null se a conexão falhar.
     */
    public Connection getConexao() {
        Connection connection = null;
        try {
            carregarDriver();
            connection = criarConexao();
            logger.info("Status: Conectado!");
            return connection;
        } catch (ClassNotFoundException erro) {
            logger.log(Level.SEVERE, "O driver não foi encontrado." + erro);
            return null;
        } catch (SQLException erro) {
            logger.log(Level.SEVERE, "Não foi possível conectar ao banco de dados.", erro);
            return null;
        }
    }

    protected void carregarDriver() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
    }

    protected Connection criarConexao() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:emprestimo.db");
    }
}
