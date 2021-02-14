package register;

import db_connection.DB_Connection;
import java.sql.SQLException;

public class RegisterStudent extends javax.swing.JFrame {

    int id = 1;
    DB_Connection dbConnection = new DB_Connection();

    public RegisterStudent() {
        initComponents();
        setIdForRegister();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txt_id_label = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txt_first_name = new javax.swing.JTextField();
        txt_last_name = new javax.swing.JTextField();
        txt_office = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txt_dob = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Register Person");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_id_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_id_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_id_label.setText("1");
        jPanel2.add(txt_id_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 320, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 340, 40));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(txt_first_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 120, -1));
        jPanel3.add(txt_last_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 130, -1));
        jPanel3.add(txt_office, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 130, -1));

        jLabel2.setText("Last Name");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, -1, -1));

        jLabel3.setText("Office");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, -1, -1));

        jLabel4.setText("First Name");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel5.setText("Dob");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jButton1.setText("Next");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, -1, -1));

        try {
            txt_dob.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel3.add(txt_dob, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 120, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 340, 200));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 380, 280));

        setSize(new java.awt.Dimension(416, 339));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String first_name = txt_first_name.getText();
        String last_name = txt_last_name.getText();

        int student_class = 4;
        String class_section = "a";

        int id = Integer.parseInt(txt_id_label.getText());
        new CaptureFace(id, first_name, last_name, student_class, class_section).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegisterStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JFormattedTextField txt_dob;
    private javax.swing.JTextField txt_first_name;
    private javax.swing.JLabel txt_id_label;
    private javax.swing.JTextField txt_last_name;
    private javax.swing.JTextField txt_office;
    // End of variables declaration//GEN-END:variables

    private void setIdForRegister() {
        dbConnection.connectDatabase();
        if (dbConnection.ifTableExists("student")) {
            dbConnection.executesql("SELECT id FROM student ORDER BY id DESC ");
        } else {
            dbConnection.createTable("student");
            dbConnection.executesql("SELECT id FROM student ORDER BY id DESC ");
        }
        try {
            if (dbConnection.resultSet != null) {
                dbConnection.resultSet.first();
                txt_id_label.setText(String.valueOf(dbConnection.resultSet.getInt("id")));
                id = Integer.parseInt(txt_id_label.getText());
                id++;
                txt_id_label.setText(String.valueOf(id));
            }else{
                txt_id_label.setText(String.valueOf(id));
            }
        } catch (NumberFormatException | SQLException e) {
            System.out.println(e);
        }
    }
}
