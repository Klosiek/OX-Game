package lab.data;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSource {
  private static HikariConfig config = new HikariConfig();
  private static HikariDataSource ds;

  static {
    config.setJdbcUrl("jdbc:hsqldb:file:db/rozgrywki");
    config.setUsername("admin");
    config.setPassword("admin");
    config.setMaximumPoolSize(1);
    ds = new HikariDataSource(config);
  }

  private DataSource() {
  }

  public static Connection getConnection() throws SQLException {
    return ds.getConnection();
  }
}
