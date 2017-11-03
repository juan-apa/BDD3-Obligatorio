/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafica.ventanas;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author juan
 */
public class MainWindow extends javax.swing.JFrame {

    private static JFrame ins_nin = null;
    private static JFrame ins_jug = null;
    private static JFrame list_nin = null;
    private static JFrame list_jug = null;
    private static JFrame dar_desc = null;
    private static JFrame del_nin_jug = null;

    public static void setIns_nin(JFrame ins_nin) {
        MainWindow.ins_nin = ins_nin;
    }

    public static void setIns_jug(JFrame ins_jug) {
        MainWindow.ins_jug = ins_jug;
    }

    public static void setList_nin(JFrame list_nin) {
        MainWindow.list_nin = list_nin;
    }

    public static void setList_jug(JFrame list_jug) {
        MainWindow.list_jug = list_jug;
    }

    public static void setDar_desc(JFrame dar_desc) {
        MainWindow.dar_desc = dar_desc;
    }

    public static void setDel_nin_jug(JFrame del_nin_jug) {
        MainWindow.del_nin_jug = del_nin_jug;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    /**
     * Creates new form mainWindow
     */
    public MainWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_ins_nin = new javax.swing.JButton();
        btn_ins_jug = new javax.swing.JButton();
        btn_list_nin = new javax.swing.JButton();
        btn_list_jug = new javax.swing.JButton();
        btn_dar_desc = new javax.swing.JButton();
        btn_del_nin_jug = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_ins_nin.setText("Ingresar niño");
        btn_ins_nin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ins_ninActionPerformed(evt);
            }
        });

        btn_ins_jug.setText("Ingresar juguete");
        btn_ins_jug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ins_jugActionPerformed(evt);
            }
        });

        btn_list_nin.setText("Listar niños");
        btn_list_nin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_list_ninActionPerformed(evt);
            }
        });

        btn_list_jug.setText("Listar juguetes");
        btn_list_jug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_list_jugActionPerformed(evt);
            }
        });

        btn_dar_desc.setText("Dar descripción");
        btn_dar_desc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dar_descActionPerformed(evt);
            }
        });

        btn_del_nin_jug.setText("Borrar niños y juguetes");
        btn_del_nin_jug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_del_nin_jugActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Juguetería");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_ins_nin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_ins_jug, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_list_nin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_list_jug, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_dar_desc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_del_nin_jug, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_ins_nin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_ins_jug)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_list_nin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_list_jug)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_dar_desc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_del_nin_jug)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ins_ninActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ins_ninActionPerformed
        if (ins_nin == null) {
            ins_nin = new InsertNinio();
            ins_nin.setVisible(true);
            ins_nin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ((Ventana) ins_nin).setParentWindow(this);
        }
    }//GEN-LAST:event_btn_ins_ninActionPerformed

    private void btn_ins_jugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ins_jugActionPerformed
        if (ins_jug == null) {
            ins_jug = new InsertJuguete();
            ins_jug.setVisible(true);
            ins_jug.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ((Ventana) ins_jug).setParentWindow(this);
        }
    }//GEN-LAST:event_btn_ins_jugActionPerformed

    private void btn_list_ninActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_list_ninActionPerformed
        if (list_nin == null) {
            list_nin = new ListarNinios();
            list_nin.setVisible(true);
            list_nin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ((Ventana) list_nin).setParentWindow(this);
        }
    }//GEN-LAST:event_btn_list_ninActionPerformed

    private void btn_list_jugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_list_jugActionPerformed
        if (list_jug == null) {
            list_jug = new ListarJuguetes();
            list_jug.setVisible(true);
            list_jug.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ((Ventana) list_jug).setParentWindow(this);
        }
    }//GEN-LAST:event_btn_list_jugActionPerformed

    private void btn_dar_descActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dar_descActionPerformed
        if (dar_desc == null) {
            dar_desc = new DarDescripcion();
            dar_desc.setVisible(true);
            dar_desc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ((Ventana) dar_desc).setParentWindow(this);
        }
    }//GEN-LAST:event_btn_dar_descActionPerformed

    private void btn_del_nin_jugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_del_nin_jugActionPerformed
        if (del_nin_jug == null) {
            del_nin_jug = new BorrarNinJug();
            del_nin_jug.setVisible(true);
            del_nin_jug.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ((Ventana) del_nin_jug).setParentWindow(this);
        }
    }//GEN-LAST:event_btn_del_nin_jugActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_dar_desc;
    private javax.swing.JButton btn_del_nin_jug;
    private javax.swing.JButton btn_ins_jug;
    private javax.swing.JButton btn_ins_nin;
    private javax.swing.JButton btn_list_jug;
    private javax.swing.JButton btn_list_nin;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
