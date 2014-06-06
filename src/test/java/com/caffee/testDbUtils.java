package com.caffee;

import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Server;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

public class testDbUtils {
    private static final Logger log = Logger.getLogger(testDbUtils.class);
    private static final String H2_URL = "jdbc:h2:mem:tcp://localhost:9123/~/test;";
    private Server server;

    @BeforeSuite
    public void setUp() throws SQLException {
        server =  Server.createTcpServer("-tcpPort", "9123", "-tcpAllowOthers");
        server.start();
        String content = null;
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(H2_URL);
        dataSource.setUser("sa");
        try {
            content = new Scanner(new File("./src/test/resources/CreateTestBase.sql")).useDelimiter("\\Z").next();
            dataSource.getConnection().prepareStatement(content).execute();
            log.debug("Test DB initialized");
        } catch (SQLException | FileNotFoundException e) {
            log.error(e);
        }
    }

    @AfterSuite
    public void shutDown() {
        server.stop();
        log.debug("Test DB stopped");
    }
}
