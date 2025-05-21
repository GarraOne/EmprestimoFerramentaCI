package dao;

import java.util.ArrayList;
import modelo.Amigo;
import modelo.Emprestimo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TesteAmigoDao {

    @BeforeEach
    void inicializacao() {
        AmigoDAO dao = new AmigoDAO();
        Amigo amigo1 = new Amigo(1, "jorge", "1234567890");
        dao.insertAmigoDB(amigo1);
        Amigo amigo2 = new Amigo(2, "alex", "0987654321");
        dao.insertAmigoDB(amigo2);
    }

    @Test
    void TestGetListaAmigo() {
        AmigoDAO dao = new AmigoDAO();
        ArrayList<Amigo> listaEmprestimo = dao.getListaAmigo();
        int retornoEsperado = 2;
        assertEquals(retornoEsperado, listaEmprestimo.size());

    }

    @Test
    void TestMaiorID() {
        AmigoDAO dao = new AmigoDAO();
        int retornoEsperado = 2;
        assertEquals(retornoEsperado, dao.maiorIDAmigo());
    }

    @Test
    void TestRetrieve() {
        AmigoDAO dao = new AmigoDAO();
        Amigo amigoRecebido = dao.retrieveAmigoDB(1);
        int idAmigoEsperado = 1;
        String NomeEsperado = "jorge";
        String TelefoneEsperado = "1234567890";

        assertEquals(idAmigoEsperado, amigoRecebido.getIdAmigo());
        assertEquals(NomeEsperado, amigoRecebido.getNomeAmigo());
        assertEquals(TelefoneEsperado, amigoRecebido.getTelefone());

    }

    @Test
    void TestRetrieveSQLException() {
        AmigoDAO dao = new AmigoDAO();
        Amigo amigoRecebido = dao.retrieveAmigoDB(4);
        int idAmigoEsperado = 4;
        String NomeEsperado = "";
        String TelefoneEsperado = "";
        assertEquals(idAmigoEsperado, amigoRecebido.getIdAmigo());
        assertEquals(NomeEsperado, amigoRecebido.getNomeAmigo());
        assertEquals(TelefoneEsperado, amigoRecebido.getTelefone());
    }

    @Test
    void TestUpdate() {
        AmigoDAO dao = new AmigoDAO();
        Amigo amigoAtualiza = new Amigo(1, "jorge", "1234567890");
        dao.updateAmigoDB(amigoAtualiza);
        Amigo amigoRecebido = dao.retrieveAmigoDB(1);
        assertEquals(amigoAtualiza.getIdAmigo(), amigoRecebido.getIdAmigo());
        assertEquals(amigoAtualiza.getNomeAmigo(), amigoRecebido.getNomeAmigo());
        assertEquals(amigoAtualiza.getTelefone(), amigoRecebido.getTelefone());
    }

    @AfterEach
    void finalizacao() {
        AmigoDAO dao = new AmigoDAO();
        dao.deleteAmigoDB(1);
        dao.deleteAmigoDB(2);
    }
}
