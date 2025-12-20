package dev.synexus.discordlinkerpro.infrastructure.persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseManager {

    private final HikariDataSource dataSource;
    private static final Logger logger = Logger.getLogger(DatabaseManager.class.getName());

    public DatabaseManager(String host, String port, String database, String user, String pass) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false");
        config.setUsername(user);
        config.setPassword(pass);
        config.setMaximumPoolSize(10);
        config.addDataSourceProperty("allowPublicKeyRetrieval", "true");
        this.dataSource = new HikariDataSource(config);
        if (dataSource.isRunning()) logger.info("Database is running...");
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

    public void close() {
        if (dataSource != null ) this.dataSource.close();
    }
}
