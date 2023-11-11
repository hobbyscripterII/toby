package com.love.toby.ch1_1;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class UserDaoVer1 {
    public void add(User user) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "", "");
        PreparedStatement ps = c.prepareStatement("INSERT INTO USERS(ID, NAME, PASSWORD) VALUES(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());
        ps.executeUpdate();
        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "", "");
        PreparedStatement ps = c.prepareStatement("SELECT * FROM USERS WHERE ID = ?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        rs.close();
        ps.close();
        c.close();
        return user;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserDaoVer1 userDao = new UserDaoVer1();
        User user = new User();
        user.setId("whiteship");
        user.setName("백기선");
        user.setPassword("married");
        userDao.add(user);

        log.info("id = {}", user.getId()); // 등록 성공

        User user2 = userDao.get(user.getId());
        log.info("name = {}", user.getName());
        log.info("pwd = {}", user.getPassword());
    }
}
