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
}
