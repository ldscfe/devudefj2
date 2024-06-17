import com.udf.BASE;

import java.util.Vector;

import static com.udf.BASE.log;

public class testFibonacci extends BASE {
    static Long fb1(int n) {
        if (n == 1 || n == 2)
            return 1l;
        return fb1(n-1) + fb1(n-2);
    }

    // fibonacci
    static Long fb2(int N) {
        Long[] f = new Long[N+1];
        f[1] = f[2] = 1l;
        return fb2hp(f, N);
    }

    static Long fb2hp(Long[] f, int n) {
        if (n > 0 && f[n] == null)
            f[n] = fb2hp(f, n-1) + fb2hp(f, n-2);
        return f[n];
    }

    static long fb3(int n) {
        Long[] f = new Long[n+1];
        f[1] = f[2] = 1l;
        for (int i=3; i<=n; i++)
            f[i] = f[i-1] + f[i-2];
        return f[n];
    }

    static long fb4(int n) {
        if (n < 1) return -1;
        if (n < 3) return 1;
        long n1 = 1l, n2 = 1l, sum = 0l;
        for (long i=3; i<=n; i++) {
            sum = n1 + n2;
            n2 = n1;
            n1 = sum;
        }
        return sum;
    }


    public static void main(String[] args) {
        log(dt());
        log(fb1(45));
        log(dt());
        log(fb2(66));
        log(dt());
        log(fb3(66));
        log(dt());
        log(fb4(66));
        log(dt());
    }
}
