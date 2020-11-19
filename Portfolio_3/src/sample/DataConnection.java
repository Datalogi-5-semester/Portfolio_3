package sample;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class DataConnection {
    Connection connection = null;
    Statement statement = null;
    String url;

    public DataConnection(String url) throws SQLException {
        this.url = url;
        connect(url);
    }

    public void connect(String url) throws SQLException {
        connection = getConnection(this.url);
    }

    public void createStatement() throws SQLException {
        this.statement = connection.createStatement();
    }
}
