package visao;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

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

    @Override
    public int confirmarCadastrarAmigoComEmprestimo() {
        return JOptionPane.YES_OPTION; // Simula clique no "Sim"
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
