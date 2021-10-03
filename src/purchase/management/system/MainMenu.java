package purchase.management.system;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainMenu extends javax.swing.JFrame {
    
    jh_center jh_center_cn=new jh_center();
    Date dNow=new Date();
    DateFormat datMonth=new SimpleDateFormat("MM");
    DateFormat datYear=new SimpleDateFormat("yy");
    
    public MainMenu() {
        initComponents();
        //กำหนดขนาดและให้เปิดกลางจอ
        this.setSize(1400, 800);
        this.setLocationRelativeTo(null);
        
        // แบบขยายตามความกว้างหน้า
        /*Dimension dimSc=Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dimSc.width, dimSc.height);*/

        jh_center.sYear=datYear.format(dNow);
        jh_center.sMonth=datMonth.format(dNow);
        
        this.setTitle(jh_center.sVersion+" (User is : "+jh_center.sUsername.trim()+") ");
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desMain = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnFix = new javax.swing.JMenu();
        mnFix_User = new javax.swing.JMenuItem();
        mnUse = new javax.swing.JMenu();
        mnUnit = new javax.swing.JMenuItem();
        mnStock = new javax.swing.JMenuItem();
        mnVender = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnProduct = new javax.swing.JMenuItem();
        mnPoInv = new javax.swing.JMenu();
        mnPo = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mnReport = new javax.swing.JMenu();
        mnStockReport = new javax.swing.JMenuItem();
        mnBackup = new javax.swing.JMenuItem();
        mnExit = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1300, 800));

        desMain.setBackground(new java.awt.Color(153, 255, 255));
        desMain.setEnabled(false);
        desMain.setMaximumSize(new java.awt.Dimension(1400, 771));
        desMain.setMinimumSize(new java.awt.Dimension(1400, 771));
        desMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Wall-PR.png"))); // NOI18N
        desMain.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jMenuBar1.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N

        mnFix.setText("ตั้งค่าระบบ");
        mnFix.setFont(new java.awt.Font("Leelawadee UI", 0, 20)); // NOI18N

        mnFix_User.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        mnFix_User.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/admin20x20.png"))); // NOI18N
        mnFix_User.setText("สิทธิ์ผู้ใช้งาน");
        mnFix_User.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnFix_UserActionPerformed(evt);
            }
        });
        mnFix.add(mnFix_User);

        jMenuBar1.add(mnFix);

        mnUse.setText("กำหนดค่าเริ่มต้น");
        mnUse.setFont(new java.awt.Font("Leelawadee UI", 0, 20)); // NOI18N

        mnUnit.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        mnUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/unit-20x20.png"))); // NOI18N
        mnUnit.setText("กำหนดหน่วยไอเทม");
        mnUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnUnitActionPerformed(evt);
            }
        });
        mnUse.add(mnUnit);

        mnStock.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        mnStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/doc2 20x20.png"))); // NOI18N
        mnStock.setText("กำหนดหมวดหมู่ไอเทม");
        mnStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnStockActionPerformed(evt);
            }
        });
        mnUse.add(mnStock);

        mnVender.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        mnVender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/store-20x20.png"))); // NOI18N
        mnVender.setText("กำหนดข้อมูลบริษัท");
        mnVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnVenderActionPerformed(evt);
            }
        });
        mnUse.add(mnVender);
        mnUse.add(jSeparator1);

        mnProduct.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        mnProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Edit20x20.png"))); // NOI18N
        mnProduct.setText("ข้อมูลไอเทม");
        mnProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnProductActionPerformed(evt);
            }
        });
        mnUse.add(mnProduct);

        jMenuBar1.add(mnUse);

        mnPoInv.setText("เอกสาร");
        mnPoInv.setFont(new java.awt.Font("Leelawadee UI", 0, 20)); // NOI18N

        mnPo.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        mnPo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save20x20.png"))); // NOI18N
        mnPo.setText("บันทึกเอกสาร");
        mnPo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnPoActionPerformed(evt);
            }
        });
        mnPoInv.add(mnPo);
        mnPoInv.add(jSeparator2);

        jMenuBar1.add(mnPoInv);

        mnReport.setText("รายงาน");
        mnReport.setFont(new java.awt.Font("Leelawadee UI", 0, 20)); // NOI18N

        mnStockReport.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        mnStockReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/print20x20.png"))); // NOI18N
        mnStockReport.setText("ประวัติสั่งซื้อไอเทม");
        mnStockReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnStockReportActionPerformed(evt);
            }
        });
        mnReport.add(mnStockReport);

        mnBackup.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        mnBackup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/backup_and_restore20x20.png"))); // NOI18N
        mnBackup.setText("สำรองข้อมูล");
        mnBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnBackupActionPerformed(evt);
            }
        });
        mnReport.add(mnBackup);

        jMenuBar1.add(mnReport);

        mnExit.setText("ออกจากโปรแกรม");
        mnExit.setFont(new java.awt.Font("Leelawadee UI", 0, 20)); // NOI18N
        mnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnExitMouseClicked(evt);
            }
        });
        mnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnExitActionPerformed(evt);
            }
        });
        jMenuBar1.add(mnExit);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnFix_UserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnFix_UserActionPerformed
        desMain.removeAll();
        // ปิดหน้าอื่น
        FrmFix_User Frm=new FrmFix_User();
        if(jh_page.jInPageFix!=null){
            jh_page.jInPageFix.dispose();
        }
        jh_page.jInPageFix=Frm;
        //jh_center_cn.Cpuv_ScreenForm(Frm, jh_page.bPageFix);
        Frm.setVisible(true);
        desMain.add(Frm);
        try {
            Frm.setSelected(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_mnFix_UserActionPerformed

    private void mnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnExitActionPerformed

    private void mnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_mnExitMouseClicked

    private void mnUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnUnitActionPerformed
        desMain.removeAll();
        // ปิดหน้าอื่น
        FrmUnit_Show Frm=new FrmUnit_Show();
        if(jh_page.jInPageUnit!=null){
            jh_page.jInPageUnit.dispose();
        }
        jh_page.jInPageUnit=Frm;
        //jh_center_cn.Cpuv_ScreenForm(Frm, jh_page.bPageUnit);
        Frm.setVisible(true);
        desMain.add(Frm);
        try {
            Frm.setSelected(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_mnUnitActionPerformed

    private void mnStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnStockActionPerformed
        desMain.removeAll();
        // ปิดหน้าอื่น
        FrmBase_Show Frm=new FrmBase_Show();
        if(jh_page.jInPageBase!=null){
            jh_page.jInPageBase.dispose();
        }
        jh_page.jInPageBase=Frm;
        //jh_center_cn.Cpuv_ScreenForm(Frm, jh_page.bPageStock);
        Frm.setVisible(true);
        desMain.add(Frm);
        try {
            Frm.setSelected(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_mnStockActionPerformed

    private void mnProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnProductActionPerformed
        desMain.removeAll();
        // ปิดหน้าอื่น
        FrmItem_Show Frm=new FrmItem_Show();
        if(jh_page.jInPageItem!=null){
            jh_page.jInPageItem.dispose();
        }
        jh_page.jInPageItem=Frm;
        //jh_center_cn.Cpuv_ScreenForm(Frm, jh_page.bPageProd);
        Frm.setVisible(true);
        desMain.add(Frm);
        try {
            Frm.setSelected(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_mnProductActionPerformed

    private void mnPoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnPoActionPerformed
        desMain.removeAll();
        // ปิดหน้าอื่น
        FrmDocument_Show Frm=new FrmDocument_Show();
        if(jh_page.jInPageDoc!=null){
            jh_page.jInPageDoc.dispose();
        }
        jh_page.jInPageDoc=Frm;
        //jh_center_cn.Cpuv_ScreenForm(Frm, jh_page.bPageInvoice);
        Frm.setVisible(true);
        desMain.add(Frm);
        try {
            Frm.setSelected(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_mnPoActionPerformed

    private void mnStockReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnStockReportActionPerformed
        desMain.removeAll();
        // ปิดหน้าอื่น
        FrmReportMgnt Frm=new FrmReportMgnt();
        if(jh_page.jInPageReportMgnt!=null){
            jh_page.jInPageReportMgnt.dispose();
        }
        jh_page.jInPageReportMgnt=Frm;
        //jh_center_cn.Cpuv_ScreenForm(Frm, jh_page.bPageReportStock);
        Frm.setVisible(true);
        desMain.add(Frm);
        try {
            Frm.setSelected(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_mnStockReportActionPerformed

    private void mnVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnVenderActionPerformed
        desMain.removeAll();
        // ปิดหน้าอื่น
        FrmVender_Show Frm=new FrmVender_Show();
        if(jh_page.jInPageVender!=null){
            jh_page.jInPageVender.dispose();
        }
        jh_page.jInPageVender=Frm;
        //jh_center_cn.Cpuv_ScreenForm(Frm, jh_page.bPageProd);
        Frm.setVisible(true);
        desMain.add(Frm);
        try {
            Frm.setSelected(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_mnVenderActionPerformed

    private void mnBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnBackupActionPerformed
        desMain.removeAll();
        // ปิดหน้าอื่น
        FrmBackup Frm=new FrmBackup();
        if(jh_page.jInPageFrmBackup!=null){
            jh_page.jInPageFrmBackup.dispose();
        }
        jh_page.jInPageFrmBackup=Frm;
        //jh_center_cn.Cpuv_ScreenForm(Frm, jh_page.bPageProd);
        Frm.setVisible(true);
        desMain.add(Frm);
        try {
            Frm.setSelected(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_mnBackupActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desMain;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem mnBackup;
    private javax.swing.JMenu mnExit;
    private javax.swing.JMenu mnFix;
    private javax.swing.JMenuItem mnFix_User;
    private javax.swing.JMenuItem mnPo;
    private javax.swing.JMenu mnPoInv;
    private javax.swing.JMenuItem mnProduct;
    private javax.swing.JMenu mnReport;
    private javax.swing.JMenuItem mnStock;
    private javax.swing.JMenuItem mnStockReport;
    private javax.swing.JMenuItem mnUnit;
    private javax.swing.JMenu mnUse;
    private javax.swing.JMenuItem mnVender;
    // End of variables declaration//GEN-END:variables
}
