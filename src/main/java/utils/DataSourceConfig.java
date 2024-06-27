package utils;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataSourceConfig {
    private static BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl(Config.getProperty("db.url"));
        dataSource.setUsername(Config.getProperty("db.username"));
        dataSource.setPassword(Config.getProperty("db.password"));

    }

    public static BasicDataSource getDataSource() {
        return dataSource;
    }
}
