package java.data.core;

public class Error {
    private String type;
    public String comment;

    public Error(String type, String comment) {
        this.type = type;
        this.comment = comment;
    }
}
