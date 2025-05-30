package visao;

import dao.AmigoDAO;
import modelo.Amigo;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class TesteFrmGerenciarAmigo {

    //Objeto amigo a ser inserido
    Amigo amigoTeste;
    //Formulário fake para inclusão dos dados
    FrmGerenciarAmigoFake frmGerenciarAmigo;

    /**
     * Inicializa os objetos para o teste.
     */
    @BeforeEach
    void inicializa() {
        //Dados de teste de inclusão
        amigoTeste = new Amigo(1, "Joao", "12345678");
        //Instância o formulário fake
        frmGerenciarAmigo = new FrmGerenciarAmigoFake();
        //Instância o controle do formulário
    }
    
     @Test
    public void testCancelarFechaJanela() {
        FrmGerenciarAmigo janela = new FrmGerenciarAmigo();
        
        assertTrue(janela.isDisplayable());
        
        janela.simularCliqueCancelar();
        
        assertFalse(janela.isDisplayable());
    }

    @Test
    void testModificarAmigo() {

        amigoTeste.insertAmigoDB("Joao", "12345678");

        frmGerenciarAmigo.inicializarLista();

        frmGerenciarAmigo.selecionarLinha(0);

        // Preenche os campos do formulário via getters do fake
        frmGerenciarAmigo.getJTFNome().setText(amigoTeste.getNomeAmigo());
        frmGerenciarAmigo.getJTFTelefone().setText(amigoTeste.getTelefone());

        // Simula o clique no botão cadastrar via método do fake
        frmGerenciarAmigo.clicarBotaoModificar();

        // Verifica se a mensagem exibida é a de sucesso
        assertEquals("Amigo atualizado com sucesso.", frmGerenciarAmigo.getMensagem());
    }

    @Test
    void testApagarAmigo() {

        amigoTeste.insertAmigoDB("Joao", "12345678");

        frmGerenciarAmigo.inicializarLista();

        frmGerenciarAmigo.selecionarLinha(0);

        // Simula o clique no botão Apagar via método do fake
        frmGerenciarAmigo.clicarBotaoApagar();

        // Verifica se a mensagem exibida é a de sucesso
        assertEquals("Amigo apagado com sucesso.", frmGerenciarAmigo.getMensagem());
    }

    @AfterEach
    void finaliza() {
        AmigoDAO amigodao = new AmigoDAO();
        amigodao.deleteAmigoDB(1);
    }
}
