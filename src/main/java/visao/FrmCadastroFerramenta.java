package visao;

import javax.swing.JOptionPane;
import service.FerramentaService;

public class FrmCadastroFerramenta extends javax.swing.JFrame {

    /**
     * Creates new form FrmCadastroFerramenta
     */
    private transient FerramentaService ferramentaService = new FerramentaService();
    private String mensagem;

    public FrmCadastroFerramenta() {
        initComponents();
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelNomeFerramenta = new javax.swing.JLabel();
        labelMarcaFerramenta = new javax.swing.JLabel();
        labelCustoFeramenta = new javax.swing.JLabel();
        textNomeFerramenta = new javax.swing.JTextField();
        textMarcaFerramenta = new javax.swing.JTextField();
        textCustoFerramenta = new javax.swing.JTextField();
        buttonCadastrar = new javax.swing.JButton();
        buttonLimpar = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro de Ferramentas");

        labelNomeFerramenta.setText("Nome da Ferramenta:");

        labelMarcaFerramenta.setText("Marca da Ferramenta:");

        labelCustoFeramenta.setText("Custo de Obtenção:");

        textNomeFerramenta.addActionListener(this::textNomeFerramentaActionPerformed);
        
        buttonCadastrar.setText("Cadastrar");
        buttonCadastrar.addActionListener(this::buttonCadastrarActionPerformed);
        
        buttonLimpar.setText("Limpar");
        buttonLimpar.addActionListener(this::buttonLimparActionPerformed);
        
        jBCancelar.setText("Cancelar");
        jBCancelar.addActionListener(this::jBCancelarActionPerformed);


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(textNomeFerramenta, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 154, Short.MAX_VALUE)
                        .addComponent(buttonCadastrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonLimpar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBCancelar)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNomeFerramenta)
                    .addComponent(labelMarcaFerramenta)
                    .addComponent(labelCustoFeramenta)
                    .addComponent(textCustoFerramenta, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textMarcaFerramenta, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(labelNomeFerramenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textNomeFerramenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMarcaFerramenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textMarcaFerramenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCustoFeramenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textCustoFerramenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBCancelar)
                    .addComponent(buttonLimpar)
                    .addComponent(buttonCadastrar))
                .addGap(8, 8, 8))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textNomeFerramentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNomeFerramentaActionPerformed
        if (evt == null) return;

    }//GEN-LAST:event_textNomeFerramentaActionPerformed

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        //Libera todos os recurso da interface gráfica
        if (evt == null) return;

        this.dispose();
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void buttonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrarActionPerformed
        if (evt == null) return;

        try {

            String nome = "";
            String marca = "";
            double custo = 0;
            if (textNomeFerramenta.getText().length() < 2) {
                mostrarMensagem("Ferramenta Invalida.");
                throw new Erro("Nome deve conter pelo menos 2 caracteres, tente novamente.");
            } else {
                nome = (textNomeFerramenta.getText());

            }
            if (textMarcaFerramenta.getText().length() < 2) {
                mostrarMensagem("Marca Invalida.");
                throw new Erro("Marca deve conter pelo menos 2 caracteres, tente novamente.");
            } else {
                marca = (textMarcaFerramenta.getText());

            }
            if (Double.parseDouble(textCustoFerramenta.getText()) <= 0) {
                mostrarMensagem("Custo Invalido.");
                throw new Erro("Custo deve ser maior que 0, tente novamente.");
            } else {
                custo = (Double.parseDouble(textCustoFerramenta.getText()));

            }
            if (ferramentaService.insertFerramentaDB(nome, marca, custo)) {
                mostrarMensagem("Ferramenta cadastrada com sucesso.");
                textMarcaFerramenta.setText("");
                textNomeFerramenta.setText("");
                textCustoFerramenta.setText("");
            }
        } catch (Erro erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }//GEN-LAST:event_buttonCadastrarActionPerformed

    private void buttonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimparActionPerformed
        if (evt == null) return;

        textMarcaFerramenta.setText("");
        textNomeFerramenta.setText("");
        textCustoFerramenta.setText("");
    }//GEN-LAST:event_buttonLimparActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroFerramenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrmCadastroFerramenta().setVisible(true));

    }

    public void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }

    protected javax.swing.JTextField getJTFNomeFerramenta() {
        return this.textNomeFerramenta;  // acesso direto porque está dentro da classe
    }

    protected javax.swing.JTextField getJTFMarcaFerramenta() {
        return this.textMarcaFerramenta;
    }

    protected javax.swing.JTextField getJTFCustoFerramenta() {
        return this.textCustoFerramenta;
    }

    protected javax.swing.JButton getJBCadastrar() {
        return this.buttonCadastrar;
    }

    protected javax.swing.JButton getJBLimpar() {
        return this.buttonLimpar;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCadastrar;
    private javax.swing.JButton buttonLimpar;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JLabel labelCustoFeramenta;
    private javax.swing.JLabel labelMarcaFerramenta;
    private javax.swing.JLabel labelNomeFerramenta;
    private javax.swing.JTextField textCustoFerramenta;
    private javax.swing.JTextField textMarcaFerramenta;
    private javax.swing.JTextField textNomeFerramenta;
    // End of variables declaration//GEN-END:variables
}
