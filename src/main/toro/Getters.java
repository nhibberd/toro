package toro;


import data.core.Get;
public class Getters {
    public static Get<Boolean> getBoolean = new Get<Boolean>() {
        public Boolean result(EdgeResultSet resultSet, Integer column) {
            return resultSet.getBoolean(column);
        }
    };

    public static Get<String> getString = new Get<String>() {
        public String result(EdgeResultSet resultSet, Integer column) {
            return resultSet.getString(column);
        }
    };

    public static Get<Long> getLong = new Get<Long>(){
        public Long result(EdgeResultSet resultSet, Integer column) {
            return resultSet.getLong(column);
        }
    };

    public static Get<Integer> getInteger = new Get<Integer>() {
        public Integer result(EdgeResultSet resultSet, Integer column) {
            return resultSet.getInt(column);
        }
    }; 

    public static Get<Short> getShort = new Get<Short>() {
        public Short result(EdgeResultSet resultSet, Integer column) {
            return resultSet.getShort(column);
        }
    };

    public static Get<Float> getFloat = new Get<Float>() {
        public Float result(EdgeResultSet resultSet, Integer column) {
            return resultSet.getFloat(column);
        }
    };

    public static Get<Double> getDouble = new Get<Double>() {
        public Double result(EdgeResultSet resultSet, Integer column) {
            return resultSet.getDouble(column);
        }
    };
    
    public static Get<Byte> getByte = new Get<Byte>() {
        public Byte result(EdgeResultSet resultSet, Integer column) {
            return resultSet.getByte(column);
        }
    };


}
