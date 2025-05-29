package visao;

public class FrmCadastroAmigoFake extends FrmCadastroAmigo {

    /**
     * Inicializa os atributos.
     */
    public FrmCadastroAmigoFake() {
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
    public javax.swing.JTextField getJTFNomeAmigo() {
        return super.getJTFNomeAmigo();  // usa o getter da superclasse
    }

    @Override
    public javax.swing.JTextField getJTFTelefone() {
        return super.getJTFTelefone();
    }

    public void clicarBotaoCadastrar() {
        super.getJBCadastrar().doClick();
    }

    public void clicarBotaoLimpar() {
        super.getJBLimpar().doClick();
    }

}
