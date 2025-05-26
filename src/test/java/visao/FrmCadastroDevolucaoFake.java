package visao;

import javax.swing.JComboBox;

public class FrmCadastroDevolucaoFake extends FrmCadastroDevolucao {

    /**
     * Inicializa os atributos.
     */
    public FrmCadastroDevolucaoFake() {
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
        carregaCBEmprestimo();
    }

    public JComboBox<String> getJCBEmprestimo() {
        return super.getJCBEmprestimo();
    }

    public void clicarBotaoCadastrar() {
        super.getJBCadastrar().doClick();
    }

}
