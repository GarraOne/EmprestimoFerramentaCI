package modelo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TesteAmigo {

    Amigo amigo;

    @BeforeEach
    void inicializacao() {
        amigo = new Amigo(1, "Joao", "48999");

    }
    @Test
    void testInsertAmigoDB() {
        boolean result = amigo.insertAmigoDB("Joao", "48999");
        assertTrue(result);
        
        // Verifica se o amigo foi realmente inserido
        ArrayList<Amigo> amigos = amigo.listaAmigo();
        assertFalse(amigos.isEmpty(), "A lista de amigos não deve estar vazia.");
        assertEquals("Joao", amigos.get(amigos.size() - 1).getNomeAmigo());
    }
    
     @Test
    void testDeleteAmigoDB() {
        amigo.insertAmigoDB("Joao", "48999");
        int id = amigo.maiorID(); // Pega o ID do último amigo inserido
        boolean result = amigo.deleteAmigoDB(id);
        
        assertTrue(result);
        
        ArrayList<Amigo> amigos = amigo.listaAmigo();
        assertFalse(amigos.stream().anyMatch(a -> a.getIdAmigo() == id));
    }

    @Test
    void testUpdateAmigoDB() {
        amigo.insertAmigoDB("Carlos", "111222333");
        int id = amigo.maiorID();
        boolean result = amigo.updateAmigoDB(id, "Carlos Silva", "444555666");
        
        assertTrue(result);
        
        Amigo amigoAtualizado = amigo.retrieveAmigoDB(id);
        assertEquals("Carlos Silva", amigoAtualizado.getNomeAmigo());
        assertEquals("444555666", amigoAtualizado.getTelefone());
    }

    @Test
    void testPossuiEmprestimoAtivo() {
        int id = amigo.maiorID(); // Verifica se voce tem um amigo com esse ID
        boolean emprestimoAtivo = amigo.possuiEmprestimoAtivo(id);
        
        assertFalse(emprestimoAtivo);
   
    }

    @Test
    void testQuantidadeEmprestimo() {
        int id = amigo.maiorID(); 
        int quantidade = amigo.quantidadeEmprestimo(id);
        
        assertEquals(0, quantidade);
    }

    @Test
    void testGetVazio() {
        String retornoEsperado = "";
        Amigo amigo = new Amigo();
        String retornoFeito = amigo.getNomeAmigo();
        assertEquals(retornoEsperado, retornoFeito, "");
    }

    @AfterEach
    public void finalizacao() {
        Amigo dao = new Amigo();
        dao.deleteAmigoDB(1);
        dao.deleteAmigoDB(2);
    }

}
