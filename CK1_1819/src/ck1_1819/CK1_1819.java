/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ck1_1819;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

/**
 *
 * @author LENOVO
 */
public class CK1_1819 {

    /**
     * @param args the command line arguments
     */
    
    private Connection connection;
    
     public  void Cau1() throws Exception{

        Class.forName("oracle.jdbc.OracleDriver").newInstance();
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","FINAL_EXAM","success");
        JFrame fr = new JFrame();
        JPanel pn = new JPanel();
        
        JTextField txtMADOI = new JTextField();
        JTextField txtTENDOI =new JTextField();
        JTextField txtQUOCGIA = new JTextField();
        JButton btnTHEM = new JButton("Thêm");

        btnTHEM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String sqString = "INSERT INTO DOIBONG VALUES (?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(sqString);
                    statement.setString(1, txtMADOI.getText());
                    statement.setString(2, txtTENDOI.getText());
                    statement.setString(3, txtQUOCGIA.getText());
                    statement.executeUpdate();
                    
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                
            }
        });
        
        pn.setLayout(new GridLayout(4, 2));
        pn.add(txtMADOI);
        pn.add(txtTENDOI);
        pn.add(txtQUOCGIA);
        pn.add(btnTHEM);
        fr.add(pn);
        fr.setSize(600, 500);
        fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }
    
     
    
     public  void Cau2() throws Exception{

        Class.forName("oracle.jdbc.OracleDriver").newInstance();
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","FINAL_EXAM","success");
        JFrame fr = new JFrame();
        JPanel pn = new JPanel();
        
        JTextField txtMAGIAI = new JTextField();
        JTextField txtTENGIAI =new JTextField();
        JTextField txtNGAYBATDAU = new JTextField();
        JTextField txtNGAYKETTHUC = new JTextField();
        JButton btnTHEM = new JButton("Thêm");

        btnTHEM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String sqString = "INSERT INTO GIAIDAU VALUES (?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(sqString);
                    statement.setString(1, txtMAGIAI.getText());
                    statement.setString(2, txtTENGIAI.getText());
                    statement.setString(3, txtNGAYBATDAU.getText());
                    statement.setString(4, txtNGAYKETTHUC.getText());
                    statement.executeUpdate();
                    
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                
            }
        });
        
        pn.setLayout(new GridLayout(5, 2));
        pn.add(txtMAGIAI);
        pn.add(txtTENGIAI);
        pn.add(txtNGAYBATDAU);
        pn.add(txtNGAYKETTHUC);
        pn.add(btnTHEM);
        fr.add(pn);
        fr.setSize(600, 500);
        fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }
    
     
    public void Cau3() throws Exception{
        Class.forName("oracle.jdbc.OracleDriver").newInstance();
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","FINAL_EXAM","success");
        JFrame fr = new JFrame();
        JPanel pn = new JPanel();
        
        JComboBox<String> cbTENGIAI = new JComboBox();
        
        String cbSql = "SELECT TENGIAI FROM GIAIDAU";
        Statement staGIAI = connection.createStatement();
        ResultSet resultTENGIAI = staGIAI.executeQuery(cbSql);
        while(resultTENGIAI.next())
            cbTENGIAI.addItem(resultTENGIAI.getString("TENGIAI"));
        
        Object[] colsName1 = {"Mã đội", "Tên đội"};
        DefaultTableModel model1 = new DefaultTableModel(null, colsName1);
        JTable tbDoiBong = new JTable(model1);  
        
        String sqlDoiBong = "SELECT MAD, TENDOI FROM DOIBONG";
        Statement staDoiBong = connection.createStatement();
        ResultSet resultDoiBong = staDoiBong.executeQuery(sqlDoiBong);
        while(resultDoiBong.next()){
            String rowString[] = {resultDoiBong.getString(1), resultDoiBong.getString(2)};
            model1.addRow(rowString);            
        }
        
        Object[] colsName2 = {"Mã đội", "Tên đội"};
        DefaultTableModel model2 = new DefaultTableModel(null, colsName2);
        JTable tbDoiBongThamGia = new JTable(model2);   
        
        tbDoiBong.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int row = tbDoiBong.rowAtPoint(evt.getPoint());
                String rowString[] = {model1.getValueAt(row, 0).toString(), model1.getValueAt(row,1).toString()};
                model2.addRow(rowString);
            }
        });
        
        JButton btnTHEM = new JButton("Thêm");
        btnTHEM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String strMAGIAI = null;
                    String sqlMAGIAI = "SELECT MAG FROM GIAIDAU WHERE TENGIAI = ?";
                    PreparedStatement pstaMAGIAI = connection.prepareStatement(sqlMAGIAI);
                    pstaMAGIAI.setString(1, cbTENGIAI.getSelectedItem().toString());
                    ResultSet resultMAGIAI = pstaMAGIAI.executeQuery();
                    while(resultMAGIAI.next())
                        strMAGIAI = resultMAGIAI.getString(1);
                    
                    for(int i=0; i< model2.getRowCount(); i++){
                    String sqlCTHD = "INSERT INTO CT_GD VALUES (?, ?)";
                    PreparedStatement pstaThuPhi = connection.prepareStatement(sqlCTHD);
                    pstaThuPhi.setString(1, strMAGIAI);
                    pstaThuPhi.setString(2, model2.getValueAt(i, 0).toString());
                    pstaThuPhi.executeUpdate();
                    }   
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        JButton btnHUY = new JButton("Hủy");
        btnHUY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model2.setRowCount (0);
            }
        });

        pn.setLayout(new GridLayout(5, 2));
        pn.add(cbTENGIAI);
        pn.add(new JScrollPane(tbDoiBong));
        pn.add(new JScrollPane(tbDoiBongThamGia));
        pn.add(btnTHEM);
        pn.add(btnHUY);
        fr.add(pn);
        fr.setSize(600, 500);
        fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }
    
    public void Cau4() throws Exception{
        Class.forName("oracle.jdbc.OracleDriver").newInstance();
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","FINAL_EXAM","success");
        JFrame fr = new JFrame();
        JPanel pn = new JPanel();
        
        JComboBox<String> cbTENGIAI = new JComboBox();
        
        String cbSqlTENGIAI = "SELECT TENGIAI FROM GIAIDAU";
        Statement staGIAI = connection.createStatement();
        ResultSet resultTENGIAI = staGIAI.executeQuery(cbSqlTENGIAI);
        while(resultTENGIAI.next())
            cbTENGIAI.addItem(resultTENGIAI.getString("TENGIAI"));
        
        JTextField txtNGAYDA = new JTextField();
        JTextField txtDIADIEM = new JTextField();
        
        JComboBox<String> cbCHUNHA = new JComboBox();
        JComboBox<String> cbDOIKHACH = new JComboBox();
        cbTENGIAI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbCHUNHA.removeAllItems();
                cbDOIKHACH.removeAllItems();
                try {
                    String cbSqlDoiBong = "SELECT TENDOI FROM DOIBONG DB, CT_GD CT, GIAIDAU GD "
                            + "WHERE DB.MAD = CT.MAD AND CT.MAG = GD.MAG AND TENGIAI =? ";
                    PreparedStatement staDoiBong = connection.prepareStatement(cbSqlDoiBong);
                    staDoiBong.setString(1, cbTENGIAI.getSelectedItem().toString());
                    ResultSet resultDoiBong = staDoiBong.executeQuery();
                    while(resultDoiBong.next()){
                        cbCHUNHA.addItem(resultDoiBong.getString("TENDOI"));
                        cbDOIKHACH.addItem(resultDoiBong.getString("TENDOI"));
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });        
        
        
        JTextField txtTISO = new JTextField();
        JButton btnTHEM = new JButton("Thêm");
        
        btnTHEM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement mat = connection.prepareStatement("SELECT MAX(MAT) FROM TRANDAU");
                    ResultSet resMaxMAT = mat.executeQuery();    
                    
                    int intMAT;
                    resMaxMAT.next();
                    if(resMaxMAT.getString(1)==null)
                        intMAT= 1;
                    else
                        intMAT = Integer.parseInt(resMaxMAT.getString(1)) + 1;
                    
                    String sqlTRANDAU = "INSERT INTO TRANDAU VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement pstaTRANDAU = connection.prepareStatement(sqlTRANDAU);
                    pstaTRANDAU.setString(1,String.valueOf(intMAT));
                    pstaTRANDAU.setString(2, txtNGAYDA.getText());
                    pstaTRANDAU.setString(3, txtDIADIEM.getText());
                    
                    String cbSqlMAG = "SELECT MAG FROM GIAIDAU WHERE TENGIAI= ? ";
                    PreparedStatement pstaMAG = connection.prepareStatement(cbSqlMAG);
                    pstaMAG.setString(1, cbTENGIAI.getSelectedItem().toString());
                    ResultSet resultMAG = pstaMAG.executeQuery();
                    while(resultMAG.next())
                        pstaTRANDAU.setString(4, resultMAG.getString(1));
                    
                    String cbSqlMADOI = "SELECT MAD FROM DOIBONG WHERE TENDOI= ? ";
                    
                    PreparedStatement pstaMACHUNHA = connection.prepareStatement(cbSqlMADOI);
                    pstaMACHUNHA.setString(1, cbCHUNHA.getSelectedItem().toString());
                    ResultSet resultCHUNHA = pstaMACHUNHA.executeQuery();
                    while(resultCHUNHA.next())
                        pstaTRANDAU.setString(5, resultCHUNHA.getString(1));
                    
                    PreparedStatement pstaMADOIKHACH = connection.prepareStatement(cbSqlMADOI);
                    pstaMADOIKHACH.setString(1, cbDOIKHACH.getSelectedItem().toString());
                    ResultSet resultDOIKHACH = pstaMADOIKHACH.executeQuery();
                    while(resultDOIKHACH.next())
                        pstaTRANDAU.setString(6, resultDOIKHACH.getString(1));
                    
                    pstaTRANDAU.setString(7, txtTISO.getText());
                    pstaTRANDAU.executeUpdate();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        
        JButton btnHUY = new JButton("Hủy");
        btnHUY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNGAYDA.setText("");
                txtDIADIEM.setText("");
                txtTISO.setText("");
                cbTENGIAI.setSelectedIndex(0);
                cbCHUNHA.setSelectedIndex(-1);
                cbDOIKHACH.setSelectedIndex(-1);
            }
        });
        
        pn.setLayout(new GridLayout(8, 2));
        pn.add(cbTENGIAI);
        pn.add(txtNGAYDA);
        pn.add(txtDIADIEM);
        pn.add(cbCHUNHA);
        pn.add(cbDOIKHACH);
        pn.add(txtTISO);
        pn.add(btnTHEM);
        pn.add(btnHUY);
        fr.add(pn);
        fr.setSize(600, 500);
        fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fr.setVisible(true);        
    }
    
    
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        CK1_1819 st = new CK1_1819();
        st.Cau4();
    }
    
}
