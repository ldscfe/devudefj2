import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Properties;

import static com.udf.BASE.*;

public class testbase {
    public static void main(String[] args) {
        //UDEFLOGOFF = true;

        // log4j
        log("==== slf4j-log4j ====");
        log("Hello, World!");
        logwarn("Hello, World!");
        logerror("Hello, World!");

        // datetime
        log("==== datetime ====");
        logwarn(dt());
        log(dt("/:"));
        log(dt(8));
        log(dt("-:", 16));

        // hash
        log("==== hash ====");
        log(md5(null));
        log(md5("hello, World!"));
        log(hash("hello, World!", null));
        log(hash("hello, World!", "sha1"));
        log(hash("hello, World!", "sha2"));   // error: sha2 MessageDigest not available
        log(hash("hello, World!", "sha256"));
        log(hash("hello, World!", "sha512"));
        log(base64(null));
        log(base64("hello, World!"));
        log(ubase64("aGVsbG8sIFdvcmxkIQ=="));
        log(des("hello, World!"));
        log(udes("ZJ49zumHoYtt9oGaSeJWeQ"));   // error: Illegal base64 character 2d

        // trim
        log("==== trim ====");
        log(ltrim("==== isnull ====", "="));
        log(rtrim("==== isnull ====", "="));
        log(trim("==== isnull ====", "==="));

        // rep
        log("==== rep ====");
        String rep_s1 = "This is a test, a=%a% and b=%b%. %a% + %b% = %a%%b%";
        Map<String, String> rep_m1 = new HashMap<>();
        rep_m1.put("a", "123");
        rep_m1.put("b", "Hello, World!");
        log(rep(rep_s1, rep_m1));

        // arr2map
        log("==== arr2map ====");
        String[] a2 = {"--host", "1", "-p", "3306", "$ip", "127.0.0.1", "$$cmd", "'split from tab'", "--", "test null key"};
        log(arr2map(a2, "- -- $$ $"));
        log(arr2map(a2, null));

        // isnull
        log("==== isnull ====");
        String s1 = null;
        log(isnull(s1));
        s1 = " ";
        log(isnull(s1));
        List l1 = new ArrayList();
        log(isnull(l1));
        Map m1 = new HashMap();
        log(isnull(m1, false));
        Properties p1 = new Properties();
        p1.put("a", 1);
        log(isnull(p1));
        // nvl
        log("==== nvl ====");
        //List l1 = new ArrayList<>();
        log(nvl(l1));
        //Map<String, String> m1 = new HashMap<>();
        log(nvl(m1));
        m1.put("a","1");
        log(nvl(m1));
        log(nvl(null));

        // json
        log("==== json ====");
        s1 = "Hello, World!";
        log(json.toJson(s1));
        m1.put("a", 1);
        m1.put("b", 3);
        log(m1);
        log(json.toJson(m1));

        // Atomic
        log("==== Atomic ====");
        log(at(1));
        log(at(1));
        log(at());
        log(at(-1));
        log(at(-1));
        log(at());
        log(at(100));
        log(at());
        log(at(0));

        // str2map
        String[] str1 = new String[2];
        str1[0] = "a=1 b=2 hello, world b=5=3";
        str1[1] = "SQL='abc 123' b=\"3\"";
        log(str2map2(str1));
        log(str2map(""));
        log(str2map(str1[0]));

        // str2list
        log("==== str2list ====");
        log(str2list("a=1 b=2 c='d e 123'\\\n ef=0"));
        log(str2list("a=1;b=2;c='d 123';\\nef=0;", ';'));
        log(str2list("a=1;b=\"2;\"c='d 123';\\nef=0;", '\''));

        // str2map
        log("==== str2map ====");
        log(str2map("a=1 b=2 c='d 123'"));
        log(str2map("a:1,b:2, c:'d 123'", ",:"));

        log("==== list2map ====");
        log(list2map(str2list("a c e f"), str2list("1 2 3")));
//        List<String> lm1 = Arrays.asList("a", "b");
//        List<Integer> lm2 = Arrays.asList(1,2);
//        log(list2map(lm1, lm2));

        int LOOP = (int)Math.pow(10.0, 1.80);
        log("LOOP: " + LOOP);
        log(dt(20));
        for (int i=0; i<LOOP; i++) {
            reverse("Hello, World!");
        }
        log(reverse("Hello, World!"));
        log(dt(20));
        for (int i=0; i<LOOP; i++) {
            reverse2("Hello, World!");
        }
        log(reverse2("Hello, World!"));
        log(dt(20));
        for (int i=0; i<LOOP; i++) {
            reverse("Hello, World!");
        }
        log(reverse("Hello, World!"));
        log(dt(20));

        // str2map
        log(str2map("c d=2 a=1"));

        // passwd
        log("==== passwd ====");
        log(des(udes("FpP9usT8toDMsV/Du6D63Q", "DeiPd8ltuAE3")));
        log(des(udes("x2FrqqJZjIA", "DeiPd8ltuAE3")));
        log(des(udes("85zbBPvpaOnMsV/Du6D63Q==", "DeiPd8ltuAE3")));
        log(des(udes("x2FrqqJZjIA=", "DeiPd8ltuAE3")));

        // type
        log("==== type ====");
        log(type(s1));
        log(type(l1));
        log(type(m1));
        log(type(p1));
        log(type(null));

        int[] na1 = {1,2,3,4,5};
        String[] sa1 = {"a", "b"};
        log(arr(sa1, 0));
        log(arr(sa1));
        log(arr(na1));
    }
}
