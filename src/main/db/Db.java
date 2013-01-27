package db;

import data.core.Action;
import data.core.Function;
import data.core.Option;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class Db {
    private final Statement statement = new Statement();

    public void executeUpdateObject(Connection connection, String sql, final Object o) {
        statement.withStatement(connection, sql, new Action<PreparedStatement>() {
            public void apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.setObject(1, o);
                z.executeUpdate();
            }
        });
    }

    public void executeUpdateObjects(Connection connection, String sql, final Object... os) {
        statement.withStatement(connection, sql, new Action<PreparedStatement>() {
            public void apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
                z.executeUpdate();
            }
        });
    }

    public void executeUpdate(Connection connection, String sql) {
        statement.withStatement(connection, sql, new Action<PreparedStatement>() {
            public void apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.executeUpdate();
            }
        });
    }

    public Boolean queryExists(Connection connection, String sql) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Boolean>() {
            public Boolean apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                return resultSet.next();
            }
        });
    }

    public Boolean queryExistsFromObject(Connection connection, String sql, final Object o) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Boolean>() {
            public Boolean apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.setObject(1, o);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                return resultSet.next();
            }
        });
    }

    public List<Object> queryLoopObject(Connection connection, String sql, final Integer columnNumber){
        return statement.withStatement(connection, sql, new Function<PreparedStatement, List<Object>>() {
            public List<Object> apply(PreparedStatement preparedStatement) {
                List<Object> list = new ArrayList<Object>();
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                while (resultSet.next()){
                    list.add(resultSet.getObject(columnNumber));
                }
                return list;
            }
        });
    }

    public Option<Object> queryObject(Connection connection, String sql, final Integer column) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<Object>>() {
            public Option<Object> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(resultSet.getObject(column));
                return Option.none();
            }
        });
    }

    public Option<Object> queryFromObject(Connection connection, String sql, final Object data, final Integer column) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<Object>>() {
            public Option<Object> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.setObject(1,data);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(resultSet.getObject(column));
                return Option.none();
            }
        });
    }

    public Option<Object> queryObjectFromObject(Connection connection, String sql, final Object data, final Integer column) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<Object>>() {
            public Option<Object> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.setObject(1,data);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(resultSet.getObject(column));
                return Option.none();
            }
        });
    }

    public Option<Object> queryObjectFromObjects(Connection connection, String sql, final Integer column, final Object... os) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<Object>>() {
            public Option<Object> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(resultSet.getObject(column));
                return Option.none();
            }
        });
    }
}
