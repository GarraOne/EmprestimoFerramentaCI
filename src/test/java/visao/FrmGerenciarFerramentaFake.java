package visao;

import javax.swing.JOptionPane;

public class FrmGerenciarFerramentaFake extends FrmGerenciarFerramenta {

    /**
     * Inicializa os atributos.
     */
    public FrmGerenciarFerramentaFake() {
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
    public int confirmarApagarFerramenta() {
        return JOptionPane.YES_OPTION; // Simula clique no "Sim"
    }

    public void inicializarLista() {
        CarregaListaFerramenta();
    }

    public void selecionarLinha(int linha) {
        getJTableAmigos().setRowSelectionInterval(linha, linha);
    }

    public javax.swing.JTextField getJTFNome() {
        return super.getJTFNome();  // usa o getter da superclasse
    }

    public javax.swing.JTextField getJTFCustoFerramenta() {
        return super.getJTFCustoFerramenta();
    }

    public javax.swing.JTextField getJTFMarca() {
        return super.getJTFMarca();
    }

    public void clicarBotaoModificar() {
        super.getJBModificar().doClick();
    }

    public void clicarBotaoApagar() {
        super.getJBApagar().doClick();
    }
}
