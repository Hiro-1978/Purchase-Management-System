package purchase.management.system;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class jh_center {

    public static Connection con;
    public static boolean bCheckConnectDB = false;
    public static String sUsername = "";
    public static String sStatusAdmin = "";
    public static String sVersion = "Purchase Management System V1.0.1";

    public static String sServer = "";
    public static String sDatabase = "";
    public static String sUser = "";
    public static String sPass = "";

    public static String sMonth = "";
    public static String sYear = "";

    //Connect DB
    public void Cpuv_ConnectionDB() {
        try {
            if (bCheckConnectDB == false) {
                String sConnectionUrl = "jdbc:mysql://" + sServer + "/" + sDatabase + "?useUnicode=true&characterEncoding=UTF-8";
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection(sConnectionUrl, sUser, sPass);
                bCheckConnectDB = true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ไม่ได้เชื่อมต่อฐานข้อมูล","ตรวจสอบ",
                        JOptionPane.WARNING_MESSAGE,null);
        }
    }

    //ShowData
    public ResultSet Cpuds_ShowData(String psSql) {
        try {
            if (bCheckConnectDB == false) {
                Cpuv_ConnectionDB();
            }

            ResultSet rs = con.createStatement().executeQuery(psSql);
            return rs;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Add, Edit, Delete
    public boolean Cpub_ActonDB(String psSql) {
        boolean bCheck = false;
        try {
            if (bCheckConnectDB = false) {
                Cpuv_ConnectionDB();
            }
            PreparedStatement pre = con.prepareStatement(psSql);
            if (pre.executeUpdate() != -1) {
                bCheck = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bCheck;
    }

    //Method Screen Form
    //Show JInternalFrame
    public void Cpuv_ScreenForm(JInternalFrame Frm, boolean pbCheck) {
        Dimension dimScreen = Toolkit.getDefaultToolkit().getScreenSize();
        int nWidth = Frm.getSize().width;
        int nHeight = Frm.getSize().height;
        int nScreenX = (dimScreen.width - nWidth) / 2;
        int nScreenY = ((dimScreen.height - nHeight) / 2) - 120;
        Frm.setLocation(nScreenX, nScreenY);
        Frm.setVisible(pbCheck);
    }

    //Show DialogBox
    public void Cpuv_ScreenFormJDialog(JDialog Frm) {
        Dimension dimScreen = Toolkit.getDefaultToolkit().getScreenSize();
        int nWidth = Frm.getSize().width;
        int nHeight = Frm.getSize().height;
        int nScreenX = (dimScreen.width - nWidth) / 2;
        int nScreenY = ((dimScreen.height - nHeight) / 2) - 120;
        Frm.setLocation(nScreenX, nScreenY);
        Frm.setVisible(true);
    }

    //Show DialogBox Center
    public void Cprv_ScreenFormJDialogCenter(JDialog Frm) {
        Dimension dimScreen = Toolkit.getDefaultToolkit().getScreenSize();
        int nWidth = Frm.getSize().width;
        int nHeight = Frm.getSize().height;
        int nScreenX = (dimScreen.width - nWidth) / 2;
        int nScreenY = (dimScreen.height - nHeight) / 2;
        Frm.setLocation(nScreenX, nScreenY);
        Frm.setVisible(true);
    }

    //Show DialogSub หน้าจอ Details
    public void Cprv_ScreenFormJDialogSub(JDialog Frm) {
        Dimension dimScreen = Toolkit.getDefaultToolkit().getScreenSize();
        int nWidth = Frm.getSize().width;
        int nHeight = Frm.getSize().height;
        int nScreenX = (dimScreen.width - nWidth) / 2;
        int nScreenY = ((dimScreen.height - nHeight) / 2) - 120;
        Frm.setLocation(nScreenX, nScreenY);
        Frm.setVisible(true);
    }

    //**************************************
    //Method Key Autorun
    public String Cpus_AutoRun(String psFname, String psTname, String psHname, String psLname) throws SQLException {
        int intId = 0;
        String strId = "";
        String sqlStr = "";
        sqlStr = "Select " + psFname + " From " + psTname + " Where " + psFname + " Like '%"+ psHname +"%' "
                + " Order By " + psFname + " desc;";
        //System.out.println(sqlStr);
        DecimalFormat df = new DecimalFormat(psLname);
        ResultSet rs = Cpuds_ShowData(sqlStr);
        if (rs.next()) {
            strId = rs.getString(psFname);
            strId = strId.replace(psHname, "");
            intId = Integer.parseInt(strId) + 1;
            return psHname + df.format(intId);
        } else {
            intId = Integer.parseInt(psLname) + 1;
            return psHname + df.format(intId);
        }
    }

    //ตรวจสอบข้อผิดพลาด
    public boolean Cpub_CheckValueData(String psTname, String psFname, String psValue) throws SQLException {
        String sqlStr = "";
        sqlStr = "Select * From " + psTname + " Where " + psFname + "='" + psValue + "'";
        ResultSet rs = Cpuds_ShowData(sqlStr);
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    //Paggin หน้า (คลิ๊กหน้าถัดไป)
    public int Cpui_RowsAll(String _sSQL, String _sFile) {
        int iRow = 0;
        ResultSet rs = Cpuds_ShowData(_sSQL);
        try {
            if (rs.next()) {
                iRow = Integer.valueOf(rs.getString(_sFile.trim()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int iRowOver = 0;
        int iRowMod = iRow % 20;
        if (iRowMod > 0) {
            iRowOver = 1;
        }
        return iRowOver + (int) (Math.floor(iRow / 20));
    }

    public int piPage = 0;
    public int piPageGet = 0;
    public int piEnd = 0;
    public int piStart = 0;
    public int piRowFloor = 0;

    public void Cpuv_GetRowPage() {
        piEnd = 20;
        piStart = (piPage * 20);
    }

    public void Cpuv_GetSql(int _iRow) {
        try {
            int iRow = _iRow;
            int iRowOver = 0;
            int iRowMod = iRow % 20;
            if (iRowMod > 0) {
                iRowOver = 1;
            }
            piRowFloor = (int) (Math.floor(iRow / 20));
            piPageGet = iRowOver + piRowFloor;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //******* End Class *******//
}
