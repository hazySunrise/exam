package cc.darhao.netty.comm.entity;

public class QueryRequest {

    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public QueryRequest(String message) {
        this.message = message;
    }
}
