package shared;


public class ResponseStatus<T> {

    private final boolean success;
    private final String responseMsg;
    private final T result;

    public ResponseStatus(boolean success, String responseMsg, T result) {
        this.success = success;
        this.responseMsg = responseMsg;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public T getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "ResponseStatus{" +
                "success=" + success +
                ", responseMsg='" + responseMsg + '\'' +
                '}';
    }
}
