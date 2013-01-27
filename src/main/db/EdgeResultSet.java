package db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EdgeResultSet {
    private ResultSet resultSet = null;

    public EdgeResultSet(EdgePreparedStatement delegate) {
        resultSet = delegate.executeQuery();
    }

    protected Boolean next(){
        try {
            return this.resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    protected String getString(String label) {
        try {
            return resultSet.getString(label);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected String getString(int index) {
        try {
            return resultSet.getString(index);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected Boolean getBoolean(int index) {
        try {
            return resultSet.getBoolean(index);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected Integer getInt(int index) {
        try {
            return resultSet.getInt(index);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected Long getLong(int index) {
        try {
            return resultSet.getLong(index);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    protected Object getObject(int index) {
        try {
            return resultSet.getObject(index);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
