package modelo;

import dao.EmprestimoDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class TesteEmprestimo {

    Emprestimo emprestimo;

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
        emprestimo.insertEmprestimoDB(1, 1, "05-05-2005");
        Emprestimo emp = emprestimo.retrieveEmprestimoDB(1);
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
        emp.deleteEmprestimoDB(emp.getIDEmprestimo());
    }

    @Test
    void TestMaiorID() {
        emprestimo.insertEmprestimoDB(1, 1, "05-05-2005");
        int maiorIDEsperado = 1;
        int maiorIDRecebido = emprestimo.maiorID();
        assertEquals(maiorIDEsperado, maiorIDRecebido);
        emprestimo.deleteEmprestimoDB(1);
    }

    @Test
    void TestEmprestimoAtivo() {
        emprestimo.insertEmprestimoDB(1, 1, "05-05-2005");
        String atividadeEsperado = "Sim";
        String atividadeRecebido = emprestimo.emprestimoAtivo(1);
        assertEquals(atividadeEsperado, atividadeRecebido);
        emprestimo.deleteEmprestimoDB(1);
    }

    @Test
    void TestListaEmprestimoAtivo() {
        EmprestimoDAO dao = new EmprestimoDAO();
        String diaAmanha = LocalDate.now().plusDays(1) + "";
        String[] diaAmanhaSeparado = diaAmanha.split("-");
        diaAmanha = diaAmanhaSeparado[2] + "-" + diaAmanhaSeparado[1] + "-" + diaAmanhaSeparado[0];
        Emprestimo emp = new Emprestimo(1, 1, 1, "05-05-2005", diaAmanha);
        dao.insertEmprestimoDB(emp);
        ArrayList<Emprestimo> listaEmprestimoAtivo = emprestimo.getListaEmprestimoAtivo();
        int tamanhoListaEsperado = 1;
        int tamanhoListaRecebido = listaEmprestimoAtivo.size();
        assertEquals(tamanhoListaEsperado, tamanhoListaRecebido);
        emp.deleteEmprestimoDB(1);
    }

    @AfterAll
    public void finalizacao() {
        emprestimo = null;
    }

}
