package Login.ResponseBody;

public class LoginResponse {
    private Data data;
    private String isSuccess;
    private int statusCode;
    private Error error;
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String success) {
        isSuccess = success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }


}
