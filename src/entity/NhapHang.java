package entity;

import helper.XDate;
import java.util.Date;

public class NhapHang {
    private String maNhap;
    private String tenSPNhap;
    private String loaiSP;
    private String soLieu;
    private float soLuong;
    private String ngayNhap;
    private float tienNhap;
    
    @Override
    public String toString() {
        return tenSPNhap + maNhap;
    }
    
    public NhapHang(){
        
    }

    public NhapHang(String maNhap, String tenSPNhap, String loaiSP, String soLieu, float soLuong, String ngayNhap, float tienNhap) {
        this.maNhap = maNhap;
        this.tenSPNhap = tenSPNhap;
        this.loaiSP = loaiSP;
        this.soLieu = soLieu;
        this.soLuong = soLuong;
        this.ngayNhap = ngayNhap;
        this.tienNhap = tienNhap;
    }

    public String getMaNhap() {
        return maNhap;
    }

    public void setMaNhap(String maNhap) {
        this.maNhap = maNhap;
    }

    public String getTenSPNhap() {
        return tenSPNhap;
    }

    public void setTenSPNhap(String tenSPNhap) {
        this.tenSPNhap = tenSPNhap;
    }

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    public String getSoLieu() {
        return soLieu;
    }

    public void setSoLieu(String soLieu) {
        this.soLieu = soLieu;
    }

    public float getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(float soLuong) {
        this.soLuong = soLuong;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public float getTienNhap() {
        return tienNhap;
    }

    public void setTienNhap(float tienNhap) {
        this.tienNhap = tienNhap;
    }       
}
