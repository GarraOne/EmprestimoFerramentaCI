package visao;

import javax.swing.JComboBox;

public class FrmCadastroEmprestimoFake extends FrmCadastroEmprestimo {

    /**
     * Inicializa os atributos.
     */
    public FrmCadastroEmprestimoFake() {
        super();
    }

    @Override
    public void mostrarMensagem(String mensagem) {
        //Atribui a mensagem ao atributo para ser utilizado nos testes
        setMensagem(mensagem);

        //Mostra a mensagem
        System.out.println("Mensagem:" + mensagem);
    }

    public void inicializarCombos() {
        carregaCBAmigo();
        carregaCBFerramenta();
    }

    public void clicarBotaoCadastrar() {
        super.getJBCadastrar().doClick();
    }

    public JComboBox<String> getJCBAmigo() {
        return super.getJCBAmigo();
    }

    public JComboBox<String> getJCBFerramenta() {
        return super.getJCBFerramenta();
    }
}
