package toro;

import data.core.Action;
import data.core.Function;
import data.core.Option;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class Db {
    private final Statement statement = new Statement();


   /*

    public <T> T query(Connection connection, String sql, final Get<T> get) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, T>() {
            public T apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    System.out.println(get.type());
                    //return resultSet.get(get);
                return null;
            }
        });
    }

    */

    public void updateObjects(Connection connection, String sql, final Object... os){
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

    public Option<String> queryString(Connection connection, String sql, final Integer column) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<String>>() {
            public Option<String> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(resultSet.getString(column));
                return Option.none();
            }
        });
    }

    public Option<Integer> queryInt(Connection connection, String sql, final Integer column) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<Integer>>() {
            public Option<Integer> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(resultSet.getInt(column));
                return Option.none();
            }
        });
    }

    public Option<Long> queryLong(Connection connection, String sql, final Integer column) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<Long>>() {
            public Option<Long> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(resultSet.getLong(column));
                return Option.none();
            }
        });
    }

    public Option<Boolean> queryBool(Connection connection, String sql, final Integer column) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<Boolean>>() {
            public Option<Boolean> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(resultSet.getBoolean(column));
                return Option.none();
            }
        });
    }

    public Option<String> queryStringFromObjects(Connection connection, String sql, final Integer column, final Object... os) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<String>>() {
            public Option<String> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(resultSet.getString(column));
                return Option.none();
            }
        });
    }

    public Option<Integer> queryIntegerFromObjects(Connection connection, String sql, final Integer column, final Object... os) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<Integer>>() {
            public Option<Integer> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(resultSet.getInt(column));
                return Option.none();
            }
        });
    }

    public Option<Long> queryLongFromObjects(Connection connection, String sql, final Integer column, final Object... os) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<Long>>() {
            public Option<Long> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(resultSet.getLong(column));
                return Option.none();
            }
        });
    }

    public Option<Boolean> queryBooleanFromObjects(Connection connection, String sql, final Integer column, final Object... os) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<Boolean>>() {
            public Option<Boolean> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(resultSet.getBoolean(column));
                return Option.none();
            }
        });
    }

    public List<String> queryListString(Connection connection, String sql, final Integer columnNumber){
        return statement.withStatement(connection, sql, new Function<PreparedStatement, List<String>>() {
            public List<String> apply(PreparedStatement preparedStatement) {
                List<String> list = new ArrayList<String>();
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                while (resultSet.next()){
                    list.add(resultSet.getString(columnNumber));
                }
                return list;
            }
        });
    }

    public List<Integer> queryListInt(Connection connection, String sql, final Integer columnNumber){
        return statement.withStatement(connection, sql, new Function<PreparedStatement, List<Integer>>() {
            public List<Integer> apply(PreparedStatement preparedStatement) {
                List<Integer> list = new ArrayList<Integer>();
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                while (resultSet.next()){
                    list.add(resultSet.getInt(columnNumber));
                }
                return list;
            }
        });
    }

    public List<Long> queryListLong(Connection connection, String sql, final Integer columnNumber){
        return statement.withStatement(connection, sql, new Function<PreparedStatement, List<Long>>() {
            public List<Long> apply(PreparedStatement preparedStatement) {
                List<Long> list = new ArrayList<Long>();
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                while (resultSet.next()){
                    list.add(resultSet.getLong(columnNumber));
                }
                return list;
            }
        });
    }

    public List<Boolean> queryListBoolean(Connection connection, String sql, final Integer columnNumber){
        return statement.withStatement(connection, sql, new Function<PreparedStatement, List<Boolean>>() {
            public List<Boolean> apply(PreparedStatement preparedStatement) {
                List<Boolean> list = new ArrayList<Boolean>();
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                while (resultSet.next()){
                    list.add(resultSet.getBoolean(columnNumber));
                }
                return list;
            }
        });
    }

    public List<String> queryListStringFromObjects(Connection connection, String sql, final Integer columnNumber, final Object... os){
        return statement.withStatement(connection, sql, new Function<PreparedStatement, List<String>>() {
            public List<String> apply(PreparedStatement preparedStatement) {
                List<String> list = new ArrayList<String>();
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                while (resultSet.next()){
                    list.add(resultSet.getString(columnNumber));
                }
                return list;
            }
        });
    }

    public List<Integer> queryListIntFromObjects(Connection connection, String sql, final Integer columnNumber, final Object... os){
        return statement.withStatement(connection, sql, new Function<PreparedStatement, List<Integer>>() {
            public List<Integer> apply(PreparedStatement preparedStatement) {
                List<Integer> list = new ArrayList<Integer>();
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                while (resultSet.next()){
                    list.add(resultSet.getInt(columnNumber));
                }
                return list;
            }
        });
    }

    public List<Long> queryListLongFromObjects(Connection connection, String sql, final Integer columnNumber, final Object... os){
        return statement.withStatement(connection, sql, new Function<PreparedStatement, List<Long>>() {
            public List<Long> apply(PreparedStatement preparedStatement) {
                List<Long> list = new ArrayList<Long>();
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                while (resultSet.next()){
                    list.add(resultSet.getLong(columnNumber));
                }
                return list;
            }
        });
    }

    public List<Boolean> queryListBooleanFromObjects(Connection connection, String sql, final Integer columnNumber, final Object... os){
        return statement.withStatement(connection, sql, new Function<PreparedStatement, List<Boolean>>() {
            public List<Boolean> apply(PreparedStatement preparedStatement) {
                List<Boolean> list = new ArrayList<Boolean>();
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                while (resultSet.next()){
                    list.add(resultSet.getBoolean(columnNumber));
                }
                return list;
            }
        });
    }
}
