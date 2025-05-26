package visao;

public class FrmCadastroFerramentaFake extends FrmCadastroFerramenta {

    /**
     * Inicializa os atributos.
     */
    public FrmCadastroFerramentaFake() {
        super();
    }

    @Override
    public void mostrarMensagem(String mensagem) {
        //Atribui a mensagem ao atributo para ser utilizado nos testes
        setMensagem(mensagem);

        //Mostra a mensagem
        System.out.println("Mensagem:" + mensagem);
    }

    public javax.swing.JTextField getJTFNomeFerramenta() {
        return super.getJTFNomeFerramenta();  // usa o getter da superclasse
    }

    public javax.swing.JTextField getJTFMarcaFerramenta() {
        return super.getJTFMarcaFerramenta();
    }

    public javax.swing.JTextField getJTFCustoFerramenta() {
        return super.getJTFCustoFerramenta();
    }

    public void clicarBotaoCadastrar() {
        super.getJBCadastrar().doClick();
    }

    public void clicarBotaoLimpar() {
        super.getJBLimpar().doClick();
    }

}
