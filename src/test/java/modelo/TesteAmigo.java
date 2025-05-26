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
        Emprestimo emp = new Emprestimo();
        emp.insertEmprestimoDB(1, 1, "05-05-2005");
        boolean emprestimoAtivoEsperado = true;
        boolean emprestimoAtivoRecebido = amigo.possuiEmprestimoAtivo(1);
        assertEquals(emprestimoAtivoEsperado, emprestimoAtivoRecebido);
        emp.deleteEmprestimoDB(1);
    }

    @Test
    void testQuantidadeEmprestimo() {
        Emprestimo emp = new Emprestimo();
        emp.insertEmprestimoDB(1, 1, "05-05-2005");
        int quantidadeEsperada = 1;
        int quantidadeRecebida = amigo.quantidadeEmprestimo(1);
        assertEquals(quantidadeEsperada, quantidadeRecebida);
        emp.deleteEmprestimoDB(1);
    }

    @Test
    void testGetVazio() {
        String retornoEsperado = "";
        Amigo amigo = new Amigo();
        String retornoFeito = amigo.getNomeAmigo();
        assertEquals(retornoEsperado, retornoFeito, "");
    }

    @Test
    void testGetNomeAmigo() {
        amigo.insertAmigoDB("jorge", "123123123");
        String nomeEsperado = "jorge";
        String nomeRecebido = amigo.getNomeAmigo(1);
        assertEquals(nomeEsperado, nomeRecebido);
        amigo.deleteAmigoDB(1);
    }

    @AfterEach
    public void finalizacao() {
        Amigo dao = new Amigo();
        dao.deleteAmigoDB(1);
        dao.deleteAmigoDB(2);
    }

}
