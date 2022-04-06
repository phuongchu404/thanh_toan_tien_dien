/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerGD;

import DatabaseConnect.DBConnect;
import GiaoDien.GDQuanLyTaiKhoan;
import Model.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Chu Phuong
 */
public class TaiKhoanController {
    Connection connection;
    DBConnect dbConnec;
    GDQuanLyTaiKhoan viewTK;
    public void insert(TaiKhoan tk){
        try{
            String sql = "insert TAIKHOAN values(?,?,?,?)";
            dbConnec=new DBConnect();
            connection=dbConnec.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, tk.getTaiKhoan());
            ps.setString(2, tk.getMatKhau());
            ps.setString(3, tk.getCauHoi());
            ps.setString(4, tk.getCauTraLoi());
            int i = ps.executeUpdate();
            if(i>0)
                JOptionPane.showMessageDialog(viewTK, "Đăng kí thành công!");
        } catch (SQLException ex) {
            if(ex.toString().indexOf("PRIMARY KEY")>0){
                JOptionPane.showMessageDialog(viewTK, "Tài khoản đã tồn tại!");
            } else
                Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update(TaiKhoan tk){
        try {
            String sql = "update TAIKHOAN set mat_khau = ?, cauhoi = ? , traloi = ? where tai_khoan = ?";
            dbConnec=new DBConnect();
            connection=dbConnec.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, tk.getMatKhau());
            ps.setString(2, tk.getCauHoi());
            ps.setString(3, tk.getCauTraLoi());
            ps.setString(4, tk.getTaiKhoan());
            int i = ps.executeUpdate();
            if(i>0)
                JOptionPane.showMessageDialog(viewTK, "Thay đổi thành công!");
            else
                JOptionPane.showMessageDialog(viewTK, "Thay đổi thất bại!");
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(String tenTK){
        try{
        String sql = "delete from taikhoan where tai_khoan = ?";
        dbConnec=new DBConnect();
        connection=dbConnec.connect();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, tenTK);
        int i = ps.executeUpdate();
        if (i > 0){
            JOptionPane.showMessageDialog(viewTK, "Xoá thành công!");    
        }else{
            JOptionPane.showMessageDialog(viewTK, "Xoá thất bại!");
        }
        }catch (SQLException ex) {
            Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
