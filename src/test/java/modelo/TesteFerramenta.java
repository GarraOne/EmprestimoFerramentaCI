package modelo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class TesteFerramenta {
    
    Ferramenta ferramenta;
    
    @BeforeAll
    void inicializacao() {
        ferramenta = new Ferramenta(1, "Martelo", 10, "Bosh");
    }
    
    @Test
    void testIdFerramenta() {
        //Define o valor esperado para a solução
        int retornoEsperado = 1;
        //Chama a operação getIdFerramenta()
        int retornoFeito = ferramenta.getIdFerramenta();
        //Premissa verifica se os valores estão iguais
        assertEquals(retornoEsperado, retornoFeito, 1);
    }
    
    @Test
    void testgetNomeFerramenta() {
        //Define o nome esperado para a solução
        String retornoEsperado = "Martelo";
        //Chama a operação getNomeFerramenta
        String retornoFeito = ferramenta.getNomeFerramenta();
        //Premisa verifica se os nomes estão corretos
        assertEquals(retornoEsperado, retornoFeito, "");
    }
    
    @Test
    void testgetNomeMarca() {
        //Define o nome esperado para a solução
        String retornoEsperado = "Bosh";
        //Chama a operação getNomeMarca
        String retornoFeito = ferramenta.getMarcaFerramenta();
        //Premisa verifica se os nomes estão corretos
        assertEquals(retornoEsperado, retornoFeito, "");
    }
    
    @Test
    void testgetCustoFerramenta() {
        //Define o valor esperado para a solução
        double retornoEsperado = 10.0;
        //Chama a operaçãogetCustoFerramenta
        double retornoFeito = ferramenta.getCustoFerramenta();
        //Premissa verifica se os valores estão corretos
        assertEquals(retornoEsperado, retornoFeito, 0);
    }
    
    @Test
    void testgetFerramentaVazio() {
        String retornoEsperado = "";
        Ferramenta ferramenta = new Ferramenta();
        String retornoFeito = ferramenta.getNomeFerramenta();
        assertEquals(retornoEsperado, retornoFeito, "");
    }
    
    @AfterAll
    public void finalizacao() {
        ferramenta = null;
    }

}
