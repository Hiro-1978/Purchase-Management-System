package purchase.management.system;

import java.awt.Color;
import java.awt.Font;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class FrmItem_Save extends javax.swing.JDialog {

    public String psAction = "";
    public String psCode = "";
    public boolean pbAction = false;
    jh_center jh_center_cn = new jh_center();

    Date dNow = new Date();
    DateFormat dDateMY = new SimpleDateFormat("yyyy/MM/dd");
    String sDate = dDateMY.format(dNow);

    private DefaultTableModel tableModel = new DefaultTableModel();
    private Object[] data = new Object[0];
    DecimalFormat df = null;

    //Method
    //Clear Grid
    private void Wprv_ClearDataGridStock() {
        int iRow = dgvShow.getRowCount() - 1;
        while (iRow > -1) {
            tableModel.removeRow(iRow);
            iRow--;
        }
    }

    private void Wprv_ClearData() {
        txtCode.setText("");
        txtGName.setText("");
        txtTName.setText("");
        txtUCode.setText("");
        txtUName.setText("");
        txtBCode.setText("");
        txtBName.setText("");
        lblShowMoney.setText("");
    }

    //Show Data Stock
    private void Wprv_ShowDBMgnt() {
        try {
            Wprv_ClearDataGridStock();
            
            jh_center_cn.Cpuv_GetRowPage();
            
            String sSqlShow = " Select stockmgnt.venderid,stockmgnt.vname,stockmgnt.qty,"
                    + " stockmgnt.bunitname,stockmgnt.amount,stockmgnt.confdate "
                    + " From "
                    + " item Inner Join stockmgnt On item.itemcode=stockmgnt.itemcode "
                    + " where item.itemcode='" + txtCode.getText().trim() + "' Order By stockmgnt.confdate Desc "
                    + " Limit " + jh_center_cn.piStart + ", " + jh_center_cn.piEnd + "";
            System.out.println(sSqlShow);
            ResultSet rs = jh_center_cn.Cpuds_ShowData(sSqlShow);
            int iRow = 0;
            df = new DecimalFormat("#,##0.00");
            double dQtyAll = 0;
            while (rs.next()) {
                tableModel.addRow(data);
           
                dgvShow.setValueAt(rs.getString("venderid"), iRow, 0);
                dgvShow.setValueAt(rs.getString("vname"), iRow, 1);
                dgvShow.setValueAt(rs.getString("qty"), iRow, 2);
                dgvShow.setValueAt(rs.getString("bunitname"), iRow, 3);                
                dgvShow.setValueAt(df.format(Double.valueOf(rs.getString("amount"))), iRow, 4);
                //dgvShow.setValueAt(rs.getString("amount"), iRow, 4);
                dgvShow.setValueAt(rs.getString("confdate"), iRow, 5);
                try {
                    if (dgvShow.getValueAt(iRow, 4) != null) {
                        if (!dgvShow.getValueAt(iRow, 4).toString().equals("")) {
                            dQtyAll = dQtyAll + Double.valueOf(rs.getString("amount"));
                        } else {
                            dQtyAll = 0;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                iRow++;
            }
                      
            jh_center_cn.Cpuv_GetSql(iRow);
            int iRowAll = jh_center_cn.Cpui_RowsAll("Select count(docno) As Num From stockmgnt", "Num");
            if (iRowAll == 0) {
                iRowAll = 1;
            //System.out.println(iRowAll);
            }
            lblTitlePage.setText("หน้าที่ : " + (jh_center_cn.piPage + 1) + ":จากทั้งหมด : " + iRowAll + " หน้า");
            
            lblShowMoney.setText(String.valueOf(df.format(dQtyAll)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Lock Tool
    private void Wprv_LockTool(boolean _bCheck) {
        txtCode.setEditable(_bCheck);
        txtGName.setEditable(_bCheck);
        txtTName.setEditable(_bCheck);
        cmdUnit.setEnabled(_bCheck);
        cmdBaseType.setEnabled(_bCheck);
        cmdClear.setEnabled(_bCheck);
    }

    //Key AutoRun
    /*private void Wprv_AutoRun() {
     String sCode = "";
     try {
     sCode = jh_center_cn.Cpus_AutoRun("Code", "bnproduct", "P-", "00000"); // P-00001
     //txtCode.setText(sCode);
     } catch (Exception e) {
     e.printStackTrace();
     }

     txtCode.setText(sCode);
     }*/
    //Show Data Product
    private void Wprv_ShowDB() {
        try {
            txtCode.setText(psCode.trim());
            String sSql = "Select *,(Select nthai from unit where unitcode=item.unitcode) as bunitname,"
                    + " (Select bname from basetype where baseid=item.baseid) as bname "
                    + " From item where itemcode='" + txtCode.getText().trim() + "' ";
            //System.out.println(sSql);
            jh_center_cn = new jh_center();
            ResultSet rs = jh_center_cn.Cpuds_ShowData(sSql);
            if (rs.next()) {
                txtCode.setText(rs.getString("itemcode").toString());
                txtGName.setText(rs.getString("gname").toString());
                txtTName.setText(rs.getString("tname").toString());
                txtUCode.setText(rs.getString("unitcode").toString());
                txtUName.setText(rs.getString("bunitname").toString());
                txtBCode.setText(rs.getString("baseid").toString());
                txtBName.setText(rs.getString("bname").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FrmItem_Save(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fiFileBrown = new javax.swing.JFileChooser();
        jToolBar1 = new javax.swing.JToolBar();
        cmdClose = new javax.swing.JButton();
        cmdSave = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        txtGName = new javax.swing.JTextField();
        txtUCode = new javax.swing.JTextField();
        txtUName = new javax.swing.JTextField();
        cmdUnit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        dgvShow = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTName = new javax.swing.JTextField();
        cmdClear = new javax.swing.JButton();
        txtBCode = new javax.swing.JTextField();
        txtBName = new javax.swing.JTextField();
        cmdBaseType = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        cmdStart = new javax.swing.JButton();
        cmdBack = new javax.swing.JButton();
        lblTitlePage = new javax.swing.JLabel();
        cmdWalk = new javax.swing.JButton();
        cmdEnd = new javax.swing.JButton();
        lblShowMoney = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("บันทึกไอเทม");
        setMinimumSize(new java.awt.Dimension(740, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

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

        cmdSave.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        cmdSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save.png"))); // NOI18N
        cmdSave.setText("บันทึก");
        cmdSave.setFocusable(false);
        cmdSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSaveActionPerformed(evt);
            }
        });
        jToolBar1.add(cmdSave);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("รหัสสินค้า :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("ชื่อไอเทม :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("หน่วย :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("หมวดหมู่ :");

        txtCode.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCode.setPreferredSize(new java.awt.Dimension(82, 29));

        txtGName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtGName.setPreferredSize(new java.awt.Dimension(82, 29));

        txtUCode.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtUCode.setPreferredSize(new java.awt.Dimension(82, 29));

        txtUName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtUName.setPreferredSize(new java.awt.Dimension(82, 29));

        cmdUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search20x20.png"))); // NOI18N
        cmdUnit.setMaximumSize(new java.awt.Dimension(53, 22));
        cmdUnit.setMinimumSize(new java.awt.Dimension(53, 22));
        cmdUnit.setPreferredSize(new java.awt.Dimension(53, 22));
        cmdUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUnitActionPerformed(evt);
            }
        });

        jScrollPane2.setPreferredSize(new java.awt.Dimension(698, 260));

        dgvShow.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dgvShow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "รหัสบริษัท", "ชื่อบริษัทผู้จำหน่าย", "จำนวน", "หน่วยไอเทม", "มูลค่าสั่งซื้อ", "วันที่รับของ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dgvShow.setPreferredSize(new java.awt.Dimension(773, 399));
        dgvShow.setRowHeight(20);
        jScrollPane2.setViewportView(dgvShow);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("มูลค่าสะสมไอเทมทั้งหมด :");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel8.setPreferredSize(new java.awt.Dimension(179, 29));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("ชื่อทางการค้า :");

        txtTName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        cmdClear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmdClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh-20x20.png"))); // NOI18N
        cmdClear.setText("เคลียร์ข้อมูล");
        cmdClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdClearActionPerformed(evt);
            }
        });

        txtBCode.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        txtBName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        cmdBaseType.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search20x20.png"))); // NOI18N
        cmdBaseType.setPreferredSize(new java.awt.Dimension(53, 22));
        cmdBaseType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBaseTypeActionPerformed(evt);
            }
        });

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);
        jToolBar2.setPreferredSize(new java.awt.Dimension(281, 25));

        cmdStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/back-30x30.png"))); // NOI18N
        cmdStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdStartActionPerformed(evt);
            }
        });
        jToolBar2.add(cmdStart);

        cmdBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/rv-30x30.png"))); // NOI18N
        cmdBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBackActionPerformed(evt);
            }
        });
        jToolBar2.add(cmdBack);

        lblTitlePage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitlePage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitlePage.setText("หน้าที่ :   จากทั้งหมด");
        jToolBar2.add(lblTitlePage);

        cmdWalk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/fw-30x30.png"))); // NOI18N
        cmdWalk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdWalkActionPerformed(evt);
            }
        });
        jToolBar2.add(cmdWalk);

        cmdEnd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/next-30x30.png"))); // NOI18N
        cmdEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEndActionPerformed(evt);
            }
        });
        jToolBar2.add(cmdEnd);

        lblShowMoney.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        lblShowMoney.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        lblShowMoney.setPreferredSize(new java.awt.Dimension(69, 29));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(13, 13, 13))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                            .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTName))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBCode, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBName, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(cmdUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmdBaseType, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdClear, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblShowMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUCode, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmdBaseType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtBCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtBName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmdClear)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblShowMoney, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed
        pbAction = false;
        this.dispose();
    }//GEN-LAST:event_cmdCloseActionPerformed

    private void cmdSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveActionPerformed
        //เช็คค่าว่าง Text
        if (txtCode.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "กรุณากรอก รหัสไอเทม", "ตรวจสอบ",
                    JOptionPane.WARNING_MESSAGE, null);
            txtCode.requestFocusInWindow();
            return;
        }

        if (txtGName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "กรุณากรอก ชื่อไอเทม", "ตรวจสอบ",
                    JOptionPane.WARNING_MESSAGE, null);
            txtGName.requestFocusInWindow();
            return;
        }

        if (txtTName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "กรุณากรอก ชื่อทางการค้า", "ตรวจสอบ",
                    JOptionPane.WARNING_MESSAGE, null);
            txtGName.requestFocusInWindow();
            return;
        }

        if (txtUCode.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "กรุณากรอก หน่วยไอเทม", "ตรวจสอบ",
                    JOptionPane.WARNING_MESSAGE, null);
            txtUCode.requestFocusInWindow();
            return;
        }

        if (txtBCode.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "กรุณากรอก หมวดไอเทม", "ตรวจสอบ",
                    JOptionPane.WARNING_MESSAGE, null);
            txtUCode.requestFocusInWindow();
            return;
        }

        //บันทึกข้อมูลลง DB
        String sql = "";
        String sTitle = "";
        FileInputStream fis = null;
        PreparedStatement ps = null;
        if (psAction.toUpperCase().endsWith("ADD")) {
            try {
                if (!txtCode.getText().trim().equals("")) {
                    String sSql = " Select * From item Where unitcode=' " + txtCode.getText().trim() + " ' ";
                    ResultSet rs = jh_center_cn.Cpuds_ShowData(sSql);
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(rootPane, "รหัสไอเทมซ้ำ กรุณาเลือกใหม่", "ตรวจสอบ",
                                JOptionPane.WARNING_MESSAGE, null);
                        txtCode.requestFocusInWindow();
                        return;
                    }
                }

                //Add Data
                sTitle = "เพิ่มข้อมูล";
                //Wprv_AutoRun();
                // sql ประกาศไว้ด้านบนแล้ว
                sql = "Insert into item(itemcode,gname,tname,unitcode,baseid,createdate,"
                        + "lastupdate,deletedate,status)"
                        + " values(?,?,?,?,?,?,?,?,?) ";
                System.out.println(sql);
                jh_center_cn.con.setAutoCommit(false);
                ps = (PreparedStatement) jh_center_cn.con.prepareStatement(sql);
                ps.setString(1, txtCode.getText().trim());
                ps.setString(2, txtGName.getText().trim());
                ps.setString(3, txtTName.getText().trim());
                ps.setString(4, txtUCode.getText().trim());
                ps.setString(5, txtBCode.getText().trim());
                ps.setString(6, sDate);
                ps.setString(7, "1990-01-01");
                ps.setString(8, "1990-01-01");
                ps.setString(9, "WC");
                ps.executeUpdate();
                jh_center_cn.con.commit();
                ps.close();

                jh_center_cn.bCheckConnectDB = false;
                jh_center_cn.con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            //-----------------------------End เพิ่มเติมข้อมูล----------------------------//
            //Add Data
        } else if (psAction.toUpperCase().endsWith("EDIT")) {
            if (JOptionPane.showConfirmDialog(null, "คุณต้องการแก้ไขข้อมูล ใช่หรือไม่", "ตรวจสอบ",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                return;
            }
            try {

                sTitle = "แก้ไขข้อมูล";
                //String sSql = "";
                sql = " Update item set gname=?,tname=?,unitcode=?,baseid=?,lastupdate=? Where itemcode=? ";
                ps = (PreparedStatement) jh_center_cn.con.prepareStatement(sql);
                ps.setString(1, txtGName.getText().trim());
                ps.setString(2, txtTName.getText().trim());
                ps.setString(3, txtUCode.getText().trim());
                ps.setString(4, txtBCode.getText().trim());
                ps.setString(5, sDate);
                ps.setString(6, txtCode.getText().trim());
                ps.executeUpdate();
                ps.close();
                
                //System.out.println(sql);
                
                updateDocSub();
                updateStockMgnt();
                        
                jh_center.bCheckConnectDB = false;
                jh_center_cn.con.close();                
            } catch (Exception e) {
                e.printStackTrace();
            }
            //-----------------------------End แก้ไขข้อมูล----------------------------//
            //Delete            
        } else if (psAction.toUpperCase().endsWith("DELETE")) {
            try { //เช็คก่อนว่ารหัสถูกใช้งานไปแล้วหรือยังจากตารางลูกที่เอาไปใช้งาน sub อาจจะมีมากกว่า 1
                if (jh_center_cn.Cpub_CheckValueData("docsub", "itemcode", txtCode.getText().trim()) == true) {
                    JOptionPane.showMessageDialog(rootPane, "รหัสไอเทมถูกใช้งานไปแล้ว ยกเลิกไม่ได้", "ตรวจสอบ",
                            JOptionPane.WARNING_MESSAGE, null);
                    return;
                }

                if (jh_center_cn.Cpub_CheckValueData("stockmgnt", "itemcode", txtCode.getText().trim()) == true) {
                    JOptionPane.showMessageDialog(rootPane, "รหัสไอเทมถูกใช้งานไปแล้ว ยกเลิกไม่ได้", "ตรวจสอบ",
                            JOptionPane.WARNING_MESSAGE, null);
                    return;
                }

                sTitle = "ยกเลิกไอเทม";
                if (JOptionPane.showConfirmDialog(null, "คุณต้องการยกเลิกไอเทมใช่ไหม?", "ตรวจสอบ",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                    return;
                }

                sql = " Update item set status='DL', deletedate='" + sDate + "' "
                        + " where itemcode='" + txtCode.getText().trim() + "' ";

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //-----------------------------End ลบ ยกเลิกข้อมูล----------------------------//

        // Action DB
        if (psAction.toUpperCase().equals("DELETE")) {
            if (jh_center_cn.Cpub_ActonDB(sql) == true) {
                JOptionPane.showMessageDialog(rootPane, sTitle + " เรียบร้อย", "ตรวจสอบ",
                        JOptionPane.INFORMATION_MESSAGE, null);
                pbAction = true;
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, sTitle + " เรียบร้อย", "ตรวจสอบ",
                    JOptionPane.INFORMATION_MESSAGE, null);
            pbAction = true;
            this.dispose();
        }

    }//GEN-LAST:event_cmdSaveActionPerformed

        private void updateDocSub(){
            String sSql = "";
            sSql = "Update docsub Set gname='" + txtGName.getText().trim() + "' "
                    + " where itemcode='" + txtCode.getText().trim() + "' ";   
          
            jh_center_cn.Cpub_ActonDB(sSql);
            System.out.println(sSql);
    }
    
    private void updateStockMgnt(){
            String sSql = "";
            sSql = "Update stockmgnt Set gname='" + txtGName.getText().trim() + "' "
                    + " where itemcode='" + txtCode.getText().trim() + "' ";   
          
            jh_center_cn.Cpub_ActonDB(sSql);
            System.out.println(sSql);
    }
    
    private void cmdUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUnitActionPerformed
        FrmUnit_List Frm = new FrmUnit_List(null, true);
        Frm.psCode = txtUCode.getText();
        Frm.psName = txtUName.getText();
        Frm.setModal(true);
        jh_center_cn.Cpuv_ScreenFormJDialog(Frm);
        if (!Frm.psCode.toString().equals("")) {
            txtUCode.setText(Frm.psCode);
            txtUName.setText(Frm.psName);
        }
    }//GEN-LAST:event_cmdUnitActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
        //lblShowMoney.setBackground(Color.BLACK);
        lblShowMoney.setForeground(Color.BLUE);
        
        cmdSave.setEnabled(true);
        cmdClear.setEnabled(true);
        lblShowMoney.setEnabled(true);
        pbAction = false;

        if (psAction.toUpperCase().endsWith("ADD")) {

            //Wprv_AutoRun();
            Wprv_LockTool(true);
            txtCode.setEnabled(true);
            
        } else if (psAction.toUpperCase().endsWith("EDIT")) {

            Wprv_ShowDB();
            Wprv_LockTool(true);
            txtCode.setEnabled(false);
            cmdClear.setEnabled(false);

        } else if (psAction.toUpperCase().endsWith("DELETE")) {

            Wprv_ShowDB();
            Wprv_LockTool(false);

        } else if (psAction.toUpperCase().endsWith("VIEW")) {

            Wprv_ShowDB();
            Wprv_LockTool(false);
            cmdSave.setEnabled(false);
            cmdClear.setEnabled(false);
            
            txtCode.setEnabled(true);
            txtGName.setEnabled(true);
            txtTName.setEnabled(true);
            txtUCode.setEnabled(true);
            txtUName.setEnabled(true);
            txtBCode.setEnabled(true);
            txtBName.setEnabled(true);
            lblShowMoney.setEnabled(true);
        
            txtCode.setEditable(false);
            txtGName.setEditable(false);
            txtTName.setEditable(false);
            txtUCode.setEditable(false);
            txtUName.setEditable(false);
            txtBCode.setEditable(false);
            txtBName.setEditable(false);
            lblShowMoney.setEditable(false);

        } else {
            cmdSave.setEnabled(false);
            cmdClear.setEnabled(false);
        }

        tableModel = (DefaultTableModel) dgvShow.getModel();
        
        dgvShow.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        dgvShow.getTableHeader().setForeground(new Color(255, 255, 255));
        dgvShow.getTableHeader().setBackground(new Color(0, 0, 102));
        dgvShow.setAutoResizeMode(dgvShow.AUTO_RESIZE_ALL_COLUMNS);

        TableColumn col1 = dgvShow.getColumnModel().getColumn(0);
        col1.setPreferredWidth(50);

        TableColumn col2 = dgvShow.getColumnModel().getColumn(1);
        col2.setPreferredWidth(248);

        TableColumn col3 = dgvShow.getColumnModel().getColumn(2);
        col3.setPreferredWidth(30);

        TableColumn col4 = dgvShow.getColumnModel().getColumn(3);
        col4.setPreferredWidth(40);

        TableColumn col5 = dgvShow.getColumnModel().getColumn(4);
        col5.setPreferredWidth(50);

        TableColumn col6 = dgvShow.getColumnModel().getColumn(4);
        col6.setPreferredWidth(50);

        if (!txtCode.getText().trim().equals("")) {
            Wprv_ShowDBMgnt();
        }

    }//GEN-LAST:event_formWindowOpened

    private void cmdClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdClearActionPerformed
        Wprv_ClearDataGridStock();
        Wprv_ClearData();
    }//GEN-LAST:event_cmdClearActionPerformed

    private void cmdBaseTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBaseTypeActionPerformed
        FrmBase_List Frm = new FrmBase_List(null, true);
        Frm.psCode = txtBCode.getText();
        Frm.psName = txtBName.getText();
        Frm.setModal(true);
        jh_center_cn.Cpuv_ScreenFormJDialog(Frm);
        if (!Frm.psCode.toString().equals("")) {
            txtBCode.setText(Frm.psCode);
            txtBName.setText(Frm.psName);
        }
    }//GEN-LAST:event_cmdBaseTypeActionPerformed

    private void cmdStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdStartActionPerformed
        jh_center_cn.piPage = 0;
        Wprv_ShowDBMgnt();
    }//GEN-LAST:event_cmdStartActionPerformed

    private void cmdBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBackActionPerformed
        jh_center_cn.piPage = jh_center_cn.piPage - 1;
        if (jh_center_cn.piPage < 0) {
            jh_center_cn.piPage = 0;
        }
        Wprv_ShowDBMgnt();
    }//GEN-LAST:event_cmdBackActionPerformed

    private void cmdWalkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdWalkActionPerformed
        if (jh_center_cn.piRowFloor > 0) {
            jh_center_cn.piPage = jh_center_cn.piPage+1;
        }
        Wprv_ShowDBMgnt();
    }//GEN-LAST:event_cmdWalkActionPerformed

    private void cmdEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEndActionPerformed
        if (jh_center_cn.piRowFloor > 0) {
            jh_center_cn.piPage = jh_center_cn.piPageGet+1;
        }
        Wprv_ShowDBMgnt();
    }//GEN-LAST:event_cmdEndActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmItem_Save dialog = new FrmItem_Save(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton cmdBack;
    private javax.swing.JButton cmdBaseType;
    private javax.swing.JButton cmdClear;
    private javax.swing.JButton cmdClose;
    private javax.swing.JButton cmdEnd;
    private javax.swing.JButton cmdSave;
    private javax.swing.JButton cmdStart;
    private javax.swing.JButton cmdUnit;
    private javax.swing.JButton cmdWalk;
    private javax.swing.JTable dgvShow;
    private javax.swing.JFileChooser fiFileBrown;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JTextField lblShowMoney;
    private javax.swing.JLabel lblTitlePage;
    private javax.swing.JTextField txtBCode;
    private javax.swing.JTextField txtBName;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtGName;
    private javax.swing.JTextField txtTName;
    private javax.swing.JTextField txtUCode;
    private javax.swing.JTextField txtUName;
    // End of variables declaration//GEN-END:variables
}
