
package purchase.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class FrmBase_List extends javax.swing.JDialog {

    jh_center jh_center_cn = new jh_center();
    DefaultTableModel tableModel=new DefaultTableModel();
    Object[] data=new Object[0];
    public String psCode="";
    public String psName="";
    
    //Method
    //Claer Grid
    private void Wprv_ClearDataGrid(){
        int iRow=dgvShow.getRowCount()-1;
        while(iRow>-1){
            tableModel.removeRow(iRow);
            iRow--;
        }
    }
    
    //Show Data
    private void Wprv_ShowDB(){
        Wprv_ClearDataGrid();
        try {
            String sSql="";
                        
            if(chkAll.isSelected()==false){
                sSql="Where baseid Like '%"+txtSearch.getText().trim()+"%' "
                + "OR bname Like '%"+txtSearch.getText().trim()+"%' ";
            }
            
            jh_center_cn.Cpuv_GetRowPage();
            
            String sSqlShow="Select * From basetype "+sSql+""
                    + " Limit " + jh_center_cn.piStart + ", " + jh_center_cn.piEnd + " ";
            
            ResultSet rs=jh_center_cn.Cpuds_ShowData(sSqlShow);
            int iRow=0;
            while(rs.next()){
                String sCode=rs.getString("baseid");
                String sName=rs.getString("bname");
                
                tableModel.addRow(data);                
                dgvShow.setValueAt(sCode, iRow, 0);
                dgvShow.setValueAt(sName, iRow, 1);
                iRow++;
            }
                                                
            jh_center_cn.Cpuv_GetSql(iRow);
            //System.out.println(iRow); 
            int iRowAll = jh_center_cn.Cpui_RowsAll("Select count(baseid) As Num From basetype ", "Num");
            if (iRowAll == 0) {
                iRowAll = 1;
            //System.out.println(iRowAll);
            }
            lblTitlePage.setText("????????????????????? : " + (jh_center_cn.piPage + 1) + ":?????????????????????????????? : " + iRowAll + " ????????????");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public FrmBase_List(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        dgvShow = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cmdSearch = new javax.swing.JButton();
        chkAll = new javax.swing.JCheckBox();
        cmdRefresh = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        cmdStart = new javax.swing.JButton();
        cmdBack = new javax.swing.JButton();
        lblTitlePage = new javax.swing.JLabel();
        cmdWalk = new javax.swing.JButton();
        cmdEnd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("?????????????????????????????????????????????");
        setPreferredSize(new java.awt.Dimension(500, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        dgvShow.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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
                "????????????????????????????????????", "????????????????????????????????????"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dgvShow.setPreferredSize(new java.awt.Dimension(479, 399));
        dgvShow.setRowHeight(20);
        dgvShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dgvShowMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dgvShow);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("??????????????? :");

        txtSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSearch.setPreferredSize(new java.awt.Dimension(69, 29));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });

        cmdSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search20x20.png"))); // NOI18N
        cmdSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSearchActionPerformed(evt);
            }
        });

        chkAll.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chkAll.setText("?????????????????????");
        chkAll.setPreferredSize(new java.awt.Dimension(75, 29));
        chkAll.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkAllStateChanged(evt);
            }
        });

        cmdRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh-20x20.png"))); // NOI18N
        cmdRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkAll, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cmdSearch, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

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
        lblTitlePage.setText("????????????????????? :   ??????????????????????????????");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dgvShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgvShowMouseClicked
        if(dgvShow.getRowCount()<=0){
            return;
        }

        if(evt.getClickCount()==2){
            psCode=dgvShow.getValueAt(dgvShow.getSelectedRow(), 0).toString();
            psName=dgvShow.getValueAt(dgvShow.getSelectedRow(), 1).toString();
            this.dispose();
        }
    }//GEN-LAST:event_dgvShowMouseClicked

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if (KeyEvent.VK_ENTER == evt.getKeyCode()) {
            if (txtSearch.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "?????????????????????????????????????????????????????????????????????????????????", "?????????????????????",
                    JOptionPane.WARNING_MESSAGE, null);
                txtSearch.requestFocusInWindow();
                return;
            }
            Wprv_ShowDB();
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void cmdSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSearchActionPerformed
        Wprv_ShowDB();
    }//GEN-LAST:event_cmdSearchActionPerformed

    private void chkAllStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkAllStateChanged
        Wprv_ShowDB();
    }//GEN-LAST:event_chkAllStateChanged

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tableModel=(DefaultTableModel) dgvShow.getModel();
        dgvShow.setAutoResizeMode(dgvShow.AUTO_RESIZE_ALL_COLUMNS);
        dgvShow.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        dgvShow.getTableHeader().setForeground(new Color(255, 255, 255));
        dgvShow.getTableHeader().setBackground(new Color(0, 0, 102));
        
        TableColumn col1=dgvShow.getColumnModel().getColumn(0);
        col1.setPreferredWidth(80);
        
        TableColumn col2=dgvShow.getColumnModel().getColumn(1);
        col2.setPreferredWidth(150);
        
        Wprv_ShowDB();
    }//GEN-LAST:event_formWindowOpened

    private void cmdStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdStartActionPerformed
        jh_center_cn.piPage = 0;
        Wprv_ShowDB();
    }//GEN-LAST:event_cmdStartActionPerformed

    private void cmdBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBackActionPerformed
        jh_center_cn.piPage = jh_center_cn.piPage - 1;
        if (jh_center_cn.piPage < 0) {
            jh_center_cn.piPage = 0;
        }
        Wprv_ShowDB();
    }//GEN-LAST:event_cmdBackActionPerformed

    private void cmdWalkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdWalkActionPerformed
        if (jh_center_cn.piRowFloor > 0) {
            jh_center_cn.piPage = jh_center_cn.piPage+1;
        }
        Wprv_ShowDB();
    }//GEN-LAST:event_cmdWalkActionPerformed

    private void cmdEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEndActionPerformed
        if (jh_center_cn.piRowFloor > 0) {
            jh_center_cn.piPage = jh_center_cn.piPageGet+1;
        }
        Wprv_ShowDB();
    }//GEN-LAST:event_cmdEndActionPerformed

    private void cmdRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRefreshActionPerformed
        chkAll.setSelected(false);
        txtSearch.setText("");
        Wprv_ShowDB();
    }//GEN-LAST:event_cmdRefreshActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmBase_List dialog = new FrmBase_List(new javax.swing.JFrame(), true);
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
    private javax.swing.JCheckBox chkAll;
    private javax.swing.JButton cmdBack;
    private javax.swing.JButton cmdEnd;
    private javax.swing.JButton cmdRefresh;
    private javax.swing.JButton cmdSearch;
    private javax.swing.JButton cmdStart;
    private javax.swing.JButton cmdWalk;
    private javax.swing.JTable dgvShow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JLabel lblTitlePage;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
