package timepoker.com.br.timepoker.database;

import android.util.Log;

/**
 * Created by PauLinHo on 24/06/2017.
 */

public class ScriptCreateTable {


    public static String criarTabelaESTRUTURAS() {


        StringBuffer sql = new StringBuffer();
        sql.append("CREATE TABLE ESTRUTURAS( ");
        sql.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql.append("nomeEstrutura varchar(45) not null unique, ");
        sql.append("level int not null, ");
        sql.append("small int not null, ");
        sql.append("big int not null, ");
        sql.append("min double not null, ");
        sql.append("ante int)");

        Log.i("TIMER", sql.toString());

        return sql.toString();
    }



}

