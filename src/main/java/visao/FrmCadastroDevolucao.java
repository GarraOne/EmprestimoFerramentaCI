package visao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import modelo.Amigo;
import modelo.Emprestimo;
import modelo.Ferramenta;

public class FrmCadastroDevolucao extends javax.swing.JFrame {

    private transient Emprestimo emprestimo;
    private transient Amigo amigo;
    private transient Ferramenta ferramenta;

    private String mensagem;

    public FrmCadastroDevolucao() {
        initComponents();
        emprestimo = new Emprestimo();
        amigo = new Amigo();
        ferramenta = new Ferramenta();
        this.carregaCBEmprestimo();
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        comboBoxEmprestimo = new javax.swing.JComboBox<>();
        buttonCancelar = new javax.swing.JButton();
        buttonCadatrar = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro de Devolução");

        jLabel1.setText("Empréstimo:");

        comboBoxEmprestimo.setMaximumRowCount(999);

        buttonCancelar.setText("Cancelar");
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });

        buttonCadatrar.setText("Cadastrar");
        buttonCadatrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadatrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 226, Short.MAX_VALUE)
                        .addComponent(buttonCadatrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(comboBoxEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCancelar)
                    .addComponent(buttonCadatrar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCancelarActionPerformed

    private void buttonCadatrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadatrarActionPerformed

        int posicaoEmprestimo = comboBoxEmprestimo.getSelectedIndex();
        ArrayList<Emprestimo> listaEmprestimo = emprestimo.getListaEmprestimoAtivo();
        Emprestimo emp = new Emprestimo();
        String data = LocalDate.now() + "";
        String[] dataInvertida = data.split("-");
        data = dataInvertida[2] + "-" + dataInvertida[1] + "-" + dataInvertida[0];

        if (emp.updateEmprestimoDB(listaEmprestimo.get(posicaoEmprestimo).getIDEmprestimo(), listaEmprestimo.get(posicaoEmprestimo).getIDAmigo(), listaEmprestimo.get(posicaoEmprestimo).getIDFerramenta(), listaEmprestimo.get(posicaoEmprestimo).getDataEmprestimo(), data)) {
            mostrarMensagem("Devolucao cadastrada com sucesso.");
            comboBoxEmprestimo.removeAllItems();
            this.carregaCBEmprestimo();
        }
    }//GEN-LAST:event_buttonCadatrarActionPerformed

    public void carregaCBEmprestimo() {
        Emprestimo emp = new Emprestimo();
        ArrayList<Emprestimo> listaEmprestimo = emp.getListaEmprestimoAtivo();
        List<Amigo> listaAmigo = amigo.listaAmigo();
        ArrayList<Ferramenta> listaFerramenta = ferramenta.listaFerramenta();

        for (Emprestimo objeto : listaEmprestimo) {
            comboBoxEmprestimo.addItem(objeto.getIDEmprestimo() + "- " + listaAmigo.get(objeto.getIDAmigo() - 1).getNomeAmigo() + "- " + listaFerramenta.get(objeto.getIDFerramenta() - 1).getNomeFerramenta());
        }
    }

    public static void main(String args[]) {
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroDevolucao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroDevolucao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroDevolucao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroDevolucao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCadastroDevolucao().setVisible(true);
            }
        });
    }

    public void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }

    public JComboBox<String> getJCBEmprestimo() {
        return comboBoxEmprestimo;
    }

    protected javax.swing.JButton getJBCadastrar() {
        return this.buttonCadatrar;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCadatrar;
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JComboBox<String> comboBoxEmprestimo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
