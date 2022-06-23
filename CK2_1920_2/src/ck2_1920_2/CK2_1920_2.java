/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ck2_1920_2;

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

/**
 *
 * @author LENOVO
 */
public class CK2_1920_2 {
    private Connection connection;
    /**
     * @param args the command line arguments
     */
    public  void Cau1() throws Exception{

        Class.forName("oracle.jdbc.OracleDriver").newInstance();
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","FINAL_EXAM","success");
        JFrame fr = new JFrame();
        JPanel pn = new JPanel();
        
        JTextField txtTENCC = new JTextField();
        JTextField txtDIACHI =new JTextField();
        JTextField txtDTHOAI = new JTextField();
        JTextField txtEMAIL = new JTextField();
        JButton btnTHEM = new JButton("Thêm");

        btnTHEM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement pstaMaxNCC = connection.prepareStatement("SELECT MAX(MANCC) FROM NHACC");
                    ResultSet resMaxNCC = pstaMaxNCC.executeQuery();    
                    
                    int intMANCC;
                    resMaxNCC.next();
                    if(resMaxNCC.getString(1)==null)
                        intMANCC= 1;
                    else
                        intMANCC = Integer.parseInt(resMaxNCC.getString(1)) + 1;
                    
                    String sqString = "INSERT INTO NHACC VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(sqString);
                    statement.setString(1, String.valueOf(intMANCC));
                    statement.setString(2, txtTENCC.getText());
                    statement.setString(3, txtDIACHI.getText());
                    statement.setString(4, txtDTHOAI.getText());
                    statement.setString(5, txtEMAIL.getText());
                    statement.executeUpdate();
                    
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                
            }
        });
        
        pn.setLayout(new GridLayout(5, 2));
        pn.add(txtTENCC);
        pn.add(txtDIACHI);
        pn.add(txtDTHOAI);
        pn.add(txtEMAIL);
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
        
        JTextField txtMANCC = new JTextField();
        JTextField txtTENNCC =new JTextField();
        txtTENNCC.disable();
        JTextField txtTENSP = new JTextField();
        JTextField txtDONGIA = new JTextField();
        JButton btnTHEM = new JButton("Thêm");
        
        txtMANCC.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                String sqpStr = "SELECT TENNCC FROM NHACC WHERE MANCC = "+ txtMANCC.getText();
                try {
                        Statement psta = connection.createStatement();
                        //psta.setString(1, txtMANCC.getText());
                        ResultSet getTENSP =psta.executeQuery(sqpStr);
                        while(getTENSP.next())
                            txtTENNCC.setText(getTENSP.getString("TENNCC"));
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                } 
            }
        });
        
        btnTHEM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement pstaMaxMASP = connection.prepareStatement("SELECT MAX(MANCC) FROM NHACC");
                    ResultSet resMaxMASP = pstaMaxMASP.executeQuery();    
                    
                    int intMASP;
                    resMaxMASP.next();
                    if(resMaxMASP.getString(1)==null)
                        intMASP= 1;
                    else
                        intMASP = Integer.parseInt(resMaxMASP.getString(1)) + 1;
                    
                    
                    String sqString = "INSERT INTO SANPHAM VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(sqString);
                    statement.setString(1, String.valueOf(intMASP));
                    statement.setString(2, txtTENSP.getText());
                    statement.setString(3, "0");
                    statement.setString(4, txtDONGIA.getText());
                    statement.setString(5, txtMANCC.getText());
                    statement.executeUpdate();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        
        pn.setLayout(new GridLayout(5, 2));
        pn.add(txtMANCC);
        pn.add(txtTENNCC);
        pn.add(txtTENSP);
        pn.add(txtDONGIA);
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
        
        JTextField txtNGLAP = new JTextField();
        JTextField txtGHICHU =new JTextField();
        
        JComboBox<String> cbTENNCC = new JComboBox();
        
//        String cbSql = "SELECT TENNCC FROM NHACC";
//        Statement staTENNCC = connection.createStatement();
        PreparedStatement psta1 = connection.prepareStatement("SELECT TENNCC FROM NHACC");
        ResultSet resultTENNCC = psta1.executeQuery();
        while(resultTENNCC.next())
            cbTENNCC.addItem(resultTENNCC.getString(1));
        
        Object[] colsName1 = {"Sản phẩm"};
        DefaultTableModel model1 = new DefaultTableModel(null, colsName1);
        JTable tbSanPham = new JTable(model1);  
        
        cbTENNCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model1.setRowCount(0);
                try {
                    String tbSanPhamSql = "SELECT TENSP FROM SANPHAM SP, NHACC NCC WHERE SP.MANCC = NCC.MANCC AND TENNCC = "
                            + cbTENNCC.getSelectedItem().toString();
                    PreparedStatement pstaSanPham = connection.prepareStatement(tbSanPhamSql);
                    //pstaSanPham.setString(1, cbTENNCC.getSelectedItem().toString());
                    ResultSet resultSanPham = pstaSanPham.executeQuery();
                    while(resultSanPham.next()){
                        String rowString[] = {resultSanPham.getString(1)};
                        model1.addRow(rowString);            
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        
        Object[] colsName2 = {"Sản phẩm", "Số lượng"};
        DefaultTableModel model2 = new DefaultTableModel(null, colsName2);
        JTable tbSanPhamNhap = new JTable(model2);   
        
        tbSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int row = tbSanPham.rowAtPoint(evt.getPoint());
                String rowString[] = {model1.getValueAt(row, 0).toString()};
                model2.addRow(rowString);
            }
        });
        
        JButton btnTaoPhieuNhap = new JButton("Tạo phiếu nhập");
        
        btnTaoPhieuNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement MAPN = connection.prepareStatement("SELECT MAX(MAPN) FROM PHIEUNHAP");
                    ResultSet resMaxMAPN = MAPN.executeQuery();    
                    
                    int intMAPN;
                    resMaxMAPN.next();
                    if(resMaxMAPN.getString(1)==null)
                        intMAPN= 1;
                    else
                        intMAPN = Integer.parseInt(resMaxMAPN.getString(1)) + 1;
                    
                    
                    String sqlHoaDon = "INSERT INTO PHIEUNHAP VALUES (?, ?, ?, ?)";
                    PreparedStatement pstaHoaDon = connection.prepareStatement(sqlHoaDon);
                    pstaHoaDon.setString(1, String.valueOf(intMAPN));
                    pstaHoaDon.setString(2, txtNGLAP.getText());
                    pstaHoaDon.setString(3, txtGHICHU.getText());
                    pstaHoaDon.setString(4, "0");
                    pstaHoaDon.executeUpdate();
                    
                    int TONGTIEN =0;
                    
                    for(int i=0; i< model2.getRowCount(); i++){
                        String sqlCTHD = "INSERT INTO CT_NHAP VALUES (?, ?, ?)";
                        PreparedStatement pstaCTNHAP = connection.prepareStatement(sqlCTHD);
                        pstaCTNHAP.setString(1,String.valueOf(intMAPN));
                        
                        PreparedStatement pstaMASP = connection.prepareStatement("SELECT MASP FROM SANPHAM WHERE TENSP = ?");
                        pstaMASP.setString(1, model2.getValueAt(i, 0).toString());
                        ResultSet resultMASP = pstaMASP.executeQuery();
                        while (resultMASP.next())
                            pstaCTNHAP.setString(2, resultMASP.getString(1));
                        
                        pstaCTNHAP.setString(3, model2.getValueAt(i, 1).toString());
                        pstaCTNHAP.executeUpdate();
                        
                        PreparedStatement pstaDonGia =connection.prepareStatement("SELECT DONGIA FROM SANPHAM WHERE TENSP = ?") ;
                        pstaDonGia.setString(1,model2.getValueAt(i, 0).toString());
                        ResultSet resDonGia = pstaDonGia.executeQuery();
                        while(resDonGia.next())
                            TONGTIEN += Integer.parseInt(resDonGia.getString(1))* Integer.parseInt(model2.getValueAt(i, 1).toString());
                    }
                    PreparedStatement pstaTongTien =connection.prepareStatement("UPDATE PHIEUNHAP SET TONGTIEN = ? WHERE MAPN = ?") ;
                    pstaTongTien.setString(1, String.valueOf(TONGTIEN));
                    pstaTongTien.setString(2, String.valueOf(intMAPN));
                    pstaTongTien.executeUpdate();
                    
                    
                    
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        
        pn.setLayout(new GridLayout(6, 2));
        pn.add(txtNGLAP);
        pn.add(txtGHICHU);
        pn.add(cbTENNCC);
        pn.add(new JScrollPane(tbSanPham));
        pn.add(new JScrollPane(tbSanPhamNhap));
        pn.add(btnTaoPhieuNhap);
        fr.add(pn);
        fr.setSize(600, 500);
        fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }
    
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        CK2_1920_2 st = new CK2_1920_2();
        st.Cau3();
    }
    
}
