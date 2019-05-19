package the.flash.serialize;

import the.flash.serialize.impl.JSONSerializer;

public interface Serializer {

    //ByteBufAllocator接口里面也是类似的写法 有一种默认的实现方式
    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlogrithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}
