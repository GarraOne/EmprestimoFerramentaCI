package visao;

import dao.AmigoDAO;
import java.awt.Window;
import javax.swing.JDialog;
import modelo.Amigo;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class TesteFrmCadrastroAmigo {

    //Objeto amigo a ser inserido
    Amigo amigoTeste;
    Amigo amigoInvalidoTest;

    //Formulário fake para inclusão dos dados
    FrmCadastroAmigoFake frmCadastroAmigo;

    /**
     * Inicializa os objetos para o teste.
     */
    @BeforeEach
    void inicializa() {
        //Dados de teste de inclusão
        amigoTeste = new Amigo(1, "Joao", "12345678");
        amigoInvalidoTest = new Amigo(1, "Ja", "10");

        //Instância o formulário fake
        frmCadastroAmigo = new FrmCadastroAmigoFake();
        //Instância o controle do formulário

    }

    @Test
    void testCadastroValido() {
        // Preenche os campos do formulário via getters do fake
        frmCadastroAmigo.getJTFNomeAmigo().setText(amigoTeste.getNomeAmigo());
        frmCadastroAmigo.getJTFTelefone().setText(amigoTeste.getTelefone());

        // Simula o clique no botão cadastrar via método do fake
        frmCadastroAmigo.clicarBotaoCadastrar();

        // Verifica se a mensagem exibida é a de sucesso
        assertEquals("Amigo cadastrado com sucesso.", frmCadastroAmigo.getMensagem());
    }

    @Test
    void testCadastroAmigoInvalido() {
        // Preenche os campos do formulário via getters do fake
        frmCadastroAmigo.getJTFNomeAmigo().setText(amigoInvalidoTest.getNomeAmigo());
        frmCadastroAmigo.getJTFTelefone().setText(amigoTeste.getTelefone());

        // Fecha a tela
        new javax.swing.Timer(500, e -> {
            for (Window w : Window.getWindows()) {
                if (w.isShowing() && w instanceof JDialog) {
                    w.dispose(); // Fecha o JOptionPane como se clicasse em "OK"
                }
            }
        }).start();

        // Simula o clique no botão cadastrar via método do fake
        frmCadastroAmigo.clicarBotaoCadastrar();

        // Verifica se a mensagem exibida é a de sucesso
        assertEquals("Amigo Invalido.", frmCadastroAmigo.getMensagem());

    }

    @Test
    void testCadastroTefoneInvalido() {
        // Preenche os campos do formulário via getters do fake
        frmCadastroAmigo.getJTFNomeAmigo().setText(amigoTeste.getNomeAmigo());
        frmCadastroAmigo.getJTFTelefone().setText(amigoInvalidoTest.getTelefone());

        // Fecha a tela
        new javax.swing.Timer(500, e -> {
            for (Window w : Window.getWindows()) {
                if (w.isShowing() && w instanceof JDialog) {
                    w.dispose(); // Fecha o JOptionPane como se clicasse em "OK"
                }
            }
        }).start();

        // Simula o clique no botão cadastrar via método do fake
        frmCadastroAmigo.clicarBotaoCadastrar();

        // Verifica se a mensagem exibida é a de sucesso
        assertEquals("Telefone Invalido.", frmCadastroAmigo.getMensagem());

    }

    @Test
    void testLimparCadastro() {
        // Preenche os campos do formulário via getters do fake
        frmCadastroAmigo.getJTFNomeAmigo().setText(amigoTeste.getNomeAmigo());
        frmCadastroAmigo.getJTFTelefone().setText(amigoTeste.getTelefone());

        // Simula o clique no botão Limpar via método do fake
        frmCadastroAmigo.clicarBotaoLimpar();

        // Verifica se foi limpo os campos do formulário
        assertEquals("", frmCadastroAmigo.getJTFNomeAmigo().getText());
        assertEquals("", frmCadastroAmigo.getJTFTelefone().getText());
    }

    @AfterEach
    void finaliza() {
        AmigoDAO amigodao = new AmigoDAO();
        amigodao.deleteAmigoDB(1);
    }
}
