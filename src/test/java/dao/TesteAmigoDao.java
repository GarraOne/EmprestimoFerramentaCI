package dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TesteAmigoDao {

    AmigoDAO amigodao;

    @BeforeAll
    void inicializacao() {
        amigodao = new AmigoDAO();

    }

    @Test
    void testMaiorIDAmigo() {
        //Define o valor esperado para a operação
        int retornoEsperado = 0;
        //Chama a operacao maiorIDAmigo()
        int retornoFeito = amigodao.maiorIDAmigo();
        //Premissa verifica se os valores são iguais
        assertEquals(retornoEsperado, retornoFeito, 0);
    }
}
