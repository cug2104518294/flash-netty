package the.flash.protocol.request;

import lombok.Data;
import the.flash.protocol.Packet;

import static the.flash.protocol.command.Command.MESSAGE_REQUEST;

public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
