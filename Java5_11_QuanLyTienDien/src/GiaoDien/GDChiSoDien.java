/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien;
import ControllerGD.ChiSoDienController;
import DatabaseConnect.DBConnect;
import Model.ChiSoDien;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Chu Phuong
 */
public class GDChiSoDien extends javax.swing.JFrame {
    private DefaultTableModel dtm;
    Connection connection;
    DBConnect dbConnect;
    /**
     * Creates new form GDChiSoDien
     */
    public GDChiSoDien() {
        initComponents();
        dtm = new DefaultTableModel();
        String[] tableHeaders = {"Mã Khách Hàng", "Mã Tháng", "Chỉ Số Cũ", "Chỉ Số Mới"};
        dtm.setColumnIdentifiers(tableHeaders);
        TbChiSoDien.setModel(dtm);
        getDataFromDB();
    }
    public ChiSoDien getDataFromResultSet(ResultSet rs) {
        ChiSoDien capNhatChiSoDien = null;
        try {
            String maKH = rs.getString("maKH");
            String maThang = rs.getString("maThang");
            String chiSoMoi = rs.getString("chiSoMoi");
            String chiSoCu = rs.getString("chiSoCu");
            capNhatChiSoDien = new ChiSoDien(maKH, maThang, Integer.parseInt(chiSoCu), Integer.parseInt(chiSoMoi));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return capNhatChiSoDien;
    }
    public void getDataFromDB() {
        try {
            dtm.setRowCount(0);
            dbConnect=new DBConnect();
            connection=dbConnect.connect();
            String sqlQuery = "Select * from chisodien";
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiSoDien model = getDataFromResultSet(rs);
                dtm.addRow(model.toStringArray());
            }
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void createDemo(String maKh, String mathang) {
        try {
            String sql = "select mahd from hoadon";
            dbConnect=new DBConnect();
            connection=dbConnect.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            String maHD_last = "";
            while (rs.next()) {
                maHD_last = rs.getString("mahd");
            }
            String maHD_next = String.valueOf(maHD_last.charAt(2)) + maHD_last.charAt(3) + maHD_last.charAt(4);
            int newMaHD = Integer.parseInt(maHD_next) + 1;
            DecimalFormat df = new DecimalFormat("#000");
            String newmaHD = df.format(newMaHD);
            String maHD = "HD" + newmaHD;
            String sql2 = "insert hoadon values(?,?,null,null)";
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps2.setString(1, maHD);
            ps2.setString(2, maKh);
            ps2.executeUpdate();
            String sql3 = "updateHoaDon ?,? ";
            PreparedStatement ps3 = connection.prepareStatement(sql3);
            ps3.setString(1, maKh);
            ps3.setString(2, mathang);
            ps3.executeUpdate();
            String sql4 = "insert into THONGKE values(?,?,?,0)";
            PreparedStatement ps4 = connection.prepareStatement(sql4);
            ps4.setString(1, maKh);
            ps4.setString(2, mathang);
            ps4.setString(3, maHD);
            ps4.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChiSoDienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void createHD() {
        try {
            String sql = "select maKH, maThang from CHISODIEN \n"
                    + "	where maKH not in (select (maKH) from HOADON)";
            dbConnect=new DBConnect();
            connection=dbConnect.connect();
            PreparedStatement ps = connection.prepareStatement(sql);// tìm xem có chỉ số điện mới được tạo nhưng chưa tạo hoá đơn
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                createDemo(rs.getString("maKH"), rs.getString("maThang"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChiSoDienController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//    private void deleteTK() {
//        try {
//            String sql1 = "delete from THONGKE where MaKH not in (select MaKH from CHISODIEN)";
//            dbConnect=new DBConnect();
//            connection=dbConnect.connect();
//            PreparedStatement ps1 = connection.prepareStatement(sql1);
//            ps1.executeQuery();
//        } catch (SQLException ex) {
//            //Logger.getLogger(ChiSoDienController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//    private void deleteHD() {
//        try {
//            String sql2 = "delete from HOADON where MaKH not in (select MaKH from THONGKE)";
//            dbConnect=new DBConnect();
//            connection=dbConnect.connect();
//            PreparedStatement ps2 = connection.prepareStatement(sql2);
//            ps2.executeQuery();
//        } catch (SQLException ex) {
//            //Logger.getLogger(ChiSoDienController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        txtMaThang = new javax.swing.JTextField();
        txtChiSoCu = new javax.swing.JTextField();
        txtChiSoMoi = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TbChiSoDien = new javax.swing.JTable();
        btnDong = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Mã khách hàng:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Mã tháng:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Chỉ số cũ:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Chỉ số mới:");

        btnThem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Notes.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/baseline_delete_forever_black_24dp.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Refresh.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        TbChiSoDien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TbChiSoDien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Mã tháng", "Chỉ số cũ", "Chỉ số mới"
            }
        ));
        TbChiSoDien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TbChiSoDienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TbChiSoDien);

        btnDong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Delete.png"))); // NOI18N
        btnDong.setText("Đóng");
        btnDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 204));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Quản lý chỉ số điện");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(345, 345, 345))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel5)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1012, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(145, 145, 145)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(40, 40, 40))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(28, 28, 28)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaThang, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtChiSoCu, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtChiSoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(237, 237, 237)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReset)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnThem, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDong)))
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnDong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtMaThang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtChiSoCu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtChiSoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addGap(39, 39, 39)
                                .addComponent(btnSua)
                                .addGap(36, 36, 36)
                                .addComponent(btnXoa)
                                .addGap(44, 44, 44)
                                .addComponent(btnReset)))
                        .addGap(26, 26, 26)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

        // TODO add your handling code here:
        if (txtMaKH.getText().isEmpty() || txtMaThang.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "không bỏ trống mã khách hàng hoặc mã tháng thông tin!");
        }else{
        try {
            String MaKh = txtMaKH.getText().toString();
            String MaThang=txtMaThang.getText().toString();
            
            ChiSoDienController chiSoDienCT=new ChiSoDienController();
                int choose = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa khách hàng này không?",
                         "Thông báo", JOptionPane.YES_NO_OPTION);
                if (choose == JOptionPane.NO_OPTION)
                {
                    return;
                } else {
                    chiSoDienCT.delete(MaKh,MaThang);
                    //deleteTK();
                    //deleteHD();
                    getDataFromDB();
                }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        }
        
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

        // TODO add your handling code here:
        if (txtMaKH.getText().isEmpty() || txtMaThang.getText().isEmpty() || txtChiSoCu.getText().isEmpty() || txtChiSoMoi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải điền tất cả thông tin!");
        } else {
            try {
                int csm = Integer.parseInt(txtChiSoMoi.getText());
                int csc = Integer.parseInt(txtChiSoCu.getText());
                if (csm < csc) {
                    JOptionPane.showMessageDialog(this, "không được để chỉ số mới nhở hơn chỉ số cũ!");
                } else {
                    ChiSoDien csd1= new ChiSoDien(txtMaKH.getText(), txtMaThang.getText(), csc, csm);
                    dtm.addRow(csd1.toStringArray());
                    ChiSoDienController chisodienCT=new ChiSoDienController();
                    chisodienCT.insert(csd1);
                    createHD();
                    getDataFromDB();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Nhập sai chỉ số!");
            }
    }//GEN-LAST:event_btnThemActionPerformed
    }
    private void TbChiSoDienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TbChiSoDienMouseClicked

        // TODO add your handling code here:
        int row = TbChiSoDien.getSelectedRow();
        if (row >= 0) {
            String MaKH = TbChiSoDien.getValueAt(row, 0).toString();
            String MaThang = TbChiSoDien.getValueAt(row, 1).toString();
            String ChiSoCu = TbChiSoDien.getValueAt(row, 2).toString();
            String ChiSoMoi = TbChiSoDien.getValueAt(row, 3).toString();
            txtMaKH.setEditable(false);
            txtMaKH.setText(MaKH);
            txtMaThang.setEditable(false);
            txtMaThang.setText(MaThang);
            txtChiSoCu.setText(ChiSoCu);
            txtChiSoMoi.setText(ChiSoMoi);
            
        }
    }//GEN-LAST:event_TbChiSoDienMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed

        // TODO add your handling code here:
        if (txtMaKH.getText().isEmpty() || txtMaThang.getText().isEmpty() || txtChiSoCu.getText().isEmpty() || txtChiSoMoi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải điền tất cả thông tin!");
        }else{
        try {
            int csc = Integer.parseInt(txtChiSoCu.getText());
            int csm = Integer.parseInt(txtChiSoMoi.getText());
            if (csm < csc) {
                    JOptionPane.showMessageDialog(this, "không được để chỉ số mới nhở hơn chỉ số cũ!");
                } else {
                    ChiSoDien csd1= new ChiSoDien(txtMaKH.getText(), txtMaThang.getText(), csc, csm);
                    ChiSoDienController chisodienCT=new ChiSoDienController();
                    chisodienCT.update(csd1);
                }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        }
        getDataFromDB();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongActionPerformed
        // TODO add your handling code here:
        int choose=JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát khỏi trang này không?",
                     "Thông báo", JOptionPane.YES_NO_OPTION);
        if(choose==JOptionPane.NO_OPTION){
            return;
        }
        else{
            this.dispose();
        }
    }//GEN-LAST:event_btnDongActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        txtMaKH.setText("");
        txtMaKH.setEditable(true);
        txtMaThang.setText("");
        txtMaThang.setEditable(true);
        txtChiSoCu.setText("");
        txtChiSoMoi.setText("");
    }//GEN-LAST:event_btnResetActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GDChiSoDien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GDChiSoDien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GDChiSoDien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GDChiSoDien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GDChiSoDien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TbChiSoDien;
    private javax.swing.JButton btnDong;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtChiSoCu;
    private javax.swing.JTextField txtChiSoMoi;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaThang;
    // End of variables declaration//GEN-END:variables
}
