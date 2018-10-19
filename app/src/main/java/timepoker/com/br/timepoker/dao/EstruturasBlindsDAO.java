package timepoker.com.br.timepoker.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import timepoker.com.br.timepoker.database.ConnectionFactory;
import timepoker.com.br.timepoker.domain.EstruturaBlind;

/**
 * Created by PauLinHo on 24/06/2017.
 */

public class EstruturasBlindsDAO {

    private SQLiteDatabase conn = null;
    private Context context = null;

    public EstruturasBlindsDAO(Context context) {
        this.context = context;
    }


    public Long save(String nomeEstrutura,
                       int level, int small,
                       int big, Double min,
                       int ante) {

        //Sql de Inserção no BD
        StringBuffer sql = new StringBuffer();
        sql.append("insert into ESTRUTURAS ");
        sql.append("(nomeEstrutura, level, small, big, min, ante) ");
        sql.append("values ( ?, ?, ?, ?, ?, ? ");

        Long iResult = null;

        //abrindo a conexao com o Banco de DAdos
        if (conn == null || !conn.isOpen()) {
            conn = ConnectionFactory.getConnection(context);
        }

        try {
            SQLiteStatement pstm = conn.compileStatement(sql.toString());
            int i = 0;

            pstm.bindString(++i, nomeEstrutura);
            pstm.bindString(++i, String.valueOf(level));
            pstm.bindString(++i, String.valueOf(small));
            pstm.bindString(++i, String.valueOf(big));
            pstm.bindString(++i, String.valueOf(min));
            pstm.bindString(++i, String.valueOf(ante));


            iResult = pstm.executeInsert();

        } catch (Exception ex) {
            Log.i("TIME", "ERRO AO REALIZAR INSERÇÃO NO BD: " + ex.getMessage());
            iResult = 0l;
        }

        conn.close();

        return iResult;
    }


    public long update(int id, String nomeEstrutura,
                       int level, int small,
                       int big, Double min,
                       int ante) {

        long retorno = 0;

        //Sql de Inserção no BD
        StringBuffer sql = new StringBuffer();
        sql.append("update ESTRUTURAS ");
        sql.append("set nomeEstrutura = ?, level = ?, small = ?, big = ?, min = ?, ante = ? ");
        sql.append("where _id = ? and level = ?");

        //abrindo a conexao com o Banco de DAdos
        if (conn == null || !conn.isOpen()) {
            conn = ConnectionFactory.getConnection(context);
        }

        try {
            SQLiteStatement pstm = conn.compileStatement(sql.toString());
            int i = 0;
            pstm.bindString(++i, nomeEstrutura);
            pstm.bindString(++i, String.valueOf(level));
            pstm.bindString(++i, String.valueOf(small));
            pstm.bindString(++i, String.valueOf(big));
            pstm.bindString(++i, String.valueOf(min));
            pstm.bindString(++i, String.valueOf(ante));
            pstm.bindLong(++i, id);
            pstm.bindString(++i, String.valueOf(level));

            retorno = pstm.executeUpdateDelete();


        } catch (Exception ex) {
            Log.i("TIME", "ERRO AO REALIZAR UPDATE NO BD: " + ex.getMessage());
            retorno = 0l;
        }

        conn.close();

        return retorno;
    }


    public void delete(EstruturaBlind p) {

        try {
            StringBuffer sql = new StringBuffer();
            sql.append("delete from ESTRUTURAS where nomeEstrutura = ?");

            //Verifica se a connection é null
            if (conn == null || !conn.isOpen()) {
                conn = ConnectionFactory.getConnection(context);
            }

            SQLiteStatement stm = conn.compileStatement(sql.toString());
            stm.bindString(1, p.getNomeEstrutura());

            stm.executeUpdateDelete();

        } catch (Exception e) {
            Log.i("TIME", e.getMessage());
        }

        conn.close();

    }

    public EstruturaBlind findByNomeEstrutura(EstruturaBlind j) {

        //sql de select para o BD
        StringBuffer sql = new StringBuffer();
        sql.append("select * from ESTRUTURAS where nomeEstrutura = ?");

        EstruturaBlind p = null;

        //abrindo a conexao com o Banco de DAdos
        if (conn == null || !conn.isOpen()) {
            conn = ConnectionFactory.getConnection(context);
        }
        try {
            //Cursor que recebe todos as entidades cadastradas
            String[] arg = {String.valueOf(j.getNomeEstrutura())};
            Cursor cursor = conn.rawQuery(sql.toString(), arg);

            Map<Integer, Integer> mapSmalls = new HashMap<>();
            Map<Integer, Integer> mapBigs = new HashMap<>();
            Map<Integer, Double> mapMins = new HashMap<>();
            Map<Integer, Integer> mapAntes = new HashMap<>();
            Map<Integer, Integer> mapLevels = new HashMap<>();

            boolean isVazio = true;

            //Se houver primeiro mova para ele
            if (cursor.moveToFirst()) {
                isVazio = false;
                do {
                    getEstruturaBlind(cursor, mapLevels, mapSmalls, mapBigs, mapMins, mapAntes);

                } while (cursor.moveToNext()); //se existir proximo mova para ele
            }

            if(!isVazio){
                p = new EstruturaBlind(j.getNomeEstrutura(),
                        mapLevels,
                        mapSmalls,
                        mapBigs,
                        mapMins,
                        mapAntes);
            }

        } catch (Exception e) {
            Log.i("TIME", e.getMessage());
        }

        conn.close();
        return p;
    }



    public synchronized LinkedList<String> findAll() {

        LinkedList<String> nomes = new LinkedList<>();

        //abrindo a conexao com o Banco de DAdos
        if (conn == null || !conn.isOpen()) {
            conn = ConnectionFactory.getConnection(context);
        }

        //sql de select para o BD
        StringBuffer sql = new StringBuffer();
        sql.append("select nomeEstrutura from ESTRUTURAS group by (nomeEstrutura)");

        //Cursor que recebe todas as entidades cadastradas
        Cursor cursor = conn.rawQuery(sql.toString(), null);

        //Existe Dados?
        if (cursor.moveToFirst()) {
            do {

                nomes.add(cursor.getString(0));


            } while (cursor.moveToNext()); //se existir proximo mova para ele
        }

        conn.close();

        return nomes;
    }


    private void getEstruturaBlind(Cursor cursor,
                                             Map<Integer, Integer> mapLevels,
                                             Map<Integer, Integer> mapSmalls,
                                             Map<Integer, Integer> mapBigs,
                                             Map<Integer, Double> mapMins,
                                             Map<Integer, Integer> mapAntes) {

        EstruturaBlind p = new EstruturaBlind();
        int i = 0;
        int key = cursor.getInt(i);
        mapLevels.put(key, cursor.getInt(++i));
        mapSmalls.put(key, cursor.getInt(++i));
        mapBigs.put(key, cursor.getInt(++i));
        mapMins.put(key, cursor.getDouble(++i));
        mapAntes.put(key, cursor.getInt(++i));

    }

}
