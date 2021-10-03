package purchase.management.system;

import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class FrmUnit_Save extends javax.swing.JDialog {

    public String psAction = "";
    public String psCode = "";
    public boolean pbAction = false;
    jh_center jh_center_cn = new jh_center();

    // Method
    // Lock Tool
    private void Wprv_LockTool(boolean _bCheck) {
        txtCode.setEditable(_bCheck);
        txtNameThai.setEditable(_bCheck);
    }

    // Show Data
    private void Wprv_ShowDB() {
        try {
            txtCode.setText(psCode.trim());
            String sSql = "Select * from unit where unitcode='" + txtCode.getText().trim() + "'";
            ResultSet rs = jh_center_cn.Cpuds_ShowData(sSql);
            if (rs.next()) {
                txtCode.setText(rs.getString("unitcode").toString());
                txtNameThai.setText(rs.getString("nthai").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //AutoRun
    /*private void Wprv_AutoRun() {
        String sCode = "";
        try {
            sCode = jh_center_cn.Cpus_AutoRun("Code", "unit", "U-", "00000"); // U-00001
            txtCode.setText(sCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //txtCode.setText(sCode);
    }*/

    public FrmUnit_Save(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabMenu = new javax.swing.JToolBar();
        cmdClose = new javax.swing.JButton();
        cmdSave = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        txtNameThai = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("เพิ่มหน่วยไอเทม");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("รหัสหน่วย :");

        txtCode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCode.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        txtNameThai.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNameThai.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNameThai.setPreferredSize(new java.awt.Dimension(69, 23));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("ชื่อหน่วยภาษาไทย :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNameThai, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNameThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(0, 36, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed
        pbAction = false;
        this.dispose();
    }//GEN-LAST:event_cmdCloseActionPerformed

    private void cmdSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveActionPerformed
        if (txtCode.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "กรุณากรอก รหัสหน่วย", "ตรวจสอบ", 
                    JOptionPane.WARNING_MESSAGE, null);
            return;
        }
        
        if (txtNameThai.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "กรุณากรอก ชื่อหน่วยภาษาไทย", "ตรวจสอบ", 
                    JOptionPane.WARNING_MESSAGE, null);
            return;
        }

        String sSql = "";
        String sTitle = "";
        if (psAction.toUpperCase().endsWith("ADD")) {
            sTitle = "เพิ่มข้อมูล";
            //Wprv_AutoRun();
            sSql = "Insert Into unit(unitcode,nthai) Values('" + txtCode.getText().toUpperCase().trim() + "','" + txtNameThai.getText().trim() + "')";
        } else if (psAction.toUpperCase().endsWith("EDIT")) {
            sTitle = "แก้ไขข้อมูล";
            if (JOptionPane.showConfirmDialog(null, "คุณต้องการแก้ไขข้อมูล ใช่หรือไม่", "ตรวจสอบ", 
                    JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                return;
            }
            sSql = "Update unit Set nthai= '" + txtNameThai.getText().trim() + "' "
                    + " where unitcode='" + txtCode.getText().trim() + "'";
        } else if (psAction.toUpperCase().endsWith("DELETE")) {
            // ตรวจสอบก่อนว่ามีการเอาข้อมูล UnitCode ไปใช้งานก่อนแล้วหรือยัง ถ้าใช้แล้วจะลบไม่ได้
            try {
                if (jh_center_cn.Cpub_CheckValueData("item", "unitcode", txtCode.getText().trim()) == true) {
                    JOptionPane.showMessageDialog(rootPane, "หน่วยนี้ถูกใช้งานไปแล้ว ยกเลิกไม่ได้", "ตรวจสอบ", 
                            JOptionPane.WARNING_MESSAGE, null);
                    return;
                }
                sTitle = "ยกเลิกข้อมูล";
                if (JOptionPane.showConfirmDialog(null, "คุณต้องการยกเลิกหน่วยนี้ ใช่หรือไม่", "ตรวจสอบ", 
                        JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                    return;
                }

                sSql = "Delete from unit where unitcode='" + txtCode.getText().trim() + "'";

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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        pbAction = false;

        txtCode.setEditable(true);
        txtCode.requestFocusInWindow();
        cmdSave.setEnabled(true);

        if (psAction.toUpperCase().endsWith("ADD")) {
            //Wprv_AutoRun();
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

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmUnit_Save dialog = new FrmUnit_Save(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JToolBar tabMenu;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtNameThai;
    // End of variables declaration//GEN-END:variables
}
