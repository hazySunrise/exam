package cc.darhao.netty.comm.entity;

public class QueryResponse {

    private String result;


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public QueryResponse(String result) {
        this.result = result;
    }

}
