import com.udf.BASE;
import com.udf.CNF;
import com.udf.DB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class tidwbatch extends BASE {
    public static void main(String[] args) {

        Map<String, String> mPara = new HashMap<>();
        mPara = str2map2(args);
        log("Parameter: " + mPara.toString());

        String config, dbname, sql, sqlid, sqlapp;
        int i, j;

        // get SQL
        sqlid = mPara.get("sqlid");
        sqlapp = mPara.get("sqlapp");
        config = mPara.getOrDefault("sql", "default.sql");
        CNF cnf1 = new CNF(config);

        // Get sql list for key from default.sql
        HashMap<String, String> sqlList = new HashMap<String, String>();
        cnf1.put(sqlList, "tidat_task_identiry");
        //log4j.info(sqlList.toString());

        String pTime;
        pTime = dt("-:", 13);
        log(pTime);

        // conn db
        dbname = mPara.getOrDefault("db", "default");
        config = mPara.getOrDefault("config", "db.cnf");
        DB mysql1 = new DB(dbname, config);

        // Traverse Batch SQL
        for (i=1; i<=sqlList.size(); i++) {
            sql = sqlList.get(""+i);
            sql = sql.replace("%sqlid%", sqlid);
            sql = sql.replace("%sqlapp%", sqlapp);
            log("---- Begin ----");
            log(String.format("The %s SQL: %s" , i, sql));
            mysql1.execSQL(sql);
            log(mysql1.col);
            //log(mysql1.val.toString());

            String sql1 = "";
            ArrayList<String> LSql = new ArrayList<>();
            ArrayList<String> LType = new ArrayList<>();
            ArrayList<String> LKey = new ArrayList<>();
            ArrayList<String> LSeq = new ArrayList<>();
            for (j=0; j<Integer.parseInt(mysql1.info.get("cs")); j++) {
                LSql.add(mysql1.val.get(j).get(3));
                LType.add(mysql1.val.get(j).get(0));
                LKey.add(mysql1.val.get(j).get(1));
                LSeq.add(mysql1.val.get(j).get(2));
            }
            for (j=0; j<LSql.size(); j++) {
                sql1 = LSql.get(j);
                sql1 = sql1.replace("%p_time%", pTime);
                //log4j.info(sql1);
                int k = mysql1.execute(sql1);
                if (k==0)
                    logerror(String.format("%s/%s: %s - %s - %s - Error.", j+1, LSql.size(), LType.get(j), LKey.get(j), LSeq.get(j)));
                else
                    log(String.format("%s/%s: %s - %s - %s - Success.", j+1, LSql.size(), LType.get(j), LKey.get(j), LSeq.get(j)));
            }
        }

    }
}
