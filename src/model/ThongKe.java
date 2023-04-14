package model;

import dao.HoaDonDAO;
import dao.LoaiSanPhamDAO;
import dao.ThongKeDAO;
import entity.LoaiSanPham;
import java.awt.*;
import java.util.List;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.plaf.synth.SynthTableHeaderUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import entity.*;
import dao.*;
import javax.swing.JOptionPane;

public class ThongKe extends javax.swing.JFrame {

    int mpX, mpY;
    ThongKeDAO dao = new ThongKeDAO();
    LoaiSanPhamDAO daoLoai = new LoaiSanPhamDAO();
    HoaDonDAO daoHD = new HoaDonDAO();
    NhapHangDao nhapHangDao = new NhapHangDao();

    public ThongKe() {
        initComponents();
        init();
        nameCollumm();
    }

    public void nameCollumm() {
        Font HeaderFont = new Font("SansSerif", Font.PLAIN, 18);
        JTableHeader tableHeader = tblLuongNhanVien.getTableHeader();
        JTableHeader tableHeaderSP = tblSanPham.getTableHeader();
        JTableHeader tableHeaderDT = tblDoanhThu.getTableHeader();
        JTableHeader tableHeaderLN = tblLoiNhuan.getTableHeader();
        JTableHeader tableHeaderNH = tblNhapHang.getTableHeader();

        tableHeader.setUI(new SynthTableHeaderUI());
        tableHeader.setOpaque(false);
        tableHeader.setBackground(new Color(81, 145, 255));
        tableHeader.setForeground(Color.white);
        tableHeader.setFont(HeaderFont);
        tblLuongNhanVien.setRowHeight(25);

        tableHeaderSP.setUI(new SynthTableHeaderUI());
        tableHeaderSP.setOpaque(false);
        tableHeaderSP.setBackground(new Color(81, 145, 255));
        tableHeaderSP.setForeground(Color.white);
        tableHeaderSP.setFont(HeaderFont);
        tblSanPham.setRowHeight(25);

        tableHeaderDT.setUI(new SynthTableHeaderUI());
        tableHeaderDT.setOpaque(false);
        tableHeaderDT.setBackground(new Color(81, 145, 255));
        tableHeaderDT.setForeground(Color.white);
        tableHeaderDT.setFont(HeaderFont);
        tblDoanhThu.setRowHeight(25);

        tableHeaderNH.setUI(new SynthTableHeaderUI());
        tableHeaderNH.setOpaque(false);
        tableHeaderNH.setBackground(new Color(81, 145, 255));
        tableHeaderNH.setForeground(Color.white);
        tableHeaderNH.setFont(HeaderFont);
        tblNhapHang.setRowHeight(25);

        tableHeaderLN.setUI(new SynthTableHeaderUI());
        tableHeaderLN.setOpaque(false);
        tableHeaderLN.setBackground(new Color(81, 145, 255));
        tableHeaderLN.setForeground(Color.white);
        tableHeaderLN.setFont(HeaderFont);
        tblLoiNhuan.setRowHeight(25);
    }

    public void init() {
        fillTableLuongNV();
        fillComboBoxLoai();
        fillTableSanPham();
        fillComboBoxNam();
        fillTableDoanhThu();
        fillTableNhapHang();
        filltableLoiNhuan();
    }

    public void selectTab(int index) {
        tabThongKe.setSelectedIndex(index);
    }

    public void fillTableLuongNV() {
        DefaultTableModel model = (DefaultTableModel) tblLuongNhanVien.getModel();
        model.setRowCount(0);

        List<Object[]> list = dao.getLuongNV();
        for (Object[] rows : list) {
            String tongLuong = getNumLuong(rows[2].toString());
            Object[] row = new Object[]{rows[0], rows[1], tongLuong};
            model.addRow(row);
        }
    }

    public void fillComboBoxLoai() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoai.getModel();
        model.removeAllElements();
        List<LoaiSanPham> list = daoLoai.selectAll();
        if (list != null) {
            for (LoaiSanPham loai : list) {
                model.addElement(loai);
            }
        }
        cboLoai.setSelectedIndex(0);
    }

    public void fillComboBoxNhapHang() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNhapHang.getModel();
        model.removeAllElements();
        List<NhapHang> list = nhapHangDao.selectAll();
        if (list != null) {
            for (NhapHang loai : list) {
                model.addElement(loai);
            }
        }
        cboNhapHang.setSelectedIndex(0);
    }

    public void fillTableSanPham() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        LoaiSanPham lsp = (LoaiSanPham) cboLoai.getSelectedItem();

        if (cboLoai.getSelectedItem() == null) {

        } else {
            List<Object[]> list = dao.getSanPham(lsp.getMaLoai());
            for (Object[] row : list) {
                model.addRow(new Object[]{row[0], row[1], row[2]});
            }
        }
    }

    public void fillTableNhapHang() {
        DefaultTableModel model = (DefaultTableModel) tblNhapHang.getModel();
        model.setRowCount(0);
        if (cboNhapHang.getSelectedItem() == null) {

        } else {
            String LoaiSP = (String) cboNhapHang.getSelectedItem();
            List<Object[]> list = dao.getNhapHang(LoaiSP);
            for (Object[] rows : list) {
                String tienNhap = getNhapHang(rows[4].toString());
                model.addRow(new Object[]{rows[0], rows[1], rows[2], rows[3], tienNhap});
                String tongTien = getNhapHang(rows[5].toString());
                lblTongTien.setText(String.valueOf(tongTien));
            }
        }
    }

    public void fillComboBoxNam() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNam.getModel();
        model.removeAllElements();
        List<Integer> list = daoHD.selectYears();
        if (list.isEmpty()) {

        } else {
            for (Integer years : list) {
                model.addElement(years);
            }
        }
    }

    public void fillTableDoanhThu() {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThu.getModel();
        model.setRowCount(0);

        if (cboNam.getSelectedItem() == null) {

        } else {
            int nam = (int) cboNam.getSelectedItem();
            List<Object[]> list = dao.getDoanhThu(nam);
            for (Object[] row : list) {
                String tongTien = getNumDoanhThu(row[2].toString());
                model.addRow(new Object[]{row[0], row[1], tongTien});
            }
        }
    }

    public void filltableLoiNhuan() {
        DefaultTableModel model = (DefaultTableModel) tblLoiNhuan.getModel();
        model.setRowCount(0);

        List<Object[]> list = dao.getLoiNhuan();
        for (Object[] rows : list) {
            String tongTienBan = getNumLoiNhuan(rows[0].toString());
            String tongTienNhap = getNumLoiNhuan(rows[1].toString());
            String loiNhuan = getNumLoiNhuan(rows[2].toString());
            model.addRow(new Object[]{tongTienBan, tongTienNhap, loiNhuan});
        }
    }

    public String getNumLoiNhuan(String num) {
        return num.substring(0, num.indexOf(".")) + " VND";
    }

    public String getNumDoanhThu(String num) {
        return num.substring(0, num.indexOf(".")) + " VND";
    }

    public String getNumLuong(String num) {
        return num.substring(0, num.indexOf(".")) + " VND";
    }

    public String getNhapHang(String num) {
        return num.substring(0, num.indexOf(".")) + " VND";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlTitleBar = new javax.swing.JPanel();
        lblExit = new javax.swing.JLabel();
        lblMini = new javax.swing.JLabel();
        tabThongKe = new javax.swing.JTabbedPane();
        panelNhanVien = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblLuongNhanVien = new javax.swing.JTable();
        panelSanPham = new javax.swing.JPanel();
        cboLoai = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNhapHang = new javax.swing.JTable();
        cboNhapHang = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        panelDoanhThu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboNam = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLoiNhuan = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 2, 2, new java.awt.Color(81, 145, 225)));

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
                .addComponent(lblExit))
        );
        pnlTitleBarLayout.setVerticalGroup(
            pnlTitleBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblMini, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabThongKe.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabThongKe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        panelNhanVien.setBackground(new java.awt.Color(255, 255, 255));

        tblLuongNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tên Nhân Viên", "Tổng số ca làm", "Tổng lương"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tblLuongNhanVien);

        javax.swing.GroupLayout panelNhanVienLayout = new javax.swing.GroupLayout(panelNhanVien);
        panelNhanVien.setLayout(panelNhanVienLayout);
        panelNhanVienLayout.setHorizontalGroup(
            panelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
        );
        panelNhanVienLayout.setVerticalGroup(
            panelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNhanVienLayout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tabThongKe.addTab("LƯƠNG NHÂN VIÊN", panelNhanVien);

        panelSanPham.setBackground(new java.awt.Color(255, 255, 255));

        cboLoai.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cboLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cà phê đen" }));
        cboLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Loại sản phẩm:");

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Số lượng bán", "Ngày nhập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblSanPham);

        javax.swing.GroupLayout panelSanPhamLayout = new javax.swing.GroupLayout(panelSanPham);
        panelSanPham.setLayout(panelSanPhamLayout);
        panelSanPhamLayout.setHorizontalGroup(
            panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSanPhamLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(344, Short.MAX_VALUE))
            .addComponent(jScrollPane4)
        );
        panelSanPhamLayout.setVerticalGroup(
            panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSanPhamLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );

        tabThongKe.addTab("SẢN PHẨM", panelSanPham);

        tblNhapHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Số liệu", "Số lượng", "Ngày nhập", "Tiền nhập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblNhapHang);

        cboNhapHang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cboNhapHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cafe hạt", "Hoa quả", "Siro", "Kem", "Sản phẩm khác" }));
        cboNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNhapHangActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Loại hàng:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Tổng tiền nhập: ");

        lblTongTien.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        lblTongTien.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblTongTien))
                .addGap(38, 38, 38))
        );

        tabThongKe.addTab("NHẬP HÀNG", jPanel2);

        panelDoanhThu.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Doanh thu theo năm: ");

        cboNam.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cboNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Năm" }));
        cboNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamActionPerformed(evt);
            }
        });

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tháng", "Số sản phẩm đã bán", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblDoanhThu);

        javax.swing.GroupLayout panelDoanhThuLayout = new javax.swing.GroupLayout(panelDoanhThu);
        panelDoanhThu.setLayout(panelDoanhThuLayout);
        panelDoanhThuLayout.setHorizontalGroup(
            panelDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(432, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        panelDoanhThuLayout.setVerticalGroup(
            panelDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDoanhThuLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );

        tabThongKe.addTab("DOANH THU", panelDoanhThu);

        tblLoiNhuan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tổng tiền bán", "Tổng tiền nhập", "Lợi nhuận"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblLoiNhuan);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );

        tabThongKe.addTab("LỢI NHUẬN", jPanel3);

        jLabel5.setBackground(new java.awt.Color(0, 102, 204));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 204));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("THỐNG KÊ TỔNG HỢP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabThongKe)
            .addComponent(pnlTitleBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(pnlTitleBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(tabThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void cboLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiActionPerformed
        fillTableSanPham();
    }//GEN-LAST:event_cboLoaiActionPerformed

    private void cboNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamActionPerformed
        fillTableDoanhThu();
    }//GEN-LAST:event_cboNamActionPerformed

    private void cboNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNhapHangActionPerformed
        // TODO add your handling code here:
        fillTableNhapHang();
    }//GEN-LAST:event_cboNhapHangActionPerformed

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
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboLoai;
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JComboBox<String> cboNhapHang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblMini;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JPanel panelDoanhThu;
    private javax.swing.JPanel panelNhanVien;
    private javax.swing.JPanel panelSanPham;
    private javax.swing.JPanel pnlTitleBar;
    private javax.swing.JTabbedPane tabThongKe;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTable tblLoiNhuan;
    private javax.swing.JTable tblLuongNhanVien;
    private javax.swing.JTable tblNhapHang;
    private javax.swing.JTable tblSanPham;
    // End of variables declaration//GEN-END:variables
}
