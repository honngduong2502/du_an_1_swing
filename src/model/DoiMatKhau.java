/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.NguoiDungDAO;
import entity.NguoiDung;
import helper.Auth;
import helper.MsgBox;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.MessagingException;

public class DoiMatKhau extends javax.swing.JFrame {

    public DoiMatKhau() {
        initComponents();

    }
    int mpX;
    int mpY;
    NguoiDungDAO dao = new NguoiDungDAO();

    public static String tenND = "";

    public void doiMatKhau() {
        if (Auth.isLogin()) {
            String matKhau = pswPass.getText();
            String nhapLai = pswConfrimPass.getText();
            NguoiDung nd = dao.selectById(Auth.getUsername());
            if (Auth.user == null) {
                MsgBox.alert(this, "Sai tên đăng nhập!");

            } else if (!matKhau.equals(nhapLai)) {
                MsgBox.alert(this, "Xác nhận mật khẩu không đúng !");
            } else {
                Auth.user.setMatKhau(matKhau);

                dao.update(Auth.user);

                MsgBox.alert(this, "Đổi mật khẩu thành công !");
                this.dispose();
            }
        }
    }

    public boolean check() {
        if (pswPass.getText().isEmpty() || pswConfrimPass.getText().isEmpty()) {
            MsgBox.alert(this, "Không được bỏ trống !");
            return false;
        } else if (pswPass.getText().length() < 3) {
            MsgBox.alert(this, "Vui lòng nhập mật khẩu trên 3 ký tự !");
            return false;
        }
        return true;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnXacNhan = new newpackage.Button();
        pswPass = new TextAndPassFiled.MyPasswordField();
        pswConfrimPass = new TextAndPassFiled.MyPasswordField();
        pnlTitleBarr = new javax.swing.JPanel();
        lblExit = new javax.swing.JLabel();
        lblMini = new javax.swing.JLabel();
        checkHienMatKhau = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 3, 3, 3, new java.awt.Color(81, 145, 255)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Đổi mật khẩu");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 570, -1));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_password_32px.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, 30, -1));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_password_32px.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, 30, -1));

        btnXacNhan.setBackground(new java.awt.Color(81, 145, 255));
        btnXacNhan.setForeground(new java.awt.Color(255, 255, 255));
        btnXacNhan.setText("Đổi mật khẩu");
        btnXacNhan.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });
        jPanel1.add(btnXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 440, 49));

        pswPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        pswPass.setColor(new java.awt.Color(255, 255, 255));
        pswPass.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        pswPass.setHint("Mật khẩu mới");
        pswPass.setOpaque(false);
        pswPass.setSelectionColor(new java.awt.Color(51, 102, 255));
        jPanel1.add(pswPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 430, 35));

        pswConfrimPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        pswConfrimPass.setColor(new java.awt.Color(255, 255, 255));
        pswConfrimPass.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        pswConfrimPass.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        pswConfrimPass.setHint("Nhập lại mật khẩu mới");
        pswConfrimPass.setOpaque(false);
        pswConfrimPass.setSelectionColor(new java.awt.Color(51, 102, 255));
        jPanel1.add(pswConfrimPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 430, 35));

        pnlTitleBarr.setBackground(new java.awt.Color(81, 145, 255));
        pnlTitleBarr.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlTitleBarrMouseDragged(evt);
            }
        });
        pnlTitleBarr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlTitleBarrMousePressed(evt);
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

        javax.swing.GroupLayout pnlTitleBarrLayout = new javax.swing.GroupLayout(pnlTitleBarr);
        pnlTitleBarr.setLayout(pnlTitleBarrLayout);
        pnlTitleBarrLayout.setHorizontalGroup(
            pnlTitleBarrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTitleBarrLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblMini)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblExit))
        );
        pnlTitleBarrLayout.setVerticalGroup(
            pnlTitleBarrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblMini, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(pnlTitleBarr, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 0, 570, -1));

        checkHienMatKhau.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        checkHienMatKhau.setForeground(new java.awt.Color(102, 102, 102));
        checkHienMatKhau.setText("Hiện mật khẩu");
        checkHienMatKhau.setOpaque(false);
        checkHienMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkHienMatKhauActionPerformed(evt);
            }
        });
        jPanel1.add(checkHienMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void pnlTitleBarrMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTitleBarrMouseDragged

        // TODO add your handling code here:
        this.setLocation(
                getLocation().x + evt.getX() - mpX,
                getLocation().y + evt.getY() - mpY);
    }//GEN-LAST:event_pnlTitleBarrMouseDragged

    private void pnlTitleBarrMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTitleBarrMousePressed
        // TODO add your handling code here:
        mpX = evt.getX();
        mpY = evt.getY();
    }//GEN-LAST:event_pnlTitleBarrMousePressed

    private void checkHienMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkHienMatKhauActionPerformed
        if (checkHienMatKhau.isSelected()) {
            pswPass.setEchoChar((char) 0);
            pswConfrimPass.setEchoChar((char) 0);
        } else {
            pswPass.setEchoChar('*');
            pswConfrimPass.setEchoChar('*');

        }
    }//GEN-LAST:event_checkHienMatKhauActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        if (check()) {
            doiMatKhau();
        }
    }//GEN-LAST:event_btnXacNhanActionPerformed

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
            java.util.logging.Logger.getLogger(DoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DoiMatKhau().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private newpackage.Button btnXacNhan;
    private javax.swing.JCheckBox checkHienMatKhau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblMini;
    private javax.swing.JPanel pnlTitleBarr;
    private TextAndPassFiled.MyPasswordField pswConfrimPass;
    private TextAndPassFiled.MyPasswordField pswPass;
    // End of variables declaration//GEN-END:variables
}
