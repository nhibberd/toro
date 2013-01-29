package toro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private String url;
    private String username;
    private String password;

    public Connector(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    public <A> A withConnection(Function<Connection, A> f) {
        Connection c = connection();
        try {
            A a = f.apply(c);
            c.commit();
            return a;
        } catch (Exception e) {            
            rollback(c);
            throw new DatabaseException(e);
        } finally {
            close(c);
        }
    }

    public void withConnection(Action<Connection> f) {
        Connection c = connection();
        try {
            f.apply(c);
            c.commit();
        } catch (Exception e) {
            rollback(c);
            throw new DatabaseException(e);
        } finally {
            close(c);
        }
    }

    private void close(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private void rollback(Connection c) {
        try {
            c.rollback();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private Connection connection() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            return connection;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
