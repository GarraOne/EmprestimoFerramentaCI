package modelo;

import dao.EmprestimoDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;
import service.EmprestimoService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class TesteEmprestimo {

    Emprestimo emprestimo;
    EmprestimoService emprestimoService;


    @BeforeAll
    void inicializacao() {
        emprestimo = new Emprestimo(5, 5, 5, "", "");
    }

    @Test
    void testgetIDEmprestimo() {
        //Define o valor esperado para a operação
        double retornoEsperado = 5.0;
        //Chama a operacao getIDEmprestimo()
        double retornoFeito = emprestimo.getIDEmprestimo();
        //Premissa verifica se os valores são iguais
        assertEquals(retornoEsperado, retornoFeito, 0);
    }

    @Test
    void TestRetrieveIDEmprestimo() {
        emprestimoService.insertEmprestimoDB(1, 1, "05-05-2005");
        Emprestimo emp = emprestimoService.retrieveEmprestimoDB(1);
        int idEmprestimoEsperado = 1;
        int idAmigoEsperado = 1;
        int idFerramentaEsperado = 1;
        String dataEmprestimoEsperado = "05-05-2005";
        String dataDevolucaoEsperada = "";
        assertEquals(idEmprestimoEsperado, emp.getIDEmprestimo());
        assertEquals(idAmigoEsperado, emp.getIDAmigo());
        assertEquals(idFerramentaEsperado, emp.getIDFerramenta());
        assertEquals(dataEmprestimoEsperado, emp.getDataEmprestimo());
        assertEquals(dataDevolucaoEsperada, emp.getDataDevolucao());
        emprestimoService.deleteEmprestimoDB(emp.getIDEmprestimo());
    }

    @Test
    void TestMaiorID() {
        emprestimoService.insertEmprestimoDB(1, 1, "05-05-2005");
        int maiorIDEsperado = 1;
        int maiorIDRecebido = emprestimoService.maiorID();
        assertEquals(maiorIDEsperado, maiorIDRecebido);
        emprestimoService.deleteEmprestimoDB(1);
    }

    @Test
    void TestEmprestimoAtivo() {
        emprestimoService.insertEmprestimoDB(1, 1, "05-05-2005");
        String atividadeEsperado = "Sim";
        String atividadeRecebido = emprestimoService.emprestimoAtivo(1);
        assertEquals(atividadeEsperado, atividadeRecebido);
        emprestimoService.deleteEmprestimoDB(1);
    }

    @Test
    void TestListaEmprestimoAtivo() {
        EmprestimoDAO dao = new EmprestimoDAO();
        String diaAmanha = LocalDate.now().plusDays(1) + "";
        String[] diaAmanhaSeparado = diaAmanha.split("-");
        diaAmanha = diaAmanhaSeparado[2] + "-" + diaAmanhaSeparado[1] + "-" + diaAmanhaSeparado[0];
        Emprestimo emp = new Emprestimo(1, 1, 1, "05-05-2005", diaAmanha);
        dao.insertEmprestimoDB(emp);
        ArrayList<Emprestimo> listaEmprestimoAtivo = emprestimoService.getListaEmprestimoAtivo();
        int tamanhoListaEsperado = 1;
        int tamanhoListaRecebido = listaEmprestimoAtivo.size();
        assertEquals(tamanhoListaEsperado, tamanhoListaRecebido);
        emprestimoService.deleteEmprestimoDB(1);
    }

    @Test
    void TestListaEmprestimoAtivoParseException() {
        EmprestimoDAO dao = new EmprestimoDAO();
        Emprestimo emp = new Emprestimo(1, 1, 1, "05-05-2005", "a-1-1");
        dao.insertEmprestimoDB(emp);
        ArrayList<Emprestimo> listaEmprestimoAtivo = emprestimoService.getListaEmprestimoAtivo();
        int tamanhoListaEsperado = 0;
        int tamanhoListaRecebido = listaEmprestimoAtivo.size();
        assertEquals(tamanhoListaEsperado, tamanhoListaRecebido);
        emprestimoService.deleteEmprestimoDB(1);
    }

    @AfterAll
    void finalizacao() {
        emprestimo = null;
    }

}
