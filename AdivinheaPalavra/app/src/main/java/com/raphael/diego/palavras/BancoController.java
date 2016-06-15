package com.raphael.diego.palavras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Raphael on 09/05/2016.
 */
public class BancoController {
    private SQLiteDatabase db;
    private BdGame banco;

    public BancoController(Context context) {
        banco = new BdGame(context);
    }
    public String insereDado(String nome, int pontuacao) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BdGame.NOME, nome);
        valores.put(BdGame.PONTUACAO, pontuacao);

        resultado = db.insert(BdGame.TABELA, null, valores);
        db.close();

        if(resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro inserido com sucesso";
    }

    public Cursor listar() {
        Cursor cursor;
        String[] campos = {BdGame.ID, BdGame.NOME, BdGame.PONTUACAO};
        db = banco.getReadableDatabase();
        cursor = db.query(BdGame.TABELA, campos,null, null, null,null, BdGame.PONTUACAO + " DESC","5");
        if(cursor != null)
            cursor.moveToFirst();
        db.close();

        return cursor;
    }


}