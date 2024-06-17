import java.util.*;
public class testarg {
    private static volatile String PROMPT = "$ > ";

    private static String host = "localhost";
    private static int port = 6379;
    private static boolean is_secure = false;
    private static boolean redis_wrapper = false;
    private static boolean cluster = false;
    private static String username = null;
    private static String passwd = null;
    private static volatile Thread[] SocketReader = null;

//    private static final Socket[] ClusterSlots = new Socket[16 * 1024];
    private static volatile String lastSended;
//    private static final ConcurrentHashMap<Socket, Object> Sockets = new ConcurrentHashMap<>();

    public static Map<String, String> parseArgs(String[] args) {
        Map<String, String> res = new HashMap<>();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            if (arg.startsWith("--")) {
                String optionName = arg.substring(2);
                String optionValue = (i + 1 < args.length && !args[i + 1].startsWith("-")) ? args[i + 1] : "";
                res.put(optionName, optionValue);
                i++;
            } else if (arg.startsWith("-")) {
                String optionName = arg.substring(1);
                String optionValue = "";
                if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                    optionValue = args[i + 1];
                    i++;
                }
                res.put(optionName, optionValue);
            }
        }

        return res;
    }
    private static void Usage() {
        System.out.println("\nClient [<-h|--host> host] [<-p|--port> port] <-s|--secure> <-r|--redis> <-c|--cluster> [<-u|--user> username] [-a password] [command]\n");
        System.out.println("For example:\n");
        System.out.println("Client.sh -r -p 6379\n");
        System.out.println("    or:\n");
        System.out.println("Client.sh -r -p 6379 -a 123456 set key1 value1\n");
        System.exit(1);
    }

    public static void main(String[] argv) throws Exception {

        Map<String, String> argMap = parseArgs(argv);
        System.out.println("Parsed arguments:");
        for (Map.Entry<String, String> entry : argMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

//        Socket socket1=createConnection("localhost",6379);
//        PackageInputStream pis1 = new PackageInputStream(socket1.getInputStream());
//        sendMessage(socket1,"cluster slots");
//        Object o1=Protocol.read(pis1);
//
//
//        host = "192.168.0.90";
//        port = 6379;
//        redis_wrapper = true;
//        cluster = true;

//        argv = new String[]{"get", "aaa"};

        List<String> commands = new ArrayList<>();

        for (int i = 0; i < argv.length; i++) {
            if ("-h".equalsIgnoreCase(argv[i])
                    || "--host".equalsIgnoreCase(argv[i])) {
                if (i + 1 < argv.length) {
                    host = argv[++i];
                } else {
                    System.out.println("invalid parameter -h");
                    Usage();
                }
            } else if ("-p".equalsIgnoreCase(argv[i])
                    || "--port".equalsIgnoreCase(argv[i])) {
                try {
                    port = Integer.parseInt(argv[++i]);
                } catch (Exception e) {
                    System.out.println("invalid parameter -p: " + e.getMessage());
                    Usage();
                }
            } else if ("-u".equalsIgnoreCase(argv[i])
                    || "--user".equalsIgnoreCase(argv[i])) {
                if (i + 1 < argv.length) {
                    username = argv[++i];
                } else {
                    System.out.println("invalid parameter -u");
                    Usage();
                }
            } else if ("-a".equalsIgnoreCase(argv[i])) {
                if (i + 1 < argv.length) {
                    passwd = argv[++i];
                } else {
                    System.out.println("invalid parameter -a");
                    Usage();
                }
            } else if ("-s".equalsIgnoreCase(argv[i])
                    || "--secure".equalsIgnoreCase(argv[i])) {
                is_secure = true;
            } else if ("-r".equalsIgnoreCase(argv[i])
                    || "--redis".equalsIgnoreCase(argv[i])) {
                redis_wrapper = true;
            } else if ("-c".equalsIgnoreCase(argv[i])
                    || "--cluster".equalsIgnoreCase(argv[i])) {
                redis_wrapper = true;
                cluster = true;
            } else if ("--help".equalsIgnoreCase(argv[i])) {
                Usage();
            } else {
                if (!argv[i].startsWith("-")) {
                    commands.add(argv[i]);
                } else {
                    System.out.println("Unknow parament: " + argv[i]);
                    Usage();
                }
            }
        }

        System.out.println(commands);
        System.out.println(host);
        System.out.println(port);
        System.out.println(username);
        System.out.println(passwd);
    }
}
