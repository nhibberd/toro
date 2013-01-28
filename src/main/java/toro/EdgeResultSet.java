package toro;

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

    protected Short getShort(int index) {
        try {
            return resultSet.getShort(index);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected Double getDouble(int index) {
        try {
            return resultSet.getDouble(index);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected Float getFloat(int index) {
        try {
            return resultSet.getFloat(index);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected Byte getByte(int index) {
        try {
            return resultSet.getByte(index);
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
