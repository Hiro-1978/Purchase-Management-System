
package purchase.management.system;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class FrmLogin extends javax.swing.JDialog {
    
    jh_center jh_center_cn = new jh_center();
    
    //Method
    private void Wprv_Login(){
        try {
            
            jh_center.sServer="localhost";
            jh_center.sDatabase="oms";
            jh_center.sUser="root";
            jh_center.sPass="root";
            
            if(txtUsername.getText().trim().equals("")){
                JOptionPane.showMessageDialog(rootPane, "กรุณากรอก Username","ตรวจสอบ",
                        JOptionPane.WARNING_MESSAGE,null);
                txtUsername.requestFocusInWindow();
                return;
            }            
            
            if(txtPassword.getText().trim().equals("")){
                JOptionPane.showMessageDialog(rootPane, "กรุณากรอlก Password","ตรวจสอบ",
                        JOptionPane.WARNING_MESSAGE,null);
                txtPassword.requestFocusInWindow();
                return;
            }
            
            String sSql=" select * From user Where username='"+txtUsername.getText().trim()+"' "
                    + " And password='"+txtPassword.getText().trim()+"' ";
            
            jh_center_cn=new jh_center();
            ResultSet rs=jh_center_cn.Cpuds_ShowData(sSql);
            if(rs.next()){
                jh_center.sUsername=rs.getString("username");
                this.dispose();
                MainMenu Frm=new MainMenu();
                Frm.setVisible(true);
            }else{
                jh_center.sUsername="";
                txtUsername.setText("");
                txtPassword.setText("");
                JOptionPane.showMessageDialog(rootPane, "UserName และ Password ไม่ถูกต้อง!!","ตรวจสอบ",
                        JOptionPane.ERROR_MESSAGE,null);
                return;
            }
            
        } catch (Exception e) {
               e.printStackTrace();                    
        }
    }
    
    public FrmLogin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        cmdLogin = new javax.swing.JButton();
        cmdClose = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(705, 285));
        setPreferredSize(new java.awt.Dimension(705, 285));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("Username :");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 100, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("Password :");
        jLabel2.setPreferredSize(new java.awt.Dimension(85, 20));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 100, 30));

        txtUsername.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtUsername.setPreferredSize(new java.awt.Dimension(6, 30));
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKeyPressed(evt);
            }
        });
        getContentPane().add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 188, -1));

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtPassword.setPreferredSize(new java.awt.Dimension(6, 30));
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 188, -1));

        cmdLogin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmdLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/login.png"))); // NOI18N
        cmdLogin.setText("Login");
        cmdLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLoginActionPerformed(evt);
            }
        });
        getContentPane().add(cmdLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, -1, -1));

        cmdClose.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmdClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/close Jframe.png"))); // NOI18N
        cmdClose.setText("Exit");
        cmdClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCloseActionPerformed(evt);
            }
        });
        getContentPane().add(cmdClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, 83, -1));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Login-เปิ้ล-เปิ้ล.png"))); // NOI18N
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setPreferredSize(new java.awt.Dimension(705, 285));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 270));

        setBounds(0, 0, 723, 317);
    }// </editor-fold>//GEN-END:initComponents

    private void cmdLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLoginActionPerformed
    Wprv_Login();
    }//GEN-LAST:event_cmdLoginActionPerformed

    private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_cmdCloseActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Dimension dimSc=Toolkit.getDefaultToolkit().getScreenSize();
        int nWidth=this.getSize().width;
        int n=this.getSize().width;
        int nHeight = this.getSize().height;        
        int nScreenX = (dimSc.width - nWidth) / 2;
        int nScreenY = ((dimSc.height - nHeight) / 2) - 120;
        this.setLocation(nScreenX, nScreenY);
        this.setVisible(true);
    }//GEN-LAST:event_formWindowOpened

    private void txtUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyPressed
        if (KeyEvent.VK_ENTER == evt.getKeyCode()) {
            Wprv_Login();          
        }
    }//GEN-LAST:event_txtUsernameKeyPressed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            Wprv_Login();
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmLogin dialog = new FrmLogin(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdClose;
    private javax.swing.JButton cmdLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
