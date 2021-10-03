package purchase.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class FrmItem_Show extends javax.swing.JInternalFrame {

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
            dgvShow.setValueAt("", iRow, 2);
            dgvShow.setValueAt("", iRow, 3);
            dgvShow.setValueAt("", iRow, 4);
            dgvShow.setValueAt("", iRow, 5);
            dgvShow.setValueAt("", iRow, 6);
            dgvShow.setValueAt("", iRow, 7);
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
                    sSql = "Where tname like '%" + txtSearch.getText().trim() + "%'";
                }else if(cmbSelect.getSelectedIndex() == 1){
                    sSql = "Where gname like '%" + txtSearch.getText().trim() + "%'";
                }else {
                    sSql = "Where itemcode like '%" + txtSearch.getText().trim() + "%'";
                }
            }

            jh_center_cn.Cpuv_GetRowPage();

            String sSqlShow = "select itemcode, gname, tname, (select nthai From unit Where unitcode=item.unitcode) AS UnitName, "
                    + " Case When status='WC' Then 'ปกติ' Else 'ยกเลิก'  End As StatusThai, "
                    + " createdate, "
                    + " Case When lastupdate='1990-01-01' Then '' Else lastupdate End As lastupdate, "
                    + " Case When deletedate='1990-01-01' Then '' Else deletedate End As deletedate "
                    + " From item " + sSql.trim() + " Limit " + jh_center_cn.piStart + " ," + jh_center_cn.piEnd + " ";
            //System.out.println(sSqlShow);
            ResultSet rs = jh_center_cn.Cpuds_ShowData(sSqlShow);
            int iRow = 0;
            while (rs.next()) {
                //String sCode = rs.getString("Code");
                //String sName = rs.getString("Name");
                
                dgvShow.setValueAt(rs.getString("itemcode"), iRow, 0);
                dgvShow.setValueAt(rs.getString("gname"), iRow, 1);
                dgvShow.setValueAt(rs.getString("tname"), iRow, 2);
                dgvShow.setValueAt(rs.getString("UnitName"), iRow, 3);
                dgvShow.setValueAt(rs.getString("StatusThai"), iRow, 4);
                dgvShow.setValueAt(rs.getString("createdate"), iRow, 5);
                dgvShow.setValueAt(rs.getString("lastupdate"), iRow, 6);
                dgvShow.setValueAt(rs.getString("deletedate"), iRow, 7);
                iRow++;
            }

            jh_center_cn.Cpuv_GetSql(iRow);

            int iRowAll = jh_center_cn.Cpui_RowsAll("Select count(itemcode) As Num From item " + sSql.trim() + "", "Num");
            if (iRowAll == 0) {
                iRowAll = 1;
            }
            lblTitlePage.setText("หน้าที่ : " + (jh_center_cn.piPage + 1) + ":จากทั้งหมด : " + iRowAll + " หน้า");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FrmItem_Show() {
        initComponents();

        setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
        dgvShow.setAutoResizeMode(dgvShow.AUTO_RESIZE_ALL_COLUMNS);
        dgvShow.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        dgvShow.getTableHeader().setForeground(new Color(255, 255, 255));
        dgvShow.getTableHeader().setBackground(new Color(0, 0, 102));

        TableColumn col1 = dgvShow.getColumnModel().getColumn(0);
        col1.setPreferredWidth(50);

        TableColumn col2 = dgvShow.getColumnModel().getColumn(1);
        col2.setPreferredWidth(150);

        TableColumn col3 = dgvShow.getColumnModel().getColumn(2);
        col3.setPreferredWidth(150);

        TableColumn col4 = dgvShow.getColumnModel().getColumn(3);
        col4.setPreferredWidth(50);

        TableColumn col5 = dgvShow.getColumnModel().getColumn(4);
        col5.setPreferredWidth(50);

        TableColumn col6 = dgvShow.getColumnModel().getColumn(5);
        col6.setPreferredWidth(50);

        TableColumn col7 = dgvShow.getColumnModel().getColumn(6);
        col7.setPreferredWidth(50);

        TableColumn col8 = dgvShow.getColumnModel().getColumn(7);
        col8.setPreferredWidth(50);

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
        mnsAdd.setText("เพิ่มรายการ");
        mnsAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnsAddActionPerformed(evt);
            }
        });
        mnsMenu.add(mnsAdd);
        mnsMenu.add(jSeparator1);

        mnsEdit.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        mnsEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Edit20x20.png"))); // NOI18N
        mnsEdit.setText("แก้ไขรายการ");
        mnsEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnsEditActionPerformed(evt);
            }
        });
        mnsMenu.add(mnsEdit);
        mnsMenu.add(jSeparator2);

        mnsDelete.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        mnsDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/close Jframe.png"))); // NOI18N
        mnsDelete.setText("ยกเลิกรายการ");
        mnsDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnsDeleteActionPerformed(evt);
            }
        });
        mnsMenu.add(mnsDelete);

        setTitle("ข้อมูลไอเทม");
        setPreferredSize(new java.awt.Dimension(1400, 771));
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("เลือก");

        cmbSelect.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ชื่อไอเทม", "ชื่อทางการค้า", "รหัสไอเทม" }));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("กรอกค้นหา");

        txtSearch.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });

        cmdSearch.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cmdSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search20x20.png"))); // NOI18N
        cmdSearch.setText("ค้นหา");
        cmdSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSearchActionPerformed(evt);
            }
        });

        cmdRefresh.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cmdRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh-20x20.png"))); // NOI18N
        cmdRefresh.setText("เริ่มใหม่");
        cmdRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRefreshActionPerformed(evt);
            }
        });

        chkAll.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        chkAll.setText("ทั้งหมด");
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
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(cmbSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkAll, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdSearch)
                    .addComponent(cmdRefresh)
                    .addComponent(chkAll))
                .addGap(25, 25, 25))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(500, 402));

        dgvShow.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dgvShow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "รหัสไอเทม", "ชื่อไอเทม", "ชื่อทางการค้า", "หน่วย", "สถานะ", "วันที่สร้าง", "วันที่แก้ไขล่าสุด", "วันที่ยกเลิก"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dgvShow.setMaximumSize(new java.awt.Dimension(1295, 448));
        dgvShow.setMinimumSize(new java.awt.Dimension(1295, 448));
        dgvShow.setPreferredSize(new java.awt.Dimension(1295, 399));
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

        lblTitlePage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitlePage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitlePage.setText("หน้าที่ :   จากทั้งหมด");
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

        cmdExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmdExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/exit30x30.png"))); // NOI18N
        cmdExit.setText("ปิด");
        cmdExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExitActionPerformed(evt);
            }
        });

        cmdAdd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmdAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add.png"))); // NOI18N
        cmdAdd.setText("เพิ่มไอเทม");
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
                .addGap(46, 46, 46)
                .addComponent(cmdExit)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1298, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmdExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1300, Short.MAX_VALUE))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if (KeyEvent.VK_ENTER == evt.getKeyCode()) {
            if (txtSearch.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "กรุณากรอกเงื่อนไขในการค้นหา", "ตรวจสอบ",
                        JOptionPane.WARNING_MESSAGE, null);
                txtSearch.requestFocusInWindow();
                return;
            }
            Wprv_ShowData();
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void cmdSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSearchActionPerformed
        if (txtSearch.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "กรุณากรอกเงื่อนไขในการค้นหา", "ตรวจสอบ",
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

    private void dgvShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgvShowMouseClicked
        sCode = dgvShow.getValueAt(dgvShow.getSelectedRow(), 0).toString();
        mnsEdit.setText("แก้ไขไอเทม รหัส: " + sCode);
        mnsDelete.setText("ยกเลิกไอเทม รหัส: " + sCode);

        //ป้องกันในสถานะที่ขึ้นว่า ยกเลิกแล้ว        
        if (dgvShow.getValueAt(dgvShow.getSelectedRow(), 4).toString().equals("ยกเลิกแล้ว")) {
            mnsEdit.setEnabled(false);
            mnsDelete.setEnabled(false);
        } else {
            mnsEdit.setEnabled(true);
            mnsDelete.setEnabled(true);
        }
        //----------------------------------------------------------------
        dgvShow.setComponentPopupMenu(mnsMenu);
        if (evt.getClickCount() == 2) {
            if(sCode.trim().equals("")) {
            FrmItem_Save Frm = new FrmItem_Save(null, closable);
            Frm.psAction = "ADD";
            Frm.setModal(true);
            jh_center_cn.Cprv_ScreenFormJDialogCenter(Frm);
            if (Frm.pbAction == true) {
            Wprv_ShowData();
            }
            //return;
            }else {
            FrmItem_Save Frm = new FrmItem_Save(null, closable);
            Frm.setTitle("ข้อมูลไอเทม");
            Frm.psAction = "VIEW";
            Frm.psCode = sCode;
            Frm.setModal(true);
            jh_center_cn.Cprv_ScreenFormJDialogCenter(Frm);
            }
        }
    }//GEN-LAST:event_dgvShowMouseClicked

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

    private void cmdExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdExitActionPerformed

    private void mnsAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnsAddActionPerformed
        FrmItem_Save Frm = new FrmItem_Save(null, closable);
        Frm.psAction = "ADD";
        Frm.setModal(true);
        jh_center_cn.Cprv_ScreenFormJDialogCenter(Frm);
        if (Frm.pbAction == true) {
            Wprv_ShowData();
        }
    }//GEN-LAST:event_mnsAddActionPerformed

    private void mnsEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnsEditActionPerformed
        if (sCode.trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "กรุณาเลือกสิ่งที่ต้องการทำ ก่อน", "ตรวจสอบ",
                    JOptionPane.WARNING_MESSAGE, null);
            return;
        }

        FrmItem_Save Frm = new FrmItem_Save(null, closable);
        Frm.setTitle("แก้ไขไอเทม");
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
            JOptionPane.showMessageDialog(rootPane, "กรุณาเลือกสิ่งที่ต้องการทำ ก่อน", "ตรวจสอบ",
                    JOptionPane.WARNING_MESSAGE, null);
            return;
        }

        FrmItem_Save Frm = new FrmItem_Save(null, closable);
        Frm.setTitle("ยกเลิกไอเทม");
        Frm.psAction = "DELETE";
        Frm.psCode = sCode;
        Frm.setModal(true);
        jh_center_cn.Cprv_ScreenFormJDialogCenter(Frm);
        if (Frm.pbAction == true) {
            Wprv_ShowData();
        }
    }//GEN-LAST:event_mnsDeleteActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        jh_page.bPageItem = false;
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        jh_page.bPageItem = true;
    }//GEN-LAST:event_formInternalFrameClosed

    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
        FrmItem_Save Frm = new FrmItem_Save(null, closable);
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
