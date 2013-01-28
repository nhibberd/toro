package toro;

import data.core.Get;

public class GetLong implements Get<Long> {
    public Long result(EdgeResultSet resultSet, Integer column) {
        return resultSet.getLong(column);
    }
}
