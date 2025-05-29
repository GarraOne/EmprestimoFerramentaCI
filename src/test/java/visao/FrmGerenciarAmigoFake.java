package visao;

import javax.swing.JOptionPane;

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

    @Override
    public int confirmarApagarAmigo() {
        return JOptionPane.YES_OPTION; // Simula clique no "Sim"
    }

    public void inicializarLista() {
        carregaListaAmigo();
    }

    public void selecionarLinha(int linha) {
        getJTableAmigos().setRowSelectionInterval(linha, linha);
    }

    @Override
    public javax.swing.JTextField getJTFNome() {
        return super.getJTFNome();  // usa o getter da superclasse
    }

    @Override
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
