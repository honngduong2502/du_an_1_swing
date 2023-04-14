package model;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
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
import dao.HoaDonCTDAO;
import dao.HoaDonDAO;
import entity.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.io.FileOutputStream;
import java.util.Date;

import javax.swing.JTable;

import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import helper.MsgBox;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import static model.BanHangJFrame.ChuyenSangChu;

/**
 *
 * @author Asus
 */
public class InHoaDon extends javax.swing.JFrame {

    Connection con;
    int index;

    HoaDonDAO hoaDon = new HoaDonDAO();
    List<HoaDon> list = hoaDon.selectAll();
    HoaDonCTDAO hoaDonCT = new HoaDonCTDAO();
    List<HoaDonCT> listCT = hoaDonCT.selectAll();
    HoaDonCTDAO daoHDCT = new HoaDonCTDAO();

    DefaultComboBoxModel<HoaDon> model_conbo = new DefaultComboBoxModel();

    public InHoaDon(String maHD, String TrangThai, String NgayTao) {
        initComponents();
        setLocationRelativeTo(null);
        txtMaHD.setText(maHD);
        txtNgayTao.setText(NgayTao);
        txtTrangThai.setText(TrangThai);

        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        List<Object[]> list = daoHDCT.selectHDCT(maHD);
        for (Object[] rowData : list) {
            String donGia = getNum(rowData[2].toString());
            String thanhTien = getNum(rowData[5].toString());
            Object[] data = new Object[]{rowData[0],
                rowData[1], donGia, rowData[3], thanhTien
            };
            model.addRow(data);
        }
        setTongTien();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void remove() {
        String maHoaDon1 = txtMaHD.getText();
        hoaDonCT.delete2(maHoaDon1);
        boolean chon = MsgBox.confirm(this, "Bạn có chắc chắn xóa hóa đơn này làm này");
        if (chon) {
            String maHoaDon = txtMaHD.getText();
            hoaDon.delete(maHoaDon);
            MsgBox.alert(this, "Đã xóa thành công");
            this.dispose();
            HoaDonJFrame hoaDonForm = new HoaDonJFrame();
            hoaDonForm.fillTable();
            hoaDonForm.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        btnInHD = new javax.swing.JButton();
        txtTrangThai = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNgayTao = new javax.swing.JTextField();
        btnXoa = new javax.swing.JButton();
        txtTongTien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("HÓA ĐƠN CHI TIẾT");

        txtMaHD.setEditable(false);
        txtMaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHDActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Mã hóa đơn");

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ma san pham", "Ten san pham", "Gia ban", "So luong", "Thanh tien"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        btnInHD.setText("In hóa đơn");
        btnInHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHDActionPerformed(evt);
            }
        });

        txtTrangThai.setEditable(false);
        txtTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrangThaiActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Trạng thái");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Ngày tạo");

        txtNgayTao.setEditable(false);
        txtNgayTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayTaoActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa hóa đơn");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        txtTongTien.setEditable(false);
        txtTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Tổng tiền");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(215, 215, 215)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInHD, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInHD, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHDActionPerformed

    private void txtTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTrangThaiActionPerformed

    private void txtNgayTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayTaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayTaoActionPerformed

    private void btnInHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHDActionPerformed
        // TODO add your handling code here:
        inFilePDF();
    }//GEN-LAST:event_btnInHDActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if (txtTrangThai.getText().equalsIgnoreCase("Da huy")) {
            remove();
        } else {
            MsgBox.alert(this, "Chi xoa duoc hoa don da huy");
        }


    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInHD;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtTrangThai;
    // End of variables declaration//GEN-END:variables

    public String getNum(String num) {
        return num.substring(0, num.indexOf(".")) + " VND";
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

                Paragraph address = new Paragraph("Trạng Thái: " + txtTrangThai.getText());
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
                        .setFont(fontTitle).setBold().setFontColor(Color.WHITE)
                        .setFontSize(9)
                        .setBorder(Border.NO_BORDER));
                tableContent.addCell(new Cell().add("Số lượng")
                        .setBackgroundColor(new DeviceRgb(1, 181, 204)).setFont(fontTitle)
                        .setBold().setFontColor(Color.WHITE)
                        .setFontSize(9)
                        .setBorder(Border.NO_BORDER));
                tableContent.addCell(new Cell().add("Giá bán")
                        .setFontSize(9)
                        .setBackgroundColor(new DeviceRgb(1, 181, 204)).setFont(fontTitle)
                        .setBold().setFontColor(Color.WHITE)
                        .setBorder(Border.NO_BORDER));
                tableContent.addCell(new Cell().add("Thành tiền")
                        .setFontSize(9)
                        .setBackgroundColor(new DeviceRgb(1, 181, 204)).setFont(fontTitle)
                        .setBold().setFontColor(Color.WHITE)
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

                Paragraph tongTien = new Paragraph("Tổng tiền: " + txtTongTien.getText());
                tongTien.setFont(fontTitle).setFontSize(12f);

                document.add(tongTien);
                document.add(cachDong);

                JOptionPane.showMessageDialog(rootPane, "Hóa đơn đã được in");
                document.close();
                this.dispose();
            } catch (FileNotFoundException e) {
//                e.printStackTrace(System.out);
//                e.getMessage();
            }
        } catch (Exception e) {
//            e.printStackTrace(System.out);
//            e.getMessage();
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
            txtTongTien.setText(String.valueOf(tongTien) + " VND");
        } else {
            txtTongTien.setText(tongTien + " VND");
        }
    }
}
