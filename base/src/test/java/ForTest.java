import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyan on 2017/12/6.
 */
public class ForTest {

    @Test
    public void test() {
        List<String> strings = new ArrayList<>(10000000);
        for (int i = 0; i < 10000000; i++) {
            strings.add("test_" + i);
        }

        Instant forStart = Instant.now();
        for (String s : strings) {
             s = s + "12";
        }
        Instant forEach = Instant.now();
        long t1 = Duration.between(forStart, forEach).toMillis();
        System.out.println("for time ：" + t1);

        Instant bgnlamda = Instant.now();
        strings.forEach(s -> s = s + "12");
        Instant lamdaend = Instant.now();
        long t2 = Duration.between(bgnlamda, lamdaend).toMillis();
        System.out.println("lamda time ：" + t2);
    }
}
