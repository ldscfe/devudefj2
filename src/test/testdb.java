import com.udf.CNF;
import com.udf.DB;

import java.util.HashMap;
import java.util.Map;

import static com.udf.BASE.*;

public class testdb {

    public static void main(String[] args) {
        String config, dbname, sql;

        Map<String, String> mPara = new HashMap<>();
        config = mPara.getOrDefault("sql", "default.sql");
        CNF cnf1 = new CNF(config);
        // Get sql list for key from default.sql
        Map<String, String> sqlList = new HashMap<>();
        cnf1.put(sqlList, "h2_test");
        log(sqlList.toString());

        // conn db
//        dbname = "h2mem1";
        dbname = "postgrep182";

        DB db1 = new DB(dbname);

        sql ="select * from test";
        db1.execSQL(sql);
        log(db1.jval2);

        sql = sqlList.get("1");
        db1.execute(sql);
        sql = sqlList.get("2");
        db1.execute(sql);
        sql = sqlList.get("21");
        db1.execute(sql);
        sql = sqlList.get("3");
        db1.execSQL(sql);
        log(db1.col);
        log(db1.val);
        log(db1.val2);
        log(db1.jval2);
        log(db1.info);
        sql = sqlList.get("4");
        db1.execSQL(sql);
        log(db1.jval2);
        log(db1.info);
        log(db1.MaxRows);

    }
}
