package the.flash.util;

import java.util.UUID;

public class IDUtil {
    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    public static void main(String[] args) {
        System.out.println(randomId());
    }
}
