package purchase.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class FrmUnit_Show extends javax.swing.JInternalFrame {

    jh_center jh_center_cn = new jh_center();
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

    //Show Data
    private void Wprv_ShowData() {
        try {
            Wprv_ClearDataGrid();

            String sSql = "";
            if (chkAll.isSelected() == false) {
                if (cmbSelect.getSelectedIndex() == 0) {
                    sSql = "Where nthai like '%" + txtSearch.getText().trim() + "%' ";
                } else if(cmbSelect.getSelectedIndex() == 1){
                    sSql = "Where unitcode like '%" + txtSearch.getText().trim() + "%' ";
                }else{
                    sSql="";
                }
            }

            jh_center_cn.Cpuv_GetRowPage();

            String sSqlShow = "select * from unit " + sSql.trim() + " Order By unitcode Asc "
                    + "Limit " + jh_center_cn.piStart + " , " + jh_center_cn.piEnd + " ";
            //System.out.println(sSqlShow);
            ResultSet rs = jh_center_cn.Cpuds_ShowData(sSqlShow);
            int iRow = 0;
            while (rs.next()) {
                String sCode = rs.getString("unitcode");
                String sName = rs.getString("nthai");

                dgvShow.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
                dgvShow.getTableHeader().setForeground(new Color(255, 255, 255));
                dgvShow.getTableHeader().setBackground(new Color(0, 0, 102));
                
                dgvShow.setValueAt(sCode, iRow, 0);
                dgvShow.setValueAt(sName, iRow, 1);
                iRow++;
            }

            jh_center_cn.Cpuv_GetSql(iRow);

            int iRowAll = jh_center_cn.Cpui_RowsAll("Select count(unitcode) As Num From unit " + sSql.trim() + "", "Num");
            if (iRowAll == 0) {
                iRowAll = 1;
            }
            lblTitlePage.setText("????????????????????? : " + (jh_center_cn.piPage + 1) + " ?????????????????????????????? : " + iRowAll + " ????????????");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FrmUnit_Show() {
        initComponents();

        setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
        dgvShow.setAutoResizeMode(dgvShow.AUTO_RESIZE_ALL_COLUMNS);

        TableColumn col1 = dgvShow.getColumnModel().getColumn(0);
        col1.setPreferredWidth(120);

        TableColumn col2 = dgvShow.getColumnModel().getColumn(1);
        col2.setPreferredWidth(400);

        Wprv_ShowData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mnsMenu = new javax.swing.JPopupMenu();
        mnsAdd = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnsEdit = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mnsDelete = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbSelect = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cmdSearch = new javax.swing.JButton();
        cmdRefresh = new javax.swing.JButton();
        chkAll = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dgvShow = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        cmdStart = new javax.swing.JButton();
        cmdBack = new javax.swing.JButton();
        lblTitlePage = new javax.swing.JLabel();
        cmdWalk = new javax.swing.JButton();
        cmdEnd = new javax.swing.JButton();
        cmdExit = new javax.swing.JButton();
        cmdAdd = new javax.swing.JButton();

        mnsAdd.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        mnsAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add.png"))); // NOI18N
        mnsAdd.setText("?????????????????????????????????");
        mnsAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnsAddActionPerformed(evt);
            }
        });
        mnsMenu.add(mnsAdd);
        mnsMenu.add(jSeparator1);

        mnsEdit.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        mnsEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Edit20x20.png"))); // NOI18N
        mnsEdit.setText("?????????????????????????????????");
        mnsEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnsEditActionPerformed(evt);
            }
        });
        mnsMenu.add(mnsEdit);
        mnsMenu.add(jSeparator2);

        mnsDelete.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        mnsDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/close Jframe.png"))); // NOI18N
        mnsDelete.setText("????????????????????????????????????");
        mnsDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnsDeleteActionPerformed(evt);
            }
        });
        mnsMenu.add(mnsDelete);

        setTitle("??????????????????????????????");
        setPreferredSize(new java.awt.Dimension(830, 650));
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
        jPanel1.setMaximumSize(new java.awt.Dimension(612, 52));
        jPanel1.setMinimumSize(new java.awt.Dimension(612, 52));
        jPanel1.setPreferredSize(new java.awt.Dimension(612, 52));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("???????????????");

        cmbSelect.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "???????????????????????????", "???????????????????????????" }));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("???????????????????????????");

        txtSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });

        cmdSearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cmdSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search20x20.png"))); // NOI18N
        cmdSearch.setText("???????????????");
        cmdSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSearchActionPerformed(evt);
            }
        });

        cmdRefresh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cmdRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh-20x20.png"))); // NOI18N
        cmdRefresh.setText("???????????????????????????");
        cmdRefresh.setPreferredSize(new java.awt.Dimension(97, 29));
        cmdRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRefreshActionPerformed(evt);
            }
        });

        chkAll.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chkAll.setText("?????????????????????");
        chkAll.setPreferredSize(new java.awt.Dimension(67, 29));
        chkAll.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkAllStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addComponent(cmbSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdSearch)
                .addGap(11, 11, 11)
                .addComponent(cmdRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdSearch)
                    .addComponent(cmdRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(471, 451));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(515, 460));

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
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "???????????????????????????", "???????????????????????????"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dgvShow.setComponentPopupMenu(mnsMenu);
        dgvShow.setPreferredSize(new java.awt.Dimension(605, 398));
        dgvShow.setRowHeight(20);
        dgvShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dgvShowMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dgvShow);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        cmdStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/back-30x30.png"))); // NOI18N
        cmdStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdStartActionPerformed(evt);
            }
        });
        jToolBar1.add(cmdStart);

        cmdBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/rv-30x30.png"))); // NOI18N
        cmdBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBackActionPerformed(evt);
            }
        });
        jToolBar1.add(cmdBack);

        lblTitlePage.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitlePage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitlePage.setText("????????????????????? :    ??????????????????????????????");
        jToolBar1.add(lblTitlePage);

        cmdWalk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/fw-30x30.png"))); // NOI18N
        cmdWalk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdWalkActionPerformed(evt);
            }
        });
        jToolBar1.add(cmdWalk);

        cmdEnd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/next-30x30.png"))); // NOI18N
        cmdEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEndActionPerformed(evt);
            }
        });
        jToolBar1.add(cmdEnd);

        cmdExit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cmdExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/exit30x30.png"))); // NOI18N
        cmdExit.setText("?????????");
        cmdExit.setMaximumSize(new java.awt.Dimension(83, 21));
        cmdExit.setMinimumSize(new java.awt.Dimension(83, 21));
        cmdExit.setPreferredSize(new java.awt.Dimension(83, 21));
        cmdExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExitActionPerformed(evt);
            }
        });

        cmdAdd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmdAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add.png"))); // NOI18N
        cmdAdd.setText("??????????????????????????????");
        cmdAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdExit, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE))
                .addContainerGap(192, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnsAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnsAddActionPerformed
        FrmUnit_Save Frm = new FrmUnit_Save(null, closable);
        Frm.psAction = "ADD";
        Frm.setModal(true);
        jh_center_cn.Cprv_ScreenFormJDialogCenter(Frm);
        if (Frm.pbAction == true) {
            Wprv_ShowData();
        }
    }//GEN-LAST:event_mnsAddActionPerformed

    private void dgvShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgvShowMouseClicked
        sCode = dgvShow.getValueAt(dgvShow.getSelectedRow(), 0).toString();
        /*if (sCode.trim().equals("")) {
        return;
        }*/
        mnsEdit.setText("?????????????????????????????? ????????????: " + sCode);
        mnsDelete.setText("????????????????????????????????? ????????????: " + sCode);
        dgvShow.setComponentPopupMenu(mnsMenu);
        if (evt.getClickCount() == 2) {
            if(sCode.trim().equals("")) {
            FrmUnit_Save Frm = new FrmUnit_Save(null, closable);
            Frm.psAction = "ADD";
            Frm.psCode = sCode;
            Frm.setModal(true);
            jh_center_cn.Cprv_ScreenFormJDialogCenter(Frm);
            }else {
            FrmUnit_Save Frm = new FrmUnit_Save(null, closable);
            Frm.setTitle("?????????????????????????????????");
            Frm.psAction = "VIEW";
            Frm.psCode = sCode;
            Frm.setModal(true);
            jh_center_cn.Cprv_ScreenFormJDialogCenter(Frm);
            }
        }else{
            Wprv_ShowData();
        }
    }//GEN-LAST:event_dgvShowMouseClicked

    private void mnsEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnsEditActionPerformed
        if (sCode.trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "?????????????????????????????????????????????????????????????????????????????? ????????????", "?????????????????????",
                    JOptionPane.WARNING_MESSAGE, null);
            return;
        }

        FrmUnit_Save Frm = new FrmUnit_Save(null, closable);
        Frm.setTitle("??????????????????????????????");
        Frm.psAction = "EDIT";
        Frm.psCode = sCode;
        Frm.setModal(true);
        jh_center_cn.Cprv_ScreenFormJDialogCenter(Frm);
        if (Frm.pbAction == true) {
            Wprv_ShowData();
        }
    }//GEN-LAST:event_mnsEditActionPerformed

    private void mnsDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnsDeleteActionPerformed
        if (sCode.trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "?????????????????????????????????????????????????????????????????????????????? ????????????", "?????????????????????",
                    JOptionPane.WARNING_MESSAGE, null);
            return;
        }

        FrmUnit_Save Frm = new FrmUnit_Save(null, closable);
        Frm.setTitle("?????????????????????????????????");
        Frm.psAction = "DELETE";
        Frm.psCode = sCode;
        Frm.setModal(true);
        jh_center_cn.Cprv_ScreenFormJDialogCenter(Frm);
        if (Frm.pbAction == true) {
            Wprv_ShowData();
        }
    }//GEN-LAST:event_mnsDeleteActionPerformed

    private void cmdSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSearchActionPerformed
        if (txtSearch.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "?????????????????????????????????????????????????????????????????????????????????", "?????????????????????",
                    JOptionPane.WARNING_MESSAGE, null);
            txtSearch.requestFocusInWindow();
            return;
        }
        Wprv_ShowData();
    }//GEN-LAST:event_cmdSearchActionPerformed
    
    private void cmdRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRefreshActionPerformed
        chkAll.setSelected(false);
        txtSearch.setText("");
        Wprv_ShowData();
    }//GEN-LAST:event_cmdRefreshActionPerformed

    private void chkAllStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkAllStateChanged
        Wprv_ShowData();
    }//GEN-LAST:event_chkAllStateChanged

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if (KeyEvent.VK_ENTER == evt.getKeyCode()) {
            if (txtSearch.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "?????????????????????????????????????????????????????????????????????????????????", "?????????????????????",
                        JOptionPane.WARNING_MESSAGE, null);
                txtSearch.requestFocusInWindow();
                return;
            }
            Wprv_ShowData();
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void cmdExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdExitActionPerformed

    private void cmdStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdStartActionPerformed
        jh_center_cn.piPage = 0;
        Wprv_ShowData();
    }//GEN-LAST:event_cmdStartActionPerformed

    private void cmdBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBackActionPerformed
        jh_center_cn.piPage = jh_center_cn.piPage - 1;
        if (jh_center_cn.piPage < 0) {
            jh_center_cn.piPage = 0;
        }
        Wprv_ShowData();
    }//GEN-LAST:event_cmdBackActionPerformed

    private void cmdWalkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdWalkActionPerformed
        if (jh_center_cn.piRowFloor > 0) {
            jh_center_cn.piPage = jh_center_cn.piPage+1;
        }        
        Wprv_ShowData();
    }//GEN-LAST:event_cmdWalkActionPerformed

    private void cmdEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEndActionPerformed
        if (jh_center_cn.piRowFloor > 0) {
            jh_center_cn.piPage = jh_center_cn.piPageGet+1;
        }
        Wprv_ShowData();
    }//GEN-LAST:event_cmdEndActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        jh_page.bPageUnit = false;
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        jh_page.bPageUnit = true;
    }//GEN-LAST:event_formInternalFrameClosed

    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
        FrmUnit_Save Frm = new FrmUnit_Save(null, closable);
        Frm.psAction = "ADD";
        Frm.setModal(true);
        jh_center_cn.Cprv_ScreenFormJDialogCenter(Frm);
        if (Frm.pbAction == true) {
            Wprv_ShowData();
        }
    }//GEN-LAST:event_cmdAddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkAll;
    private javax.swing.JComboBox cmbSelect;
    private javax.swing.JButton cmdAdd;
    private javax.swing.JButton cmdBack;
    private javax.swing.JButton cmdEnd;
    private javax.swing.JButton cmdExit;
    private javax.swing.JButton cmdRefresh;
    private javax.swing.JButton cmdSearch;
    private javax.swing.JButton cmdStart;
    private javax.swing.JButton cmdWalk;
    private javax.swing.JTable dgvShow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblTitlePage;
    private javax.swing.JMenuItem mnsAdd;
    private javax.swing.JMenuItem mnsDelete;
    private javax.swing.JMenuItem mnsEdit;
    private javax.swing.JPopupMenu mnsMenu;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
