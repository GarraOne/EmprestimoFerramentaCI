package modelo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)


public class TesteEmprestimo {

    Emprestimo emprestimo;

    @BeforeAll
    void inicializacao() {
        emprestimo = new Emprestimo(5, 5, 5, "", "");
    }
    
    @Test
    void testgetIDEmprestimo() {
        //Define o valor esperado para a operação
        double retornoEsperado = 5.0;
        //Chama a operacao getIDEmprestimo()
        double retornoFeito = emprestimo.getIDEmprestimo();
        //Premissa verifica se os valores são iguais
        assertEquals(retornoEsperado, retornoFeito, 0);
    }

    @AfterAll

    public void finalizacao() {
        emprestimo = null;
    }

}
