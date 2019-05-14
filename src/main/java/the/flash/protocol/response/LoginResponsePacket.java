package the.flash.protocol.response;

import lombok.Data;
import the.flash.protocol.Packet;

import static the.flash.protocol.command.Command.LOGIN_RESPONSE;

public class LoginResponsePacket extends Packet {
    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
