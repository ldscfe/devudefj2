import java.util.Vector;
import static com.udf.BASE.str2list;

public class testSplit {
    private static final Vector<String> splitString(String src) {

        Vector<String> spliter = new Vector<>();

        if (src == null) {
            return spliter;
        }

        int begin = 0, end = 0;
        int len = src != null ? src.length() : 0;
        StringBuilder buf = new StringBuilder(128);

        //for (int i = 0; i < max; i++) {
        char seperater = ' ';
        while (true) {
            // 跳过连续找到非空格的第一个字符
            begin = end;// 不能加1，否则src的第一个字符可能会被漏掉
            while (begin < len) {
                if (seperater == ' ' && (src.charAt(begin) == '"' || src.charAt(begin) == '\'')) {
                    seperater = src.charAt(begin);
                    begin++;
                    break;
                } else if (src.charAt(begin) != seperater) {
                    break;
                }
                begin++;
            }

            // 找到下1个分隔字符
            end = begin;
            boolean shift = false;
            buf.setLength(0);
            if (end < len) {
                while (end < len) {
                    char b = src.charAt(end);
                    if (shift) {
                        // 被转义的字符
                        // \r 系统内有特殊用途
                        if (b == 'r') {
                            // "\\r"
                            buf.append('\r');
                        } else if (b == 'x' || b == 'X') {
                            // 例如："\x0d"
                            buf.append('\\');
                            buf.append(b);
                        } else {
                            buf.append(b);
                        }
                        shift = false;
                    } else if (b == '\\') {
                        // 转义符
                        shift = true;
                    } else if (b == seperater) {
                        if (seperater != ' ') {
                            seperater = ' ';
                            end++;
                        }
                        break;
                    } else {
                        buf.append(b);
                    }
                    end++;
                }
                spliter.add(buf.toString());
            }
            if (end >= len) {
                break;
            }
        }
        return spliter;
    }

    public static void main(String[] args)
    {
        String s1 = "ab ,' c d ' e";
        //s1 = "ab ,\" c d \" e";
        System.out.println(splitString(s1));
        System.out.println(splitString(s1).size());
        System.out.println(str2list(s1));
        System.out.println(str2list(s1).size());
    }
}
