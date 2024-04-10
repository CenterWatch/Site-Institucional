package connection;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Connection {

    private JdbcTemplate dbConnection;
    BasicDataSource dataSource = new BasicDataSource();

    public Connection() {
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/cwdb");
        dataSource.setUsername("root");
        dataSource.setPassword("");

        dbConnection = new  JdbcTemplate(dataSource);
    }

    public JdbcTemplate getDbConnection() {
        return dbConnection;
    }
}
