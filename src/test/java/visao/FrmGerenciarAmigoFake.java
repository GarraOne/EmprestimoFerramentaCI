package visao;

public class FrmGerenciarAmigoFake extends FrmGerenciarAmigo {

    /**
     * Inicializa os atributos.
     */
    public FrmGerenciarAmigoFake() {
        super();
    }

    @Override
    public void mostrarMensagem(String mensagem) {
        //Atribui a mensagem ao atributo para ser utilizado nos testes
        setMensagem(mensagem);

        //Mostra a mensagem
        System.out.println("Mensagem:" + mensagem);
    }

    public void inicializarLista() {
        carregaListaAmigo();
    }

    public void selecionarLinha(int linha) {
        getJTableAmigos().setRowSelectionInterval(linha, linha);
    }

    public javax.swing.JTextField getJTFNome() {
        return super.getJTFNome();  // usa o getter da superclasse
    }

    public javax.swing.JTextField getJTFTelefone() {
        return super.getJTFTelefone();
    }

    public void clicarBotaoModificar() {
        super.getJBModificar().doClick();
    }

    public void clicarBotaoApagar() {
        super.getJBApagar().doClick();
    }
}
