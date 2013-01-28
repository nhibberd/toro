package toro;

import data.core.Action;
import data.core.Function;
import data.error.DatabaseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Statement {
    protected Connection connection;
    protected String sql;


    protected Statement() {
    }

    protected <A> A withStatement(Connection connection, String sql, Function<PreparedStatement, A> thunk) {
        this.connection = connection;
        this.sql = sql;
        PreparedStatement statement = statement();
        try {
            return thunk.apply(statement);
        } catch (Exception e) {
            rollback(statement);
            throw new DatabaseException(e);
        } finally {
            close(statement);
        }
    }

    protected void withStatement(Connection connection, String sql, Action<PreparedStatement> thunk) {
        this.connection = connection;
        this.sql = sql;
        PreparedStatement statement = statement();
        try {
            thunk.apply(statement);
        } catch (Exception e) {
            rollback(statement);
            throw new DatabaseException(e);
        } finally {
            close(statement);
        }
    }

    private void close(PreparedStatement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private void rollback(PreparedStatement statement) {
        try {
            statement.cancel();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private PreparedStatement statement() {
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }


}
