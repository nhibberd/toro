package data.core;

import toro.EdgeResultSet;

public interface Get<T> {
    T result(EdgeResultSet resultSet, Integer column);
}
