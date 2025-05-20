package modelo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TesteAmigo {

    Amigo amigo;

    @BeforeAll
    void inicializacao() {
        amigo = new Amigo(1, "Joao", "48999");

    }

    @Test
    void testGetIdAmigo() {
        //Define o valor esperado para a operação
        int retornoEsperado = 1;
        //Chama a operacao getIDEmprestimo()
        int retornoFeito = amigo.getIdAmigo();
        //Premissa verifica se os valores são iguais
        assertEquals(retornoEsperado, retornoFeito, 0);
    }

    @Test
    void testGetNomeAmigo() {
        //Define o valor esperado para a operação
        String retornoEsperado = "Joao";
        //Chama a operacao getIDEmprestimo()
        String retornoFeito = amigo.getNomeAmigo();
        //Premissa verifica se os valores são iguais
        assertEquals(retornoEsperado, retornoFeito, "");
    }

    @Test
    void testGetTelefone() {
        //Define o valor esperado para a operação
        String retornoEsperado = "48999";
        //Chama a operacao getIDEmprestimo()
        String retornoFeito = amigo.getTelefone();
        //Premissa verifica se os valores são iguais
        assertEquals(retornoEsperado, retornoFeito, "");
    }

    @Test
    void testGetVazio() {
        String retornoEsperado = "";
        Amigo amigo = new Amigo();
        String retornoFeito = amigo.getNomeAmigo();
        assertEquals(retornoEsperado, retornoFeito, "");
    }

    @AfterAll
    public void finalizacao() {
        amigo = null;
    }

}
