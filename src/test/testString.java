import com.udf.BASE;

public class testString extends BASE {
    public static void main(String[] args) {

        int NLOOP = 10000;

        StringBuffer sf1 = new StringBuffer();
        StringBuilder sb1 = new StringBuilder();
        String s1 = new String();

        int i = 0;
        String tb1, te1;
        // String
        tb1 = dt(17);
        for (i=0; i<NLOOP; i++) {
            s1 = s1 + "0";
        }
        te1 = dt(17);
        log(String.format("String TB = %s, TE = %s, Len = %s", tb1, te1, s1.length()));

        // StringBuffer
        tb1 = dt(17);
        for (i=0; i<NLOOP; i++) {
            sf1 = sf1.append("0");
        }
        te1 = dt(17);
        log(String.format("StringBuffer TB = %s, TE = %s, Len = %s", tb1, te1, sf1.length()));


        // StringBuilder
        tb1 = dt(17);
        for (i=0; i<NLOOP; i++) {
            sb1 = sb1.append("0");
        }
        te1 = dt(17);
        log(String.format("StringBuffer TB = %s, TE = %s, Len = %s", tb1, te1, sb1.length()));
    }
}
