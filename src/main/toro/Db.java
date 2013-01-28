package toro;

import data.core.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class Db {
    private final Statement statement = new Statement();

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

    public <T> Option<T> queryX(Connection connection, String sql, final FromDb<T> object) {
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

    public Option<Short> queryShort(Connection connection, String sql, final Integer column) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<Short>>() {
            public Option<Short> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(resultSet.getShort(column));
                return Option.none();
            }
        });
    }

    public Option<Double> queryDouble(Connection connection, String sql, final Integer column) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<Double>>() {
            public Option<Double> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(resultSet.getDouble(column));
                return Option.none();
            }
        });
    }

    public Option<Float> queryFloat(Connection connection, String sql, final Integer column) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<Float>>() {
            public Option<Float> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(resultSet.getFloat(column));
                return Option.none();
            }
        });
    }

    public Option<Byte> queryByte(Connection connection, String sql, final Integer column) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<Byte>>() {
            public Option<Byte> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(resultSet.getByte(column));
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

    public Option<Short> queryShortFromObjects(Connection connection, String sql, final Integer column, final Object... os) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<Short>>() {
            public Option<Short> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(resultSet.getShort(column));
                return Option.none();
            }
        });
    }

    public Option<Double> queryDoubleFromObjects(Connection connection, String sql, final Integer column, final Object... os) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<Double>>() {
            public Option<Double> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(resultSet.getDouble(column));
                return Option.none();
            }
        });
    }

    public Option<Float> queryFloatFromObjects(Connection connection, String sql, final Integer column, final Object... os) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<Float>>() {
            public Option<Float> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(resultSet.getFloat(column));
                return Option.none();
            }
        });
    }

    public Option<Byte> queryByteFromObjects(Connection connection, String sql, final Integer column, final Object... os) {
        return statement.withStatement(connection, sql, new Function<PreparedStatement, Option<Byte>>() {
            public Option<Byte> apply(PreparedStatement preparedStatement) {
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                if(resultSet.next())
                    return Option.some(resultSet.getByte(column));
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

    public List<Short> queryListShort(Connection connection, String sql, final Integer columnNumber){
        return statement.withStatement(connection, sql, new Function<PreparedStatement, List<Short>>() {
            public List<Short> apply(PreparedStatement preparedStatement) {
                List<Short> list = new ArrayList<Short>();
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                while (resultSet.next()){
                    list.add(resultSet.getShort(columnNumber));
                }
                return list;
            }
        });
    }

    public List<Double> queryListDouble(Connection connection, String sql, final Integer columnNumber){
        return statement.withStatement(connection, sql, new Function<PreparedStatement, List<Double>>() {
            public List<Double> apply(PreparedStatement preparedStatement) {
                List<Double> list = new ArrayList<Double>();
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                while (resultSet.next()){
                    list.add(resultSet.getDouble(columnNumber));
                }
                return list;
            }
        });
    }

    public List<Float> queryListFloat(Connection connection, String sql, final Integer columnNumber){
        return statement.withStatement(connection, sql, new Function<PreparedStatement, List<Float>>() {
            public List<Float> apply(PreparedStatement preparedStatement) {
                List<Float> list = new ArrayList<Float>();
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                while (resultSet.next()){
                    list.add(resultSet.getFloat(columnNumber));
                }
                return list;
            }
        });
    }

    public List<Byte> queryListByte(Connection connection, String sql, final Integer columnNumber){
        return statement.withStatement(connection, sql, new Function<PreparedStatement, List<Byte>>() {
            public List<Byte> apply(PreparedStatement preparedStatement) {
                List<Byte> list = new ArrayList<Byte>();
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                while (resultSet.next()){
                    list.add(resultSet.getByte(columnNumber));
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

    public List<Short> queryListShortFromObjects(Connection connection, String sql, final Integer columnNumber, final Object... os){
        return statement.withStatement(connection, sql, new Function<PreparedStatement, List<Short>>() {
            public List<Short> apply(PreparedStatement preparedStatement) {
                List<Short> list = new ArrayList<Short>();
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                while (resultSet.next()){
                    list.add(resultSet.getShort(columnNumber));
                }
                return list;
            }
        });
    }

    public List<Double> queryListDoubleFromObjects(Connection connection, String sql, final Integer columnNumber, final Object... os){
        return statement.withStatement(connection, sql, new Function<PreparedStatement, List<Double>>() {
            public List<Double> apply(PreparedStatement preparedStatement) {
                List<Double> list = new ArrayList<Double>();
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                while (resultSet.next()){
                    list.add(resultSet.getDouble(columnNumber));
                }
                return list;
            }
        });
    }

    public List<Float> queryListFloatFromObjects(Connection connection, String sql, final Integer columnNumber, final Object... os){
        return statement.withStatement(connection, sql, new Function<PreparedStatement, List<Float>>() {
            public List<Float> apply(PreparedStatement preparedStatement) {
                List<Float> list = new ArrayList<Float>();
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                while (resultSet.next()){
                    list.add(resultSet.getFloat(columnNumber));
                }
                return list;
            }
        });
    }
    
    public List<Byte> queryListByteFromObjects(Connection connection, String sql, final Integer columnNumber, final Object... os){
        return statement.withStatement(connection, sql, new Function<PreparedStatement, List<Byte>>() {
            public List<Byte> apply(PreparedStatement preparedStatement) {
                List<Byte> list = new ArrayList<Byte>();
                EdgePreparedStatement z = new EdgePreparedStatement(preparedStatement);
                z.set(os);
                EdgeResultSet resultSet = new EdgeResultSet(z);
                while (resultSet.next()){
                    list.add(resultSet.getByte(columnNumber));
                }
                return list;
            }
        });
    }
}
