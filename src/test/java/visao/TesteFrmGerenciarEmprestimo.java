package visao;

import dao.AmigoDAO;
import modelo.Amigo;
import dao.FerramentaDAO;
import modelo.Ferramenta;
import dao.EmprestimoDAO;
import modelo.Emprestimo;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TesteFrmGerenciarEmprestimo {
    //Objeto amigo a ser inserido

    Amigo amigoTeste;
    //Objeto ferramenta a ser inserido
    Ferramenta ferramentaTeste;
    Emprestimo emprestimoTeste;
    //Formulário fake para inclusão dos dados
    FrmGerenciarEmprestimoFake frmGerenciarEmprestimo;

    /**
     * Inicializa os objetos para o teste.
     */
    @BeforeEach
    public void inicializa() {
        //Dados de teste de inclusão
        amigoTeste = new Amigo(1, "Joao", "12345678");
        ferramentaTeste = new Ferramenta(1, "Tesoura", 17, "selos");
        emprestimoTeste = new Emprestimo(1, 1, 1, "21-05-2025", "");

        //Instância o formulário fake
        frmGerenciarEmprestimo = new FrmGerenciarEmprestimoFake();
        //Instância o controle do formulário
    }

    @Test
    public void testModificarEmprestimo() {

        amigoTeste.insertAmigoDB("Joao", "12345678");
        ferramentaTeste.InsertFerramentaDB("Tesoura", "selos", 17);
        emprestimoTeste.insertEmprestimoDB(1, 1, "21-05-2025");

        frmGerenciarEmprestimo.inicializarLista();

        frmGerenciarEmprestimo.selecionarLinha(0);

        // Preenche os campos do formulário via getters do fake
        frmGerenciarEmprestimo.getJTFIdAmigo().setText(String.valueOf(emprestimoTeste.getIDAmigo()));
        frmGerenciarEmprestimo.getJTFIdFerramenta().setText(String.valueOf(emprestimoTeste.getIDFerramenta()));
        frmGerenciarEmprestimo.getJTFDataEmprestimo().setText(emprestimoTeste.getDataEmprestimo());
        frmGerenciarEmprestimo.getJTFDataDevolucao().setText(emprestimoTeste.getDataDevolucao());

        // Simula o clique no botão cadastrar via método do fake
        frmGerenciarEmprestimo.clicarBotaoModificar();

        // Verifica se a mensagem exibida é a de sucesso
        assertEquals("Empréstimo atualizado com sucesso.", frmGerenciarEmprestimo.getMensagem());
    }

    @AfterEach
    void finaliza() {
        AmigoDAO amigodao = new AmigoDAO();
        amigodao.deleteAmigoDB(1);
        FerramentaDAO ferramentadao = new FerramentaDAO();
        ferramentadao.deleteFerramentaDB(1);
        EmprestimoDAO emprestimodao = new EmprestimoDAO();
        emprestimodao.deleteEmprestimoDB(1);
    }
}
