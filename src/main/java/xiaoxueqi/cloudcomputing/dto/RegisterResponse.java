package xiaoxueqi.cloudcomputing.dto;

public class RegisterResponse {

    int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "RegisterResponse{" +
                "code=" + code +
                '}';
    }
}
