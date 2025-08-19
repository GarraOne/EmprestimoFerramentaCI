package modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import service.AmigoService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TesteAmigo {

    Amigo amigo;
    AmigoService amigoService = new AmigoService();

    @BeforeEach
    void inicializacao() {
        amigo = new Amigo(1, "Joao", "48999");

    }

    @Test
    void testInsertAmigoDB() {
        boolean result = amigoService.insertAmigoDB("Joao", "48999");
        assertTrue(result);

        // Verifica se o amigo foi realmente inserido
        List<Amigo> amigos = amigoService.listaAmigo();
        assertFalse(amigos.isEmpty(), "A lista de amigos não deve estar vazia.");
        assertEquals("Joao", amigos.get(amigos.size() - 1).getNomeAmigo());
    }

    @Test
    void testDeleteAmigoDB() {
        amigoService.insertAmigoDB("Joao", "48999");
        int id = amigoService.maiorID(); // Pega o ID do último amigo inserido
        boolean result = amigoService.deleteAmigoDB(id);

        assertTrue(result);

        List<Amigo> amigos = amigoService.listaAmigo();
        assertFalse(amigos.stream().anyMatch(a -> a.getIdAmigo() == id));
    }

    @Test
    void testUpdateAmigoDB() {
        amigoService.insertAmigoDB("Carlos", "111222333");
        int id = amigoService.maiorID();
        boolean result = amigoService.updateAmigoDB(id, "Carlos Silva", "444555666");

        assertTrue(result);

        Amigo amigoAtualizado = amigoService.retrieveAmigoDB(id);
        assertEquals("Carlos Silva", amigoAtualizado.getNomeAmigo());
        assertEquals("444555666", amigoAtualizado.getTelefone());
    }

    @Test
    void testPossuiEmprestimoAtivo() {
        Emprestimo emp = new Emprestimo();
        emp.insertEmprestimoDB(1, 1, "05-05-2005");
        boolean emprestimoAtivoEsperado = true;
        boolean emprestimoAtivoRecebido = amigoService.possuiEmprestimoAtivo(1);
        assertEquals(emprestimoAtivoEsperado, emprestimoAtivoRecebido);
        emp.deleteEmprestimoDB(1);
    }

    @Test
    void testQuantidadeEmprestimo() {
        Emprestimo emp = new Emprestimo();
        emp.insertEmprestimoDB(1, 1, "05-05-2005");
        int quantidadeEsperada = 1;
        int quantidadeRecebida = amigoService.quantidadeEmprestimo(1);
        assertEquals(quantidadeEsperada, quantidadeRecebida);
        emp.deleteEmprestimoDB(1);
    }

    @Test
    void testGetVazio() {
        String retornoEsperado = "";
        Amigo ami = new Amigo();
        String retornoFeito = ami.getNomeAmigo();
        assertEquals(retornoEsperado, retornoFeito, "");
    }

    @Test
    void testGetNomeAmigo() {
        amigoService.insertAmigoDB("jorge", "123123123");
        String nomeEsperado = "jorge";
        String nomeRecebido = amigoService.getNomeAmigo(1);
        assertEquals(nomeEsperado, nomeRecebido);
        amigoService.deleteAmigoDB(1);
    }

    @AfterEach
    void finalizacao() {
        amigoService.deleteAmigoDB(1);
        amigoService.deleteAmigoDB(2);
    }
}
