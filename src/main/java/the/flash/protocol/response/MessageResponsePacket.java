package the.flash.protocol.response;

import the.flash.protocol.Packet;

import static the.flash.protocol.command.Command.MESSAGE_RESPONSE;

public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {

        return MESSAGE_RESPONSE;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
