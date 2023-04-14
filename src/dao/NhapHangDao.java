package dao;

import entity.*;
import helper.XDate;
import helper.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhapHangDao extends CoffeeDao<NhapHang, String> {

    String insertNhaphang = "INSERT INTO NHAPHANG VALUES (?,?,?,?,?,?,?)";
    String updateNhapHang = "UPDATE NHAPHANG SET TenHangNhap = ?, Loai=?, soLieu = ?, soLuong = ?, NgayNhap = ?,GiaNhap = ? WHERE MaHangNhap = ?";
    String deleteNhaphang = "DELETE FROM NHAPHANG WHERE MaHangNhap=?";
    String selectAllNhaphang = "Select * from NHAPHANG";
    String selectByIdNhapHang = "SELECT * FROM NHAPHANG WHERE MaHangNhap=?";
    String updateSoLuongNhapHang = "UPDATE NHAPHANG SET soLuong = ? WHERE MaHangNhap = ?";

    @Override
    public void insert(NhapHang entity) {
        try {
            XJdbc.update(insertNhaphang,
                    entity.getMaNhap(),
                    entity.getTenSPNhap(),
                    entity.getLoaiSP(),
                    entity.getSoLieu(),
                    entity.getSoLuong(),
                    entity.getNgayNhap(),
                    entity.getTienNhap()
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(NhapHang entity) {
        try {
            XJdbc.update(updateNhapHang,
                    entity.getTenSPNhap(),
                    entity.getLoaiSP(),
                    entity.getSoLieu(),
                    entity.getSoLuong(),
                    entity.getNgayNhap(),
                    entity.getTienNhap(),
                    entity.getMaNhap()
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try {
            XJdbc.update(deleteNhaphang, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public NhapHang selectById(String id) {
        List<NhapHang> list = this.selectBySql(selectByIdNhapHang, id);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public List<NhapHang> selectAll() {
        return this.selectBySql(selectAllNhaphang);
    }

    @Override
    public List<NhapHang> selectBySql(String sqlString, Object... args) {
        List<NhapHang> list = new ArrayList<>();
        ResultSet rs;
        try {
            rs = XJdbc.query(sqlString, args);
            while (rs.next()) {
                NhapHang nhaphang = new NhapHang();
                nhaphang.setMaNhap(rs.getString(1));
                nhaphang.setTenSPNhap(rs.getString(2));
                nhaphang.setLoaiSP(rs.getString(3));
                nhaphang.setSoLieu(rs.getString(4));
                nhaphang.setSoLuong(rs.getFloat(5));
                nhaphang.setNgayNhap(XDate.toString(rs.getDate(6), "yyyy-MM-dd"));
                nhaphang.setTienNhap(rs.getFloat(7));
                list.add(nhaphang);
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<NhapHang> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM NHAPHANG WHERE MaHangNhap LIKE ? ";
        return this.selectBySql(sql, "%" + keyword + "%");
    }
}
