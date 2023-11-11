package com.love.toby.ch1_1;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

class NUserDao extends UserDaoVer3 {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "", "");
    }
}

class DUserDao extends UserDaoVer3 {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "", "");
    }
}

@Slf4j
public abstract class UserDaoVer3 {
    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("INSERT INTO USERS(ID, NAME, PASSWORD) VALUES(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());
        ps.executeUpdate();
        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();
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
        NUserDao nUserDao = new NUserDao();
        User user = new User();
        user.setId("whiteship");
        user.setName("백기선");
        user.setPassword("married");
        nUserDao.add(user);

        log.info("id = {}", user.getId()); // 등록 성공

        User user2 = nUserDao.get(user.getId());
        log.info("name = {}", user2.getName());
        log.info("pwd = {}", user2.getPassword());

        DUserDao dUserDao = new DUserDao();
        User user3 = new User();
        user3.setId("blackship");
        user3.setName("흑기선");
        user3.setPassword("married");
        dUserDao.add(user3);

        log.info("id = {}", user.getId()); // 등록 성공

        User user4 = dUserDao.get(user3.getId());
        log.info("name = {}", user4.getName());
        log.info("pwd = {}", user4.getPassword());
    }
}
