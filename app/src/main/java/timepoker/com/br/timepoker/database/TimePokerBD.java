package timepoker.com.br.timepoker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by PauLinHo on 24/06/2017.
 */

/**
 * Classe que representa o BD do projeto
 */
public class TimePokerBD extends SQLiteOpenHelper {

    private static Integer VERSION_BD = 1;

    public TimePokerBD(Context context){

        super(context, "timePokerBD",null, VERSION_BD);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ScriptDropTable.excluirTabelaESTRUTURAS());

        db.execSQL(ScriptCreateTable.criarTabelaESTRUTURAS());

        Log.i("TIMER", "Connection create with success in onCREATE");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(ScriptDropTable.excluirTabelaESTRUTURAS());

        db.execSQL(ScriptCreateTable.criarTabelaESTRUTURAS());

        Log.i("TIMER", "Connection create with success in onUPDATE");
    }
}
