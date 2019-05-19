package the.flash.serialize;


/**
 * 序列化算法 其实主要就是可以根据转换算法 其实作用就好比是command接口一样
 */
public interface SerializerAlogrithm {
    /**
     * json 序列化
     */
    byte JSON = 1;
}
