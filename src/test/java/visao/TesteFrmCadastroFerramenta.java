package visao;

import dao.FerramentaDAO;
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
    //Formulário fake para inclusão dos dados
    FrmCadastroFerramentaFake frmCadastroFerramenta;

    /**
     * Inicializa os objetos para o teste.
     */
    @BeforeEach
    public void inicializa() {
        //Dados de teste de inclusão
        ferramentaTeste = new Ferramenta(1, "Tesoura", 17, "selos");
        //Instância o formulário fake
        frmCadastroFerramenta = new FrmCadastroFerramentaFake();
        //Instância o controle do formulário
    }

    @Test
    public void testCadastroValido() {
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
    public void testLimparCadastro() {
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

    @AfterEach
    void finaliza() {
        FerramentaDAO ferramentadao = new FerramentaDAO();
        ferramentadao.deleteFerramentaDB(1);
    }
}
