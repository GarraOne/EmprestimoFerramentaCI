package dao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class TesteConexaoDao {
    @Test
    void testConexaoBemSucedidaOuNao() {
        // Captura a saída do console
        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(saida));

        ConexaoDAO dao = new ConexaoDAO();
        Connection conexao = dao.getConexao();

        // Restaura o console padrão
        System.setOut(originalOut);

        String output = saida.toString();

        // Verifica se o retorno é uma conexão válida ou null
        if (conexao != null) {
            assertTrue(output.contains("Status: Conectado!"));
        } else {
            assertTrue(output.contains("Status: NÃO CONECTADO!") ||
                       output.contains("Não foi possível conectar...") ||
                       output.contains("O driver não foi encontrado"));
        }
    }
}
