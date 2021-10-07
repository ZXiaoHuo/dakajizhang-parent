import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;

/**
 * @author zhangqh
 * @date 2021/9/16 0016 9:47
 */
public class T {

    @Test
    public void e() {
        int uuid = new Random().nextInt();
        String lUUID = String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
        System.out.println(uuid);
        System.out.println(lUUID.substring(0,15));
    }
}
