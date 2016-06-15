package com.raphael.diego.palavras;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BdGame extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "rank";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String PONTUACAO = "pontuacao";
    public static final int VERSAO = 1;

    public BdGame(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " +TABELA+" ("
                    + ID + " integer primary key autoincrement, "
                    + NOME + " text, "
                    + PONTUACAO + " int"
                    + ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        db.execSQL(sql);
        onCreate(db);
    }
}
