package purchase.management.system;

import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Hiro
 */
public class FrmVender_Save extends javax.swing.JDialog {

    public String psAction = "";
    public String psCode = "";
    public boolean pbAction = false;
    jh_center jh_center_cn = new jh_center();

    // Method
    // Lock Tool
    private void Wprv_LockTool(boolean _bCheck) {
        txtVName.setEditable(_bCheck);
        txtSName.setEditable(_bCheck);
        txtTel.setEditable(_bCheck);
    }

    // Show Data
    private void Wprv_ShowDB() {
        try {
            txtCode.setText(psCode.trim());
            String sSql = "Select * from vender where venderid='" + txtCode.getText().trim() + "'";
            ResultSet rs = jh_center_cn.Cpuds_ShowData(sSql);
            if (rs.next()) {
                txtVName.setText(rs.getString("vname").toString());
                txtSName.setText(rs.getString("sname").toString());
                txtTel.setText(rs.getString("tel").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //AutoRun
    private void Wprv_AutoRun() {
        String sCode = "";
        try {
            sCode = jh_center_cn.Cpus_AutoRun("venderid", "vender", "V-", "000"); // V-001
            txtCode.setText(sCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FrmVender_Save(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtVName = new javax.swing.JTextField();
        txtCode = new javax.swing.JTextField();
        tabMenu = new javax.swing.JToolBar();
        cmdClose = new javax.swing.JButton();
        cmdSave = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtSName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTel = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("บันทึกบริษัทตัวแทนจำหน่าย");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("ชื่อบริษัท :");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("รหัสบริษัท :");

        txtVName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtVName.setPreferredSize(new java.awt.Dimension(6, 29));

        txtCode.setEditable(false);
        txtCode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCode.setPreferredSize(new java.awt.Dimension(6, 29));

        tabMenu.setFloatable(false);
        tabMenu.setRollover(true);

        cmdClose.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        cmdClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/exit20x20.png"))); // NOI18N
        cmdClose.setText("ปิด");
        cmdClose.setFocusable(false);
        cmdClose.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCloseActionPerformed(evt);
            }
        });
        tabMenu.add(cmdClose);

        cmdSave.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        cmdSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save20x20.png"))); // NOI18N
        cmdSave.setText("บันทึก");
        cmdSave.setFocusable(false);
        cmdSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSaveActionPerformed(evt);
            }
        });
        tabMenu.add(cmdSave);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("เซลล์ :");

        txtSName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSName.setPreferredSize(new java.awt.Dimension(69, 29));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("เบอร์โทร :");

        txtTel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTel.setPreferredSize(new java.awt.Dimension(69, 29));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtVName, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtVName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed
        pbAction = false;
        this.dispose();
    }//GEN-LAST:event_cmdCloseActionPerformed

    private void cmdSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveActionPerformed
        if (txtCode.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "กรุณากรอก รหัสบริษัท", "ตรวจสอบ",
                    JOptionPane.WARNING_MESSAGE, null);
            return;
        }

        if (txtVName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "กรุณากรอก ชื่อบริษัท", "ตรวจสอบ",
                    JOptionPane.WARNING_MESSAGE, null);
            return;
        }
        
        if (txtSName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "กรุณากรอก ชื่อเซลล์", "ตรวจสอบ",
                    JOptionPane.WARNING_MESSAGE, null);
            return;
        }

        if (txtTel.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "กรุณากรอก หมายเลขโทรศัพท์", "ตรวจสอบ",
                    JOptionPane.WARNING_MESSAGE, null);
            return;
        }
        String sSql = "";
        String sTitle = "";
        if (psAction.toUpperCase().endsWith("ADD")) {
            sTitle = "เพิ่มข้อมูล";
            Wprv_AutoRun();
            sSql = "Insert Into vender(venderid,vname,sname,tel) Values('" + txtCode.getText().trim() + "', "
                    + " '" + txtVName.getText().trim() + "', "
                    + " '" + txtSName.getText().trim() + "', "
                    + " '" + txtTel.getText().trim() + "') ";
            //System.out.println(sSql);
        } else if (psAction.toUpperCase().endsWith("EDIT")) {
            sTitle = "แก้ไขข้อมูล";
            if (JOptionPane.showConfirmDialog(null, "คุณต้องการแก้ไขข้อมูล ใช่หรือไม่", "ตรวจสอบ",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                return;
            }
            sSql = "Update vender Set vname='" + txtVName.getText().trim() + "', "
                    + " sname='" + txtSName.getText().trim() + "', "
                    + " tel='" + txtTel.getText().trim() + "' "
                    + " where venderid='" + txtCode.getText().trim() + "' ";
           
            //System.out.println(sSql);
            updateDochead(); 
            updateStockMgnt();
            
        } else if (psAction.toUpperCase().endsWith("DELETE")) {
            // ตรวจสอบก่อนว่ามีการเอาข้อมูล UnitCode ไปใช้งานก่อนแล้วหรือยัง ถ้าใช้แล้วจะลบไม่ได้
            try {
                if (jh_center_cn.Cpub_CheckValueData("dochead", "venderid", txtCode.getText().trim()) == true) {
                    JOptionPane.showMessageDialog(rootPane, "รหัสนี้ถูกใช้งานไปแล้ว ยกเลิกไม่ได้", "ตรวจสอบ",
                            JOptionPane.WARNING_MESSAGE, null);
                    return;
                }
                sTitle = "ยกเลิกข้อมูล";
                if (JOptionPane.showConfirmDialog(null, "คุณต้องการยกเลิกข้อมูล ใช่หรือไม่", "ตรวจสอบ",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                    return;
                }

                sSql = "Delete from vender where venderid='" + txtCode.getText().trim() + "'";

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (jh_center_cn.Cpub_ActonDB(sSql) == true) {
            JOptionPane.showMessageDialog(rootPane, sTitle + " เรียบร้อยแล้ว", "ตรวจสอบ",
                    JOptionPane.INFORMATION_MESSAGE, null);
            pbAction = true;
            this.dispose();
        } else {
            return;
        }
    }//GEN-LAST:event_cmdSaveActionPerformed
    
    private void updateDochead(){
            String sSql = "";
            sSql = "Update dochead Set vname='" + txtVName.getText().trim() + "' "
                    + " where venderid='" + txtCode.getText().trim() + "' ";   
          
            jh_center_cn.Cpub_ActonDB(sSql);
            System.out.println(sSql);
    }
    
    private void updateStockMgnt(){
            String sSql = "";
            sSql = "Update stockmgnt Set vname='" + txtVName.getText().trim() + "' "
                    + " where venderid='" + txtCode.getText().trim() + "' ";   
          
            jh_center_cn.Cpub_ActonDB(sSql);
            System.out.println(sSql);
    }
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        pbAction = false;

        txtCode.setEditable(false);
        cmdSave.setEnabled(true);

        if (psAction.toUpperCase().endsWith("ADD")) {
            Wprv_AutoRun();
            Wprv_LockTool(true);
        } else if (psAction.toUpperCase().endsWith("EDIT")) {
            Wprv_ShowDB();
            Wprv_LockTool(true);
        } else if (psAction.toUpperCase().endsWith("DELETE")) {
            Wprv_ShowDB();
            Wprv_LockTool(false);
        } else if (psAction.toUpperCase().endsWith("VIEW")) {
            Wprv_ShowDB();
            Wprv_LockTool(false);
            cmdSave.setEnabled(false);
        } else {
            cmdSave.setEnabled(false);
        }
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting Code (optional) ">
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
            java.util.logging.Logger.getLogger(FrmVender_Save.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmVender_Save.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmVender_Save.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmVender_Save.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmVender_Save dialog = new FrmVender_Save(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton cmdSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JToolBar tabMenu;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtSName;
    private javax.swing.JTextField txtTel;
    private javax.swing.JTextField txtVName;
    // End of variables declaration//GEN-END:variables
}
