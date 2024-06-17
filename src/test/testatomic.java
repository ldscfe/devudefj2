import java.util.concurrent.atomic.AtomicInteger;

import static com.udf.BASE.*;

public class testatomic {

    public static void main(String[] args) {
        log(VERSION);
        log(at(1000));
        log(at(1));
        log(at(1));
        log(at(-1));
        log(at(-1));
        log(at(0));
        log(at());
    }
}
