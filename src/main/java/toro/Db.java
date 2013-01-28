package toro;

import data.core.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class Db {
    private final Statement statement = new Statement();

    public void executeUpdate(Connection connection, String sql) {
        statement.withStatement(connection, sql, new Action<PreparedStatement>() {
            public void apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.executeUpdate();
            }
        });
    }

    public void updateObjects(Connection connection, String sql, final Object... os){
        statement.withStatement(connection, sql, new Action<PreparedStatement>() {
            public void apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
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

    public Boolean queryExistsFromObjects(Connection connection, String sql, final Object... os) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Boolean>() {
            public Boolean apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                return resultSet.next();
            }
        });
    }

    public <T> Option<T> query(Connection connection, String sql, final Get<T> get) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<T>>() {
            public Option<T> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(get.result(resultSet,1));
                return Option.none();
            }
        });
    }

    public <T> Option<T> query(Connection connection, String sql, final Integer column, final Get<T> get) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<T>>() {
            public Option<T> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(get.result(resultSet, column));
                return Option.none();
            }
        });
    }

    public <T> Option<T> queryObject(Connection connection, String sql, final FromDb<T> object) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<T>>() {
            public Option<T> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(object.from(resultSet));
                return Option.none();
            }
        });
    }


    public <T> Option<T> queryFromObjects(Connection connection, String sql, final Get<T> get, final Object... os) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<T>>() {
            public Option<T> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(get.result(resultSet,1));
                return Option.none();
            }
        });
    }

    public <T> Option<T> queryObjectFromObjects(Connection connection, String sql, final FromDb<T> object, final Object... os) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<T>>() {
            public Option<T> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(object.from(resultSet));
                return Option.none();
            }
        });
    }

    public <T> List<T> queryList(Connection connection, String sql, final Get<T> get, final Integer columnNumber) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, List<T>>() {
            public List<T> apply(PreparedStatement preparedStatement) {
                List<T> list = new ArrayList<T>();
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                while (resultSet.next()){
                    list.add(get.result(resultSet, columnNumber));
                }
                return list;
            }
        });
    }

    public <T> List<T> queryListObject(Connection connection, String sql, final FromDb<T> object) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, List<T>>() {
            public List<T> apply(PreparedStatement preparedStatement) {
                List<T> list = new ArrayList<T>();
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                while (resultSet.next()){
                    list.add(object.from(resultSet));
                }
                return list;
            }
        });
    }

    public <T> List<T> queryListObjectFromObjects(Connection connection, String sql, final FromDb<T> object, final Object... os) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, List<T>>() {
            public List<T> apply(PreparedStatement preparedStatement) {
                List<T> list = new ArrayList<T>();
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                while (resultSet.next()){
                    list.add(object.from(resultSet));
                }
                return list;
            }
        });
    }

}
