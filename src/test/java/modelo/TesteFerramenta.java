package modelo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import service.EmprestimoService;
import service.FerramentaService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class TesteFerramenta {

    FerramentaService ferramentaService = new FerramentaService();
    Ferramenta ferramenta;
    EmprestimoService emprestimoService;

    @BeforeEach
    void inicializacao() {
        ferramenta = new Ferramenta(1, "Martelo", 10, "Bosh");
        emprestimoService = new EmprestimoService();

    }

    @Test
    void TestGetDisponivel() {
        emprestimoService.insertEmprestimoDB(1, 1, "05-05-2005");
        String disponivelEsperado = "Não";
        String DisponivelRecebido = ferramentaService.getDisponivel(1);
        assertEquals(disponivelEsperado, DisponivelRecebido);
        emprestimoService.deleteEmprestimoDB(1);
    }

    @Test
    void TestRetriveFerramenta() {
        ferramentaService.insertFerramentaDB("martelo", "Bosh", 15);
        ferramenta = ferramentaService.retrieveFerramentaDB(1);
        int idEsperado = 1;
        String nomeEsperado = "martelo";
        String marcaEsperada = "Bosh";
        double custoEsperado = 15;
        assertEquals(idEsperado, ferramenta.getIdFerramenta());
        assertEquals(nomeEsperado, ferramenta.getNomeFerramenta());
        assertEquals(marcaEsperada, ferramenta.getMarcaFerramenta());
        assertEquals(custoEsperado, ferramenta.getCustoFerramenta());
        ferramentaService.deleteFerramentaDB(ferramenta.getIdFerramenta());
    }

    @Test
    void TestGetNomeFerramenta() {
        ferramentaService.insertFerramentaDB("martelo", "Bosh", 15);
        ferramenta = ferramentaService.retrieveFerramentaDB(1);
        String nomeEsperado = "martelo";
        assertEquals(nomeEsperado, ferramentaService.getNomeFerramenta(1));
        ferramentaService.deleteFerramentaDB(ferramenta.getIdFerramenta());
    }

    @Test
    void TestMaiorID() {
        ferramentaService.insertFerramentaDB("martelo", "Bosh", 15);
        int maiorIDEsperado = 1;
        int maiorIDRecebido = ferramentaService.maiorID();
        assertEquals(maiorIDEsperado, maiorIDRecebido);
        ferramentaService.deleteFerramentaDB(1);
    }

    @AfterEach
    void finalizacao() {
        ferramenta = null;
    }
}
