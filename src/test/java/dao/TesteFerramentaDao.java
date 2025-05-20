package dao;

import java.util.ArrayList;
import modelo.Ferramenta;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

public class TesteFerramentaDao {
    
    @BeforeEach
    void inicializacao() {
        FerramentaDAO dao = new FerramentaDAO();
        Ferramenta ferramenta1 = new Ferramenta(1, "Martelo", 12.0, "Bosh");
        dao.insertFerramentaDB(ferramenta1);
        Ferramenta ferramenta2 = new Ferramenta(2, "Furadeira", 25.0, "Makita");
        dao.insertFerramentaDB(ferramenta2);
    }
    
    @Test
    void TestGetListaFerramenta() {
        FerramentaDAO dao = new FerramentaDAO();
        ArrayList<Ferramenta> listaFerramenta = dao.getListaFerramenta();
        int retornoEsperado = 2;
        assertEquals(retornoEsperado, listaFerramenta.size());
    }
    
    void TestMaiorIdFerramenta() {
        FerramentaDAO dao = new FerramentaDAO();
        int retornoEsperado = 2;
        assertEquals(retornoEsperado, dao.maiorIDFerramenta());
    }
    
    void TestRetriveFerramenta() {
        FerramentaDAO dao = new FerramentaDAO();
        Ferramenta ferramentaRecebida = dao.retrieveFerramentaDB(1);
        int idFerramentaEsperado = 1;
        String nomeFerramentaEsperada = "Martelo";
        double precoFerramentaEsperado = 12.0;
        String marcaFerramentaEsperado = "Bosh";
        assertEquals(idFerramentaEsperado, ferramentaRecebida.getIdFerramenta());
        assertEquals(nomeFerramentaEsperada, ferramentaRecebida.getNomeFerramenta());
        assertEquals(precoFerramentaEsperado, ferramentaRecebida.getCustoFerramenta());
        assertEquals(marcaFerramentaEsperado, ferramentaRecebida.getMarcaFerramenta());
    }
    
    void TestUpdateFerramenta() {
        FerramentaDAO dao = new FerramentaDAO();
        Ferramenta ferramentaAtualiza = new Ferramenta(1, "Martelo", 12.0, "Bosh");
        dao.updateFerramentaDB(ferramentaAtualiza);
        Ferramenta ferramentaRecebido = dao.retrieveFerramentaDB(1);
        assertEquals(ferramentaAtualiza.getIdFerramenta(), ferramentaRecebido.getIdFerramenta());
        assertEquals(ferramentaAtualiza.getNomeFerramenta(), ferramentaRecebido.getNomeFerramenta());
        assertEquals(ferramentaAtualiza.getCustoFerramenta(), ferramentaRecebido.getCustoFerramenta());
        assertEquals(ferramentaAtualiza.getMarcaFerramenta(), ferramentaRecebido.getMarcaFerramenta());
    }
    
    @AfterEach
    void finalizacao() {
        FerramentaDAO dao = new FerramentaDAO();
        dao.deleteFerramentaDB(1);
        dao.deleteFerramentaDB(2);
    }
}
