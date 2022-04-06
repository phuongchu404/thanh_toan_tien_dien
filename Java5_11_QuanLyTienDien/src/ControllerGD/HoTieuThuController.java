/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerGD;

import DatabaseConnect.DBConnect;
import GiaoDien.GDQuanLyHoTieuThu;
import Model.HoTieuThuDien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Chu Phuong
 */
public class HoTieuThuController {
    Connection connection;
    DBConnect dbConnec;
    GDQuanLyHoTieuThu viewHTTD;
    public void insert(HoTieuThuDien httd) {
        try {
            String sql = "INSERT HOTIEUTHU VALUES (?, ? ,? ,? ,? ,? ,? ,? ,?)";
            dbConnec=new DBConnect();
            connection=dbConnec.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, httd.getMaKH());
            ps.setString(2, httd.getHoTen());
            ps.setString(3, httd.getCmnd());
            ps.setString(4, httd.getDiaChi());
            ps.setString(5, httd.getGioiTinh());
            ps.setObject(6, httd.utilDateToSQLDate(httd.getNgaySinh()));
            ps.setString(7, httd.getSdt());
            ps.setObject(8, httd.utilDateToSQLDate(httd.getNgayDK()));
            ps.setString(9, httd.getLoaiDien());
            int result = ps.executeUpdate();
            if (result == 1) {
                JOptionPane.showMessageDialog(viewHTTD, "Thêm thành công!");
            } else {
                JOptionPane.showMessageDialog(viewHTTD, "Thêm thất bại!");
            }
            ps.close();
        } catch (SQLException ex) {
            if (ex.toString().contains("PRIMARY KEY")) {
                JOptionPane.showMessageDialog(viewHTTD, "Trùng khoá chính!");
            }else {
                Logger.getLogger(HoTieuThuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void update(HoTieuThuDien httd){
        try{
            String sql = "UPDATE [dbo].[HOTIEUTHU] SET hoTen = ?"
                        + ",CMND = ?"
                        + ",diaChi = ?"
                        + ",gioiTinh = ?"
                        + ",ngaySinh = ?"
                        + ",sdt = ?"
                        + ",ngayDangKi = ?"
                        + ",loaidien = ?"
                        + " WHERE maKH like ?";
                dbConnec=new DBConnect();
                connection=dbConnec.connect();
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, httd.getHoTen());
                ps.setString(2, httd.getCmnd());
                ps.setString(3, httd.getDiaChi());
                ps.setString(4, httd.getGioiTinh());
                ps.setObject(5, httd.utilDateToSQLDate(httd.getNgaySinh()));
                ps.setString(6, httd.getSdt());
                ps.setObject(7, httd.utilDateToSQLDate(httd.getNgayDK()));
                ps.setString(8, httd.getLoaiDien());
                ps.setString(9, httd.getMaKH());
                int result = ps.executeUpdate();
                if (result == 1) {
                    JOptionPane.showMessageDialog(viewHTTD, "Thay đổi giá trị thành công!");
                }else {
                    JOptionPane.showMessageDialog(viewHTTD, "Thay đổi thất bại!");
                }
                ps.close();
        } catch (SQLException ex) {
                Logger.getLogger(HoTieuThuController.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(viewHTTD, "Không thể cập nhật giá trị!");
            }
        }
    public void deleteFromChiSoDien(String MaKH) {
        try {
            String sql1 = "delete from CHISODIEN where MaKH=?";
            dbConnec=new DBConnect();
            connection=dbConnec.connect();
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ps1.setString(1, MaKH);
            int result = ps1.executeUpdate();
            ps1.close();
            ps1.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(HoTieuThuController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void deleteFromThongKe(String MaKH) {
        try {
            String sql2 = "delete from THONGKE where MaKH=?";
            dbConnec=new DBConnect();
            connection=dbConnec.connect();
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps2.setString(1, MaKH);
            int result = ps2.executeUpdate();
            ps2.close();
            ps2.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(HoTieuThuController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void deleteFromHoaDon(String MaKH){
        try {
                String sq3 = "DELETE FROM HOADON WHERE MaKH=?";
                dbConnec=new DBConnect();
                connection=dbConnec.connect();
                PreparedStatement ps3 = connection.prepareStatement(sq3);
                ps3.setString(1, MaKH);
                int result = ps3.executeUpdate();
                ps3.close();
        } catch (SQLException ex) {
            {
                Logger.getLogger(HoTieuThuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void delete(String MaKH){
        try {
                String sq4 = "DELETE FROM HOTIEUTHU WHERE MaKH=?";
                dbConnec=new DBConnect();
                connection=dbConnec.connect();
                PreparedStatement ps4 = connection.prepareStatement(sq4);
                ps4.setString(1, MaKH);
                int result = ps4.executeUpdate();
                if (result == 1) {
                    JOptionPane.showMessageDialog(viewHTTD, "xóa thành công!");
                } else {
                    JOptionPane.showMessageDialog(viewHTTD, "xóa thất bại!");
                }
                ps4.close();
        } catch (SQLException ex) {
            {
                Logger.getLogger(HoTieuThuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
