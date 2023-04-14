/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package model;

import KetNoi.KetNoi;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import com.sun.webkit.BackForwardList;
import com.toedter.calendar.*;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import dao.NguoiDungDAO;
import dao.NhanVienDAO;
import entity.NguoiDung;
import entity.NhanVien;
import helper.Auth;
import helper.MsgBox;
import helper.XDate;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.plaf.basic.BasicPanelUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import dao.*;
import entity.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Asus
 */
public class NhapHangModel extends javax.swing.JFrame {

    public NhapHangModel() {
        initComponents();
        setLocationRelativeTo(null);
        txtNgayNHap.setText(sdf.format(date) + "");
        fillTable();
        viTri = -1;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    NhapHangDao nhapHangDao = new NhapHangDao();
    List<NhapHang> list = nhapHangDao.selectAll();
    Date date = new Date();
    int viTri;

    public void eventButtonEnable(JButton button) {
        button.setEnabled(false);
        button.setBackground(Color.GRAY);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel10 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tabNhapHang = new javax.swing.JTabbedPane();
        pnlDiaChi = new javax.swing.JPanel();
        lblMaNV = new javax.swing.JLabel();
        lblTenNv = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        btnXoa = new newpackage.Button();
        btnSua = new newpackage.Button();
        btnThem = new newpackage.Button();
        lblNgaySinh = new javax.swing.JLabel();
        btnLamMoi = new newpackage.Button();
        txtTongTien = new javax.swing.JTextField();
        txtNgayNHap = new javax.swing.JTextField();
        lblTenNv1 = new javax.swing.JLabel();
        cboLoai = new javax.swing.JComboBox<>();
        lblTenNv2 = new javax.swing.JLabel();
        cboSoLieu = new javax.swing.JComboBox<>();
        lblGioiTinh1 = new javax.swing.JLabel();
        txtMaNhap = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtTenHang = new javax.swing.JTextField();
        pnlDanhSach = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhapHang = new javax.swing.JTable();
        pnlTimKiem = new javax.swing.JPanel();
        btnTimKiem = new newpackage.Button();
        txtTimKiem = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 2, 2, new java.awt.Color(81, 145, 255)));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("QUẢN LÝ NHẬP HÀNG");

        tabNhapHang.setBackground(new java.awt.Color(255, 255, 255));
        tabNhapHang.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabNhapHang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tabNhapHang.setOpaque(true);

        pnlDiaChi.setBackground(new java.awt.Color(255, 255, 255));

        lblMaNV.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMaNV.setText("MÃ NHẬP HÀNG:");

        lblTenNv.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTenNv.setText("TÊN HÀNG :");

        lblGioiTinh.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblGioiTinh.setText("NGÀY NHẬP :");

        btnXoa.setBackground(new java.awt.Color(255, 0, 0));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(81, 145, 255));
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");
        btnSua.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(81, 145, 255));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        lblNgaySinh.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNgaySinh.setText("TỔNG TIỀN:");

        btnLamMoi.setBackground(new java.awt.Color(0, 204, 102));
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        txtTongTien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtNgayNHap.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lblTenNv1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTenNv1.setText("LOẠI:");

        cboLoai.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cboLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cafe hạt", "Hoa quả", "Siro", "Kem", "Sản phẩm khác" }));

        lblTenNv2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTenNv2.setText("SỐ LIỆU:");

        cboSoLieu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cboSoLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cân", "Chai", "Chiếc", "Hộp" }));

        lblGioiTinh1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblGioiTinh1.setText("SỐ LƯỢNG:");

        txtMaNhap.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtSoLuong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtTenHang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout pnlDiaChiLayout = new javax.swing.GroupLayout(pnlDiaChi);
        pnlDiaChi.setLayout(pnlDiaChiLayout);
        pnlDiaChiLayout.setHorizontalGroup(
            pnlDiaChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDiaChiLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlDiaChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDiaChiLayout.createSequentialGroup()
                        .addGroup(pnlDiaChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTenNv)
                            .addComponent(lblMaNV))
                        .addContainerGap(514, Short.MAX_VALUE))
                    .addGroup(pnlDiaChiLayout.createSequentialGroup()
                        .addGroup(pnlDiaChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlDiaChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtTenHang, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlDiaChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboSoLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTenNv2)
                                    .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlDiaChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(pnlDiaChiLayout.createSequentialGroup()
                                            .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlDiaChiLayout.createSequentialGroup()
                                            .addGroup(pnlDiaChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblTenNv1)
                                                .addComponent(lblNgaySinh)
                                                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtNgayNHap, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblGioiTinh)
                                                .addComponent(lblGioiTinh1))
                                            .addGap(195, 195, 195))
                                        .addComponent(txtMaNhap, javax.swing.GroupLayout.Alignment.LEADING)))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlDiaChiLayout.setVerticalGroup(
            pnlDiaChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDiaChiLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblMaNV)
                .addGap(16, 16, 16)
                .addComponent(txtMaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTenNv)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTenHang, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(lblTenNv1)
                .addGap(18, 18, 18)
                .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTenNv2)
                .addGap(18, 18, 18)
                .addComponent(cboSoLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblGioiTinh1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGioiTinh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNgayNHap, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNgaySinh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(pnlDiaChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(95, 95, 95))
        );

        tabNhapHang.addTab("Cập Nhật", pnlDiaChi);

        pnlDanhSach.setBackground(new java.awt.Color(255, 255, 255));

        tblNhapHang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblNhapHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhập hàng", "Tên hàng", "Loại", "Số liệu", "Số lượng", "Ngày nhập", "Tiền nhập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhapHang.setRowHeight(25);
        tblNhapHang.setRowMargin(0);
        tblNhapHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhapHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhapHang);

        pnlTimKiem.setBackground(new java.awt.Color(255, 255, 255));
        pnlTimKiem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search_32_icon.png"))); // NOI18N
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        txtTimKiem.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTimKiem.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0));
        txtTimKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimKiemFocusLost(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemLayout = new javax.swing.GroupLayout(pnlTimKiem);
        pnlTimKiem.setLayout(pnlTimKiemLayout);
        pnlTimKiemLayout.setHorizontalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txtTimKiem)
                .addGap(0, 0, 0)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        pnlTimKiemLayout.setVerticalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout pnlDanhSachLayout = new javax.swing.GroupLayout(pnlDanhSach);
        pnlDanhSach.setLayout(pnlDanhSachLayout);
        pnlDanhSachLayout.setHorizontalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDanhSachLayout.setVerticalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabNhapHang.addTab("Danh Sách", pnlDanhSach);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabNhapHang, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed

        if (check()) {
            update();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

//        insert();
        if (checkTrung() && check()) {
            insert();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed

        clearForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tblNhapHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhapHangMouseClicked

        if (evt.getClickCount() == 2) {
            writeFrom();
        }
    }//GEN-LAST:event_tblNhapHangMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed

        fillTable();
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void txtTimKiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusLost

    }//GEN-LAST:event_txtTimKiemFocusLost

    private void txtTimKiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusGained

    }//GEN-LAST:event_txtTimKiemFocusGained

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
            java.util.logging.Logger.getLogger(NhapHangModel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhapHangModel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhapHangModel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhapHangModel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhapHangModel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private newpackage.Button btnLamMoi;
    private newpackage.Button btnSua;
    private newpackage.Button btnThem;
    private newpackage.Button btnTimKiem;
    private newpackage.Button btnXoa;
    private javax.swing.JComboBox<String> cboLoai;
    private javax.swing.JComboBox<String> cboSoLieu;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblGioiTinh1;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblTenNv;
    private javax.swing.JLabel lblTenNv1;
    private javax.swing.JLabel lblTenNv2;
    private javax.swing.JPanel pnlDanhSach;
    private javax.swing.JPanel pnlDiaChi;
    private javax.swing.JPanel pnlTimKiem;
    private javax.swing.JTabbedPane tabNhapHang;
    private javax.swing.JTable tblNhapHang;
    private javax.swing.JTextField txtMaNhap;
    private javax.swing.JTextField txtNgayNHap;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenHang;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables

    private void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblNhapHang.getModel();
        model.setRowCount(0);

        String maHang = txtTimKiem.getText();
        list = nhapHangDao.selectByKeyword(maHang);

        for (int i = 0; i < list.size(); i++) {
            Object[] rows = new Object[]{list.get(i).getMaNhap(),
                list.get(i).getTenSPNhap(), list.get(i).getLoaiSP(),
                list.get(i).getSoLieu(), list.get(i).getSoLuong(),
                list.get(i).getNgayNhap(), list.get(i).getTienNhap()
            };
            model.addRow(rows);
        }
    }

    public NhapHang readForm() {
        NhapHang nhapHang = new NhapHang();

        nhapHang.setMaNhap(txtMaNhap.getText());
        nhapHang.setTenSPNhap(txtTenHang.getText());
        nhapHang.setLoaiSP(cboLoai.getSelectedItem().toString());
        nhapHang.setSoLieu(cboSoLieu.getSelectedItem().toString());
        nhapHang.setSoLuong(Float.parseFloat(txtSoLuong.getText()));

        String ngay = txtNgayNHap.getText();
        Date ngay2 = XDate.toDate(ngay, "yyyy-MM-dd");
        nhapHang.setNgayNhap(XDate.toString(ngay2, "yyyy-MM-dd"));

        nhapHang.setTienNhap(Float.parseFloat(txtTongTien.getText()));

        return nhapHang;
    }

    public void eventButton(JButton button, Color color) {
        button.setEnabled(true);
        button.setBackground(color);
    }

    public void getAdmin(int i) {

        if (tblNhapHang.getValueAt(i, 0).equals("admin")) {
            eventButtonEnable(btnXoa);
            btnXoa.setEffectColor(Color.gray);
        } else {
            eventButton(btnXoa, new Color(255, 0, 0));
        }
    }

    public void writeFrom() {
        int i = tblNhapHang.getSelectedRow();
        getAdmin(i);
        eventButtonEnable(btnThem);
        btnThem.setEffectColor(Color.gray);
        eventButton(btnSua, new Color(81, 145, 255));
        txtMaNhap.setEnabled(false);
        tabNhapHang.setSelectedIndex(0);
        int index = tblNhapHang.getSelectedRow();

        txtMaNhap.setText(tblNhapHang.getValueAt(index, 0).toString());
        txtTenHang.setText(tblNhapHang.getValueAt(index, 1).toString());
        cboLoai.setSelectedItem(tblNhapHang.getValueAt(index, 2).toString());
        cboSoLieu.setSelectedItem(tblNhapHang.getValueAt(index, 3).toString());
        txtSoLuong.setText(tblNhapHang.getValueAt(index, 4).toString());
        txtNgayNHap.setText(tblNhapHang.getValueAt(index, 5).toString());
        txtTongTien.setText(tblNhapHang.getValueAt(index, 6).toString());

    }

    public void clearForm() {
        int index = tblNhapHang.getSelectedRow();
        eventButton(btnThem, new Color(81, 145, 255));
        eventButtonEnable(btnSua);
        btnSua.setEffectColor(Color.gray);
        eventButtonEnable(btnXoa);
        btnXoa.setEffectColor(Color.gray);
        txtMaNhap.setEnabled(true);
        txtMaNhap.setText("");
        txtTenHang.setText("");
        cboLoai.setSelectedItem("");
        cboSoLieu.setSelectedItem("");
        txtSoLuong.setText("");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        txtNgayNHap.setText(sdf.format(now) + "");
        txtTongTien.setText("");
    }

    public void insert() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date now = new Date();
        txtNgayNHap.setText(sdf.format(now) + "");

        NhapHang nhapHang = this.readForm();
        nhapHangDao.insert(nhapHang);
        fillTable();
        clearForm();
        MsgBox.alert(this, "Thêm thành công !?");
    }

    public void delete() {
        int viTri = tblNhapHang.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblNhapHang.getModel();
        String maNhapHang = tblNhapHang.getValueAt(viTri, 0).toString();
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (hoi == JOptionPane.YES_NO_OPTION) {
            nhapHangDao.delete(maNhapHang);
            clearForm();
            MsgBox.alert(this, "Xoa thanh cong");
        } else {
            MsgBox.alert(this, "Da thoi xoa");
        }
    }

    public void update() {
        NhapHang nhapHang = this.readForm();
        nhapHangDao.update(nhapHang);
        fillTable();
        clearForm();
        MsgBox.alert(this, "Update thanh cong");
    }

    public boolean checkTrung() {
        String maNhap = txtMaNhap.getText();
        List<NhapHang> list = nhapHangDao.selectByKeyword(maNhap);
        String loi = "";
        for (int i = 0; list.size() > i; i++) {
            if (maNhap.equalsIgnoreCase(list.get(i).getMaNhap())) {
                loi += "Trùng mã nhập hàng";
            }
        }
        if (loi.length() != 0) {
            MsgBox.alert(this, loi);
            return false;
        }
        return true;
    }

    public boolean check() {
        String maNhap = txtMaNhap.getText();
        String tenHang = txtTenHang.getText();
        String soLuong = txtSoLuong.getText();
        String ngayNhap = txtNgayNHap.getText();
        String tienNhap = txtTongTien.getText();
        String loi = "";
        if (maNhap.isEmpty() || tenHang.isEmpty() || soLuong.isEmpty() || ngayNhap.isEmpty() || tienNhap.isEmpty()) {
            loi += "Không được bỏ trống \n";
        }

        if (loi.length() > 0) {
            MsgBox.alert(this, loi);
            return false;
        }

        return true;
    }
}
