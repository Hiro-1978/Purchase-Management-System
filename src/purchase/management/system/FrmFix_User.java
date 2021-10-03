package purchase.management.system;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class FrmFix_User extends javax.swing.JInternalFrame {

    jh_center jh_center_cn = new jh_center();
    String sAction = "";
    private DefaultTableModel tableModel = new DefaultTableModel();
    private Object[] data = new Object[0];
    private String sCode = "";

    //Method
    //Clear Grid
    private void Wprv_ClearDataGrid() {
        int iRow = dgvShow.getRowCount() - 1;
        while (iRow > -1) {
            dgvShow.setValueAt("", iRow, 0);
            dgvShow.setValueAt("", iRow, 1);
            iRow--;
        }
    }

    //Clear Text
    private void Wprv_Clear() {
        txtUsername.setText("");
        txtPassword.setText("");
    }

    //Show Data
    private void Wprv_ShowData() {
        try {
            Wprv_ClearDataGrid();

            String sSqlShow = " Select * From user ";
            ResultSet rs = jh_center_cn.Cpuds_ShowData(sSqlShow);
            int iRow = 0;
            while (rs.next()) {
                String sUser = rs.getString("username");
                String sPass = rs.getString("password");

                dgvShow.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
                dgvShow.getTableHeader().setForeground(new Color(255, 255, 255));
                dgvShow.getTableHeader().setBackground(new Color(0, 0, 102));
                
                dgvShow.setValueAt(sUser, iRow, 0);
                dgvShow.setValueAt(sPass, iRow, 1);
                iRow++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Save Data
    private void Wprv_SaveData() {

        if (txtUsername.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "กรุณากรอก Username", "ตรวจสอบ",
                    JOptionPane.WARNING_MESSAGE, null);
            return;
        }

        if (txtPassword.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "กรุณากรอก Password", "ตรวจสอบ",
                    JOptionPane.WARNING_MESSAGE, null);
            return;
        }

        String sSql = "";
        String sTitle = "";
        if (sAction.toUpperCase().endsWith("ADD")) {
            sTitle = "เพิ่มข้อมูล";
            sSql = "Insert Into user(username,password) Values('" + txtUsername.getText().trim() + "','" + txtPassword.getText().trim() + "')";
        } else if (sAction.toUpperCase().endsWith("EDIT")) {
            sTitle = "แก้ไขข้อมูล";
            if (JOptionPane.showConfirmDialog(null, "คุณต้องการแก้ไขข้อมูล ใช่หรือไม่", "ตรวจสอบ",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                return;
            }
            sSql = " Update user Set password = '" + txtPassword.getText().trim() + "' Where username='" + txtUsername.getText().trim() + "' ";
        } else if (sAction.toUpperCase().endsWith("DELETE")) {
            // ตรวจสอบก่อนว่ามีการเอาข้อมูล Unitcode ไปใช้งานก่อนแล้วหรือยัง ถ้าใช้แล้วจะลบไม่ได้
            try {
            
                sTitle = "ลบข้อมูล";
                if (JOptionPane.showConfirmDialog(null, "คุณต้องการลบข้อมูล ใช่หรือไม่", "ตรวจสอบ",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                    return;
                }

                sSql = "Delete from user where username='" + txtUsername.getText().trim() + "'";

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (jh_center_cn.Cpub_ActonDB(sSql) == true) {
            JOptionPane.showMessageDialog(rootPane, sTitle + " เรียบร้อยแล้ว", "ตรวจสอบ",
                    JOptionPane.INFORMATION_MESSAGE, null);
        } else {
            return;
        }

        Wprv_Clear();
        Wprv_ShowData();

    }

    public FrmFix_User() {
        initComponents();

        setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
        dgvShow.setAutoResizeMode(dgvShow.AUTO_RESIZE_ALL_COLUMNS);

        TableColumn col1 = dgvShow.getColumnModel().getColumn(0);
        col1.setPreferredWidth(100);

        TableColumn col2 = dgvShow.getColumnModel().getColumn(1);
        col2.setPreferredWidth(180);

        Wprv_ShowData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        cmdClose = new javax.swing.JButton();
        cmdAdd = new javax.swing.JButton();
        cmdEdit = new javax.swing.JButton();
        cmdDelete = new javax.swing.JButton();
        cmdClear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        dgvShow = new javax.swing.JTable();

        setTitle("กำหนดสิทธิ์การใช้งาน");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        cmdClose.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        cmdClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/exit20x20.png"))); // NOI18N
        cmdClose.setText("ปิด");
        cmdClose.setFocusable(false);
        cmdClose.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCloseActionPerformed(evt);
            }
        });
        jToolBar1.add(cmdClose);

        cmdAdd.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        cmdAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add.png"))); // NOI18N
        cmdAdd.setText("เพิ่มช้อมูล");
        cmdAdd.setFocusable(false);
        cmdAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });
        jToolBar1.add(cmdAdd);

        cmdEdit.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        cmdEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Edit20x20.png"))); // NOI18N
        cmdEdit.setText("แก้ไขข้อมูล");
        cmdEdit.setFocusable(false);
        cmdEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditActionPerformed(evt);
            }
        });
        jToolBar1.add(cmdEdit);

        cmdDelete.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        cmdDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/close Jframe.png"))); // NOI18N
        cmdDelete.setText("ลบข้อมูล");
        cmdDelete.setFocusable(false);
        cmdDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteActionPerformed(evt);
            }
        });
        jToolBar1.add(cmdDelete);

        cmdClear.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        cmdClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/clearlist20x20.png"))); // NOI18N
        cmdClear.setText("ล้างข้อมูล");
        cmdClear.setFocusable(false);
        cmdClear.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdClearActionPerformed(evt);
            }
        });
        jToolBar1.add(cmdClear);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("๊Username :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Password :");

        txtUsername.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jScrollPane1.setPreferredSize(new java.awt.Dimension(270, 402));

        dgvShow.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dgvShow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Username", "Password"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dgvShow.setPreferredSize(new java.awt.Dimension(252, 275));
        dgvShow.setRowHeight(20);
        dgvShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dgvShowMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dgvShow);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtUsername)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 408, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(23, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 136, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        jh_page.bPageFix = false;
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        jh_page.bPageFix = true;
    }//GEN-LAST:event_formInternalFrameClosed

    private void dgvShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgvShowMouseClicked
        try {
            int nRow = dgvShow.getSelectedRow();
            String sUsername = dgvShow.getValueAt(nRow, 0).toString();
            String sPassword = dgvShow.getValueAt(nRow, 1).toString();

            txtUsername.setText(sUsername);
            txtPassword.setText(sPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_dgvShowMouseClicked

    private void cmdClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdClearActionPerformed
        Wprv_Clear();
    }//GEN-LAST:event_cmdClearActionPerformed

    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
        if(jh_center.sUsername.equals(txtUsername.getText().trim())){
                JOptionPane.showMessageDialog(rootPane, "ไม่สามารถลบข้อมูลได้", "ตรวจสอบ",
                        JOptionPane.ERROR_MESSAGE,null);
                return;
        }  
        sAction = "DELETE";
        Wprv_SaveData();
    }//GEN-LAST:event_cmdDeleteActionPerformed

    private void cmdEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditActionPerformed
        sAction = "EDIT";
        Wprv_SaveData();
    }//GEN-LAST:event_cmdEditActionPerformed

    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
        sAction = "ADD";
        Wprv_SaveData();
    }//GEN-LAST:event_cmdAddActionPerformed

    /**/
    private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdCloseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAdd;
    private javax.swing.JButton cmdClear;
    private javax.swing.JButton cmdClose;
    private javax.swing.JButton cmdDelete;
    private javax.swing.JButton cmdEdit;
    private javax.swing.JTable dgvShow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
