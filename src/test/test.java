import static com.udf.BASE.oscmd;
public class test {

    public static void main(String[] argv) {
        String cmd1, cmd2;
        cmd1 = "curl -k https://192.168.0.242:8000/v2/_catalog";
        cmd2 = "curl -p -k -u admin:Harbor12345 http://192.168.0.89:80/v2/_catalog";
        cmd1 = "sh /Users/adamlee/Documents/cpp/test.sh";
        cmd1 = "ls -l /Users/adamlee/Downloads";

        System.out.println(oscmd(cmd1, "GBK", "ABC"));
        System.out.println(oscmd(cmd1, "UTF-8", "ABC"));
        System.out.println(oscmd(cmd1));
    }
}