package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {

    /**
     * Estabelece uma conexão com o banco de dados de empréstimos.
     *
     * @return Conexão com o banco de dados ou null se a conexão falhar.
     */
    public Connection getConexao() {
        Connection connection = null;
        try {
            // Carrega o driver do banco de dados
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            // Informações de conexão com o banco de dados
            String server = "localhost";
            String database = "db_emprestimo";
            String url = "jdbc:mysql://" + server + ":3306/" + database + "?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String password = "root";

            // Estabelece a conexão com o banco de dados
            connection = DriverManager.getConnection(url, user, password);

            // Verifica se a conexão foi bem-sucedida e exibe uma mensagem
            if (connection != null) {
                System.out.println("Status: Conectado!");
            } else {
                System.out.println("Status: NÃO CONECTADO!");
            }
            return connection;

        } catch (ClassNotFoundException erro) {
            // Trata o erro caso o driver não seja encontrado
            System.out.println("O driver não foi encontrado. " + erro.getMessage());
            return null;
        } catch (SQLException erro) {
            // Trata o erro caso a conexão com o banco de dados falhe
            System.out.println("Não foi possível conectar...");
            return null;
        }
    }

}
