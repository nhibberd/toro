package java.toro;

import java.data.core.FromDb;

public class TestObject implements FromDb<TestObject> {
    public Integer version;
    public String second;

    public TestObject(Integer version, String second) {
        this.version = version;
        this.second = second;
    }

    public TestObject() {

    }

    public TestObject from(EdgeResultSet resultSet) {
        return new TestObject(resultSet.getInt(1),resultSet.getString(2));
    }
}
