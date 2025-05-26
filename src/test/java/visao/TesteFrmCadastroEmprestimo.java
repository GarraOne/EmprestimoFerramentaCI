package visao;

import dao.AmigoDAO;
import dao.EmprestimoDAO;
import modelo.Amigo;
import dao.FerramentaDAO;
import modelo.Ferramenta;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class TesteFrmCadastroEmprestimo {

    //Objeto amigo a ser inserido
    Amigo amigoTeste;
    //Objeto ferramenta a ser inserido
    Ferramenta ferramentaTeste;
    //Formulário fake para inclusão dos dados
    FrmCadastroEmprestimoFake frmCadastroEmprestimo;

    /**
     * Inicializa os objetos para o teste.
     */
    @BeforeEach
    public void inicializa() {
        //Dados de teste de inclusão
        amigoTeste = new Amigo(1, "Joao", "12345678");
        ferramentaTeste = new Ferramenta(1, "Tesoura", 17, "selos");

        //Instância o formulário fake
        frmCadastroEmprestimo = new FrmCadastroEmprestimoFake();
        //Instância o controle do formulário
    }

    @Test
    public void testCadastroValido() {

        amigoTeste.insertAmigoDB("Joao", "12345678");
        ferramentaTeste.InsertFerramentaDB("Tesoura", "selos", 17);

        frmCadastroEmprestimo.inicializarCombos();

        frmCadastroEmprestimo.getJCBAmigo().setSelectedIndex(0);
        frmCadastroEmprestimo.getJCBFerramenta().setSelectedIndex(0);

        // Simula o clique no botão cadastrar via método do fake
        frmCadastroEmprestimo.clicarBotaoCadastrar();

        // Verifica se a mensagem exibida é a de sucesso
        assertEquals("Empréstimo cadastrado com sucesso.", frmCadastroEmprestimo.getMensagem());
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
