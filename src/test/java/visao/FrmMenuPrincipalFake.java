package visao;

public class FrmMenuPrincipalFake extends FrmMenuPrincipal {
        public boolean encerramentoChamado = false;
        
        @Override
        protected void encerrarAplicacao() {
            encerramentoChamado = true;
        }
    }