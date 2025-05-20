package dao;

import modelo.Amigo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TesteAmigoDao {

    AmigoDAO amigodao;

    @BeforeAll
    void inicializacao() {
        amigodao = new AmigoDAO();

    }
@BeforeEach()
    void insercao(){
    Amigo amigo1 = new Amigo(1,"jorge","123123123");
    amigodao.insertAmigoDB(amigo1);
}

    @Test
    void testMaiorIDAmigo() {
        //Define o valor esperado para a operação
        
        int retornoEsperado = 1;
        //Chama a operacao maiorIDAmigo()
        int retornoFeito = amigodao.maiorIDAmigo();
        //Premissa verifica se os valores são iguais
        assertEquals(retornoEsperado, retornoFeito);
    }
}
