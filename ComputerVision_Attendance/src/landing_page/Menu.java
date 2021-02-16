package landing_page;

import register.RegisterStudent;
import attendance.AttendanceCheck;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import register.RegisterEmployee;

public class Menu extends javax.swing.JFrame {

    public Menu(String user) {
        initComponents();
        txt_title_menu.setText("Welcome "+user);
        
        this.setResizable(false);
        
        //jPanel1.setPreferredSize(new Dimension(100,100));
        //setFullscreen(true);
    }
    
    /**
     * Method allows changing whether this window is displayed in fullscreen or
     * windowed mode.
     * @param fullscreen true = change to fullscreen,
     *                   false = change to windowed
     */
//    public void setFullscreen( boolean fullscreen )
//    {
//        //get a reference to the device.
//        GraphicsDevice device  = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//        DisplayMode dispMode = device.getDisplayMode();
//        //save the old display mode before changing it.
//        DisplayMode dispModeOld = device.getDisplayMode();
//        
//        device.setFullScreenWindow(this);
//
//        
//        //make sure that the screen is refreshed.
//        //repaint();
//        
//    }

    private Menu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_title_menu = new javax.swing.JLabel();
        jtbAllTab = new javax.swing.JTabbedPane();
        jpRegister = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(1, 1), new java.awt.Dimension(1, 1), new java.awt.Dimension(1, 1));
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnStudentRegister = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnEmployeeRegister = new javax.swing.JButton();
        jpAttendence = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jbAttendence = new javax.swing.JButton();
        jpData = new javax.swing.JPanel();
        jbData = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menu");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 960));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_title_menu.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txt_title_menu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_title_menu.setText("Welcome User");
        jPanel1.add(txt_title_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 470, 30));

        jtbAllTab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jtbAllTab.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jtbAllTab.setPreferredSize(new java.awt.Dimension(480, 240));

        jpRegister.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        jpRegister.setAlignmentX(0.0F);
        jpRegister.setAlignmentY(0.0F);
        jpRegister.add(jPanel2);
        jpRegister.add(filler1);

        jPanel8.setMinimumSize(new java.awt.Dimension(200, 100));
        jPanel8.setPreferredSize(new java.awt.Dimension(800, 60));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("Registration");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 75, 1, 1));
        jLabel3.setFocusable(false);
        jLabel3.setPreferredSize(new java.awt.Dimension(264, 29));
        jPanel8.add(jLabel3);

        jpRegister.add(jPanel8);
        jpRegister.add(filler2);

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 1, 1, 1));
        jPanel3.setMinimumSize(new java.awt.Dimension(210, 210));
        jPanel3.setPreferredSize(new java.awt.Dimension(600, 300));

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 31));
        jPanel4.setMinimumSize(new java.awt.Dimension(230, 230));
        jPanel4.setPreferredSize(new java.awt.Dimension(280, 200));

        btnStudentRegister.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnStudentRegister.setText("Student Register");
        btnStudentRegister.setAlignmentY(0.0F);
        btnStudentRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStudentRegister.setPreferredSize(new java.awt.Dimension(200, 170));
        btnStudentRegister.setSelected(true);
        btnStudentRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStudentRegisterActionPerformed(evt);
            }
        });
        jPanel4.add(btnStudentRegister);

        jPanel3.add(jPanel4);

        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 31, 1, 1));
        jPanel5.setMinimumSize(new java.awt.Dimension(200, 200));
        jPanel5.setPreferredSize(new java.awt.Dimension(280, 200));

        btnEmployeeRegister.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnEmployeeRegister.setText("Employee Register");
        btnEmployeeRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEmployeeRegister.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEmployeeRegister.setPreferredSize(new java.awt.Dimension(202, 170));
        btnEmployeeRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmployeeRegisterActionPerformed(evt);
            }
        });
        jPanel5.add(btnEmployeeRegister);

        jPanel3.add(jPanel5);

        jpRegister.add(jPanel3);

        jtbAllTab.addTab("Register", jpRegister);

        jPanel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(11, 11, 11, 11));
        jPanel7.setMinimumSize(new java.awt.Dimension(580, 150));
        jPanel7.setPreferredSize(new java.awt.Dimension(580, 100));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Attendence");
        jPanel7.add(jLabel2);

        jpAttendence.add(jPanel7);

        jPanel6.setMinimumSize(new java.awt.Dimension(280, 200));
        jPanel6.setPreferredSize(new java.awt.Dimension(280, 200));

        jbAttendence.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jbAttendence.setText("Start Attendence");
        jbAttendence.setPreferredSize(new java.awt.Dimension(200, 170));
        jbAttendence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAttendenceActionPerformed(evt);
            }
        });
        jPanel6.add(jbAttendence);

        jpAttendence.add(jPanel6);

        jtbAllTab.addTab("Attendence", jpAttendence);

        jbData.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jbData.setText("Data");
        jbData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDataActionPerformed(evt);
            }
        });
        jpData.add(jbData);

        jtbAllTab.addTab("Data", jpData);

        jPanel1.add(jtbAllTab, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 790, 430));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 790, 470));

        jButton1.setText("Logout");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 490, 100, 30));

        setSize(new java.awt.Dimension(817, 558));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnStudentRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudentRegisterActionPerformed
        new RegisterStudent().setVisible(true);
    }//GEN-LAST:event_btnStudentRegisterActionPerformed

    private void jbDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDataActionPerformed
        
    }//GEN-LAST:event_jbDataActionPerformed

    private void jbAttendenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAttendenceActionPerformed
        new AttendanceCheck().setVisible(true);
    }//GEN-LAST:event_jbAttendenceActionPerformed

    private void btnEmployeeRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmployeeRegisterActionPerformed
        new RegisterEmployee().setVisible(true);
    }//GEN-LAST:event_btnEmployeeRegisterActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEmployeeRegister;
    private javax.swing.JButton btnStudentRegister;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JButton jbAttendence;
    private javax.swing.JButton jbData;
    private javax.swing.JPanel jpAttendence;
    private javax.swing.JPanel jpData;
    private javax.swing.JPanel jpRegister;
    private javax.swing.JTabbedPane jtbAllTab;
    private javax.swing.JLabel txt_title_menu;
    // End of variables declaration//GEN-END:variables
}
