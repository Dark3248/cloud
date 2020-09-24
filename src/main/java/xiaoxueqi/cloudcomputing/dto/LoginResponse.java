package xiaoxueqi.cloudcomputing.dto;

public class LoginResponse {

    int code;
    int id;
    String username;
    Byte type;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "code=" + code +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", type=" + type +
                '}';
    }
}
