package the.flash.protocol.command;

import static the.flash.protocol.command.Command.LOGIN_REQUEST;

public class LoginRequestPacket extends Packet {
    private Integer userId;

    private String username;

    private String password;

    public LoginRequestPacket() {
    }

    public LoginRequestPacket(Integer userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
