import com.udf.BASE;

import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class testQueue extends BASE {
    static void queuePriority() {
        //PriorityQueue<String> sendQ1 = new PriorityQueue<>(Comparator.naturalOrder());
        PriorityQueue<String> sendQ1 = new PriorityQueue<>();

        sendQ1.add("00002,Hello, Java!");
        sendQ1.add("00012,abc123");
        sendQ1.add("00001,Hello, uq!");
        sendQ1.add("00000,Hello, World!");
        sendQ1.add("00001,Hello, UQ!");
        log(sendQ1);
        String s, s1;
        int n1;
        while(true) {
            s = sendQ1.poll();
            if(s==null) break;
            n1 = Integer.parseInt(s.substring(0, 5));
            s1 = s.substring(6);
            log(n1 + "-" + s1);
        }
    }
    static void queueArrayBlockingQueue() {
        //PriorityQueue<String> sendQ1 = new PriorityQueue<>(Comparator.naturalOrder());
        BlockingQueue<String> sendQ1 = new ArrayBlockingQueue<>(5);

        try {
            sendQ1.put("00002,Hello, Java!");
            sendQ1.put("00012,abc123");
            sendQ1.put("00000,Hello, World!");
            sendQ1.put("00001,Hello, UQ!");
            sendQ1.put("00001,Hello, uq!");
            // sendQ1.put("00003,Hello, 3!");    // Threads block until there is free space
            sendQ1.offer("00003,Hello, 3!");
            log(sendQ1.offer("00003,Hello, 3!"));
        }
        catch(Exception e) {
            log(e);
        }
        log(sendQ1);
        log(sendQ1.size());

        String s, s1;
        int n1;
        while(true) {
            s = sendQ1.poll();
            if(s==null) break;
            n1 = Integer.parseInt(s.substring(0, 5));
            s1 = s.substring(6);
            log(n1 + "-" + s1);
        }
    }

    public static void main(String[] args) {
        queuePriority();
        queueArrayBlockingQueue();
    }
}