package com.love.toby.ch1_1;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class UserDao {
    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(UserDto userDto) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();
        PreparedStatement ps = c.prepareStatement("INSERT INTO USERS(ID, NAME, PASSWORD) VALUES(?, ?, ?)");
        ps.setString(1, userDto.getId());
        ps.setString(2, userDto.getName());
        ps.setString(3, userDto.getPassword());
        ps.executeUpdate();
        close(ps, c);
    }

    public UserDto get(String id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();
        PreparedStatement ps = c.prepareStatement("SELECT * FROM USERS WHERE ID = ?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        UserDto userDto = new UserDto();
        userDto.setId(rs.getString("id"));
        userDto.setName(rs.getString("name"));
        userDto.setPassword(rs.getString("password"));
        rs.close();
        close(ps, c);
        return userDto;
    }

    void close(PreparedStatement ps, Connection c) throws SQLException {
        ps.close();
        c.close();
    }

    public void print(UserDto userDto) {
        log.info("id = {}", userDto.getId());
        log.info("name = {}", userDto.getName());
        log.info("pwd = {}", userDto.getPassword());
    }
}
