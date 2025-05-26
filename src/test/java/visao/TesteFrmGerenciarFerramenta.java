package visao;

import dao.FerramentaDAO;
import modelo.Ferramenta;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class TesteFrmGerenciarFerramenta {
    //Objeto amigo a ser inserido

    Ferramenta ferramentaTeste;
    //Formulário fake para inclusão dos dados
    FrmGerenciarFerramentaFake frmGerenciarFerramenta;

    /**
     * Inicializa os objetos para o teste.
     */
    @BeforeEach
    public void inicializa() {
        //Dados de teste de inclusão
        ferramentaTeste = new Ferramenta(1, "Tesoura", 17, "selos");
        //Instância o formulário fake
        frmGerenciarFerramenta = new FrmGerenciarFerramentaFake();
        //Instância o controle do formulário
    }

    @Test
    public void testModificarFerramenta() {

        ferramentaTeste.InsertFerramentaDB("Tesoura", "selos", 17);

        frmGerenciarFerramenta.inicializarLista();

        frmGerenciarFerramenta.selecionarLinha(0);

        // Preenche os campos do formulário via getters do fake
        frmGerenciarFerramenta.getJTFNome().setText(ferramentaTeste.getNomeFerramenta());
        frmGerenciarFerramenta.getJTFCustoFerramenta().setText(String.valueOf(ferramentaTeste.getCustoFerramenta()));
        frmGerenciarFerramenta.getJTFMarca().setText(ferramentaTeste.getMarcaFerramenta());

        // Simula o clique no botão cadastrar via método do fake
        frmGerenciarFerramenta.clicarBotaoModificar();

        // Verifica se a mensagem exibida é a de sucesso
        assertEquals("Ferramenta atualizada com sucesso.", frmGerenciarFerramenta.getMensagem());
    }

    @AfterEach
    void finaliza() {
        FerramentaDAO ferramentadao = new FerramentaDAO();
        ferramentadao.deleteFerramentaDB(1);
    }
}
