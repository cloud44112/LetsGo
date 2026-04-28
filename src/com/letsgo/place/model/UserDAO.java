package com.letsgo.place.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Connection conn;

    public UserDAO() throws Exception {
        conn = DBCP.getConnection();
    }

    public UserDAO(Connection conn) throws Exception {
        this.conn = conn;
    }

    // 로그인
    public UserVO login(String userID, String password) {

        UserVO user = null;


        try (PreparedStatement pstmt = conn.prepareStatement("LOGIN_SQL")) {
            pstmt.setString(1, userID);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("NAME");
                    String email = rs.getString("EMAIL");
                    user = new UserVO(userID, email, name, password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // 회원가입
    public boolean signup(String userID, String email, String name, String password) {


        try (PreparedStatement pstmt = conn.prepareStatement("SIGNUP_SQL")) {
            pstmt.setString(1, userID);
            pstmt.setString(2, email);
            pstmt.setString(3, name);
            pstmt.setString(4, password);

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 아이디 체크
    public boolean idcheck(String userID) {


        try (PreparedStatement pstmt = conn.prepareStatement("IDCHECK_SQL")) {
            pstmt.setString(1, userID);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
