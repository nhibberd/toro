package toro;

public class GetBoolean implements Get<Boolean> {
    public Boolean result(EdgeResultSet resultSet, Integer column) {
        return resultSet.getBoolean(column);
    }
}
