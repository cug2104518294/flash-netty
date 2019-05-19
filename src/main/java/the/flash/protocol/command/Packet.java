package the.flash.protocol.command;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public abstract class Packet {
    /**
     * 协议版本  可以用来今后的协议升级  如ipv4和ipv6
     */
    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;

    /**
     * 通信的时候需要指明具体的请求操作  也就是所谓的指令
     */
    @JSONField(serialize = false)
    public abstract Byte getCommand();
}
