package visao;

import dao.FerramentaDAO;
import java.awt.Window;
import javax.swing.JDialog;
import modelo.Ferramenta;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class TesteFrmCadastroFerramenta {

    //Objeto ferramenta a ser inserido
    Ferramenta ferramentaTeste;
    Ferramenta ferramentaInvalidaTest;
    //Formulário fake para inclusão dos dados
    FrmCadastroFerramentaFake frmCadastroFerramenta;

    /**
     * Inicializa os objetos para o teste.
     */
    @BeforeEach
    void inicializa() {
        //Dados de teste de inclusão
        ferramentaTeste = new Ferramenta(1, "Tesoura", 17, "selos");
        ferramentaInvalidaTest = new Ferramenta(1, "T", 0, "S");

        //Instância o formulário fake
        frmCadastroFerramenta = new FrmCadastroFerramentaFake();
        //Instância o controle do formulário
    }

    @Test
    void testCadastroValido() {
        // Preenche os campos do formulário via getters do fake
        frmCadastroFerramenta.getJTFNomeFerramenta().setText(ferramentaTeste.getNomeFerramenta());
        frmCadastroFerramenta.getJTFMarcaFerramenta().setText(ferramentaTeste.getMarcaFerramenta());
        frmCadastroFerramenta.getJTFCustoFerramenta().setText(String.valueOf(ferramentaTeste.getCustoFerramenta()));
        // Simula o clique no botão cadastrar via método do fake
        frmCadastroFerramenta.clicarBotaoCadastrar();

        // Verifica se a mensagem exibida é a de sucesso
        assertEquals("Ferramenta cadastrada com sucesso.", frmCadastroFerramenta.getMensagem());
    }

    @Test
    void testLimparCadastro() {
        // Preenche os campos do formulário via getters do fake
        frmCadastroFerramenta.getJTFNomeFerramenta().setText(ferramentaTeste.getNomeFerramenta());
        frmCadastroFerramenta.getJTFMarcaFerramenta().setText(ferramentaTeste.getMarcaFerramenta());
        frmCadastroFerramenta.getJTFCustoFerramenta().setText(String.valueOf(ferramentaTeste.getCustoFerramenta()));

        // Simula o clique no botão Limpar via método do fake
        frmCadastroFerramenta.clicarBotaoLimpar();

        // Verifica se foi limpo os campos do formulário
        assertEquals("", frmCadastroFerramenta.getJTFNomeFerramenta().getText());
        assertEquals("", frmCadastroFerramenta.getJTFMarcaFerramenta().getText());
        assertEquals("", frmCadastroFerramenta.getJTFCustoFerramenta().getText());

    }

    @Test
    void testCadastroFerramentaInvalida() {
        // Preenche os campos do formulário via getters do fake
        frmCadastroFerramenta.getJTFNomeFerramenta().setText(ferramentaInvalidaTest.getNomeFerramenta());
        frmCadastroFerramenta.getJTFMarcaFerramenta().setText(ferramentaTeste.getMarcaFerramenta());
        frmCadastroFerramenta.getJTFCustoFerramenta().setText(String.valueOf(ferramentaTeste.getCustoFerramenta()));

        // Fecha a tela
        new javax.swing.Timer(500, e -> {
            for (Window w : Window.getWindows()) {
                if (w.isShowing() && w instanceof JDialog) {
                    w.dispose(); // Fecha o JOptionPane como se clicasse em "OK"
                }
            }
        }).start();

        // Simula o clique no botão cadastrar via método do fake
        frmCadastroFerramenta.clicarBotaoCadastrar();

        // Verifica se a mensagem exibida é a de sucesso
        assertEquals("Ferramenta Invalida.", frmCadastroFerramenta.getMensagem());

    }

    @Test
    void testCadastroMarcaInvalida() {
        // Preenche os campos do formulário via getters do fake
        frmCadastroFerramenta.getJTFNomeFerramenta().setText(ferramentaTeste.getNomeFerramenta());
        frmCadastroFerramenta.getJTFMarcaFerramenta().setText(ferramentaInvalidaTest.getMarcaFerramenta());
        frmCadastroFerramenta.getJTFCustoFerramenta().setText(String.valueOf(ferramentaTeste.getCustoFerramenta()));

        // Fecha a tela
        new javax.swing.Timer(500, e -> {
            for (Window w : Window.getWindows()) {
                if (w.isShowing() && w instanceof JDialog) {
                    w.dispose(); // Fecha o JOptionPane como se clicasse em "OK"
                }
            }
        }).start();

        // Simula o clique no botão cadastrar via método do fake
        frmCadastroFerramenta.clicarBotaoCadastrar();

        // Verifica se a mensagem exibida é a de sucesso
        assertEquals("Marca Invalida.", frmCadastroFerramenta.getMensagem());

    }

    @Test
    void testCadastroCustoInvalida() {
        // Preenche os campos do formulário via getters do fake
        frmCadastroFerramenta.getJTFNomeFerramenta().setText(ferramentaTeste.getNomeFerramenta());
        frmCadastroFerramenta.getJTFMarcaFerramenta().setText(ferramentaTeste.getMarcaFerramenta());
        frmCadastroFerramenta.getJTFCustoFerramenta().setText(String.valueOf(ferramentaInvalidaTest.getCustoFerramenta()));

        // Fecha a tela
        new javax.swing.Timer(500, e -> {
            for (Window w : Window.getWindows()) {
                if (w.isShowing() && w instanceof JDialog) {
                    w.dispose(); // Fecha o JOptionPane como se clicasse em "OK"
                }
            }
        }).start();

        // Simula o clique no botão cadastrar via método do fake
        frmCadastroFerramenta.clicarBotaoCadastrar();

        // Verifica se a mensagem exibida é a de sucesso
        assertEquals("Custo Invalido.", frmCadastroFerramenta.getMensagem());

    }

    @AfterEach
    void finaliza() {
        FerramentaDAO ferramentadao = new FerramentaDAO();
        ferramentadao.deleteFerramentaDB(1);
    }
}
