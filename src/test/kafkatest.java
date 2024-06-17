import com.udf.KAFKA;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class kafkatest {
    private static final Logger logger = Logger.getLogger(kafkatest.class);

    public static void main(String[] args) {
        String topic1 = "default-86_56789_410-wJUxSQ24030700105";
        String group1 = "test";

        KAFKA k1 = new KAFKA("kafkap182");

        // 2024-04-30 10:21:54,599 ERROR [com.udf.KAFKA] Consumer closed.
        k1.reset(topic1,group1, 0);

        // topic list
        List<String> l1 = k1.getTopics();
        for (String s1 : l1) {
            logger.info(String.format("%s, %s", s1, k1.getos(s1, group1).toString()));
        }

        logger.info(k1.getos(topic1, group1));
        //k1.get(topic1, group1, 2);
        k1.get(topic1, group1);
        logger.info(k1.cs);
        System.out.println(k1.val);
        System.out.println(k1.jval);
        logger.info(k1.getos(topic1, group1));

        // producer
//        ArrayList<String> ldat1 = new ArrayList<String>();
//        ldat1.addAll(Arrays.asList("a", "b", "c", "d"));
//        k1.put("test", ldat1);
    }
}
