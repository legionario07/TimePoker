package timepoker.com.br.timepoker.database;

import android.util.Log;

/**
 * Created by PauLinHo on 24/06/2017.
 */

public class ScriptDropTable {


    public static String excluirTabelaESTRUTURAS() {

        StringBuffer sql = new StringBuffer();
        sql.append("DROP TABLE IF EXISTS ESTRUTURAS");

        Log.i("TIMER", sql.toString());

        return sql.toString();
    }

}


