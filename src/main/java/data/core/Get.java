package java.data.core;

import java.toro.EdgeResultSet;

public interface Get<T> {
    T result(EdgeResultSet resultSet, Integer column);
}
