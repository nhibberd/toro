package toro;

import data.core.Get;

public class GetString implements Get<java.lang.String> {
    public String result(EdgeResultSet resultSet, Integer column) {
        return resultSet.getString(column);
    }
}
