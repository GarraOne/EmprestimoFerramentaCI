package principal;

import visao.FrmMenuPrincipal;

/**
 * Classe principal do aplicativo que inicia a interface gráfica do usuário.
 */
public class Principal {

    /**
     * Método principal que inicia o aplicativo.
     *
     * @param args Argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        FrmMenuPrincipal objetotela = new FrmMenuPrincipal();
        objetotela.setVisible(true);
    }
}
