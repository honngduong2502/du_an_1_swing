package model;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import dao.*;
import entity.*;
import entity.HoaDon;
import helper.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.plaf.synth.SynthTableHeaderUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import scrollbar.ScrollBarCustom;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class BanHangJFrame extends javax.swing.JFrame {

    public BanHangJFrame() {
        initComponents();
        nameCollum();
        fillComboBoxBan();
        getNguoiDung();
        fillComboBoxSanPham();
        fillTableDanhSachSP();
        fillSanPham();
        setTongTien();

        fillBan();
    }
    int mpX, mpY;
    int hdCheck = 0;
    String maHDCT = "";
    String maHD = "";
    BanDAO daoBan = new BanDAO();
    SanPhamDAO daoSP = new SanPhamDAO();
    HoaDonDAO daoHD = new HoaDonDAO();
    HoaDonCTDAO daoHDCT = new HoaDonCTDAO();
    SanPham sanPham;

    public void nameCollum() {
        panelBanHang.getViewport().setOpaque(false);
        panelBanHang.setVerticalScrollBar(new ScrollBarCustom());
        panelBanHang.setViewportBorder(null);

        JTableHeader tableHeader = tblSanPham.getTableHeader();
        tableHeader.setUI(new SynthTableHeaderUI());
        Font HeaderFont = new Font("SansSerif", Font.PLAIN, 18);
        tableHeader.setOpaque(false);
        tableHeader.setBackground(new Color(81, 145, 255));
        tableHeader.setForeground(Color.white);

        tableHeader.setFont(HeaderFont);
        tblSanPham.setRowHeight(25);
    }

    public void eventButtonEnable(JButton button) {
        button.setEnabled(false);
        button.setBackground(Color.GRAY);
    }

    public void eventButton(JButton button, Color color) {
        button.setEnabled(true);
        button.setBackground(color);
    }

    public void check() {
        int row = tblSanPham.getRowCount();
        int viTri = tblSanPham.getSelectedRow();
        if (row == 0) {
            eventButtonEnable(btnThanhToan);
            eventButtonEnable(btnHuy);

            btnThanhToan.setEffectColor(Color.gray);
            btnHuy.setEffectColor(Color.gray);
        } else {
            eventButton(btnThanhToan, new Color(0, 0, 0));
            eventButton(btnHuy, new Color(255, 51, 51));

            btnThanhToan.setEffectColor(Color.white);
            btnHuy.setEffectColor(Color.white);
        }
        if (viTri < 0) {
            eventButtonEnable(btnXoa);
            eventButtonEnable(btnCapNhat);

            btnXoa.setEffectColor(Color.gray);
            btnCapNhat.setEffectColor(Color.gray);
        }
        if (txtTrangThaiHD.getText().equals("Chưa tạo hóa đơn")) {
            eventButtonEnable(btnThem);
            eventButtonEnable(btnXoa);
            eventButtonEnable(btnCapNhat);

            btnThem.setEffectColor(Color.gray);
            btnXoa.setEffectColor(Color.gray);
            btnCapNhat.setEffectColor(Color.gray);

            txtSoLuong.setEnabled(false);
//            txtGiamGia.setEnabled(false);

        } else {
            eventButton(btnThem, new Color(255, 0, 0));
            btnThem.setEffectColor(Color.white);
            txtSoLuong.setEnabled(true);
//            txtGiamGia.setEnabled(true);
        }

    }

    public void checkSanPham() {
        int row = tblSanPham.getRowCount();
        int so = 0;
        if (cboSanPham.getSelectedItem() != null) {
            SanPham sanPham = (SanPham) cboSanPham.getSelectedItem();
            SanPham list = daoSP.selectById(sanPham.getMaSP());
            List<String> sp = new ArrayList<>();
            for (int i = 0; i < row; i++) {
                String maSP = tblSanPham.getValueAt(i, 0).toString();
                String soLuong = tblSanPham.getValueAt(0, 3).toString();
                sp.add(maSP);
                if (String.valueOf(list.getMaSP()).equals(sp.get(i))) {
                    so = 1;
//                    String giamGia = String.valueOf(tblSanPham.getValueAt(i, 4));
//                    String giamGia2 = giamGia.substring(0, giamGia.indexOf(" VND"));

//                    txtGiamGia.setText(giamGia2);
                    String thanhTien = String.valueOf(tblSanPham.getValueAt(i, 4));
                    String thanhTien2 = thanhTien.substring(0, thanhTien.indexOf(" VND"));
                    txtThanhTien.setText(thanhTien2);
                    txtSoLuong.setText(soLuong);
                    tblSanPham.setRowSelectionInterval(i, i);
                }

            }

        }
        if (so == 1) {
            eventButtonEnable(btnThem);
            btnThem.setEffectColor(Color.gray);

            eventButton(btnXoa, new Color(0, 204, 52));
            btnXoa.setEffectColor(Color.white);
            eventButton(btnCapNhat, new Color(0, 102, 204));
            btnCapNhat.setEffectColor(Color.white);

        } else {
            if (!txtTrangThaiHD.getText().equals("Chưa tạo hóa đơn")) {
                eventButton(btnThem, new Color(255, 0, 0));
                btnThem.setEffectColor(Color.white);
                eventButtonEnable(btnXoa);
                eventButtonEnable(btnCapNhat);

                btnXoa.setEffectColor(Color.gray);
                btnCapNhat.setEffectColor(Color.gray);
            }

        }

    }

    public void fillComboBoxBan() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMaBan.getModel();
        model.removeAllElements();
        List<Ban> list = daoBan.selectAll();
        for (Ban ban : list) {
            model.addElement(ban);
        }
        fillBan();

    }

    public void fillBan() {
        if (cboMaBan.getSelectedItem() != null) {

            Ban ban = (Ban) cboMaBan.getSelectedItem();
            Ban list = daoBan.selectById(String.valueOf(ban.getMaBan()));
            if (list.isTrangThai() == true) {
                cboTrangThai.setSelectedItem("Có khách");
                cboTrangThai.setEnabled(false);
                fillHDBan();
                hdCheck = 0;
            } else {
                cboTrangThai.setSelectedItem("Trống");
//                cboMaBan.setSelectedItem(cboMaBan.getSelectedItem() + " - " + cboTrangThai.getSelectedItem());
                cboTrangThai.setEnabled(true);
                txtMaHD.setText("");
            }
            if (!cboTrangThai.getSelectedItem().equals("Có khách")) {
//                cboMaBan.setSelectedItem(ban + " - " + cboTrangThai.getSelectedItem());
                fillHoaDon();
                hdCheck = 1;
            }

        }

    }

    public void updateBan(boolean trangThai) {
        if (cboMaBan.getSelectedItem() != null) {
            Ban ban1 = (Ban) cboMaBan.getSelectedItem();
            Ban list2 = daoBan.selectById(String.valueOf(ban1.getMaBan()));

            list2.setTrangThai(trangThai);

            daoBan.updateTrangThai(list2);
        }
    }

    public HoaDon readFormHD(boolean trangThai, boolean Huy, boolean DangBan) {

        Ban ban = (Ban) cboMaBan.getSelectedItem();
        HoaDon hd = new HoaDon();
        hd.setMaHD(txtMaHD.getText());
        Date ngayTao = XDate.toDate(txtNgayTao.getText(), "dd-MM-yyyy");
        String nt = XDate.toString(ngayTao, "yyyy-MM-dd");
        hd.setNgayTao(nt);
        hd.setTenDN(txtTenDN.getText());
        hd.setTrangThai(trangThai);
        hd.setHuy(Huy);
        hd.setBan(ban.getMaBan());
        hd.setDangBan(DangBan);

        return hd;
    }

    public void insertHD() {
        HoaDon hd = this.readFormHD(false, false, true);
        HoaDon list = daoHD.selectById(hd.getMaHD());
        if (list == null) {
            daoHD.insert(hd);
            fillBan();
            MsgBox.alert(this, "Đã tạo hóa đơn " + hd.getMaHD() + "!!");
        }

    }

    public void fillHoaDon() {

        List<Object> list = daoHD.selectMaxMaHD();
        String ma = "";
        int hdd = 0;
        if (list != null) {
            for (Object hd : list) {

                hdd = Integer.parseInt(hd.toString().substring(2, hd.toString().length()));

                if (hdd >= 99 && hdd < 999) {
                    hdd = Integer.parseInt(hd.toString().substring(2, hd.toString().length()));
                    ma += "HD" + hdd;

                } else if (hdd < 9) {
                    hdd = Integer.parseInt(hd.toString().substring(4, hd.toString().length()));
                    ma += "HD00" + (hdd + 1);
                } else if (hdd >= 9) {
                    hdd = Integer.parseInt(hd.toString().substring(3, hd.toString().length()));
                    ma += "HD0" + (hdd + 1);
                }
            }
        }
        txtMaHD.setText(ma);
        txtTrangThaiHD.setText("Chưa tạo hóa đơn");
        txtTrangThaiHD.setForeground(Color.blue);

    }

    public void fillHDBan() {
        if (cboMaBan.getSelectedItem() != null) {
            Ban ban = (Ban) cboMaBan.getSelectedItem();
            List<HoaDon> hd = daoHD.selectByKeyword(ban.getMaBan());

            if (hd != null) {
                for (HoaDon hoadon : hd) {

                    txtMaHD.setText(hoadon.getMaHD());
                    txtTrangThaiHD.setForeground(Color.red);
                    if (hoadon.getTrangThai()) {
                        txtTrangThaiHD.setText("Chua thanh toan");
                    } else if (hoadon.isDangBan()) {
                        txtTrangThaiHD.setText("Đang bán");
                    } else {
                        txtTrangThaiHD.setText("");
                    }
                }
            }

        }
    }

    public void getNguoiDung() {
        if (Auth.isLogin()) {
            String maND = Auth.getUsername();
            txtTenDN.setText(maND);
            NhanVienDAO daoNV = new NhanVienDAO();
            List<NhanVien> list = daoNV.selectByKeyword3(maND);
            for (NhanVien nv : list) {
                txtTenNguoiTao.setText(nv.getTenNV());
            }

        } else {
            txtTenDN.setText("admin123");
            txtTenNguoiTao.setText("Võ Lê Nhật Linh");
        }
    }

    public void fillComboBoxSanPham() {

        DefaultComboBoxModel model = (DefaultComboBoxModel) cboSanPham.getModel();
        model.removeAllElements();
        List<SanPham> list = daoSP.selectAll();
        for (SanPham sp : list) {
            model.addElement(sp);

        }

    }

    public void fillSanPham() {
        if (cboSanPham.getSelectedItem() != null) {

            int viTri = tblSanPham.getSelectedRow();
            SanPham sanPham = (SanPham) cboSanPham.getSelectedItem();
            SanPham list = daoSP.selectById(sanPham.getMaSP());
//            txtSoLuongTon.setText(String.valueOf(list.getSoLuong()));
            txtSoLuong.setText("0");
//            txtGiamGia.setText("0");
            String donGia = getNum(String.valueOf(list.getGiaBan()));
            txtDonGia.setText(donGia);
            txtThanhTien.setText("0");
            if (viTri < 0) {
                eventButtonEnable(btnXoa);
                eventButtonEnable(btnCapNhat);

                btnXoa.setEffectColor(Color.gray);
                btnCapNhat.setEffectColor(Color.gray);
            }

        }
    }

    public void fillTableDanhSachSP() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        List<Object[]> list = daoHDCT.selectHDCT(txtMaHD.getText());
        if (list != null) {
            for (Object[] rowData : list) {

                String donGia = getNum(rowData[2].toString());
                String thanhTien = getNum(rowData[5].toString());

                Object[] data = new Object[]{rowData[0],
                    rowData[1], donGia, rowData[3], thanhTien
                };
                model.addRow(data);
            }
            setTongTien();
        }
    }

    public String getNum(String num) {

        return num.substring(0, num.indexOf(".")) + " VND";
    }

    public HoaDonCT readFormSanPham() {
        fillHoaDonCT();
        SanPham sp = (SanPham) cboSanPham.getSelectedItem();
        HoaDonCT hdct = new HoaDonCT();
        hdct.setMaHD(txtMaHD.getText());
        hdct.setMaHDCT(maHDCT);
        hdct.setMaSP(sp.getMaSP());
        hdct.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        hdct.setDonGia(Double.valueOf(txtThanhTien.getText()));
        return hdct;

    }
    String maHDCT2 = "";

    public HoaDonCT readFormSanPhamUP() {
        getMaHDCT();
        HoaDonCT hdct = new HoaDonCT();
        hdct.setMaHDCT(maHDCT2);
        hdct.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        hdct.setDonGia(Double.valueOf(txtThanhTien.getText()));

        return hdct;

    }

    public void getMaHDCT() {
        int viTri = tblSanPham.getSelectedRow();

        String maSP = tblSanPham.getValueAt(viTri, 0).toString();
        String maHD = txtMaHD.getText();

        List<HoaDonCT> list = daoHDCT.selectByKeyword(maHD, maSP);
        if (list != null) {
            for (HoaDonCT hdc : list) {
                maHDCT2 = hdc.getMaHDCT();
            }

        }

    }

    public void insertHDCT() {
        HoaDonCT hdct = this.readFormSanPham();
        daoHDCT.insert(hdct);
        updateSP("Thêm");
        fillTableDanhSachSP();
        MsgBox.alert(this, "Đã thêm sản phẩm !");

    }

    public void updateHDCT() {
        int viTri = tblSanPham.getSelectedRow();
        if (viTri < 0) {
            MsgBox.alert(this, "Vui lòng chọn sản phẩm cần sửa!");
            return;
        }
        HoaDonCT hdct = this.readFormSanPhamUP();
        daoHDCT.update(hdct);
        updateSP("Cập nhật");
        fillTableDanhSachSP();
        MsgBox.alert(this, "Cập nhật thành công !");
    }

    public void removeSanPham() {
        int viTri = tblSanPham.getSelectedRow();
        if (viTri < 0) {
            MsgBox.alert(this, "Vui lòng chọn sản phẩm cần xóa!");
            return;
        }
        String maSP = tblSanPham.getValueAt(viTri, 0).toString();
        String maHD = txtMaHD.getText();
        String maHDCT = "";
        List<HoaDonCT> list = daoHDCT.selectByKeyword(maHD, maSP);
        if (list != null) {
            for (HoaDonCT hdct : list) {
                maHDCT = hdct.getMaHDCT();
            }

        }
        boolean chon = MsgBox.confirm(this, "Bạn có chắc chắn muốn xóa ");
        if (chon) {
            daoHDCT.delete(maHDCT);
            updateSP("Xóa");
            fillTableDanhSachSP();
            MsgBox.alert(this, "Đã xóa sản phẩm !");

        }

    }

    public void fillHoaDonCT() {

        List<Object> list = daoHDCT.selectMaxMaHDCT();
        maHDCT = "";
        if (list != null) {
            for (Object hd : list) {

                int hdd = Integer.parseInt(hd.toString().substring(4, hd.toString().length()));

                if (hdd >= 99 && hdd < 999) {
                    hdd = Integer.parseInt(hd.toString().substring(4, hd.toString().length()));
                    maHDCT += "HDCT" + (hdd + 1);
                } else if (hdd < 9) {
                    hdd = Integer.parseInt(hd.toString().substring(4, hd.toString().length()));
                    maHDCT += "HDCT00" + (hdd + 1);
                } else if (hdd >= 9) {
                    hdd = Integer.parseInt(hd.toString().substring(4, hd.toString().length()));
                    maHDCT += "HDCT0" + (hdd + 1);
                }
            }

        }
    }

    public void setTongTien() {

        int j = tblSanPham.getRowCount();
        int tongTien = 0;
        String thanhTien = "";
        if (j != 0) {
            for (int i = 0; i < j; i++) {
                thanhTien = tblSanPham.getValueAt(i, 4).toString();
                int thanhT = Integer.valueOf(thanhTien.substring(0, thanhTien.indexOf(" VND")));
                tongTien += thanhT;
            }
            lblTongTien.setText(String.valueOf(tongTien) + " VND");

        } else {
            lblTongTien.setText(tongTien + " VND");
        }

    }

    public void setThanhTien() {
        String donG = txtDonGia.getText();
        int donGia = Integer.valueOf(donG.substring(0, donG.indexOf(" VND")));
        int soLuong = 0;
        int giamGia = 0;
        if (txtSoLuong.getText().isEmpty()) {
            txtSoLuong.setText("0");
        }
        try {
            soLuong = Integer.valueOf(txtSoLuong.getText());
            if (soLuong > 500) {
                MsgBox.alert(this, "Số lượng phải từ 500 trở xuống!!");
                txtSoLuong.setText("0");
                return;
            }
        } catch (NumberFormatException e) {
            MsgBox.alert(this, "Số lượng vui lòng nhập số!");
            txtSoLuong.setText("");
            txtSoLuong.setText("0");

        }

        int ThanhTien = (donGia * soLuong) - giamGia;

        txtThanhTien.setText(String.valueOf(ThanhTien));

    }

    public void writeFormSP() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboSanPham.getModel();
        SanPham sp = (SanPham) cboSanPham.getSelectedItem();
        int viTri = tblSanPham.getSelectedRow();
        String maSP = tblSanPham.getValueAt(viTri, 0).toString();

        eventButton(btnXoa, new Color(0, 204, 52));
        btnXoa.setEffectColor(Color.white);
        eventButton(btnCapNhat, new Color(0, 102, 204));
        btnCapNhat.setEffectColor(Color.white);

        List<SanPham> list1 = daoSP.selectSanPham(maSP);
        if (!list1.isEmpty()) {
            for (SanPham sp1 : list1) {
                if (sp1.getMaSP().equals(maSP)) {
                    model.setSelectedItem(sp1);
                }
            }
        }

        SanPham list = daoSP.selectById(maSP);

        String donGia = getNum(String.valueOf(list.getGiaBan()));
        txtDonGia.setText(donGia);

        txtSoLuong.setText(String.valueOf(tblSanPham.getValueAt(viTri, 3)));
//        String giamGia = String.valueOf(tblSanPham.getValueAt(viTri, 4));
//        String giamGia2 = giamGia.substring(0, giamGia.indexOf(" VND"));
    }

    public void updateSP(String cn) {
        int viTri = tblSanPham.getSelectedRow();
        SanPham sp = new SanPham();

        int soLuong2 = Integer.valueOf(txtSoLuong.getText());

        int soLuongCapNhat = 0;
        if (cn.equals("Cập nhật")) {
            int soLuong = Integer.valueOf(tblSanPham.getValueAt(viTri, 3).toString());
            sp.setMaSP(tblSanPham.getValueAt(viTri, 0).toString());
        } else if (cn.equals("Xóa")) {
            int soLuong = Integer.valueOf(tblSanPham.getValueAt(viTri, 3).toString());
            sp.setMaSP(tblSanPham.getValueAt(viTri, 0).toString());
        } else {
            SanPham sanPham = (SanPham) cboSanPham.getSelectedItem();
            SanPham list = daoSP.selectById(sanPham.getMaSP());
            sp.setMaSP(String.valueOf(list.getMaSP()));
        }
        fillSanPham();

    }

    public boolean checkBoTrong() {
        int soLuong = 0;
        try {
            soLuong = Integer.valueOf(txtSoLuong.getText());
            if (soLuong > 500) {
                MsgBox.alert(this, "Số lượng phải từ 500 trở xuống!!");
                txtSoLuong.setText("0");
                return false;
            }
        } catch (NumberFormatException e) {
            MsgBox.alert(this, "Số lượng vui lòng nhập số!");
            txtSoLuong.setText("");
            return false;

        }
        if (soLuong == 0) {
            MsgBox.alert(this, "Vui lòng nhập số lượng !!");
            return false;
        }
        int viTri = tblSanPham.getSelectedRow();
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelThongTinChung = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cboMaBan = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNgayTao = new javax.swing.JTextField();
        txtTenDN = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTenNguoiTao = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtTrangThaiHD = new javax.swing.JTextField();
        panelThongTinSP = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtThanhTien = new javax.swing.JTextField();
        btnXoa = new newpackage.Button();
        btnThem = new newpackage.Button();
        btnCapNhat = new newpackage.Button();
        jLabel15 = new javax.swing.JLabel();
        cboSanPham = new javax.swing.JComboBox<>();
        panelBanHang = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        btnHuy = new newpackage.Button();
        btnThanhToan = new newpackage.Button();
        jLabel14 = new javax.swing.JLabel();
        pnlTitleBar = new javax.swing.JPanel();
        lblExit = new javax.swing.JLabel();
        lblMaxi = new javax.swing.JLabel();
        lblMini = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 2, 2, new java.awt.Color(0, 153, 255)));

        panelThongTinChung.setBackground(new java.awt.Color(255, 255, 255));
        panelThongTinChung.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)), "THÔNG TIN CHUNG", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 51, 255))); // NOI18N
        panelThongTinChung.setOpaque(false);
        panelThongTinChung.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)), "BÀN\n", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 51, 255))); // NOI18N
        jPanel2.setOpaque(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Mã Bàn:");

        cboMaBan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cboMaBan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "000003", "000002", "000001" }));
        cboMaBan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMaBanItemStateChanged(evt);
            }
        });
        cboMaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaBanActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Trạng thái:");

        cboTrangThai.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trống", "Có khách" }));
        cboTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel17.setText("<html>Chú ý: Để lưu thông tin hóa đơn vui lòng <br> chuyển trạng thái bàn từ trống sang có khách</html>");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cboMaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 5, Short.MAX_VALUE))))
            .addComponent(jLabel17)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        panelThongTinChung.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 360, 210));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)), "ĐƠN HÀNG", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 51, 255))); // NOI18N
        jPanel3.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Mã đơn :");

        txtMaHD.setEditable(false);
        txtMaHD.setBackground(new java.awt.Color(255, 255, 255));
        txtMaHD.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtMaHD.setForeground(new java.awt.Color(255, 102, 0));
        txtMaHD.setText("HD001");
        txtMaHD.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Ngày tạo :");

        txtNgayTao.setEditable(false);
        txtNgayTao.setBackground(new java.awt.Color(255, 255, 255));
        txtNgayTao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNgayTao.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        txtTenDN.setEditable(false);
        txtTenDN.setBackground(new java.awt.Color(242, 242, 242));
        txtTenDN.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTenDN.setText("admin");
        txtTenDN.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Tên ND:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Người tạo :");

        txtTenNguoiTao.setEditable(false);
        txtTenNguoiTao.setBackground(new java.awt.Color(242, 242, 242));
        txtTenNguoiTao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTenNguoiTao.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtTenNguoiTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNguoiTaoActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Trạng thái:");

        txtTrangThaiHD.setEditable(false);
        txtTrangThaiHD.setBackground(new java.awt.Color(255, 255, 255));
        txtTrangThaiHD.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTrangThaiHD.setForeground(new java.awt.Color(204, 0, 0));
        txtTrangThaiHD.setText("Chưa thanh toán");
        txtTrangThaiHD.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel16)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenNguoiTao, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addComponent(txtTenDN)
                    .addComponent(txtNgayTao)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTrangThaiHD, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTenDN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTrangThaiHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap(140, Short.MAX_VALUE))
        );

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date now = new Date();
        txtNgayTao.setText(sdf.format(now) + "");

        panelThongTinChung.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 360, 370));
        jPanel3.getAccessibleContext().setAccessibleParent(jPanel3);

        panelThongTinSP.setBackground(new java.awt.Color(255, 255, 255));
        panelThongTinSP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)), "SẢN PHẨM", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 51, 255))); // NOI18N
        panelThongTinSP.setOpaque(false);
        panelThongTinSP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Mã sản phẩm:");
        panelThongTinSP.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 120, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Thành tiền :");
        panelThongTinSP.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 150, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Số lượng:");
        panelThongTinSP.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, 80, -1));

        txtDonGia.setEditable(false);
        txtDonGia.setBackground(new java.awt.Color(242, 242, 242));
        txtDonGia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDonGia.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        panelThongTinSP.add(txtDonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 370, -1));

        txtSoLuong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtSoLuong.setText("0");
        txtSoLuong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtSoLuong.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSoLuongFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSoLuongFocusLost(evt);
            }
        });
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });
        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyReleased(evt);
            }
        });
        panelThongTinSP.add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, 270, -1));

        txtThanhTien.setEditable(false);
        txtThanhTien.setBackground(new java.awt.Color(242, 242, 242));
        txtThanhTien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtThanhTien.setText("0");
        txtThanhTien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtThanhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThanhTienActionPerformed(evt);
            }
        });
        panelThongTinSP.add(txtThanhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 150, 270, -1));

        btnXoa.setBackground(new java.awt.Color(0, 204, 51));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        panelThongTinSP.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 230, 130, -1));

        btnThem.setBackground(new java.awt.Color(255, 0, 0));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        panelThongTinSP.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 230, 130, -1));

        btnCapNhat.setBackground(new java.awt.Color(0, 102, 204));
        btnCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        panelThongTinSP.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 230, 130, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Đơn giá :");
        panelThongTinSP.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 80, -1));

        cboSanPham.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cboSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SP001" }));
        cboSanPham.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSanPhamItemStateChanged(evt);
            }
        });
        panelThongTinSP.add(cboSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 370, -1));

        panelBanHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 3, 3, 3, new java.awt.Color(123, 189, 255)));

        tblSanPham.setBackground(new java.awt.Color(123, 189, 255));
        tblSanPham.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblSanPham.setForeground(new java.awt.Color(255, 255, 255));
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Đơn giá", "Số lượng", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setFocusable(false);
        tblSanPham.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblSanPham.setRowHeight(25);
        tblSanPham.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tblSanPham.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        panelBanHang.setViewportView(tblSanPham);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel12.setText("Tổng tiền: ");

        btnHuy.setBackground(new java.awt.Color(255, 51, 51));
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("Hủy đơn");
        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnThanhToan.setBackground(new java.awt.Color(0, 0, 0));
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 204));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("QUẢN LÝ BÁN HÀNG");

        pnlTitleBar.setBackground(new java.awt.Color(81, 145, 255));
        pnlTitleBar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlTitleBarMouseDragged(evt);
            }
        });
        pnlTitleBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlTitleBarMousePressed(evt);
            }
        });

        lblExit.setBackground(new java.awt.Color(81, 145, 255));
        lblExit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblExit.setForeground(new java.awt.Color(255, 255, 255));
        lblExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/x_icon.png"))); // NOI18N
        lblExit.setOpaque(true);
        lblExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblExitMouseExited(evt);
            }
        });

        lblMaxi.setBackground(new java.awt.Color(81, 145, 255));
        lblMaxi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMaxi.setForeground(new java.awt.Color(255, 255, 255));
        lblMaxi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaxi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/square_icon.png"))); // NOI18N
        lblMaxi.setOpaque(true);
        lblMaxi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMaxiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblMaxiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblMaxiMouseExited(evt);
            }
        });

        lblMini.setBackground(new java.awt.Color(81, 145, 255));
        lblMini.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMini.setForeground(new java.awt.Color(255, 255, 255));
        lblMini.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMini.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/minus_icon.png"))); // NOI18N
        lblMini.setOpaque(true);
        lblMini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMiniMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblMiniMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblMiniMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlTitleBarLayout = new javax.swing.GroupLayout(pnlTitleBar);
        pnlTitleBar.setLayout(pnlTitleBarLayout);
        pnlTitleBarLayout.setHorizontalGroup(
            pnlTitleBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTitleBarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblMini)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMaxi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblExit))
        );
        pnlTitleBarLayout.setVerticalGroup(
            pnlTitleBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblMaxi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblMini, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        lblTongTien.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(255, 0, 0));
        lblTongTien.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelThongTinChung, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelThongTinSP, javax.swing.GroupLayout.PREFERRED_SIZE, 953, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panelBanHang))
                                .addGap(21, 21, 21))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(19, 19, 19)
                                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlTitleBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlTitleBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelThongTinSP, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(panelBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(lblTongTien))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))
                    .addComponent(panelThongTinChung, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseClicked
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_lblExitMouseClicked

    private void lblExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseEntered
        // TODO add your handling code here:
        lblExit.setBackground(Color.red);
    }//GEN-LAST:event_lblExitMouseEntered

    private void lblExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseExited
        // TODO add your handling code here:
        lblExit.setBackground(new Color(81, 145, 255));
    }//GEN-LAST:event_lblExitMouseExited

    private void lblMaxiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMaxiMouseClicked
        // TODO add your handling code here:
        Insets screenInsets = getToolkit().getScreenInsets(getGraphicsConfiguration());
        Rectangle screenSize = getGraphicsConfiguration().getBounds();
        Rectangle maxBounds = new Rectangle(screenInsets.left + screenSize.x,
                screenInsets.top + screenSize.y,
                screenSize.x + screenSize.width - screenInsets.right - screenInsets.left,
                screenSize.y + screenSize.height - screenInsets.bottom - screenInsets.top);
        super.setMaximizedBounds(maxBounds);
        if (this.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
            lblMaxi.setIcon(new ImageIcon("src\\icon\\square_icon.png"));
            this.setExtendedState(JFrame.NORMAL);
        } else {
            lblMaxi.setIcon(new ImageIcon("src\\icon\\restore_down_icon.png"));
            this.setExtendedState(MAXIMIZED_BOTH);
        }
    }//GEN-LAST:event_lblMaxiMouseClicked

    private void lblMaxiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMaxiMouseEntered
        // TODO add your handling code here:
        lblMaxi.setBackground(Color.lightGray);
    }//GEN-LAST:event_lblMaxiMouseEntered

    private void lblMaxiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMaxiMouseExited
        // TODO add your handling code here:
        lblMaxi.setBackground(new Color(81, 145, 255));
    }//GEN-LAST:event_lblMaxiMouseExited

    private void lblMiniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMiniMouseClicked
        // TODO add your handling code here:
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_lblMiniMouseClicked

    private void lblMiniMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMiniMouseEntered
        // TODO add your handling code here:
        lblMini.setBackground(Color.lightGray);
    }//GEN-LAST:event_lblMiniMouseEntered

    private void lblMiniMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMiniMouseExited
        // TODO add your handling code here:
        lblMini.setBackground(new Color(81, 145, 255));
    }//GEN-LAST:event_lblMiniMouseExited

    private void pnlTitleBarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTitleBarMouseDragged
        // TODO add your handling code here:
        if (this.getExtendedState() == JFrame.MAXIMIZED_BOTH) {

        } else {
            this.setLocation(
                    getLocation().x + evt.getX() - mpX,
                    getLocation().y + evt.getY() - mpY);
        }
    }//GEN-LAST:event_pnlTitleBarMouseDragged

    private void pnlTitleBarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTitleBarMousePressed
        // TODO add your handling code hee:
        mpX = evt.getX();
        mpY = evt.getY();
    }//GEN-LAST:event_pnlTitleBarMousePressed

    private void cboSanPhamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSanPhamItemStateChanged

        fillSanPham();
        check();
        checkSanPham();
    }//GEN-LAST:event_cboSanPhamItemStateChanged

    private void cboMaBanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMaBanItemStateChanged
        fillBan();
        fillTableDanhSachSP();
        check();
        fillComboBoxSanPham();
        checkSanPham();

    }//GEN-LAST:event_cboMaBanItemStateChanged

    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
        test();
        check();
    }//GEN-LAST:event_cboTrangThaiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (checkBoTrong()) {
            insertHDCT();
            check();
            checkSanPham();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        removeSanPham();
        check();
        checkSanPham();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        if (checkBoTrong()) {
            updateHDCT();
            check();
            checkSanPham();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void txtSoLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyReleased
        setThanhTien();
    }//GEN-LAST:event_txtSoLuongKeyReleased

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        writeFormSP();
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void txtSoLuongFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoLuongFocusLost
        setThanhTien();
    }//GEN-LAST:event_txtSoLuongFocusLost

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed

        int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn không?", "Hỏi", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_NO_OPTION) {
            inFilePDF();
            updateBan(false);
            HoaDon hd = this.readFormHD(true, false, false);
            daoHD.update(hd);
            fillComboBoxBan();
            fillTableDanhSachSP();
        } else {
            thanhToanHD();
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        huyHoaDon();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtSoLuongFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoLuongFocusGained
        if (txtSoLuong.getText().equals("0")) {
            txtSoLuong.setText("");
        }
    }//GEN-LAST:event_txtSoLuongFocusGained

    private void txtTenNguoiTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNguoiTaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNguoiTaoActionPerformed

    private void txtThanhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThanhTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThanhTienActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void cboMaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMaBanActionPerformed
    public void test() {
        if (cboTrangThai.getSelectedItem().equals("Có khách")) {
            Ban ban = (Ban) cboMaBan.getSelectedItem();
            Ban list = daoBan.selectById(String.valueOf(ban.getMaBan()));
            List<HoaDon> hd = daoHD.selectByKeyword(ban.getMaBan());
            if (hd.isEmpty()) {
                if (cboTrangThai.isEnabled() == false && list.isTrangThai()) {

                } else {
                    updateBan(true);
                    insertHD();
                }
            }

        }
    }

    public void thanhToanHD() {
        updateBan(false);
        HoaDon hd = this.readFormHD(true, false, false);
        daoHD.update(hd);
        fillComboBoxBan();
        fillTableDanhSachSP();
        MsgBox.alert(this, "Hóa đơn " + maHD + " da thanh toan!");
    }

    public void huyHoaDon() {
        String maHD = txtMaHD.getText();
        boolean chon = MsgBox.confirm(this, "Bạn chắc chắn muốn hủy hóa đơn " + maHD + " !");
        if (chon) {
            updateBan(false);
            HoaDon hd = this.readFormHD(false, true, false);
            daoHD.update(hd);
            fillComboBoxBan();
            fillTableDanhSachSP();
            MsgBox.alert(this, "Đã hủy hóa đơn " + maHD + " !!");

        }
    }

    public static HashMap<String, String> hm_tien = new HashMap<String, String>() {
        {
            put("0", "không");
            put("1", "một");
            put("2", "hai");
            put("3", "ba");
            put("4", "bốn");
            put("5", "năm");
            put("6", "sáu");
            put("7", "bảy");
            put("8", "tám");
            put("9", "chín");
        }
    };
    public static HashMap<String, String> hm_hanh = new HashMap<String, String>() {
        {
            put("1", "VND");
            put("2", "mươi");
            put("3", "trăm");
            put("4", "nghìn");
            put("5", "mươi");
            put("6", "trăm");
            put("7", "triệu");
            put("8", "mươi");
            put("9", "trăm");
            put("10", "tỷ");
            put("11", "mươi");
            put("12", "trăm");
            put("13", "nghìn");
            put("14", "mươi");
            put("15", "trăm");

        }
    };

    public static String ChuyenSangChu(String x) {
        String kq = "";
        x = x.replace(".", "");
        String arr_temp[] = x.split(",");
        String m = arr_temp[0];
        int dem = m.length();
        String dau = "";
        int flag10 = 1;
        while (!m.equals("")) {
            if (m.length() <= 3 && m.length() > 1 && Long.parseLong(m) == 0) {

            } else {
                dau = m.substring(0, 1);
                if (dem % 3 == 1 && m.startsWith("1") && flag10 == 0) {
                    System.out.println(dem % 3);
                    kq += "mốt ";
                    flag10 = 0;
                } else if (dem % 3 == 1 && m.startsWith("5") && flag10 == 0) {
                    kq += "lăm ";
                    flag10 = 0;
                } else if (dem % 3 == 2 && m.startsWith("1")) {
                    kq += "mười ";
                    flag10 = 1;
                } else if (dem % 3 == 2 && m.startsWith("0") && m.length() >= 2 && !m.substring(1, 2).equals("0")) {
                    //System.out.println("a  "+m.substring(1, 2));
                    kq += "lẻ ";
                    flag10 = 1;
                } else {
                    if (!m.startsWith("0")) {
                        kq += hm_tien.get(dau) + " ";
                        flag10 = 0;
                    }
                }
                if (dem % 3 != 1 && m.startsWith("0") && m.length() > 1) {
                } else {
                    if (dem % 3 == 2 && (m.startsWith("1") || m.startsWith("0"))) {//mười
                    } else {
                        kq += hm_hanh.get(dem + "") + " ";
                    }
                }
            }
            m = m.substring(1);
            dem = m.length();
        }
        kq = kq.substring(0, kq.length() - 1);
        return kq;
    }

    public void inFilePDF() {
        Document document;
        try {
            try {
                String pathStr = "E:\\DuAn1_Nhom2\\Code_DuAn1\\SourceCode\\Coffee_DuAn1_Nhom2\\src\\hoaDon.pdf";
                String font1 = "E:\\DuAn1_Nhom2\\Code_DuAn1\\SourceCode\\Coffee_DuAn1_Nhom2\\src\\unicode.ttf";
                String imgPath = "E:\\DuAn1_Nhom2\\Code_DuAn1\\SourceCode\\Coffee_DuAn1_Nhom2\\src\\icon\\logocafe.jpg";
                PdfFont fontTitle = PdfFontFactory.createFont(font1, com.itextpdf.text.pdf.BaseFont.IDENTITY_H);
                Date date = new Date();
                Calendar calendar = GregorianCalendar.getInstance();
                calendar.setTime(date);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int min = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                String timeNow = hour + ":" + min + ":" + second + "\t" + day + "/" + month + "/" + year;

                PdfWriter pdfWriter = new PdfWriter(pathStr);

                PdfDocument pdfDocument = new PdfDocument(pdfWriter);
                pdfDocument.addNewPage();

                ImageData imageData = ImageDataFactory.create(imgPath);
                Image imageLogo = new Image(imageData);
                imageLogo.setHeight(50f).setWidth(60f);

                document = new Document(pdfDocument);

                float columnWith[] = {80, 1000};
                Table tableHeader = new Table(columnWith).setBorder(Border.NO_BORDER).setHeight(60f).setAutoLayout();
                tableHeader.setBackgroundColor(new DeviceRgb(1, 181, 204));
                tableHeader.addCell(new Cell().add(imageLogo).setBorder(Border.NO_BORDER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE).setMarginTop(5f));
                tableHeader.addCell(new Cell().add("Hóa đơn bán hàng")
                        .setFontColor(new DeviceRgb(255, 255, 255)).setFontSize(17f)
                        .setBold()
                        .setMarginLeft(15f)
                        .setFont(fontTitle)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setBorder(Border.NO_BORDER));

                Paragraph infoCostumer = new Paragraph("Thông tin hóa đơn");
                infoCostumer.setFont(fontTitle).setBold().setMarginTop(15f);

                Paragraph nameCos = new Paragraph("Mã Hóa Đơn:\t" + txtMaHD.getText());
                nameCos.setFont(fontTitle).setFontSize(12f);

                Paragraph purchaseTime = new Paragraph("Ngày Tạo:\t" + txtNgayTao.getText());
                purchaseTime.setFont(fontTitle).setFontSize(12f);

                Paragraph address = new Paragraph("Trạng Thái: Đã thanh toán");
                address.setFont(fontTitle).setFontSize(12f);

                document.add(tableHeader);
                document.add(infoCostumer);
                document.add(nameCos);
                document.add(purchaseTime);
                document.add(address);

                Paragraph listProducts = new Paragraph("Sản phẩm");
                listProducts.setFont(fontTitle).setBold().setMarginTop(25f).setMarginBottom(-10);

                document.add(listProducts);

                float columnWithTableContent[] = {550, 150, 150, 150};
                Table tableContent = new Table(columnWithTableContent)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setBorder(Border.NO_BORDER).setMarginTop(15f);

                tableContent.addCell(new Cell().add("Tên sản phẩm")
                        .setBackgroundColor(new DeviceRgb(1, 181, 204))
                        .setFont(fontTitle).setBold().setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                        .setFontSize(9)
                        .setBorder(Border.NO_BORDER));
                tableContent.addCell(new Cell().add("Số lượng")
                        .setBackgroundColor(new DeviceRgb(1, 181, 204)).setFont(fontTitle)
                        .setBold().setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                        .setFontSize(9)
                        .setBorder(Border.NO_BORDER));
                tableContent.addCell(new Cell().add("Giá bán")
                        .setFontSize(9)
                        .setBackgroundColor(new DeviceRgb(1, 181, 204)).setFont(fontTitle)
                        .setBold().setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                        .setBorder(Border.NO_BORDER));
                tableContent.addCell(new Cell().add("Thành tiền")
                        .setFontSize(9)
                        .setBackgroundColor(new DeviceRgb(1, 181, 204)).setFont(fontTitle)
                        .setBold().setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                        .setBorder(Border.NO_BORDER));

                for (int i = 0; i < tblSanPham.getRowCount(); i++) {

                    tableContent.addCell(new Cell().add(tblSanPham.getValueAt(i, 1).toString()).setFont(fontTitle).setBorder(Border.NO_BORDER).setFontSize(9));
                    tableContent.addCell(new Cell().add(tblSanPham.getValueAt(i, 3).toString()).setFont(fontTitle).setBorder(Border.NO_BORDER).setFontSize(9));
                    tableContent.addCell(new Cell().add(tblSanPham.getValueAt(i, 2).toString()).setFont(fontTitle).setBorder(Border.NO_BORDER).setFontSize(9));
                    tableContent.addCell(new Cell().add(tblSanPham.getValueAt(i, 4).toString()).setFont(fontTitle).setBorder(Border.NO_BORDER).setFontSize(9));

                }
                document.add(tableContent);

                float coulumnWithFotter[] = {100, 300, 900, 250, 150};
                Table tableFotter = new Table(coulumnWithFotter)
                        .setTextAlignment(TextAlignment.LEFT)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setBorder(Border.NO_BORDER);
                tableFotter.addCell(new Cell().setBackgroundColor(new DeviceRgb(1, 181, 204)).setBorder(Border.NO_BORDER));
                tableFotter.addCell(new Cell().setBackgroundColor(new DeviceRgb(1, 181, 204)).setBorder(Border.NO_BORDER));
                tableFotter.addCell(new Cell().setBackgroundColor(new DeviceRgb(1, 181, 204)).setBorder(Border.NO_BORDER));
                tableFotter.addCell(new Cell().setBackgroundColor(new DeviceRgb(1, 181, 204)).setBorder(Border.NO_BORDER));
                tableFotter.addCell(new Cell().setBackgroundColor(new DeviceRgb(1, 181, 204)).setBorder(Border.NO_BORDER));
//                tableFotter.addCell(new Cell().setBackgroundColor(new DeviceRgb(1, 181, 204)).setBorder(Border.NO_BORDER));
                document.add(tableFotter);

                Paragraph cachDong = new Paragraph("\t");
                cachDong.setFont(fontTitle).setFontSize(12f);

                Paragraph tongTien = new Paragraph("Tổng tiền: " + lblTongTien.getText());
                tongTien.setFont(fontTitle).setFontSize(12f);

                document.add(tongTien);
                document.add(cachDong);

                JOptionPane.showMessageDialog(rootPane, "Hóa đơn đã được in");
                document.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace(System.out);
                e.getMessage();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            e.getMessage();
        }
    }

    public static String currencyFormat(String curr) {
        try {
            double vaelue = Double.parseDouble(curr);
            String pattern = "###,###";
            DecimalFormat myFormatter = new DecimalFormat(pattern);
            String output = myFormatter.format(vaelue);
            return output;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "";
    }

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
            java.util.logging.Logger.getLogger(BanHangJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BanHangJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BanHangJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanHangJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BanHangJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private newpackage.Button btnCapNhat;
    private newpackage.Button btnHuy;
    private newpackage.Button btnThanhToan;
    private newpackage.Button btnThem;
    private newpackage.Button btnXoa;
    private javax.swing.JComboBox<String> cboMaBan;
    private javax.swing.JComboBox<String> cboSanPham;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblMaxi;
    private javax.swing.JLabel lblMini;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JScrollPane panelBanHang;
    private javax.swing.JPanel panelThongTinChung;
    private javax.swing.JPanel panelThongTinSP;
    private javax.swing.JPanel pnlTitleBar;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenDN;
    private javax.swing.JTextField txtTenNguoiTao;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTrangThaiHD;
    // End of variables declaration//GEN-END:variables
}
