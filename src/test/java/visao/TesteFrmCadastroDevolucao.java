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
import service.AmigoService;
import service.EmprestimoService;
import service.FerramentaService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class TesteFrmCadastroDevolucao {

    private transient FerramentaService ferramentaService = new FerramentaService();
    AmigoService amigoService = new AmigoService();

    //Objeto amigo a ser inserido
    Amigo amigoTeste;
    //Objeto ferramenta a ser inserido
    Ferramenta ferramentaTeste;
    Emprestimo emprestimoTeste;
    EmprestimoService emprestimoService;

    //Formulário fake para inclusão dos dados
    FrmCadastroDevolucaoFake frmCadastroDevolucao;

    /**
     * Inicializa os objetos para o teste.
     */
    @BeforeEach
    void inicializa() {
        //Dados de teste de inclusão
        amigoTeste = new Amigo(1, "Joao", "12345678");
        ferramentaTeste = new Ferramenta(1, "Tesoura", 17, "selos");
        emprestimoTeste = new Emprestimo(1, 1, 1, "21-05-2025", "");

        emprestimoService = new EmprestimoService();

        //Instância o formulário fake
        frmCadastroDevolucao = new FrmCadastroDevolucaoFake();
        //Instância o controle do formulário
    }

    @Test
    void testCadastroValido() {

        amigoService.insertAmigoDB("Joao", "12345678");
        ferramentaService.insertFerramentaDB("Tesoura", "selos", 17);
        emprestimoService.insertEmprestimoDB(1, 1, "21-05-2025");

        frmCadastroDevolucao.inicializarCombos();

        frmCadastroDevolucao.getJCBEmprestimo().setSelectedIndex(0);

        // Simula o clique no botão cadastrar via método do fake
        frmCadastroDevolucao.clicarBotaoCadastrar();

        // Verifica se a mensagem exibida é a de sucesso
        assertEquals("Devolucao cadastrada com sucesso.", frmCadastroDevolucao.getMensagem());
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
