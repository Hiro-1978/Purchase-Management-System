package purchase.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class FrmDocument_Show extends javax.swing.JInternalFrame {

    jh_center jh_center_cn = new jh_center();
    private DefaultTableModel tableModel = new DefaultTableModel();
    private Object[] data = new Object[0];
    private String sCode = "";
    Locale locale = new Locale ( "th", "TH" );

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
            dgvShow.setValueAt("", iRow, 8);
            dgvShow.setValueAt("", iRow, 9);
            iRow--;
        }
    }

    //Show Data
    private void Wprv_ShowData() {
        
        dateBegin.setLocale(locale); 
        dateEnd.setLocale(locale);
        dateBegin.setDateFormatString("yyyy-MM-dd");
        dateEnd.setDateFormatString("yyyy-MM-dd");
        
        String FDate = ((JTextField) dateBegin.getDateEditor().getUiComponent()).getText().toString();
        String TDate = ((JTextField) dateEnd.getDateEditor().getUiComponent()).getText().toString(); 
        
        try {
            Wprv_ClearDataGrid();

            String sSql = "";
            String sSqlStatus = "";
            String dSql = "";

            if (chkAll.isSelected() == false) {

                if (cmbStatus.getSelectedIndex() == 0) {
                    sSqlStatus = " And status='WC' ";
                } else if (cmbStatus.getSelectedIndex() == 1) {
                    sSqlStatus = " And status='CO' ";
                } else if (cmbStatus.getSelectedIndex() == 2) {
                    sSqlStatus = " And status='DL' ";
                } else {
                    sSqlStatus = "";
                }

                if (cmbSelect.getSelectedIndex() == 0) {
                    sSql = " Where vname Like '%" + txtSearch.getText().trim() + "%' ";
                } else if (cmbSelect.getSelectedIndex() == 1){
                    sSql = " Where quonumber Like '%" + txtSearch.getText().trim() + "%' ";
                } else if (cmbSelect.getSelectedIndex() == 2){
                    sSql = " Where recnumber Like '%" + txtSearch.getText().trim() + "%' ";
                } else {
                    sSql = "";
                }
                //-----------------------------------------------------------------------------
                if (cmbStatus.getSelectedIndex() == 0) {
                    if (FDate.length() > 0 && TDate.isEmpty() && FDate.isEmpty() && TDate.length() > 0) {
                    dSql = "";
                    } else if (FDate.length() > 0 && TDate.length() > 0) {
                    dSql = " And docdate between'" + FDate + "' and '" + TDate + "' ";
                    }
                } else if (cmbStatus.getSelectedIndex() == 1) {
                    if (FDate.length() > 0 && TDate.isEmpty() && FDate.isEmpty() && TDate.length() > 0) {
                    dSql = "";
                    } else if (FDate.length() > 0 && TDate.length() > 0) {
                    dSql = " And confdate between'" + FDate + "' and '" + TDate + "' ";
                    }
                } else if (cmbStatus.getSelectedIndex() == 2) {
                    if (FDate.length() > 0 && TDate.isEmpty() && FDate.isEmpty() && TDate.length() > 0) {
                    dSql = "";
                    } else if (FDate.length() > 0 && TDate.length() > 0) {
                    dSql = " And deletedate between'" + FDate + "' and '" + TDate + "' ";
                    }
                }
            //-----------------------------------------------------------------------------  
            }
            jh_center_cn.Cpuv_GetRowPage();

            String sSqlShow = "Select docno, docdate, vname, quonumber, recnumber, "
                    + " Case When status='WC' Then 'รอส่งของ' When status='CO' Then 'ส่งของแล้ว' Else 'ยกเลิกแล้ว'  End As StatusThai, "
                    + " Case When editdate='1990-01-01' Then '' Else editdate End As EditDate, "
                    + " Case When deletedate='1990-01-01' Then '' Else deletedate End As DeleteDate, "
                    + " Case When confdate='1990-01-01' Then '' Else confdate End As ConfDate,"
                    + " mark,status "// ระบุ status เพื่อเปลี่ยนพื้นหลังเป็นสีชมพู 01-01-1990  1990-01-01
                    + " From dochead " + sSql + " " + sSqlStatus + " " + dSql + " Order By docno Desc "
                    + " Limit " + jh_center_cn.piStart + ", " + jh_center_cn.piEnd + "";
            //System.out.println(sSqlShow);       	 	 	 	 	 	 
            ResultSet rs = jh_center_cn.Cpuds_ShowData(sSqlShow);
            int iRow = 0;
            while (rs.next()) {
                
                dgvShow.setValueAt(rs.getString("docno"), iRow, 0);
                dgvShow.setValueAt(rs.getString("docdate"), iRow, 1);
                dgvShow.setValueAt(rs.getString("vname"), iRow, 2);
                dgvShow.setValueAt(rs.getString("quonumber"), iRow, 3);
                dgvShow.setValueAt(rs.getString("recnumber"), iRow, 4);
                dgvShow.setValueAt(rs.getString("StatusThai"), iRow, 5);
                dgvShow.setValueAt(rs.getString("EditDate"), iRow, 6);
                dgvShow.setValueAt(rs.getString("DeleteDate"), iRow, 7);
                dgvShow.setValueAt(rs.getString("ConfDate"), iRow, 8);
                dgvShow.setValueAt(rs.getString("mark"), iRow, 9);
                
                if(rs.getString("status").toString().equals("DL")){
                    setBackground(java.awt.Color.pink);
                }
                
                iRow++;
            }

            jh_center_cn.Cpuv_GetSql(iRow);
            //System.out.println(iRow); 

            int iRowAll = jh_center_cn.Cpui_RowsAll("Select count(docno) As Num From dochead " + sSql.trim() + "", "Num");
            if (iRowAll == 0) {
                iRowAll = 1;
            }
            lblTitlePage.setText("หน้าที่ : " + (jh_center_cn.piPage + 1) + ":จากทั้งหมด : " + iRowAll + " หน้า");

        } catch (Exception e) {
            e.printStackTrace();
        }    
    }

    public FrmDocument_Show() {
        initComponents();

        setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
        dgvShow.setAutoResizeMode(dgvShow.AUTO_RESIZE_ALL_COLUMNS);
                        
        dgvShow.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        dgvShow.getTableHeader().setForeground(new Color(255, 255, 255));
        dgvShow.getTableHeader().setBackground(new Color(0, 0, 102));

        TableColumn col1 = dgvShow.getColumnModel().getColumn(0);
        col1.setPreferredWidth(120);

        TableColumn col2 = dgvShow.getColumnModel().getColumn(1);
        col2.setPreferredWidth(110);

        TableColumn col3 = dgvShow.getColumnModel().getColumn(2);
        col3.setPreferredWidth(300);

        TableColumn col4 = dgvShow.getColumnModel().getColumn(3);
        col4.setPreferredWidth(130);

        TableColumn col5 = dgvShow.getColumnModel().getColumn(4);
        col5.setPreferredWidth(150);
        
        TableColumn col6 = dgvShow.getColumnModel().getColumn(5);
        col6.setPreferredWidth(130);

        TableColumn col7 = dgvShow.getColumnModel().getColumn(6);
        col7.setPreferredWidth(110);

        TableColumn col8 = dgvShow.getColumnModel().getColumn(7);
        col8.setPreferredWidth(110);

        TableColumn col9 = dgvShow.getColumnModel().getColumn(8);
        col9.setPreferredWidth(110);

        TableColumn col10 = dgvShow.getColumnModel().getColumn(9);
        col10.setPreferredWidth(220);

        Wprv_ShowData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mnsMenu = new javax.swing.JPopupMenu();
        mnsAdd = new javax.swing.JMenuItem();
        mnsEdit = new javax.swing.JMenuItem();
        mnsDelete = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mnsConf = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mnsPrint = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbSelect = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cmdSearch = new javax.swing.JButton();
        cmdRefresh = new javax.swing.JButton();
        chkAll = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<String>();
        jLabel4 = new javax.swing.JLabel();
        dateBegin = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        dateEnd = new com.toedter.calendar.JDateChooser();
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

        mnsEdit.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        mnsEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Edit20x20.png"))); // NOI18N
        mnsEdit.setText("แก้ไขรายการ");
        mnsEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnsEditActionPerformed(evt);
            }
        });
        mnsMenu.add(mnsEdit);

        mnsDelete.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        mnsDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/close Jframe.png"))); // NOI18N
        mnsDelete.setText("ยกเลิกรายการ");
        mnsDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnsDeleteActionPerformed(evt);
            }
        });
        mnsMenu.add(mnsDelete);
        mnsMenu.add(jSeparator2);

        mnsConf.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        mnsConf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save20x20.png"))); // NOI18N
        mnsConf.setText("ยืนยันรายการ");
        mnsConf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnsConfActionPerformed(evt);
            }
        });
        mnsMenu.add(mnsConf);
        mnsMenu.add(jSeparator3);

        mnsPrint.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        mnsPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/print20x20.png"))); // NOI18N
        mnsPrint.setText("พิมพ์รายการ");
        mnsPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnsPrintActionPerformed(evt);
            }
        });
        mnsMenu.add(mnsPrint);

        setTitle("ใบรับสินค้า");
        setMaximumSize(new java.awt.Dimension(1390, 735));
        setMinimumSize(new java.awt.Dimension(1390, 735));
        setPreferredSize(new java.awt.Dimension(1390, 735));
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
        jPanel1.setMaximumSize(new java.awt.Dimension(1300, 110));
        jPanel1.setMinimumSize(new java.awt.Dimension(1300, 110));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("เลือก");

        cmbSelect.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "บริษัท", "เลขที่ใบเสนอราคา", "เลขที่ใบแจ้งหนี้" }));

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("สถานะเอกสาร");

        cmbStatus.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "รายการที่รอส่งของ", "รายการที่ส่งของแล้ว", "รายการที่ยกเลิก", "รายการทั้งหมด" }));
        cmbStatus.setPreferredSize(new java.awt.Dimension(56, 29));
        cmbStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbStatusItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("ตั้งแต่วันที่");

        dateBegin.setDateFormatString("yyyy-MM-dd");
        dateBegin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dateBegin.setPreferredSize(new java.awt.Dimension(120, 29));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("ถึง");

        dateEnd.setDateFormatString("yyyy-MM-dd");
        dateEnd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dateEnd.setPreferredSize(new java.awt.Dimension(120, 29));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmbSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateBegin, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(76, 76, 76)
                .addComponent(cmdSearch)
                .addGap(18, 18, 18)
                .addComponent(cmdRefresh)
                .addGap(18, 18, 18)
                .addComponent(chkAll, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dateBegin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addComponent(dateEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdSearch)
                    .addComponent(cmdRefresh)
                    .addComponent(chkAll))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        dgvShow.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dgvShow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "เลขที่เอกสาร", "วันที่เอกสาร", "บริษัท", "ใบเสนอราคา", "ใบแจ้งหนี้/ใบเสร็จ", "สถานะ", "วันที่แก้ไข", "วันที่ยกเลิก", "วันที่ยืนยัน", "หมายเหตุ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dgvShow.setPreferredSize(new java.awt.Dimension(1305, 398));
        dgvShow.setRowHeight(20);
        dgvShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dgvShowMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dgvShow);

        jToolBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
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
        cmdAdd.setText("เพิ่มเอกสาร");
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1337, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmdExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 63, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if (KeyEvent.VK_ENTER == evt.getKeyCode()) {
            /*if (txtSearch.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "กรุณากรอกเงื่อนไขในการค้นหา", "ตรวจสอบ",
                        JOptionPane.WARNING_MESSAGE, null);
                txtSearch.requestFocusInWindow();
                return;
            }*/
            Wprv_ShowData();
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void cmdSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSearchActionPerformed
        /*if (txtSearch.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "กรุณากรอกเงื่อนไขในการค้นหา", "ตรวจสอบ",
                    JOptionPane.WARNING_MESSAGE, null);
            txtSearch.requestFocusInWindow();
            return;
        }*/
        Wprv_ShowData();
    }//GEN-LAST:event_cmdSearchActionPerformed

    private void cmdRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRefreshActionPerformed
        chkAll.setSelected(false);
        txtSearch.setText("");
        dateBegin.setCalendar(null);
        dateEnd.setCalendar(null);
        Wprv_ShowData();
    }//GEN-LAST:event_cmdRefreshActionPerformed

    private void chkAllStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkAllStateChanged
        dateBegin.setCalendar(null);
        dateEnd.setCalendar(null);
        Wprv_ShowData();
    }//GEN-LAST:event_chkAllStateChanged

    private void dgvShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgvShowMouseClicked
        sCode = dgvShow.getValueAt(dgvShow.getSelectedRow(), 0).toString();
        mnsEdit.setText("แก้ไขรายการ รหัส: " + sCode);
        mnsDelete.setText("ยกเลิกรายการ รหัส: " + sCode);
        mnsConf.setText("ยืนยันรายการ รหัส: " + sCode);
        mnsPrint.setText("พิมพ์รายการ รหัส: " + sCode);

        //ป้องกันในสถานะที่ขึ้นว่า ยกเลิกแล้ว
        if (dgvShow.getValueAt(dgvShow.getSelectedRow(), 5).toString().equals("ยกเลิกแล้ว")
                || dgvShow.getValueAt(dgvShow.getSelectedRow(), 5).toString().equals("ส่งของแล้ว")) {
            mnsEdit.setEnabled(false);
            mnsDelete.setEnabled(false);
            mnsConf.setEnabled(false);
        } else {
            mnsEdit.setEnabled(true);
            mnsDelete.setEnabled(true);
            mnsConf.setEnabled(true);
        }
        //----------------------------------------------------------------
        dgvShow.setComponentPopupMenu(mnsMenu);
        if (evt.getClickCount() == 2) {
            if(sCode.trim().equals("")) {
            FrmDocument_Save Frm = new FrmDocument_Save(null, closable);
            Frm.psAction = "ADD";
            Frm.setModal(true);
            jh_center_cn.Cprv_ScreenFormJDialogCenter(Frm);
            if (Frm.pbAction == true) {
            Wprv_ShowData();
            }
            }else{
            FrmDocument_Save Frm = new FrmDocument_Save(null, closable);
            Frm.setTitle("ข้อมูลรายการ");
            Frm.psAction = "VIEW";
            Frm.psDocNo = sCode;
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
        FrmDocument_Save Frm = new FrmDocument_Save(null, closable);
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

        FrmDocument_Save Frm = new FrmDocument_Save(null, closable);
        Frm.psAction = "EDIT";
        Frm.psDocNo = sCode;
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

        FrmDocument_Save Frm = new FrmDocument_Save(null, closable);
        Frm.psAction = "DELETE";
        Frm.psDocNo = sCode;
        Frm.setModal(true);
        jh_center_cn.Cprv_ScreenFormJDialogCenter(Frm);
        if (Frm.pbAction == true) {
            Wprv_ShowData();
        }
    }//GEN-LAST:event_mnsDeleteActionPerformed

    private void mnsConfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnsConfActionPerformed
        if (sCode.trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "กรุณาเลือกสิ่งที่ต้องการทำ ก่อน", "ตรวจสอบ",
                    JOptionPane.WARNING_MESSAGE, null);
            return;
        }

        FrmDocument_Save Frm = new FrmDocument_Save(null, closable);
        Frm.psAction = "CONF";
        Frm.psDocNo = sCode;
        Frm.setModal(true);
        jh_center_cn.Cprv_ScreenFormJDialogCenter(Frm);
        if (Frm.pbAction == true) {
            Wprv_ShowData();
        }
    }//GEN-LAST:event_mnsConfActionPerformed

    private void cmbStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbStatusItemStateChanged
        Wprv_ShowData();
    }//GEN-LAST:event_cmbStatusItemStateChanged

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        jh_page.bPageDoc=false;
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        jh_page.bPageDoc=true;
    }//GEN-LAST:event_formInternalFrameClosed

    private void mnsPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnsPrintActionPerformed
            try {
            HashMap param=new HashMap();
            param.put("sdocno", sCode);
            JasperPrint print=JasperFillManager.fillReport("Report_Invoice.jasper", param,jh_center.con);
            JasperViewer view=new JasperViewer(print,false);
            view.setTitle("เอกสารใบรับสินค้า");
            view.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_mnsPrintActionPerformed

    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
        FrmDocument_Save Frm = new FrmDocument_Save(null, closable);
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
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JButton cmdAdd;
    private javax.swing.JButton cmdBack;
    private javax.swing.JButton cmdEnd;
    private javax.swing.JButton cmdExit;
    private javax.swing.JButton cmdRefresh;
    private javax.swing.JButton cmdSearch;
    private javax.swing.JButton cmdStart;
    private javax.swing.JButton cmdWalk;
    private com.toedter.calendar.JDateChooser dateBegin;
    private com.toedter.calendar.JDateChooser dateEnd;
    private javax.swing.JTable dgvShow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblTitlePage;
    private javax.swing.JMenuItem mnsAdd;
    private javax.swing.JMenuItem mnsConf;
    private javax.swing.JMenuItem mnsDelete;
    private javax.swing.JMenuItem mnsEdit;
    private javax.swing.JPopupMenu mnsMenu;
    private javax.swing.JMenuItem mnsPrint;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
