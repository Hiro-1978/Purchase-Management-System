package purchase.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class FrmDocument_Save extends javax.swing.JDialog {

    jh_center jh_center_cn = new jh_center();
    int iRowClick = 0;
    public String psAction = "";
    public String psDocNo = "";
    public boolean pbAction = false;
    DecimalFormat df = null;
    Date dNow = new Date();

    /*-----------------------------------*/
    //Method
    //Sum Cost
    public void Wprv_SumCell() {
        if (psAction.equals("ADD") || psAction.equals("EDIT")) {
            df = new DecimalFormat("#0.00"); //#,##0.00
            if (!dgvShow.getValueAt(dgvShow.getSelectedRow(), 0).toString().equals("")) {
                //ถ้ารหัสสินค้าใน Column ที่ 0 ไม่เป็นค่าว่าง
                double dNum = 0;
                double dAvg = 0;
                if (!dgvShow.getValueAt(dgvShow.getSelectedRow(), 3).toString().equals("")) {
                    //ถ้าจำนวนสินค้าใน Column ที่ 3 ไม่เป็นค่าว่าง ให้ทำราคารวมทั้งหมดออกมา
                    try {
                        dNum = Double.valueOf(dgvShow.getValueAt(dgvShow.getSelectedRow(), 3).toString());
                        dgvShow.setValueAt(df.format(Double.valueOf(dgvShow.getValueAt(
                                dgvShow.getSelectedRow(), 3).toString())), dgvShow.getSelectedRow(), 3);
                        // ได้ค่า dNum พร้อมแปลงค่าให้เป็นตัวเลขออกมา
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(rootPane, "กรุณากรอกข้อมูลเป็นตัวเลข", "ตรวจสอบ",
                                JOptionPane.ERROR_MESSAGE);
                        dNum = 0;
                        dgvShow.setValueAt("", dgvShow.getSelectedRow(), 3);
                        return;
                    }
                }

                // Column ที่ 4 ราคา/หน่วย
                if (!dgvShow.getValueAt(dgvShow.getSelectedRow(), 4).toString().equals("")) {
                    try {
                        dAvg = Double.valueOf(dgvShow.getValueAt(dgvShow.getSelectedRow(), 4).toString());
                        dgvShow.setValueAt(df.format(Double.valueOf(dgvShow.getValueAt(
                                dgvShow.getSelectedRow(), 4).toString())), dgvShow.getSelectedRow(), 4);
                        // ได้ค่า dAvg พร้อมแปลงค่าให้เป็นตัวเลขออกมา
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(rootPane, "กรุณากรอกข้อมูลเป็นตัวเลข", "ตรวจสอบ",
                                JOptionPane.ERROR_MESSAGE);
                        dAvg = 0;
                        dgvShow.setValueAt("", dgvShow.getSelectedRow(), 4);
                        return;
                    }
                }

                //นำมาคูณกัน เพื่อใส่ Column ราคารวม
                double dAmount = dNum * dAvg;
                dgvShow.setValueAt(String.valueOf(dAmount), dgvShow.getSelectedRow(), 5);
                dgvShow.setValueAt(df.format(Double.valueOf(dgvShow.getValueAt(
                        dgvShow.getSelectedRow(), 5).toString())), dgvShow.getSelectedRow(), 5);
                //ถ้ากรอกค่าราคาเป็น 0 มา ให้ Set เป็นค่าว่างก่อน
                if (dgvShow.getValueAt(dgvShow.getSelectedRow(), 5).toString().equals("0.00")) {
                    dgvShow.setValueAt("0.00", dgvShow.getSelectedRow(), 5);
                }
                
                //ราคารวม
                double dTotalAmount = 0;
                for (int iR = 0; iR < dgvShow.getRowCount(); iR++) {
                    try {
                        if (dgvShow.getValueAt(iR, 5) != null) {
                            if (!dgvShow.getValueAt(iR, 5).toString().equals("")) {
                                dTotalAmount = dTotalAmount + Double.valueOf(dgvShow.getValueAt(iR, 5).toString().trim());
                            } else {
                                dTotalAmount = 0;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                
                txtTotalAmount.setText(String.valueOf(df.format(dTotalAmount)));
                        
                /*if (txtDiscount.getText().trim().equals("")) {
                        txtDiscount.setText("0.00");
                }

                double dDiscount = Double.parseDouble(txtDiscount.getText());
                
                if (txtVat.getText().trim().equals("")) {
                        txtVat.setText("0.00");
                } 
                
                double dVat = Double.parseDouble(txtVat.getText());
                
                //ราคารวมทั้งหมด
                double dAmountAll = 0;
                        
                    if (dTotalAmount != 0) {
                        dAmountAll = (dTotalAmount - dDiscount) + dVat;
                    } else {
                        dAmountAll = 0;
                    }
                txtGtotal.setText(String.valueOf(df.format(dAmountAll)));*/
            }
        }
    }

    //----------------------------------------------End Sum Cost ------------------------------------
    // Lock Tool
    private void Wprv_LockTool(boolean _bCheck) {

        txtVCode.setEnabled(_bCheck);
        txtVName.setEnabled(_bCheck);
        cmdVender.setEnabled(_bCheck);
        txtQnumber.setEnabled(_bCheck);
        txtRnumber.setEnabled(_bCheck);
        dgvShow.setEnabled(_bCheck);
        txtTotalAmount.setEnabled(_bCheck);
        txtDiscount.setEnabled(_bCheck);
        txtVat.setEnabled(_bCheck);
        txtGtotal.setEnabled(_bCheck);
        txtMark.setEnabled(_bCheck);
        mnsAdd.setEnabled(_bCheck);
        mnsDelete.setEnabled(_bCheck);
        cmdSave.setEnabled(_bCheck);
        cmdCal.setEnabled(_bCheck);
        cmbVat.setEnabled(_bCheck);
    }

    //Key Invoice AutoRun
    private void Wprv_AutoRun() {
        String sDocNo = "";
        try {
            sDocNo = jh_center_cn.Cpus_AutoRun("docno", "dochead", "PR" + jh_center.sYear.trim()
                    + jh_center.sMonth.trim() + "-", "000"); // PR6401-001 (INV+ปี+เดือน+เลขที่ใบ Invoice)
            //txtDocNo.setText(sDocNo);    
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtDocNo.setText(sDocNo);
    }

    //ShowData Head
    private void Wprv_ShowDB() {
        try {
            if (psAction.equals("ADD") || psAction.equals("EDIT") || psAction.equals("CONF")) {
                df = new DecimalFormat("#0.00"); //#0.00
            } else {
                df = new DecimalFormat("#,##0.00");
            }
            txtDocNo.setText(psDocNo.trim());
            String sSql = " Select * From dochead Where docno='" + txtDocNo.getText().trim() + "' ";
            jh_center_cn = new jh_center();
            ResultSet rs = jh_center_cn.Cpuds_ShowData(sSql);
            if (rs.next()) {
                txtDocDate.setText(rs.getString("docdate"));
                txtVCode.setText(rs.getString("venderid"));
                txtVName.setText(rs.getString("vname"));
                txtQnumber.setText(rs.getString("quonumber"));
                txtRnumber.setText(rs.getString("recnumber"));
                txtTotalAmount.setText(df.format(Double.valueOf(rs.getString("totalamount").toString())));
                txtDiscount.setText(df.format(Double.valueOf(rs.getString("discount").toString())));
                txtVat.setText(df.format(Double.valueOf(rs.getString("vat").toString())));
                txtGtotal.setText(df.format(Double.valueOf(rs.getString("grandamount").toString())));
                txtMark.setText(rs.getString("mark"));
                if(rs.getString("vtype").trim().equals("Excl")){
                    cmbVat.setSelectedIndex(0);
                }else if(rs.getString("vtype").trim().equals("Incl")){
                    cmbVat.setSelectedIndex(1);
                }else {
                    cmbVat.setSelectedIndex(2);
                }
                
            //System.out.println("Wprv_ShowDB()");    
            //System.out.println(sSql);
            }
            Wrpv_ShowDBSub();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //ShowData Detail (Sub)
    private void Wrpv_ShowDBSub() {
        try {
            String sSql = " Select * From docsub Where docno='" + txtDocNo.getText().trim() + "' ";
            //String sSql = " Select * From docsub Where docno='PR6409-001'";
            
            //System.out.println("Wprv_ShowDBSub()");    
            System.out.println(sSql);
            
            jh_center_cn = new jh_center();
            ResultSet rs = jh_center_cn.Cpuds_ShowData(sSql);
            int iRow = 0;
            if (psAction.equals("ADD") || psAction.equals("EDIT") || psAction.equals("CONF")) {
                df = new DecimalFormat("#0.00"); //#0.00
            } else {
                df = new DecimalFormat("#,##0.00");
            }
            while (rs.next()) {
                
                dgvShow.setValueAt(rs.getString("itemcode"), iRow, 0);
                dgvShow.setValueAt(rs.getString("gname"), iRow, 1);
                dgvShow.setValueAt(rs.getString("bunitname"), iRow, 2);
                dgvShow.setValueAt(df.format(Double.valueOf(rs.getString("qty"))), iRow, 3);
                dgvShow.setValueAt(df.format(Double.valueOf(rs.getString("avgamount"))), iRow, 4);
                dgvShow.setValueAt(df.format(Double.valueOf(rs.getString("amount"))), iRow, 5);
	 	 	 	 
                iRow++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Save Data Table Detail (Sub)
    private void Wprv_SaveSub() {
        for (int iR = 0; iR < dgvShow.getRowCount(); iR++) {
            if (dgvShow.getValueAt(iR, 5) != null) {
                if (!dgvShow.getValueAt(iR, 5).toString().equals("")) {
                    String sSql = " INSERT INTO docsub(docno, itemcode, gname, bunitname, qty, avgamount, amount) "
                            + " VALUES('" + txtDocNo.getText().trim() + "',"
                            + " '" + dgvShow.getValueAt(iR, 0).toString().trim() + "', "
                            + " '" + dgvShow.getValueAt(iR, 1).toString().trim() + "', "
                            + " '" + dgvShow.getValueAt(iR, 2).toString().trim() + "', "
                            // เป็นค่าตัวเลข เอา '' เดี่ยวออก
                            + " " + dgvShow.getValueAt(iR, 3).toString().trim() + ", "
                            + " " + dgvShow.getValueAt(iR, 4).toString().trim() + ", "
                            + " " + dgvShow.getValueAt(iR, 5).toString().trim() + ") ";
                            //-------------------------

                    //System.out.println("Wprv_SaveSub()");    
                    //System.out.println(sSql);
                    
                    jh_center_cn.Cpub_ActonDB(sSql);
                }
            }
        }
    }

    private void Wprv_SaveStockMgnt() {
        DateFormat datDate = new SimpleDateFormat("yyyy/MM/dd");
        //DateFormat datDate = new SimpleDateFormat("dd/MM/yyyy");
        for (int iR = 0; iR < dgvShow.getRowCount(); iR++) {
            if (dgvShow.getValueAt(iR, 5) != null) {
                if (!dgvShow.getValueAt(iR, 5).toString().equals("")) {
                    String sSql = " INSERT INTO stockmgnt(docno, venderid, vname, confdate, itemcode, gname, "
                            + "bunitname, qty, avgamount, amount) "
                            + " VALUES('" + txtDocNo.getText().trim() + "', "
                            + " '" + txtVCode.getText().trim() + "', "
                            + " '" + txtVName.getText().trim() + "', "
                            + " '" + datDate.format(dNow).toString().trim() + "',"
                            + " '" + dgvShow.getValueAt(iR, 0).toString().trim() + "', "
                            + " '" + dgvShow.getValueAt(iR, 1).toString().trim() + "', "
                            + " '" + dgvShow.getValueAt(iR, 2).toString().trim() + "', "
                            // เป็นค่าตัวเลข เอา '' เดี่ยวออก
                            + " " + dgvShow.getValueAt(iR, 3).toString().trim() + ", "
                            + " " + dgvShow.getValueAt(iR, 4).toString().trim() + ", "
                            + " " + dgvShow.getValueAt(iR, 5).toString().trim() + ") ";
                            //-------------------------

                    //System.out.println("SaveStockMgnt()");    
                    //System.out.println(sSql);
                    
                    jh_center_cn.Cpub_ActonDB(sSql);
                }
            }
        }
    }

    public FrmDocument_Save(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mnsMenu = new javax.swing.JPopupMenu();
        mnsAdd = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnsDelete = new javax.swing.JMenuItem();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMark = new javax.swing.JTextArea();
        jToolBar1 = new javax.swing.JToolBar();
        cmdClose = new javax.swing.JButton();
        cmdSave = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dgvShow = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtVCode = new javax.swing.JTextField();
        txtVName = new javax.swing.JTextField();
        cmdVender = new javax.swing.JButton();
        txtQnumber = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtRnumber = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDocNo = new javax.swing.JTextField();
        txtDocDate = new javax.swing.JTextField();
        txtVat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTotalAmount = new javax.swing.JTextField();
        txtDiscount = new javax.swing.JTextField();
        txtGtotal = new javax.swing.JTextField();
        cmdCal = new javax.swing.JButton();
        cmbVat = new javax.swing.JComboBox();

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

        mnsDelete.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        mnsDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/close Jframe.png"))); // NOI18N
        mnsDelete.setText("ยกเลิกรายการ");
        mnsDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnsDeleteActionPerformed(evt);
            }
        });
        mnsMenu.add(mnsDelete);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("บันทึกใบรับสินค้า");
        setPreferredSize(new java.awt.Dimension(947, 785));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMark.setColumns(20);
        txtMark.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtMark.setRows(5);
        jScrollPane2.setViewportView(txtMark);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 591, -1, 139));

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        cmdClose.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
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

        cmdSave.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
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

        getContentPane().add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 50));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Vat");
        jLabel6.setPreferredSize(new java.awt.Dimension(50, 29));
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 665, 40, -1));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(908, 402));

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
                "รหัสสินค้า", "ชื่อสินค้า", "หน่วยสินค้า", "จำนวน", "ราคา/หน่วย", "ราคารวม"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dgvShow.setPreferredSize(new java.awt.Dimension(905, 399));
        dgvShow.setRowHeight(20);
        dgvShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dgvShowMouseClicked(evt);
            }
        });
        dgvShow.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                dgvShowInputMethodTextChanged(evt);
            }
        });
        dgvShow.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dgvShowKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(dgvShow);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 147, -1, 437));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("ข้อมูลบริษัท");
        jLabel3.setPreferredSize(new java.awt.Dimension(50, 29));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("ใบเสนอราคา");
        jLabel4.setPreferredSize(new java.awt.Dimension(50, 29));

        txtVCode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtVCode.setPreferredSize(new java.awt.Dimension(50, 29));

        txtVName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtVName.setPreferredSize(new java.awt.Dimension(50, 29));

        cmdVender.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmdVender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        cmdVender.setPreferredSize(new java.awt.Dimension(73, 29));
        cmdVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdVenderActionPerformed(evt);
            }
        });

        txtQnumber.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtQnumber.setPreferredSize(new java.awt.Dimension(6, 29));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("ใบแจ้งหนี้/ใบเสร็จ");
        jLabel8.setPreferredSize(new java.awt.Dimension(50, 29));

        txtRnumber.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtRnumber.setPreferredSize(new java.awt.Dimension(6, 29));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtVCode, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtVName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdVender, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtQnumber, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtRnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdVender, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtVCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtVName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 55, -1, 85));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("เลขที่เอกสาร");
        jLabel1.setPreferredSize(new java.awt.Dimension(50, 29));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("วันที่บันทึกเอกสาร");
        jLabel2.setPreferredSize(new java.awt.Dimension(50, 29));

        txtDocNo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDocNo.setPreferredSize(new java.awt.Dimension(50, 29));

        txtDocDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDocDate.setPreferredSize(new java.awt.Dimension(50, 29));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDocNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDocDate, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDocNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDocDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 55, -1, -1));

        txtVat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtVat.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtVat.setMaximumSize(new java.awt.Dimension(280, 29));
        txtVat.setMinimumSize(new java.awt.Dimension(280, 29));
        txtVat.setPreferredSize(new java.awt.Dimension(280, 29));
        getContentPane().add(txtVat, new org.netbeans.lib.awtextra.AbsoluteConstraints(641, 665, 170, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("หมายเหตุ");
        jLabel5.setPreferredSize(new java.awt.Dimension(50, 29));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 591, 86, 19));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("ราคารวมทั้งหมด");
        jLabel7.setPreferredSize(new java.awt.Dimension(50, 29));
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 701, 149, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("ราคาไม่รวม Vat");
        jLabel9.setPreferredSize(new java.awt.Dimension(65, 29));
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(483, 591, 140, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("ส่วนลด");
        jLabel10.setPreferredSize(new java.awt.Dimension(65, 29));
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(558, 627, -1, -1));

        txtTotalAmount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotalAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalAmount.setPreferredSize(new java.awt.Dimension(82, 29));
        getContentPane().add(txtTotalAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(641, 591, 170, -1));

        txtDiscount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDiscount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDiscount.setPreferredSize(new java.awt.Dimension(6, 29));
        getContentPane().add(txtDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(641, 627, 170, -1));

        txtGtotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtGtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtGtotal.setPreferredSize(new java.awt.Dimension(6, 29));
        getContentPane().add(txtGtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(641, 701, 170, -1));

        cmdCal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Cal-103x140.png"))); // NOI18N
        cmdCal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCalActionPerformed(evt);
            }
        });
        getContentPane().add(cmdCal, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 590, 103, 140));

        cmbVat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbVat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Excl", "Incl", "NoVat" }));
        cmbVat.setMinimumSize(new java.awt.Dimension(80, 29));
        cmbVat.setPreferredSize(new java.awt.Dimension(69, 29));
        getContentPane().add(cmbVat, new org.netbeans.lib.awtextra.AbsoluteConstraints(566, 665, 70, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed
        pbAction = false;
        this.dispose();
    }//GEN-LAST:event_cmdCloseActionPerformed

    private void cmdSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveActionPerformed

        if (txtDocNo.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "กรุณากรอก เลขที่เอกสาร", "ตรวจสอบ",
                    JOptionPane.WARNING_MESSAGE, null);
            txtDocNo.requestFocusInWindow();
            return;
        }

        if (txtDocDate.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "กรุณากรอก วันที่เอกสาร", "ตรวจสอบ",
                    JOptionPane.WARNING_MESSAGE, null);
            txtDocDate.requestFocusInWindow();
            return;
        }

        if (txtVCode.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "กรุณาเลือก บริษัท", "ตรวจสอบ",
                    JOptionPane.WARNING_MESSAGE, null);
            txtVCode.requestFocusInWindow();
            return;
        }

        try {
            if (dgvShow.getValueAt(0, 0).toString().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "กรุณาเลือกไอเทมที่ต้องการก่อน", "ตรวจสอบ",
                        JOptionPane.WARNING_MESSAGE, null);
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "กรุณาเลือกไอเทมที่ต้องการก่อน", "ตรวจสอบ",
                    JOptionPane.WARNING_MESSAGE, null);
            return;
        }

        try {
            for (int iR = 0; iR < dgvShow.getRowCount(); iR++) {
                if (!dgvShow.getValueAt(iR, 0).toString().equals("")) {
                    if (dgvShow.getValueAt(iR, 3).toString().equals("")) {
                        JOptionPane.showMessageDialog(rootPane, "กรุณากรอกจำนวน ไอเทมก่อน", "ตรวจสอบ",
                                JOptionPane.WARNING_MESSAGE, null);
                        return;
                    }

                    if (dgvShow.getValueAt(iR, 4).toString().equals("")) {
                        JOptionPane.showMessageDialog(rootPane, "กรุณากรอก ราคา/หน่วย ไอเทมก่อน", "ตรวจสอบ",
                                JOptionPane.WARNING_MESSAGE, null);
                        return;
                    }

                    if (dgvShow.getValueAt(iR, 5).toString().equals("")) {
                        JOptionPane.showMessageDialog(rootPane, "กรุณากรอกราคารวม ไอเทมก่อน", "ตรวจสอบ",
                                JOptionPane.WARNING_MESSAGE, null);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            //กันไว้เฉยๆ ไม่ต้องทำอะไร
            e.printStackTrace();
        }
        
        if (txtDiscount.getText().trim().equals("")) {
               txtDiscount.setText("0.00");
        }
        
        if (txtVat.getText().trim().equals("")) {
            txtVat.setText("0.00");
        }        

        String sSql = "";
        String sTitle = "";
        DateFormat datDate = new SimpleDateFormat("yyyy/MM/dd");
        //DateFormat datDate = new SimpleDateFormat("dd/MM/yyyy");
        if (psAction.toUpperCase().endsWith("ADD")) {

            sTitle = "เพิ่มข้อมูล";
            sSql = " INSERT INTO dochead(docno,docdate,venderid,vname,quonumber,recnumber,status,"
                    + "editdate,deletedate,confdate,totalamount,discount,vat,grandamount,mark,vtype)"
                    + " VALUES('" + txtDocNo.getText().trim() + "',"
                    + " '" + datDate.format(dNow).toString().trim() + "',"
                    + " '" + txtVCode.getText().trim() + "',"
                    + " '" + txtVName.getText().trim() + "',"
                    + " '" + txtQnumber.getText().trim() + "',"
                    + " '" + txtRnumber.getText().trim() + "',"
                    + " 'WC',"
                    + " '" + datDate.format(dNow).toString().trim() + "',"
                    + " '1990-01-01',"
                    + " '1990-01-01'," //01-01-1990 1990-01-01
                    + " '" + txtTotalAmount.getText().toString().trim() + "', "
                    + " '" + txtDiscount.getText().toString().trim() + "', "
                    + " '" + txtVat.getText().toString().trim() + "', "
                    + " '" + txtGtotal.getText().toString().trim() + "', "
                    + " '" + txtMark.getText().trim() + "', "
                    + " '" + cmbVat.getSelectedItem().toString().trim() + "') ";
           
            //System.out.println("cmdSaveAction");
            System.out.println(sSql);
            
            Wprv_SaveSub();

        } else if (psAction.toUpperCase().endsWith("EDIT")) {

            sTitle = "แก้ไขข้อมูล";
            sSql = " UPDATE dochead Set "
                    + "editdate='" + datDate.format(dNow).toString().trim() + "',"
                    + "totalamount='" + txtTotalAmount.getText().trim() + "', "
                    + "discount='" + txtDiscount.getText().trim() + "', "
                    + "vat='" + txtVat.getText().trim() + "', "
                    + "grandamount='" + txtGtotal.getText().trim() + "', "
                    + "mark='" + txtMark.getText().trim() + "', "
                    + "vtype='" + cmbVat.getSelectedItem().toString().trim() + "' "
                    + " Where docno='" + txtDocNo.getText().trim() + "' ";
            //System.out.println(sSql);
            //ลบข้อมูลเอกสารเก่าออกก่อนด้วย
            jh_center_cn.Cpub_ActonDB("Delete From docsub where docno='" + txtDocNo.getText().trim() + "'");
            //บันทึกเอกสารใหม่ที่แก้ไขเข้าไป
            Wprv_SaveSub();
                       
        } else if (psAction.toUpperCase().endsWith("DELETE")) {

            sTitle = "ยกเลิกรายการ";
            if (JOptionPane.showConfirmDialog(null, "คุณต้องการยกเลิกรายการใช่หรือไม่", "ตรวจสอบ",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                return;
            }

            sSql = "Update dochead set "
                    + "deletedate='" + datDate.format(dNow).toString().trim() + "',"
                    + "status='DL'"
                    + " Where docno='" + txtDocNo.getText().trim() + "' ";

        } else if (psAction.toUpperCase().endsWith("CONF")) {
            try{
            
               if (jh_center_cn.Cpub_CheckValueData("stockmgnt", "docno", txtDocNo.getText().trim()) == true) {
                    JOptionPane.showMessageDialog(rootPane, "เอกสารนี้ถูกบันทึกไปแล้ว", "ตรวจสอบ", 
                            JOptionPane.WARNING_MESSAGE, null);
                    return;
                }
            
            sTitle = "ยืนยันรายการ";
            if (JOptionPane.showConfirmDialog(null, "คุณต้องการยืนยันรายการใช่หรือไม่", "ตรวจสอบ",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                return;
            }

            sSql = "Update dochead set "
                    + "confdate='" + datDate.format(dNow).toString().trim() + "',"
                    + "status='CO'"
                    + " Where docno='" + txtDocNo.getText().trim() + "' ";
            
            // Save Stockmgnt หลังจาก ยืนยันรายการ
            System.out.println(sSql);
            
            Wprv_SaveStockMgnt();
            
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //-------------------- จัดการตารางหลัก ------------------------------------------------------------------------------
        if (jh_center_cn.Cpub_ActonDB(sSql) == true) {
            JOptionPane.showMessageDialog(rootPane, sTitle + " เรียบร้อยแล้ว", "ตรวจสอบ",
                    JOptionPane.INFORMATION_MESSAGE, null);
            pbAction = true;
            this.dispose();
        } else {
            return;
        }

    }//GEN-LAST:event_cmdSaveActionPerformed

    private void dgvShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgvShowMouseClicked
        dgvShow.setComponentPopupMenu(mnsMenu);
        if (evt.getClickCount() == 2) {
            if (txtVCode.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "กรุณาเลือกบริษัทก่อน", "ตรวจสอบ",
                        JOptionPane.WARNING_MESSAGE, null);
                return;
            }

            int iRowsValue = 0;
            //เช็ค Cell หรือ Column จำนวนหรือราคา ไหนบ้าง? ที่ยังไม่ได้กรอก
            for (int iR = 0; iR < dgvShow.getRowCount() - 1; iR++) {
                try {
                    if (!dgvShow.getValueAt(iR, 0).toString().equals("")) {
                        iRowsValue = iRowsValue + 1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (iRowsValue != 0) {
                try {
                    if (dgvShow.getValueAt(iRowsValue - 1, 5).toString().equals("")) {
                        JOptionPane.showMessageDialog(rootPane, "กรุณาคิดราคารวม \n คลิ๊กที่แถวที่ไม่มีราคารวมก่อน", "ตรวจสอบ",
                                JOptionPane.ERROR_MESSAGE, null);
                        return;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, "กรุณาคิดราคารวม \n คลิ๊กที่แถวที่ไม่มีราคารวมก่อน", "ตรวจสอบ",
                            JOptionPane.ERROR_MESSAGE, null);
                    return;
                }
            }

            FrmItem_List Frm = new FrmItem_List(null, true);
            Frm.setModal(true);
            jh_center_cn.Cprv_ScreenFormJDialogCenter(Frm);

            if (!Frm.psCode.toString().equals("")) {
                /*for (int i = 0; i < dgvShow.getRowCount(); i++) {
                    try {
                        if (Frm.psCode.toString().equals(dgvShow.getValueAt(i, 0).toString())) {
                            JOptionPane.showMessageDialog(rootPane, "สินค้าซ้ำกรุณาเลือกใหม่", "ตรวจสอบ",
                                    JOptionPane.WARNING_MESSAGE, null);
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }*/

                dgvShow.setValueAt(Frm.psCode, iRowsValue, 0);
                dgvShow.setValueAt(Frm.psGName, iRowsValue, 1);
                dgvShow.setValueAt(Frm.psUnitName, iRowsValue, 2);           

            }
        }else{
            Wprv_SumCell();
        }
    }//GEN-LAST:event_dgvShowMouseClicked

    private void dgvShowInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_dgvShowInputMethodTextChanged
        // เมื่อมีการแก้ไขอะไรใหม่ๆ ก็จะต้องมีการ Sum มูลค่าใหม่อีกครั้งเสมอ
        Wprv_SumCell();
    }//GEN-LAST:event_dgvShowInputMethodTextChanged

    private void cmdVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdVenderActionPerformed

        FrmVender_List Frm = new FrmVender_List(null, true);
        Frm.psCode = txtVCode.getText();
        Frm.psName = txtVName.getText();
        Frm.setModal(true);

        jh_center_cn.Cprv_ScreenFormJDialogSub(Frm);
        if (!Frm.psCode.toString().equals("")) {
            txtVCode.setText(Frm.psCode);
            txtVName.setText(Frm.pvName);
        }
    }//GEN-LAST:event_cmdVenderActionPerformed

    private void mnsAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnsAddActionPerformed
        if (txtVCode.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "กรุณาเลือกบริษัทก่อน", "ตรวจสอบ",
                    JOptionPane.WARNING_MESSAGE, null);
            return;
        }

        int iRowsValue = 0;
        //เช็ค Cell หรือ Column จำนวนหรือราคา ไหนบ้าง? ที่ยังไม่ได้กรอก
        for (int iR = 0; iR < dgvShow.getRowCount() - 1; iR++) {
            try {
                if (!dgvShow.getValueAt(iR, 0).toString().equals("")) {
                    iRowsValue = iRowsValue + 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (iRowsValue != 0) {
            try {
                if (dgvShow.getValueAt(iRowsValue - 1, 5).toString().equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "กรุณาคิดราคารวม \n คลิ๊กที่แถวที่ไม่มีราคารวมก่อน", "ตรวจสอบ",
                            JOptionPane.ERROR_MESSAGE, null);
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "กรุณาคิดราคารวม \n คลิ๊กที่แถวที่ไม่มีราคารวมก่อน", "ตรวจสอบ",
                        JOptionPane.ERROR_MESSAGE, null);
                return;
            }
        }

        FrmItem_List Frm = new FrmItem_List(null, true);
        Frm.setModal(true);
        jh_center_cn.Cprv_ScreenFormJDialogCenter(Frm);

        if (!Frm.psCode.toString().equals("")) {
            /*for (int i = 0; i < dgvShow.getRowCount(); i++) {
                try {
                    if (Frm.psCode.toString().equals(dgvShow.getValueAt(i, 0).toString())) {
                        JOptionPane.showMessageDialog(rootPane, "สินค้าซ้ำกรุณาเลือกใหม่", "ตรวจสอบ",
                                JOptionPane.WARNING_MESSAGE, null);
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }*/

            dgvShow.setValueAt(Frm.psCode, iRowsValue, 0);
            dgvShow.setValueAt(Frm.psGName, iRowsValue, 1);
            dgvShow.setValueAt(Frm.psUnitName, iRowsValue, 2);           

        }
    }//GEN-LAST:event_mnsAddActionPerformed

    private void mnsDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnsDeleteActionPerformed
        //เช็คว่าในแถวแรกเป็นค่าว่างหรือเปล่า? ก่อนจะลบ
        if (!dgvShow.getValueAt(dgvShow.getSelectedRow(), 0).toString().equals("")) {
            ((DefaultTableModel) dgvShow.getModel()).removeRow(dgvShow.getSelectedRow());
            ((DefaultTableModel) dgvShow.getModel()).addRow(new Vector());
            //ลบออกแล้วก็ต้องรวมมูลค่าใหม่อีกครั้ง
            Wprv_SumCell();
        }
    }//GEN-LAST:event_mnsDeleteActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        //txtGtotal.setBackground(Color.BLACK);
        txtGtotal.setForeground(Color.BLUE);

        //DefaultTableModel tableModel = (DefaultTableModel) dgvShow.getModel();
        dgvShow.setAutoResizeMode(dgvShow.AUTO_RESIZE_ALL_COLUMNS);
                        
        dgvShow.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        dgvShow.getTableHeader().setForeground(new Color(255, 255, 255));
        dgvShow.getTableHeader().setBackground(new Color(0, 0, 102));      


        TableColumn col1 = dgvShow.getColumnModel().getColumn(0);
        col1.setPreferredWidth(100);

        TableColumn col2 = dgvShow.getColumnModel().getColumn(1);
        col2.setPreferredWidth(200);

        TableColumn col3 = dgvShow.getColumnModel().getColumn(2);
        col3.setPreferredWidth(100);

        TableColumn col4 = dgvShow.getColumnModel().getColumn(3);
        col4.setPreferredWidth(100);

        TableColumn col5 = dgvShow.getColumnModel().getColumn(4);
        col5.setPreferredWidth(100);

        TableColumn col6 = dgvShow.getColumnModel().getColumn(5);
        col6.setPreferredWidth(100);

        DateFormat datDateNow = new SimpleDateFormat("yyyy/MM/dd");
        txtDocDate.setText(datDateNow.format(dNow).toString());
        pbAction = false;

        txtDocNo.setEnabled(false);
        txtDocDate.setEnabled(false);
        cmdSave.setEnabled(false);        

        if (psAction.toUpperCase().endsWith("ADD")) {
            Wprv_AutoRun();
            Wprv_LockTool(true);
        } else if (psAction.toUpperCase().endsWith("EDIT")) {
            Wprv_ShowDB();
            Wprv_LockTool(true);
        } else if (psAction.toUpperCase().endsWith("DELETE")) {
            Wprv_ShowDB();
            Wprv_LockTool(false);
            cmdSave.setEnabled(true);
            cmdCal.setEnabled(false);
            cmbVat.setEnabled(false);
            
        } else if (psAction.toUpperCase().endsWith("CONF")) {
            Wprv_ShowDB();
            Wprv_LockTool(false);
            cmdSave.setEnabled(true);
            cmdCal.setEnabled(false);
            //-------------------------------------------//
            txtVCode.setEnabled(true);
            txtVName.setEnabled(true);
            txtQnumber.setEnabled(true);
            txtRnumber.setEnabled(true);
            txtTotalAmount.setEnabled(true);
            txtDiscount.setEnabled(true);
            txtVat.setEnabled(true);
            txtGtotal.setEnabled(true);
            cmbVat.setEnabled(false);
            cmdCal.setEnabled(false);
            
            txtVCode.setEditable(false);
            txtVName.setEditable(false);
            txtQnumber.setEditable(false);
            txtRnumber.setEditable(false);
            txtTotalAmount.setEditable(false);
            txtDiscount.setEditable(false);
            txtVat.setEditable(false);
            txtGtotal.setEditable(false);
            txtMark.setEditable(false);
            
            //-------------------------------------------//
        } else if (psAction.toUpperCase().endsWith("VIEW")) {
            Wprv_ShowDB();
            Wprv_LockTool(false);
            cmdSave.setEnabled(false);
            //-------------------------------------------//
            txtVCode.setEnabled(true);
            txtVName.setEnabled(true);
            txtQnumber.setEnabled(true);
            txtRnumber.setEnabled(true);
            txtTotalAmount.setEnabled(true);
            txtDiscount.setEnabled(true);
            txtVat.setEnabled(true);
            txtGtotal.setEnabled(true);
            txtMark.setEnabled(true);
            cmbVat.setEnabled(false);
            cmdCal.setEnabled(false);
            
            txtVCode.setEditable(false);
            txtVName.setEditable(false);
            txtQnumber.setEditable(false);
            txtRnumber.setEditable(false);
            txtTotalAmount.setEditable(false);
            txtDiscount.setEditable(false);
            txtVat.setEditable(false);
            txtGtotal.setEditable(false);
            txtMark.setEditable(false);
            //-------------------------------------------//
        }

    }//GEN-LAST:event_formWindowOpened

    private void dgvShowKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dgvShowKeyPressed
        if (KeyEvent.VK_ENTER == evt.getKeyCode()) {
            Wprv_SumCell();          
        }
    }//GEN-LAST:event_dgvShowKeyPressed

    private void cmdCalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCalActionPerformed
        //Wprv_SumCell();
        DecimalFormat df = new DecimalFormat("#.##");
        //df.setRoundingMode(RoundingMode.UP);
        if (txtTotalAmount.getText().trim().equals("")) {
                txtTotalAmount.setText("0.00");
        }
        double dTotalAmount = Double.parseDouble(txtTotalAmount.getText());
        
        if (txtDiscount.getText().trim().equals("")) {
                txtDiscount.setText("0.00");
        }

        double dDiscount = Double.parseDouble(txtDiscount.getText());
                
        if (cmbVat.getSelectedIndex() == 0) {
            double de = (dTotalAmount - dDiscount)+((dTotalAmount - dDiscount)*0.07);         // ราคารวมทั้งหมด = ราคารวม+(ราคารวม*0.07)
            double ve = ((dTotalAmount - dDiscount)*0.07);                                    // Vat นอก = ราคารวม*0.07
            //double dAmountAll = de - dDiscount;
            
            txtTotalAmount.setText(String.valueOf(df.format(dTotalAmount)));
            txtVat.setText(String.valueOf(df.format(ve)));
            txtGtotal.setText(String.valueOf(df.format(de)));
            //txtGtotal.setText("Vat นอก");
        } else if (cmbVat.getSelectedIndex() == 1){
            double dn = ((dTotalAmount)*100)/107;                   // ราคารวมทั้งหมด = (ราคารวม*107)/100
            double vn = (dTotalAmount - dDiscount) - (((dTotalAmount - dDiscount)*100)/107);                 // Vat ใน = ((ราคารวม*107)/100) - ราคารวม
            double dTotalNoVat = dTotalAmount - vn;
            double dAmountAll = dTotalAmount - dDiscount;
            
            txtTotalAmount.setText(String.valueOf(df.format(dTotalNoVat)));
            txtVat.setText(String.valueOf(df.format(vn)));
            txtGtotal.setText(String.valueOf(df.format(dAmountAll)));
                    //txtGtotal.setText("Vat ใน");
        } else {
            txtVat.setText("0.00");
            double dAmountAll = 0;                        
            if (dTotalAmount != 0) {
                dAmountAll = dTotalAmount - dDiscount;
            } else {
                dAmountAll = 0;
            }  
            txtGtotal.setText(String.valueOf(df.format(dAmountAll)));
        }
               
        //double dVat = Double.parseDouble(txtVat.getText());
                
        //ราคารวมทั้งหมด
        /*double dAmountAll = 0;
                        
        if (dTotalAmount != 0) {
                dAmountAll = (dTotalAmount - dDiscount) + dVat;
        } else {
                dAmountAll = 0;
        }
        
        txtGtotal.setText(String.valueOf(df.format(dAmountAll)));*/
        
    }//GEN-LAST:event_cmdCalActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmDocument_Save dialog = new FrmDocument_Save(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox cmbVat;
    private javax.swing.JButton cmdCal;
    private javax.swing.JButton cmdClose;
    private javax.swing.JButton cmdSave;
    private javax.swing.JButton cmdVender;
    private javax.swing.JTable dgvShow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem mnsAdd;
    private javax.swing.JMenuItem mnsDelete;
    private javax.swing.JPopupMenu mnsMenu;
    private javax.swing.JTextField txtDiscount;
    private javax.swing.JTextField txtDocDate;
    private javax.swing.JTextField txtDocNo;
    private javax.swing.JTextField txtGtotal;
    private javax.swing.JTextArea txtMark;
    private javax.swing.JTextField txtQnumber;
    private javax.swing.JTextField txtRnumber;
    private javax.swing.JTextField txtTotalAmount;
    private javax.swing.JTextField txtVCode;
    private javax.swing.JTextField txtVName;
    private javax.swing.JTextField txtVat;
    // End of variables declaration//GEN-END:variables
}
