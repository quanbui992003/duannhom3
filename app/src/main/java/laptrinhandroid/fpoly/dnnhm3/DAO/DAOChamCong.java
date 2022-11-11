package laptrinhandroid.fpoly.dnnhm3.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import laptrinhandroid.fpoly.dnnhm3.Entity.BangLuong;
import laptrinhandroid.fpoly.dnnhm3.Entity.ChamCong;
import laptrinhandroid.fpoly.dnnhm3.JDBC.DbSqlServer;

public class DAOChamCong {
    Connection objConn;
    public DAOChamCong() {
        DbSqlServer db = new DbSqlServer(); // hàm khởi tạo để mở kết nối
        objConn = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
    }

    public boolean addChamCong(ChamCong chamCong) throws SQLException {
        Statement statement = objConn.createStatement();

        String s1 = "Insert into ChamCong(maNV, gioBatDau,gioKetThuc,ngay,xacNhanChamCong) values (" +
                "'" + chamCong.getMaNV() + "'," +
                "'" + chamCong.getGioBatDau() + "'," +
                "'" + chamCong.getGioKetThuc() + "'," +
                "'" + chamCong.getNgay() + "'," +
                "'" + chamCong.getXacNhanChamCong() + "'," +
                "')";
        if (statement.executeUpdate(s1) > 0) {
            statement.close();
            return true;
        }
        statement.close();
        return false;
    }

    public boolean updateChamCong(ChamCong chamCong) throws SQLException {
        String sql = "UPDATE ChamCong  SET " + "maNV = ?," + " gioBatDau =?," + "gioKetThuc=?" + ",ngay=?" + ",xacNhanChamCong=?" +  " WHERE maCong=?;";
        PreparedStatement preparedStatement = objConn.prepareStatement(sql);
        preparedStatement.setInt(1, chamCong.getMaNV());
        preparedStatement.setDate(2, (java.sql.Date) chamCong.getGioBatDau());
        preparedStatement.setDate(3, (java.sql.Date) chamCong.getGioKetThuc());
        preparedStatement.setDate(4, (java.sql.Date) chamCong.getNgay());
        preparedStatement.setInt(5, chamCong.getXacNhanChamCong());
        if (preparedStatement.executeUpdate(sql) > 0) {
            preparedStatement.close();
            return true;
        }
        return false;
    }


    public List<ChamCong> getListChamCong(String maNV) throws SQLException {
        List<ChamCong> list = new ArrayList<>();
        Statement statement = objConn.createStatement();// Tạo đối tượng Statement.
        String sql = " SELECT * FROM  ChamCong where maNV='"+maNV+"'";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new ChamCong(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getDate(4), rs.getDate(5), rs.getInt(6) ));// Đọc dữ liệu từ ResultSet
        }
        objConn.close();// Đóng kết nối
        return list;
    }
}