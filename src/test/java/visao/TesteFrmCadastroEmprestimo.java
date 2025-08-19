package visao;

import dao.AmigoDAO;
import dao.EmprestimoDAO;
import modelo.Amigo;
import dao.FerramentaDAO;
import java.awt.Window;
import javax.swing.JDialog;
import modelo.Emprestimo;
import modelo.Ferramenta;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import service.AmigoService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class TesteFrmCadastroEmprestimo {

    AmigoService amigoService = new AmigoService();

    //Objeto amigo a ser inserido
    Amigo amigoTeste;
    //Objeto ferramenta a ser inserido
    Ferramenta ferramentaTeste;
    Emprestimo emprestimoTeste;
    //Formulário fake para inclusão dos dados
    FrmCadastroEmprestimoFake frmCadastroEmprestimo;

    /**
     * Inicializa os objetos para o teste.
     */
    @BeforeEach
    void inicializa() {
        //Dados de teste de inclusão
        amigoTeste = new Amigo(1, "Joao", "12345678");
        ferramentaTeste = new Ferramenta(1, "Tesoura", 17, "selos");
        emprestimoTeste = new Emprestimo();

        //Instância o formulário fake
        frmCadastroEmprestimo = new FrmCadastroEmprestimoFake();
        //Instância o controle do formulário
    }

    @Test
    void testCadastroValido() {

        amigoService.insertAmigoDB("Joao", "12345678");
        ferramentaTeste.insertFerramentaDB("Tesoura", "selos", 17);

        frmCadastroEmprestimo.inicializarCombos();

        frmCadastroEmprestimo.getJCBAmigo().setSelectedIndex(0);
        frmCadastroEmprestimo.getJCBFerramenta().setSelectedIndex(0);

        // Simula o clique no botão cadastrar via método do fake
        frmCadastroEmprestimo.clicarBotaoCadastrar();

        // Verifica se a mensagem exibida é a de sucesso
        assertEquals("Empréstimo cadastrado com sucesso.", frmCadastroEmprestimo.getMensagem());

        AmigoDAO amigodao = new AmigoDAO();
        amigodao.deleteAmigoDB(1);
        FerramentaDAO ferramentadao = new FerramentaDAO();
        ferramentadao.deleteFerramentaDB(1);
        EmprestimoDAO emprestimodao = new EmprestimoDAO();
        emprestimodao.deleteEmprestimoDB(1);

    }

    @Test
    void testFerramentaInvalido() {

        amigoService.insertAmigoDB("Joao", "12345678");
        amigoService.insertAmigoDB("Ana", "87654321");
        ferramentaTeste.insertFerramentaDB("Tesoura", "selos", 17);
        emprestimoTeste.insertEmprestimoDB(1, 1, "21-05-2025");

        frmCadastroEmprestimo.inicializarCombos();

        frmCadastroEmprestimo.getJCBAmigo().setSelectedIndex(0);
        frmCadastroEmprestimo.getJCBFerramenta().setSelectedIndex(0);

        // Fecha a tela
        new javax.swing.Timer(500, e -> {
            for (Window w : Window.getWindows()) {
                if (w.isShowing() && w instanceof JDialog) {
                    w.dispose(); // Fecha o JOptionPane como se clicasse em "OK"
                }
            }
        }).start();

        // Simula o clique no botão cadastrar via método do fake
        frmCadastroEmprestimo.clicarBotaoCadastrar();

        assertEquals("Ferramenta já emprestada.", frmCadastroEmprestimo.getMensagem());

        AmigoDAO amigodao = new AmigoDAO();
        amigodao.deleteAmigoDB(1);
        amigodao.deleteAmigoDB(2);
        FerramentaDAO ferramentadao = new FerramentaDAO();
        ferramentadao.deleteFerramentaDB(1);
        EmprestimoDAO emprestimodao = new EmprestimoDAO();
        emprestimodao.deleteEmprestimoDB(1);
    }

    @Test
    void testEmprestimoInvalido() {

        amigoService.insertAmigoDB("Joao", "12345678");
        ferramentaTeste.insertFerramentaDB("Tesoura", "selos", 17);
        ferramentaTeste.insertFerramentaDB("Tesoura", "selos", 17);
        emprestimoTeste.insertEmprestimoDB(1, 1, "21-05-2025");

        frmCadastroEmprestimo.inicializarCombos();

        frmCadastroEmprestimo.getJCBAmigo().setSelectedIndex(0);
        frmCadastroEmprestimo.getJCBFerramenta().setSelectedIndex(1);

        // Fecha a tela
        new javax.swing.Timer(500, e -> {
            for (Window w : Window.getWindows()) {
                if (w.isShowing() && w instanceof JDialog) {
                    w.dispose(); // Fecha o JOptionPane como se clicasse em "OK"
                }
            }
        }).start();

        // Simula o clique no botão cadastrar via método do fake
        frmCadastroEmprestimo.clicarBotaoCadastrar();

        assertEquals("Empréstimo cadastrado com sucesso.", frmCadastroEmprestimo.getMensagem());

        AmigoDAO amigodao = new AmigoDAO();
        amigodao.deleteAmigoDB(1);
        FerramentaDAO ferramentadao = new FerramentaDAO();
        ferramentadao.deleteFerramentaDB(1);
        ferramentadao.deleteFerramentaDB(2);
        EmprestimoDAO emprestimodao = new EmprestimoDAO();
        emprestimodao.deleteEmprestimoDB(1);
        emprestimodao.deleteEmprestimoDB(2);
    }

}
