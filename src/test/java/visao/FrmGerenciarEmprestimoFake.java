package visao;

import javax.swing.JOptionPane;

public class FrmGerenciarEmprestimoFake extends FrmGerenciarEmprestimo {

    /**
     * Inicializa os atributos.
     */
    public FrmGerenciarEmprestimoFake() {
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
    public int confirmarApagarEmprestimo() {
        return JOptionPane.YES_OPTION; // Simula clique no "Sim"
    }

    public void inicializarLista() {
        carregaListaEmprestimo();
    }

    public void selecionarLinha(int linha) {
        getJTableEmprestimo().setRowSelectionInterval(linha, linha);
    }

    @Override
    public javax.swing.JTextField getJTFIdAmigo() {
        return super.getJTFIdAmigo();  // usa o getter da superclasse
    }

    @Override
    public javax.swing.JTextField getJTFIdFerramenta() {
        return super.getJTFIdFerramenta();
    }

    @Override
    public javax.swing.JTextField getJTFDataEmprestimo() {
        return super.getJTFDataEmprestimo();
    }

    @Override
    public javax.swing.JTextField getJTFDataDevolucao() {
        return super.getJTFDataDevolucao();
    }

    public void clicarBotaoModificar() {
        super.getJBModificar().doClick();
    }

    public void clicarBotaoApagar() {
        super.getJBApagar().doClick();
    }
}
