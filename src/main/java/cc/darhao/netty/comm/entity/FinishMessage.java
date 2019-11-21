package cc.darhao.netty.comm.entity;

public class FinishMessage {

    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FinishMessage(String message) {
        this.message = message;
    }


}
