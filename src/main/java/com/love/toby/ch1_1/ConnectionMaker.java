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
        Class.forName("org.mariadb.jdbc.Driver");
        log.info("DConnectionMaker");
        return DriverManager.getConnection("jdbc:mariadb://localhost:3306/toby", "root", "green502");
    }
}

@Slf4j
class NConnectionMaker implements ConnectionMaker {
    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        log.info("DConnectionMaker");
        return DriverManager.getConnection("jdbc:mariadb://localhost:3306/toby", "root", "green502");
    }
}