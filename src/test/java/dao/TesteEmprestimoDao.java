package dao;

import java.util.List;
import modelo.Emprestimo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class TesteEmprestimoDao {

    @BeforeEach
    void inicializacao() {
        EmprestimoDAO dao = new EmprestimoDAO();
        Emprestimo emprestimo1 = new Emprestimo(1, 7, 9, "10-05-2020", "15-02-2021");
        dao.insertEmprestimoDB(emprestimo1);
        Emprestimo emprestimo2 = new Emprestimo(2, 2, 1, "15-06-2019", "15-02-2027");
        dao.insertEmprestimoDB(emprestimo2);
    }

    @Test
    void TestGetListaEmprestimo() {
        EmprestimoDAO dao = new EmprestimoDAO();
        List<Emprestimo> listaEmprestimo = dao.getListaEmprestimo();
        int retornoEsperado = 2;
        assertEquals(retornoEsperado, listaEmprestimo.size());

    }

    @Test
    void TestMaiorID() {
        EmprestimoDAO dao = new EmprestimoDAO();
        int retornoEsperado = 2;
        assertEquals(retornoEsperado, dao.maiorIDEmprestimo());
    }

    @Test
    void TestRetrieve() {
        EmprestimoDAO dao = new EmprestimoDAO();
        Emprestimo emprestimoRecebido = dao.retrieveEmprestimoDB(1);
        int idEmprestimoEsperado = 1;
        int idFerramentaEsperado = 9;
        int idAmigoEsperado = 7;
        String dataEmprestimoEsperada = "10-05-2020";
        String dataDevolucaoEsperada = "15-02-2021";
        assertEquals(idEmprestimoEsperado, emprestimoRecebido.getIDEmprestimo());
        assertEquals(idAmigoEsperado, emprestimoRecebido.getIDAmigo());
        assertEquals(idFerramentaEsperado, emprestimoRecebido.getIDFerramenta());
        assertEquals(dataEmprestimoEsperada, emprestimoRecebido.getDataEmprestimo());
        assertEquals(dataDevolucaoEsperada, emprestimoRecebido.getDataDevolucao());
    }

    @Test
    void TestRetrieveSQLException() {
        EmprestimoDAO dao = new EmprestimoDAO();
        Emprestimo emprestimoRecebido = dao.retrieveEmprestimoDB(4);
        int idEmprestimoEsperado = 4;
        int idFerramentaEsperado = 0;
        int idAmigoEsperado = 0;
        String dataEmprestimoEsperada = "";
        String dataDevolucaoEsperada = "";
        assertEquals(idEmprestimoEsperado, emprestimoRecebido.getIDEmprestimo());
        assertEquals(idAmigoEsperado, emprestimoRecebido.getIDAmigo());
        assertEquals(idFerramentaEsperado, emprestimoRecebido.getIDFerramenta());
        assertEquals(dataEmprestimoEsperada, emprestimoRecebido.getDataEmprestimo());
        assertEquals(dataDevolucaoEsperada, emprestimoRecebido.getDataDevolucao());
    }

    @Test
    void TestUpdate() {
        EmprestimoDAO dao = new EmprestimoDAO();
        Emprestimo emprestimoAtualiza = new Emprestimo(1, 2, 3, "12-09-2025", "13-10-2026");
        dao.updateEmprestimoDB(emprestimoAtualiza);
        Emprestimo emprestimoRecebido = dao.retrieveEmprestimoDB(1);
        assertEquals(emprestimoAtualiza.getIDEmprestimo(), emprestimoRecebido.getIDEmprestimo());
        assertEquals(emprestimoAtualiza.getIDAmigo(), emprestimoRecebido.getIDAmigo());
        assertEquals(emprestimoAtualiza.getIDFerramenta(), emprestimoRecebido.getIDFerramenta());
        assertEquals(emprestimoAtualiza.getDataEmprestimo(), emprestimoRecebido.getDataEmprestimo());
        assertEquals(emprestimoAtualiza.getDataDevolucao(), emprestimoRecebido.getDataDevolucao());
    }

    @AfterEach
    void finalizacao() {
        EmprestimoDAO dao = new EmprestimoDAO();
        dao.deleteEmprestimoDB(1);
        dao.deleteEmprestimoDB(2);
    }
}
