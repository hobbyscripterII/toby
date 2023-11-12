package com.love.toby.ch1_1;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface ConnectionMaker {
    public Connection makeConnection() throws ClassNotFoundException, SQLException;
}

@Slf4j
class DConnectionMaker implements ConnectionMaker {
    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        log.info("DConnectionMaker");
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
    }
}

@Slf4j
class NConnectionMaker implements ConnectionMaker {
    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        log.info("NConnectionMaker");
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
    }
}