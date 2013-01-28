package toro;

import data.core.Get;

public class GetInteger implements Get<Integer> {
    public Integer result(EdgeResultSet resultSet, Integer column) {
        return resultSet.getInt(column);
    }
}
