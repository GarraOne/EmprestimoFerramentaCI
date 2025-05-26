package modelo;

import dao.EmprestimoDAO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class TesteFerramenta {

    Ferramenta ferramenta;

    @BeforeEach
    void inicializacao() {
        ferramenta = new Ferramenta(1, "Martelo", 10, "Bosh");
    }

    @Test
    void TestGetDisponivel() {
        Emprestimo emp = new Emprestimo();
        emp.insertEmprestimoDB(1, 1, "05-05-2005");
        String disponivelEsperado = "Não";
        String DisponivelRecebido = ferramenta.getDisponivel(1);
        assertEquals(disponivelEsperado, DisponivelRecebido);
        emp.deleteEmprestimoDB(1);
    }

    @Test
    void TestRetriveFerramenta() {
        ferramenta.InsertFerramentaDB("martelo", "Bosh", 15);
        ferramenta = ferramenta.retrieveFerramentaDB(1);
        int idEsperado = 1;
        String nomeEsperado = "martelo";
        String marcaEsperada = "Bosh";
        double custoEsperado = 15;
        assertEquals(idEsperado, ferramenta.getIdFerramenta());
        assertEquals(nomeEsperado, ferramenta.getNomeFerramenta());
        assertEquals(marcaEsperada, ferramenta.getMarcaFerramenta());
        assertEquals(custoEsperado, ferramenta.getCustoFerramenta());
        ferramenta.deleteFerramentaDB(ferramenta.getIdFerramenta());
    }

        @Test
    void TestGetNomeFerramenta() {
        ferramenta.InsertFerramentaDB("martelo", "Bosh", 15);
        ferramenta = ferramenta.retrieveFerramentaDB(1);
        String nomeEsperado = "martelo";
        assertEquals(nomeEsperado, ferramenta.getNomeFerramenta(1));
        ferramenta.deleteFerramentaDB(ferramenta.getIdFerramenta());
    }
    
    @Test
    void TestGProcuraIndice() {
        ferramenta.InsertFerramentaDB("martelo", "Bosh", 15);
        ferramenta.InsertFerramentaDB("maquita", "jorge", 20);
        int indiceEsperado = 1;
        int indiceRecebido = ferramenta.procuraIndice(2);
        assertEquals(indiceEsperado, indiceRecebido);
        ferramenta.deleteFerramentaDB(1);
        ferramenta.deleteFerramentaDB(2);
    }

    @Test
    void TestProcuraIndice(){
        ferramenta.InsertFerramentaDB("martelo", "Bosh", 15);
        int maiorIDEsperado = 1;
        int maiorIDRecebido = ferramenta.MaiorID();
        assertEquals(maiorIDEsperado, maiorIDRecebido);
        ferramenta.deleteFerramentaDB(1);
    }
    
    @AfterEach
    public void finalizacao() {
        ferramenta = null;
    }

}
