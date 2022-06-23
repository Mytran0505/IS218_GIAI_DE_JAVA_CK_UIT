/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ck2_1819;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;


public class CK2_1819 {

    private Connection connection;
    
     public  void Cau1() throws Exception{

        Class.forName("oracle.jdbc.OracleDriver").newInstance();
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","FINAL_EXAM","success");
        JFrame fr = new JFrame();
        JPanel pn = new JPanel();
        
        JTextField txtMABN = new JTextField();
        JTextField txtTENBN =new JTextField();
        JTextField txtNGSINH = new JTextField();
        JTextField txtDIACHI = new JTextField();
        JTextField txtDIENTHOAI = new JTextField();
        JComboBox<String> cbGIOITINH = new JComboBox();
        cbGIOITINH.addItem("NAM");
        cbGIOITINH.addItem("NU");
        JButton btnTHEM = new JButton("Thêm");

        btnTHEM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String sqString = "INSERT INTO BENHNHAN VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(sqString);
                    statement.setString(1, txtMABN.getText());
                    statement.setString(2, txtTENBN.getText());
                    statement.setString(3, txtNGSINH.getText());
                    statement.setString(4, txtDIACHI.getText());
                    statement.setString(5, txtDIENTHOAI.getText());
                    statement.setString(6, cbGIOITINH.getSelectedItem().toString());
                    statement.executeUpdate();
                    
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                
            }
        });
        
        pn.setLayout(new GridLayout(7, 2));
        pn.add(txtMABN);
        pn.add(txtTENBN);
        pn.add(txtNGSINH);
        pn.add(txtDIACHI);
        pn.add(txtDIENTHOAI);
        pn.add(cbGIOITINH);
        pn.add(btnTHEM);
        fr.add(pn);
        fr.setSize(600, 500);
        fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }
    
     
    public void Cau2() throws Exception{
        Class.forName("oracle.jdbc.OracleDriver").newInstance();
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","FINAL_EXAM","success");
        JFrame fr = new JFrame();
        JPanel pn = new JPanel();
        
        JTextField txtMABN = new JTextField();
        JTextField txtTENBN =new JTextField();
        txtTENBN.disable();
        JTextField txtNGKHAM = new JTextField();
        JTextField txtYEUCAU = new JTextField();
        JComboBox<String> cbTENBS = new JComboBox();
        JButton btnTHEM = new JButton("Đặt lịch khám");
        
     
        txtMABN.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                String sqpStr = "SELECT TENBN FROM BENHNHAN WHERE MABN = ?";
                try {
                        PreparedStatement psta = connection.prepareStatement(sqpStr);
                        psta.setString(1, txtMABN.getText());
                        ResultSet getTENSP =psta.executeQuery();
                        while(getTENSP.next())
                            txtTENBN.setText(getTENSP.getString("TENBN"));
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }                       
            }
        });
        
        String cbSql = "SELECT TENBS FROM BACSI";
        Statement staTENBS = connection.createStatement();
        ResultSet resultTENBS = staTENBS.executeQuery(cbSql);
        while(resultTENBS.next())
            cbTENBS.addItem(resultTENBS.getString("TENBS"));
        
        btnTHEM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement MAKB = connection.prepareStatement("SELECT MAX(MAKB) FROM KHAMBENH");
                    ResultSet resMaxMAKB = MAKB.executeQuery();    
                    
                    int intMAKB;
                    resMaxMAKB.next();
                    if(resMaxMAKB.getString(1)==null)
                        intMAKB= 1;
                    else
                        intMAKB = Integer.parseInt(resMaxMAKB.getString(1)) + 1;
                    
                    
                    String sqString = "INSERT INTO KHAMBENH VALUES (?, ?, ?, ?, ?, NULL, NULL)";
                    PreparedStatement statement = connection.prepareStatement(sqString);
                    statement.setString(1, String.valueOf(intMAKB));
                    statement.setString(2, txtMABN.getText());
                    
                    PreparedStatement MABS = connection.prepareStatement("SELECT MABS FROM BACSI WHERE TENBS = ? ");
                    MABS.setString(1, cbTENBS.getSelectedItem().toString());
                    ResultSet resMABS = MABS.executeQuery();  
                    while(resMABS.next())
                        statement.setString(3, resMABS.getString(1));
                    statement.setString(4, txtNGKHAM.getText());
                    statement.setString(5, txtYEUCAU.getText());
                    statement.executeUpdate();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        
        pn.setLayout(new GridLayout(6, 2));
        pn.add(txtMABN);
        pn.add(txtTENBN);
        pn.add(txtNGKHAM);
        pn.add(txtYEUCAU);
        pn.add(cbTENBS);
        pn.add(btnTHEM);
        fr.add(pn);
        fr.setSize(600, 500);
        fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }
    
     public  void Cau3() throws Exception{
        Class.forName("oracle.jdbc.OracleDriver").newInstance();
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","FINAL_EXAM","success");
        JFrame fr = new JFrame();
        JPanel pn = new JPanel();
        
        JComboBox<String> cbTENBS = new JComboBox();
        String cbSql = "SELECT TENBS FROM BACSI";
        Statement staTENBS = connection.createStatement();
        ResultSet resultTENBS = staTENBS.executeQuery(cbSql);
        while(resultTENBS.next())
            cbTENBS.addItem(resultTENBS.getString("TENBS"));
        
        JTextField txtNGKHAM = new JTextField();
        JComboBox<String> cbTENBN = new JComboBox();
        
     
        txtNGKHAM.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                cbTENBN.removeAllItems();
                try {
                    String cbSql = "SELECT TENBN FROM BENHNHAN BN, KHAMBENH KB, BACSI BS WHERE BN.MABN=KB.MABN "
                            + "AND KB.MABS=BS.MABS AND NGAYKHAM = ? AND TENBS = ?";
                    PreparedStatement pstaTENBN = connection.prepareStatement(cbSql);
                    pstaTENBN.setString(1, txtNGKHAM.getText());
                    pstaTENBN.setString(2, cbTENBS.getSelectedItem().toString());
                    ResultSet resultTENBN = pstaTENBN.executeQuery();
                    while(resultTENBN.next())
                        cbTENBN.addItem(resultTENBN.getString("TENBN"));
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        
        JTextField txtYEUCAU = new JTextField();
        
        cbTENBN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String sqlYEUCAU = "SELECT YEUCAUKHAM FROM KHAMBENH KB, BENHNHAN BN WHERE KB.MABN=BN.MABN AND TENBN = ?";
                    PreparedStatement pstaYEUCAU = connection.prepareStatement(sqlYEUCAU);
                    pstaYEUCAU.setString(1, cbTENBN.getSelectedItem().toString());
                    ResultSet resultYEUCAU = pstaYEUCAU.executeQuery();
                    while(resultYEUCAU.next())
                        txtYEUCAU.setText(resultYEUCAU.getString(1));
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        
        JTextField txtKETLUAN = new JTextField();
        
        Object[] colsName1 = {"Tên dịch vụ"};
        DefaultTableModel model1 = new DefaultTableModel(null, colsName1);
        JTable tbDichVu = new JTable(model1);  
        
        String sqlTENDV = "SELECT TENDV FROM DICHVU";
        Statement staTENDV = connection.createStatement();
        ResultSet resultTENDV = staTENDV.executeQuery(sqlTENDV);
        while(resultTENDV.next()){
            String rowString[] = {resultTENDV.getString(1)};
            model1.addRow(rowString);            
        }
        
        Object[] colsName2 = {"Tên dịch vụ", "Số lượng"};
        DefaultTableModel model2 = new DefaultTableModel(null, colsName2);
        JTable tbDichVuChon = new JTable(model2);   
        
        tbDichVu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int row = tbDichVu.rowAtPoint(evt.getPoint());
                String rowString[] = {model1.getValueAt(row, 0).toString()};
                model2.addRow(rowString);
            }
        });
        
        JButton btnTHEM = new JButton("Thêm");
        
        btnTHEM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String strMAKB = null;
                    String sqlMAKB = "SELECT MAKB FROM KHAMBENH KB, BENHNHAN BN, BACSI BS WHERE KB.MABN=BN.MABN AND KB.MABS=BS.MABS "
                            + "AND TENBS =? AND TENBN =? ";
                    PreparedStatement pstaMAKB = connection.prepareStatement(sqlMAKB);
                    pstaMAKB.setString(1, cbTENBS.getSelectedItem().toString());
                    pstaMAKB.setString(2, cbTENBN.getSelectedItem().toString());
                    ResultSet resultMAKB = pstaMAKB.executeQuery();
                    while(resultMAKB.next())
                        strMAKB = resultMAKB.getString(1);
                    
                    String qslUpdateKhamBenh = "UPDATE KHAMBENH SET KETLUAN = ? WHERE MAKB = ?" ;
                    PreparedStatement pstaUKB = connection.prepareStatement(qslUpdateKhamBenh);
                    pstaUKB.setString(1, txtKETLUAN.getText());
                    pstaUKB.setString(2, strMAKB);
                    pstaUKB.executeUpdate();
                    
                    for(int i=0; i< model2.getRowCount(); i++){
                        int THANHTIEN =0;
                        String strMADV = null;
                        String sqlCTHD = "INSERT INTO THUPHI VALUES (?, ?, ?, ?)";
                        PreparedStatement pstaThuPhi = connection.prepareStatement(sqlCTHD);
                        pstaThuPhi.setString(1,strMAKB);
                        
                        PreparedStatement pstaMADV = connection.prepareStatement("SELECT MADV FROM DICHVU WHERE TENDV = ?");
                        pstaMADV.setString(1, model2.getValueAt(i, 0).toString());
                        ResultSet resultMADV = pstaMADV.executeQuery();
                        while(resultMADV.next())
                            strMADV = resultMADV.getString(1);
                        
                        pstaThuPhi.setString(2, strMADV);
                        pstaThuPhi.setString(3, model2.getValueAt(i, 1).toString());
                        
                        PreparedStatement pstaDonGia =connection.prepareStatement("SELECT DONGIA FROM DICHVU WHERE MADV = ?") ;
                        pstaDonGia.setString(1,strMADV);
                        ResultSet resDonGia = pstaDonGia.executeQuery();
                        while(resDonGia.next())
                            THANHTIEN += Integer.parseInt(resDonGia.getString(1))* Integer.parseInt(model2.getValueAt(i, 1).toString());
                        pstaThuPhi.setString(4, String.valueOf(THANHTIEN));
                        
                        pstaThuPhi.executeUpdate();
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        
        pn.setLayout(new GridLayout(8, 2));
        pn.add(cbTENBS);
        pn.add(txtNGKHAM);
        pn.add(cbTENBN);
        pn.add(txtYEUCAU);
        pn.add(txtKETLUAN);
        pn.add(new JScrollPane(tbDichVu));
        pn.add(new JScrollPane(tbDichVuChon));
        pn.add(btnTHEM);
        fr.add(pn);
        fr.setSize(600, 500);
        fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }
    public static void main(String[] args) throws Exception {
        CK2_1819 st = new CK2_1819();
        st.Cau2();
    }
    
}
