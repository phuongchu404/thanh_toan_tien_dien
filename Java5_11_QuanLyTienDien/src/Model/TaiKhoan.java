/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Chu Phuong
 */
public class TaiKhoan extends Login{
    private String cauHoi;
    private String cauTraLoi;

    public TaiKhoan() {
    }
    public TaiKhoan(String taiKhoan, String matKhau,String cauHoi, String cauTraLoi) {
        super(taiKhoan, matKhau);
        this.cauHoi = cauHoi;
        this.cauTraLoi = cauTraLoi;
    }
    
    public String getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }

    public String getCauTraLoi() {
        return cauTraLoi;
    }

    public void setCauTraLoi(String cauTraLoi) {
        this.cauTraLoi = cauTraLoi;
    }
    
    
}
