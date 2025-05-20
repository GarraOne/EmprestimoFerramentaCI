package visao;

import dao.AmigoDAO;
import modelo.Amigo;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class TesteFrmCadrastroAmigo {

    //Objeto amigo a ser inserido
    Amigo amigoTeste;
    //Formulário fake para inclusão dos dados
    FrmCadastroAmigoFake frmCadastroAmigo;

    /**
     * Inicializa os objetos para o teste.
     */
    
    @BeforeEach
    public void inicializa() {
        //Dados de teste de inclusão
        amigoTeste = new Amigo(1, "Joao", "12345678");
        //Instância o formulário fake
        frmCadastroAmigo = new FrmCadastroAmigoFake();
        //Instância o controle do formulário
    }

    @Test
    public void testCadastroValido() {
        // Preenche os campos do formulário via getters do fake
        frmCadastroAmigo.getJTFNomeAmigo().setText(amigoTeste.getNomeAmigo());
        frmCadastroAmigo.getJTFTelefone().setText(amigoTeste.getTelefone());

        // Simula o clique no botão cadastrar via método do fake
        frmCadastroAmigo.clicarBotaoCadastrar();

        // Verifica se a mensagem exibida é a de sucesso
        assertEquals("Amigo cadastrado com sucesso.", frmCadastroAmigo.getMensagem());
    }
@AfterEach
void finaliza(){
    AmigoDAO amigodao = new AmigoDAO();
    amigodao.deleteAmigoDB(1);
}
}
